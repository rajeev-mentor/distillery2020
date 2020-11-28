package com.mentor.Datatable;

import java.util.Date;

public class PD2recievingDt {
	private int srno;
	private String gatepass_no;
	private Date gate_dt;
	private String vat_name;
	private String al;
	private String bl;
	private Date recv_dt;
	
	
	public Date getRecv_dt() {
		return recv_dt;
	}
	public void setRecv_dt(Date recv_dt) {
		this.recv_dt = recv_dt;
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public String getGatepass_no() {
		return gatepass_no;
	}
	public void setGatepass_no(String gatepass_no) {
		this.gatepass_no = gatepass_no;
	}
	public Date getGate_dt() {
		return gate_dt;
	}
	public void setGate_dt(Date gate_dt) {
		this.gate_dt = gate_dt;
	}
	public String getVat_name() {
		return vat_name;
	}
	public void setVat_name(String vat_name) {
		this.vat_name = vat_name;
	}
	public String getAl() {
		return al;
	}
	public void setAl(String al) {
		this.al = al;
	}
	public String getBl() {
		return bl;
	}
	public void setBl(String bl) {
		this.bl = bl;
	}
	
	private int srno_s;
	private String vat_name_s;
	public int getSrno_s() {
		return srno_s;
	}
	public void setSrno_s(int srno_s) {
		this.srno_s = srno_s;
	}
	public String getVat_name_s() {
		return vat_name_s;
	}
	public void setVat_name_s(String vat_name_s) {
		this.vat_name_s = vat_name_s;
	}
	public String getAl_s() {
		return al_s;
	}
	public void setAl_s(String al_s) {
		this.al_s = al_s;
	}
	public String getBl_s() {
		return bl_s;
	}
	public void setBl_s(String bl_s) {
		this.bl_s = bl_s;
	}

	private String al_s;
	private String bl_s;

}
