package com.mentor.Datatable;
import java.util.Date;

public class FLB11_EOIDt {

	public int int_brand_id;
	public String vch_brand;
	public int int_package_ml;
	public int int_bottle_avaliable = 0;
	public int int_dispatch;
	public int getInt_brand_id() {
		return int_brand_id;
	}
	public void setInt_brand_id(int int_brand_id) {
		this.int_brand_id = int_brand_id;
	}
	public String getVch_brand() {
		return vch_brand;
	}
	public void setVch_brand(String vch_brand) {
		this.vch_brand = vch_brand;
	}
	public int getInt_package_ml() {
		return int_package_ml;
	}
	public void setInt_package_ml(int int_package_ml) {
		this.int_package_ml = int_package_ml;
	}
	public int getInt_bottle_avaliable() {
		return int_bottle_avaliable;
	}
	public void setInt_bottle_avaliable(int int_bottle_avaliable) {
		this.int_bottle_avaliable = int_bottle_avaliable;
	}
	public int getInt_dispatch() {
		this.int_dispatch = this.dispatchBoxes * this.size;
		return int_dispatch;
	}
	public void setInt_dispatch(int int_dispatch) {
		this.int_dispatch = int_dispatch;
	}
	public int getBoxAvailable() {
		return boxAvailable;
	}
	public void setBoxAvailable(int boxAvailable) {
		this.boxAvailable = boxAvailable;
	}
	public int getDispatchBoxes() {
		//this.int_dispatch = this.dispatchBoxes * this.size;
		if(this.size != 0){
		this.dispatchBoxes= (this.int_dispatch)/(this.size);
		}
		return dispatchBoxes;
	}
	public void setDispatchBoxes(int dispatchBoxes) {
		this.dispatchBoxes = dispatchBoxes;
	}
	private int boxAvailable;
	private int dispatchBoxes;
	public int int_pckg_id;
	public int getInt_pckg_id() {
		return int_pckg_id;
	}
	public void setInt_pckg_id(int int_pckg_id) {
		this.int_pckg_id = int_pckg_id;
	}
	public int getSlno() {
		return slno;
	}
	public void setSlno(int slno) {
		this.slno = slno;
	}
	private int slno;
	private int size;
	private int stkbtl;
	
	
	public int getStkbtl() {
		return stkbtl;
	}
	public void setStkbtl(int stkbtl) {
		this.stkbtl = stkbtl;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	private String batchNo;
	public double db_duty = 0;
	public double db_add_duty = 0;
	public double db_total_duty = 0;
	public double calculated_duty;
	private String desc = "";
	public double calculated_add_duty;
	private double expfee;
	private String lic_type;
	public double cesh_tot;
	public double cesh;
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public double getDb_duty() {
		return db_duty;
	}
	public void setDb_duty(double db_duty) {
		this.db_duty = db_duty;
	}
	public double getDb_add_duty() {
		return db_add_duty;
	}
	public void setDb_add_duty(double db_add_duty) {
		this.db_add_duty = db_add_duty;
	}
	public double getDb_total_duty() {
		return db_total_duty;
	}
	public void setDb_total_duty(double db_total_duty) {
		this.db_total_duty = db_total_duty;
	}
	public double getCalculated_duty() {
		this.calculated_duty = this.int_dispatch * this.db_duty;
		return calculated_duty;
	}
	public void setCalculated_duty(double calculated_duty) {
		this.calculated_duty = calculated_duty;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getCalculated_add_duty() {
		this.calculated_add_duty = this.int_dispatch * this.db_add_duty;
		return calculated_add_duty;
	}
	public void setCalculated_add_duty(double calculated_add_duty) {
		this.calculated_add_duty = calculated_add_duty;
	}
	public double getExpfee() {
		return expfee;
	}
	public void setExpfee(double expfee) {
		this.expfee = expfee;
	}
	public String getLic_type() {
		return lic_type;
	}
	public void setLic_type(String lic_type) {
		this.lic_type = lic_type;
	}
	public double getCesh_tot() {
		cesh_tot=this.int_dispatch * this.cesh;
		return cesh_tot;
	}
	public void setCesh_tot(double cesh_tot) {
		this.cesh_tot = cesh_tot;
	}
	public double getCesh() {
		return cesh;
	}
	public void setCesh(double cesh) {
		this.cesh = cesh;
	}
	
	public String tntflg = "N";
	public String getTntflg() {
		return tntflg;
	}
	public void setTntflg(String tntflg) {
		this.tntflg = tntflg;
	}
	
	private String code_generate_through;
	public String getCode_generate_through() {
		return code_generate_through;
	}

	public void setCode_generate_through(String code_generate_through) {
		this.code_generate_through = code_generate_through;
	}
	
	private int qty;
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	private String domain;
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	private int app_id;
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	
	//------------------
	
	

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
	private Date permit_date;
	private int findispatchedbox = 0;
	private int findispatchedbttl = 0;
	private String gtinno = "";
	private boolean finalizePrint;
	
	
	
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
	public String getGtinno() {
		return gtinno;
	}
	public void setGtinno(String gtinno) {
		this.gtinno = gtinno;
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
	private boolean printDraft;
	
	private boolean draftprintFlag;
	private String pdfNameDt1;
	public boolean isDraftprintFlag() {
		return draftprintFlag;
	}
	public void setDraftprintFlag(boolean draftprintFlag) {
		this.draftprintFlag = draftprintFlag;
	}
	public String getPdfNameDt1() {
		return pdfNameDt1;
	}
	public void setPdfNameDt1(String pdfNameDt1) {
		this.pdfNameDt1 = pdfNameDt1;
	}
	
	private String pdfNameDt;
	public String getPdfNameDt() {
		return pdfNameDt;
	}
	public void setPdfNameDt(String pdfNameDt) {
		this.pdfNameDt = pdfNameDt;
	}
	
	public String PdfDraft1;

	private boolean draftprintFlag1;
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
	
	private boolean myFlagDt;
	public boolean isMyFlagDt() {
		return myFlagDt;
	}
	public void setMyFlagDt(boolean myFlagDt) {
		this.myFlagDt = myFlagDt;
	}
	private boolean myFlagDt1;
	public boolean isMyFlagDt1() {
		return myFlagDt1;
	}
	public void setMyFlagDt1(boolean myFlagDt1) {
		this.myFlagDt1 = myFlagDt1;
	}
	
	
	private String gatepassNo;
	private String gatepass;
	private String casecode;
	public String getGatepassNo() {
		return gatepassNo;
	}
	public void setGatepassNo(String gatepassNo) {
		this.gatepassNo = gatepassNo;
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
	
	public String cascodeMatching;

	public String getCascodeMatching() {
		return cascodeMatching;
	}

	public void setCascodeMatching(String cascodeMatching) {
		this.cascodeMatching = cascodeMatching;
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
	
	public Date getPermit_date() {
		return permit_date;
	}
	public void setPermit_date(Date permit_date) {
		this.permit_date = permit_date;
	}
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
	
	
	
	
	
}
