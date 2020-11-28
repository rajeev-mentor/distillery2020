package com.mentor.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.Datatable.LiquorVehicleAccidentalCaseBWFLDT;
import com.mentor.Datatable.LiquorVehicleAccidentalCaseBWFLDT;
import com.mentor.impl.LiquorVehicleAccidentalCaseBWFLImpl;
import com.mentor.impl.LiquorVehicleAccidentalCaseBWFLImpl;
import com.mentor.utility.Constants;

public class LiquorVehicleAccidentalCaseBWFLAction {

	LiquorVehicleAccidentalCaseBWFLImpl impl = new LiquorVehicleAccidentalCaseBWFLImpl();
	private int distillery_id, district_id, req_id;
	private String gatepass_type, license_no, licensee_name, licensee_add, route_detail, vehicle_no, driver_name, agency_name, radio="N";
	private String gatepass_no,upload2,pdf_name, printGatePassNo,return_route_detail, return_vehicle_no, return_driver_name, return_agency_name;
	private Date gatepass_date;
	private String accident_address;
	private String accident_district_name,vch_from;
	private Date accident_date;
	private String hidden;
	private String name;
	private String address;
	private ArrayList display_list = new ArrayList();
	private ArrayList gatepass_list = new ArrayList();
	private ArrayList brand_list = new ArrayList();
	private boolean viewflag, doc3upload1,pdfUploaderFlag1,flagupload1,printFlag,flag=true,retrun_flag,gatePassFlag,tableFlag,bottlingNotDoneFlag;
	private Date validity_date,return_validity_date;
	private String csvFilename,scanGatePassNo;
	private String csvFilepath;
	private String exclcsv;
	private boolean valFlag,scanFlag;
	public ArrayList getVal = new ArrayList();
	
	public boolean isScanFlag() {
		return scanFlag;
	}
	public void setScanFlag(boolean scanFlag) {
		this.scanFlag = scanFlag;
	}
	public String getVch_from() {
		return vch_from;
	}
	public void setVch_from(String vch_from) {
		this.vch_from = vch_from;
	}
	public String getScanGatePassNo() {
		return scanGatePassNo;
	}
	public void setScanGatePassNo(String scanGatePassNo) {
		this.scanGatePassNo = scanGatePassNo;
	}
	public boolean isValFlag() {
		return valFlag;
	}
	public void setValFlag(boolean valFlag) {
		this.valFlag = valFlag;
	}
	public ArrayList getGetVal() {
		return getVal;
	}
	public void setGetVal(ArrayList getVal) {
		this.getVal = getVal;
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
	public String getExclcsv() {
		return exclcsv;
	}
	public void setExclcsv(String exclcsv) {
		this.exclcsv = exclcsv;
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
	public boolean isBottlingNotDoneFlag() {
		return bottlingNotDoneFlag;
	}
	public void setBottlingNotDoneFlag(boolean bottlingNotDoneFlag) {
		this.bottlingNotDoneFlag = bottlingNotDoneFlag;
	}
	public boolean isRetrun_flag() {
		return retrun_flag;
	}
	public void setRetrun_flag(boolean retrun_flag) {
		this.retrun_flag = retrun_flag;
	}
	public Date getReturn_validity_date() {
		return return_validity_date;
	}
	public void setReturn_validity_date(Date return_validity_date) {
		this.return_validity_date = return_validity_date;
	}
	public String getReturn_route_detail() {
		return return_route_detail;
	}
	public void setReturn_route_detail(String return_route_detail) {
		this.return_route_detail = return_route_detail;
	}
	public String getReturn_vehicle_no() {
		return return_vehicle_no;
	}
	public void setReturn_vehicle_no(String return_vehicle_no) {
		this.return_vehicle_no = return_vehicle_no;
	}
	public String getReturn_driver_name() {
		return return_driver_name;
	}
	public void setReturn_driver_name(String return_driver_name) {
		this.return_driver_name = return_driver_name;
	}
	public String getReturn_agency_name() {
		return return_agency_name;
	}
	public void setReturn_agency_name(String return_agency_name) {
		this.return_agency_name = return_agency_name;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getPrintGatePassNo() {
		return printGatePassNo;
	}
	public void setPrintGatePassNo(String printGatePassNo) {
		this.printGatePassNo = printGatePassNo;
	}
	public boolean isPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(boolean printFlag) {
		this.printFlag = printFlag;
	}
	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		this.upload2 = upload2;
	}
	public String getPdf_name() {
		return pdf_name;
	}
	public void setPdf_name(String pdf_name) {
		this.pdf_name = pdf_name;
	}
	public boolean isDoc3upload1() {
		return doc3upload1;
	}
	public void setDoc3upload1(boolean doc3upload1) {
		this.doc3upload1 = doc3upload1;
	}
	public boolean isPdfUploaderFlag1() {
		return pdfUploaderFlag1;
	}
	public void setPdfUploaderFlag1(boolean pdfUploaderFlag1) {
		this.pdfUploaderFlag1 = pdfUploaderFlag1;
	}
	public boolean isFlagupload1() {
		return flagupload1;
	}
	public void setFlagupload1(boolean flagupload1) {
		this.flagupload1 = flagupload1;
	}
	public Date getValidity_date() {
		return validity_date;
	}
	public void setValidity_date(Date validity_date) {
		this.validity_date = validity_date;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getLicense_no() {
		return license_no;
	}
	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}
	public String getLicensee_name() {
		return licensee_name;
	}
	public void setLicensee_name(String licensee_name) {
		this.licensee_name = licensee_name;
	}
	public String getLicensee_add() {
		return licensee_add;
	}
	public void setLicensee_add(String licensee_add) {
		this.licensee_add = licensee_add;
	}
	public String getRoute_detail() {
		return route_detail;
	}
	public void setRoute_detail(String route_detail) {
		this.route_detail = route_detail;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getAgency_name() {
		return agency_name;
	}
	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}
	public boolean isViewflag() {
		return viewflag;
	}
	public void setViewflag(boolean viewflag) {
		this.viewflag = viewflag;
	}
	public int getReq_id() {
		return req_id;
	}
	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}
	public int getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(int district_id) {
		this.district_id = district_id;
	}
	public ArrayList getGatepass_list() {
		return gatepass_list;
	}
	public void setGatepass_list(ArrayList gatepass_list) {
		this.gatepass_list = gatepass_list;
	}
	public ArrayList getBrand_list() {
		return brand_list;
	}
	public void setBrand_list(ArrayList brand_list) {
		this.brand_list = brand_list;
	}
	public String getAccident_district_name() {
		return accident_district_name;
	}
	public void setAccident_district_name(String accident_district_name) {
		this.accident_district_name = accident_district_name;
	}


	
	public int getDistillery_id() {
		return distillery_id;
	}
	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
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
	
	public Date getAccident_date() {
		return accident_date;
	}
	public void setAccident_date(Date accident_date) {
		this.accident_date = accident_date;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
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
	public ArrayList getDisplay_list() {
		if(this.flag==true){
		impl.getDetails(this);
		this.display_list=impl.display_detail(this);
		this.flag=false;
		}
		return display_list;
	}
	public void setDisplay_list(ArrayList display_list) {
		this.display_list = display_list;
	}
	public void doc3uploadMethod(UploadEvent event) throws Exception {

		try {

			UploadItem item = event.getUploadItem();
			String FullfileName = item.getFileName();
			this.upload2 = item.getFile().getPath();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

	}
	
	

	public void pdf31() {
		
		Random rand = new Random();
		int n = rand.nextInt(99999) + 1;
		

		try {
			
			String mypath = Constants.JBOSS_SERVER_PATH
					+ Constants.JBOSS_LINX_PATH + File.separator
					+ "ExciseUp" + File.separator + "WholeSale"+ File.separator + "pdf";
			InputStream io = new FileInputStream(this.upload2);

			String recordFile = this.gatepass_no+"inpection_bwfl.pdf";
					

			this.pdf_name = recordFile;

			FileOutputStream out = new FileOutputStream(mypath
					+ File.separator + recordFile);

			BufferedOutputStream outb = new BufferedOutputStream(out);
			int z = 0;
			while ((z = io.read()) != -1) {
				outb.write(z);
				doc3upload1 = true;
	            this.pdfUploaderFlag1=true;
			}
			//System.out.println("doc3 uploaded success fully");
			this.flagupload1 = false;
			outb.flush();
			outb.close();
			io.close();
			this.upload2 = "";
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void listener(ValueChangeEvent e){
		this.flag=true;
	}
	
	
	public void view(ActionEvent e){
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		LiquorVehicleAccidentalCaseBWFLDT dt = (LiquorVehicleAccidentalCaseBWFLDT) this.display_list.get(uiTable.getRowIndex());
		
		this.setGatepass_type(dt.getGatepass_type());
		this.setGatepass_no(dt.getGatepass_no());
		this.setGatepass_date(dt.getGatepass_date());
		this.setAccident_address(dt.getAccident_address());
		this.setAccident_date(dt.getAccident_date());
		this.distillery_id = dt.getDistillery_id();
		this.req_id = dt.getReq_id();
		this.viewflag = true;
		this.pdfUploaderFlag1=false;
		this.doc3upload1=false;
		this.gatepass_list = impl.gatepass_detail(this);
		this.brand_list = impl.brand_detail(this);
	}
	
	public void print(ActionEvent e){
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		LiquorVehicleAccidentalCaseBWFLDT dt = (LiquorVehicleAccidentalCaseBWFLDT) this.display_list.get(uiTable.getRowIndex());
		if(impl.printReport(this,dt)){
			dt.setPrintFlag(true);
		}else{
			dt.setPrintFlag(false);
		}
	}
	
	public void scanUpload(ActionEvent e){
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		LiquorVehicleAccidentalCaseBWFLDT dt = (LiquorVehicleAccidentalCaseBWFLDT) this.display_list.get(uiTable.getRowIndex());
		this.scanGatePassNo = dt.getGatepass_no()+"-Accidental";
		this.gatepass_no = dt.getGatepass_no();
		this.gatepass_type=dt.getGatepass_type();
		this.vch_from = dt.getVch_from();
		this.scanFlag=true;
		this.gatePassFlag = true;
		this.tableFlag = true;
		
		this.getVal = impl.getExcelData(this);
	}
	
	public void save(){
		if(this.validity_date!=null){
			if(this.doc3upload1){
		       if(impl.SaveImpl(this)){
		    	   this.viewflag=false;
		    	   this.flag=true;
		       }
			}else {

				FacesContext
				.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR,
								"Please Upload Inpection Report !",
								"Please Upload Inpection Report !"));
			
			}
		}else {

			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Select Validity Date !",
							"Select Validity Date !"));
		
		}
	}
	
public void back(){
		this.viewflag=false;
		this.scanFlag=false;
	}
public void returnBack(){
	this.retrun_flag=false;
	this.printFlag=false;
}

public void printRetrnGatepass(ActionEvent e){
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
	LiquorVehicleAccidentalCaseBWFLDT dt = (LiquorVehicleAccidentalCaseBWFLDT) this.display_list.get(uiTable.getRowIndex());
	this.gatepass_no=dt.getGatepass_no();
	this.setRetrun_flag(true);
	this.return_agency_name=null;
	this.return_driver_name=null;
	this.return_route_detail=null;
	this.return_validity_date=null;
	this.return_vehicle_no=null;
}
public void printReturn(){
	if(this.return_validity_date!=null){
		impl.UpadteReturnImpl(this);
	    impl.printReturnReport(this);
		
	}else{
		FacesContext
		.getCurrentInstance()
		.addMessage(
				null,
				new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"All Fields Are Required !",
						"All Fields Are Required !"));
	}
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
	
	impl.updateData(this);
	impl.saveCSV(this);
	this.bottlingNotDoneFlag=false;
	this.gatePassFlag = true;
	this.tableFlag = true;
	
	this.getVal = impl.getExcelData(this);
	
	
	return "";
}

public void finalSubmit() {

	if (impl.excelCases(this) == impl.recieveCases(this)) {
		if (bottlingNotDoneFlag == false) {
		if (impl.updateFL3(this) == true) {
			
			this.tableFlag = false;
			this.gatePassFlag = false;
			this.display_list=impl.display_detail(this);
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

public void delete() {

	impl.deleteData(this);
	this.tableFlag = false;
	/*this.finalsubmit = false;
	this.cancelFlag = false;
	this.gatePassFlag = false;
	this.kindlyUploadFlag = false;
	this.uploaderFlag = false;
	this.submitFlag = false;*/
}

}
