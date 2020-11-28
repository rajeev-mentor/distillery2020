package com.mentor.Datatable;

import java.util.Date;

public class GatepassForBottolingDatatable {
	
	//// ====Aman ======created date 20/01/2020========from line 9 to 74===========////
	
	private int srNo;
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public Date getGatepass_date() {
		return gatepass_date;
	}
	public void setGatepass_date(Date gatepass_date) {
		this.gatepass_date = gatepass_date;
	}
	public String getVch_from_lic_no() {
		return vch_from_lic_no;
	}
	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}
	public String getVatNoList() {
		return vatNoList;
	}
	public void setVatNoList(String vatNoList) {
		this.vatNoList = vatNoList;
	}
	public double getQuantityFinal_trnfr() {
		return quantityFinal_trnfr;
	}
	public void setQuantityFinal_trnfr(double quantityFinal_trnfr) {
		this.quantityFinal_trnfr = quantityFinal_trnfr;
	}
	public double getQuantityFinalal_trnfr() {
		return quantityFinalal_trnfr;
	}
	public void setQuantityFinalal_trnfr(double quantityFinalal_trnfr) {
		this.quantityFinalal_trnfr = quantityFinalal_trnfr;
	}
	public double getInt_id() {
		return int_id;
	}
	public void setInt_id(double int_id) {
		this.int_id = int_id;
	}
	public Date gatepass_date;
	public String vch_from_lic_no;
	public String vatNoList;
	public double quantityFinal_trnfr;
	public double quantityFinalal_trnfr;
	public double int_id;
	public String pdfName;
	private boolean printFlag;
	public String gtno	;
	
	
	
	public String getGtno() {
		return gtno;
	}
	public void setGtno(String gtno) {
		this.gtno = gtno;
	}
	public boolean isPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	
}
