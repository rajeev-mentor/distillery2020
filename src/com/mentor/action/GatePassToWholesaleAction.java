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
 
import com.mentor.Datatable.GatePassToDistrictWholesaleDatatable;
import com.mentor.Datatable.GatePassToWholesaleDataTable; 
import com.mentor.impl.GatePassToDistrictWholesaleImpl;
import com.mentor.impl.GatePassToWholesaleImpl;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;
import com.mentor.utility.Utility;
import com.mentor.utility.Validate;

public class GatePassToWholesaleAction {

	GatePassToWholesaleImpl impl = new GatePassToWholesaleImpl();

	private String scanGatePassNo;

	public String getScanGatePassNo() {
		return scanGatePassNo;
	}

	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}

	private double feeExport;
	private String hidden;
	private String dispatch = "0";

	private String routeDtl;
	private String vehicleNo;
	private String session;
	private boolean withflag;
	private boolean underflag;
	private String authName;
	public String undr_bond = "2";
	private boolean licNoflg;
	private boolean authNmflg;

	private String barcode;
	public ArrayList barcodelist = new ArrayList();
	private int totalscannedbox = 0;
	private GatePassToWholesaleDataTable gptwd;
	ArrayList districtList = new ArrayList();
	private int districtLic;
	private String vehicleDrvrName;
	private String vehicleAgencyNmAdrs;
	private String exportLicenseNumber;
	private String transportMode;
	private double bondValue;
	public int countryId;
	ArrayList countryList = new ArrayList();
	public Date permitDt;
	private String permitNmbr = "NA";
	private boolean PrintDraft;
	private String ScanVchFrom;
	private String Casecode;

	private String exclcsv;
	private boolean bottlingNotDoneFlag = false;
	private String csvFilename;
	private String csvFilepath;

	public double getFeeExport() {
		return feeExport;
	}

	public void setFeeExport(double feeExport) {
		this.feeExport = feeExport;
	}

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

	public boolean isBottlingNotDoneFlag() {
		return bottlingNotDoneFlag;
	}

	public void setBottlingNotDoneFlag(boolean bottlingNotDoneFlag) {
		this.bottlingNotDoneFlag = bottlingNotDoneFlag;
	}

	public String getExclcsv() {
		return exclcsv;
	}

	public void setExclcsv(String exclcsv) {
		this.exclcsv = exclcsv;
	}

	public String getCasecode() {
		return Casecode;
	}

	public void setCasecode(String casecode) {
		Casecode = casecode;
	}

	public String getScanVchFrom() {
		return ScanVchFrom;
	}

	public void setScanVchFrom(String scanVchFrom) {
		ScanVchFrom = scanVchFrom;
	}

	public boolean isPrintDraft() {
		return PrintDraft;
	}

	public void setPrintDraft(boolean printDraft) {
		PrintDraft = printDraft;
	}

	public Date getPermitDt() {
		return permitDt;
	}

	public void setPermitDt(Date permitDt) {
		this.permitDt = permitDt;
	}

	public String getPermitNmbr() {
		return permitNmbr;
	}

	public void setPermitNmbr(String permitNmbr) {
		this.permitNmbr = permitNmbr;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public ArrayList getCountryList() {
		this.countryList = impl.getCountry();
		return countryList;
	}

	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}

	public ArrayList getDistrictList() {
		districtList = impl.getDistList();
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public int getDistrictLic() {
		return districtLic;
	}

	public void setDistrictLic(int districtLic) {
		this.districtLic = districtLic;
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

	public String getExportLicenseNumber() {
		return exportLicenseNumber;
	}

	public void setExportLicenseNumber(String exportLicenseNumber) {
		this.exportLicenseNumber = exportLicenseNumber;
	}

	public String getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	public double getBondValue() {
		return bondValue;
	}

	public void setBondValue(double bondValue) {
		this.bondValue = bondValue;
	}

	public GatePassToWholesaleDataTable getGptwd() {
		return gptwd;
	}

	public void setGptwd(GatePassToWholesaleDataTable gptwd) {
		this.gptwd = gptwd;
	}

	public int getTotalscannedbox() {
		return totalscannedbox;
	}

	public void setTotalscannedbox(int totalscannedbox) {
		this.totalscannedbox = totalscannedbox;
	}

	public ArrayList getBarcodelist() {
		barcodelist = impl.getbarcodeDetails(this);
		return barcodelist;
	}

	public void setBarcodelist(ArrayList barcodelist) {
		this.barcodelist = barcodelist;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public boolean isLicNoflg() {
		return licNoflg;
	}

	public void setLicNoflg(boolean licNoflg) {
		this.licNoflg = licNoflg;
	}

	public boolean isAuthNmflg() {
		return authNmflg;
	}

	public void setAuthNmflg(boolean authNmflg) {
		this.authNmflg = authNmflg;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getUndr_bond() {
		return undr_bond;
	}

	public void setUndr_bond(String undr_bond) {
		this.undr_bond = undr_bond;
	}

	public boolean isWithflag() {
		return withflag;
	}

	public void setWithflag(boolean withflag) {
		this.withflag = withflag;
	}

	public boolean isUnderflag() {
		return underflag;
	}

	public void setUnderflag(boolean underflag) {
		this.underflag = underflag;
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

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
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

	public int dist_id;
	public String name;
	public String address;

	public String vch_gatepass_no;
	public Date dt_date = new Date();
	public String vch_from;
	public String vch_to;
	public String vch_from_lic_no;
	public String vch_to_lic_no;
	public boolean flag = true;
	public String vch_bond = "1";
	public boolean addflag = true;
	private boolean kindlyUploadFlag;
	private boolean gatePassFlag;
	private boolean tableFlag;
	private boolean finalsubmit;
	private boolean cancelFlag;
	private boolean uploaderFlag;
	private boolean submitFlag;
	private String excelFilename;
	private String excelFilepath;
	private int excelCases;
	private boolean scanUploadFlag;
	private boolean printDraft;
	private String getvch;
	private boolean valFlag;
	ArrayList getVal = new ArrayList();

	public boolean isValFlag() {
		return valFlag;
	}

	public void setValFlag(boolean valFlag) {
		this.valFlag = valFlag;
	}

	public ArrayList getGetVal() {
		// this.getVal = impl.getExcelData(this);
		return getVal;
	}

	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
	}

	public String getGetvch() {
		return getvch;
	}

	public void setGetvch(String getvch) {
		this.getvch = getvch;
	}

	public boolean isScanUploadFlag() {
		return scanUploadFlag;
	}

	public void setScanUploadFlag(boolean scanUploadFlag) {
		this.scanUploadFlag = scanUploadFlag;
	}

	public int getExcelCases() {
		return excelCases;
	}

	public void setExcelCases(int excelCases) {
		this.excelCases = excelCases;
	}

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

	public boolean isSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

	public boolean isUploaderFlag() {
		return uploaderFlag;
	}

	public void setUploaderFlag(boolean uploaderFlag) {
		this.uploaderFlag = uploaderFlag;
	}

	public boolean isGatePassFlag() {
		return gatePassFlag;
	}

	public void setGatePassFlag(boolean gatePassFlag) {
		this.gatePassFlag = gatePassFlag;
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

	public boolean isKindlyUploadFlag() {
		return kindlyUploadFlag;
	}

	public void setKindlyUploadFlag(boolean kindlyUploadFlag) {
		this.kindlyUploadFlag = kindlyUploadFlag;
	}

	public void clearAll() {

		this.lic_disable_flag = false;
		this.lic_disable_flag2 = false;

		// this.dist_id=0;
		// this.bottlingNotDoneFlag=false;
		this.name = "";
		this.address = "";
		this.vch_gatepass_no = "";
		this.vehicleNo = null;
		this.routeDtl = null;
		this.dt_date = null;
		this.vch_from = "";
		saveflg = false;
		this.vch_to = "";
		this.vch_from_lic_no = "";
		this.vch_to_lic_no = "";
		this.vch_bond = "";
		this.fromliclist.clear();
		this.toliclist.clear();
		this.displaylist.clear();
		this.db_total_value = 0;
		this.db_total_add_value = 0;
		this.sum = 0;
		this.setSum(0.0);
		this.setDb_total_value(0.0);
		this.setDb_total_add_value(0.0);
		listFlagForPrint = true;
		this.savecss = "position: absolute; top: 230px; right: 0px; left: 0px; display: none; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
		this.shOverlayStyle = "position: fixed; display: none; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";
		this.saveflg = false;
		this.cancle();
		this.vehicleAgencyNmAdrs=null;
		this.vehicleDrvrName=null;
		this.validtilldt_date=null;
		this.tierWeight=0.0;
		this.grossWeight=0.0;
		this.netWeight=0.0;
	}

	private String navgtinno;
	private int navdisptchdbox;
	GatePassToWholesaleDataTable gptw;

	public int getNavdisptchdbox() {
		return navdisptchdbox;
	}

	public void setNavdisptchdbox(int navdisptchdbox) {
		this.navdisptchdbox = navdisptchdbox;
	}

	public GatePassToWholesaleDataTable getGptw() {
		return gptw;
	}

	public void setGptw(GatePassToWholesaleDataTable gptw) {
		this.gptw = gptw;
	}

	public String getNavgtinno() {
		return navgtinno;
	}

	public void setNavgtinno(String navgtinno) {
		this.navgtinno = navgtinno;
	}

	public String navscan() {
		this.navgtinno = gptw.getGtinno();
		this.navdisptchdbox = gptw.getFindispatchedbox();
		return "go";

	}

	public String navreset() {
		this.navgtinno = null;
		this.navdisptchdbox = 0;
		this.totalscannedbox = 0;
		return "go";

	}

	// ---------------------save confirmation----------------

	private boolean saveflg = false;
	private String savecss = " display: none; ";
	private String shOverlayStyle = "position: fixed; display: none; width: 100%; height: 100%; top: 0; left: 0; right: 0; bottom: 0; background-color: rgba(0, 0, 0, 0.5); z-index: 1500; cursor: pointer; animation-name: sh-entry; animation-duration: 0.3s;";

	public String PdfDraft;

	public String getPdfDraft() {
		return PdfDraft;
	}

	public void setPdfDraft(String pdfDraft) {
		PdfDraft = pdfDraft;
	}

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

	public void saveconfirmation(ActionEvent e) {
		try {
			String tntfg = "";
			boolean flg = false;
			boolean x = false;
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) displaylist
						.get(i);
				if (dt.getDispatchBoxes() > 0) {
					if (x == false) {
						tntfg = dt.getTntflg();
						x = true;
					}

					if (dt.getTntflg().equalsIgnoreCase(tntfg)) {
						flg = true;
					} else {
						flg = false;
					}
				}
			}
			if (flg == true) {
				saveflg = true;
				this.savecss = "position: fixed; top: 200px; right: 0px; left: 0px; display: block; z-index: 1502; animation-name: sh-entry; animation-duration: 0.3s;";
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

	// final submit---------------------
	private int receiveCases;
	private boolean printFlag;
	private boolean finalPrint;

	public boolean isFinalPrint() {
		return finalPrint;
	}

	public void setFinalPrint(boolean finalPrint) {
		this.finalPrint = finalPrint;
	}

	public boolean isPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}

	// delete data form...........

	public void delete() {
		impl.deleteData(this);
		this.tableFlag = false;
		this.gatePassFlag = false;
	}

	/*
	 * public void delete() { new GatePassToWholesaleImpl().deleteData();
	 * this.tableFlag=false; this.finalsubmit=false; this.cancelFlag=false;
	 * this.gatePassFlag=false; this.kindlyUploadFlag=false;
	 * this.uploaderFlag=false; this.submitFlag=false; }
	 */

	/*
	 * public void finalSubmit() {
	 * System.out.println("-----excel   cases------------"+excelCases);
	 * System.out.println("-----receive cases------------"+receiveCases);
	 * 
	 * if(excelCases==receiveCases) {
	 * 
	 * if(impl.updateFL3(this)==true) { this.printFlag=true;
	 * this.scanUploadFlag=false; this.submitFlag=false; this.cancelFlag=false;
	 * this.finalsubmit=true; this.uploaderFlag=false; this.finalsubmit=false;
	 * this.cancelFlag=false; this.tableFlag=false; this.kindlyUploadFlag=false;
	 * this.gatePassFlag=false; this.printFlag=true; this.printDraft=false;
	 * this.printDraftFlag=false; this.finalPrint=true;
	 * 
	 * 
	 * FacesContext.getCurrentInstance().addMessage( null, new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Save Successfully",
	 * "Data Save Successfully")); }else {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage("Error Occured while saving !!! ",
	 * "Error Occured while saving !!!")); } }else {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage("Invalid cases is provided ",
	 * "Invalid cases is provided ")); } }
	 */

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

	// scan and upload------------------

	public int getReceiveCases() {
		return receiveCases;
	}

	public void setReceiveCases(int receiveCases) {
		this.receiveCases = receiveCases;
	}

	public void scanAndUpload(ActionEvent ae) {
		UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent()
				.getParent();
		GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this.getListWholesale
				.get(uiTable.getRowIndex());
		this.setGetvch(dt.getVch_from());

		this.setScanGatePassNo(dt.getVch_gatepass_no());
		this.setScanVchFrom(dt.getVch_from());
		this.gatePassFlag = true;
		this.scanUploadFlag = true;
		this.tableFlag = true;
		this.getVal = impl.getExcelData(this);
	}

	// ---------------------print draft report---------------

	public void printDraftReport(ActionEvent e) {
		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this.getListWholesale
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

	public boolean isPrintDraftFlag() {
		return printDraftFlag;
	}

	public void setPrintDraftFlag(boolean printDraftFlag) {
		this.printDraftFlag = printDraftFlag;
	}

	private boolean printDraftFlag;

	// private boolean scanUploadFlag;

	// ---------------------save----------------
	public void saveMethod() {
		try {
			if (isValidateInput1()) {
				if (saveflg == true) {
					/*
					 * if(this.getFeeExport()==0.0) { FacesContext
					 * .getCurrentInstance() .addMessage( null, new
					 * FacesMessage( FacesMessage.SEVERITY_ERROR,
					 * "Kindly Enter Export Fees !!! ",
					 * "Kindly Enter Export Fees !!!")); }else {
					 */
					impl.saveMethodImpl(this);

					// }

					listFlagForPrint = true;
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

	// --------------------------------------------------------------------------
	private boolean validateInput1;

	public boolean isValidateInput1() {
		validateInput1 = true;
		if (!(Validate.validateDate("date", this.getDt_date())))
			validateInput1 = false;
		else if (!(Validate.validateDate("validtill",
				this.getValidtilldt_date())))
			validateInput1 = false;
		// else
		// if(!(Validate.validateDate("permitdate",this.getPermitDt())))validateInput1=false;
		// else if (!(Validate.validateStrReq("permitnmbr",
		// this.getPermitNmbr())))validateInput1 = false;
		else if (!(Validate.validateStrReq("radiofrom", this.getVch_from())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("radioto", this.getVch_to())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vch_from_lic_no",
				this.getVch_from_lic_no())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("routedtl", this.getRouteDtl())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vehiclenmbr", this.getVehicleNo())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("routedetails", this.getRouteDtl())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vehicleno", this.getVehicleNo())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("drivername",
				this.getVehicleDrvrName())))
			validateInput1 = false;
		else if (!(Validate.validateStrReq("vehicleAgencyNmAdrs",
				this.getVehicleAgencyNmAdrs())))
			validateInput1 = false;
		else if (!(Validate
				.validateDouble("grossWeight", this.getGrossWeight())))
			validateInput1 = false;
		else if (this.getVch_to().equalsIgnoreCase("FL1")
				|| this.getVch_to().equalsIgnoreCase("FL1A")
				|| this.getVch_to().equalsIgnoreCase("FL2A")
				|| this.getVch_to() == null) {
			// if (isLicNoflg()) {
			if (this.vch_to_lic_no.equalsIgnoreCase("0")) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage("select to license no",
								"select to license no"));

				validateInput1 = false;
			}

			else if (this.getVch_to_lic_no().equals("NA")) {
				ResourceUtil.addErrorMessage(Constants.SELECT_TO,
						Constants.SELECT_TO);
				validateInput1 = false;
			}
			/*
			 * } else if (isAuthNmflg()) { if
			 * (this.getExportLicenseNumber().equalsIgnoreCase("") ||
			 * this.getExportLicenseNumber().equalsIgnoreCase(null)) {
			 * FacesContext.getCurrentInstance().addMessage( null, new
			 * FacesMessage("Export License Number Required",
			 * "Export License Number Required")); } }
			 */
		} else if (this.getVch_to().equalsIgnoreCase("EXPORT")) {
			if (isAuthNmflg()) {
				if (!(Validate.validateStrReq("exportLicenseNo",
						this.getExportLicenseNumber())))
					validateInput1 = false;

				/*
				 * else if (!(Validate.validateStrReq("transportmode",
				 * this.getTransportMode()))) validateInput1 = false;
				 */

				/*
				 * else if (!(Validate.validateDoubleValue("feeexport",
				 * this.getFeeExport()))) validateInput1 = false;
				 */

				else if (!(Validate.validateDoubleValue("bondvalue",
						this.getBondValue())))
					validateInput1 = false;

				else if (!(Validate.validateStrReq("authname",
						this.getAuthName())))
					validateInput1 = false;
				else if (!(Validate.validateOnlyInt("SELECT_STATE",
						this.getDistrictLic())))
					validateInput1 = false;
			}
		}

		for (int i = 0; i < this.displaylist.size(); i++) {
			GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) displaylist
					.get(i);
			if (dt.getDispatchBoxes() > 0 && dt.getInt_dispatch() > 0) {
				if (dt.getBatchNo() == null || dt.getBatchNo().equals("")) {
					validateInput1 = false;
					FacesContext.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_ERROR,
											"Enter Batch No At Line No !!! "
													+ (i + 1),
											"Enter Batch No At Line No !!! "
													+ (i + 1)));
					break;
				}
			}
		}
		int sumBottles = 0;
		int sumBoxes = 0;
		for (int i = 0; i < this.displaylist.size(); i++) {
			GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) displaylist
					.get(i);
			sumBottles += dt.getInt_dispatch();
			sumBoxes += dt.getDispatchBoxes();
			if (dt.getDispatchBoxes() > dt.getBoxAvailable()) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Dispatch Boxes Should Be Less Than Available Boxes !!! ",
										"Dispatch Boxes Should Be Less Than Available Boxes !!!"));
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

	// ===========list import
	ArrayList fromliclist = new ArrayList();

	public ArrayList getFromliclist() {
		// this.fromliclist=impl.fromliclistImpl(this);

		return fromliclist;
	}

	public String fromListMethod(ValueChangeEvent vce) {
		try {
			this.db_total_value = 0;
			this.sum = 0;
			this.setSum(0.0);
			this.setDb_total_value(0.0);
			this.vch_to = null;
			Object obj = vce.getNewValue();
			String s = (String) obj;

			this.lic_disable_flag2 = false;

			if (s.equalsIgnoreCase("FL3A")) {
				this.vch_from = "FL3A";
				this.fromliclist = impl.fromliclistImpl(this);
				this.displaylist = impl.displaylistImpl(this, vch_from_lic_no);
			}

			else if (s.equalsIgnoreCase("FL3")) {
				this.vch_from = "FL3";
				this.fromliclist = impl.fromliclistImpl(this);

				this.displaylist = impl.displaylistImpl(this, vch_from_lic_no);
				// System.out.println("ijhfejrfeijfei");

			}

			else {
			}

		} catch (Exception e) {

		}

		return "";
	}

	public String listMethod(ValueChangeEvent vce) {
		String val = (String) vce.getNewValue();
		this.displaylist = impl.displaylistImpl(this, val);
		this.vch_from_lic_no = val;

		if (this.vch_from_lic_no==null || this.vch_from_lic_no.equalsIgnoreCase(""))
			this.lic_disable_flag2 = false;
		else
			this.lic_disable_flag2 = true;
		return "";
	}

	ArrayList toliclist = new ArrayList();

	public ArrayList getToliclist() {
		// this.toliclist=impl.toliclistImpl(this);
		return toliclist;
	}

	public String toListMethod(ValueChangeEvent vce) {
		try {
			this.db_total_value = 0;
			this.sum = 0;
			this.setSum(0.0);
			this.setDb_total_value(0.0);

			Object obj = vce.getNewValue();
			String s = (String) obj;

			this.lic_disable_flag = false;

			if (s.equalsIgnoreCase("FL1") || s.equalsIgnoreCase("FL1A")) {
				this.setWithflag(true);
				this.setUnderflag(false);
			}
			if (s.equalsIgnoreCase("FL2A") || s.equalsIgnoreCase("EXPORT")
					|| s.equalsIgnoreCase("EOI")) {
				this.setWithflag(false);
				this.setUnderflag(true);
			}
			if (s.equalsIgnoreCase("FL1") || s.equalsIgnoreCase("FL1A")
					|| s.equalsIgnoreCase("FL2A")) {
				this.setLicNoflg(true);
				this.setAuthNmflg(false);
			}
			if (s.equalsIgnoreCase("EXPORT")) {
				this.setLicNoflg(false);
				this.setAuthNmflg(true);
			}
			if (s.equalsIgnoreCase("EOI")) {
				this.setLicNoflg(false);
				this.setAuthNmflg(true);
			}
			if (s.equalsIgnoreCase("FL1A")) {
				this.vch_to = "FL1A";
				this.toliclist = impl.toliclistImpl1a(this);
				// this.displaylist= impl.displaylistImpl(this,vch_to_lic_no);
			}

			else if (s.equalsIgnoreCase("FL1")) {
				this.vch_to = "FL1";
				this.toliclist = impl.toliclistImpl(this);
				// this.displaylist= impl.displaylistImpl(this,vch_to_lic_no);

			}

			else if (s.equalsIgnoreCase("FL2A")) {
				this.vch_to = "FL2A";
				this.toliclist = impl.toliclistImpl2a(this);
				// this.displaylist= impl.displaylistImpl(this,vch_to_lic_no);

			}

		} catch (Exception e) {

		}

		return "";
	}

	// ------------display-------------------------------
	public ArrayList displaylist = new ArrayList();

	private boolean flgEOI;

	public boolean isFlgEOI() {
		return flgEOI;
	}

	public void setFlgEOI(boolean flgEOI) {
		this.flgEOI = flgEOI;
	}

	public ArrayList getDisplaylist() {

		return displaylist;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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
			// this.db_total_value=Math.round(this.getSum()*100.0)/100.0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this
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

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}

	/*
	 * public void calculateDuty(ActionEvent ae){ this.flag=true;
	 * //System.out.println("inside"); UIDataTable
	 * uiTable=(UIDataTable)ae.getComponent().getParent().getParent(); int
	 * rowId=uiTable.getRowIndex(); //System.out.println("rowid"+rowId);
	 * GatePassToWholesaleDataTable dt=(GatePassToWholesaleDataTable)
	 * this.displaylist.get(rowId);
	 * 
	 * //System.out.println("printty"+dt.getDb_duty()+
	 * "   "+dt.getInt_dispatch()); dt.setCalculated_duty (
	 * Math.round(((dt.getDb_duty()*dt.getInt_dispatch()))*100.0)/100.0 );
	 * 
	 * 
	 * 
	 * }
	 */

	public double db_total_value = 0;
	public double sum = 0;

	public void calculateTotalDuty(ActionEvent ae) {
		// System.out.println("inside2");
		if (isFlag()) {
			this.setSum(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this.displaylist
						.get(i);

				this.setSum(this.getSum() + dt.getCalculated_duty());

			}
		}
		this.flag = false;

	}

	public double sum_exp = 0;
	public double db_total_value_exp = 0;

	public double getDb_total_value_exp() {

		double duty = 0.0;
		try {
			// this.db_total_value=Math.round(this.getSum()*100.0)/100.0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this
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

	public double getSum_exp() {
		return sum_exp;
	}

	public void setSum_exp(double sum_exp) {
		this.sum_exp = sum_exp;
	}

	public void calculateTotalexpfee(ActionEvent ae) {
		// System.out.println("inside2");
		if (isFlag()) {
			this.setSum_exp(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this.displaylist
						.get(i);

				this.setSum_exp(this.getSum_exp() + dt.getExpfee());

			}
		}
		this.flag = false;

	}

	// ==================FOR ADDITIONAL TOTAL DUTY==========================

	public double db_total_add_value = 0;
	public double sum_add = 0;

	public void calculateTotalAddDuty(ActionEvent ae) {
		// System.out.println("inside2");
		if (isAddflag()) {
			this.setSum_add(0);
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this.displaylist
						.get(i);

				this.setSum_add(this.getSum_add() + dt.getCalculated_add_duty());

			}
		}
		this.addflag = false;

	}

	// ///////////////////////////////////////////////////////////////////

	public void setFromliclist(ArrayList fromliclist) {
		this.fromliclist = fromliclist;
	}

	public void setToliclist(ArrayList toliclist) {
		this.toliclist = toliclist;
	}

	public int getDist_id() {
		return dist_id;
	}

	public void setDist_id(int dist_id) {
		this.dist_id = dist_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public double getDb_total_value() {
		double duty = 0.0;
		try {
			// this.db_total_value=Math.round(this.getSum()*100.0)/100.0;
			for (int i = 0; i < this.displaylist.size(); i++) {
				GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this
						.getDisplaylist().get(i);
				duty += dt.getCalculated_duty();

			}
			db_total_value = (duty);
		} catch (Exception e) {
			// TODO: handle exception
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

	public String getVch_bond() {
		return vch_bond;
	}

	public void setVch_bond(String vch_bond) {
		this.vch_bond = vch_bond;
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

	// upload excel--------------------------------

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

			// System.out.println("filename" + FullfileName+
			// "---------------filename" + fileName);
			size = item.getFileSize();
			this.excelFilename = FullfileName;
			this.excelFilepath = path;

		} catch (Exception ee) {
			ee.printStackTrace();
			// System.out.println("exception in upload@");
		} finally {

		}

	}

	public String importExcel() {
		new GatePassToWholesaleImpl().saveExcelData(this);
		this.bottlingNotDoneFlag = false;
		this.gatePassFlag = true;
		this.scanUploadFlag = true;
		this.getVal = impl.getExcelData(this);
		/*
		 * this.tableFlag=true; this.submitFlag=false; this.cancelFlag=true;
		 * this.gatePassFlag=false; this.kindlyUploadFlag=false;
		 * this.uploaderFlag=false; // this.scanUploadFlag=true;
		 * 
		 * this.finalsubmit=true; this.uploaderFlag=false;
		 */

		return "";
	}

	public void setGetListWholesale(ArrayList getListWholesale) {
		this.getListWholesale = getListWholesale;
	}

	public void savebarcode() {
		try {

			impl.saveBarImpl(this);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ========================newly added=============================

	private String pdfName;
	private boolean myFlag;
	private Date printDate;
	private String printGatePassNo;

	public String getPrintGatePassNo() {
		return printGatePassNo;
	}

	public void setPrintGatePassNo(String printGatePassNo) {
		this.printGatePassNo = printGatePassNo;
	}

	public Date getPrintDate() {
		return printDate;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}

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

	private double grossWeight = 0;
	private double tierWeight = 0;

	private double netWeight = 0;

	public Date validtilldt_date;

	public Date getValidtilldt_date() {
		return validtilldt_date;
	}

	public void setValidtilldt_date(Date validtilldt_date) {
		this.validtilldt_date = validtilldt_date;
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

	public void printReport(ActionEvent e) {
		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this.getListWholesale
					.get(uiTable.getRowIndex());

			this.setPrintDate(dt.getDt_date());
			this.setPrintGatePassNo(dt.getVch_gatepass_no());
			if (dt.getVch_to().equalsIgnoreCase("EXPORT")
					|| dt.getVch_to().equalsIgnoreCase("EOI")) {

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
			} else {

				if (impl.printReport(this, dt) == true) {
					dt.setMyFlagDt(true);

				} else {

					dt.setMyFlagDt(false);

				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// ==========================code for csv===================================

	/*
	 * private String csvFilename; private String csvFilepath;
	 * 
	 * public String getCsvFilename() { return csvFilename; }
	 * 
	 * public void setCsvFilename(String csvFilename) { this.csvFilename =
	 * csvFilename; }
	 * 
	 * public String getCsvFilepath() { return csvFilepath; }
	 * 
	 * public void setCsvFilepath(String csvFilepath) { this.csvFilepath =
	 * csvFilepath; }
	 * 
	 * public void uploadCsv(UploadEvent event) {
	 * 
	 * try { int size = 0; int counter = 0; UploadItem item =
	 * event.getUploadItem();
	 * 
	 * String FullfileName = item.getFileName();
	 * 
	 * String path = item.getFile().getPath();
	 * 
	 * String fileName = FullfileName.substring(FullfileName .lastIndexOf("\\")
	 * + 1);
	 * 
	 * ExternalContext con = FacesContext.getCurrentInstance()
	 * .getExternalContext();
	 * 
	 * ServletContext sCon = (ServletContext) con.getContext();
	 * 
	 * System.out.println("filename" + FullfileName + "---------------filename"
	 * + fileName); size = item.getFileSize();
	 * this.setCsvFilename(FullfileName); this.setCsvFilepath(path);
	 * 
	 * } catch (Exception ee) { ee.printStackTrace();
	 * System.out.println("exception in upload@"); } finally {
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public String csvSubmit() throws IOException { new
	 * GatePassToWholesaleImpl().saveCSV(this);
	 * System.out.println("hhiiiiiiiiii"); return ""; }
	 */

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
			GatePassToWholesaleDataTable dt = (GatePassToWholesaleDataTable) this.getListWholesale
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

	private boolean lic_disable_flag;
	private boolean lic_disable_flag2;

	public boolean isLic_disable_flag2() {
		return lic_disable_flag2;
	}

	public void setLic_disable_flag2(boolean lic_disable_flag2) {
		this.lic_disable_flag2 = lic_disable_flag2;
	}

	public boolean isLic_disable_flag() {
		return lic_disable_flag;
	}

	public void setLic_disable_flag(boolean lic_disable_flag) {
		this.lic_disable_flag = lic_disable_flag;
	}

	public void lic_change(ValueChangeEvent e) {

		this.vch_to_lic_no = (String) e.getNewValue();

		if (this.vch_to_lic_no==null || this.vch_to_lic_no.equalsIgnoreCase("0"))
			this.lic_disable_flag = false;
		else
			this.lic_disable_flag = true;

	}

	public void lic_change2(ValueChangeEvent e) {

	}
}
