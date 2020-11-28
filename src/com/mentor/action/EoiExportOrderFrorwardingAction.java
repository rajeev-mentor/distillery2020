package com.mentor.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.Datatable.EoiExportOrderFrorwardingDT;
import com.mentor.Datatable.eoi_app_exporder_dt;
import com.mentor.impl.EoiExportOrderFrorwardingImpl;
import com.mentor.utility.Constants;
import com.mentor.utility.ResourceUtil;

public class EoiExportOrderFrorwardingAction {

	//===========================================variable ===============================================
	private String import_order_no ;
	private String import_order_date ;
	private String name_of_importing_unit ;
	private String icd_id ;
	private String performa_invoice_no ;
	private String upload_performa_invoice ;
	private String validity_demanded ;
	private ArrayList icd_list = new ArrayList();
	private ArrayList brand_detaiil_list = new ArrayList();	
	private ArrayList export_order_list = new ArrayList();
	private ArrayList import_order_no_list = new ArrayList();
	private String hidden ;
	private int distillery_id ;
	private boolean validate_input ;
	private boolean flag_brand_table ;
	private String radio="N";
	private int app_id, seq_pk;
	private String app_date;
	private String rvrtRemark;
	private String remark;
	private String objection_for;
	private String obj_Description;
	private String popup4Hidden;
	private String popup4ObjectedFor;
	private String popup4ActionTaken;
	private String popup4ObjectedPdf;
	private boolean viewpdfFlg;
	private int popup4objID;
	private boolean revertFlg;
	private boolean raise_flg;
	private boolean user4_flag;
	private boolean viewFlag;
	private String icd_name;
	private String user1_remark;
	private String user2_remark;
	private String user3_remark;
	private String user4_remark;
	private String leo_no;
	private String brc_no;
	private String total_bottles;
	private String icd_reciept="NA";
	private String distillery_name;
	private String user_revert_remark;
	private boolean user_revert_flag;
	private String pi_pdf;
	private boolean ds_flag;
	private String mainServiceId;private String maincntrlId;private String mainunitId;
	 private String requestId, purcahse_order_no, purcahse_order_pdf, imp_exp_no, imp_exp_pdf, rcm_no, rcm_pdf, house_no, house_pdf,puc_no, puc_pdf;
	 
	 
	public int getSeq_pk() {
		return seq_pk;
	}

	public void setSeq_pk(int seq_pk) {
		this.seq_pk = seq_pk;
	}

	public String getPuc_no() {
		return puc_no;
	}

	public void setPuc_no(String puc_no) {
		this.puc_no = puc_no;
	}

	public String getPuc_pdf() {
		return puc_pdf;
	}

	public void setPuc_pdf(String puc_pdf) {
		this.puc_pdf = puc_pdf;
	}

	public String getPurcahse_order_no() {
		return purcahse_order_no;
	}

	public void setPurcahse_order_no(String purcahse_order_no) {
		this.purcahse_order_no = purcahse_order_no;
	}

	public String getPurcahse_order_pdf() {
		return purcahse_order_pdf;
	}

	public void setPurcahse_order_pdf(String purcahse_order_pdf) {
		this.purcahse_order_pdf = purcahse_order_pdf;
	}

	public String getImp_exp_no() {
		return imp_exp_no;
	}

	public void setImp_exp_no(String imp_exp_no) {
		this.imp_exp_no = imp_exp_no;
	}

	public String getImp_exp_pdf() {
		return imp_exp_pdf;
	}

	public void setImp_exp_pdf(String imp_exp_pdf) {
		this.imp_exp_pdf = imp_exp_pdf;
	}

	public String getRcm_no() {
		return rcm_no;
	}

	public void setRcm_no(String rcm_no) {
		this.rcm_no = rcm_no;
	}

	public String getRcm_pdf() {
		return rcm_pdf;
	}

	public void setRcm_pdf(String rcm_pdf) {
		this.rcm_pdf = rcm_pdf;
	}

	public String getHouse_no() {
		return house_no;
	}

	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}

	public String getHouse_pdf() {
		return house_pdf;
	}

	public void setHouse_pdf(String house_pdf) {
		this.house_pdf = house_pdf;
	}

	public String getMainServiceId() {
		return mainServiceId;
	}

	public void setMainServiceId(String mainServiceId) {
		this.mainServiceId = mainServiceId;
	}

	public String getMaincntrlId() {
		return maincntrlId;
	}

	public void setMaincntrlId(String maincntrlId) {
		this.maincntrlId = maincntrlId;
	}

	public String getMainunitId() {
		return mainunitId;
	}

	public void setMainunitId(String mainunitId) {
		this.mainunitId = mainunitId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public boolean isFlag_brand_table() {
		return flag_brand_table;
	}

	public void setFlag_brand_table(boolean flag_brand_table) {
		this.flag_brand_table = flag_brand_table;
	}

	EoiExportOrderFrorwardingImpl  impl = new EoiExportOrderFrorwardingImpl();
	
	//=========================================  Getter and setter  ========================================
	
	
	


	public boolean isValidate_input() {
		
		validate_input = true ;
		
		if (validate_input) {
			if (this.icd_id == null || this.import_order_no.length() == 0  || this.import_order_no.trim().equalsIgnoreCase("")) {
				this.validate_input = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Please Enter ICD ID"));
			} else if (this.import_order_no == null  || this.import_order_no.length() == 0  || this.import_order_no.trim().equalsIgnoreCase("")) {
				this.validate_input = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Please Enter Importer Exporter Code"));
			}
		    else if (this.performa_invoice_no == null || this.performa_invoice_no.length() == 0 || this.performa_invoice_no.trim().equalsIgnoreCase("")) {
				this.validate_input = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Please Enter Performa Invoice No."));
			} else if (this.upload_performa_invoice == null || this.upload_performa_invoice.length() == 0 || this.upload_performa_invoice.trim().equalsIgnoreCase("")) {
				this.validate_input = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Please Upload Performa pdf Copy"));
			} else if (this.validity_demanded == null) {
				this.validate_input = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Upload Validity Demanded "));
			}
			else if(this.getBrand_detaiil_list().size()>0){
				for (int i = 0; i < this.getBrand_detaiil_list().size(); i++) {
					eoi_app_exporder_dt dt = new eoi_app_exporder_dt();
				dt = (eoi_app_exporder_dt) this.getBrand_detaiil_list().get(i);
				
				 System.out.println("---validate enter in for loop  ===");
				if(dt.getRequested_qty() > dt.getBalance_qty()){
					this.validate_input = false;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Please Enter Requested Qty less than Balance Qty."));
					
				}			 
				
					}
				}

		}
		
		return validate_input;
	}

	public boolean isDs_flag() {
		return ds_flag;
	}

	public void setDs_flag(boolean ds_flag) {
		this.ds_flag = ds_flag;
	}

	public String getPi_pdf() {
		return pi_pdf;
	}

	public void setPi_pdf(String pi_pdf) {
		this.pi_pdf = pi_pdf;
	}

	public String getUser_revert_remark() {
		return user_revert_remark;
	}

	public void setUser_revert_remark(String user_revert_remark) {
		this.user_revert_remark = user_revert_remark;
	}

	public boolean isUser_revert_flag() {
		return user_revert_flag;
	}

	public void setUser_revert_flag(boolean user_revert_flag) {
		this.user_revert_flag = user_revert_flag;
	}

	public String getImport_order_date() {
		return import_order_date;
	}

	public String getValidity_demanded() {
		return validity_demanded;
	}

	public void setImport_order_date(String import_order_date) {
		this.import_order_date = import_order_date;
	}

	public void setValidity_demanded(String validity_demanded) {
		this.validity_demanded = validity_demanded;
	}

	public String getDistillery_name() {
		return distillery_name;
	}

	public void setDistillery_name(String distillery_name) {
		this.distillery_name = distillery_name;
	}

	public String getLeo_no() {
		return leo_no;
	}

	public void setLeo_no(String leo_no) {
		this.leo_no = leo_no;
	}

	public String getBrc_no() {
		return brc_no;
	}

	public void setBrc_no(String brc_no) {
		this.brc_no = brc_no;
	}

	public String getTotal_bottles() {
		return total_bottles;
	}

	public void setTotal_bottles(String total_bottles) {
		this.total_bottles = total_bottles;
	}

	public String getIcd_reciept() {
		return icd_reciept;
	}

	public void setIcd_reciept(String icd_reciept) {
		this.icd_reciept = icd_reciept;
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

	public String getIcd_name() {
		return icd_name;
	}

	public void setIcd_name(String icd_name) {
		this.icd_name = icd_name;
	}

	public boolean isViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(boolean viewFlag) {
		this.viewFlag = viewFlag;
	}

	public boolean isUser4_flag() {
		return user4_flag;
	}

	public void setUser4_flag(boolean user4_flag) {
		this.user4_flag = user4_flag;
	}

	public boolean isRevertFlg() {
		return revertFlg;
	}

	public void setRevertFlg(boolean revertFlg) {
		this.revertFlg = revertFlg;
	}

	public boolean isRaise_flg() {
		return raise_flg;
	}

	public void setRaise_flg(boolean raise_flg) {
		this.raise_flg = raise_flg;
	}

	public int getPopup4objID() {
		return popup4objID;
	}

	public void setPopup4objID(int popup4objID) {
		this.popup4objID = popup4objID;
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

	public boolean isViewpdfFlg() {
		return viewpdfFlg;
	}

	public void setViewpdfFlg(boolean viewpdfFlg) {
		this.viewpdfFlg = viewpdfFlg;
	}

	public String getRvrtRemark() {
		return rvrtRemark;
	}

	public void setRvrtRemark(String rvrtRemark) {
		this.rvrtRemark = rvrtRemark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getApp_id() {
		return app_id;
	}

	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}

	public String getApp_date() {
		return app_date;
	}

	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}

	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public void setValidate_input(boolean validate_input) {
		this.validate_input = validate_input;
	}


	public int getDistillery_id() {
		return distillery_id;
	}


	public void setDistillery_id(int distillery_id) {
		this.distillery_id = distillery_id;
	}



	public ArrayList getImport_order_no_list() {
		this.import_order_no_list = impl.import_list(this);
		return import_order_no_list;
	}


	public void setImport_order_no_list(ArrayList import_order_no_list) {
		this.import_order_no_list = import_order_no_list;
	}


	public String getImport_order_no() {
		return import_order_no;
	}


	public void setImport_order_no(String import_order_no) {
		this.import_order_no = import_order_no;
	}



	public String getName_of_importing_unit() {
		return name_of_importing_unit;
	}


	public void setName_of_importing_unit(String name_of_importing_unit) {
		this.name_of_importing_unit = name_of_importing_unit;
	}


	public String getIcd_id() {
		return icd_id;
	}


	public void setIcd_id(String icd_id) {
		this.icd_id = icd_id;
	}


	public String getPerforma_invoice_no() {
		return performa_invoice_no;
	}


	public void setPerforma_invoice_no(String performa_invoice_no) {
		this.performa_invoice_no = performa_invoice_no;
	}


	public String getUpload_performa_invoice() {
		return upload_performa_invoice;
	}


	public void setUpload_performa_invoice(String upload_performa_invoice) {
		this.upload_performa_invoice = upload_performa_invoice;
	}



	public ArrayList getIcd_list() {
		this.icd_list = impl.getIcd_list(this);
		return icd_list;
	}


	public void setIcd_list(ArrayList icd_list) {
		this.icd_list = icd_list;
	}


	public ArrayList getBrand_detaiil_list() {		
		return brand_detaiil_list;
	}


	public void setBrand_detaiil_list(ArrayList brand_detaiil_list) {
		this.brand_detaiil_list = brand_detaiil_list;
	}


	public ArrayList getExport_order_list() {
		this.export_order_list = impl.export_order_display_detail(this);
		return export_order_list;
	}


	public void setExport_order_list(ArrayList export_order_list) {
		this.export_order_list = export_order_list;
	}

	public String getHidden() {
		//impl.getDetails(this);
		
		if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-DEC")){
			raise_flg=true;
			ds_flag=true;
			
		}else{
			raise_flg=false;
			ds_flag=false;
		}
		if(!ResourceUtil.getUserNameAllReq().substring(0, 9).equalsIgnoreCase("Excise-DL")){
			revertFlg=true;
		}else {
			revertFlg=false;
		}
		if(ResourceUtil.getUserNameAllReq().equalsIgnoreCase("Excise-Commissioner")){
			user4_flag=true;
		}else {
			user4_flag=false;
		}
		
		return hidden;
	}


	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	
	
	
	
	
	//====================================doc3 upload =================================================
	private boolean pdfUploaderFlag;
	private String upload1 = null;
	private boolean flagupload ;
	

	public boolean isFlagupload() {
		return flagupload;
	}


	public void setFlagupload(boolean flagupload) {
		this.flagupload = flagupload;
	}


	public String getUpload1() {
		return upload1;
	}


	public void setUpload1(String upload1) {
		this.upload1 = upload1;
	}


	public void setPdfUploaderFlag(boolean pdfUploaderFlag) {
		this.pdfUploaderFlag = pdfUploaderFlag;
	}


	public boolean isPdfUploaderFlag() {
		return pdfUploaderFlag;
	}

	
	public void doc3uploadMethod(UploadEvent event) throws Exception {

		try {

			UploadItem item = event.getUploadItem();
			String FullfileName = item.getFileName();
			this.upload1 = item.getFile().getPath();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

	}

	public boolean doc3upload = false;



	public boolean isDoc3upload() {
		return doc3upload;
	}
	public void setDoc3upload(boolean doc3upload) {
		this.doc3upload = doc3upload;
	}
	
	//===================================pdf 3 ==================================================

	public void pdf3() {
		
		Random rand = new Random();
		int n = rand.nextInt(99999) + 1;
		

		try {
			
			String mypath = Constants.JBOSS_SERVER_PATH
					+ Constants.JBOSS_LINX_PATH + File.separator
					+ "ExciseUp" + File.separator + "ExportOutsideIndia"+ File.separator + "SEH";
			InputStream io = new FileInputStream(this.upload1);

			String recordFile = "performa_certificate_"+n+ ".pdf";
					

			this.upload_performa_invoice = recordFile;

			FileOutputStream out = new FileOutputStream(mypath
					+ File.separator + recordFile);

			BufferedOutputStream outb = new BufferedOutputStream(out);
			int z = 0;
			while ((z = io.read()) != -1) {
				outb.write(z);
				doc3upload = true;

			}
			//System.out.println("doc3 uploaded success fully");
			this.flagupload = false;
			outb.flush();
			outb.close();
			io.close();
			this.upload1 = "";
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
//==============================   change listener ============================================
	
	public void change_import_order_list(ValueChangeEvent vce){
		String value_import = (String) vce.getNewValue() ;	
		this.import_order_no = value_import ;
		flag_brand_table = true ;
		
	
		
	}
	
public void change_icd_list(ValueChangeEvent vce){
		String val_icd =  (String) vce.getNewValue();	
		this.icd_id = val_icd ;
	
			
	}
	
	
//=========================================methos ===============================================
public void forward(){
	
	if(this.remark!=null && this.remark.length()>0){
	if(impl.updateDetails(this)){
		
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

  public void revert(){
	  if(this.rvrtRemark!=null && this.rvrtRemark.length()>0){
	  if(impl.revertBack(this)){
		this.remark=null;
	     }
	  }else{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please fill the revert comment !","Please fill the revert comment !"));
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
		this.reset();
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

	EoiExportOrderFrorwardingDT dt = (EoiExportOrderFrorwardingDT) this.export_order_list.get(rowId);
	this.app_date=dt.getApplication_date();
	this.app_id = dt.getApplication_no();
	this.leo_no=String.valueOf(dt.getCeo_no());
	this.brc_no=String.valueOf(dt.getBrc_no());
	this.total_bottles=String.valueOf(dt.getNo_of_bottles());
	this.icd_reciept=String.valueOf(dt.getReciept_at_icd());
	this.distillery_name=dt.getDistillery_name();
	this.distillery_id=Integer.parseInt(dt.getDistillery_id());
	this.setMaincntrlId(dt.getMaincntrlId());
	this.setMainServiceId(dt.getMainServiceId());
	this.setMainunitId(dt.getMainunitId());
	this.setRequestId(dt.getRequestId());
	this.setPuc_no(dt.getPuc_no());
	this.setPuc_pdf(dt.getPuc_pdf());
	this.icd_reciept=dt.getReciept_at_icd();
	
	
	if(this.show_flag.equalsIgnoreCase("F")){
		
		this.viewFlag=true;
	}
	///this.viewFlag=true;
	impl.viewImpl(this,dt.getApplication_no());
	impl.viewPDF(this);
	this.brand_detaiil_list=impl.brand_display_detail(this, dt.getApplication_no());
	
}


	public void clickRadio(ValueChangeEvent e){
		String val = (String) e.getNewValue();
		this.radio=val;
		this.export_order_list=impl.export_order_display_detail(this);
	}

//========================================================================

//=================================================reset ===================================

public void reset(){

	this.import_order_no            = null ;
	this.import_order_date          = null ;
	this.name_of_importing_unit     = null ;
	this.icd_id                     = null ;
	this.performa_invoice_no        = null ;
	this.distillery_id              = 0 ;
	this.validity_demanded          = null ;
	this.icd_list                   = null ;
	this.brand_detaiil_list.clear();     
	this.import_order_no_list.clear();        
	this.export_order_list.clear(); 
	this.upload_performa_invoice = null ;
	this.flag_brand_table = false ;
	this.doc3upload = true ;
	this.remark=null;
	this.user1_remark=null;
	this.user2_remark=null;
	this.user3_remark=null;
	this.user4_remark=null;
	this.distillery_name=null;
	this.icd_name=null;
	this.total_bottles=null;
	 this.export_order_list = impl.export_order_display_detail(this);
}                                 

/*public void print(ActionEvent ae){
	UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent().getParent();
	int rowId = uiTable.getRowIndex();

	
}

private String pdfname;
private boolean printFlag;


public String getPdfname() {
	return pdfname;
}

public void setPdfname(String pdfname) {
	this.pdfname = pdfname;
}

public boolean isPrintFlag() {
	return printFlag;
}

public void setPrintFlag(boolean printFlag) {
	this.printFlag = printFlag;
}*/

////ankur 29-09-2020

private boolean printFlag;
private String pdfname;

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
//ankur 01-10-2020
public void print(ActionEvent ae){
	
	UIDataTable uiTable = (UIDataTable) ae.getComponent().getParent().getParent();
	int rowId = uiTable.getRowIndex();
	
	EoiExportOrderFrorwardingDT dt = (EoiExportOrderFrorwardingDT) this.export_order_list.get(rowId);

	try{
		this.app_id = dt.getApplication_no();
	impl.printReport(this,dt);
}
catch(Exception e)
{
	e.printStackTrace();
} 

}
private String show_flag = "F";



public String getShow_flag() {
return show_flag;
}

public void setShow_flag(String show_flag) {
this.show_flag = show_flag;
}	

}
