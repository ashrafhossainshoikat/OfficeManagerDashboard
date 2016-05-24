-- Oracle
-- =====================================================

-- GRANT insert, update, delete ON sdm.card to biostar;
-- GRANT insert ON sdm.customer_history to biostar;
-- GRANT  SELECT  ON sdm.customer_history_seq TO biostar;
/*
insert into TB_EVENT_DATA(NEVENTIDN, SNAME)
values(300, 'Punch-in failed, already punched-in')
*/

CREATE OR REPLACE TRIGGER trg_bs_usr_card
AFTER INSERT OR UPDATE OR DELETE
   ON tb_user
   FOR EACH ROW
   /*
	Auther: Md. Khalid Saifullah
	Purpose: To insert data in sdm.card whenever new User is created in BioStar interface
	Date: 12-Mar-2015
	Version: 1.0
   */
--DECLARE
   -- variable declarations

BEGIN
	IF INSERTING THEN
		INSERT INTO sdm.card(id, card_number, is_bsso_card, is_active, is_approved, create_date, created_by, edit_count)
		--VALUES(to_number(:new.suserid), :new.susername, 0, 0, 0, sysdate, 'biostar', 0);
		VALUES(:new.nuseridn, :new.susername, 0, 0, 0, sysdate, 'biostar', 0);
	END IF;
	
	IF UPDATING AND (:old.susername <> :new.susername) THEN
		UPDATE sdm.card SET card_number = :new.susername
		-- WHERE card_number = :old.susername;
		WHERE id = :old.nuseridn;
	END IF;
	
	IF DELETING THEN
		DELETE FROM sdm.card 
		WHERE id = :old.nuseridn;
	END IF;

--EXCEPTION
--   WHEN
END;
/


CREATE OR REPLACE TRIGGER trg_bs_event_cust_hist
BEFORE INSERT
	ON tb_event_log
	FOR EACH ROW
	/*
	Auther: Md. Khalid Saifullah
	Purpose: To insert data in sdm.customer_history whenever punch-in is successful
	Date: 12-Mar-2015
	Version: 1.0
	*/
DECLARE
   cdid number(22);
   cdno varchar2(96);
   cdcount number(2);

BEGIN
	IF (:new.neventidn = 47 ) THEN
		SELECT nuseridn, susername INTO cdid, cdno
		FROM tb_user
		WHERE :new.nuserid = to_number(suserid);
		
		-- ** Check if the card is already punched-in **
		SELECT count(*) INTO cdcount
		FROM sdm.customer_history
		WHERE card_id = cdid
		AND punch_out_time IS NULL;
		
		IF (cdcount > 0) THEN
			:new.neventidn := 300;
		ELSE
			INSERT INTO sdm.customer_history(id, card_id, card_number, punch_in_time, edit_count)
			VALUES(sdm.customer_history_seq.NEXTVAL, cdid, cdno, sysdate, 0);
		END IF;
	END IF;
END;
/



-- MS SQL
-- =====================================================
CREATE TRIGGER trg_bs_usr_card ON dbo.tb_user
FOR INSERT, UPDATE, DELETE
AS 
SET NOCOUNT ON;
BEGIN TRANSACTION 
IF EXISTS(SELECT * FROM INSERTED) AND NOT EXISTS(SELECT * FROM DELETED) --step1; if inserting  
BEGIN
  INSERT INTO sdm.dbo.card(id, card_number, is_bsso_card, is_active, is_approved, create_date, created_by, edit_count)
  SELECT INS.nUserIdn, INS.sUserName, 0, 0, 0, GETDATE(), 'biostar', 0
  FROM INSERTED INS
END

ELSE IF EXISTS(SELECT * FROM INSERTED) AND EXISTS(SELECT * FROM DELETED) --step2; if updating
BEGIN
  UPDATE sdm.dbo.card 
  SET card_number = (SELECT sUserName FROM  INSERTED) 
  WHERE id = (SELECT nUserIdn FROM  DELETED)
  
END

ELSE IF NOT EXISTS(SELECT * FROM INSERTED) AND EXISTS(SELECT * FROM DELETED) --step3; if deleting
BEGIN
  DELETE 
  FROM sdm.dbo.card 
  WHERE id = (SELECT nUserIdn FROM  DELETED)
END
    	
IF @@ERROR!=0
	ROLLBACK TRANSACTION
ELSE
	COMMIT TRANSACTION


CREATE TRIGGER trg_bs_event_cust_hist ON dbo.TB_EVENT_LOG
FOR INSERT
AS 
SET NOCOUNT ON;
BEGIN TRANSACTION 
DECLARE @nUserIdn INT
DECLARE @sUserName VARCHAR(192)
DECLARE @nEventLogIdn INT


IF EXISTS (SELECT * FROM INSERTED WHERE nEventIdn = 47)
BEGIN

	SELECT   @nUserIdn=US.nUserIdn
	     	,@sUserName= US.sUserName
	     	,@nEventLogIdn = nEventLogIdn
	FROM INSERTED INS
	INNER JOIN tb_user US ON CONVERT(INT,US.sUserID) =INS.nUserID

	IF EXISTS(SELECT * FROM sdm.dbo.customer_history WHERE  card_id= @nUserIdn AND punch_out_time IS NULL )--FOR UPDATE 
	BEGIN
		UPDATE TB_EVENT_LOG
		SET nEventIdn=40
		WHERE  
		nEventLogIdn=@nEventLogIdn
	END
	ELSE --FOR INSERT
	BEGIN
		INSERT INTO sdm.dbo.customer_history(card_id, card_number, punch_in_time, edit_count)
	    SELECT @nUserIdn, @sUserName, GETDATE() punch_in_time,0 edit_count
	END
END
    	
IF @@ERROR!=0
	ROLLBACK TRANSACTION
ELSE
	COMMIT TRANSACTION
	
	
	
	
	
	
	
	
	
	
	
alter TRIGGER trg_bs_event_cust_hist ON dbo.TB_EVENT_LOG
INSTEAD OF INSERT
AS 
SET NOCOUNT ON;
BEGIN TRANSACTION 
DECLARE @nUserIdn INT
DECLARE @sUserName VARCHAR(192)
DECLARE @nEventLogIdn INT

SELECT * INTO #Inserted FROM Inserted

IF EXISTS (SELECT * FROM INSERTED WHERE nEventIdn = 47)
BEGIN
	SELECT   @nUserIdn=US.nUserIdn
	     	,@sUserName= US.sUserName
	     	,@nEventLogIdn = INS.nEventLogIdn
	FROM INSERTED INS
	INNER JOIN tb_user US ON CONVERT(INT,US.sUserID) = INS.nUserID
    
	IF EXISTS(SELECT * FROM sdm.dbo.customer_history WHERE  card_id=@nUserIdn AND punch_out_time IS NULL )--FOR UPDATE 
	BEGIN
        UPDATE #Inserted SET nEventIdn = 40
        INSERT INTO TB_EVENT_LOG  
        (
        nDateTime
        ,nReaderIdn
        ,nUserID
        ,nIsLog
        ,nTNAEvent
        ,nIsUseTA
        ,nType
        ) 
        SELECT 
        nDateTime
        ,nReaderIdn
        ,nUserID
        ,nIsLog
        ,nTNAEvent
        ,nIsUseTA
        ,nType  
        FROM #Inserted
	END
	ELSE --FOR INSERT
	BEGIN
		INSERT INTO sdm.dbo.customer_history(card_id, card_number, punch_in_time, edit_count)
	    SELECT @nUserIdn, @sUserName, GETDATE() punch_in_time, 0 edit_count
	    
        INSERT INTO TB_EVENT_LOG  
        (
        nDateTime
        ,nReaderIdn
        ,nUserID
        ,nIsLog
        ,nTNAEvent
        ,nIsUseTA
        ,nType
        ) 
        SELECT 
        nDateTime
        ,nReaderIdn
        ,nUserID
        ,nIsLog
        ,nTNAEvent
        ,nIsUseTA
        ,nType  
        FROM Inserted
	END
END
ELSE
BEGIN
	INSERT INTO TB_EVENT_LOG  
        (
        nDateTime
        ,nReaderIdn
        ,nUserID
        ,nIsLog
        ,nTNAEvent
        ,nIsUseTA
        ,nType
        ) 
        SELECT 
        nDateTime
        ,nReaderIdn
        ,nUserID
        ,nIsLog
        ,nTNAEvent
        ,nIsUseTA
        ,nType  
        FROM Inserted
END
    	
IF @@ERROR!=0
	ROLLBACK TRANSACTION
ELSE
	COMMIT TRANSACTION