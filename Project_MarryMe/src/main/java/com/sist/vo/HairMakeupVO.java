package com.sist.vo;

import lombok.Data;

/*
MNO      NOT NULL NUMBER         
NAME     NOT NULL VARCHAR2(50)   
INFO              VARCHAR2(1000) 
THUMB             VARCHAR2(300)  
TEL      NOT NULL VARCHAR2(20)   
KEYWORD           VARCHAR2(300)  
OPENDAY           VARCHAR2(100)  
OPENTIME          VARCHAR2(100)  
CONCEPT           VARCHAR2(4000) 
POINT             VARCHAR2(4000) 
NOTICE            VARCHAR2(4000) 
CANCEL            VARCHAR2(4000) 
REFUND            VARCHAR2(4000) 
VIDEO             VARCHAR2(1000) 
ADDRESS  NOT NULL VARCHAR2(200)  
LINK              VARCHAR2(200)  
 */
@Data
public class HairMakeupVO {
	private int mno, shno;
	private String name, info, thumb, tel, keyword, openday, opentime, 
		concept, point, notice, cancel, refund, video, address, link;
}
