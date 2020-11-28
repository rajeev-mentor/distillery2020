package com.mentor.Datatable;

import java.util.Date;

public class Download_Export_OrderDT {
private Integer sn;
private Date orderdate;
private long orderno;
private String status;
private String contno;
private String seal;
private Date sealdate;
private String impu;
private int appNO ;
private String pdfName;
private boolean objection_reply_button;
private String sealstatus;
private String conttype;
private boolean sealstausFlag;


public boolean isSealstausFlag() {
	return sealstausFlag;
}
public void setSealstausFlag(boolean sealstausFlag) {
	this.sealstausFlag = sealstausFlag;
}
public String getSealstatus() {
	return sealstatus;
}
public void setSealstatus(String sealstatus) {
	this.sealstatus = sealstatus;
}
public String getConttype() {
	return conttype;
}
public void setConttype(String conttype) {
	this.conttype = conttype;
}
public int getAppNO() {
	return appNO;
}
public void setAppNO(int appNO) {
	this.appNO = appNO;
}
public boolean isObjection_reply_button() {
	return objection_reply_button;
}
public void setObjection_reply_button(boolean objection_reply_button) {
	this.objection_reply_button = objection_reply_button;
}
public String getPdfName() {
	return pdfName;
}
public void setPdfName(String pdfName) {
	this.pdfName = pdfName;
}

public Integer getSn() {
	return sn;
}
public void setSn(Integer sn) {
	this.sn = sn;
}
public Date getOrderdate() {
	return orderdate;
}
public void setOrderdate(Date orderdate) {
	this.orderdate = orderdate;
}
public long getOrderno() {
	return orderno;
}
public void setOrderno(long orderno) {
	this.orderno = orderno;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

public String getContno() {
	return contno;
}
public void setContno(String contno) {
	this.contno = contno;
}
public String getSeal() {
	return seal;
}
public void setSeal(String seal) {
	this.seal = seal;
}
public Date getSealdate() {
	return sealdate;
}
public void setSealdate(Date sealdate) {
	this.sealdate = sealdate;
}

public String getImpu() {
	return impu;
}
public void setImpu(String impu) {
	this.impu = impu;
}




}
