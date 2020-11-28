package com.mentor.Datatable;

import java.util.Date;

public class eoi_app_exporder_dt {
	
	//===========brand details dt =====================================================================		
	
	private int srno_brand ;
	private String brand_name ;
	private String package_name ;
	private String etin ;
	private long balance_qty ;
	private double requested_qty ;
	
//===================================== brand details dt = getter and setter ==============================	
	public int getSrno_brand() {
		return srno_brand;
	}
	
	public double getRequested_qty() {
		return requested_qty;
	}

	public void setRequested_qty(double requested_qty) {
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
	
	private long deleted_bottles ;
	//===================================== export order dt = getter and setter ==============================	

	public long getDeleted_bottles() {
		return deleted_bottles;
	}
	public void setDeleted_bottles(long deleted_bottles) {
		this.deleted_bottles = deleted_bottles;
	}
	
	private String del_etin ;

	public String getDel_etin() {
		return del_etin;
	}
	public void setDel_etin(String del_etin) {
		this.del_etin = del_etin;
	}
	
	private boolean flg;

	public boolean isFlg() {
		return flg;
	}
	public void setFlg(boolean flg) {
		this.flg = flg;
	}
	
//===========================
	
	private int srno ;
	
	private int application ;
	
	private String application_dt ;
	
	private long no_of_bottl ;
	
	private String import_no ;

	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public int getApplication() {
		return application;
	}
	public void setApplication(int application) {
		this.application = application;
	}
	public String getApplication_dt() {
		return application_dt;
	}
	public void setApplication_dt(String application_dt) {
		this.application_dt = application_dt;
	}
	public long getNo_of_bottl() {
		return no_of_bottl;
	}
	public void setNo_of_bottl(long no_of_bottl) {
		this.no_of_bottl = no_of_bottl;
	}
	public String getImport_no() {
		return import_no;
	}
	public void setImport_no(String import_no) {
		this.import_no = import_no;
	}
	private String import_order_no ;

	public String getImport_order_no() {
		return import_order_no;
	}
	public void setImport_order_no(String import_order_no) {
		this.import_order_no = import_order_no;
	}
	
	
	//========================================= box and bottles ====================================

	private int requested_box;
	
	private double bottles_in_box ;
	
	private int no_of_box ;
	
	


	public double getBottles_in_box() {
		return bottles_in_box;
	}
	public void setBottles_in_box(double bottles_in_box) {
		this.bottles_in_box = bottles_in_box;
	}
	public int getNo_of_box() {
		return no_of_box;
	}
	public void setNo_of_box(int no_of_box) {
		this.no_of_box = no_of_box;
	}
	public int getRequested_box() {
		
		if(this.requested_qty > 0 && this.bottles_in_box > 0 ){
		this.requested_box =  (int) Math.ceil(this.requested_qty / this.bottles_in_box);
		}else{
			this.requested_box = 0 ;
		}
		
		return requested_box;
	}
	public void setRequested_box(int requested_box) {
		this.requested_box = requested_box;
	}
	
	
	
	
	
}
