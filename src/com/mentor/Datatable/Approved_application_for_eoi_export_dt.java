package com.mentor.Datatable;

import java.util.Date;

public class Approved_application_for_eoi_export_dt {
	

	
	//===========brand details dt =====================================================================		
	
	private int srno_brand ;
	private String brand_name ;
	private String package_name ;
	private String etin ;
	private long balance_qty ;
	private long requested_qty ;
	
//===================================== brand details dt = getter and setter ==============================	
	public int getSrno_brand() {
		return srno_brand;
	}
	public long getRequested_qty() {
		return requested_qty;
	}
	public void setRequested_qty(long requested_qty) {
		this.requested_qty = requested_qty;
	}
	public void setSrno_brand(int srno_brand) {
		this.srno_brand = srno_brand;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	

	public String getPackage_name() {
		return package_name;
	}
	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}
	public String getEtin() {
		return etin;
	}
	public void setEtin(String etin) {
		this.etin = etin;
	}
	public long getBalance_qty() {
		return balance_qty;
	}
	public void setBalance_qty(long balance_qty) {
		this.balance_qty = balance_qty;
	}
	
	
	
	
	
	//==================================  export order dt ================================================
	private int srno_export ;
	private int application_no ;
	private String application_date ;
	private long no_of_bottles ;
	private long ceo_no ;
	private long brc_no ;
	private Date reciept_at_icd ;

	public int getSrno_export() {
		return srno_export;
	}
	public void setSrno_export(int srno_export) {
		this.srno_export = srno_export;
	}
	public int getApplication_no() {
		return application_no;
	}
	public void setApplication_no(int application_no) {
		this.application_no = application_no;
	}
	public String getApplication_date() {
		return application_date;
	}
	public void setApplication_date(String application_date) {
		this.application_date = application_date;
	}
	public long getNo_of_bottles() {
		return no_of_bottles;
	}
	public void setNo_of_bottles(long no_of_bottles) {
		this.no_of_bottles = no_of_bottles;
	}
	public long getCeo_no() {
		return ceo_no;
	}
	public void setCeo_no(long ceo_no) {
		this.ceo_no = ceo_no;
	}
	public long getBrc_no() {
		return brc_no;
	}
	public void setBrc_no(long brc_no) {
		this.brc_no = brc_no;
	}
	public Date getReciept_at_icd() {
		return reciept_at_icd;
	}
	public void setReciept_at_icd(Date reciept_at_icd) {
		this.reciept_at_icd = reciept_at_icd;
	}
	
	//===================================== export order dt = getter and setter ==============================	
	
	
	
	


}
