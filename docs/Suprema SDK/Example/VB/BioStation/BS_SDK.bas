Attribute VB_Name = "BS_SDK"
Enum BS_TCPIP
    BS_IP_DISABLE = 0
    BS_IP_ETHERNET = 1
    BS_IP_WLAN = 2
    
    BS_MAX_NETWORK_ADDR_LEN = 32
    
End Enum

Enum BSUser
    BS_MAX_NAME_LEN = 32
    BS_MAX_PASSWORD_LEN = 16
    BS_TEMPLATE_SIZE = 384
    BS_MAX_TEMPLATE_PER_USER = 10
    BS_MAX_PRIVATE_MSG_LEN = 64

    BS_USER_SECURITY_DEFAULT = 260
    BS_USER_SECURITY_LOWER = 261
    BS_USER_SECURITY_LOW = 262
    BS_USER_SECURITY_NORMAL = 263
    BS_USER_SECURITY_HIGH = 264
    BS_USER_SECURITY_HIGHER = 265

    BS_USER_ADMIN = 240
    BS_USER_NORMAL = 241

    BS_BINARY_IMAGE = 0
    BS_GRAY_IMAGE = 1
    
    PRIVATE_TYPE_USER = 0
    PRIVATE_TYPE_GROUP = 1
End Enum

Public Type BSIPConfig
    lanType As Long
    useDHCP As Long
    port As Long
    ipAddr(31) As Byte
    gateway(31) As Byte
    subnetMask(31) As Byte
    serverIP(31) As Byte
    maxConnextion As Long
    useServer As Long
    serverPort As Long
    syncTimeWithServer As Long
    reserved(47) As Long
End Type

Public Type BSLogRecord
    event As Byte
    reserved1 As Byte
    tnaEvent As Integer
    eventTime As Long
    userID As Long
    reserved2 As Long
End Type

Public Type BSUserhdr
    ID As Long
    headerVersion As Integer
    adminLevel As Integer
    securityLevel As Integer
    statusMask As Integer
    accessGroupMask As Long
    userName(BS_MAX_NAME_LEN) As Byte
    department(BS_MAX_NAME_LEN) As Byte
    password(BS_MAX_PASSWORD_LEN) As Byte

    numOfFinger As Integer
    duressMask As Integer
    checksum(4) As Integer
End Type

Public Type BSFingerprintConfig
    security As Long
    userSecurity As Long
    fastMode As Long
    sensitivity As Long
    timeout As Long
    imageQuality As Long
    viewImage As Integer
    reserved(4) As Long
End Type

'wiegand config
Enum BS_WIEGAND_MAX
    MAX_WIEGAND_FIELD = 12
    MAX_WIEGAND_PARITY = 8
End Enum

Enum BS_WIEGAND_FORMAT
    BS_WIEGAND_26BIT = &H1
    BS_WIEGAND_PASS_THRU = &H2
    BS_WIEGAND_CUSTOM = &H3
End Enum

Enum BS_WIEGAND_PARITY_TYPE
    BS_WIEGAND_EVEN_PARITY = 0
    BS_WIEGAND_ODD_PARITY = 1
End Enum

Public Type BSWiegandField
    bitIndex As Long
    bitLength As Long
End Type

Public Type BSWiegandParity
    bitIndex As Long
    type As BS_WIEGAND_PARITY_TYPE
    bitMask(7) As Byte
End Type
'
Public Type BSWiegandFormatHeader
    format As BS_WIEGAND_FORMAT
    totalBits As Long
End Type

Public Type BSWiegandPassThruData
    numOfIDField As Long
    field(MAX_WIEGAND_FIELD - 1) As BSWiegandField
End Type

Public Type BSWiegandCustomData
    numOfField As Long
    idFieldMask As Long
    field(MAX_WIEGAND_FIELD - 1) As BSWiegandField
    numOfParity As Long
    parity(MAX_WIEGAND_PARITY - 1) As BSWiegandParity
End Type

Public Type BSWiegandConfig
    reserved1 As Long
    reserved2 As Long
    header As BSWiegandFormatHeader
    data As BSWiegandCustomData
    fieldValue(MAX_WIEGAND_FIELD - 1) As Long
End Type

Public Type BSCardLayout
    templateSize As Integer
    headerBlock As Byte
    template1StartBlock As Byte
    template1BlockSize As Byte
    template2StartBlock As Byte
    template2BlockSize As Byte
End Type

Enum BSEncryption
    BS_ENCRYPTION_PASSWORD_LEN = 32
End Enum

Public Type BSEncryptionConfig
    useEncryption As Byte
    password(BS_ENCRYPTION_PASSWORD_LEN - 1) As Byte
    reserved(2) As Long
End Type

Type BSUserHdrEx
    ID As Long
    headerVersion As Integer
    adminLevel As Integer
    securityLevel As Integer
    statusMask As Integer
    accessGroupMask As Long
    userName(BS_MAX_NAME_LEN) As Byte
    department(BS_MAX_NAME_LEN) As Byte
    password(BS_MAX_PASSWORD_LEN) As Byte

    numOfFinger As Integer
    duressMask As Integer
    checksum(4) As Integer

    authLimitCount As Long
    timedAntiPassback As Long
    cardID As Long
    bypassCard As Byte
    disabled As Byte
    expireDateTime As Long
    customID As Long
    version As Long
    reserved As Long
End Type


Type BSPrivateInfo
    ID As Long
    department(BS_MAX_NAME_LEN) As Byte
    greetingMsg(BS_MAX_PRIVATE_MSG_LEN) As Byte
    useImage As Long
    duration As Long
    countPerDay As Long
    imageChecksum As Long
    reserved(3) As Long
End Type


Type BSDisplayConfig
    language As Long
    background As Long
    bottomInfo As Long
    reserved1 As Long
    timeout As Long
    volume As Long
    msgTimeout As Long
    usePrivateAuth As Long
    dateType As Long
    reserved2(6) As Long
End Type





Public Declare Sub Sleep Lib "kernel32.dll" (ByVal dwMilliseconds As Long)


Public Declare Function BS_InitSDK Lib "BS_SDK" () As Long

Public Declare Function BS_OpenSocket Lib "BS_SDK" (ByVal biostationAddr As String, ByVal port As Long, ByRef handle As Long) As Long
Public Declare Function BS_CloseSocket Lib "BS_SDK" (ByVal handle As Long) As Long

Public Declare Function BS_OpenUSBMemory Lib "BS_SDK" (ByVal driveLetter As String, ByRef handle As Long) As Long
Public Declare Function BS_CloseUSBMemory Lib "BS_SDK" (ByVal handle As Long) As Long

Public Declare Function BS_OpenUSB Lib "BS_SDK" (ByRef handle As Long) As Long
Public Declare Function BS_CloseUSB Lib "BS_SDK" (ByVal handle As Long) As Long


Public Declare Function BS_GetBiostationID Lib "BS_SDK" (ByVal handle As Long, ByRef biostationID As Long) As Long
Public Declare Function BS_SetBiostationID Lib "BS_SDK" (ByVal handle As Long, ByVal ID As Long) As Long
Public Declare Function BS_ReadLog Lib "BS_SDK" (ByVal handle As Long, ByVal startTime As Long, ByVal endTime As Long, ByRef numOfLog As Long, ByRef logRecord As BSLogRecord) As Long
Public Declare Function BS_ConvertToLocalTime Lib "BS_SDK" (ByVal utcTime As Long) As Long
Public Declare Function BS_ClearLogCache Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_ReadLogCache Lib "BS_SDK" (ByVal handle As Long, ByRef numOfLog As Long, ByRef logRecord As BSLogRecord) As Long
Public Declare Function BS_GetUserDBInfo Lib "BS_SDK" (ByVal handle As Long, ByRef numOfUser As Long, ByRef numOfTemplate As Long) As Long
Public Declare Function BS_Disable Lib "BS_SDK" (ByVal handle As Long, ByVal timeout As Long) As Long
Public Declare Function BS_ScanTemplate Lib "BS_SDK" (ByVal handle As Long, ByRef template As Byte) As Long
Public Declare Function BS_ReadImage Lib "BS_SDK" (ByVal handle As Long, ByVal imageType As Long, ByRef imageData As Byte, ByRef imageSize As Long) As Long
Public Declare Function BS_EnrollUser Lib "BS_SDK" (ByVal handle As Long, ByRef hdr As BSUserhdr, ByRef template As Byte) As Long
Public Declare Function BS_Enable Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_ReadFingerprintConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSFingerprintConfig) As Long
Public Declare Function BS_WriteFingerprintConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSFingerprintConfig) As Long
Public Declare Function BS_ReadIPConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSIPConfig) As Long
Public Declare Function BS_WriteIPConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSIPConfig) As Long
Public Declare Function BS_ReadEncryptionConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSEncryptionConfig) As Long
Public Declare Function BS_WriteEncryptionConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSEncryptionConfig) As Long

Public Declare Function BS_SetKey Lib "BS_SDK" (ByRef key As Byte) As Long
Public Declare Function BS_EncryptTemplate Lib "BS_SDK" (ByRef inputData As Byte, ByRef outputData As Byte, ByVal length As Long) As Long
Public Declare Function BS_DecryptTemplate Lib "BS_SDK" (ByRef inputData As Byte, ByRef outputData As Byte, ByVal length As Long) As Long

Public Declare Function BS_GetTime Lib "BS_SDK" (ByVal handle As Long, ByRef timeVal As Long) As Long
Public Declare Function BS_SetTime Lib "BS_SDK" (ByVal handle As Long, ByVal timeVal As Long) As Long
Public Declare Function BS_Reset Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_UpgradeEx Lib "BS_SDK" (ByVal handle As Long, ByVal upgradeFile As String) As Long

Public Declare Function BS_GetLogCount Lib "BS_SDK" (ByVal handle As Long, ByRef numOfLog As Long) As Long

Public Declare Function BS_DeleteUser Lib "BS_SDK" (ByVal handle As Long, ByVal userID As Long) As Long
Public Declare Function BS_DeleteAllUser Lib "BS_SDK" (ByVal handle As Long) As Long

Public Declare Function BS_GetUser Lib "BS_SDK" (ByVal handle As Long, ByVal userID As Long, ByRef hdr As BSUserhdr, ByRef template As Byte) As Long
Public Declare Function BS_GetUserInfo Lib "BS_SDK" (ByVal handle As Long, ByVal userID As Long, ByRef hdr As BSUserhdr) As Long
Public Declare Function BS_GetAllUserInfo Lib "BS_SDK" (ByVal handle As Long, ByRef hdr As BSUserhdr, ByRef numOfUser As Long) As Long

Public Enum ServerEvent
    BS_SERVER_CB_CONN_NOAUTH = 0
    BS_SERVER_CB_CONN_SSL
    BS_SERVER_CB_CONN_LOG
    BS_SERVER_CB_DISCONN_NOAUTH
    BS_SERVER_CB_DISCONN_SSL
    BS_SERVER_CB_LOG
    KEEP_ALIVE_INTERVAL = 100
End Enum

Public Declare Function BS_StartServerApp Lib "BS_SDK" (ByVal port As Long, ByVal maxConnection As Long, ByVal sslPath As String, ByVal sslPassword As String, ByVal connCheckDuration As Long) As Long
Public Declare Function BS_StopServerApp Lib "BS_SDK" () As Long
Public Declare Function BS_SetCallback Lib "BS_SDK" (ByVal functionID As Long, ByVal proc As Long) As Long
Public Declare Function BS_IssueCertificate Lib "BS_SDK" (ByVal handle As Long, ByVal biostationID As Long) As Long
Public Declare Function BS_StartLogMonitoring Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_GetConnectedList Lib "BS_SDK" (ByVal handle As Long, ByRef biostationList As Long, ByRef count As Long) As Long
Public Declare Function BS_CloseConnection Lib "BS_SDK" (ByVal biostationID As Long) As Long

Public Declare Sub CopyMemory Lib "kernel32" Alias "RtlMoveMemory" (Destination As Any, Source As Any, ByVal length As Long)
Public Declare Function BS_ConvertToUTF8 Lib "BS_SDK" (ByVal msg As String, ByRef utf8Msg As Byte, ByVal limitLen As Long) As Long

Public Declare Function BS_WriteWiegandConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSWiegandConfig) As Long
Public Declare Function BS_ReadWiegandConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSWiegandConfig) As Long

Public Declare Function BS_EnrollUserEx Lib "BS_SDK" (ByVal handle As Long, ByRef hdr As BSUserHdrEx, ByRef template As Byte) As Long
Public Declare Function BS_GetUserEx Lib "BS_SDK" (ByVal handle As Long, ByVal userID As Long, ByRef hdr As BSUserHdrEx, ByRef template As Byte) As Long
Public Declare Function BS_GetUserInfoEx Lib "BS_SDK" (ByVal handle As Long, ByVal userID As Long, ByRef hdr As BSUserHdrEx) As Long
Public Declare Function BS_GetAllUserInfoEx Lib "BS_SDK" (ByVal handle As Long, ByRef hdr As BSUserHdrEx, ByRef numOfUser As Long) As Long

Public Declare Function BS_SetPrivateInfo Lib "BS_SDK" (ByVal handle As Long, ByVal imageType As Long, ByRef privateInfo As BSPrivateInfo, ByVal imagePath As String) As Long
Public Declare Function BS_GetPrivateInfo Lib "BS_SDK" (ByVal handle As Long, ByVal userID As Long, ByRef privateInfo As BSPrivateInfo) As Long
Public Declare Function BS_GetAllPrivateInfo Lib "BS_SDK" (ByVal handle As Long, ByRef privateInfo As BSPrivateInfo, ByRef numOfInfo As Long) As Long


Public Declare Function BS_WriteDisplayConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSDisplayConfig) As Long
Public Declare Function BS_ReadDisplayConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSDisplayConfig) As Long

