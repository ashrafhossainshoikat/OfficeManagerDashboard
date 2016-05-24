/**
 *  User management
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

#ifndef __BS_USER_H__
#define __BS_USER_H__

#define BS_MAX_NAME_LEN 32
#define BS_MAX_PASSWORD_LEN 16
#define BS_TEMPLATE_SIZE 384
#define BS_MAX_TEMPLATE_PER_USER 10
#define BS_MAX_PRIVATE_MSG_LEN 64


#define BS_USER_HEADER_ONLY	0x01
#define BS_USER_HEADER_TEMPLATE 0x02
#define BS_USER_HEADER_PICTURE 0x03
#define BS_USER_HEADER_TEMPLATE_PICTURE 0x04

#define BS_USER_SECURITY_DEFAULT 260
#define BS_USER_SECURITY_LOWER 261
#define BS_USER_SECURITY_LOW 262
#define BS_USER_SECURITY_NORMAL 263
#define BS_USER_SECURITY_HIGH 264
#define BS_USER_SECURITY_HIGHER 265

#define BS_USER_ADMIN 240
#define BS_USER_NORMAL 241

#define BS_BINARY_IMAGE	0
#define BS_GRAY_IMAGE	1

#define CARD_INFO_VERSION 0x13
#define DEFAULT_CUSTOMID 0x01

#define BS_AUTH_MODE_DISABLED	 0x00

typedef struct{
	unsigned ID;

	unsigned short reserved1;
	unsigned short adminLevel;
	unsigned short securityLevel;
	unsigned short statusMask; 
	unsigned accessGroupMask;

	char name[BS_MAX_NAME_LEN + 1];
	char department[BS_MAX_NAME_LEN + 1];
	char password[BS_MAX_PASSWORD_LEN + 1];

	unsigned short numOfFinger;
	unsigned short duressMask;
	unsigned short checksum[5]; // index 0, 1
} BSUserHdr;


typedef struct{
	unsigned ID;

	unsigned short headerVersion;
	unsigned short adminLevel;
	unsigned short securityLevel;
	unsigned short statusMask; 
	unsigned accessGroupMask;

	char name[BS_MAX_NAME_LEN + 1];
	char department[BS_MAX_NAME_LEN + 1];
	char password[BS_MAX_PASSWORD_LEN + 1];

	unsigned short numOfFinger;
	unsigned short duressMask;
	unsigned short checksum[5]; // index 0, 1

	unsigned short authMode; //2
	unsigned short authLimitCount; // 0 for no limit //2
	unsigned short reserved; //2
	unsigned short timedAntiPassback; // in minutes. 0 for no limit //2
	unsigned cardID; // 0 for not used
	bool	bypassCard;
	bool	disabled;
	unsigned expireDateTime;
	int customID; //card Custom ID
	int version; // card Info Version
    unsigned startDateTime; //4 36
} BSUserHdrEx;

#define PRIVATE_TYPE_USER 0
#define PRIVATE_TYPE_GROUP 1
typedef struct{
	unsigned ID;
	char department[BS_MAX_NAME_LEN + 1];
	char greetingMsg[BS_MAX_PRIVATE_MSG_LEN + 1];
	int useImage;
	unsigned duration;
	unsigned countPerDay;
	unsigned imageChecksum;  
	int reserved[4];
} BSPrivateInfo;

#endif