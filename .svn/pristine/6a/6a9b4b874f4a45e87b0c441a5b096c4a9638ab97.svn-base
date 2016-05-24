Attribute VB_Name = "BS_SDK"
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
    reserved1 As Integer
    adminLevel As Integer
    securityLevel As Integer
    statusMask As Integer
    accessGroupMask As Long

    username As String * 33
    department As String * 33
    password As String * 17

    numOfFinger As Integer
    duressMask As Integer
    checksum(5) As Integer
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


Public Declare Function BS_InitSDK Lib "BS_SDK" () As Long
Public Declare Function BS_OpenSocket Lib "BS_SDK" (ByVal biostationAddr As String, ByVal port As Long, ByRef handle As Long) As Long
Public Declare Function BS_CloseSocket Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_GetBiostationID Lib "BS_SDK" (ByVal handle As Long, ByRef biostationID As Long) As Long
Public Declare Function BS_SetBiostationID Lib "BS_SDK" (ByVal handle As Long, ByVal ID As Long) As Long
Public Declare Function BS_ReadLog Lib "BS_SDK" (ByVal handle As Long, ByVal startTime As Long, ByVal endTime As Long, ByRef numOfLog As Long, ByRef logRecord As BSLogRecord) As Long
Public Declare Function BS_ConvertToLocalTime Lib "BS_SDK" (ByVal utcTime As Long) As Long

Public Declare Function BS_ClearLogCache Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_ReadLogCache Lib "BS_SDK" (ByVal handle As Long, ByRef numOfLog As Long, ByRef logRecord As BSLogRecord) As Long
Public Declare Function BS_DeleteAllLog Lib "BS_SDK" (ByVal handle As Long, ByVal numOfLog As Long, ByRef numOfDeletedLog As Long) As Long

Public Declare Function BS_GetUserDBInfo Lib "BS_SDK" (ByVal handle As Long, ByRef numOfUser As Long, ByRef numOfTemplate As Long) As Long
Public Declare Function BS_Disable Lib "BS_SDK" (ByVal handle As Long, ByVal timeout As Long) As Long
Public Declare Function BS_ScanTemplate Lib "BS_SDK" (ByVal handle As Long, ByRef template As Byte) As Long
Public Declare Function BS_EnrollUser Lib "BS_SDK" (ByVal handle As Long, ByRef hdr As BSUserhdr, ByRef template As Byte) As Long
Public Declare Function BS_Enable Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_ReadFingerprintConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSFingerprintConfig) As Long
Public Declare Function BS_WriteFingerprintConfig Lib "BS_SDK" (ByVal handle As Long, ByRef config As BSFingerprintConfig) As Long

Public Declare Function BS_OpenUSBMemory Lib "BS_SDK" (ByVal driverletter As String, ByVal handle As Long) As Long
Public Declare Function BS_CloseUSBMemory Lib "BS_SDK" (ByVal handle As Long) As Long

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

'typedef BS_RET_CODE (__stdcall *BS_CallbackProc)( int handle, unsigned biostationID, int type, void* data, int dataLen );
Public Declare Function BS_StartServerApp Lib "BS_SDK" (ByVal port As Long, ByVal maxConnection As Long, ByVal sslPath As String, ByVal sslPassword As String, ByVal connCheckDuration As Long) As Long
Public Declare Function BS_StopServerApp Lib "BS_SDK" () As Long
Public Declare Function BS_SetCallback Lib "BS_SDK" (ByVal functionID As Long, ByVal proc As Long) As Long
Public Declare Function BS_IssueCertificate Lib "BS_SDK" (ByVal handle As Long, ByVal biostationID As Long) As Long
Public Declare Function BS_StartLogMonitoring Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_GetConnectedList Lib "BS_SDK" (ByVal handle As Long, ByRef biostationList As Long, ByRef count As Long) As Long
Public Declare Function BS_CloseConnection Lib "BS_SDK" (ByVal biostationID As Long) As Long

Public Declare Sub CopyMemory Lib "kernel32" Alias "RtlMoveMemory" (Destination As Any, Source As Any, ByVal length As Long)


'BEPlus
Enum BEUserValue
    'card Flag
     NORMAL_CARD = &H0
     BYPASS_CARD = &H1

     'card Version
     CARD_VERSION_1 = &H13

     'Admin level
     USER_LEVEL_NORMAL = 0
     USER_LEVEL_ADMIN = 1

     'Security leve
     USER_SECURITY_DEFAULT = 0
     USER_SECURITY_LOWER = 1
     USER_SECURITY_LOW = 2
     USER_SECURITY_NORMAL = 3
     USER_SECURITY_HIGH = 4
     USER_SECURITY_HIGHER = 5
End Enum

Public Type BEUserHdr
    version As Long
    userID As Long
    startTime As Long
    expiryTime As Long
    cardID As Long
    
    cardCustomID As Byte
    commandCardFlag As Byte
    cardFlag As Byte
    cardVersion As Byte

    adminLevel As Integer
    securityLevel As Integer

    accessGroupMask As Long

    numOfFinger As Integer ' 0, 1, 2
    fingerChecksum(1) As Integer
    isDuress(1) As Byte
    
    disabled As Long
    reserved2(20) As Long
End Type

Enum BS_WIEGAND_FORMAT
    BS_WIEGAND_26BIT = &H1
    BS_WIEGAND_PASS_THRU = &H2
    BS_WIEGAND_CUSTOM = &H3
    MAX_WIEGAND_FIELD = 12
    MAX_WIEGAND_PARITY = 8
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
    bitMask(8) As Byte
End Type
'
Public Type BSWiegandFormatHeader
    format As BS_WIEGAND_FORMAT
    totalBits As Long
End Type

Public Type BSWiegandPassThruData
    numOfIDField As Long
    field(MAX_WIEGAND_FIELD) As BSWiegandField
End Type

Public Type BSWiegandCustomData
    numOfField As Long
    idFieldMask As Long
    field(MAX_WIEGAND_FIELD) As BSWiegandField
    numOfParity As Long
    parity(MAX_WIEGAND_PARITY) As BSWiegandParity
End Type

'typedef union {
'    BSWiegandPassThruData   passThruData;
'    BSWiegandCustomData     customData;
'} BSWiegandFormatData;
'
'
Public Type BSWiegandConfig
    reserved1 As Long
    reserved2 As Long
    header As BSWiegandFormatHeader
    data As BSWiegandCustomData
    fieldValue(MAX_WIEGAND_FIELD) As Long
End Type



Enum IOValue
    'device id
    BS_DEVICE_PRIMARY = &H0
    BS_DEVICE_SECONDARY = &H1
    BS_DEVICE_SECUREIO0 = &H2
    BS_DEVICE_SECUREIO1 = &H3
    BS_DEVICE_SECUREIO2 = &H4
    BS_DEVICE_SECUREIO3 = &H5

    BS_DEVICE_ALL = &HFF
    
    'input port id
    BS_PORT_INPUT0 = &H0
    BS_PORT_INPUT1 = &H1
    BS_PORT_INPUT2 = &H2
    BS_PORT_INPUT3 = &H3
    BS_PORT_TAMPER = &H4

    'relay id
    BS_PORT_RELAY0 = &H0
    BS_PORT_RELAY1 = &H1


    'relay
    RELAY_DISABLED = &HFFFF
    PRIMARY_RELAY = (BS_DEVICE_PRIMARY * (2 ^ 8)) Or BS_PORT_RELAY0
    SECONDARY_RELAY = (BS_DEVICE_SECONDARY * (2 ^ 8)) Or BS_PORT_RELAY0
    SECUREIO0_RELAY0 = (BS_DEVICE_SECUREIO0 * (2 ^ 8)) Or BS_PORT_RELAY0
    SECUREIO0_RELAY1 = (BS_DEVICE_SECUREIO0 * (2 ^ 8)) Or BS_PORT_RELAY1
    SECUREIO1_RELAY0 = (BS_DEVICE_SECUREIO1 * (2 ^ 8)) Or BS_PORT_RELAY0
    SECUREIO1_RELAY1 = (BS_DEVICE_SECUREIO1 * (2 ^ 8)) Or BS_PORT_RELAY1
    SECUREIO2_RELAY0 = (BS_DEVICE_SECUREIO2 * (2 ^ 8)) Or BS_PORT_RELAY0
    SECUREIO2_RELAY1 = (BS_DEVICE_SECUREIO2 * (2 ^ 8)) Or BS_PORT_RELAY1
    SECUREIO3_RELAY0 = (BS_DEVICE_SECUREIO3 * (2 ^ 8)) Or BS_PORT_RELAY0
    SECUREIO3_RELAY1 = (BS_DEVICE_SECUREIO3 * (2 ^ 8)) Or BS_PORT_RELAY1
    
    'RTE
    PRIMARY_INPUT0 = (BS_DEVICE_PRIMARY * (2 ^ 8)) Or BS_PORT_INPUT0
    SECONDARY_INPUT0 = (BS_DEVICE_SECONDARY * (2 ^ 8)) Or BS_PORT_INPUT0
    SECUREIO0_RTE0 = (BS_DEVICE_SECUREIO0 * (2 ^ 8)) Or BS_PORT_INPUT0
    SECUREIO0_RTE1 = (BS_DEVICE_SECUREIO0 * (2 ^ 8)) Or BS_PORT_INPUT2
    SECUREIO1_RTE0 = (BS_DEVICE_SECUREIO1 * (2 ^ 8)) Or BS_PORT_INPUT0
    SECUREIO1_RTE1 = (BS_DEVICE_SECUREIO1 * (2 ^ 8)) Or BS_PORT_INPUT2
    SECUREIO2_RTE0 = (BS_DEVICE_SECUREIO2 * (2 ^ 8)) Or BS_PORT_INPUT0
    SECUREIO2_RTE1 = (BS_DEVICE_SECUREIO2 * (2 ^ 8)) Or BS_PORT_INPUT2
    SECUREIO3_RTE0 = (BS_DEVICE_SECUREIO3 * (2 ^ 8)) Or BS_PORT_INPUT0
    SECUREIO3_RTE1 = (BS_DEVICE_SECUREIO3 * (2 ^ 8)) Or BS_PORT_INPUT2
    
    'Door Sensor
    PRIMARY_INPUT1 = (BS_DEVICE_PRIMARY * (2 ^ 8)) Or BS_PORT_INPUT1
    SECONDARY_INPUT1 = (BS_DEVICE_SECONDARY * (2 ^ 8)) Or BS_PORT_INPUT1
    SECUREIO0_DOOR_SENSOR0 = (BS_DEVICE_SECUREIO0 * (2 ^ 8)) Or BS_PORT_INPUT1
    SECUREIO0_DOOR_SENSOR1 = (BS_DEVICE_SECUREIO0 * (2 ^ 8)) Or BS_PORT_INPUT3
    SECUREIO1_DOOR_SENSOR0 = (BS_DEVICE_SECUREIO1 * (2 ^ 8)) Or BS_PORT_INPUT1
    SECUREIO1_DOOR_SENSOR1 = (BS_DEVICE_SECUREIO1 * (2 ^ 8)) Or BS_PORT_INPUT3
    SECUREIO2_DOOR_SENSOR0 = (BS_DEVICE_SECUREIO2 * (2 ^ 8)) Or BS_PORT_INPUT1
    SECUREIO2_DOOR_SENSOR1 = (BS_DEVICE_SECUREIO2 * (2 ^ 8)) Or BS_PORT_INPUT3
    SECUREIO3_DOOR_SENSOR0 = (BS_DEVICE_SECUREIO3 * (2 ^ 8)) Or BS_PORT_INPUT1
    SECUREIO3_DOOR_SENSOR1 = (BS_DEVICE_SECUREIO3 * (2 ^ 8)) Or BS_PORT_INPUT3

    'Open Event
    ALL_EVENT = &H0
    AUTH_ONLY = &H1
    TNA_ONLY = &H2
    AUTH_N_TNA = &H3

    'Door/switch type
    NORMALLY_OPEN = &H0
    NORMALLY_CLOSED = &H1

    'Reader
    NO_READER = &H0
    PRIMARY_READER = &H1
    SECONDARY_READER = &H2

    MAX_DOOR = 2

    'Door mode
    NO_DOOR = 0
    ONE_DOOR = 1
    TWO_DOOR = 2

    disabled = &H0
    GENERIC_INPUT = &H1
    EMERGENCY_OPEN = &H2
    ALL_ALARM_OFF = &H3
    RESET_READER = &H4
    LOCK_READER = &H5
    
    '16 bit event ID
    AUTH_SUCCESS = &H1
    AUTH_FAIL = &H2
    AUTH_DURESS = &H3
    ANTI_PASSBACK_FAIL = &H4
    ACCESS_NOT_GRANTED = &H5
    ENTRANCE_LIMITATION = &H6
    ADMIN_AUTH_SUCCESS = &H7
    
    TAMPER_ON = &H30
    DOOR_OPEN = &H31
    DOOR_CLOSED = &H32
    DOOR_FORCED_OPEN = &H33
    DOOR_HELD_OPEN_WARNING = &H34

    INPUT0_ON = &H60
    INPUT1_ON = &H61
    INPUT2_ON = &H62
    INPUT3_ON = &H63
    
    NUM_OF_INTERNAL_INPUT = 2
    NUM_OF_SECURE_IO = 4
    NUM_OF_SECURE_INPUT = 4
    NUM_OF_SLAVE_INPUT = 2
    
    MAX_OUTPUT = 200 - 16
End Enum


Public Type BSDoor
    realy As Long
    useRTE As Long
    useDoorSensor As Long
    openEvent As Long
    opentime As Long
    heldOpenTime As Long
    forcedOpenSchedule As Long
    forcedCloseSchedule As Long
    RTEType As Long
    sensorType As Long
    reader(2) As Integer
    reserved2(5) As Integer
End Type


Public Type BSDoorConfig
    door As BSDoor
    apbType As Long
    apbResetTime As Long
    doorMode As Long
End Type


Public Type BSInputFunction
    functionType As Long
    minimumDuration As Integer
    switchType As Integer
    timeSchedule As Long
    reserved(5) As Long
End Type


Public Type BSOutputEvent
    event As Long
    outputDeviceID As Byte
    outputRelayID As Byte
    relayOn As Byte
    reserved1 As Byte
    delay As Integer
    high As Integer
    low As Integer
    count As Integer
    priority As Long
    reserved2(3) As Long
End Type

Public Type BSEMOutputEvent
    inputType As Integer
    outputRelayID As Integer
    inputDuration As Integer
    high As Integer
    low As Integer
    count As Integer
    reserved3(5) As Long
End Type


Public Type BSInputConfig
    internalTamper As BSInputFunction
    internal(NUM_OF_INTERNAL_INPUT) As BSInputFunction

    secureIO(NUM_OF_SECURE_IO, NUM_OF_SECURE_INPUT) As BSInputFunction

    slaveTamper As BSInputFunction
    slaveInput(NUM_OF_SLAVE_INPUT) As BSInputFunction

    reserved(32) As Long
End Type


Public Type BSOutputConfig
    numOfEvent As Long
    outputEvent(MAX_OUTPUT) As BSOutputEvent
    emergencyEvent(NUM_OF_SECURE_IO, NUM_OF_SECURE_INPUT) As BSEMOutputEvent
    reserved(31) As Long
End Type


Enum BEConfigValue
    VALID_MAGIC_NO = &H1F2F3F00
    CONFIG_V01 = &H1

    MAX_OPMODE = &H4
        
    MAX_COMMAND_CARD = 16

    ' entrance type
    BI_READER = 0
    IN_READER = 1
    OUT_READER = 2

    'operation mode
    CARD_OR_FINGER = 0
    CARD_N_FINGER = 1
    CARD_ONLY = 2
    FINGER_ONLY = 3
    disabled = 4

    'security level
    AUTOMATIC_NORMAL = &H0
    AUTOMATIC_SECURE = &H1
    AUTOMATIC_MORE_SECURE = &H2

    'fast mode
    FAST_MODE_AUTO = &H0
    FAST_MODE_NORMAL = &H1
    FAST_MODE_FAST = &H2
    FAST_MODE_FASTER = &H3

    'serial mode
    SERIAL_DISABLED = &H0
    SERIAL_IO_PRIMARY = &H1
    SERIAL_IO_SECONDARY = &H2
    SERIAL_HOST = &H3

    'serial baudrate
    BAUDRATE_115200 = 0
    BAUDRATE_57600 = 1
    BAUDRATE_38400 = 2
    BAUDRATE_19200 = 3
    BAUDRATE_9600 = 4

    'tna mode
    TNA_NONE = &H0
    TNA_FIX_IN = &H1
    TNA_FIX_OUT = &H2
    TNA_AUTO = &H3

    'default access group
    DEFAULT_NO_ACCESS = &H0
    DEFAULT_FULL_ACCESS = &H1
    
    
    BEPLUS_CONFIG = &H50
    
    MAX_ARG = 3
    'LED color
    RED = 0
    YELLOW = 1
    GREEN = 2
    CYAN = 3
    BLUE = 4
    MAGENTA = 5
    WHITE = 6
    
    FADEOUT = &H1000000

    HIGH_FREQ = 3136
    MIDDLE_FREQ = 2637
    LOW_FREQ = 2093
   
    MAX_SIGNAL = 48

    STATUS_NORMAL = &H0
    STATUS_LOCKED
    STATUS_TAMPER_ON
    STATUS_RTC_ERROR
    STATUS_WAITING_INPUT

    SCAN_FINGER = &HC
    SCAN_CARD
End Enum

Public Type BEOutputPattern
    repeat As Long ' 0: indefinite, -1: don't user
    arg(MAX_ARG) As Long 'color for LED, frequency for Buzzer, -1 for last
    high(MAX_ARG) As Integer
    low(MAX_ARG) As Integer
End Type


Public Type BELEDBuzzerConfig
    reserved(4) As Long
    ledPattern(MAX_SIGNAL) As BEOutputPattern
    buzzerPattern(MAX_SIGNAL) As BEOutputPattern
    signalReserved(MAX_SIGNAL) As Integer
End Type



Public Type BECommandCard
    cardID As Long
    customID As Long
    commandType As Long
    needAdminFinger As Byte
End Type
    

Public Type BEConfigData
    magicNo As Long
    version As Long
    timestamp As Long
    checksum As Long
    headerReserved(3) As Long

    opMode(3) As Long
    opModeSchedule(3) As Long
    opDualMode(3) As Byte
    opReserved(7) As Long
    
    useDHCP As Byte
    ipAddr As Long
    gateway As Long
    subnetMask As Long
    serverIpAddr As Long
    port As Long
    useServer As Byte
    synchTime As Byte
    ipReserved(7) As Long
    
    securityLevel As Long
    sensitivity As Long
    fastMode As Long
    timeout As Long
    matchTimeout As Long
    fingerReserved(10) As Long

    inputConfig As BSInputConfig
    outputConfig As BSOutputConfig
    doorConfig As BSDoorConfig
    ioReserved(12) As Long
    
    serialMode As Long
    serialBaudrate As Long
    serialAPBType As Byte
    secureIO As Byte '// 0x01 - Secure I/O 0, 0x02 - Secure I/O 1, 0x04, 0x08
    serialABPResetTime As Byte
    serialFallbackMode As Byte
    serialIsMaster As Byte
    serialReserved(2)  As Byte
    slaveID As Long

    reserved1(13) As Long
     
    minEntryInterval As Long
    numOfEntranceLimit As Long
    maxEntry(3) As Long
    entryLimitInterval(3) As Long
    entranceLimitReserved(7) As Long

    
    numOfCommandCard As Long
    commandCard(MAX_COMMAND_CARD) As BECommandCard
    commandCardReserved(2) As Long

    tnaMode As Long
    autoInSchedule As Long
    autoOutSchedule As Long
    tnaReserved(4) As Long
    
    defaultAG As Long
    userReserved(6) As Long

    reserved2(21) As Long
    
    useWiegandOutput As Byte
    wiegandReserved(6) As Long
    
    wiegandConfig As BSWiegandConfig
    ledBuzzerConfig As BELEDBuzzerConfig
    
    padding(214) As Long

End Type

Public Declare Function BS_OpenInternalUDP Lib "BS_SDK" (ByRef handle As Long) As Long
Public Declare Function BS_CloseInternalUDP Lib "BS_SDK" (ByVal handle As Long) As Long
Public Declare Function BS_SearchDeviceInLAN Lib "BS_SDK" (ByVal handle As Long, ByRef numOfDevice As Long, ByRef deviceIDs As Long, ByRef deviceTypes As Long, ByRef readerAddrs As Long) As Long
Public Declare Function BS_ChangePasswordBEPlus Lib "BS_SDK" (ByVal handle As Long, ByRef oldPassword As Byte, ByRef newPassword As Byte) As Long
Public Declare Function BS_GetDeviceID Lib "BS_SDK" (ByVal handle As Long, ByRef deviceID As Long, ByRef deviceType As Long) As Long
Public Declare Function BS_SearchDevice Lib "BS_SDK" (ByVal handle As Long, ByRef deviceIDs As Long, ByRef deviceTypes As Long, ByRef numOfReader As Long) As Long
Public Declare Function BS_SetDeviceID Lib "BS_SDK" (ByVal handle As Long, ByVal deviceID As Long, ByVal deviceType As Long) As Long

Public Declare Function BS_EnrollUserBEPlus Lib "BS_SDK" (ByVal handle As Long, ByRef hdr As BEUserHdr, ByRef templateData As Byte) As Long
Public Declare Function BS_EnrollMultipleUserBEPlus Lib "BS_SDK" (ByVal handle As Long, ByVal numOfUser As Long, ByRef hdr As BEUserHdr, ByRef templateData As Byte) As Long   'max 64 user
Public Declare Function BS_GetUserBEPlus Lib "BS_SDK" (ByVal handle As Long, ByVal userID As Long, ByRef hdr As BEUserHdr, ByRef templateData As Byte) As Long
Public Declare Function BS_GetUserInfoBEPlus Lib "BS_SDK" (ByVal handle As Long, ByVal userID As Long, ByRef hdr As BEUserHdr) As Long
Public Declare Function BS_GetAllUserInfoBEPlus Lib "BS_SDK" (ByVal handle As Long, ByRef hdr As BEUserHdr, ByRef numOfUser As Long) As Long

Public Declare Function BS_AddCommandCard Lib "BS_SDK" (ByVal cardID As Long, ByVal customID As Byte, ByVal cardType As Long) As Long
Public Declare Function BS_DeleteCommandCard Lib "BS_SDK" (ByVal cardID As Long, ByVal customID As Byte) As Long
Public Declare Function BS_DeleteAllCommandCard Lib "BS_SDK" () As Long
Public Declare Function BS_WriteConfig Lib "BS_SDK" (ByVal handle As Long, ByVal configType As Long, ByVal size As Long, ByRef data As BEConfigData) As Long
Public Declare Function BS_ReadConfig Lib "BS_SDK" (ByVal handle As Long, ByVal configType As Long, ByRef size As Long, ByRef data As BEConfigData) As Long

Public Declare Function inet_addr Lib "ws2_32" (ByRef Addr As Byte) As Long

Public Declare Function BS_ResetUDP Lib "BS_SDK" (ByVal handle As Long, ByVal Addr As Long, ByVal deviceID As Long) As Long
Public Declare Function BS_WriteConfigUDP Lib "BS_SDK" (ByVal handle As Long, ByVal targetAddr As Long, ByVal targetID As Long, ByVal configType As Long, ByVal dataSize As Long, ByRef configData As BEConfigData) As Long
Public Declare Function BS_ReadConfigUDP Lib "BS_SDK" (ByVal handle As Long, ByVal targetAddr As Long, ByVal targetID As Long, ByVal configType As Long, ByRef dataSize As Long, ByRef configData As BEConfigData) As Long
Public Declare Function BS_FactoryDefault Lib "BS_SDK" (ByVal handle As Long, ByVal mask As Long) As Long

