// BioStarCPPDlg.cpp : implementation file
//

#include "stdafx.h"
#include "BioStarCPP.h"
#include "BioStarCPPDlg.h"
#include "NetworkConfig.h"
#include "SetupTime.h"
#include "LogManagement.h"
#include "UserManagement.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

#include "BS_API.h"

/////////////////////////////////////////////////////////////////////////////
// CAboutDlg dialog used for App About

class CAboutDlg : public CDialog
{
public:
	CAboutDlg();

// Dialog Data
	//{{AFX_DATA(CAboutDlg)
	enum { IDD = IDD_ABOUTBOX };
	//}}AFX_DATA

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CAboutDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	//{{AFX_MSG(CAboutDlg)
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialog(CAboutDlg::IDD)
{
	//{{AFX_DATA_INIT(CAboutDlg)
	//}}AFX_DATA_INIT
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CAboutDlg)
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialog)
	//{{AFX_MSG_MAP(CAboutDlg)
		// No message handlers
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CBioStarCPPDlg dialog

CBioStarCPPDlg::CBioStarCPPDlg(CWnd* pParent /*=NULL*/)
	: CDialog(CBioStarCPPDlg::IDD, pParent)
{
	//{{AFX_DATA_INIT(CBioStarCPPDlg)
		// NOTE: the ClassWizard will add member initialization here
	//}}AFX_DATA_INIT
	// Note that LoadIcon does not require a subsequent DestroyIcon in Win32
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);

	m_NumOfDevice = 0;
	m_NumOfConnectedDevice = 0;

	m_Handle = -1;
}

void CBioStarCPPDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CBioStarCPPDlg)
	DDX_Control(pDX, IDC_CONNECTED_LIST, m_ConnectedDeviceList);
	DDX_Control(pDX, IDC_DEVICE_LIST, m_DeviceList);
	//}}AFX_DATA_MAP
}

BEGIN_MESSAGE_MAP(CBioStarCPPDlg, CDialog)
	//{{AFX_MSG_MAP(CBioStarCPPDlg)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_BN_CLICKED(IDC_SEARCH, OnSearch)
	ON_BN_CLICKED(IDC_NETWORK_CONFIG, OnNetworkConfig)
	ON_BN_CLICKED(IDC_DISCONNECT, OnDisconnect)
	ON_BN_CLICKED(IDC_TIME, OnTime)
	ON_BN_CLICKED(IDC_LOG, OnLog)
	ON_BN_CLICKED(IDC_USER, OnUser)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CBioStarCPPDlg message handlers

BOOL CBioStarCPPDlg::OnInitDialog()
{
	CDialog::OnInitDialog();

	// Add "About..." menu item to system menu.

	// IDM_ABOUTBOX must be in the system command range.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != NULL)
	{
		CString strAboutMenu;
		strAboutMenu.LoadString(IDS_ABOUTBOX);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// Set the icon for this dialog.  The framework does this automatically
	//  when the application's main window is not a dialog
	SetIcon(m_hIcon, TRUE);			// Set big icon
	SetIcon(m_hIcon, FALSE);		// Set small icon
	
	// TODO: Add extra initialization here
	BS_InitSDK();

	if( BS_OpenInternalUDP( &m_Handle ) != BS_SUCCESS )
	{
		return FALSE;
	}
	
	return TRUE;  // return TRUE  unless you set the focus to a control
}

void CBioStarCPPDlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialog::OnSysCommand(nID, lParam);
	}
}

// If you add a minimize button to your dialog, you will need the code below
//  to draw the icon.  For MFC applications using the document/view model,
//  this is automatically done for you by the framework.

void CBioStarCPPDlg::OnPaint() 
{
	if (IsIconic())
	{
		CPaintDC dc(this); // device context for painting

		SendMessage(WM_ICONERASEBKGND, (WPARAM) dc.GetSafeHdc(), 0);

		// Center icon in client rectangle
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Draw the icon
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialog::OnPaint();
	}
}

// The system calls this to obtain the cursor to display while the user drags
//  the minimized window.
HCURSOR CBioStarCPPDlg::OnQueryDragIcon()
{
	return (HCURSOR) m_hIcon;
}


static char* s_DeviceName[] = {
	"BST", "BE+", "BLN"
};

void CBioStarCPPDlg::OnSearch() 
{
	// TODO: Add your control notification handler code here
	m_NumOfDevice = 0;

	m_DeviceList.ResetContent();

	UpdateData();

	BeginWaitCursor();

	BS_RET_CODE result = BS_SearchDeviceInLAN( m_Handle, &m_NumOfDevice, m_DeviceID, m_DeviceType, m_DeviceAddress );

	EndWaitCursor();

	for( int i = 0; i < m_NumOfDevice; i++ )
	{
		char buf[32];

		if( m_DeviceType[i] >= BS_DEVICE_BIOSTATION &&
			m_DeviceType[i] <= BS_DEVICE_BIOLITE )
		{
			sprintf( buf, "%s(%u) %d.%d.%d.%d", s_DeviceName[m_DeviceType[i]], m_DeviceID[i], m_DeviceAddress[i] & 0xff, (m_DeviceAddress[i] & 0xff00) >> 8, 
				(m_DeviceAddress[i] & 0xff0000) >> 16, (m_DeviceAddress[i] & 0xff000000) >> 24 );
		}
		else
		{
			sprintf( buf, "Unknown(%u) %d.%d.%d.%d", m_DeviceID[i], m_DeviceAddress[i] & 0xff, (m_DeviceAddress[i] & 0xff00) >> 8, 
				(m_DeviceAddress[i] & 0xff0000) >> 16, (m_DeviceAddress[i] & 0xff000000) >> 24 );
		}

		int itemIndex = m_DeviceList.AddString( buf );

		m_DeviceList.SetItemData( itemIndex, i );
	}  
}

void CBioStarCPPDlg::OnNetworkConfig() 
{
	// TODO: Add your control notification handler code here
	// TODO: Add your control notification handler code here
	UpdateData();
	
	int selIndex = m_DeviceList.GetCurSel();

	if( selIndex == LB_ERR )
	{
		MessageBox( "Please select a device first" );
		return;
	}

	CNetworkConfig* config = new CNetworkConfig();

	config->setHandle( m_Handle, m_DeviceID[selIndex], m_DeviceAddress[selIndex], m_DeviceType[selIndex] );

	config->DoModal();

	delete config;
}


void CBioStarCPPDlg::addConnectedDevice( unsigned id, unsigned addr, int handle, int type )
{
	int deviceIndex = -1;
	bool addNewDevice = true;

	for( int i = 0; i < m_NumOfConnectedDevice; i++ )
	{
		if( id == m_ConnectedDeviceID[i] )
		{
			BS_CloseSocket( m_ConnectedHandle[i] );

			deviceIndex = i;
			addNewDevice = false;
			break;
		}
	}

	if( deviceIndex < 0 )
	{
		deviceIndex = m_NumOfConnectedDevice++;
	}

	m_ConnectedDeviceID[deviceIndex] = id;
	m_ConnectedHandle[deviceIndex] = handle;
	m_ConnectedDeviceAddress[deviceIndex] = addr;
	m_ConnectedDeviceType[deviceIndex] = type;

	if( addNewDevice )
	{
		updateConnectedDevices();
	}
}


void CBioStarCPPDlg::updateConnectedDevices()
{
	m_ConnectedDeviceList.ResetContent();

	for( int i = 0;i < m_NumOfConnectedDevice; i++ )
	{
		char buf[32];

		sprintf( buf, "%d.%d.%d.%d(%u)", m_ConnectedDeviceAddress[i] & 0xff, (m_ConnectedDeviceAddress[i] & 0xff00) >> 8, 
			(m_ConnectedDeviceAddress[i] & 0xff0000) >> 16, (m_ConnectedDeviceAddress[i] & 0xff000000) >> 24, m_ConnectedDeviceID[i] );

		int itemIndex = m_ConnectedDeviceList.AddString( buf );

		m_ConnectedDeviceList.SetItemData( itemIndex, i );
	}
}


void CBioStarCPPDlg::OnDisconnect() 
{
	// TODO: Add your control notification handler code here
	for( int i = 0; i < m_NumOfConnectedDevice; i++ )
	{
		BS_CloseSocket( m_ConnectedHandle[i] );
	}

	m_NumOfConnectedDevice = 0;

	m_ConnectedDeviceList.ResetContent();
}

void CBioStarCPPDlg::OnTime() 
{
	// TODO: Add your control notification handler code here
	int selIndex = m_ConnectedDeviceList.GetCurSel();

	if( selIndex == LB_ERR )
	{
		MessageBox( "Please select a connected device first" );
		return;
	}

	BS_SetDeviceID( m_ConnectedHandle[selIndex], m_ConnectedDeviceID[selIndex], m_ConnectedDeviceType[selIndex] );

	CSetupTime* timeSetup = new CSetupTime();

	timeSetup->setHandle( m_ConnectedHandle[selIndex] );

	timeSetup->DoModal();

	delete timeSetup;	
}

void CBioStarCPPDlg::OnLog() 
{
	// TODO: Add your control notification handler code here
	int selIndex = m_ConnectedDeviceList.GetCurSel();

	if( selIndex == LB_ERR )
	{
		MessageBox( "Please select a connected device first" );
		return;
	}

	BS_SetDeviceID( m_ConnectedHandle[selIndex], m_ConnectedDeviceID[selIndex], m_ConnectedDeviceType[selIndex] );

	CLogManagement* logDialog = new CLogManagement();

	logDialog->setDevice( m_ConnectedHandle[selIndex], m_ConnectedDeviceID[selIndex], m_ConnectedDeviceAddress[selIndex] );

	logDialog->DoModal();

	delete logDialog;		
}

void CBioStarCPPDlg::OnUser() 
{
	// TODO: Add your control notification handler code here
	int selIndex = m_ConnectedDeviceList.GetCurSel();

	if( selIndex == LB_ERR )
	{
		MessageBox( "Please select a connected device first" );
		return;
	}

	BS_SetDeviceID( m_ConnectedHandle[selIndex], m_ConnectedDeviceID[selIndex], m_ConnectedDeviceType[selIndex] );

	CUserManagement* userDialog = new CUserManagement();

	userDialog->setDevice( m_ConnectedHandle[selIndex], m_ConnectedDeviceID[selIndex], m_ConnectedDeviceAddress[selIndex], m_ConnectedDeviceType[selIndex] );

	userDialog->DoModal();

	delete userDialog;		
}
