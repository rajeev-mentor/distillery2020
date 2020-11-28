package com.mentor.Datatable;

import java.util.Date;

public class BondRegisterDt {
	
	private Date  date;
	private String regNm;
	private String type;
	private String bondSub;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBondSub() {
		return bondSub;
	}
	public void setBondSub(String bondSub) {
		this.bondSub = bondSub;
	}
	public int getSr() {
		return sr;
	}
	public void setSr(int sr) {
		this.sr = sr;
	}
	private int sr;

}
