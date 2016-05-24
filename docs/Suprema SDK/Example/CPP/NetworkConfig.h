#if !defined(AFX_NETWORKCONFIG_H__0801E8CC_6E87_4284_ADF3_1B4357DD0B81__INCLUDED_)
#define AFX_NETWORKCONFIG_H__0801E8CC_6E87_4284_ADF3_1B4357DD0B81__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// NetworkConfig.h : header file
//

#include "BS_API.h"

/////////////////////////////////////////////////////////////////////////////
// CNetworkConfig dialog

class CNetworkConfig : public CDialog
{
// Construction
public:
	CNetworkConfig(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CNetworkConfig)
	enum { IDD = IDD_NETWORKC_CONFIG };
	CIPAddressCtrl	m_SubnetMask;
	CIPAddressCtrl	m_ServerIP;
	CIPAddressCtrl	m_IPAddr;
	CIPAddressCtrl	m_Gateway;
	CString	m_DeviceInfo;
	BOOL	m_DHCP;
	CString	m_FirmwareVersion;
	CString	m_MacAddr;
	int		m_Port;
	BOOL	m_SyncTime;
	BOOL	m_UseServer;
	UINT	m_DeviceID;
	//}}AFX_DATA

	void setHandle( int handle, unsigned deviceID, unsigned deviceAddr, int deviceType );

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CNetworkConfig)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CNetworkConfig)
	virtual BOOL OnInitDialog();
	afx_msg void OnRefresh();
	afx_msg void OnConnect();
	afx_msg void OnWrite();
	afx_msg void OnNetworkRefresh();
	afx_msg void OnDhcp();
	afx_msg void OnUseServer();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()

	bool readSysInfoBEPlus( BESysInfoData* sysInfo );
	bool readConfigBEPlus( BEConfigData* config );

	bool readSysInfoBLN( BESysInfoDataBLN* sysInfo );
	bool readConfigBLN( BEConfigDataBLN* config );

	bool readSysInfoBST( BSSysInfoConfig* sysInf );
	bool readConfigBST( BSIPConfig* config );

	int m_Handle;
	unsigned m_ID;
	unsigned m_Addr;
	int m_DeviceType;
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_NETWORKCONFIG_H__0801E8CC_6E87_4284_ADF3_1B4357DD0B81__INCLUDED_)
