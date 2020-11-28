package com.mentor.Datatable;

import java.util.Date;

public class CLGatepassAfterReturnDT {
	



	

	public int int_brand_id;
	public String vch_brand;
	public int int_package_ml;
	public int int_bottle_avaliable=0;
	public int int_dispatch;
	public double db_duty=0;
	public double db_add_duty=0;
	public double db_total_duty=0;
	public double calculated_duty;
	private String desc="";
	public double calculated_add_duty;
	public int int_pckg_id;
	private int boxAvailable;
	private int dispatchBoxes;
	private int size;
	
	private String pdfName;
	private boolean printFlag=false;
	private String licnobottling;
	private String cascodeMatching;
	private String batchNo;
	public double cesh_tot;
	public double cesh;
	
	
	
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
public String getCascodeMatching() {
		return cascodeMatching;
	}

	public void setCascodeMatching(String cascodeMatching) {
		this.cascodeMatching = cascodeMatching;
	}

private String gatepassNo;



private boolean draftprintFlag=false;
private String pdfDraftName;
private boolean finalizePrint;
private boolean printDraft;

public String tntflg="X";


public String getBatchNo() {
	return batchNo;
}

public void setBatchNo(String batchNo) {
	this.batchNo = batchNo;
}

public String getTntflg() {
	return tntflg;
}

public void setTntflg(String tntflg) {
	this.tntflg = tntflg;
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
	public String getGatepassNo() {
		return gatepassNo;
	}
	public void setGatepassNo(String gatepassNo) {
		this.gatepassNo = gatepassNo;
	}
	
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
	
	
	
	
	
	public String getLicnobottling() {
		return licnobottling;
	}
	public void setLicnobottling(String licnobottling) {
		this.licnobottling = licnobottling;
	}
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public boolean isPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}
	
	
	
	
	
	public int getBoxAvailable() {
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
		
		//System.out.println("calculated additional duty----------------"+this.calculated_add_duty);
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
		//System.out.println("calculated duty----------------"+this.calculated_duty);
		 
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
		int_dispatch = this.dispatchBoxes*this.size;
		 //System.out.println("bottle dispatched------------------"+int_dispatch);
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
	

	public String getPdfDraftName() {
		return pdfDraftName;
	}
	public void setPdfDraftName(String pdfDraftName) {
		this.pdfDraftName = pdfDraftName;
	}
	public boolean isDraftprintFlag() {
		return draftprintFlag;
	}
	public void setDraftprintFlag(boolean draftprintFlag) {
		this.draftprintFlag = draftprintFlag;
	}
	
	
	// ---------------------cancel gatepass-----------------------

		private int dispatcBotlsDt;
		private int brandIdDt;
		private int pckgIdDt;
		private String licenseNoDt;
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

		public String getTntflgDt() {
			return tntflgDt;
		}

		public void setTntflgDt(String tntflgDt) {
			this.tntflgDt = tntflgDt;
		}

//-*----------------------------------------------------------------
		private String indentNo;
		private String indentDate;
		private String brandName;
		private String packageSize;
		private int dispatchboxIndent;
		private int brandIdIndent;
		private int packageIdIndent;
		private int snoIndent;
		private boolean  selected;


		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}

		public int getBrandIdIndent() {
			return brandIdIndent;
		}

		public void setBrandIdIndent(int brandIdIndent) {
			this.brandIdIndent = brandIdIndent;
		}

		public int getPackageIdIndent() {
			return packageIdIndent;
		}

		public void setPackageIdIndent(int packageIdIndent) {
			this.packageIdIndent = packageIdIndent;
		}

		public String getIndentNo() {
			return indentNo;
		}

		public void setIndentNo(String indentNo) {
			this.indentNo = indentNo;
		}

		public String getIndentDate() {
			return indentDate;
		}

		public void setIndentDate(String indentDate) {
			this.indentDate = indentDate;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}

		public String getPackageSize() {
			return packageSize;
		}

		public void setPackageSize(String packageSize) {
			this.packageSize = packageSize;
		}

		public int getDispatchboxIndent() {
			return dispatchboxIndent;
		}

		public void setDispatchboxIndent(int dispatchboxIndent) {
			this.dispatchboxIndent = dispatchboxIndent;
		}

		public int getSnoIndent() {
			return snoIndent;
		}

		public void setSnoIndent(int snoIndent) {
			this.snoIndent = snoIndent;
		}
		
		
		
		
	private int indentbox;


	public int getIndentbox() {
		return indentbox;
	}

	public void setIndentbox(int indentbox) {
		this.indentbox = indentbox;
	}

		
		
		


}
