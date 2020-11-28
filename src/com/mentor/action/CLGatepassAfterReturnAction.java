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

import com.mentor.Datatable.CLGatepassAfterReturnDT;
import com.mentor.Datatable.CLGatepassDT;
import com.mentor.Datatable.CLGatepassAfterReturnDT;
import com.mentor.impl.CLGatepassAfterReturnImpl;
import com.mentor.impl.ClGatepass_20_21_active_impl;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Validate;

public class CLGatepassAfterReturnAction {


	CLGatepassAfterReturnImpl impl = new CLGatepassAfterReturnImpl();

	private Date dt_date = new Date();
	private String distName;
	private String distAddress;
	private int distId;
	private String vch_from = "CL";
	private String vch_from_lic_no;
	private String vch_to_lic_no;
	private String vch_to = "CL2";
	private String licenceeName;
	private String licenceeAdrs;
	private int licenceeId;
	private String hidden;
	private String session;
	private String routeDtl;
	private String vehicleNo;
	private ArrayList fromliclist = new ArrayList();
	private ArrayList toliclist = new ArrayList();
	public ArrayList displaylist = new ArrayList();
	private ArrayList getListWholesale = new ArrayList();
	private Date validtill = new Date();

	private String vehicleDrvrName;
	private String vehicleAgencyNmAdrs;
	private int district1;
	private int district2;
	private int district3;
	private boolean bottlingNotDoneFlag = false;

	public boolean isBottlingNotDoneFlag() {
		return bottlingNotDoneFlag;
	}

	public void setBottlingNotDoneFlag(boolean bottlingNotDoneFlag) {
		this.bottlingNotDoneFlag = bottlingNotDoneFlag;
	}

	private ArrayList districtList = new ArrayList();

	private int districtLic;
	private String distMob;

	private String officrEmail;

	public String getDistMob() {
		return distMob;
	}

	public void setDistMob(String distMob) {
		this.distMob = distMob;
	}

	public String getOfficrEmail() {
		return officrEmail;
	}

	public void setOfficrEmail(String officrEmail) {
		this.officrEmail = officrEmail;
	}

	public int getDistrictLic() {
		return districtLic;
	}

	public void setDistrictLic(int districtLic) {
		this.districtLic = districtLic;
	}

	public Date getValidtill() {
		return validtill;
	}

	public void setValidtill(Date validtill) {
		this.validtill = validtill;
	}

	public ArrayList getDistrictList() {
		this.districtList = impl.districtListImpl(this);
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
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

	public int getDistrict1() {
		return district1;
	}

	public void setDistrict1(int district1) {
		this.district1 = district1;
	}

	public int getDistrict2() {
		return district2;
	}

	public void setDistrict2(int district2) {
		this.district2 = district2;
	}

	public int getDistrict3() {
		return district3;
	}

	public void setDistrict3(int district3) {
		this.district3 = district3;
	}

	public Date getDt_date() {
		return dt_date;
	}

	public void setDt_date(Date dt_date) {
		this.dt_date = dt_date;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getDistAddress() {
		return distAddress;
	}

	public void setDistAddress(String distAddress) {
		this.distAddress = distAddress;
	}

	public int getDistId() {
		return distId;
	}

	public void setDistId(int distId) {
		this.distId = distId;
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

	public String getVch_to_lic_no() {
		return vch_to_lic_no;
	}

	public void setVch_to_lic_no(String vch_to_lic_no) {
		this.vch_to_lic_no = vch_to_lic_no;
	}

	public String getVch_to() {
		return vch_to;
	}

	public void setVch_to(String vch_to) {
		this.vch_to = vch_to;
	}

	public String getLicenceeName() {
		if (vch_to_lic_no != null && vch_to_lic_no.length() > 0) {
			impl.licenceeDetail(this);
		}
		return licenceeName;
	}

	public void setLicenceeName(String licenceeName) {
		this.licenceeName = licenceeName;
	}

	public String getLicenceeAdrs() {
		if (vch_to_lic_no != null && vch_to_lic_no.length() > 0) {
			impl.licenceeDetail(this);
		}
		return licenceeAdrs;
	}

	public void setLicenceeAdrs(String licenceeAdrs) {
		this.licenceeAdrs = licenceeAdrs;
	}

	public int getLicenceeId() {
		if (vch_to_lic_no != null && vch_to_lic_no.length() > 0) {
			impl.licenceeDetail(this);
		}
		return licenceeId;
	}

	public void setLicenceeId(int licenceeId) {
		this.licenceeId = licenceeId;
	}

	public String getHidden() {
		try {
			impl.getDetails(this);
			impl.getSeasonDetails(this);
			impl.getEmailDetails(this);
		} catch (Exception e) {
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
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

	public ArrayList getFromliclist() {
		return fromliclist;
	}

	public void setFromliclist(ArrayList fromliclist) {
		this.fromliclist = fromliclist;
	}

	public ArrayList getToliclist() {
		this.toliclist = impl.licListFL2Impl(this);
		return toliclist;
	}

	public void setToliclist(ArrayList toliclist) {
		this.toliclist = toliclist;
	}

	public ArrayList getDisplaylist() {

		return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}

	public ArrayList getGetListWholesale() {

		if (this.listFlagForPrint == true) {
			this.getListWholesale = impl.getGatePassWholeSaleListCL(this);
			this.listFlagForPrint = false;
		}
		return getListWholesale;
	}

	public void setGetListWholesale(ArrayList getListWholesale) {
		this.getListWholesale = getListWholesale;
	}

	// ====================calculate duty======================
	public boolean flag = true;
	public double db_total_value = 0;
	public double sum = 0;

	public void calculateTotalDuty(ActionEvent ae) {

		if (isFlag()) {
			this.setSum(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.displaylist.get(i);

				this.setSum(this.getSum() + dt.getCalculated_duty());

			}
		}
		this.flag = false;

	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public double getDb_total_value() {

		double duty = 0.0;
		try {

			for (int i = 0; i < this.displaylist.size(); i++) {
				CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.getDisplaylist().get(i);
				duty += dt.getCalculated_duty();
				// System.out.println("duty----------------" + duty);
			}
			db_total_value = duty;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return db_total_value;

	}

	public void setDb_total_value(double db_total_value) {
		this.db_total_value = db_total_value;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public boolean isAddflag() {
		return addflag;
	}

	public void setAddflag(boolean addflag) {
		this.addflag = addflag;
	}

	public double getDb_total_add_value() {

		double addduty = 0.0;
		try {

			for (int i = 0; i < this.displaylist.size(); i++) {
				CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.getDisplaylist().get(i);
				addduty += dt.getCalculated_add_duty();
				// System.out.println("add duty----------------" + addduty);
			}
			db_total_add_value = addduty;
		} catch (Exception e) {
			e.printStackTrace();
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

	// ====================calculate additional duty======================

	public boolean addflag = true;
	public double db_total_add_value = 0;
	public double sum_add = 0;

	public void calculateTotalAddDuty(ActionEvent ae) {

		if (isAddflag()) {
			this.setSum_add(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.displaylist.get(i);

				this.setSum_add(this.getSum_add() + dt.getCalculated_add_duty());

			}
		}
		this.addflag = false;

	}

	// =====================validation=====================================

	private boolean validateInput1;

	public boolean isValidateInput1() {

		validateInput1 = true;

		if (!(Validate.validateStrReq("vehiclenmbr", this.getVehicleNo())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("radiofrom", this.getVch_from())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("radioto", this.getVch_to())))
			validateInput1 = false;
		else if (!(Validate
				.validateStrReq("licenename", this.getLicenceeName())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("liceneadd", this.getLicenceeAdrs())))
			validateInput1 = false;
		/*
		 * if(this.getVch_from_lic_no()==null ||
		 * this.getVch_from_lic_no().equals(null) ||
		 * this.getVch_from_lic_no().equalsIgnoreCase("0") ){
		 * ResourceUtil.addErrorMessage(Constants.SELECT_FROM,
		 * Constants.SELECT_FROM); validateInput1=false; }
		 */

		if (this.getVch_to_lic_no() == null
				|| this.getVch_to_lic_no().equals(null)
				|| this.getVch_to_lic_no().equalsIgnoreCase("0")) {
			ResourceUtil.addErrorMessage(Constants.SELECT_TO,
					Constants.SELECT_TO);
			validateInput1 = false;
		}

		int sumBottles = 0;
		int sumBoxes = 0;

		for (int i = 0; i < this.displaylist.size(); i++) {
			CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) displaylist.get(i);

			sumBottles += dt.getInt_dispatch();
			sumBoxes += dt.getDispatchBoxes();

			/*
			 * if (dt.getBalance() <= 0) {
			 * FacesContext.getCurrentInstance().addMessage( null, new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Balance !!! ",
			 * "Invalid Balance !!!")); validateInput1 = false;
			 * 
			 * }
			 */
			if (dt.getDispatchBoxes() > dt.getBoxAvailable()
					&& dt.getInt_dispatch() > dt.getInt_bottle_avaliable()) 
			{
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Dispatch Boxes & Bottles Should Be Less Than Available Boxes & Bottles !!! ",
										"Dispatch Boxes & Bottles Should Be Less Than Available Boxes & Bottles !!!"));
				validateInput1 = false;

			}
			if (dt.getIndentbox()==0) 
			{
				FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Indented Box Cannot Be Zero!!! ",
								"Indented Box Cannot Be Zero!!!"));
		validateInput1 = false;
			}
			if (dt.getDispatchBoxes() > dt.getIndentbox()) 
			{
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Dispatch Boxes  Should Be Less Than Indented Box !!! ",
										"Dispatch Boxes  Should Be Less Than Indented Box !!!"));
				validateInput1 = false;

			}
			
			
			
			
			
			
		}

		if (sumBoxes == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Dispatch Boxes Should Be Greater Than Zero !!! ",
							"Dispatch Boxes Should Be Greater Than Zero !!!"));
			validateInput1 = false;
		} else if (sumBottles == 0) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Dispatch Bottles Should Be Greater Than Zero !!! ",
									"Dispatch Bottles Should Be Greater Than Zero !!!"));
			validateInput1 = false;

		}

		return validateInput1;

	}

	public void setValidateInput1(boolean validateInput1) {
		this.validateInput1 = validateInput1;
	}

	// ---------------------save----------------
	private boolean saveflg;
	private String savecss = " display: none; ";
	private String shOverlayStyle = "position: fixed; display: none; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";

	public String getShOverlayStyle() {

		return shOverlayStyle;
	}

	public void setShOverlayStyle(String shOverlayStyle) {
		this.shOverlayStyle = shOverlayStyle;
	}

	public String getSavecss() {
		return savecss;
	}

	public void setSavecss(String savecss) {
		this.savecss = savecss;
	}

	public boolean isSaveflg() {
		return saveflg;
	}

	public void setSaveflg(boolean saveflg) {
		this.saveflg = saveflg;
	}

	public String tntflg = "X";

	public String getTntflg() {
		return tntflg;
	}

	public void setTntflg(String tntflg) {
		this.tntflg = tntflg;
	}

	public void saveconfirmation(ActionEvent e) {
		try {
			String tntfg = "";
			boolean flg = false;
			boolean x = false;
			for (int i = 0; i < this.displaylist.size(); i++) {
				CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) displaylist.get(i);
				if (dt.getDispatchBoxes() > 0) {
					if (x == false) {
						tntfg = dt.getTntflg();
						x = true;
					}

					if (dt.getTntflg().equalsIgnoreCase(tntfg)) {
						this.tntflg = dt.getTntflg();
						flg = true;
					} else {
						flg = false;
					}
				}
			}
			if (flg == true) {
				saveflg = true;
				this.savecss = "position: fixed; top: 160px; right: 0px; left: 0px; display: block; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
				this.shOverlayStyle = "position: fixed; display: block; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";
			} else {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Dispatch should either contain stock made before 1st June'18 or stock made after 31st May'18.  ",
										"Dispatch should either contain stock made before 1st June'18 or stock made after 31st May'18. "));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void cancle() {
		try {
			saveflg = false;
			// savecss="position:aboslute;       pointer-events: none;          opacity:0.5;   z-index:9999;  height:100%; width:100%;";
			// this.savecss =
			// "position: absolute;  top: 200px; right: 0px; left: 0px; display: block; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
			this.savecss = "position: absolute; top: 230px; right: 0px; left: 0px; display: none; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
			this.shOverlayStyle = "position: fixed; display: none; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void saveMethod() {
		try {
			if (isValidateInput1()) 
			{
				
				if (saveflg == true) 
				{
					
					
					if(impl.checkDate(this) != true){
					 
					impl.saveMethodImpl(this);
				    listFlagForPrint = true;
				    
					}else{
						
						FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										" First Finalize/Delete all your Draft Gatapasses Till Yesterday!!",
										" First Finalize/Delete all your Draft Gatapasses Till Yesterday!!"));
					}
				}
				

			} else {
				saveflg = false;
				this.savecss = "position: absolute; top: 230px; right: 0px; left: 0px; display: none; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
				this.shOverlayStyle = "position: fixed; display: none; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ===================reset method======================

	public void clearAll() {
		// this.bottlingNotDoneFlag=false;
		
		this.licence_disable_flag = false;
		
		getstockflg=false;
		this.distName = "";
		this.distAddress = "";
		this.distId = 0;
		this.dt_date = null;
		this.vch_from = "";
		this.licenceeId = 0;
		this.licenceeAdrs = null;
		this.licenceeName = null;
		this.vch_to = "CL2";
		this.vch_from_lic_no = "";
		this.vch_to_lic_no = "";
		this.fromliclist.clear();
		this.toliclist.clear();
		this.displaylist.clear();
		this.routeDtl = null;
		this.vehicleNo = null;
		this.vehicleDrvrName = null;
		this.vehicleAgencyNmAdrs = null;
		this.district1 = 0;
		this.district2 = 0;
		this.district3 = 0;
		this.savecss = "position: absolute; top: 230px; right: 0px; left: 0px; display: none; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
		this.shOverlayStyle = "position: fixed; display: none; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";
		saveflg = false;
		this.printFlag = false;
		this.listFlagForPrint = true;
		this.displaylist1.clear();
		this.cancle();

	}

	public String listMethod(ValueChangeEvent vce) {

		this.displaylist = impl.displaylistImpl(this);
		return "";
	}

	// ------------------------------- pRINT REPORT ------------------

	private boolean printFlag;
	private String pdfname;
	private Date printDate;
	private String printGatePassNo;

	private boolean listFlagForPrint = true;

	public String getPrintGatePassNo() {
		return printGatePassNo;
	}

	public void setPrintGatePassNo(String printGatePassNo) {
		this.printGatePassNo = printGatePassNo;
	}

	public boolean isListFlagForPrint() {
		return listFlagForPrint;
	}

	public void setListFlagForPrint(boolean listFlagForPrint) {
		this.listFlagForPrint = listFlagForPrint;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	public String getPdfname() {
		return pdfname;
	}

	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}

	public void printreport(ActionEvent e) {

		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.getListWholesale.get(uiTable
				.getRowIndex());

		this.setPrintDate(dt.getDt_date());
		this.setPrintGatePassNo(dt.getVch_gatepass_no());
		if (impl.printReport(this) == true) {
			dt.setPrintFlag(true);

		} else {

			dt.setPrintFlag(false);

		}

	}

	// ==============new variables============================

	private double grossWeight = 0;
	private double tierWeight = 0;
	private double netWeight = 0;

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
		}
		return netWeight;
	}

	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}

	// -----------------------kamil--------------------------------------------------------

	private boolean printDraftFlag;
	private boolean scanUploadFlag;
	private boolean printDraft;
	private boolean kindlyUploadFlag;
	private boolean uploaderFlag;
	private boolean submitFlag;
	private boolean gatePassFlag;
	private boolean tableFlag;
	private boolean finalsubmit;
	private boolean cancelFlag;

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

	public boolean isSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

	public String importExcel() {

		new CLGatepassAfterReturnImpl().saveExcelData(this);
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
		return "";
	}

	public void scanAndUpload(ActionEvent ae) {
		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent().getParent();
		CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.getListWholesale.get(uiTable.getRowIndex());
		
		this.setGetvch(dt.getVch_from());

		this.setScanGatePassNo(dt.getVch_gatepass_no());
		this.gatePassFlag = true;
		this.tableFlag = true;
		
		this.getVal = impl.getExcelData(this);

		/*this.submitFlag = true;
		this.cancelFlag = true;
		this.uploaderFlag = true;
		this.tableFlag = true;
		this.finalsubmit = true;
		this.kindlyUploadFlag = true;
		this.gatePassFlag = true;
		this.scanUploadFlag = true;
		this.printDraft = true;*/
		
	}

	private boolean valFlag;
	public ArrayList getVal = new ArrayList();

	public ArrayList getGetVal() {
		//this.getVal = impl.getExcelData(this);
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}

	public boolean isPrintDraft() {
		return printDraft;
	}

	public void setPrintDraft(boolean printDraft) {
		this.printDraft = printDraft;
	}

	public boolean isPrintDraftFlag() {
		return printDraftFlag;
	}

	public void setPrintDraftFlag(boolean printDraftFlag) {
		this.printDraftFlag = printDraftFlag;
	}

	public boolean isScanUploadFlag() {
		return scanUploadFlag;
	}

	public void setScanUploadFlag(boolean scanUploadFlag) {
		this.scanUploadFlag = scanUploadFlag;

	}

	CLGatepassAfterReturnDT dt = new CLGatepassAfterReturnDT();
	private String getvch;

	private String excelFilename;
	private String excelFilepath;
	private boolean finalPrint;

	private String scanGatePassNo;
	private int excelCases;

	public String getExcelFilename() {
		return excelFilename;
	}

	public void setExcelFilename(String excelFilename) {
		this.excelFilename = excelFilename;
	}

	public String getExcelFilepath() {
		return excelFilepath;
	}

	public void setExcelFilepath(String excelFilepath) {
		this.excelFilepath = excelFilepath;
	}

	public String getScanGatePassNo() {
		return scanGatePassNo;
	}

	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}

	public int getExcelCases() {
		return excelCases;
	}

	public void setExcelCases(int excelCases) {
		this.excelCases = excelCases;
	}

	public void uploadExcel(UploadEvent event) {

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
			this.excelFilename = FullfileName;
			this.excelFilepath = path;

		} catch (Exception ee) {
			ee.printStackTrace();

		} finally {

		}

	}

	private int receiveCases;

	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
	}

	public boolean isFinalPrint() {
		return finalPrint;
	}

	public void setFinalPrint(boolean finalPrint) {
		this.finalPrint = finalPrint;
	}

	public void delete() {

		new CLGatepassAfterReturnImpl().deleteData(this);
		this.tableFlag = false;
		/*this.finalsubmit = false;
		this.cancelFlag = false;
		this.gatePassFlag = false;
		this.kindlyUploadFlag = false;
		this.uploaderFlag = false;
		this.submitFlag = false;*/
	}

	public void finalSubmit() {

		if (impl.excelCases(this) == impl.recieveCases(this)) {
			if (bottlingNotDoneFlag == false) {
			if (impl.updateFL3(this) == true) {
				this.printFlag = true;
				this.scanUploadFlag = false;
				this.submitFlag = false;
				this.cancelFlag = false;
				this.finalsubmit = true;
				this.uploaderFlag = false;
				this.finalsubmit = false;
				this.cancelFlag = false;
				this.tableFlag = false;
				this.kindlyUploadFlag = false;
				this.gatePassFlag = false;
				this.printFlag = true;
				this.printDraft = false;
				this.printDraftFlag = false;
				this.finalPrint = true;

				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Data Save Successfully",
								"Data Save Successfully"));

				clearAll();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("Error Occured while saving !!! ",
								"Error Occured while saving !!!"));
			}}
		} else {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"No.of cases in Gatepass and in Uploaded Excel does not match! ",
									"No.of cases in Gatepass and in Uploaded Excel does not match!"));
		}
	}

	public boolean isTableFlag() {
		return tableFlag;
	}

	public void setTableFlag(boolean tableFlag) {
		this.tableFlag = tableFlag;
	}

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

	public boolean isValFlag() {
		return valFlag;
	}

	public void setValFlag(boolean valFlag) {
		this.valFlag = valFlag;
	}

	public int getReceiveCases() {
		return receiveCases;
	}

	public void setReceiveCases(int receiveCases) {
		this.receiveCases = receiveCases;
	}

	public String getGetvch() {
		return getvch;
	}

	public void setGetvch(String getvch) {
		this.getvch = getvch;
	}

	// ---------------------------------------print drfat--------------
	private boolean printdraftFlag;
	private String pdfDraftname;
	private boolean draftFlag;

	public boolean isDraftFlag() {
		return draftFlag;
	}

	public void setDraftFlag(boolean draftFlag) {
		this.draftFlag = draftFlag;
	}

	public boolean isPrintdraftFlag() {
		return printdraftFlag;
	}

	public void setPrintdraftFlag(boolean printdraftFlag) {
		this.printdraftFlag = printdraftFlag;
	}

	public String getPdfDraftname() {
		return pdfDraftname;
	}

	public void setPdfDraftname(String pdfDraftname) {
		this.pdfDraftname = pdfDraftname;
	}

	public void printDraftreport(ActionEvent e) {

		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.getListWholesale.get(uiTable
				.getRowIndex());

		this.setPrintDate(dt.getDt_date());
		this.setPrintGatePassNo(dt.getVch_gatepass_no());
		if (impl.printDraftReport(this) == true) {
			dt.setDraftprintFlag(true);

		} else {

			dt.setDraftprintFlag(false);

		}

	}

	// ---------------------cancel gatepass-----------------------

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
			CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.getListWholesale.get(uiTable
					.getRowIndex());

			this.setPrintDate(dt.getDt_date());
			this.setPrintGatePassNo(dt.getVch_gatepass_no());
			this.setCancelDuty(dt.getDb_total_duty_2());
			this.setCancelAddDuty(dt.getDb_total_additional_duty());
			impl.cancel_gatepassImpl(this);

		} catch (Exception a) {
			a.printStackTrace();

		}
	}

	public void getLicenseDetails() 
	{
	// impl.getLicenseDetail(this);
		getstockflg=false;
		
		if(impl.getLicenseDetail(this)) {
			this.displaylist1=impl.indentDetail(this);
		}
		displaylist.clear();
	}

	// ==========================code for csv===================================

	private String csvFilename;
	private String csvFilepath;
	private String exclcsv;

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

	public String getExclcsv() {
		return exclcsv;
	}

	public void setExclcsv(String exclcsv) {
		this.exclcsv = exclcsv;
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

	public String csvSubmit() throws IOException {
		
		
		impl.saveCSV(this);
		this.bottlingNotDoneFlag=false;
		this.gatePassFlag = true;
		this.tableFlag = true;
		
		this.getVal = impl.getExcelData(this);
		
		/*this.bottlingNotDoneFlag = false;
		this.tableFlag = true;
		this.submitFlag = false;
		this.cancelFlag = true;
		this.gatePassFlag = false;
		this.kindlyUploadFlag = false;
		this.uploaderFlag = false;
		this.scanUploadFlag=true;
		this.finalsubmit = true;
		this.uploaderFlag = false;*/
		
		return "";
	}
//-------------------------------------------------------------
	ArrayList displaylist1 = new ArrayList();

	public ArrayList getDisplaylist1() {
		return displaylist1;
	}

	public void setDisplaylist1(ArrayList displaylist1) {
		this.displaylist1 = displaylist1;
	}
	private boolean getstockflg;
	
	
	
	public boolean isGetstockflg() {
		return getstockflg;
	}

	public void setGetstockflg(boolean getstockflg) {
		this.getstockflg = getstockflg;
	}

public void getData(ActionEvent e)
{
	
	this.displaylist = impl.displaylistImpl2(this);
	getstockflg=true;
}

private boolean licence_disable_flag;

public boolean isLicence_disable_flag() {
	return licence_disable_flag;
}

public void setLicence_disable_flag(boolean licence_disable_flag) {
	this.licence_disable_flag = licence_disable_flag;
}


public boolean ceshflag = true;
public double ceshdb_total_value = 0;
public double ceshsum = 0;


public boolean isCeshflag() {
	return ceshflag;
}

public void setCeshflag(boolean ceshflag) {
	this.ceshflag = ceshflag;
}

public double getCeshdb_total_value() {
	return ceshdb_total_value;
}

public void setCeshdb_total_value(double ceshdb_total_value) {
	this.ceshdb_total_value = ceshdb_total_value;
}

public double getCeshsum() {
	double addduty = 0.0;
	try {

		for (int i = 0; i < this.displaylist.size(); i++) {
			CLGatepassAfterReturnDT dt = (CLGatepassAfterReturnDT) this.getDisplaylist().get(i);
			addduty += dt.getCesh_tot();
			// System.out.println("add duty----------------" + addduty);
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

public void calculateTotalCeshDuty(ActionEvent ae) {

	if (isCeshflag()) {
		this.setCeshsum(0);
		for (int i = 0; i < this.displaylist.size(); i++) {
			CLGatepassDT dt = (CLGatepassDT) this.displaylist.get(i);

			this.setCeshsum(this.getCeshsum() + dt.getCesh_tot());

		}
	}
	this.ceshflag = false;

}





}
