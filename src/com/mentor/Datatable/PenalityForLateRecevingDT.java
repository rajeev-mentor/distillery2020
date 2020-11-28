package com.mentor.Datatable;

public class PenalityForLateRecevingDT {
	
	private int srno;
	private String gatepass;
	private String gatepass_date;
	private double bond_value;
	private String date_of_lading;
	private int no_of_days;
	private double penality_value;
	
	
	public double getPenality_value() {
		return penality_value;
	}
	public void setPenality_value(double penality_value) {
		this.penality_value = penality_value;
	}
	public int getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public String getGatepass() {
		return gatepass;
	}
	public void setGatepass(String gatepass) {
		this.gatepass = gatepass;
	}
	public String getGatepass_date() {
		return gatepass_date;
	}
	public void setGatepass_date(String gatepass_date) {
		this.gatepass_date = gatepass_date;
	}
	public double getBond_value() {
		return bond_value;
	}
	public void setBond_value(double bond_value) {
		this.bond_value = bond_value;
	}
	public String getDate_of_lading() {
		return date_of_lading;
	}
	public void setDate_of_lading(String date_of_lading) {
		this.date_of_lading = date_of_lading;
	}
	

	
}
