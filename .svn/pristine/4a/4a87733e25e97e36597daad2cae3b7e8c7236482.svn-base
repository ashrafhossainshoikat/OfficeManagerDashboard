#if !defined(AFX_LOGMANAGEMENT_H__7286F0F5_5729_4FFD_97B0_2D04F9320973__INCLUDED_)
#define AFX_LOGMANAGEMENT_H__7286F0F5_5729_4FFD_97B0_2D04F9320973__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// LogManagement.h : header file
//

#include "BS_API.h"

/////////////////////////////////////////////////////////////////////////////
// CLogManagement dialog

class CLogManagement : public CDialog
{
// Construction
public:
	CLogManagement(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CLogManagement)
	enum { IDD = IDD_LOG_MANAGEMENT };
	CListCtrl	m_LogList;
	CString	m_Device;
	int		m_NumOfLog;
	//}}AFX_DATA

	void setDevice( int handle, unsigned readerID, unsigned readerAddr );

	bool readLogInfo();
	bool readLog();
	bool deleteLog();

	void updateLogRecord( int numOfLog, BSLogRecord* logRecord );
	void addLogRecord( BSLogRecord* logRecord );

	const char* getEventString( int eventCode );

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CLogManagement)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CLogManagement)
	virtual BOOL OnInitDialog();
	afx_msg void OnRead();
	afx_msg void OnDeleteAll();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()

	int m_Handle;
	unsigned m_ID;
	unsigned m_Addr;
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_LOGMANAGEMENT_H__7286F0F5_5729_4FFD_97B0_2D04F9320973__INCLUDED_)
