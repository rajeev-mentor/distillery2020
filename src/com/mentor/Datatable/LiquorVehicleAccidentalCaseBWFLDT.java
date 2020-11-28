package com.mentor.Datatable;

import java.util.Date;

public class LiquorVehicleAccidentalCaseBWFLDT {
	private int srno,sr1,sr2, damage_box,damage_bottle, return_box, return_bottle, prcced_dispatch_box, prcced_dispatch_bottle,int_brand_id, int_pack_id;
	private int req_id, distillery_id;
	private String gatepass_type, vch_from, vch_finalize, vch_lic_no;
	private String gatepass_no;
	private Date gatepass_date;
	private String accident_address;
	private String accident_district_name;
	private Date accident_date;
	private Date req_dt;
	
	private String brand_name,gatepass,casecode,cascodeMatching;
	private String size;
	private int box_size;
	private int dispatch_box;
	private int dispatch_bottle;
	private String license_type,license_no,distillery_name,pdfname, batch_no, inpection_pdf;
	private boolean finalizePrint,printFlag, verify_flg;

	
	public String getVch_lic_no() {
		return vch_lic_no;
	}
	public void setVch_lic_no(String vch_lic_no) {
		this.vch_lic_no = vch_lic_no;
	}
	public String getVch_finalize() {
		return vch_finalize;
	}
	public void setVch_finalize(String vch_finalize) {
		this.vch_finalize = vch_finalize;
	}
	public String getVch_from() {
		return vch_from;
	}
	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}
	public String getGatepass() {
		return gatepass;
	}
	public void setGatepass(String gatepass) {
		this.gatepass = gatepass;
	}
	public String getCasecode() {
		return casecode;
	}
	public void setCasecode(String casecode) {
		this.casecode = casecode;
	}
	public String getCascodeMatching() {
		return cascodeMatching;
	}
	public void setCascodeMatching(String cascodeMatching) {
		this.cascodeMatching = cascodeMatching;
	}
	public String getInpection_pdf() {
		return inpection_pdf;
	}
	public void setInpection_pdf(String inpection_pdf) {
		this.inpection_pdf = inpection_pdf;
	}
	public boolean isVerify_flg() {
		return verify_flg;
	}
	public void setVerify_flg(boolean verify_flg) {
		this.verify_flg = verify_flg;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public boolean isPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}
	public int getDistillery_id() {
		return distillery_id;
	}
	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}
	public int getInt_brand_id() {
		return int_brand_id;
	}
	public void setInt_brand_id(int int_brand_id) {
		this.int_brand_id = int_brand_id;
	}
	public int getInt_pack_id() {
		return int_pack_id;
	}
	public void setInt_pack_id(int int_pack_id) {
		this.int_pack_id = int_pack_id;
	}
	public int getDamage_box() {
		return damage_box;
	}
	public void setDamage_box(int damage_box) {
		this.damage_box = damage_box;
	}
	public int getDamage_bottle() {
		return damage_bottle;
	}
	public void setDamage_bottle(int damage_bottle) {
		this.damage_bottle = damage_bottle;
	}
	public int getReturn_box() {
		return return_box;
	}
	public void setReturn_box(int return_box) {
		this.return_box = return_box;
	}
	public int getReturn_bottle() {
		return return_bottle;
	}
	public void setReturn_bottle(int return_bottle) {
		this.return_bottle = return_bottle;
	}
	public int getPrcced_dispatch_box() {
		return prcced_dispatch_box;
	}
	public void setPrcced_dispatch_box(int prcced_dispatch_box) {
		this.prcced_dispatch_box = prcced_dispatch_box;
	}
	public int getPrcced_dispatch_bottle() {
		return prcced_dispatch_bottle;
	}
	public void setPrcced_dispatch_bottle(int prcced_dispatch_bottle) {
		this.prcced_dispatch_bottle = prcced_dispatch_bottle;
	}
	public String getPdfname() {
		return pdfname;
	}
	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}
	public boolean isFinalizePrint() {
		return finalizePrint;
	}
	public void setFinalizePrint(boolean finalizePrint) {
		this.finalizePrint = finalizePrint;
	}
	public int getSr1() {
		return sr1;
	}
	public void setSr1(int sr1) {
		this.sr1 = sr1;
	}
	public int getSr2() {
		return sr2;
	}
	public void setSr2(int sr2) {
		this.sr2 = sr2;
	}
	public String getDistillery_name() {
		return distillery_name;
	}
	public void setDistillery_name(String distillery_name) {
		this.distillery_name = distillery_name;
	}
	public String getLicense_type() {
		return license_type;
	}
	public void setLicense_type(String license_type) {
		this.license_type = license_type;
	}
	public String getLicense_no() {
		return license_no;
	}
	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getBox_size() {
		return box_size;
	}
	public void setBox_size(int box_size) {
		this.box_size = box_size;
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
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public int getReq_id() {
		return req_id;
	}
	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}
	public String getGatepass_type() {
		return gatepass_type;
	}
	public void setGatepass_type(String gatepass_type) {
		this.gatepass_type = gatepass_type;
	}
	public String getGatepass_no() {
		return gatepass_no;
	}
	public void setGatepass_no(String gatepass_no) {
		this.gatepass_no = gatepass_no;
	}
	public Date getGatepass_date() {
		return gatepass_date;
	}
	public void setGatepass_date(Date gatepass_date) {
		this.gatepass_date = gatepass_date;
	}
	public String getAccident_address() {
		return accident_address;
	}
	public void setAccident_address(String accident_address) {
		this.accident_address = accident_address;
	}
	public String getAccident_district_name() {
		return accident_district_name;
	}
	public void setAccident_district_name(String accident_district_name) {
		this.accident_district_name = accident_district_name;
	}
	public Date getAccident_date() {
		return accident_date;
	}
	public void setAccident_date(Date accident_date) {
		this.accident_date = accident_date;
	}
	public Date getReq_dt() {
		return req_dt;
	}
	public void setReq_dt(Date req_dt) {
		this.req_dt = req_dt;
	}
	
	
}
