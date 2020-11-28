package com.mentor.Datatable;

import java.util.*;

public class UploadShippingAndLadingBillDT {

	private int sno;
	private int srno;
	private String vch_gatepass_no;
	private Date dt_date;
	private int total_dis_box;
	private int total_dis_bottles;
	private String brand_name;
	private String package_name;
	private int dispatch_box;
	private int dispatch_bottle;
	private String shipping_pdf;
	private String lading_pdf;
	private String brc_pdf;
	private String seal_no;
	private String container;
	private String container_no;
	private Date seal_dt;
	
	
	
	
	public String getSeal_no() {
		return seal_no;
	}
	public void setSeal_no(String seal_no) {
		this.seal_no = seal_no;
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public String getContainer_no() {
		return container_no;
	}
	public void setContainer_no(String container_no) {
		this.container_no = container_no;
	}
	public Date getSeal_dt() {
		return seal_dt;
	}
	public void setSeal_dt(Date seal_dt) {
		this.seal_dt = seal_dt;
	}
	public String getBrc_pdf() {
		return brc_pdf;
	}
	public void setBrc_pdf(String brc_pdf) {
		this.brc_pdf = brc_pdf;
	}
	public String getShipping_pdf() {
		return shipping_pdf;
	}
	public void setShipping_pdf(String shipping_pdf) {
		this.shipping_pdf = shipping_pdf;
	}
	public String getLading_pdf() {
		return lading_pdf;
	}
	public void setLading_pdf(String lading_pdf) {
		this.lading_pdf = lading_pdf;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public String getVch_gatepass_no() {
		return vch_gatepass_no;
	}
	public void setVch_gatepass_no(String vch_gatepass_no) {
		this.vch_gatepass_no = vch_gatepass_no;
	}
	public Date getDt_date() {
		return dt_date;
	}
	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}
	public int getTotal_dis_box() {
		return total_dis_box;
	}
	public void setTotal_dis_box(int total_dis_box) {
		this.total_dis_box = total_dis_box;
	}
	public int getTotal_dis_bottles() {
		return total_dis_bottles;
	}
	public void setTotal_dis_bottles(int total_dis_bottles) {
		this.total_dis_bottles = total_dis_bottles;
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
	public int getDispatch_box() {
		return dispatch_box;
	}
	public void setDispatch_box(int dispatch_box) {
		this.dispatch_box = dispatch_box;
	}
	public int getDispatch_bottle() {
		return dispatch_bottle;
	}
	public void setDispatch_bottle(int dispatch_bottle) {
		this.dispatch_bottle = dispatch_bottle;
	}
}
