VERSION 5.00
Begin VB.Form BEPlus 
   Caption         =   "BEPlus Sample"
   ClientHeight    =   11370
   ClientLeft      =   60
   ClientTop       =   450
   ClientWidth     =   6885
   LinkTopic       =   "Form1"
   ScaleHeight     =   11370
   ScaleWidth      =   6885
   StartUpPosition =   3  'Windows ±âº»°ª
   Begin VB.Frame Frame2 
      Caption         =   "TCP"
      Height          =   5655
      Left            =   120
      TabIndex        =   8
      Top             =   5640
      Width           =   6615
      Begin VB.CommandButton Command19 
         Caption         =   "FactoryDefault"
         Height          =   855
         Left            =   120
         TabIndex        =   21
         Top             =   3840
         Width           =   2055
      End
      Begin VB.CommandButton Command18 
         Caption         =   "Get All User Info"
         Height          =   735
         Left            =   120
         TabIndex        =   20
         Top             =   1920
         Width           =   2055
      End
      Begin VB.CommandButton Command13 
         Caption         =   "Delete All Log"
         Height          =   855
         Left            =   4440
         TabIndex        =   19
         Top             =   2760
         Width           =   2055
      End
      Begin VB.CommandButton Command1 
         Caption         =   "Open"
         Height          =   735
         Left            =   120
         TabIndex        =   18
         Top             =   240
         Width           =   2055
      End
      Begin VB.CommandButton Command2 
         Caption         =   "Enroll User"
         Height          =   735
         Left            =   4440
         TabIndex        =   17
         Top             =   1080
         Width           =   2055
      End
      Begin VB.CommandButton Command3 
         Caption         =   "Get User Info"
         Height          =   735
         Left            =   4440
         TabIndex        =   16
         Top             =   240
         Width           =   2055
      End
      Begin VB.CommandButton Command4 
         Caption         =   "Close"
         Height          =   735
         Left            =   2280
         TabIndex        =   15
         Top             =   240
         Width           =   2055
      End
      Begin VB.CommandButton Command5 
         Caption         =   "Scan Template0"
         Height          =   735
         Left            =   2280
         TabIndex        =   14
         Top             =   1080
         Width           =   2055
      End
      Begin VB.CommandButton Command6 
         Caption         =   "Scan Template1"
         Height          =   735
         Left            =   120
         TabIndex        =   13
         Top             =   1080
         Width           =   2055
      End
      Begin VB.CommandButton Command8 
         Caption         =   "Read Config"
         Height          =   735
         Left            =   2280
         TabIndex        =   12
         Top             =   1920
         Width           =   2055
      End
      Begin VB.CommandButton Command9 
         Caption         =   "Write Config"
         Height          =   735
         Left            =   4440
         TabIndex        =   11
         Top             =   1920
         Width           =   2055
      End
      Begin VB.CommandButton Command10 
         Caption         =   "Read All Log"
         Height          =   855
         Left            =   120
         TabIndex        =   10
         Top             =   2760
         Width           =   2055
      End
      Begin VB.CommandButton Command11 
         Caption         =   "Read Log"
         Height          =   855
         Left            =   2280
         TabIndex        =   9
         Top             =   2760
         Width           =   2055
      End
   End
   Begin VB.Frame Frame1 
      Caption         =   "UDP"
      Height          =   2055
      Left            =   120
      TabIndex        =   1
      Top             =   3480
      Width           =   6615
      Begin VB.CommandButton Command17 
         Caption         =   "Close UDP"
         Height          =   735
         Left            =   2280
         TabIndex        =   7
         Top             =   240
         Width           =   2055
      End
      Begin VB.CommandButton Command16 
         Caption         =   "Open UDP"
         Height          =   735
         Left            =   120
         TabIndex        =   6
         Top             =   240
         Width           =   2055
      End
      Begin VB.CommandButton Command15 
         Caption         =   "Read Config"
         Height          =   735
         Left            =   2280
         TabIndex        =   5
         Top             =   1080
         Width           =   2055
      End
      Begin VB.CommandButton Command14 
         Caption         =   "Write Config"
         Height          =   735
         Left            =   4440
         TabIndex        =   4
         Top             =   1080
         Width           =   2055
      End
      Begin VB.CommandButton Command12 
         Caption         =   "Search Device"
         Height          =   735
         Left            =   4440
         TabIndex        =   3
         Top             =   240
         Width           =   2055
      End
      Begin VB.CommandButton Command7 
         Caption         =   "Reset Device"
         Height          =   735
         Left            =   120
         TabIndex        =   2
         Top             =   1080
         Width           =   2055
      End
   End
   Begin VB.TextBox Text1 
      Height          =   3255
      Left            =   240
      MultiLine       =   -1  'True
      TabIndex        =   0
      Top             =   120
      Width           =   6375
   End
End
Attribute VB_Name = "BEPlus"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Dim result As Long
Dim deviceType As Long
Dim handle As Long
Dim templateData(768) As Byte
Dim userID As Long
Dim configData As BEConfigData

Dim internalHandle As Long
Dim numOfDevice As Long
Dim deviceIDs(128) As Long
Dim deviceTypes(128) As Long
Dim readerAddrs(128) As Long

Private Sub Command1_Click()
    result = BS_OpenSocket("192.168.1.185", 1471, handle)
    If result <> 0 Then
        MsgBox "Cannot open the communication channel : " & result
        Exit Sub
    End If
    
    result = BS_GetDeviceID(handle, deviceID, deviceType)
    If result <> 0 Then
        MsgBox "Cannot get the ID : " & result
        Exit Sub
    End If
    
    result = BS_SetDeviceID(handle, deviceID, deviceType)
        
    If result <> 0 Then
        MsgBox "Cannot set the ID : " & result
        Exit Sub
    End If
    
    MsgBox "Open Socket Success"
 
End Sub

Private Sub Command10_Click()
    Dim result As Long
    Dim logRecord(32767) As BSLogRecord
    
    result = BS_ReadLog(handle, 0, 0, numOfLog, logRecord(0))
    If result <> 0 Then
        MsgBox "Cannot read log record : " & result
        Exit Sub
    End If
    
    Text1.SelText = "Read " & numOfLog & " log records" & vbCrLf
End Sub

Private Sub Command11_Click()
    Dim currentTime As Long
    Dim result As Long
    Dim logRecord(32767) As BSLogRecord
    
    currentTime = DateDiff("s", "1/1/1970", Now)
    result = BS_ReadLog(handle, currentTime - 1, currentTime, numOfLog, logRecord(0))
    If result <> 0 Then
        MsgBox "Cannot read log record : " & result
        Exit Sub
    End If
    
    Text1.SelText = "Read " & numOfLog & " log records" & vbCrLf
End Sub

Private Sub Command12_Click()
    Dim ipAddress As String
    
    For i = 0 To 128
        deviceIDs(i) = 0
        deviceTypes(i) = 0
        readerAddrs(i) = 0
    Next i
    
    result = BS_SearchDeviceInLAN(internalHandle, numOfDevice, deviceIDs(0), deviceTypes(0), readerAddrs(0))
    If result <> 0 Then
        MsgBox "Cannot search device in lan : " & result
        Exit Sub
    End If
    
    For i = 0 To 128
        If deviceIDs(i) = 0 Then
            Exit Sub
        End If
        
        If deviceTypes(i) = 1 Then
            ipAddress = (readerAddrs(i) And &HFF) & "." & ((readerAddrs(i) And &HFF00) / (2 ^ 8) And &HFF) & "." & ((readerAddrs(i) And &HFF0000) / (2 ^ 16) And &HFF) & "." & ((readerAddrs(i) And &HFF000000) / (2 ^ 24) And &HFF)
            Text1.SelText = "Device:" & deviceIDs(i) & "    Type:" & deviceTypes(i) & "     IP:" & ipAddress & vbCrLf
        End If
    Next i

End Sub

Private Sub Command13_Click()
    result = BS_DeleteAllLog(handle, 0, numOfDeletedLog)
    If result <> 0 Then
        MsgBox "Cannot Delete All Log : " & result
        Exit Sub
    End If

    Text1.SelText = numOfUser & "logs deleted..." & vbCrLf

End Sub

Private Sub Command14_Click()
    Dim Addr(4) As Long
    
    Addr(0) = 192
    Addr(1) = 168
    Addr(2) = 1
    Addr(3) = 85
    configData.serverIpAddr = (Addr(3) * (2 ^ 24)) Or (Addr(2) * (2 ^ 16)) Or (Addr(1) * (2 ^ 8)) Or Addr(0)
        
    Addr(0) = 192
    Addr(1) = 168
    Addr(2) = 1
    Addr(3) = 71
    configData.ipAddr = (Addr(3) * (2 ^ 24)) Or (Addr(2) * (2 ^ 16)) Or (Addr(1) * (2 ^ 8)) Or Addr(0)
    
    configData.port = 1490
    configData.useServer = False
    
    For i = 0 To 128
        If deviceIDs(i) = 0 Then
            Exit Sub
        End If
        
        If deviceTypes(i) = 1 Then
            If deviceIDs(i) = 1059 Then
                result = BS_WriteConfigUDP(internalHandle, readerAddrs(i), deviceIDs(i), BEPLUS_CONFIG, Len(configData), configData)
                If result <> 0 Then
                    MsgBox "Cannot Write Configuration : " & result
                    Exit Sub
                End If
                
                Exit For
            End If
        End If
    Next i

    Text1.SelText = "Write Configuration Success..." & vbCrLf
        
End Sub

Private Sub Command15_Click()
    Dim readSize As Long
    
    For i = 0 To 128
        If deviceIDs(i) = 0 Then
            Exit Sub
        End If
        
        If deviceTypes(i) = 1 Then
            If deviceIDs(i) = 1059 Then
                result = BS_ReadConfigUDP(internalHandle, readerAddrs(i), deviceIDs(i), BEPLUS_CONFIG, readSize, configData)
                If result <> 0 Then
                    MsgBox "Cannot Read Configuration : " & result
                    Exit Sub
                End If
                
                Exit For
            End If
        End If
    Next i
     
    Text1.SelText = "Use DHCP:" & configData.useDHCP & "    IP:" & (configData.ipAddr And &HFF) & "." & ((configData.ipAddr And &HFF00) / (2 ^ 8) And &HFF) & "." & ((configData.ipAddr And &HFF0000) / (2 ^ 16) And &HFF) & "." & ((configData.ipAddr And &HFF000000) / (2 ^ 24) And &HFF) & vbCrLf
    Text1.SelText = "Use Server:" & configData.useServer & "    Server IP:" & (configData.serverIpAddr And &HFF) & "." & ((configData.serverIpAddr And &HFF00) / (2 ^ 8) And &HFF) & "." & ((configData.serverIpAddr And &HFF0000) / (2 ^ 16) And &HFF) & "." & ((configData.serverIpAddr And &HFF000000) / (2 ^ 24) And &HFF) & vbCrLf
    Text1.SelText = "Port:" & configData.port & vbCrLf

End Sub

Private Sub Command16_Click()
    result = BS_OpenInternalUDP(internalHandle)
    If result <> 0 Then
        MsgBox "Cannot open internal udp socket : " & result
        Exit Sub
    End If
End Sub

Private Sub Command17_Click()
    BS_CloseInternalUDP (internalHandle)
End Sub

Private Sub Command18_Click()
    Dim numOfUser, numOfTemplate As Long
    Dim userHdr() As BEUserHdr
    
    result = BS_GetUserDBInfo(handle, numOfUser, numOfTemplate)
    If result <> 0 Then
        MsgBox "Cannot get user info : " & result
        Exit Sub
    End If


    ReDim userHdr(numOfUser)
    result = BS_GetAllUserInfoBEPlus(handle, userHdr(0), numOfUser)
    If result <> 0 Then
        MsgBox "Cannot get all user info : " & result
        Exit Sub
    End If
    
    Text1.SelText = "Total User:" & numOfUser & vbCrLf
End Sub

Private Sub Command19_Click()
    restul = BS_FactoryDefault(hanel, 1)
    If result <> 0 Then
        MsgBox "Factory Default Fail : " & result
        Exit Sub
    End If

    MsgBox "Factory Default Success"
End Sub

Private Sub Command2_Click()
    userID = userID + 1
    
    Dim userHdr As BEUserHdr
    
    userHdr.version = 0
    userHdr.userID = userID
    userHdr.startTime = 0
    userHdr.expiryTime = 0
    userHdr.cardID = 0
    
    userHdr.cardCustomID = 0
    userHdr.commandCardFlag = 0
    userHdr.cardFlag = 0
    userHdr.cardVersion = CARD_VERSION_1

    userHdr.adminLevel = USER_LEVEL_NORMAL
    userHdr.securityLevel = USER_SECURITY_DEFAULT

    userHdr.accessGroupMask = &HFFFFFFFF

    userHdr.numOfFinger = 1
    userHdr.fingerChecksum(0) = 0
    userHdr.fingerChecksum(1) = 0
    
    For i = 0 To i < 384
        userHdr.fingerChecksum(0) = userHdr.fingerChecksum(0) + templateData(i)
        userHdr.fingerChecksum(1) = userHdr.fingerChecksum(1) + templateData(384 + i)
    Next i
    
    userHdr.isDuress(0) = False
    userHdr.isDuress(1) = False
    
    userHdr.disabled = False
    
    result = BS_EnrollUserBEPlus(handle, userHdr, templateData(0))
    If result <> 0 Then
        MsgBox "Cannot enroll user : " & result
        Exit Sub
    End If

    MsgBox "Enroll User Success"

End Sub

Private Sub Command3_Click()
    Dim userHdr As BEUserHdr
    Dim userTemplateData() As Byte

    result = BS_GetUserInfoBEPlus(handle, userID, userHdr)
    If result <> 0 Then
        MsgBox "Cannot get user info : " & result
        Exit Sub
    End If
    
    Text1.SelText = "UserID:" & userHdr.userID & "    NumOfFinger:" & userHdr.numOfFinger & vbCrLf

    ReDim userTemplateData(userHdr.numOfFinger * 384 * 2)
    result = BS_GetUserBEPlus(handle, userID, userHdr, userTemplateData(0))
    If result <> 0 Then
        MsgBox "Cannot get user info : " & result
        Exit Sub
    End If
    
    
    MsgBox "Get User Info Success."

End Sub

Private Sub Command4_Click()
    BS_CloseSocket (handle)
End Sub

Private Sub Command5_Click()
    For i = 0 To 384
        templateData(i) = 0
    Next i
    
    result = BS_ScanTemplate(handle, templateData(0))
        
    If result <> 0 Then
        MsgBox "Cannot scan template 0 : " & result
        Exit Sub
    End If
    
    MsgBox "Scan Template Success"

End Sub

Private Sub Command6_Click()
    For i = 384 To 768
        templateData(i) = 0
    Next i
    
    result = BS_ScanTemplate(handle, templateData(384))
        
    If result <> 0 Then
        MsgBox "Cannot scan template 1 : " & result
        Exit Sub
    End If
    
    MsgBox "Scan Template Success"

End Sub

Private Sub Command7_Click()
    For i = 0 To 128
        If deviceIDs(i) = 0 Then
            Exit Sub
        End If
        
        If deviceTypes(i) = 1 Then
            If deviceIDs(i) = 1059 Then
                result = BS_ResetUDP(internalHandle, readerAddrs(i), deviceIDs(i))
                If result <> 0 Then
                    Text1.SelText = "1059 Reset Fail..." & result & vbCrLf
                Else
                    Text1.SelText = "1059 Reset Success..." & result & vbCrLf
                End If
            End If
        End If
    Next i
    
End Sub

Private Sub Command8_Click()
    Dim readSize As Long
    
    result = BS_ReadConfig(handle, BEPLUS_CONFIG, readSize, configData)
    If result <> 0 Then
        MsgBox "Cannot Read Configuration : " & result
        Exit Sub
    End If
     
    Text1.SelText = "Use DHCP:" & configData.useDHCP & "    IP:" & (configData.ipAddr And &HFF) & "." & ((configData.ipAddr And &HFF00) / (2 ^ 8) And &HFF) & "." & ((configData.ipAddr And &HFF0000) / (2 ^ 16) And &HFF) & "." & ((configData.ipAddr And &HFF000000) / (2 ^ 24) And &HFF) & vbCrLf
    Text1.SelText = "Use Server:" & configData.useServer & "    Server IP:" & (configData.serverIpAddr And &HFF) & "." & ((configData.serverIpAddr And &HFF00) / (2 ^ 8) And &HFF) & "." & ((configData.serverIpAddr And &HFF0000) / (2 ^ 16) And &HFF) & "." & ((configData.serverIpAddr And &HFF000000) / (2 ^ 24) And &HFF) & vbCrLf
End Sub

Private Sub Command9_Click()
    Dim Addr(4) As Long
    
    Addr(0) = 192
    Addr(1) = 168
    Addr(2) = 1
    Addr(3) = 85
    configData.serverIpAddr = (Addr(3) * (2 ^ 24)) Or (Addr(2) * (2 ^ 16)) Or (Addr(1) * (2 ^ 8)) Or Addr(0)
        
    Addr(0) = 192
    Addr(1) = 168
    Addr(2) = 1
    Addr(3) = 71
    configData.ipAddr = (Addr(3) * (2 ^ 24)) Or (Addr(2) * (2 ^ 16)) Or (Addr(1) * (2 ^ 8)) Or Addr(0)
    
    result = BS_WriteConfig(handle, BEPLUS_CONFIG, Len(configData), configData)
    If result <> 0 Then
        MsgBox "Cannot Write Configuration : " & result
        Exit Sub
    End If
End Sub

Private Sub Form_Load()
    result = BS_InitSDK()
    userID = 0
End Sub
