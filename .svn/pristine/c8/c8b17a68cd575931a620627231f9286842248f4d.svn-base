/**
 *  Wiegand interface
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
 
#ifndef __BS_WIEGAND_H__
#define __BS_WIEGAND_H__

#define MAX_WIEGAND_FIELD		12
#define MAX_WIEGAND_PARITY		8

typedef enum {
	BS_WIEGAND_26BIT			= 0x01,
	BS_WIEGAND_PASS_THRU		= 0x02,
	BS_WIEGAND_CUSTOM			= 0x03,
} BS_WIEGAND_FORMAT;

typedef enum {
	BS_WIEGAND_EVEN_PARITY		= 0,
	BS_WIEGAND_ODD_PARITY 		= 1,
} BS_WIEGAND_PARITY_TYPE;

typedef struct {
	int	bitIndex;
	int	bitLength;
} BSWiegandField;

typedef struct {
	int	bitIndex;
	BS_WIEGAND_PARITY_TYPE type;
	unsigned char	bitMask[8];
} BSWiegandParity;

typedef struct {
	BS_WIEGAND_FORMAT	format;
	int					totalBits;
} BSWiegandFormatHeader;

typedef struct {
	int					numOfIDField;
	BSWiegandField 		field[MAX_WIEGAND_FIELD];
} BSWiegandPassThruData;

typedef struct {
	int				numOfField;
	unsigned		idFieldMask;
	BSWiegandField	field[MAX_WIEGAND_FIELD];
	int				numOfParity;
	BSWiegandParity	parity[MAX_WIEGAND_PARITY];
} BSWiegandCustomData;

typedef union {
	BSWiegandPassThruData	passThruData;
	BSWiegandCustomData		customData;
} BSWiegandFormatData;


typedef struct {
    int outWidth;
    int outInterval;
	BSWiegandFormatHeader header;
	BSWiegandFormatData data;
	unsigned fieldValue[MAX_WIEGAND_FIELD];
} BSWiegandConfig;


#endif
