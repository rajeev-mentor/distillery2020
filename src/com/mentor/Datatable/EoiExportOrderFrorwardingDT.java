package com.mentor.Datatable;

import java.util.Date;

public class EoiExportOrderFrorwardingDT {
	
	//===========brand details dt =====================================================================		
	
	private int srno_brand ;
	private String brand_name ;
	private String package_name ;
	private String etin ;
	private long balance_qty ;
	private long requested_qty ;
	private String status;
	private String distillery_name;
	private String distillery_id;
	private boolean esign_flag;
	private String permitNmbr_dt;
	private String mainServiceId;private String maincntrlId;private String mainunitId;
	private String requestId, puc_no,puc_pdf;
	 
	 
	public String getPuc_no() {
		return puc_no;
	}

	public void setPuc_no(String puc_no) {
		this.puc_no = puc_no;
	}

	public String getPuc_pdf() {
		return puc_pdf;
	}

	public void setPuc_pdf(String puc_pdf) {
		this.puc_pdf = puc_pdf;
	}

	public String getMainServiceId() {
		return mainServiceId;
	}

	public void setMainServiceId(String mainServiceId) {
		this.mainServiceId = mainServiceId;
	}

	public String getMaincntrlId() {
		return maincntrlId;
	}

	public void setMaincntrlId(String maincntrlId) {
		this.maincntrlId = maincntrlId;
	}

	public String getMainunitId() {
		return mainunitId;
	}

	public void setMainunitId(String mainunitId) {
		this.mainunitId = mainunitId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
//===================================== brand details dt = getter and setter ==============================	
	
	public String getDistillery_name() {
		return distillery_name;
	}
	public String getPermitNmbr_dt() {
		return permitNmbr_dt;
	}
	public void setPermitNmbr_dt(String permitNmbr_dt) {
		this.permitNmbr_dt = permitNmbr_dt;
	}
	public boolean isEsign_flag() {
		return esign_flag;
	}
	public void setEsign_flag(boolean esign_flag) {
		this.esign_flag = esign_flag;
	}
	public String getDistillery_id() {
		return distillery_id;
	}
	public void setDistillery_id(String distillery_id) {
		this.distillery_id = distillery_id;
	}
	public void setDistillery_name(String distillery_name) {
		this.distillery_name = distillery_name;
	}
	public int getSrno_brand() {
		return srno_brand;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	private String reciept_at_icd ;

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
	
	
	//===================================== export order dt = getter and setter ==============================	
	
	public String getReciept_at_icd() {
		return reciept_at_icd;
	}

	public void setReciept_at_icd(String reciept_at_icd) {
		this.reciept_at_icd = reciept_at_icd;
	}





	private String permit_no;


	public String getPermit_no() {
		return permit_no;
	}

	public void setPermit_no(String permit_no) {
		this.permit_no = permit_no;
	}
	
	//ankur 29-09-2020
	
	public boolean check_pdf ;


	public boolean isCheck_pdf() {
		return check_pdf;
	}
	public void setCheck_pdf(boolean check_pdf) {
		this.check_pdf = check_pdf;
	}
		
	
	
 
private String pdfname;
private boolean printFlag;


 
public String getPdfname() {
	return pdfname;
}
public void setPdfname(String pdfname) {
	this.pdfname = pdfname;
}
public boolean isPrintFlag() {
	return printFlag;
}
public void setPrintFlag(boolean printFlag) {
	this.printFlag = printFlag;
}
	

	
	
}
