/**
 *  BioStation information
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

#ifndef __BS_TERMINAL_H__
#define __BS_TERMINAL_H__

// device type
#define BS_DEVICE_BIOSTATION		0x00
#define BS_DEVICE_BIOENTRY_PLUS		0x01
#define BS_DEVICE_BIOLITE			0x02

// factory default mask
#define BS_FACTORY_DEFAULT_CONFIG	0x01
#define BS_FACTORY_DEFAULT_USER		0x02
#define BS_FACTORY_DEFAULT_LOG		0x04
#define BS_FACTORY_DEFAULT_LED		0x08 // BEPlus only

#define BS_MAX_485_DEVICE           8
#endif