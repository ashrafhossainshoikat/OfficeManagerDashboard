/**
 *  Configuration
 *
 *  @author sjlee@suprema.co.kr
 *  @see    
 */


/*  
 *  Copyright (c) 2006 Suprema Co., Ltd. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of 
 *  Suprema Co., Ltd. ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Suprema.
 */

#ifndef __BS_CONFIG_H__
#define __BS_CONFIG_H__

#include "BS_Error.h"

#define BS_CONFIG_FINGERPRINT	0x01
#define BS_CONFIG_IO			0x02
#define BS_CONFIG_RELAY			0x03
#define BS_CONFIG_ENCRYPTION	0x04
#define BS_CONFIG_TCPIP			0x10
#define BS_CONFIG_WLAN			0x11
#define BS_CONFIG_SERIAL		0x12
#define BS_CONFIG_USB			0x13
#define BS_CONFIG_DISPLAY		0x20
#define BS_CONFIG_OP_MODE		0x30
#define BS_CONFIG_TNA_EVENT		0x31
#define BS_CONFIG_TNA_EVENT_EX		0x32
#define BS_CONFIG_ENTRANCE_LIMIT 0x33
#define BS_CONFIG_INFO			0x40
#define BS_CONFIG_SYS_INFO		0x41
#define BS_CONFIG_WIEGAND		0x42

#define BS_CONFIG_DOOR			0x60
#define BS_CONFIG_INPUT			0x61
#define BS_CONFIG_OUTPUT		0x62
#define BS_CONFIG_ZONE			0x63
#define BS_CONFIG_ZONE_EX		0x64
#define BS_CONFIG_485_NET		0x65
#define BS_CONFIG_SSL		    0x66

#define BS_MAX_TNA_FUNCTION_KEY 16
#define BS_MAX_TNA_EVENT_LEN	16
#define BS_MAX_NETWORK_ADDR_LEN 32
#define BS_MAX_WLAN_PRESET		4

typedef struct {
	unsigned ID;
	char macAddr[32];
	char productName[32];
	char boardVer[16];
	char firmwareVer[16];
	char blackfinVer[16];
	char kernelVer[16];
	int language;
	char reserved[32];
} BSSysInfoConfig;

#define BS_UI_LANG_KOREAN	1220
#define BS_UI_LANG_ENGLISH	1221
#define BS_UI_LANG_CUSTOM	1222

#define BS_UI_BG_LOGO		1240
#define BS_UI_BG_NOTICE		1242
#define BS_UI_BG_PICTURE	1244

#define BS_UI_INFO_NONE		1260
#define BS_UI_INFO_TIME		1261
#define BS_UI_INFO_NOTICE	1263
	
#define BS_UI_TIMEOUT_INDEFINITE	1280
#define BS_UI_TIMEOUT_10	1282
#define BS_UI_TIMEOUT_20	1284
#define BS_UI_TIMEOUT_30	1286

// Message timeout
#define BS_MSG_TIMEOUT_500MS	0
#define BS_MSG_TIMEOUT_1000MS	1
#define BS_MSG_TIMEOUT_2000MS	2
#define BS_MSG_TIMEOUT_3000MS	3
#define BS_MSG_TIMEOUT_4000MS	4
#define BS_MSG_TIMEOUT_5000MS	5

#define BS_UI_DATE_TYPE_AM 1320
#define BS_UI_DATE_TYPE_EU 1321
typedef struct {
	int language;
	int background;
	int bottomInfo;
	int reserved1; // bool useSound;
	int timeout; // 0 for indefinite
	int volume; // 0 ~ 100
	int msgTimeout;
	int usePrivateAuth;
	int dateType;
	int reserved2[7];
} BSDisplayConfig;


#define BS_AUTH_FINGER_ONLY			1020
#define BS_AUTH_FINGER_N_PASSWORD	1021
#define BS_AUTH_FINGER_OR_PASSWORD	1022
#define BS_AUTH_PASS_ONLY			1023
#define BS_AUTH_CARD_ONLY			1024

#define BS_1TON_FREESCAN	1040
#define BS_1TON_BUTTON		1041
#define BS_1TON_DISABLE		1042

#define BS_TNA_DISABLE		1060
#define BS_TNA_FUNCTION_KEY	1061
#define BS_TNA_AUTO			1062

#define	BS_TNA_AUTO_CHANGE 1070
#define	BS_TNA_MANUAL_CHANGE 1071
#define BS_TNA_FIXED 1072

#define MAX_AUTH_COUNT 4
#define FINGER_INDEX 0
#define PIN_INDEX 1
#define FINGER_PIN_INDEX 2
#define CARD_INDEX 3

#define MAX_AUTH_EX_COUNT 1
#define FINGER_N_PIN_INDEX 0

#define OP_CONFIG_VERSION14 0x14
#define OP_CONFIG_VERSION15 0x15

#define BS_COMMON_DISABLE 135
#define BS_OP_CARD_CSN 1073
#define BS_OP_CARD_TEMPLATE 1074

typedef struct {
	int authMode;
	int identificationMode;
	int tnaMode;
	int tnaChange;
	unsigned char authSchedule[MAX_AUTH_COUNT];
	unsigned char identificationSchedule;
	unsigned char dualMode;
	unsigned char dualSchedule;
	unsigned char version;
	int cardMode;
	unsigned char authScheduleEx[MAX_AUTH_EX_COUNT];
	unsigned char usePrivateAuthMode;
	char reserved[2];
} BSOPModeConfig ;


#define BS_TNA_F1	0
#define BS_TNA_F2	1
#define BS_TNA_F3	2
#define BS_TNA_F4	3
#define BS_TNA_1	4
#define BS_TNA_2	5
#define BS_TNA_3	6
#define BS_TNA_4	7
#define BS_TNA_5	8
#define BS_TNA_6	9
#define BS_TNA_7	10
#define BS_TNA_8	11
#define BS_TNA_9	12
#define BS_TNA_CALL	13
#define BS_TNA_0	14	
#define BS_TNA_ESC	15


typedef struct {
	unsigned char enabled[BS_MAX_TNA_FUNCTION_KEY]; 
	unsigned char useRelay[BS_MAX_TNA_FUNCTION_KEY];
	unsigned short reserved[BS_MAX_TNA_FUNCTION_KEY];  
	char eventStr[BS_MAX_TNA_FUNCTION_KEY][BS_MAX_TNA_EVENT_LEN];
} BSTnaEventConfig;


typedef struct {
	int fixedTnaIndex;
	int manualTnaIndex;
	int timeSchedule[BS_MAX_TNA_FUNCTION_KEY];
} BSTnaEventExConfig;


typedef struct {
	unsigned reserved1;
	unsigned char masterPassword[16];
	char reserved2[44];
} BSInfoConfig;


#define BS_IP_DISABLE 0
#define BS_IP_ETHERNET 1
#define BS_IP_WLAN 2

#define BS_SERVER_DISABLE 0
#define BS_SERVER_ADMIN 1
#define BS_SERVER_STAR 2        

typedef struct {
	int lanType;
	bool useDHCP;
	unsigned port; 
	char ipAddr[BS_MAX_NETWORK_ADDR_LEN];
	char gateway[BS_MAX_NETWORK_ADDR_LEN];
	char subnetMask[BS_MAX_NETWORK_ADDR_LEN];
	char serverIP[BS_MAX_NETWORK_ADDR_LEN];
	int	 maxConnection;
	unsigned char useServer;
	unsigned serverPort;
	bool syncTimeWithServer;
	char reserved[48];
} BSIPConfig;


#define BS_WLAN_MANAGED 2060
#define BS_WLAN_AD_HOC	2061

// authType
#define BS_WLAN_AUTH_OPEN		2080
#define BS_WLAN_AUTH_SHARED		2081
#define BS_WLAN_AUTH_WPA_PSK	2082

// encryptionType
#define BS_WLAN_NO_ENCRYPTION	2090
#define BS_WLAN_WEP				2091
#define BS_WLAN_TKIP_AES		2092

#define BS_WLAN_KEY_ASCII		2100
#define BS_WLAN_KEY_HEX			2101

typedef struct {
	char name[BS_MAX_NETWORK_ADDR_LEN];
	int operationMode;
	short authType;
	short encryptionType;
	int keyType;
	char essid[BS_MAX_NETWORK_ADDR_LEN];
	char key1[BS_MAX_NETWORK_ADDR_LEN];
	char key2[BS_MAX_NETWORK_ADDR_LEN];
	char wpaPassphrase[64];
} BSWLANPreset;


typedef struct {
	int selected;
	BSWLANPreset preset[BS_MAX_WLAN_PRESET];
} BSWLANConfig;


#define BS_SECURITY_NORMAL		1680
#define BS_SECURITY_SECURE		1681
#define BS_SECURITY_MORE_SECURE	1682

#define BS_USER_SECURITY_READER	1700
#define BS_USER_SECURITY_USER	1701

#define BS_FAST_MODE_NORMAL		1720
#define BS_FAST_MODE_FAST		1721
#define BS_FAST_MODE_FASTER		1722
#define BS_FAST_MODE_AUTO		1723

#define BS_IMAGE_QUALITY_WEAK		320
#define BS_IMAGE_QUALITY_MODERATE	321
#define BS_IMAGE_QUALITY_STRONG		322

#define BS_FREESCAN_0 1910
#define BS_FREESCAN_1 1911
#define BS_FREESCAN_2 1912
#define BS_FREESCAN_3 1913
#define BS_FREESCAN_4 1914
#define BS_FREESCAN_5 1915
#define BS_FREESCAN_6 1916
#define BS_FREESCAN_7 1917
#define BS_FREESCAN_8 1918
#define BS_FREESCAN_9 1919
#define BS_FREESCAN_10 1920

typedef struct {
	int security;
	int userSecurity;
	int fastMode;
	int sensitivity; // 0(Least) ~ 7(Most)
	int timeout; // 0 for indefinite, 1 ~ 20 sec
	int imageQuality;
	bool viewImage;
	int freeScanDelay;
	int useCheckDuplicate;
	int matchTimeout;
	short useSIF;
	short useFakeDetect;
	bool useServerMatching;
    char reserved[3];
} BSFingerprintConfig;


#define BS_NUM_OF_INPUT	2
#define BS_NUM_OF_OUTPUT 2

#define	BS_IO_INPUT_DISABLED		1840
#define	BS_IO_INPUT_EXIT			1841
#define	BS_IO_INPUT_WIEGAND_CARD 1842
#define	BS_IO_INPUT_WIEGAND_USER 1843

#define BS_IO_OUTPUT_DISABLED		1860
#define BS_IO_OUTPUT_DURESS			1861
#define BS_IO_OUTPUT_TAMPER			1862
#define BS_IO_OUTPUT_AUTH_SUCCESS	1863
#define	BS_IO_OUTPUT_AUTH_FAIL		1864
#define	BS_IO_OUTPUT_WIEGAND_USER 1865
#define	BS_IO_OUTPUT_WIEGAND_CARD 1866

#define BS_IO_TAMPER_NONE			1880
#define BS_IO_TAMPER_LOCK_SYSTEM	1881

typedef struct {
	int input[BS_NUM_OF_INPUT];
	int output[BS_NUM_OF_OUTPUT];
	int tamper;
	int outputDuration;
	int inputDuration[BS_NUM_OF_INPUT];
	int inputSchedule[BS_NUM_OF_INPUT];
	short inputType[BS_NUM_OF_INPUT];
	int reserved[58];
} BSIOConfig;


#define	BS_RELAY_EVENT_ALL 1800
#define	BS_RELAY_EVENT_AUTH_TNA 1801
#define	BS_RELAY_EVENT_NONE 1802
#define	BS_RELAY_EVENT_AUTH 1803
#define	BS_RELAY_EVENT_TNA 1804

typedef struct {
	int event;
	int openDuration;
	int lockSchedule;
	int unlockSchedule;
	bool useInterphone;
	int reserved[7];
} BSRelayConfig;


#define BS_CHANNEL_DISABLED	 135

#define BS_485_HOST_DEIVCE	1
#define BS_485_SLAVE_DEVICE	2

typedef struct {
	int rs485;
	int rs232;
	int useSecureIO;
	char activeSecureIO[4]; // 0 ~ 3 - byte[0] ~ byte[3]
	unsigned slaveID;  
	int deviceType; 
	int reserved[2];
} BSSerialConfig;

typedef struct {
	bool connectToPC; 
	int reserved[7];
} BSUSBConfig;


#define BS_ENCRYPTION_PASSWORD_LEN	32

typedef struct {
	bool useEncryption;
	unsigned char password[BS_ENCRYPTION_PASSWORD_LEN];
	int useProtection;
	int reserved[2];
} BSEncryptionConfig;

typedef struct {
	int minEntryInterval; // 0 for no limit
	int numOfEntranceLimit; // MAX 4
	int maxEntry[4]; // 0 (no limit) ~ 16
	unsigned entryLimitInterval[4];
	int defaultAccessGroup;
    int bypassGroupId;
	int entranceLimitReserved[6]; 
} BSEntranceLimit;

typedef struct{
 	unsigned slaveID;
    int slaveType;
} BS485SlaveInfo;


typedef struct {
	enum 
    {
        TYPE_DISABLE = 0,
        TYPE_CONN_PC = 3,
        TYPE_HOST = 4,
        TYPE_SLAVE = 5,
 
        MAX_NUM_OF_SIO = 4,
        MAX_NUM_OF_SLAVE = 7,
    };
    
    unsigned short deviceType;
    unsigned short useIO;
	char activeSIO[MAX_NUM_OF_SIO];
 	BS485SlaveInfo slaveInfo[MAX_NUM_OF_SLAVE];
    int reserved[18];
} BS485NetworkConfig;

typedef struct {
	bool useSSL;
    unsigned char reserved[11];
} BSSSLConfig;


#endif
