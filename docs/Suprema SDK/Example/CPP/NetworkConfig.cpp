// NetworkConfig.cpp : implementation file
//

#include "stdafx.h"
#include "BioStarCPP.h"
#include "NetworkConfig.h"
#include "BioStarCPPDlg.h"

#include <winsock2.h>

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CNetworkConfig dialog


CNetworkConfig::CNetworkConfig(CWnd* pParent /*=NULL*/)
	: CDialog(CNetworkConfig::IDD, pParent)
{
	//{{AFX_DATA_INIT(CNetworkConfig)
	m_DeviceInfo = _T("");
	m_DHCP = FALSE;
	m_FirmwareVersion = _T("");
	m_MacAddr = _T("");
	m_Port = 0;
	m_SyncTime = FALSE;
	m_UseServer = FALSE;
	m_DeviceID = 0;
	//}}AFX_DATA_INIT
}


void CNetworkConfig::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CNetworkConfig)
	DDX_Control(pDX, IDC_SUBNET_MASK, m_SubnetMask);
	DDX_Control(pDX, IDC_SERVER_IP, m_ServerIP);
	DDX_Control(pDX, IDC_IP_ADDR, m_IPAddr);
	DDX_Control(pDX, IDC_GATEWAY, m_Gateway);
	DDX_Text(pDX, IDC_DEVICE_INFO, m_DeviceInfo);
	DDX_Check(pDX, IDC_DHCP, m_DHCP);
	DDX_Text(pDX, IDC_FIRM_VER, m_FirmwareVersion);
	DDX_Text(pDX, IDC_MAC_ADDR, m_MacAddr);
	DDX_Text(pDX, IDC_PORT, m_Port);
	DDX_Check(pDX, IDC_SYNC_TIME, m_SyncTime);
	DDX_Check(pDX, IDC_USE_SERVER, m_UseServer);
	DDX_Text(pDX, IDC_DEVICE_ID, m_DeviceID);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CNetworkConfig, CDialog)
	//{{AFX_MSG_MAP(CNetworkConfig)
	ON_BN_CLICKED(IDC_REFRESH, OnRefresh)
	ON_BN_CLICKED(IDC_CONNECT, OnConnect)
	ON_BN_CLICKED(IDC_WRITE, OnWrite)
	ON_BN_CLICKED(IDC_NETWORK_REFRESH, OnNetworkRefresh)
	ON_BN_CLICKED(IDC_DHCP, OnDhcp)
	ON_BN_CLICKED(IDC_USE_SERVER, OnUseServer)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CNetworkConfig message handlers

void CNetworkConfig::setHandle( int handle, unsigned deviceID, unsigned deviceAddr, int deviceType )
{
	m_Handle = handle;
	m_ID = deviceID;
	m_Addr = deviceAddr;
	m_DeviceType = deviceType;
}

BOOL CNetworkConfig::OnInitDialog() 
{
	CDialog::OnInitDialog();
	
	// TODO: Add extra initialization here
	char buf[32];

	sprintf( buf, "%d.%d.%d.%d(%u)", m_Addr & 0xff, (m_Addr & 0xff00) >> 8, 
			(m_Addr & 0xff0000) >> 16, (m_Addr & 0xff000000) >> 24, m_ID );

	m_DeviceInfo = buf;

	m_DeviceID = 0;
	m_MacAddr = _T("");

	BeginWaitCursor();

	if( m_DeviceType == BS_DEVICE_BIOENTRY_PLUS )
	{
		BESysInfoData sysInfo;
		if( readSysInfoBEPlus( &sysInfo ) )
		{
			m_DeviceID = sysInfo.ID;
			m_MacAddr.Format( "%02x:%02x:%02x:%02x:%02x:%02x", sysInfo.macAddr[0], sysInfo.macAddr[1], sysInfo.macAddr[2], sysInfo.macAddr[3], sysInfo.macAddr[4], sysInfo.macAddr[5] );
			m_FirmwareVersion = sysInfo.firmwareVer;
		}
		else
		{
			MessageBox( "Cannot read sys info" );
		}

		BEConfigData config;
		if( readConfigBEPlus( &config ) )
		{
			m_DHCP = config.useDHCP;
			m_IPAddr.SetAddress( ntohl( config.ipAddr ) );
			m_Gateway.SetAddress( ntohl( config.gateway ) );
			m_SubnetMask.SetAddress( ntohl( config.subnetMask ) );
			m_ServerIP.SetAddress( ntohl( config.serverIpAddr ) );
			m_Port = config.port;
			m_UseServer = config.useServer;
			m_SyncTime = config.synchTime;

			if( m_DHCP )
			{
				m_IPAddr.EnableWindow( FALSE );
				m_Gateway.EnableWindow( FALSE );
				m_SubnetMask.EnableWindow( FALSE );
			}
			else
			{
				m_IPAddr.EnableWindow( TRUE );
				m_Gateway.EnableWindow( TRUE );
				m_SubnetMask.EnableWindow( TRUE );
			}

			if( m_UseServer )
			{
				m_ServerIP.EnableWindow( TRUE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( FALSE );
			}
			else
			{
				m_ServerIP.EnableWindow( FALSE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( TRUE );
			}
		}
		else
		{
			MessageBox( "Cannot read config" );
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOLITE )
	{
		BESysInfoDataBLN sysInfo;
		if( readSysInfoBLN( &sysInfo ) )
		{
			m_DeviceID = sysInfo.ID;
			m_MacAddr.Format( "%02x:%02x:%02x:%02x:%02x:%02x", sysInfo.macAddr[0], sysInfo.macAddr[1], sysInfo.macAddr[2], sysInfo.macAddr[3], sysInfo.macAddr[4], sysInfo.macAddr[5] );
			m_FirmwareVersion = sysInfo.firmwareVer;
		}
		else
		{
			MessageBox( "Cannot read sys info" );
		}

		BEConfigDataBLN config;
		if( readConfigBLN( &config ) )
		{
			m_DHCP = config.useDHCP;
			m_IPAddr.SetAddress( ntohl( config.ipAddr ) );
			m_Gateway.SetAddress( ntohl( config.gateway ) );
			m_SubnetMask.SetAddress( ntohl( config.subnetMask ) );
			m_ServerIP.SetAddress( ntohl( config.serverIpAddr ) );
			m_Port = config.port;
			m_UseServer = config.useServer;
			m_SyncTime = config.synchTime;

			if( m_DHCP )
			{
				m_IPAddr.EnableWindow( FALSE );
				m_Gateway.EnableWindow( FALSE );
				m_SubnetMask.EnableWindow( FALSE );
			}
			else
			{
				m_IPAddr.EnableWindow( TRUE );
				m_Gateway.EnableWindow( TRUE );
				m_SubnetMask.EnableWindow( TRUE );
			}

			if( m_UseServer )
			{
				m_ServerIP.EnableWindow( TRUE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( FALSE );
			}
			else
			{
				m_ServerIP.EnableWindow( FALSE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( TRUE );
			}
		}
		else
		{
			MessageBox( "Cannot read config" );
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOSTATION )
	{
		BSSysInfoConfig sysInfo;
		if( readSysInfoBST( &sysInfo ) )
		{
			m_DeviceID = sysInfo.ID;
			m_MacAddr = sysInfo.macAddr; 
			m_FirmwareVersion = sysInfo.firmwareVer;
		}
		else
		{
			MessageBox( "Cannot read sys info" );
		}

		BSIPConfig config;
		if( readConfigBST( &config ) )
		{
			m_DHCP = config.useDHCP;
			m_IPAddr.SetAddress( ntohl( inet_addr( config.ipAddr ) ) );
			m_Gateway.SetAddress( ntohl( inet_addr( config.gateway ) ) );
			m_SubnetMask.SetAddress( ntohl( inet_addr( config.subnetMask ) ) );
			m_ServerIP.SetAddress( ntohl( inet_addr( config.serverIP ) ) );

			if( config.useServer )
			{
				m_Port = config.serverPort;
			}
			else
			{
				m_Port = config.port;
			}

			m_UseServer = config.useServer;
			m_SyncTime = config.syncTimeWithServer;

			if( m_DHCP )
			{
				m_IPAddr.EnableWindow( FALSE );
				m_Gateway.EnableWindow( FALSE );
				m_SubnetMask.EnableWindow( FALSE );
			}
			else
			{
				m_IPAddr.EnableWindow( TRUE );
				m_Gateway.EnableWindow( TRUE );
				m_SubnetMask.EnableWindow( TRUE );
			}

			if( m_UseServer )
			{
				m_ServerIP.EnableWindow( TRUE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( FALSE );
			}
			else
			{
				m_ServerIP.EnableWindow( FALSE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( TRUE );
			}
		}
		else
		{
			MessageBox( "Cannot read config" );
		}
	}

	UpdateData( FALSE );
	
	EndWaitCursor();

	
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE

}


bool CNetworkConfig::readSysInfoBEPlus( BESysInfoData* sysInfo )
{
	int dataSize = sizeof( BESysInfoData );
	BS_RET_CODE result = BS_ReadConfigUDP( m_Handle, m_Addr, m_ID, BEPLUS_CONFIG_SYS_INFO, &dataSize, (unsigned char*)sysInfo );

	if( result != BS_SUCCESS )
	{
		return false;
	}

	return true;
}


bool CNetworkConfig::readConfigBEPlus( BEConfigData* config )
{
	int dataSize = sizeof( BEConfigData );

	BS_RET_CODE result = BS_ReadConfigUDP( m_Handle, m_Addr, m_ID, BEPLUS_CONFIG, &dataSize, (unsigned char*)config );

	if( result != BS_SUCCESS )
	{
		return false;
	}

	return true;
}


bool CNetworkConfig::readSysInfoBLN( BESysInfoDataBLN* sysInfo )
{
	int dataSize = sizeof( BESysInfoDataBLN );
	BS_RET_CODE result = BS_ReadConfigUDP( m_Handle, m_Addr, m_ID, BIOLITE_CONFIG_SYS_INFO, &dataSize, (unsigned char*)sysInfo );

	if( result != BS_SUCCESS )
	{
		return false;
	}

	return true;
}


bool CNetworkConfig::readConfigBLN( BEConfigDataBLN* config )
{
	int dataSize = sizeof( BEConfigDataBLN );

	BS_RET_CODE result = BS_ReadConfigUDP( m_Handle, m_Addr, m_ID, BIOLITE_CONFIG, &dataSize, (unsigned char*)config );

	if( result != BS_SUCCESS )
	{
		return false;
	}

	return true;
}


bool CNetworkConfig::readSysInfoBST( BSSysInfoConfig* sysInfo )
{
	int dataSize = sizeof( BSSysInfoConfig );
	BS_RET_CODE result = BS_ReadConfigUDP( m_Handle, m_Addr, m_ID, BS_CONFIG_SYS_INFO, &dataSize, (unsigned char*)sysInfo );

	if( result != BS_SUCCESS )
	{
		return false;
	}

	return true;
}


bool CNetworkConfig::readConfigBST( BSIPConfig* config )
{
	int dataSize = sizeof( BSIPConfig );

	BS_RET_CODE result = BS_ReadConfigUDP( m_Handle, m_Addr, m_ID, BS_CONFIG_TCPIP, &dataSize, (unsigned char*)config );

	if( result != BS_SUCCESS )
	{
		return false;
	}

	return true;
}


void CNetworkConfig::OnRefresh() 
{
	// TODO: Add your control notification handler code here
	m_DeviceID = 0;
	m_MacAddr = _T("");
	m_FirmwareVersion = _T("");

	BeginWaitCursor();

	if( m_DeviceType == BS_DEVICE_BIOENTRY_PLUS )
	{
		BESysInfoData sysInfo;
		if( readSysInfoBEPlus( &sysInfo ) )
		{
			m_DeviceID = sysInfo.ID;
			m_MacAddr.Format( "%02x:%02x:%02x:%02x:%02x:%02x", sysInfo.macAddr[0], sysInfo.macAddr[1], sysInfo.macAddr[2], sysInfo.macAddr[3], sysInfo.macAddr[4], sysInfo.macAddr[5] );
			m_FirmwareVersion = sysInfo.firmwareVer;
		}
		else
		{
			MessageBox( "Cannot read sys info" );
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOLITE )
	{
		BESysInfoDataBLN sysInfo;
		if( readSysInfoBLN( &sysInfo ) )
		{
			m_DeviceID = sysInfo.ID;
			m_MacAddr.Format( "%02x:%02x:%02x:%02x:%02x:%02x", sysInfo.macAddr[0], sysInfo.macAddr[1], sysInfo.macAddr[2], sysInfo.macAddr[3], sysInfo.macAddr[4], sysInfo.macAddr[5] );
			m_FirmwareVersion = sysInfo.firmwareVer;
		}
		else
		{
			MessageBox( "Cannot read sys info" );
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOSTATION )
	{
		BSSysInfoConfig sysInfo;
		if( readSysInfoBST( &sysInfo ) )
		{
			m_DeviceID = sysInfo.ID;
			m_MacAddr = sysInfo.macAddr; 
			m_FirmwareVersion = sysInfo.firmwareVer;
		}
		else
		{
			MessageBox( "Cannot read sys info" );
		}
	}

	UpdateData( FALSE );

	EndWaitCursor();	
}

void CNetworkConfig::OnConnect() 
{
	// TODO: Add your control notification handler code here
	// TODO: Add your control notification handler code here
	UpdateData();

	int handle;

	char buf[32];

	sprintf( buf, "%d.%d.%d.%d", m_Addr & 0xff, (m_Addr & 0xff00) >> 8, 
			(m_Addr & 0xff0000) >> 16, (m_Addr & 0xff000000) >> 24 );

	BeginWaitCursor();

	BS_RET_CODE result = BS_OpenSocket( buf, m_Port, &handle );

	EndWaitCursor();

	if( result != BS_SUCCESS )
	{
		MessageBox( "Cannot connect to the reader" );
		return;
	}

	((CBioStarCPPDlg*)AfxGetMainWnd())->addConnectedDevice( m_ID, m_Addr, handle, m_DeviceType );	
}

void CNetworkConfig::OnWrite() 
{
	// TODO: Add your control notification handler code here
	BeginWaitCursor();

	if( m_DeviceType == BS_DEVICE_BIOENTRY_PLUS )
	{
		BEConfigData config;
		if( !readConfigBEPlus( &config ) )
		{
			MessageBox( "Cannot read config" );
			EndWaitCursor();
			return;
		}

		UpdateData();

		config.useDHCP = m_DHCP;
		
		m_IPAddr.GetAddress( (DWORD&)config.ipAddr );
		config.ipAddr = htonl( config.ipAddr );
		m_Gateway.GetAddress( (DWORD&)config.gateway );
		config.gateway = htonl( config.gateway );
		m_SubnetMask.GetAddress( (DWORD&)config.subnetMask );
		config.subnetMask = htonl( config.subnetMask );
		m_ServerIP.GetAddress( (DWORD&)config.serverIpAddr );
		config.serverIpAddr = htonl( config.serverIpAddr );
		
		config.port = m_Port;

		config.useServer = (int)m_UseServer;
		config.synchTime = m_SyncTime;

		int dataSize = sizeof( config );
		BS_RET_CODE result = BS_WriteConfigUDP( m_Handle, m_Addr, m_ID, BEPLUS_CONFIG, dataSize, (unsigned char*)&config );

		if( result != BS_SUCCESS )
		{
			MessageBox( "Cannot write config" );
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOLITE )
	{
		BEConfigDataBLN config;
		if( !readConfigBLN( &config ) )
		{
			MessageBox( "Cannot read config" );
			EndWaitCursor();
			return;
		}

		UpdateData();

		config.useDHCP = m_DHCP;
		
		m_IPAddr.GetAddress( (DWORD&)config.ipAddr );
		config.ipAddr = htonl( config.ipAddr );
		m_Gateway.GetAddress( (DWORD&)config.gateway );
		config.gateway = htonl( config.gateway );
		m_SubnetMask.GetAddress( (DWORD&)config.subnetMask );
		config.subnetMask = htonl( config.subnetMask );
		m_ServerIP.GetAddress( (DWORD&)config.serverIpAddr );
		config.serverIpAddr = htonl( config.serverIpAddr );
		
		config.port = m_Port;

		config.useServer = (int)m_UseServer;
		config.synchTime = m_SyncTime;

		int dataSize = sizeof( config );
		BS_RET_CODE result = BS_WriteConfigUDP( m_Handle, m_Addr, m_ID, BIOLITE_CONFIG, dataSize, (unsigned char*)&config );

		if( result != BS_SUCCESS )
		{
			MessageBox( "Cannot write config" );
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOSTATION )
	{
		BSIPConfig config;
		if( !readConfigBST( &config ) )
		{
			MessageBox( "Cannot read config" );
			EndWaitCursor();
			return;
		}

		UpdateData();

		config.useDHCP = m_DHCP;

		struct in_addr addr;

		m_IPAddr.GetAddress( addr.S_un.S_addr );
		addr.S_un.S_addr = htonl( addr.S_un.S_addr );
		strcpy( config.ipAddr, inet_ntoa( addr ) );

		m_Gateway.GetAddress( addr.S_un.S_addr );
		addr.S_un.S_addr = htonl( addr.S_un.S_addr );
		strcpy( config.gateway, inet_ntoa( addr ) );

		m_SubnetMask.GetAddress( addr.S_un.S_addr );
		addr.S_un.S_addr = htonl( addr.S_un.S_addr );
		strcpy( config.subnetMask, inet_ntoa( addr ) );

		m_ServerIP.GetAddress( addr.S_un.S_addr );
		addr.S_un.S_addr = htonl( addr.S_un.S_addr );
		strcpy( config.serverIP, inet_ntoa( addr ) );

		if( m_UseServer )
		{
			config.serverPort = m_Port;
		}
		else
		{
			config.port = m_Port;
		}

		config.useServer = m_UseServer;
		config.syncTimeWithServer = m_SyncTime;

		int dataSize = sizeof( config );
		BS_RET_CODE result = BS_WriteConfigUDP( m_Handle, m_Addr, m_ID, BS_CONFIG_TCPIP, dataSize, (unsigned char*)&config );

		if( result != BS_SUCCESS )
		{
			MessageBox( "Cannot write config" );
		}
	}

	EndWaitCursor();
	
}

void CNetworkConfig::OnNetworkRefresh() 
{
	// TODO: Add your control notification handler code here
	BeginWaitCursor();
	
	if( m_DeviceType == BS_DEVICE_BIOENTRY_PLUS )
	{
		BEConfigData config;
		if( readConfigBEPlus( &config ) )
		{
			m_DHCP = config.useDHCP;
			m_IPAddr.SetAddress( ntohl( config.ipAddr ) );
			m_Gateway.SetAddress( ntohl( config.gateway ) );
			m_SubnetMask.SetAddress( ntohl( config.subnetMask ) );
			m_ServerIP.SetAddress( ntohl( config.serverIpAddr ) );
			m_Port = config.port;
			m_UseServer = config.useServer;
			m_SyncTime = config.synchTime;

			if( m_DHCP )
			{
				m_IPAddr.EnableWindow( FALSE );
				m_Gateway.EnableWindow( FALSE );
				m_SubnetMask.EnableWindow( FALSE );
			}
			else
			{
				m_IPAddr.EnableWindow( TRUE );
				m_Gateway.EnableWindow( TRUE );
				m_SubnetMask.EnableWindow( TRUE );
			}

			if( m_UseServer )
			{
				m_ServerIP.EnableWindow( TRUE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( FALSE );
			}
			else
			{
				m_ServerIP.EnableWindow( FALSE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( TRUE );
			}
		}
		else
		{
			MessageBox( "Cannot read config" );
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOLITE )
	{
		BEConfigDataBLN config;
		if( readConfigBLN( &config ) )
		{
			m_DHCP = config.useDHCP;
			m_IPAddr.SetAddress( ntohl( config.ipAddr ) );
			m_Gateway.SetAddress( ntohl( config.gateway ) );
			m_SubnetMask.SetAddress( ntohl( config.subnetMask ) );
			m_ServerIP.SetAddress( ntohl( config.serverIpAddr ) );
			m_Port = config.port;
			m_UseServer = config.useServer;
			m_SyncTime = config.synchTime;

			if( m_DHCP )
			{
				m_IPAddr.EnableWindow( FALSE );
				m_Gateway.EnableWindow( FALSE );
				m_SubnetMask.EnableWindow( FALSE );
			}
			else
			{
				m_IPAddr.EnableWindow( TRUE );
				m_Gateway.EnableWindow( TRUE );
				m_SubnetMask.EnableWindow( TRUE );
			}

			if( m_UseServer )
			{
				m_ServerIP.EnableWindow( TRUE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( FALSE );
			}
			else
			{
				m_ServerIP.EnableWindow( FALSE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( TRUE );
			}
		}
		else
		{
			MessageBox( "Cannot read config" );
		}
	}
	else if( m_DeviceType == BS_DEVICE_BIOSTATION )
	{
		BSIPConfig config;
		if( readConfigBST( &config ) )
		{
			m_DHCP = config.useDHCP;
			m_IPAddr.SetAddress( ntohl( inet_addr( config.ipAddr ) ) );
			m_Gateway.SetAddress( ntohl( inet_addr( config.gateway ) ) );
			m_SubnetMask.SetAddress( ntohl( inet_addr( config.subnetMask ) ) );
			m_ServerIP.SetAddress( ntohl( inet_addr( config.serverIP ) ) );

			if( config.useServer )
			{
				m_Port = config.serverPort;
			}
			else
			{
				m_Port = config.port;
			}

			m_UseServer = config.useServer;
			m_SyncTime = config.syncTimeWithServer;

			if( m_DHCP )
			{
				m_IPAddr.EnableWindow( FALSE );
				m_Gateway.EnableWindow( FALSE );
				m_SubnetMask.EnableWindow( FALSE );
			}
			else
			{
				m_IPAddr.EnableWindow( TRUE );
				m_Gateway.EnableWindow( TRUE );
				m_SubnetMask.EnableWindow( TRUE );
			}

			if( m_UseServer )
			{
				m_ServerIP.EnableWindow( TRUE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( FALSE );
			}
			else
			{
				m_ServerIP.EnableWindow( FALSE );
				GetDlgItem( IDC_CONNECT )->EnableWindow( TRUE );
			}
		}
		else
		{
			MessageBox( "Cannot read config" );
		}
	}

	EndWaitCursor();	
	
}

void CNetworkConfig::OnDhcp() 
{
	// TODO: Add your control notification handler code here
	UpdateData();
	
	if( m_DHCP )
	{
		m_IPAddr.EnableWindow( FALSE );
		m_Gateway.EnableWindow( FALSE );
		m_SubnetMask.EnableWindow( FALSE );
	}
	else
	{
		m_IPAddr.EnableWindow( TRUE );
		m_Gateway.EnableWindow( TRUE );
		m_SubnetMask.EnableWindow( TRUE );
	}
}

void CNetworkConfig::OnUseServer() 
{
	// TODO: Add your control notification handler code here
	UpdateData();

	if( m_UseServer )
	{
		m_ServerIP.EnableWindow( TRUE );
		GetDlgItem( IDC_CONNECT )->EnableWindow( FALSE );
		GetDlgItem( IDC_SYNC_TIME )->EnableWindow( TRUE );
	}
	else
	{
		m_ServerIP.EnableWindow( FALSE );
		GetDlgItem( IDC_CONNECT )->EnableWindow( TRUE );
		GetDlgItem( IDC_SYNC_TIME )->EnableWindow( FALSE );
	}	
}
