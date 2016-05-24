// LogManagement.cpp : implementation file
//

#include "stdafx.h"
#include "BioStarCPP.h"
#include "LogManagement.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CLogManagement dialog


CLogManagement::CLogManagement(CWnd* pParent /*=NULL*/)
	: CDialog(CLogManagement::IDD, pParent)
{
	//{{AFX_DATA_INIT(CLogManagement)
	m_Device = _T("");
	m_NumOfLog = 0;
	//}}AFX_DATA_INIT
}


void CLogManagement::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CLogManagement)
	DDX_Control(pDX, IDC_LOG_LIST, m_LogList);
	DDX_Text(pDX, IDC_DEVICE, m_Device);
	DDX_Text(pDX, IDC_NUM_OF_LOG, m_NumOfLog);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CLogManagement, CDialog)
	//{{AFX_MSG_MAP(CLogManagement)
	ON_BN_CLICKED(IDC_READ, OnRead)
	ON_BN_CLICKED(IDC_DELETE_ALL, OnDeleteAll)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CLogManagement message handlers


void CLogManagement::setDevice( int handle, unsigned readerID, unsigned readerAddr )
{
	m_Handle = handle;
	m_ID = readerID;
	m_Addr = readerAddr;
}


bool CLogManagement::readLogInfo()
{
	BeginWaitCursor();
	
	BS_RET_CODE result = BS_GetLogCount( m_Handle, (int*)&m_NumOfLog );

	UpdateData( FALSE );

	EndWaitCursor();

	return (result == BS_SUCCESS);
}


void CLogManagement::updateLogRecord( int numOfLog, BSLogRecord* logRecord )
{
	m_LogList.DeleteAllItems();

	for( int i = 0; i < numOfLog; i++ )
	{
		CString value;

		struct tm logTime;

		if( (int)logRecord[i].eventTime > 0 )
		{
			memcpy( &logTime, gmtime( &logRecord[i].eventTime), sizeof( struct tm ) );

			value.Format( "%02d/%02d %02d:%02d:%02d", logTime.tm_mon + 1, logTime.tm_mday, logTime.tm_hour, logTime.tm_min, logTime.tm_sec );
		}
		else
		{
			value.Format( "" );
		}
		
		int listIndex = m_LogList.InsertItem( LVIF_TEXT | LVIF_PARAM, i, value, 0, 0, 0, logRecord[i].eventTime ); 
		
		if( listIndex != -1 )
		{
			value.Format( "%u", logRecord[i].userID );
			m_LogList.SetItem( listIndex, 1, LVIF_TEXT, value, 0, 0, 0, 0 );

			const char* eventStr = getEventString( logRecord[i].event );

			if( !eventStr )
			{
				value.Format( "%#x", logRecord[i].event );
				m_LogList.SetItem( listIndex, 2, LVIF_TEXT, value, 0, 0, 0, 0 );
			}
			else
			{
				m_LogList.SetItem( listIndex, 2, LVIF_TEXT, eventStr, 0, 0, 0, 0 );
			} 

			if( logRecord[i].tnaEvent != 0xffff )
			{
				if( logRecord[i].tnaEvent == 0x00 )
				{
					m_LogList.SetItem( listIndex, 3, LVIF_TEXT, "IN", 0, 0, 0, 0 );
				}
				else if( logRecord[i].tnaEvent == 0x01 )
				{
					m_LogList.SetItem( listIndex, 3, LVIF_TEXT, "OUT", 0, 0, 0, 0 );
				}
				else
				{
					value.Format( "%#x", logRecord[i].tnaEvent );
					m_LogList.SetItem( listIndex, 3, LVIF_TEXT, value, 0, 0, 0, 0 );
				}
			}

			if( logRecord[i].reserved2 == 0 )
			{
				m_LogList.SetItem( listIndex, 4, LVIF_TEXT, "local", 0, 0, 0, 0 );
			}
			else
			{
				value.Format( "%u", logRecord[i].reserved2 );
				m_LogList.SetItem( listIndex, 4, LVIF_TEXT, value, 0, 0, 0, 0 );
			}

		}
	}
}


void CLogManagement::addLogRecord( BSLogRecord* logRecord )
{
	CString value;

	struct tm logTime;
	memcpy( &logTime, gmtime( &logRecord->eventTime ), sizeof( struct tm ) );

	value.Format( "%02d/%02d %02d:%02d:%02d", logTime.tm_mon + 1, logTime.tm_mday, logTime.tm_hour, logTime.tm_min, logTime.tm_sec );
	int listIndex = m_LogList.InsertItem( LVIF_TEXT | LVIF_PARAM, m_LogList.GetItemCount(), value, 0, 0, 0, logRecord->eventTime ); 
		
	if( listIndex != -1 )
	{
		value.Format( "%10u", logRecord->userID );
		m_LogList.SetItem( listIndex, 1, LVIF_TEXT, value, 0, 0, 0, 0 );

		const char* eventStr = getEventString( logRecord->event );

		if( !eventStr )
		{
			value.Format( "%#x", logRecord->event );
			m_LogList.SetItem( listIndex, 2, LVIF_TEXT, value, 0, 0, 0, 0 );
		}
		else
		{
			m_LogList.SetItem( listIndex, 2, LVIF_TEXT, eventStr, 0, 0, 0, 0 );
		}

		if( logRecord->tnaEvent != 0xffff )
		{
			if( logRecord->tnaEvent == 0x00 )
			{
				m_LogList.SetItem( listIndex, 3, LVIF_TEXT, "IN", 0, 0, 0, 0 );
			}
			else if( logRecord->tnaEvent == 0x01 )
			{
				m_LogList.SetItem( listIndex, 3, LVIF_TEXT, "OUT", 0, 0, 0, 0 );
			}
			else
			{
				value.Format( "%#x", logRecord->tnaEvent );
				m_LogList.SetItem( listIndex, 3, LVIF_TEXT, value, 0, 0, 0, 0 );
			}
		}

		if( logRecord->reserved2 == 0 )
		{
			m_LogList.SetItem( listIndex, 4, LVIF_TEXT, "local", 0, 0, 0, 0 );
		}
		else
		{
			value.Format( "%u", logRecord->reserved2 );
			m_LogList.SetItem( listIndex, 4, LVIF_TEXT, value, 0, 0, 0, 0 );
		}
	}
}

BOOL CLogManagement::OnInitDialog() 
{
	CDialog::OnInitDialog();
	
	// TODO: Add extra initialization here
	// TODO: Add extra initialization here
	DWORD style = LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES;
	m_LogList.SendMessage(LVM_SETEXTENDEDLISTVIEWSTYLE, 0, LPARAM(style));

	m_LogList.InsertColumn( 1, "Time", LVCFMT_LEFT, 110, 0 );
	m_LogList.InsertColumn( 2, "ID", LVCFMT_LEFT, 70, 1 );
	m_LogList.InsertColumn( 3, "Event", LVCFMT_LEFT, 200, 2  );
	m_LogList.InsertColumn( 4, "TNA", LVCFMT_LEFT, 40, 3 );
	m_LogList.InsertColumn( 5, "Reader ID", LVCFMT_LEFT, 70, 4 );

	char buf[32];

	sprintf( buf, "%d.%d.%d.%d(%u)", m_Addr & 0xff, (m_Addr & 0xff00) >> 8, 
		(m_Addr & 0xff0000) >> 16, (m_Addr & 0xff000000) >> 24, m_ID );

	m_Device = buf;

	readLogInfo();
	
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}


const char* CLogManagement::getEventString( int eventCode )
{
	switch( eventCode ) {
	case BE_EVENT_SCAN_SUCCESS:
		return "Scan Success";

	case BE_EVENT_ENROLL_BAD_FINGER:
		return "Enroll Bad Finger";

	case BE_EVENT_ENROLL_SUCCESS:
		return "Enroll Success";

	case BE_EVENT_ENROLL_FAIL:
		return "Enroll Fail";

	case BE_EVENT_ENROLL_CANCELED:
		return "Enroll Canceled";

	case BE_EVENT_VERIFY_BAD_FINGER:
		return "Verify Bad Finger";

	case BE_EVENT_VERIFY_SUCCESS:
		return "Verify Success";

	case BE_EVENT_VERIFY_FAIL:
		return "Verify Fail";

	case BE_EVENT_VERIFY_CANCELED:
		return "Verify Canceled";

	case BE_EVENT_VERIFY_NO_FINGER:
		return "Verify No Finger";

	case BE_EVENT_IDENTIFY_BAD_FINGER:
		return "Identify Bad Finger";

	case BE_EVENT_IDENTIFY_SUCCESS:
		return "Identify Success";

	case BE_EVENT_IDENTIFY_FAIL:
		return "Identify Fail";

	case BE_EVENT_IDENTIFY_CANCELED:
		return "Identify Canceled";

	case BE_EVENT_DELETE_BAD_FINGER:
		return "Delete Bad Finger";

	case BE_EVENT_DELETE_SUCCESS:
		return "Delete Success";

	case BE_EVENT_DELETE_FAIL:
		return "Delete Fail";

	case BE_EVENT_DELETE_ALL_SUCCESS:
		return "Delete All";

	case BE_EVENT_VERIFY_DURESS:
		return "Verify Duress";

	case BE_EVENT_IDENTIFY_DURESS:
		return "Identify Duress";

	case BE_EVENT_TAMPER_SWITCH_ON:
		return "Tamper On";

	case BE_EVENT_TAMPER_SWITCH_OFF:
		return "Tamper Off";

	case BE_EVENT_SYS_STARTED:
		return "System Started";

	case BE_EVENT_IDENTIFY_NOT_GRANTED:
		return "Identify Not Granted";

	case BE_EVENT_VERIFY_NOT_GRANTED:
		return "Verify Not Granted";

	case BE_EVENT_IDENTIFY_LIMIT_COUNT:
		return "Identify Limit Count";

	case BE_EVENT_IDENTIFY_LIMIT_TIME:
		return "Identify Limit Time";

	case BE_EVENT_IDENTIFY_DISABLED:
		return "Identify Disabled";

	case BE_EVENT_IDENTIFY_EXPIRED:
		return "Identify Expired";

	case BE_EVENT_APB_FAIL:
		return "Anti-passback Failed";

	case BE_EVENT_COUNT_LIMIT:
		return "Entrance Limit Count";

	case BE_EVENT_TIME_INTERVAL_LIMIT:
		return "Entrance Time Interval";

	case BE_EVENT_INVALID_AUTH_MODE:
		return "Invalid Auth Mode";

	case BE_EVENT_EXPIRED_USER:
		return "Expired User";

	case BE_EVENT_NOT_GRANTED:
		return "Access Not Granted";

	case BS_EVENT_RELAY_ON:
		return "Relay On";

	case BS_EVENT_RELAY_OFF:
		return "Relay Off";

	case BE_EVENT_DOOR0_OPEN:
		return "Door 0 Open";

	case BE_EVENT_DOOR0_CLOSED:
		return "Door 0 Closed";

	case BE_EVENT_DOOR1_OPEN:
		return "Door 1 Open";

	case BE_EVENT_DOOR1_CLOSED:
		return "Door 1 Closed";

	case BE_EVENT_DOOR0_FORCED_OPEN:
		return "Door 0 Forced Open";

	case BE_EVENT_DOOR1_FORCED_OPEN:
		return "Door 1 Forced Open";

	case BE_EVENT_DOOR0_HELD_OPEN:
		return "Door 0 Held Open";

	case BE_EVENT_DOOR1_HELD_OPEN:
		return "Door 1 Held Open";

	case BE_EVENT_DOOR0_RELAY_ON:
		return "Door 0 Relay On";

	case BE_EVENT_DOOR1_RELAY_ON:
		return "Door 1 Relay On";
		
	case BE_EVENT_TIMEOUT:
		return "Timeout";

	// IO event
	case BE_EVENT_INTERNAL_INPUT0:
		return "Detect Internal Input 0";

	case BE_EVENT_INTERNAL_INPUT1:
		return "Detect Internal Input 1";

	case BE_EVENT_SLAVE_INPUT0:
		return "Detect Secondary Input 0";

	case BE_EVENT_SLAVE_INPUT1:
		return "Detect Secondary Input 1";

	case BE_EVENT_SIO0_INPUT0:
		return "Detect SIO 0 Input 0";

	case BE_EVENT_SIO0_INPUT1:
		return "Detect SIO 0 Input 1";
	
	case BE_EVENT_SIO0_INPUT2:
		return "Detect SIO 0 Input 2";

	case BE_EVENT_SIO0_INPUT3:
		return "Detect SIO 0 Input 3";

	case BE_EVENT_SIO1_INPUT0:
		return "Detect SIO 1 Input 0";

	case BE_EVENT_SIO1_INPUT1:
		return "Detect SIO 1 Input 1";

	case BE_EVENT_SIO1_INPUT2:
		return "Detect SIO 1 Input 2";

	case BE_EVENT_SIO1_INPUT3:
		return "Detect SIO 1 Input 3";

	case BE_EVENT_SIO2_INPUT0:
		return "Detect SIO 2 Input 0";

	case BE_EVENT_SIO2_INPUT1:
		return "Detect SIO 2 Input 1";

	case BE_EVENT_SIO2_INPUT2:
		return "Detect SIO 2 Input 2";

	case BE_EVENT_SIO2_INPUT3:
		return "Detect SIO 2 Input 3";

	case BE_EVENT_SIO3_INPUT0:
		return "Detect SIO 3 Input 0";

	case BE_EVENT_SIO3_INPUT1:
		return "Detect SIO 3 Input 1";

	case BE_EVENT_SIO3_INPUT2:
		return "Detect SIO 3 Input 2";

	case BE_EVENT_SIO3_INPUT3:
		return "Detect SIO 3 Input 3";

	case BE_EVENT_LOCKED:
		return "Locked";

	case BE_EVENT_UNLOCKED:
		return "Unlocked";

	case BE_EVENT_TIME_SET:
		return "Set Time";
		
	case BE_EVENT_SOCK_CONN:
		return "Connected";

	case BE_EVENT_SOCK_DISCONN:
		return "Disconnected";

	case BE_EVENT_SERVER_SOCK_CONN:
		return "Server Connected";

	case BE_EVENT_SERVER_SOCK_DISCONN:
		return "Server Disconnected";

	case BE_EVENT_LINK_CONN:
		return "Link Connected";

	case BE_EVENT_LINK_DISCONN:
		return "Link Disconnected";

	case BE_EVENT_INIT_IP:
		return "IP Initialized";

	case BE_EVENT_INIT_DHCP:
		return "DHCP Initialized";

	case BE_EVENT_DHCP_SUCCESS:
		return "DHCP Succeed";

	default:
		return NULL;
	}
}


void CLogManagement::OnRead() 
{
	// TODO: Add your control notification handler code here
	UpdateData();

	time_t startTime, endTime;

	startTime = 0;
	endTime = 0;

	BSLogRecord* logRecord = (BSLogRecord*)malloc( 16 * 50000 ); 

	BeginWaitCursor();

	int numOfLog;
	m_NumOfLog = 0;

	do {
		BS_RET_CODE result;
		
		if( m_NumOfLog == 0 )
		{
			result = BS_ReadLog( m_Handle, startTime, endTime, &numOfLog, logRecord );
		}
		else
		{
			result = BS_ReadNextLog( m_Handle, 0, 0, &numOfLog, logRecord + m_NumOfLog );
		}

		if( result != BS_SUCCESS )
		{
			MessageBox( "Cannot read log records" );
			break;
		}

		m_NumOfLog += numOfLog;
	} while( numOfLog == 8192 );

	updateLogRecord( m_NumOfLog, logRecord );

	EndWaitCursor();

	free( logRecord );

	UpdateData( FALSE );
}

void CLogManagement::OnDeleteAll() 
{
	// TODO: Add your control notification handler code here
	BeginWaitCursor();
	
	int numOfDeleted;
	if( BS_DeleteAllLog( m_Handle, 0, &numOfDeleted ) == BS_SUCCESS )
	{
		m_LogList.DeleteAllItems();
	}
	else
	{
		MessageBox( "Cannot delete log records" );
	}

	EndWaitCursor();	
}
