// SetupTime.cpp : implementation file
//

#include "stdafx.h"
#include "BioStarCPP.h"
#include "SetupTime.h"
#include "BS_API.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CSetupTime dialog


CSetupTime::CSetupTime(CWnd* pParent /*=NULL*/)
	: CDialog(CSetupTime::IDD, pParent)
{
	//{{AFX_DATA_INIT(CSetupTime)
	m_Time = 0;
	m_Date = 0;
	//}}AFX_DATA_INIT
}


void CSetupTime::DoDataExchange(CDataExchange* pDX)
{
	CDialog::DoDataExchange(pDX);
	//{{AFX_DATA_MAP(CSetupTime)
	DDX_DateTimeCtrl(pDX, IDC_TIME, m_Time);
	DDX_DateTimeCtrl(pDX, IDC_DATE, m_Date);
	//}}AFX_DATA_MAP
}


BEGIN_MESSAGE_MAP(CSetupTime, CDialog)
	//{{AFX_MSG_MAP(CSetupTime)
	ON_BN_CLICKED(IDC_GET, OnGet)
	ON_BN_CLICKED(IDC_SET, OnSet)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CSetupTime message handlers

void CSetupTime::OnGet() 
{
	// TODO: Add your control notification handler code here
	time_t localTime;
	BS_RET_CODE result = BS_GetTime( m_Handle, &localTime );

	TIME_ZONE_INFORMATION timeInfo;

	GetTimeZoneInformation( &timeInfo );

	time_t utcTime = localTime + timeInfo.Bias * 60;

	if( result == BS_SUCCESS )
	{
		m_Date = CTime( utcTime );
		m_Time = CTime( utcTime );

		UpdateData( FALSE );
	}
	else
	{
		MessageBox( "Cannot get time\n" );
	}	
}

void CSetupTime::OnSet() 
{
	// TODO: Add your control notification handler code here
	UpdateData();

	CTime utcTime( m_Date.GetYear(), m_Date.GetMonth(), m_Date.GetDay(), m_Time.GetHour(), m_Time.GetMinute(), m_Time.GetSecond() );

	time_t localTime = BS_ConvertToLocalTime( utcTime.GetTime() );

	BS_RET_CODE result = BS_SetTime( m_Handle, localTime );

	if( result != BS_SUCCESS )
	{
		MessageBox( "Cannot set time\n" );
	}	
}

BOOL CSetupTime::OnInitDialog() 
{
	CDialog::OnInitDialog();
	
	// TODO: Add extra initialization here
	m_Date = CTime::GetCurrentTime();
	m_Time = CTime::GetCurrentTime();

	UpdateData( FALSE );
	
	return TRUE;  // return TRUE unless you set the focus to a control
	              // EXCEPTION: OCX Property Pages should return FALSE
}
