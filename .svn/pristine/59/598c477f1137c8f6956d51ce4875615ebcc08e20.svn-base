#if !defined(AFX_SETUPTIME_H__F31898CF_DE71_4861_94EE_C5445983147B__INCLUDED_)
#define AFX_SETUPTIME_H__F31898CF_DE71_4861_94EE_C5445983147B__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
// SetupTime.h : header file
//

/////////////////////////////////////////////////////////////////////////////
// CSetupTime dialog

class CSetupTime : public CDialog
{
// Construction
public:
	CSetupTime(CWnd* pParent = NULL);   // standard constructor

// Dialog Data
	//{{AFX_DATA(CSetupTime)
	enum { IDD = IDD_SETUP_TIME };
	CTime	m_Time;
	CTime	m_Date;
	//}}AFX_DATA

	void setHandle( int handle ) { m_Handle = handle; }

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CSetupTime)
	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV support
	//}}AFX_VIRTUAL

// Implementation
protected:

	// Generated message map functions
	//{{AFX_MSG(CSetupTime)
	afx_msg void OnGet();
	afx_msg void OnSet();
	virtual BOOL OnInitDialog();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()

	int m_Handle;
};

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_SETUPTIME_H__F31898CF_DE71_4861_94EE_C5445983147B__INCLUDED_)
