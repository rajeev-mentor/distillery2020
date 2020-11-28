package com.mentor.Datatable;

import java.util.Date;

public class ImportOrderEntryEOIDt {
	
	private String  brandNm;
	private int  brandId;
	private String  packageNm;
	private int  packageId;
	private int  sr_n;
	private String  etin;
	 private double  quantity;
	private double size ;
	private int no_of_box;	    
	private int show_size ;
	private int show_no_of_box ;
	

		public void setSize(double size) {
		this.size = size;
	}
		public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getSize() {
		return size;
	}
		public int getShow_size() {
		return show_size;
	}
	public void setShow_size(int show_size) {
		this.show_size = show_size;
	}
	public int getShow_no_of_box() {
		return show_no_of_box;
	}
	public void setShow_no_of_box(int show_no_of_box) {
		this.show_no_of_box = show_no_of_box;
	}
		public int getNo_of_box() {
			if(this.size>0){
				this.no_of_box = (int)Math.ceil(this.quantity / this.size) ;
			}
			
			return no_of_box;
		}
		public void setNo_of_box(int no_of_box) {
			this.no_of_box = no_of_box;
		}

	public String getBrandNm() {
		return brandNm;
	}
	public void setBrandNm(String brandNm) {
		this.brandNm = brandNm;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getPackageNm() {
		return packageNm;
	}
	public void setPackageNm(String packageNm) {
		this.packageNm = packageNm;
	}
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public int getSr_n() {
		return sr_n;
	}
	public void setSr_n(int sr_n) {
		this.sr_n = sr_n;
	}
	public String getEtin() {
		return etin;
	}
	public void setEtin(String etin) {
		this.etin = etin;
	}
	private boolean select;
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	
	
	//--------------------Submit Data
	private int  sr_n1;
	private String  countryNm;
	private String  unitNM;
	private String  purchaseNo;
	private Date  crDate;
	private String  orderCopy;
	private Date  validDate;
	public int getSr_n1() {
		return sr_n1;
	}
	public void setSr_n1(int sr_n1) {
		this.sr_n1 = sr_n1;
	}
	public String getCountryNm() {
		return countryNm;
	}
	public void setCountryNm(String countryNm) {
		this.countryNm = countryNm;
	}
	public String getUnitNM() {
		return unitNM;
	}
	public void setUnitNM(String unitNM) {
		this.unitNM = unitNM;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public Date getCrDate() {
		return crDate;
	}
	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}
	public String getOrderCopy() {
		return orderCopy;
	}
	public void setOrderCopy(String orderCopy) {
		this.orderCopy = orderCopy;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	public int getNoOfbottles() {
		return noOfbottles;
	}
	public void setNoOfbottles(int noOfbottles) {
		this.noOfbottles = noOfbottles;
	}
	private int  noOfbottles;


}
