package com.mentor.Datatable;

import java.util.Date;

public class GatePassToWholesaleDataTable {

	public int int_brand_id;
	public String vch_brand;
	public int int_package_ml;
	public int int_bottle_avaliable = 0;
	public int int_dispatch;
	public double db_duty = 0;
	public double db_add_duty = 0;
	public double db_total_duty = 0;
	public double calculated_duty;
	private String desc = "";
	public double calculated_add_duty;
	public int int_pckg_id;
	private int boxAvailable;
	private int dispatchBoxes;
	private int size;
	private String gtinno = "";
	private String barcode = "";
	private String pdfDraft;
	private boolean printFlag = false;

	private boolean finalizePrint;
	private boolean printDraft;
	private boolean draftprintFlag;

	private boolean draftprintFlag1;
	public String PdfDraft1;
	public String tntflg = "X";
	
	private int qty;
	private double expfee;
	private String lic_type;
	
	public String getLic_type() {
		return lic_type;
	}

	public void setLic_type(String lic_type) {
		this.lic_type = lic_type;
	}

	public double getExpfee() {
		
		
		
		if(this.lic_type.equalsIgnoreCase("FL")){
		
			expfee=(( Double.valueOf(this.getInt_dispatch()) * Double.valueOf(this.getQty()))/1000) * 1.5;
		
		}else if(this.lic_type.equalsIgnoreCase("LAB")){
			expfee=(( Double.valueOf(this.getInt_dispatch()) * Double.valueOf(this.getQty()))/1000) * 3;
		}
		//System.out.println("-Faizal--- "+this.getQty()+" --- "+ this.getInt_dispatch()+" --- "+expfee+" ---- "+( Double.valueOf(this.getInt_dispatch()) * Double.valueOf(this.getQty()))/1000);
		
		return expfee;
	}

	public void setExpfee(double expfee) {
		this.expfee = expfee;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String cascodeMatching;

	public String getCascodeMatching() {
		return cascodeMatching;
	}

	public void setCascodeMatching(String cascodeMatching) {
		this.cascodeMatching = cascodeMatching;
	}

	public String getTntflg() {
		return tntflg;
	}

	public void setTntflg(String tntflg) {
		this.tntflg = tntflg;
	}

	public String getPdfDraft1() {
		return PdfDraft1;
	}

	public void setPdfDraft1(String pdfDraft1) {
		PdfDraft1 = pdfDraft1;
	}

	public boolean isDraftprintFlag1() {
		return draftprintFlag1;
	}

	public void setDraftprintFlag1(boolean draftprintFlag1) {
		this.draftprintFlag1 = draftprintFlag1;
	}

	public boolean isFinalizePrint() {
		return finalizePrint;
	}

	public void setFinalizePrint(boolean finalizePrint) {
		this.finalizePrint = finalizePrint;
	}

	public boolean isPrintDraft() {
		return printDraft;
	}

	public void setPrintDraft(boolean printDraft) {
		this.printDraft = printDraft;
	}

	public boolean isDraftprintFlag() {
		return draftprintFlag;
	}

	public void setDraftprintFlag(boolean draftprintFlag) {
		this.draftprintFlag = draftprintFlag;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public String getPdfDraft() {
		return pdfDraft;
	}

	public void setPdfDraft(String pdfDraft) {
		this.pdfDraft = pdfDraft;
	}

	private int findispatchedbox = 0;
	private int findispatchedbttl = 0;

	private String pdfNameDt;
	private boolean myFlagDt;
	private String pdfNameDt1;
	private boolean myFlagDt1;
	private boolean PrintDraftFlagDt;
	private String gatepassNo;
	private String gatepass;
	private String casecode;

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

	public String getGatepassNo() {
		return gatepassNo;
	}

	public void setGatepassNo(String gatepassNo) {
		this.gatepassNo = gatepassNo;
	}

	public boolean isPrintDraftFlagDt() {
		return PrintDraftFlagDt;
	}

	public void setPrintDraftFlagDt(boolean printDraftFlagDt) {
		PrintDraftFlagDt = printDraftFlagDt;
	}

	private String batchNo;
	private int slno;

	public int getSlno() {
		return slno;
	}

	public void setSlno(int slno) {
		this.slno = slno;
	}

	public String getPdfNameDt1() {
		return pdfNameDt1;
	}

	public void setPdfNameDt1(String pdfNameDt1) {
		this.pdfNameDt1 = pdfNameDt1;
	}

	public boolean isMyFlagDt1() {
		return myFlagDt1;
	}

	public void setMyFlagDt1(boolean myFlagDt1) {
		this.myFlagDt1 = myFlagDt1;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getPdfNameDt() {
		return pdfNameDt;
	}

	public void setPdfNameDt(String pdfNameDt) {
		this.pdfNameDt = pdfNameDt;
	}

	public boolean isMyFlagDt() {
		return myFlagDt;
	}

	public void setMyFlagDt(boolean myFlagDt) {
		this.myFlagDt = myFlagDt;
	}

	public int getFindispatchedbox() {
		return findispatchedbox;
	}

	public void setFindispatchedbox(int findispatchedbox) {
		this.findispatchedbox = findispatchedbox;
	}

	public int getFindispatchedbttl() {
		return findispatchedbttl;
	}

	public void setFindispatchedbttl(int findispatchedbttl) {
		this.findispatchedbttl = findispatchedbttl;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getGtinno() {
		return gtinno;
	}

	public void setGtinno(String gtinno) {
		this.gtinno = gtinno;
	}

	public int getBoxAvailable() {
		this.boxAvailable = Math.round(this.int_bottle_avaliable / this.size);
		return boxAvailable;
	}

	public void setBoxAvailable(int boxAvailable) {
		this.boxAvailable = boxAvailable;
	}

	public int getDispatchBoxes() {
		return dispatchBoxes;
	}

	public void setDispatchBoxes(int dispatchBoxes) {
		this.dispatchBoxes = dispatchBoxes;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getInt_pckg_id() {
		return int_pckg_id;
	}

	public void setInt_pckg_id(int int_pckg_id) {
		this.int_pckg_id = int_pckg_id;
	}

	public double getCalculated_add_duty() {
		this.calculated_add_duty = this.int_dispatch * this.db_add_duty;

		/*System.out.println("calculated additional duty----------------"
				+ this.calculated_add_duty);*/
		return calculated_add_duty;
	}

	public void setCalculated_add_duty(double calculated_add_duty) {
		this.calculated_add_duty = calculated_add_duty;
	}

	public double getDb_add_duty() {
		return db_add_duty;
	}

	public void setDb_add_duty(double db_add_duty) {
		this.db_add_duty = db_add_duty;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getCalculated_duty() {
		this.calculated_duty = this.int_dispatch * this.db_duty;
		/*System.out.println("calculated duty----------------"
				+ this.calculated_duty);*/
		// calculated_duty= Math.round(((db_duty*int_dispatch))*100.0)/100.0 ;
		return calculated_duty;
	}

	public void setCalculated_duty(double calculated_duty) {
		this.calculated_duty = calculated_duty;
	}

	public String getVch_brand() {
		return vch_brand;
	}

	public void setVch_brand(String vch_brand) {
		this.vch_brand = vch_brand;
	}

	public int getInt_bottle_avaliable() {
		return int_bottle_avaliable;
	}

	public void setInt_bottle_avaliable(int int_bottle_avaliable) {
		this.int_bottle_avaliable = int_bottle_avaliable;
	}

	public int getInt_dispatch() {
		this.int_dispatch = this.dispatchBoxes * this.size;
		/*System.out.println("bottle dispatched------------------"
				+ this.int_dispatch);*/
		return int_dispatch;
	}

	public void setInt_dispatch(int int_dispatch) {
		this.int_dispatch = int_dispatch;
	}

	public double getDb_duty() {
		return db_duty;
	}

	public void setDb_duty(double db_duty) {
		this.db_duty = db_duty;
	}

	public double getDb_total_duty() {
		return db_total_duty;
	}

	public void setDb_total_duty(double db_total_duty) {
		this.db_total_duty = db_total_duty;
	}

	public int getInt_brand_id() {
		return int_brand_id;
	}

	public void setInt_brand_id(int int_brand_id) {
		this.int_brand_id = int_brand_id;
	}

	public int getInt_package_ml() {
		return int_package_ml;
	}

	public void setInt_package_ml(int int_package_ml) {
		this.int_package_ml = int_package_ml;
	}

	private int sno;
	private int int_dist_id;
	private String vch_distillary_name;
	private String vch_distillary_address;
	private String vch_gatepass_no;
	private Date dt_date;
	private String vch_from;
	private String vch_to;
	private String vch_from_lic_no;
	private String vch_to_lic_no;
	private String vch_bond;
	private Date curr_date;
	private int int_max_id;
	private double db_total_duty_2;
	private double db_total_additional_duty;

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getInt_dist_id() {
		return int_dist_id;
	}

	public void setInt_dist_id(int int_dist_id) {
		this.int_dist_id = int_dist_id;
	}

	public String getVch_distillary_name() {
		return vch_distillary_name;
	}

	public void setVch_distillary_name(String vch_distillary_name) {
		this.vch_distillary_name = vch_distillary_name;
	}

	public String getVch_distillary_address() {
		return vch_distillary_address;
	}

	public void setVch_distillary_address(String vch_distillary_address) {
		this.vch_distillary_address = vch_distillary_address;
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

	public String getVch_from() {
		return vch_from;
	}

	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}

	public String getVch_to() {
		return vch_to;
	}

	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}

	public String getVch_from_lic_no() {
		return vch_from_lic_no;
	}

	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}

	public String getVch_to_lic_no() {
		return vch_to_lic_no;
	}

	public void setVch_to_lic_no(String vch_to_lic_no) {
		this.vch_to_lic_no = vch_to_lic_no;
	}

	public String getVch_bond() {
		return vch_bond;
	}

	public void setVch_bond(String vch_bond) {
		this.vch_bond = vch_bond;
	}

	public Date getCurr_date() {
		return curr_date;
	}

	public void setCurr_date(Date curr_date) {
		this.curr_date = curr_date;
	}

	public int getInt_max_id() {
		return int_max_id;
	}

	public void setInt_max_id(int int_max_id) {
		this.int_max_id = int_max_id;
	}

	public double getDb_total_duty_2() {
		return db_total_duty_2;
	}

	public void setDb_total_duty_2(double db_total_duty_2) {
		this.db_total_duty_2 = db_total_duty_2;
	}

	public double getDb_total_additional_duty() {
		return db_total_additional_duty;
	}

	public void setDb_total_additional_duty(double db_total_additional_duty) {
		this.db_total_additional_duty = db_total_additional_duty;
	}

	// --------------------- cancel gatepass-----------------------------

	private String vch_lic_no1;
	private int dispatched_bottle;
	private int pckg_id;
	private String vch_lic_type;
	private int brand_id;

	public String getVch_lic_no1() {
		return vch_lic_no1;
	}

	public void setVch_lic_no1(String vch_lic_no1) {
		this.vch_lic_no1 = vch_lic_no1;
	}

	public int getDispatched_bottle() {
		return dispatched_bottle;
	}

	public void setDispatched_bottle(int dispatched_bottle) {
		this.dispatched_bottle = dispatched_bottle;
	}

	public int getPckg_id() {
		return pckg_id;
	}

	public void setPckg_id(int pckg_id) {
		this.pckg_id = pckg_id;
	}

	public String getVch_lic_type() {
		return vch_lic_type;
	}

	public void setVch_lic_type(String vch_lic_type) {
		this.vch_lic_type = vch_lic_type;
	}

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	// ---------------------cancel gatepass-----------------------

	private int dispatcBotlsDt;
	private int brandIdDt;
	private int pckgIdDt;
	private String licenseNoDt;
	private String licenseTypeDt;
	private int avlBoxDt;
	private int avlBottleDt;
	private int dispatchBoxDt;
	private double dutyDt;
	private double adddutyDt;
	public String tntflgDt;

	public int getDispatcBotlsDt() {
		return dispatcBotlsDt;
	}

	public void setDispatcBotlsDt(int dispatcBotlsDt) {
		this.dispatcBotlsDt = dispatcBotlsDt;
	}

	public int getBrandIdDt() {
		return brandIdDt;
	}

	public void setBrandIdDt(int brandIdDt) {
		this.brandIdDt = brandIdDt;
	}

	public int getPckgIdDt() {
		return pckgIdDt;
	}

	public void setPckgIdDt(int pckgIdDt) {
		this.pckgIdDt = pckgIdDt;
	}

	public String getLicenseNoDt() {
		return licenseNoDt;
	}

	public void setLicenseNoDt(String licenseNoDt) {
		this.licenseNoDt = licenseNoDt;
	}

	public String getLicenseTypeDt() {
		return licenseTypeDt;
	}

	public void setLicenseTypeDt(String licenseTypeDt) {
		this.licenseTypeDt = licenseTypeDt;
	}

	public int getAvlBoxDt() {
		return avlBoxDt;
	}

	public void setAvlBoxDt(int avlBoxDt) {
		this.avlBoxDt = avlBoxDt;
	}

	public int getAvlBottleDt() {
		return avlBottleDt;
	}

	public void setAvlBottleDt(int avlBottleDt) {
		this.avlBottleDt = avlBottleDt;
	}

	public int getDispatchBoxDt() {
		return dispatchBoxDt;
	}

	public void setDispatchBoxDt(int dispatchBoxDt) {
		this.dispatchBoxDt = dispatchBoxDt;
	}

	public double getDutyDt() {
		return dutyDt;
	}

	public void setDutyDt(double dutyDt) {
		this.dutyDt = dutyDt;
	}

	public double getAdddutyDt() {
		return adddutyDt;
	}

	public void setAdddutyDt(double adddutyDt) {
		this.adddutyDt = adddutyDt;
	}

	public String getTntflgDt() {
		return tntflgDt;
	}

	public void setTntflgDt(String tntflgDt) {
		this.tntflgDt = tntflgDt;
	}

	private String code_generate_through;
	private String domain;

	public String getCode_generate_through() {
		return code_generate_through;
	}

	public void setCode_generate_through(String code_generate_through) {
		this.code_generate_through = code_generate_through;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	private String brand_name;
	private int casecoseBrandSize;
	private Date date_plan;
	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public int getCasecoseBrandSize() {
		return casecoseBrandSize;
	}

	public void setCasecoseBrandSize(int casecoseBrandSize) {
		this.casecoseBrandSize = casecoseBrandSize;
	}

	public Date getDate_plan() {
		return date_plan;
	}

	public void setDate_plan(Date date_plan) {
		this.date_plan = date_plan;
	}

	
	
	
	
}
