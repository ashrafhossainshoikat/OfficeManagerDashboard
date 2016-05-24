// UserManagement.cpp : implementation file
//

#include "stdafx.h"
#include "BioStarCPP.h"
#include "UserManagement.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

#define TEMPLATE_SIZE	384

/////////////////////////////////////////////////////////////////////////////
// CUserManagement dialog


CUserManagement::CUserManagement(CWnd* pParent /*=NULL*/)
	: CDialog(CUserManagement::IDD, pParent)
{
	//{{AFX_DATA_INIT(CUserManagement)
	m_AdminLevel = -1;
	m_AuthMode = -1;
	m_CardID = 0;
	m_CardType = -1;
	m_Checksum1 = 0;
	m_Checksum2 = 0;
	m_CustomID = 0;
	m_Device = _T("");
	m_Duress1 = FALSE;
	m_Duress2 = FALSE;
	m_ExpiryDate = 0;
	m_Finger1 = FALSE;
	m_Finger2 = FALSE;
	m_UserID = 0;
	m_Name = _T("");
	m_NumOfTemplate = 0;
	m_NumOfUser = 0;
	m_SecurityLevel = -1;
	m_StartDate = 0;
	m_AccessGroup = _T("");
	//}}AFX_DATA_INIT

	m_TemplateData = (unsigned char*)malloc( TEMPLATE_SIZE * 2 * 2 );
}


void CUserManagement::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CUserManagement)
	DDX_Control(pDX, IDC_USER_LIST, m_UserList);
	DDX_CBIndex(pDX, IDC_ADMIN_LEVEL, m_AdminLevel);
	DDX_CBIndex(pDX, IDC_AUTH_MODE, m_AuthMode);
	DDX_Text(pDX, IDC_CARD_ID, m_CardID);
	DDX_CBIndex(pDX, IDC_CARD_TYPE, m_CardType);
	DDX_Text(pDX, IDC_CHECKSUM1, m_Checksum1);
	DDX_Text(pDX, IDC_CHECKSUM2, m_Checksum2);
	DDX_Text(pDX, IDC_CUSTOM_ID, m_CustomID);
	DDX_Text(pDX, IDC_DEVICE, m_Device);
	DDX_Check(pDX, IDC_DURESS1, m_Duress1);
	DDX_Check(pDX, IDC_DURESS2, m_Duress2);
	DDX_DateTimeCtrl(pDX, IDC_EXPIRY_DATE, m_ExpiryDate);
	DDX_Check(pDX, IDC_FINGER1, m_Finger1);
	DDX_Check(pDX, IDC_FINGER2, m_Finger2);
	DDX_Text(pDX, IDC_ID, m_UserID);
	DDX_Text(pDX, IDC_NAME, m_Name);
	DDX_Text(pDX, IDC_NUM_OF_TEMPLATE, m_NumOfTemplate);
	DDX_Text(pDX, IDC_NUM_OF_USER, m_NumOfUser);
	DDX_CBIndex(pDX, IDC_SECURITY_LEVEL, m_SecurityLevel);
	DDX_DateTimeCtrl(pDX, IDC_START_DATE, m_StartDate);
	DDX_Text(pDX, IDC_ACCESS_GROUP, m_AccessGroup);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CUserManagement, CDialog)
	//{{AFX_MSG_MAP(CUserManagement)
	ON_NOTIFY(NM_CLICK, IDC_USER_LIST, OnClickUserList)
	ON_BN_CLICKED(IDC_DELETE, OnDelete)
	ON_BN_CLICKED(IDC_DELETE_ALL, OnDeleteAll)
	ON_BN_CLICKED(IDC_REFRESH, OnRefresh)
	ON_BN_CLICKED(IDC_UPDATE, OnUpdate)
	ON_BN_CLICKED(IDC_ADD, OnAdd)
	ON_BN_CLICKED(IDC_READ_CARD, OnReadCard)
	ON_WM_DESTROY()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CUserManagement message handlers

BOOL CUserManagement::OnInitDialog() 
{
	CDialog::OnInitDialog();
	
	// TODO: Add extra initialization here
	DWORD style = LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES;
	m_UserList.SendMessage(LVM_SETEXTENDEDLISTVIEWSTYLE, 0, LPARAM(style));

	m_UserList.InsertColumn( 1, "ID", LVCFMT_LEFT, 70, 0 );
	m_UserList.InsertColumn( 2, "Finger", LVCFMT_CENTER, 60, 1  );
	m_UserList.InsertColumn( 3, "Card ID", LVCFMT_CENTER, 80, 2  );
	
	// TODO: Add extra initialization here
	char buf[32];

	sprintf( buf, "%d.%d.%d.%d(%u)", m_DeviceAddr & 0xff, (m_DeviceAddr & 0xff00) >> 8, 
		(m_DeviceAddr & 0xff0000) >> 16, (m_DeviceAddr & 0xff000000) >> 24, m_DeviceID );

	m_Device = buf;

	if( !getUserInfo() )
	{
		MessageBox( "Cannot get user info" );
	}
	
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}


void CUserManagement::setDevice( int handle, unsigned deviceID, unsigned deviceAddr, int deviceType )
{
	m_Handle = handle;
	m_DeviceID = deviceID;
	m_DeviceAddr = deviceAddr;
	m_DeviceType = deviceType;
}


bool CUserManagement::getUserInfo()
{
	m_NumOfUser = 0;
	m_NumOfTemplate = 0;

	m_UserList.DeleteAllItems();

	BeginWaitCursor();

	if( m_DeviceType == BS_DEVICE_BIOENTRY_PLUS || m_DeviceType == BS_DEVICE_BIOLITE )
	{
		BEUserHdr* userHdr = NULL;

		BS_RET_CODE result = BS_GetUserDBInfo( m_Handle, (int*)&m_NumOfUser, (int*)&m_NumOfTemplate );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();
			return false;
		}

		userHdr = (BEUserHdr*)malloc( sizeof(BEUserHdr) * m_NumOfUser );

		result = BS_GetAllUserInfoBEPlus( m_Handle, userHdr, (int*)&m_NumOfUser );

		if( result != BS_SUCCESS && result != BS_ERR_NOT_FOUND )
		{
			EndWaitCursor();

			free( userHdr );

			return false;
		}

		for( int i = 0; i < m_NumOfUser; i++ )
		{
			CString value;
			value.Format( "%10u", userHdr[i].userID );
			int listIndex = m_UserList.InsertItem( LVIF_TEXT | LVIF_PARAM, i, value, 0, 0, 0, userHdr[i].userID );

			if( listIndex != -1 )
			{
				value.Format( "%d", userHdr[i].numOfFinger );
				m_UserList.SetItem( listIndex, 1, LVIF_TEXT, value, 0, 0, 0, 0 );

				value.Format( "%#x", userHdr[i].cardID );
				m_UserList.SetItem( listIndex, 2, LVIF_TEXT, value, 0, 0, 0, 0 );
			}
		}

		UpdateData( FALSE );

		EndWaitCursor();

		if( m_NumOfUser > 0 )
		{
			getUserInfo( userHdr[0].userID );
		}

		free( userHdr );
	}
	else if( m_DeviceType == BS_DEVICE_BIOSTATION )
	{
		BSUserHdrEx* userHdr = NULL;

		BS_RET_CODE result = BS_GetUserDBInfo( m_Handle, (int*)&m_NumOfUser, (int*)&m_NumOfTemplate );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();
			return false;
		}

		userHdr = (BSUserHdrEx*)malloc( sizeof(BSUserHdrEx) * m_NumOfUser );

		result = BS_GetAllUserInfoEx( m_Handle, userHdr, (int*)&m_NumOfUser );

		if( result != BS_SUCCESS && result != BS_ERR_NOT_FOUND )
		{
			EndWaitCursor();

			free( userHdr );

			return false;
		}

		for( int i = 0; i < m_NumOfUser; i++ )
		{
			CString value;
			value.Format( "%10u", userHdr[i].ID );
			int listIndex = m_UserList.InsertItem( LVIF_TEXT | LVIF_PARAM, i, value, 0, 0, 0, userHdr[i].ID );

			if( listIndex != -1 )
			{
				value.Format( "%d", userHdr[i].numOfFinger );
				m_UserList.SetItem( listIndex, 1, LVIF_TEXT, value, 0, 0, 0, 0 );

				value.Format( "%#x", userHdr[i].cardID );
				m_UserList.SetItem( listIndex, 2, LVIF_TEXT, value, 0, 0, 0, 0 );
			}
		}

		UpdateData( FALSE );

		EndWaitCursor();

		if( m_NumOfUser > 0 )
		{
			getUserInfo( userHdr[0].ID );
		}

		free( userHdr );
	}

	return true;	
}


bool CUserManagement::getUserInfo( unsigned userID )
{
	m_CardID = 0;
	m_Checksum1 = 0;
	m_Checksum2 = 0;
	m_CustomID = 0;
	m_Finger1 = FALSE;
	m_Finger2 = FALSE;
	m_Duress1 = FALSE;
	m_Duress2 = FALSE;
	m_UserID = 0;
	m_Name = "";
	m_AccessGroup = "";

	m_StartDate = CTime( (time_t)0 );
	m_ExpiryDate = CTime( (time_t)0 );

	m_AdminLevel = 0;
	m_SecurityLevel = 0;
	m_CardType = 0;
	m_AuthMode = 0;

	BeginWaitCursor();

	if( m_DeviceType == BS_DEVICE_BIOENTRY_PLUS || m_DeviceType == BS_DEVICE_BIOLITE )
	{
		BEUserHdr userHdr;
		BS_RET_CODE result = BS_GetUserBEPlus( m_Handle, userID, &userHdr, m_TemplateData );

		EndWaitCursor();

		if( result != BS_SUCCESS )
		{
			UpdateData( FALSE );
			return false;
		}

		m_CardID = userHdr.cardID;
		m_Checksum1 = userHdr.fingerChecksum[0];
		m_Checksum2 = userHdr.fingerChecksum[1];
		m_CustomID = userHdr.cardCustomID;
		m_Finger1 = (userHdr.numOfFinger >= 1)? TRUE : FALSE;
		m_Finger2 = (userHdr.numOfFinger >= 2)? TRUE : FALSE;
		m_Duress1 = userHdr.isDuress[0];
		m_Duress2 = userHdr.isDuress[1];
		m_UserID = userHdr.userID;
		
		TIME_ZONE_INFORMATION timeInfo;
		GetTimeZoneInformation( &timeInfo );

		time_t utcTime = userHdr.startTime + timeInfo.Bias * 60;
		m_StartDate = CTime( utcTime );

		utcTime = userHdr.expiryTime + timeInfo.Bias * 60;
		m_ExpiryDate = CTime( utcTime );

		m_AdminLevel = userHdr.adminLevel;
		m_SecurityLevel = userHdr.securityLevel;
		m_CardType = userHdr.cardFlag;

		if( userHdr.opMode == BS_AUTH_MODE_DISABLED )
		{
			m_AuthMode = 0;
		}
		else
		{
			m_AuthMode -= BS_AUTH_FINGER_ONLY + 1;
		}

		char buf[16];
		sprintf( buf, "%#0x", userHdr.accessGroupMask );
		m_AccessGroup = buf;
		
	}
	else if( m_DeviceType == BS_DEVICE_BIOSTATION )
	{
		BSUserHdrEx userHdr;
		BS_RET_CODE result = BS_GetUserEx( m_Handle, userID, &userHdr, m_TemplateData );

		EndWaitCursor();

		if( result != BS_SUCCESS )
		{
			UpdateData( FALSE );
			return false;
		}

		m_CardID = userHdr.cardID;
		m_Checksum1 = userHdr.checksum[0];
		m_Checksum2 = userHdr.checksum[1];
		m_CustomID = userHdr.customID;
		m_Finger1 = (userHdr.numOfFinger >= 1)? TRUE : FALSE;
		m_Finger2 = (userHdr.numOfFinger >= 2)? TRUE : FALSE;
		m_Duress1 = userHdr.duressMask & 0x01;
		m_Duress2 = userHdr.duressMask & 0x02;
		m_UserID = userHdr.ID;

		m_Name = userHdr.name;
		
		TIME_ZONE_INFORMATION timeInfo;
		GetTimeZoneInformation( &timeInfo );

		time_t utcTime = userHdr.startDateTime + timeInfo.Bias * 60;
		m_StartDate = CTime( utcTime );

		utcTime = userHdr.expireDateTime + timeInfo.Bias * 60;
		m_ExpiryDate = CTime( utcTime );

		m_AdminLevel = (userHdr.adminLevel == BS_USER_ADMIN)? 1 : 0;
		m_SecurityLevel = (userHdr.securityLevel >= BS_USER_SECURITY_DEFAULT)? userHdr.securityLevel - BS_USER_SECURITY_DEFAULT : 0;
		m_CardType = userHdr.bypassCard;

		if( userHdr.authMode == BS_AUTH_MODE_DISABLED )
		{
			m_AuthMode = 0;
		}
		else
		{
			m_AuthMode -= BS_AUTH_FINGER_ONLY + 1;
		}

		char buf[16];
		sprintf( buf, "%#0x", userHdr.accessGroupMask );
		m_AccessGroup = buf;
		
	}

	UpdateData( FALSE );

	return true;
}

void CUserManagement::OnClickUserList(NMHDR* pNMHDR, LRESULT* pResult) 
{
	// TODO: Add your control notification handler code here
	
	*pResult = 0;

	POSITION pos = m_UserList.GetFirstSelectedItemPosition();

	if( pos )
	{
		int userIndex = m_UserList.GetNextSelectedItem( pos );

		unsigned userID = m_UserList.GetItemData( userIndex );

		if( !getUserInfo( userID ) )
		{
			MessageBox( "Cannot get user info" );
		}
	}
}

void CUserManagement::OnDelete() 
{
	// TODO: Add your control notification handler code here
	UpdateData();

	BeginWaitCursor();

	BS_RET_CODE result = BS_DeleteUser( m_Handle, m_UserID );

	EndWaitCursor();

	if( result != BS_SUCCESS )
	{
		MessageBox( "Cannot delete user" );
		return;
	}

	getUserInfo();	
}


void CUserManagement::OnDeleteAll() 
{
	// TODO: Add your control notification handler code here
	UpdateData();

	BeginWaitCursor();

	BS_RET_CODE result = BS_DeleteAllUser( m_Handle );

	EndWaitCursor();

	if( result != BS_SUCCESS )
	{
		MessageBox( "Cannot delete all user" );
		return;
	}

	getUserInfo();

}

void CUserManagement::OnRefresh() 
{
	// TODO: Add your control notification handler code here
	if( !getUserInfo() )
	{
		MessageBox( "Cannot get user info" );
	}	
}

void CUserManagement::OnUpdate() 
{
	// TODO: Add your control notification handler code here
	UpdateData();
	
	BeginWaitCursor();

	if( m_DeviceType == BS_DEVICE_BIOENTRY_PLUS || m_DeviceType == BS_DEVICE_BIOLITE )
	{
		BEUserHdr userHdr;

		BS_RET_CODE result = BS_GetUserBEPlus( m_Handle, m_UserID, &userHdr, m_TemplateData );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();

			MessageBox( "Cannot get the user info" );

			return;
		}

		if( m_Finger1 )
		{
			userHdr.numOfFinger = 1;
			userHdr.isDuress[0] = m_Duress1;

			if( m_Finger2 )
			{
				userHdr.numOfFinger = 2;
				userHdr.isDuress[1] = m_Duress2;
			}
		}
		else
		{
			userHdr.numOfFinger = 0;
		}

		userHdr.startTime = BS_ConvertToLocalTime( m_StartDate.GetTime() );
		userHdr.expiryTime = BS_ConvertToLocalTime( m_ExpiryDate.GetTime() );

		userHdr.adminLevel = m_AdminLevel;
		userHdr.securityLevel = m_SecurityLevel;
		userHdr.cardFlag = m_CardType;
		
		userHdr.opMode = (m_AuthMode == BS_AUTH_MODE_DISABLED)? BS_AUTH_MODE_DISABLED : m_AuthMode + BS_AUTH_FINGER_ONLY - 1;

		sscanf( m_AccessGroup, "&%x", &userHdr.accessGroupMask );

		result = BS_EnrollUserBEPlus( m_Handle, &userHdr, m_TemplateData );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();

			MessageBox( "Cannot update the user info" );
			return;
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOSTATION )
	{
		BSUserHdrEx userHdr;

		BS_RET_CODE result = BS_GetUserEx( m_Handle, m_UserID, &userHdr, m_TemplateData );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();

			MessageBox( "Cannot get the user info" );

			return;
		}

		userHdr.duressMask = 0x00;

		if( m_Finger1 )
		{
			userHdr.numOfFinger = 1;
			userHdr.duressMask |= m_Duress1;

			if( m_Finger2 )
			{
				userHdr.numOfFinger = 2;
				userHdr.duressMask |= m_Duress2 << 1;
			}
		}
		else
		{
			userHdr.numOfFinger = 0;
		}

		userHdr.startDateTime = BS_ConvertToLocalTime( m_StartDate.GetTime() );
		userHdr.expireDateTime = BS_ConvertToLocalTime( m_ExpiryDate.GetTime() );

		userHdr.adminLevel = (m_AdminLevel == 1)? BS_USER_ADMIN : BS_USER_NORMAL;
		userHdr.securityLevel = m_SecurityLevel + BS_USER_SECURITY_DEFAULT;
		userHdr.bypassCard = m_CardType;
		
		userHdr.authMode = (m_AuthMode == BS_AUTH_MODE_DISABLED)? BS_AUTH_MODE_DISABLED : m_AuthMode + BS_AUTH_FINGER_ONLY - 1;

		sscanf( m_AccessGroup, "&%x", &userHdr.accessGroupMask );

		result = BS_EnrollUserEx( m_Handle, &userHdr, m_TemplateData );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();

			MessageBox( "Cannot update the user info" );
			return;
		}
	}

	EndWaitCursor();	
	
}

void CUserManagement::OnAdd() 
{
	// TODO: Add your control notification handler code here
	UpdateData();
	
	BeginWaitCursor();

	BS_RET_CODE result;

	if( m_Finger1 )
	{
		result = BS_ScanTemplate( m_Handle, m_TemplateData );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();

			MessageBox( "Cannot scan the template" );

			return;
		}

		result = BS_ScanTemplate( m_Handle, m_TemplateData + TEMPLATE_SIZE );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();

			MessageBox( "Cannot scan the template" );

			return;
		}

		if( m_Finger2 )
		{
			BS_RET_CODE result = BS_ScanTemplate( m_Handle, m_TemplateData + TEMPLATE_SIZE * 2 );

			if( result != BS_SUCCESS )
			{
				EndWaitCursor();

				MessageBox( "Cannot scan the template" );

				return;
			}

			result = BS_ScanTemplate( m_Handle, m_TemplateData + TEMPLATE_SIZE * 3 );

			if( result != BS_SUCCESS )
			{
				EndWaitCursor();

				MessageBox( "Cannot scan the template" );

				return;
			}
		}
	}

	if( m_DeviceType == BS_DEVICE_BIOENTRY_PLUS || m_DeviceType == BS_DEVICE_BIOLITE )
	{
		BEUserHdr userHdr;

		memset( &userHdr, 0, sizeof( BEUserHdr ) );

		if( m_Finger1 )
		{
			userHdr.numOfFinger = 1;
			userHdr.isDuress[0] = m_Duress1;

			userHdr.fingerChecksum[0] = 0;

			for( int i = 0; i < TEMPLATE_SIZE * 2; i++ )
			{
				userHdr.fingerChecksum[0] += m_TemplateData[i];
			}

			if( m_Finger2 )
			{
				userHdr.numOfFinger = 2;
				userHdr.isDuress[1] = m_Duress2;

				userHdr.fingerChecksum[1] = 0;

				for( int i = 0; i < TEMPLATE_SIZE * 2; i++ )
				{
					userHdr.fingerChecksum[1] += m_TemplateData[TEMPLATE_SIZE * 2 + i];
				}
			}
		}
		else
		{
			userHdr.numOfFinger = 0;
		}

		userHdr.userID = m_UserID;
		userHdr.cardID = m_CardID;
		userHdr.cardCustomID = m_CustomID;
		userHdr.cardVersion = BEUserHdr::CARD_VERSION_1;

		userHdr.startTime = BS_ConvertToLocalTime( m_StartDate.GetTime() );
		userHdr.expiryTime = BS_ConvertToLocalTime( m_ExpiryDate.GetTime() );

		userHdr.adminLevel = m_AdminLevel;
		userHdr.securityLevel = m_SecurityLevel;
		userHdr.cardFlag = m_CardType;
		
		userHdr.opMode = (m_AuthMode == BS_AUTH_MODE_DISABLED)? BS_AUTH_MODE_DISABLED : m_AuthMode + BS_AUTH_FINGER_ONLY - 1;

		sscanf( m_AccessGroup, "&%x", &userHdr.accessGroupMask );

		result = BS_EnrollUserBEPlus( m_Handle, &userHdr, m_TemplateData );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();

			MessageBox( "Cannot enroll the user" );
			return;
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOSTATION )
	{
		BSUserHdrEx userHdr;

		memset( &userHdr, 0, sizeof( BSUserHdrEx ) );

		userHdr.duressMask = 0x00;

		if( m_Finger1 )
		{
			userHdr.numOfFinger = 1;
			userHdr.duressMask |= m_Duress1;

			userHdr.checksum[0] = 0;

			for( int i = 0; i < TEMPLATE_SIZE * 2; i++ )
			{
				userHdr.checksum[0] += m_TemplateData[i];
			}

			if( m_Finger2 )
			{
				userHdr.numOfFinger = 2;
				userHdr.duressMask |= m_Duress2 << 1;

				userHdr.checksum[1] = 0;

				for( int i = 0; i < TEMPLATE_SIZE * 2; i++ )
				{
					userHdr.checksum[1] += m_TemplateData[TEMPLATE_SIZE * 2 + i];
				}
			}
		}
		else
		{
			userHdr.numOfFinger = 0;
		}

		userHdr.ID = m_UserID;
		userHdr.cardID = m_CardID;
		userHdr.customID = m_CustomID;
		userHdr.version = CARD_INFO_VERSION;


		userHdr.startDateTime = BS_ConvertToLocalTime( m_StartDate.GetTime() );
		userHdr.expireDateTime = BS_ConvertToLocalTime( m_ExpiryDate.GetTime() );

		userHdr.adminLevel = (m_AdminLevel == 1)? BS_USER_ADMIN : BS_USER_NORMAL;
		userHdr.securityLevel = m_SecurityLevel + BS_USER_SECURITY_DEFAULT;
		userHdr.bypassCard = m_CardType;
		
		userHdr.authMode = (m_AuthMode == BS_AUTH_MODE_DISABLED)? BS_AUTH_MODE_DISABLED : m_AuthMode + BS_AUTH_FINGER_ONLY - 1;

		sscanf( m_AccessGroup, "&%x", &userHdr.accessGroupMask );

		result = BS_EnrollUserEx( m_Handle, &userHdr, m_TemplateData );

		if( result != BS_SUCCESS )
		{
			EndWaitCursor();

			MessageBox( "Cannot enroll the user" );
			return;
		}
	}

	EndWaitCursor();	
	
}

void CUserManagement::OnReadCard() 
{
	// TODO: Add your control notification handler code here
	UpdateData();

	BeginWaitCursor();

	unsigned cardID;
	int customID;
	
	int result = BS_ReadCardIDEx( m_Handle, &cardID, &customID );

	if( result != BS_SUCCESS )
	{
		EndWaitCursor();

		MessageBox( "Cannot read the card" );
		return;
	}

	m_CardID = cardID;
	m_CustomID = customID;

	UpdateData( FALSE );

	EndWaitCursor();
}

void CUserManagement::OnDestroy() 
{
	CDialog::OnDestroy();
	
	// TODO: Add your message handler code here
	free( m_TemplateData );
}

