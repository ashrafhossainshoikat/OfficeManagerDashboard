// BioStarCPPDlg.h : header file
//

#if !defined(AFX_BIOSTARCPPDLG_H__254E293C_2F8D_40A0_A8FA_9C3994D583AA__INCLUDED_)
#define AFX_BIOSTARCPPDLG_H__254E293C_2F8D_40A0_A8FA_9C3994D583AA__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#define MAX_DEVICE	128

/////////////////////////////////////////////////////////////////////////////
// CBioStarCPPDlg dialog

class CBioStarCPPDlg : public CDialog
{
// Construction
public:
	CBioStarCPPDlg(CWnd* pParent = NULL);	// standard constructor

// Dialog Data
	//{{AFX_DATA(CBioStarCPPDlg)
	enum { IDD = IDD_BIOSTARCPP_DIALOG };
	CListBox	m_ConnectedDeviceList;
	CListBox	m_DeviceList;
	//}}AFX_DATA

	void addConnectedDevice( unsigned id, unsigned addr, int handle, int type );
	void updateConnectedDevices();

	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CBioStarCPPDlg)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:
	HICON m_hIcon;

	// Generated message map functions
	//{{AFX_MSG(CBioStarCPPDlg)
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	afx_msg void OnSearch();
	afx_msg void OnNetworkConfig();
	afx_msg void OnDisconnect();
	afx_msg void OnTime();
	afx_msg void OnLog();
	afx_msg void OnUser();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()

	int m_Handle;

	int m_NumOfDevice;
	unsigned m_DeviceID[MAX_DEVICE];
	int m_DeviceType[MAX_DEVICE];
	unsigned m_DeviceAddress[MAX_DEVICE];
	
	int m_NumOfConnectedDevice;
	unsigned m_ConnectedDeviceID[MAX_DEVICE];
	unsigned m_ConnectedDeviceAddress[MAX_DEVICE];
	int m_ConnectedHandle[MAX_DEVICE];
	int m_ConnectedDeviceType[MAX_DEVICE];
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_BIOSTARCPPDLG_H__254E293C_2F8D_40A0_A8FA_9C3994D583AA__INCLUDED_)
