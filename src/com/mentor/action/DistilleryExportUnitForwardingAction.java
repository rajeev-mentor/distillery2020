package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;

import com.mentor.Datatable.DistilleryExportUnitForwardingDT;
import com.mentor.impl.DistilleryExportUnitForwardingImpl;
import com.mentor.utility.ResourceUtil;

public class DistilleryExportUnitForwardingAction {
	DistilleryExportUnitForwardingImpl impl = new DistilleryExportUnitForwardingImpl();
	
//=========================================== Variables ============
	
private String icd_id ;
private int app_id;
private String importer_exporter_code ;
private Date icd_certificate_issue_date ;
private String upload_certificate_export_1 ;
private String regis_cum_membership_no ;
private Date membership_certificate_issue_date ;
private String upload_rcmc_2 ;
private String  house_no ;
private Date export_certificate_issue_date ;
private String upload_certi_export_3 ;
private String gstin_no ;
private ArrayList icd_list = new ArrayList();
public boolean doc1upload = false;
private String upload1 = null;
private boolean flagupload ;
public boolean doc2upload = false;
private ArrayList display_detail = new ArrayList();
private String hidden;
private long distillery_id ;
private String distillery_name ;
private String user1_remark;
private String user2_remark;
private String user3_remark;
private String user4_remark;
private String remark;
private String radio="N";
private boolean user4_flag;
private boolean raise_flg;
private String objection_for;
private String obj_Description;
private String popup4Hidden;
private String popup4ObjectedFor;
private String popup4ActionTaken;
private String popup4ObjectedPdf="/doc";
private int Popup4objID;
private boolean viewpdfFlg;
private boolean viewFlag;
private ArrayList export_order_list = new ArrayList();
private ArrayList bank_detail = new ArrayList();
private boolean doc3upload;
private Date app_date;
//==============================Getter setter================

public String getIcd_id() {
	return icd_id;
}
public Date getApp_date() {
	return app_date;
}
public void setApp_date(Date app_date) {
	this.app_date = app_date;
}
public boolean isDoc3upload() {
	return doc3upload;
}
public void setDoc3upload(boolean doc3upload) {
	this.doc3upload = doc3upload;
}
public ArrayList getBank_detail() {
	return bank_detail;
}
public void setBank_detail(ArrayList bank_detail) {
	this.bank_detail = bank_detail;
}
public ArrayList getExport_order_list() {
	this.export_order_list=impl.export_order_display_detail(this);
	return export_order_list;
}
public void setExport_order_list(ArrayList export_order_list) {
	this.export_order_list = export_order_list;
}
public int getPopup4objID() {
	return Popup4objID;
}
public void setPopup4objID(int popup4objID) {
	Popup4objID = popup4objID;
}
public boolean isViewFlag() {
	return viewFlag;
}
public void setViewFlag(boolean viewFlag) {
	this.viewFlag = viewFlag;
}
public int getApp_id() {
	return app_id;
}
public void setApp_id(int app_id) {
	this.app_id = app_id;
}
public boolean isViewpdfFlg() {
	return viewpdfFlg;
}
public void setViewpdfFlg(boolean viewpdfFlg) {
	this.viewpdfFlg = viewpdfFlg;
}
public String getObjection_for() {
	return objection_for;
}
public void setObjection_for(String objection_for) {
	this.objection_for = objection_for;
}
public String getObj_Description() {
	return obj_Description;
}
public void setObj_Description(String obj_Description) {
	this.obj_Description = obj_Description;
}
public String getPopup4Hidden() {
	impl.getReplies(this);
	return popup4Hidden;
}
public void setPopup4Hidden(String popup4Hidden) {
	this.popup4Hidden = popup4Hidden;
}
public String getPopup4ObjectedFor() {
	return popup4ObjectedFor;
}
public void setPopup4ObjectedFor(String popup4ObjectedFor) {
	this.popup4ObjectedFor = popup4ObjectedFor;
}
public String getPopup4ActionTaken() {
	return popup4ActionTaken;
}
public void setPopup4ActionTaken(String popup4ActionTaken) {
	this.popup4ActionTaken = popup4ActionTaken;
}
public String getPopup4ObjectedPdf() {
	return popup4ObjectedPdf;
}
public void setPopup4ObjectedPdf(String popup4ObjectedPdf) {
	this.popup4ObjectedPdf = popup4ObjectedPdf;
}
public boolean isRaise_flg() {
	return raise_flg;
}
public void setRaise_flg(boolean raise_flg) {
	this.raise_flg = raise_flg;
}
public boolean isUser4_flag() {
	return user4_flag;
}
public void setUser4_flag(boolean user4_flag) {
	this.user4_flag = user4_flag;
}
public String getRadio() {
	return radio;
}
public void setRadio(String radio) {
	this.radio = radio;
}
public String getUser1_remark() {
	return user1_remark;
}
public void setUser1_remark(String user1_remark) {
	this.user1_remark = user1_remark;
}
public String getUser2_remark() {
	return user2_remark;
}
public void setUser2_remark(String user2_remark) {
	this.user2_remark = user2_remark;
}
public String getUser3_remark() {
	return user3_remark;
}
public void setUser3_remark(String user3_remark) {
	this.user3_remark = user3_remark;
}
public String getUser4_remark() {
	return user4_remark;
}
public void setUser4_remark(String user4_remark) {
	this.user4_remark = user4_remark;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public void setIcd_id(String icd_id) {
	this.icd_id = icd_id;
}
public String getImporter_exporter_code() {
	return importer_exporter_code;
}
public void setImporter_exporter_code(String importer_exporter_code) {
	this.importer_exporter_code = importer_exporter_code;
}
public Date getIcd_certificate_issue_date() {
	return icd_certificate_issue_date;
}
public void setIcd_certificate_issue_date(Date icd_certificate_issue_date) {
	this.icd_certificate_issue_date = icd_certificate_issue_date;
}
public String getUpload_certificate_export_1() {
	return upload_certificate_export_1;
}
public void setUpload_certificate_export_1(String upload_certificate_export_1) {
	this.upload_certificate_export_1 = upload_certificate_export_1;
}
public String getRegis_cum_membership_no() {
	return regis_cum_membership_no;
}
public void setRegis_cum_membership_no(String regis_cum_membership_no) {
	this.regis_cum_membership_no = regis_cum_membership_no;
}
public Date getMembership_certificate_issue_date() {
	return membership_certificate_issue_date;
}
public void setMembership_certificate_issue_date(
		Date membership_certificate_issue_date) {
	this.membership_certificate_issue_date = membership_certificate_issue_date;
}
public String getUpload_rcmc_2() {
	return upload_rcmc_2;
}
public void setUpload_rcmc_2(String upload_rcmc_2) {
	this.upload_rcmc_2 = upload_rcmc_2;
}
public String getHouse_no() {
	return house_no;
}
public void setHouse_no(String house_no) {
	this.house_no = house_no;
}
public Date getExport_certificate_issue_date() {
	return export_certificate_issue_date;
}
public void setExport_certificate_issue_date(Date export_certificate_issue_date) {
	this.export_certificate_issue_date = export_certificate_issue_date;
}
public String getUpload_certi_export_3() {
	return upload_certi_export_3;
}
public void setUpload_certi_export_3(String upload_certi_export_3) {
	this.upload_certi_export_3 = upload_certi_export_3;
}
public String getGstin_no() {
	return gstin_no;
}
public void setGstin_no(String gstin_no) {
	this.gstin_no = gstin_no;
}
public ArrayList getIcd_list() {
	return icd_list;
}
public void setIcd_list(ArrayList icd_list) {
	this.icd_list = icd_list;
}
public boolean isDoc1upload() {
	return doc1upload;
}
public void setDoc1upload(boolean doc1upload) {
	this.doc1upload = doc1upload;
}
public String getUpload1() {
	return upload1;
}
public void setUpload1(String upload1) {
	this.upload1 = upload1;
}
public boolean isFlagupload() {
	return flagupload;
}
public void setFlagupload(boolean flagupload) {
	this.flagupload = flagupload;
}
public boolean isDoc2upload() {
	return doc2upload;
}
public void setDoc2upload(boolean doc2upload) {
	this.doc2upload = doc2upload;
}
public ArrayList getDisplay_detail() {
	return display_detail;
}
public void setDisplay_detail(ArrayList display_detail) {
	this.display_detail = display_detail;
}
public String getHidden() {
	if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){
		user4_flag=true;
	}else {
		user4_flag=false;
	}
	
	if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-DEC") || ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-AC-License")){
		raise_flg=true;
	}else {
		raise_flg=false;
	}
	return hidden;
}
public void setHidden(String hidden) {
	this.hidden = hidden;
}
public long getDistillery_id() {
	return distillery_id;
}
public void setDistillery_id(long distillery_id) {
	this.distillery_id = distillery_id;
}
public String getDistillery_name() {
	return distillery_name;
}
public void setDistillery_name(String distillery_name) {
	this.distillery_name = distillery_name;
}
public boolean isBank_flag() {
	return bank_flag;
}
public void setBank_flag(boolean bank_flag) {
	this.bank_flag = bank_flag;
}
private boolean bank_flag ;

//================================Methods===============================

public void forward(){
	
	if(this.remark!=null && this.remark.length()>0){
	if(impl.updateDetails(this)){
		this.remark=null;
	}
	}else{
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please fill the remark !","Please fill the remark !"));
	}
	
	
}

public void approve(){
	
	if(this.remark!=null && this.remark.length()>0){
	if(impl.approveimpl(this)){
		this.remark=null;
	}
	}else{
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please fill the remark !","Please fill the remark !"));
	}
	
}

   public void reject(){
	   if(this.remark!=null && this.remark.length()>0){
	   if(impl.rejectimpl(this)){
		this.remark=null;
	}
   }else{
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please fill the remark !","Please fill the remark !"));
	}
   }


  public void agreeReply(){
	  try {
		impl.agreeReplyImpl(this);
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  public void declineReply(){
	  try {
		impl.declineReplyImpl(this);
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  public void close(){
		this.viewFlag=false;
  }
  
  public void objection(){
	  if(this.objection_for!=null && this.objection_for.length()>0 && this.obj_Description !=null && this.obj_Description.length()>0){
	   impl.save_Objection(this);
	  }else{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please fill the objection title and objection description!",
							"Please fill the objection title and objection description !"));
		}
  }
public void view(ActionEvent ae){
	UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent().getParent();
	int rowId = uiTable.getRowIndex();

	DistilleryExportUnitForwardingDT dt = (DistilleryExportUnitForwardingDT) this.export_order_list.get(rowId);
	
	this.distillery_name=dt.getDistillery_name();
	this.viewFlag=true;
	this.distillery_id=dt.getDistillery_id();
	this.app_id=dt.getApp_no();
	this.app_date=dt.getApp_date();
	impl.getShowDetails(this);
	this.bank_detail=impl.Bank_detail(this);
	this.display_detail=impl.display_detail(this);
	
}


	public void clickRadio(ValueChangeEvent e){
		String val = (String) e.getNewValue();
		this.radio=val;
		this.export_order_list=impl.export_order_display_detail(this);
	}

}



