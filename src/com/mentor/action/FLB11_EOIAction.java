package com.mentor.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.Datatable.FLB11_EOIDt; 
import com.mentor.impl.FLB11_EOIImpl;
import com.mentor.utility.Utility;


public class FLB11_EOIAction {
	
	FLB11_EOIImpl impl = new FLB11_EOIImpl();
	
	
	private int dissteleryId;
	public int getDissteleryId() {
		return dissteleryId;
	}
	public void setDissteleryId(int dissteleryId) {
		this.dissteleryId = dissteleryId;
	}
	private String session;
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getHidden() {
		try {
			impl.getDetails(this);
			impl.getSeasonDetails(this);

		} catch (Exception e) {
		}
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public Date getDt_date() {
		return dt_date;
	}
	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}
	public Date getValidtilldt_date() {
		return validtilldt_date;
	}
	public void setValidtilldt_date(Date validtilldt_date) {
		this.validtilldt_date = validtilldt_date;
	}
	public String getDissteleryName() {
		return dissteleryName;
	}
	public void setDissteleryName(String dissteleryName) {
		this.dissteleryName = dissteleryName;
	}
	public String getDisslryAdd() {
		return disslryAdd;
	}
	public void setDisslryAdd(String disslryAdd) {
		this.disslryAdd = disslryAdd;
	}
	public String getVch_from() {
		return vch_from;
	}
	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}
	public String getVch_from_lic_no() {
		return vch_from_lic_no;
	}
	public void setVch_from_lic_no(String vch_from_lic_no) {
		this.vch_from_lic_no = vch_from_lic_no;
	}
	public boolean isLic_disable_flag2() {
		return lic_disable_flag2;
	}
	public void setLic_disable_flag2(boolean lic_disable_flag2) {
		this.lic_disable_flag2 = lic_disable_flag2;
	}
	public ArrayList getFromliclist() {
		return fromliclist;
	}
	public void setFromliclist(ArrayList fromliclist) {
		this.fromliclist = fromliclist;
	}
	public String getVch_to() {
		return vch_to;
	}
	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}
	public String getVch_to_lic_no() {
		return vch_to_lic_no;
	}
	public void setVch_to_lic_no(String vch_to_lic_no) {
		this.vch_to_lic_no = vch_to_lic_no;
	}
	
	public double getBondValue() {
		return bondValue;
	}
	public void setBondValue(double bondValue) {
		this.bondValue = bondValue;
	}
	public int getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(int unitNo) {
		this.unitNo = unitNo;
	}
	public int getPurchaseOrderNo() {
		return purchaseOrderNo;
	}
	public void setPurchaseOrderNo(int purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getUnit_nm() {
		return unit_nm;
	}
	public void setUnit_nm(String unit_nm) {
		this.unit_nm = unit_nm;
	}
	public String getRouteDtl() {
		return routeDtl;
	}
	public void setRouteDtl(String routeDtl) {
		this.routeDtl = routeDtl;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getVehicleDrvrName() {
		return vehicleDrvrName;
	}
	public void setVehicleDrvrName(String vehicleDrvrName) {
		this.vehicleDrvrName = vehicleDrvrName;
	}
	public String getVehicleAgencyNmAdrs() {
		return vehicleAgencyNmAdrs;
	}
	public void setVehicleAgencyNmAdrs(String vehicleAgencyNmAdrs) {
		this.vehicleAgencyNmAdrs = vehicleAgencyNmAdrs;
	}
	public double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public double getTierWeight() {
		return tierWeight;
	}
	public void setTierWeight(double tierWeight) {
		this.tierWeight = tierWeight;
	}
	public double getNetWeight() {
		if (this.grossWeight > 0.0 && this.tierWeight > 0.0) {
			this.netWeight = this.grossWeight - this.tierWeight;
		} else {
			this.netWeight = 0.0;
		}
		return netWeight;
	}
	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}
	public String getVch_type() {
		return vch_type;
	}
	public void setVch_type(String vch_type) {
		this.vch_type = vch_type;
	}
	public int getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(int containerNo) {
		this.containerNo = containerNo;
	}
	public String getContainerSealNo() {
		return containerSealNo;
	}
	public void setContainerSealNo(String containerSealNo) {
		this.containerSealNo = containerSealNo;
	}

	private String hidden;
	private Date dt_date=new Date();
	private Date  validtilldt_date;
	private String dissteleryName;
	private String disslryAdd;
	private String vch_from;
	private String  vch_from_lic_no;
	private boolean lic_disable_flag2;
	ArrayList fromliclist = new ArrayList();
	private String vch_to="EOI";
	private String vch_to_lic_no;
	 
	ArrayList toliclist = new ArrayList();

	private double bondValue;
	private int unitNo;
	private int purchaseOrderNo;
	private String country;
	private String unit_nm;
	private String routeDtl;
	private String vehicleNo;
	private String vehicleDrvrName;
	private String vehicleAgencyNmAdrs;
	private double grossWeight = 0;
	private double tierWeight = 0;
	private double netWeight = 0;
	public String vch_type;
	public int containerNo;
	private String containerSealNo;
	private String icd ;
	
	
	
	public String getIcd() {
		return icd;
	}
	public void setIcd(String icd) {
		this.icd = icd;
	}
	private boolean lic_disable_flag;
	public boolean isLic_disable_flag() {
		return lic_disable_flag;
	}

	public void setLic_disable_flag(boolean lic_disable_flag) {
		this.lic_disable_flag = lic_disable_flag;
	}
	public String fromListMethod(ValueChangeEvent vce) {
		try {
			System.out.println("========="+this.vch_from);
			Object obj = vce.getNewValue();
			String s = (String) obj;
			this.setVch_from(s);
			this.lic_disable_flag2 = false;
          if(this.vch_from !=null){
		if (s.equalsIgnoreCase("FL3A")) {
			System.out.println("========="+this.vch_from);
				this.vch_from = "FL3A";
				this.fromliclist = impl.fromliclistImpl(this);
			//	this.displaylist = impl.displaylistImpl(this);
				System.out.println("=====FL3A===="+this.vch_from_lic_no);
			}

			else if (s.equalsIgnoreCase("FL3")) {
				this.vch_from = "FL3";
				this.fromliclist = impl.fromliclistImpl(this);

		     /// this.displaylist = impl.displaylistImpl(this);
				System.out.println("====FL3====="+this.vch_from_lic_no);

			}
          }
		} catch (Exception e) {

		}

		return "";
	}
	
	private Date permit_date;
	
	public Date getPermit_date() {
		return permit_date;
	}
	public void setPermit_date(Date permit_date) {
		this.permit_date = permit_date;
	}
	public String listMethod(ValueChangeEvent vce) {
	try {
		String val = (String) vce.getNewValue();
		//int vat_id=Integer.parseInt(String.valueOf(val));
		this.setVch_to_lic_no(val);
		//System.out.println("val---------"+val);
		this.displaylist=impl.displaylistImpl(this,val);
		impl.displayImpl(this, val);
		 dt_date=new Date();
		} catch (Exception e) {

		}

		return "";
		
	}
	

	public String toListMethod(ValueChangeEvent vce) {
		try { 
			
			Object obj = vce.getNewValue();
			String s = (String) obj;

			this.lic_disable_flag = false;
			this.setVch_to(s); 
			if (s.equalsIgnoreCase("EOI")) {
				this.vch_to = "EOI";
				 
				
			}
	} catch (Exception e) {

		}

		return "";
	}
	
	public String typeMethod(ValueChangeEvent vce) {
		try {
		
			
		} catch (Exception e) {

		}

		return "";
	}


	public ArrayList getToliclist() {
		 toliclist = impl.toliclistImpl1a(this);
		return toliclist;
	}
	public void setToliclist(ArrayList toliclist) {
		this.toliclist = toliclist;
	}
	//-------------------------------------displaylist---------------------
	public ArrayList displaylist = new ArrayList();
	
	
	
	public ArrayList getDisplaylist() {
		return displaylist;
	}
	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}
	public void save(){
		try{
		if (isValidateInput1()) {
			if(radio1.equalsIgnoreCase("PC") && containerno!=null) {
		impl.saveMethodImpl(this);
		listFlagForPrint=true;
			}else if(radio1.equalsIgnoreCase("FC") && sealno!=null && sealdate!=null) {
		impl.saveMethodImpl(this);
		listFlagForPrint=true;
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sealing Details Missing ! ")); 
			}
		
		}
		}
		catch(Exception e){
			
		}
		}
	
	public int countryId;
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public boolean addflag = true;
	public boolean flag = true;
	public boolean isAddflag() {
		return addflag;
	}
	public void setAddflag(boolean addflag) {
		this.addflag = addflag;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void calculateTotalDuty(ActionEvent ae) {
		// System.out.println("inside2");
		if (isFlag()) {
			this.setSum(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
			FLB11_EOIDt dt = (FLB11_EOIDt) this.displaylist
						.get(i);

				this.setSum(this.getSum() + dt.getCalculated_duty());

			}
		}
		this.flag = false;

	}
	// ==================FOR ADDITIONAL TOTAL DUTY==========================
	public void calculateTotalAddDuty(ActionEvent ae) {
		// System.out.println("inside2");
		if (isAddflag()) {
			this.setSum_add(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				FLB11_EOIDt dt = (FLB11_EOIDt) this.displaylist
						.get(i);

				this.setSum_add(this.getSum_add() + dt.getCalculated_add_duty());

			}
		}
		this.addflag = false;

	}
	
	public void calculateTotalexpfee(ActionEvent ae) {
		// System.out.println("inside2");
		if (isFlag()) {
			this.setSum_exp(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				FLB11_EOIDt dt = (FLB11_EOIDt) this.displaylist
						.get(i);

				this.setSum_exp(this.getSum_exp() + dt.getExpfee());

			}
		}
		this.flag = false;

	}
	
	public double db_total_value = 0;
	public double db_total_add_value = 0;
	public double sum_add = 0;
	public double sum = 0;
	public double sum_exp = 0;
	public double db_total_value_exp = 0;
	public double getDb_total_value() {

		double duty = 0.0;
		try {
			// this.db_total_value=Math.round(this.getSum()*100.0)/100.0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				FLB11_EOIDt dt = (FLB11_EOIDt) this
						.getDisplaylist().get(i);
				duty += dt.getExpfee();

			}
			db_total_value_exp = (duty);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return db_total_value_exp;
	}
	public void setDb_total_value(double db_total_value) {
		this.db_total_value = db_total_value;
	}
	public double getDb_total_add_value() {
		double addduty = 0.0;
		try {
			// this.db_total_value=Math.round(this.getSum()*100.0)/100.0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				FLB11_EOIDt dt = (FLB11_EOIDt) this
						.getDisplaylist().get(i);
				addduty += dt.getCalculated_add_duty();

			}
			db_total_add_value = (addduty);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return db_total_add_value;
	}
	public void setDb_total_add_value(double db_total_add_value) {
		this.db_total_add_value = db_total_add_value;
	}
	public double getSum_add() {
		return sum_add;
	}
	public void setSum_add(double sum_add) {
		this.sum_add = sum_add;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getSum_exp() {
		return sum_exp;
	}
	public void setSum_exp(double sum_exp) {
		this.sum_exp = sum_exp;
	}
	public double getDb_total_value_exp() {

		double duty = 0.0;
		try {
			// this.db_total_value=Math.round(this.getSum()*100.0)/100.0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				FLB11_EOIDt dt = (FLB11_EOIDt) this
						.getDisplaylist().get(i);
				duty += dt.getExpfee();

			}
			db_total_value_exp = (duty);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return db_total_value_exp;
	}
	public void setDb_total_value_exp(double db_total_value_exp) {
		this.db_total_value_exp = db_total_value_exp;
	}
	public double getCeshsum() {
		double addduty = 0.0;
		try {

			for (int i = 0; i < this.displaylist.size(); i++) {
				FLB11_EOIDt dt = (FLB11_EOIDt) this
						.getDisplaylist().get(i);
				addduty += dt.getCesh_tot();

			}
			ceshsum = addduty;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ceshsum;
	}
	public void setCeshsum(double ceshsum) {
		this.ceshsum = ceshsum;
	}
	public double ceshsum = 0;
	
	public void clearAll() {
		
		this.displaylist.clear();
       this.unit_nm=null;
       this.unitNo=0;
       this.purchaseOrderNo=0;
       this.countryId=0;
		this.lic_disable_flag = false;
		this.lic_disable_flag2 = false;
		 dt_date=new Date();
		// this.dist_id=0;
		// this.bottlingNotDoneFlag=false;
		
		this.vehicleNo = null;
		this.routeDtl = null;
		//this.dt_date = null;
		this.vch_from = "";
		saveflg = false;
		this.vch_to = "EOI";
		this.vch_from_lic_no = "";
		this.vch_to_lic_no = "";
		
		this.fromliclist.clear();
		this.toliclist.clear();
		this.displaylist.clear();
		this.db_total_value = 0;
		this.db_total_add_value = 0;
		this.sum = 0;
		this.setSum(0.0);
		this.setDb_total_value(0.0);
		this.setDb_total_add_value(0.0);
	//	listFlagForPrint = true;
		this.saveflg = false;
		//this.cancle();
		this.vehicleAgencyNmAdrs = null;
		this.vehicleDrvrName = null;
		this.validtilldt_date = null;
		this.tierWeight = 0.0;
		this.grossWeight = 0.0;
		this.netWeight = 0.0;
	}
	
	private boolean saveflg = false;
	public boolean isSaveflg() {
		return saveflg;
	}
	public void setSaveflg(boolean saveflg) {
		this.saveflg = saveflg;
	}
	
	// --------------------------------------------------------------------------
	private boolean validateInput1;

	public boolean isValidateInput1() {
		validateInput1 = true;
		
		/*  if(this.country_id.equalsIgnoreCase("NA") || this.country_id.length()==0 || this.country_id ==null ){
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select Country Dropdown"));
				}	*/
		 if(this.dt_date ==null ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select Date"));
		 }
		 else  if(this.validtilldt_date == null ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select Valid Till"));
		 }
		 else  if(this.vch_from == null ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select FL3 FL3A Radio "));
		 }
		 else  if(this.vch_to == null ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select EXPORT OUTSIDE INDIA  Radio "));
		 }

		 else if(this.vch_from_lic_no.equalsIgnoreCase("NA") || this.vch_from_lic_no.length()==0 || this.vch_from_lic_no ==null ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select License No"));
				}
		 else if(this.vch_to_lic_no.equalsIgnoreCase("NA") || this.vch_to_lic_no.length()==0 || this.vch_to_lic_no ==null ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please select Export Order No"));
				}
		 else if(this.unit_nm.length()==0 || this.unit_nm ==null ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Name Of Importing Unit."));
				}
		 else if(this.purchaseOrderNo ==0 ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Purchase Order No."));
				}
		 else if(this.unitNo ==0 ){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Import Order No "));
				}
		 else if(this.vehicleNo.length() ==0 || this.vehicleNo ==null){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill vehicleNo No "));
				}
		 else if(this.routeDtl.length() ==0 || this.routeDtl ==null){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Route Details "));
				}
		 else if(this.vehicleDrvrName.length() ==0 || this.vehicleDrvrName ==null){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Vehicle Driver Name "));
				}
		 else if(this.vehicleAgencyNmAdrs.length() ==0 || this.vehicleAgencyNmAdrs ==null){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Vehicle Agency Name And Address "));
				}
		 else if(this.grossWeight ==0  || this.tierWeight ==0 || this.netWeight ==0){
				this.validateInput1 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Import Order No "));
				}
		
		
	
		
		return validateInput1;
	}

	public void setValidateInput1(boolean validateInput1) {
		this.validateInput1 = validateInput1;
	}

	public Date table_dt = new Date();

	public Date getTable_dt() {
		return table_dt;
	}

	public void setTable_dt(Date table_dt) {
		this.table_dt = table_dt;
	}

	
	public String listMethod1(ValueChangeEvent vce) {
		Date ev = (Date) vce.getNewValue();
		this.getListWholesale = impl.getGatePassWholeSaleListManufacture(this,
				ev);
		return "";

	}
	
	private ArrayList getListWholesale = new ArrayList();
	private boolean listFlagForPrint = true;

	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}

	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}

	public ArrayList getGetListWholesale() {
		if (this.listFlagForPrint == true) {
			this.getListWholesale = impl.getGatePassWholeSaleListManufacture(
					this, Utility.convertUtilDateToSQLDate(new Date()));
			this.listFlagForPrint = false;
		}

		return getListWholesale;
	}
	public void setGetListWholesale(ArrayList getListWholesale) {
		this.getListWholesale = getListWholesale;
	}
	
	// ---------------------print draft report---------------

	public void printDraftReport(ActionEvent e) {
		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			FLB11_EOIDt dt = (FLB11_EOIDt) this.getListWholesale
					.get(uiTable.getRowIndex());

			this.setPrintDate(dt.getDt_date());
			this.setPrintGatePassNo(dt.getVch_gatepass_no());

			if (dt.getVch_to().equalsIgnoreCase("EXPORT")
					|| dt.getVch_to().equalsIgnoreCase("EOI")) {

				if (impl.printReportexp1Draft(this, dt) == true) {
					dt.setDraftprintFlag(true);

				} else {

					dt.setDraftprintFlag(false);

				}

				if (impl.printReport1Draft(this, dt) == true) {
					dt.setDraftprintFlag1(true);

				} else {

					dt.setDraftprintFlag1(false);
				}
			} else {

				if (impl.printDraft(this, dt) == true) {
					dt.setDraftprintFlag(true);

				} else {

					dt.setDraftprintFlag(false);

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	

	private String pdfName;
	private boolean myFlag;
	private Date printDate;
	private String printGatePassNo;
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public boolean isMyFlag() {
		return myFlag;
	}
	public void setMyFlag(boolean myFlag) {
		this.myFlag = myFlag;
	}
	public Date getPrintDate() {
		return printDate;
	}
	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	public String getPrintGatePassNo() {
		return printGatePassNo;
	}
	public void setPrintGatePassNo(String printGatePassNo) {
		this.printGatePassNo = printGatePassNo;
	}
	
	public String PdfDraft;

	public String getPdfDraft() {
		return PdfDraft;
	}

	public void setPdfDraft(String pdfDraft) {
		PdfDraft = pdfDraft;
	}
	
	public void printReport(ActionEvent e) {
		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			FLB11_EOIDt dt = (FLB11_EOIDt) this.getListWholesale
					.get(uiTable.getRowIndex());

			this.setPrintDate(dt.getDt_date());
			this.setPrintGatePassNo(dt.getVch_gatepass_no());
			 

				if (impl.printReportexp1(this, dt) == true) {
					dt.setMyFlagDt(true);

				} else {

					dt.setMyFlagDt(false);

				}

				if (impl.printReport1(this, dt) == true) {
					dt.setMyFlagDt1(true);

				} else {

					dt.setMyFlagDt1(false);

				}
			   

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	public void scanAndUpload(ActionEvent ae) {
		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent()
				.getParent();
		FLB11_EOIDt dt = (FLB11_EOIDt) this.getListWholesale
				.get(uiTable.getRowIndex());
		this.setGetvch(dt.getVch_from());

		this.setScanGatePassNo(dt.getVch_gatepass_no());
		this.setScanVchFrom(dt.getVch_from());
		this.gatePassFlag = true;
		this.scanUploadFlag = true;
		this.tableFlag = true;
		this.getVal = impl.getExcelData(this);
	}
   
	private boolean valFlag;
	public boolean isValFlag() {
		return valFlag;
	}
	public void setValFlag(boolean valFlag) {
		this.valFlag = valFlag;
	}
	private String getvch;
	private String ScanVchFrom;
	private boolean gatePassFlag;
	private boolean scanUploadFlag;
	private boolean tableFlag;
	public String getGetvch() {
		return getvch;
	}
	public void setGetvch(String getvch) {
		this.getvch = getvch;
	}
	public String getScanVchFrom() {
		return ScanVchFrom;
	}
	public void setScanVchFrom(String scanVchFrom) {
		ScanVchFrom = scanVchFrom;
	}
	public boolean isGatePassFlag() {
		return gatePassFlag;
	}
	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
	}
	public boolean isScanUploadFlag() {
		return scanUploadFlag;
	}
	public void setScanUploadFlag(boolean scanUploadFlag) {
		this.scanUploadFlag = scanUploadFlag;
	}
	public boolean isTableFlag() {
		return tableFlag;
	}
	public void setTableFlag(boolean tableFlag) {
		this.tableFlag = tableFlag;
	}
	public ArrayList getGetVal() {
		return getVal;
	}
	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}
	ArrayList getVal = new ArrayList();
	
	// -------------------------cancel gatepass--------------------------------

		private double cancelDuty;
		private double cancelAddDuty;

		public double getCancelDuty() {
			return cancelDuty;
		}

		public void setCancelDuty(double cancelDuty) {
			this.cancelDuty = cancelDuty;
		}

		public double getCancelAddDuty() {
			return cancelAddDuty;
		}

		public void setCancelAddDuty(double cancelAddDuty) {
			this.cancelAddDuty = cancelAddDuty;
		}
	
	
	public void cancel_gatepass(ActionEvent e) {
		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			FLB11_EOIDt  dt = (FLB11_EOIDt ) this.getListWholesale
					.get(uiTable.getRowIndex());

			this.setPrintDate(dt.getDt_date());
			this.setPrintGatePassNo(dt.getVch_gatepass_no());
			this.setCancelDuty(dt.getDb_total_duty_2());
			this.setCancelAddDuty(dt.getDb_total_additional_duty());
			 impl.cancel_gatepassImpl(this, dt.getVch_to(), dt
			 		.getVch_to_lic_no().trim());

		} catch (Exception a) {
			a.printStackTrace();

		}
	}

	

	private String scanGatePassNo;

	public String getScanGatePassNo() {
		return scanGatePassNo;
	}

	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}

	
	private boolean bottlingNotDoneFlag = false;
	public boolean isBottlingNotDoneFlag() {
		return bottlingNotDoneFlag;
	}

	public void setBottlingNotDoneFlag(boolean bottlingNotDoneFlag) {
		this.bottlingNotDoneFlag = bottlingNotDoneFlag;
	}

	private String csvFilename;
	private String csvFilepath;
	public String getCsvFilename() {
		return csvFilename;
	}

	public void setCsvFilename(String csvFilename) {
		this.csvFilename = csvFilename;
	}

	public String getCsvFilepath() {
		return csvFilepath;
	}

	public void setCsvFilepath(String csvFilepath) {
		this.csvFilepath = csvFilepath;
	}
	public void uploadCsv(UploadEvent event) {

		try {
			int size = 0;
			int counter = 0;
			UploadItem item = event.getUploadItem();

			String FullfileName = item.getFileName();

			String path = item.getFile().getPath();

			String fileName = FullfileName.substring(FullfileName
					.lastIndexOf("\\") + 1);

			ExternalContext con = FacesContext.getCurrentInstance()
					.getExternalContext();

			ServletContext sCon = (ServletContext) con.getContext();

			size = item.getFileSize();
			this.setCsvFilename(FullfileName);
			this.setCsvFilepath(path);

		} catch (Exception ee) {
			ee.printStackTrace();

		} finally {

		}

	}
	private boolean finalsubmit;
	private boolean cancelFlag;
	private boolean submitFlag;
	private boolean kindlyUploadFlag;
	private boolean uploaderFlag;
	
	public boolean isFinalsubmit() {
		return finalsubmit;
	}
	public void setFinalsubmit(boolean finalsubmit) {
		this.finalsubmit = finalsubmit;
	}
	public boolean isCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public boolean isSubmitFlag() {
		return submitFlag;
	}
	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}
	public boolean isKindlyUploadFlag() {
		return kindlyUploadFlag;
	}
	public void setKindlyUploadFlag(boolean kindlyUploadFlag) {
		this.kindlyUploadFlag = kindlyUploadFlag;
	}
	public boolean isUploaderFlag() {
		return uploaderFlag;
	}
	public void setUploaderFlag(boolean uploaderFlag) {
		this.uploaderFlag = uploaderFlag;
	}
	public String csvSubmit() throws IOException {
		try {
			impl.saveCSV(this);
			this.gatePassFlag = true;
			this.bottlingNotDoneFlag = false;
			this.tableFlag = true;
			this.submitFlag = false;
			this.cancelFlag = true;
			this.gatePassFlag = false;
			this.kindlyUploadFlag = false;
			this.uploaderFlag = false;
			// this.scanUploadFlag=true;

			this.finalsubmit = true;
			this.uploaderFlag = false;

			this.getVal = impl.getExcelData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
   
	private boolean printFlag;
	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	
	public void finalSubmit() {

		if (bottlingNotDoneFlag == false) {
			if (impl.excelCases(this) == impl.recieveCases(this)) {

				if (impl.updateFL3(this) == true) {
					this.printFlag = true;
					this.scanUploadFlag = false;

					this.tableFlag = false;

					this.gatePassFlag = false;
					listFlagForPrint = true;

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Data Save Successfully",
									"Data Save Successfully"));
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage("Error Occured while saving !!! ",
									"Error Occured while saving !!!"));
				}
			} else {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										"No.of cases in Gatepass and in Uploaded Excel does not match! ",
										"No.of cases in Gatepass and in Uploaded Excel does not match!"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(" Invalid Casecode Found !!! ",
							"Invalid Casecode Found !!!"));
		}
	}
	
	public void delete() {
		impl.deleteData(this);
		this.tableFlag = false;
		this.gatePassFlag = false;
	}
	private String exclcsv;
	public String getExclcsv() {
		return exclcsv;
	}

	public void setExclcsv(String exclcsv) {
		this.exclcsv = exclcsv;
	}

	private String radio1;
	private String containerno;
	private String sealno;
	private Date sealdate;
	public String getRadio1() {
		return radio1;
	}
	public void setRadio1(String radio1) {
		this.radio1 = radio1;
	}
	public String getContainerno() {
		return containerno;
	}
	public void setContainerno(String containerno) {
		this.containerno = containerno;
	}
	public String getSealno() {
		return sealno;
	}
	public void setSealno(String sealno) {
		this.sealno = sealno;
	}
	public Date getSealdate() {
		return sealdate;
	}
	public void setSealdate(Date sealdate) {
		this.sealdate = sealdate;
	}
	
	
	
}
