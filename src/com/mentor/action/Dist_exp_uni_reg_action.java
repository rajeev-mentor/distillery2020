	package com.mentor.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.portlet.PortletSession;

import org.jboss.portletbridge.context.ServletSessionWrapper;
import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.Datatable.dist_exp_uni_reg_dt;
import com.mentor.impl.dist_exp_uni_impl;
import com.mentor.utility.Constants;
import com.mentor.utility.Validate;


public class Dist_exp_uni_reg_action {	
	
	dist_exp_uni_impl  impl = new dist_exp_uni_impl();
				
private String icd_id ;
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
private boolean bank_flag ;
private boolean objection_reply_button;
private String status;

private boolean update_flag  ;

private boolean upload_flag ;


public boolean isUpload_flag() {
	return upload_flag;
}

public void setUpload_flag(boolean upload_flag) {
	this.upload_flag = upload_flag;
}

public boolean isUpdate_flag() {
	return update_flag;
}

public void setUpdate_flag(boolean update_flag) {
	this.update_flag = update_flag;
}
private int app_id;

public int getApp_id() {
	return app_id;
}

public void setApp_id(int app_id) {
	this.app_id = app_id;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public boolean isObjection_reply_button() {
	return objection_reply_button;
}

public void setObjection_reply_button(boolean objection_reply_button) {
	this.objection_reply_button = objection_reply_button;
}


public boolean isBank_flag() {
	return bank_flag;
}

public void setBank_flag(boolean bank_flag) {
	this.bank_flag = bank_flag;
}

public void setIcd_id(String icd_id) {
	this.icd_id = icd_id;
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

public void setDistillery_id(int distillery_id) {
	this.distillery_id = distillery_id;
}
public String getIcd_id() {
	return icd_id;
}


public void setHidden(String hidden) {
	this.hidden = hidden;
}

public String flag_bank_detail = "false" ;


public String getFlag_bank_detail() {
	return flag_bank_detail;
}

public void setFlag_bank_detail(String flag_bank_detail) {
	this.flag_bank_detail = flag_bank_detail;
}

public String getHidden(){
	
	  impl.getDetails(this);	  
	  impl.getShowDetails(this);
	  
      if(flag_bank_detail.equalsIgnoreCase("false")){
		// System.out.println("--flag_bank_detail-- "+this.flag_bank_detail);
		 
			  this.addRowdetailsData = impl.Bank_detail(this);  
			this.flag_bank_detail = "true";  
      } 
 
	return hidden;

}

private String listflag = "T";
public ArrayList getDisplay_detail() {
	if(listflag.equalsIgnoreCase("T")){
	display_detail = impl.display_detail(this);
	 listflag = "F";
	}
	
	return display_detail;
}
public void setDisplay_detail(ArrayList display_detail) {
	this.display_detail = display_detail;
}
public boolean isDoc2upload() {
	return doc2upload;
}
public void setDoc2upload(boolean doc2upload) {
	this.doc2upload = doc2upload;
}
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





//====================================== getter and setter ====================================================

	
	

public boolean isDoc1upload() {
	return doc1upload;
}

public void setDoc1upload(boolean doc1upload) {
	this.doc1upload = doc1upload;
}

public void setPdfUploaderFlag(boolean pdfUploaderFlag) {
	this.pdfUploaderFlag = pdfUploaderFlag;
}

public void setIcd_list(ArrayList icd_list) {
	this.icd_list = icd_list;
}

		public String getUpload_certificate_export_1() {
	return upload_certificate_export_1;
}

public void setUpload_certificate_export_1(String upload_certificate_export_1) {
	this.upload_certificate_export_1 = upload_certificate_export_1;
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





//======================= add bank details row ================================

		public String addRowdetails() {
             
			 dist_exp_uni_reg_dt dt = new dist_exp_uni_reg_dt();                                  
				dt.setSno_r(addRowdetailsData.size() + 1);                                                 
				                                                                                   
				addRowdetailsData.add(dt);   
                                                                                
			return "";                                                                             
		}
		 
			public ArrayList addRowdetailsData = new ArrayList();


			public ArrayList getAddRowdetailsData() {
				return addRowdetailsData;
			}

			public void setAddRowdetailsData(ArrayList addRowdetailsData) {
				this.addRowdetailsData = addRowdetailsData;
			}

	
			
			
//==============================  icd list =============================================
			
			/*public String icd_change_event(ValueChangeEvent event) {
				String val =  (String) event.getNewValue();
				
				icd_id = val ;
				
				return "";
			}
*/


/*public ArrayList getIcd_list() {
	
	icd_list = impl.getIcd_list();
	  return icd_list;
}*/

//===================================    ics certificate   upload  ---------------------------------
public void doc1uploadMethod(UploadEvent event) throws Exception {

	UploadItem item = event.getUploadItem();
	String FullfileName = item.getFileName();
	this.upload1 = item.getFile().getPath();
}



Random rand = new Random();
int n = rand.nextInt(99999) + 1;

public void pdf1() {

	

		try {
			
			String mypath = Constants.JBOSS_SERVER_PATH
					+ Constants.JBOSS_LINX_PATH + File.separator
					+ "ExciseUp" + File.separator + "ExportOutsideIndia"+ File.separator + "ICD";
			InputStream io = new FileInputStream(this.upload1);

			String recordFile = "ICD_"+n+ this.distillery_id
					+ ".pdf";

			this.upload_certificate_export_1 = recordFile;
			FileOutputStream out = new FileOutputStream(mypath
					+ File.separator + recordFile);

			BufferedOutputStream outb = new BufferedOutputStream(out);
			int z = 0;
			while ((z = io.read()) != -1) {
				outb.write(z);
				doc1upload = true;

			}
			io.close();
			//System.out.println("doc1 uploaded success fully");
			this.flagupload = false;
			outb.flush();
			outb.close();
			this.upload1 = "";
          
           
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

private boolean pdfUploaderFlag;

public boolean isPdfUploaderFlag() {
	return pdfUploaderFlag;
}

//============================doc upload ===============================================

public void doc2uploadMethod(UploadEvent event) throws Exception {

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

//==================================  dist exp =======================================

public void pdf2() {

	try {
		

		String mypath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH + File.separator
				+ "ExciseUp" + File.separator + "ExportOutsideIndia"+ File.separator + "RCM";
		InputStream io = new FileInputStream(this.upload1);
	
		String recordFile = "Registration_cum_"+n+ this.distillery_id+ ".pdf";

		this.upload_rcmc_2 = recordFile;

		FileOutputStream out = new FileOutputStream(mypath
				+ File.separator + recordFile);

		BufferedOutputStream outb = new BufferedOutputStream(out);
		int z = 0;
		while ((z = io.read()) != -1) {
			outb.write(z);
			doc2upload = true;

		}
		//System.out.println("doc2 uploaded success fully");
		this.flagupload = false;
		outb.flush();
		outb.close();
		io.close();

		this.upload1 = "";

	
		 
		 } catch (Exception e) {
		// TODO: handle exception
	}

}






//===================================pdf 3 ==================================================

public void pdf3() {

	

	try {
		
		String mypath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH + File.separator
				+ "ExciseUp" + File.separator + "ExportOutsideIndia"+ File.separator + "SEH";
		InputStream io = new FileInputStream(this.upload1);

		String recordFile = "StarExport_"+n+ this.distillery_id+ ".pdf";
				

		this.upload_certi_export_3 = recordFile;

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



//================================================== save ====================================
public boolean saveflag  ;

public boolean isSaveflag() {
	return saveflag;
}

public void setSaveflag(boolean saveflag) {
	this.saveflag = saveflag;
}

public void save(){

if(isValidateInput()){
		 String regex = "^[0-9]{2}[A-Z]{5}[0-9]{4}"
                 + "[A-Z]{1}[1-9A-Z]{1}"
                 + "Z[0-9A-Z]{1}$"; 

  Pattern p = Pattern.compile(regex); 
  Matcher m = p.matcher(this.gstin_no);
		
	if(m.matches()){
	impl.savedetails(this);
	
	//impl.getShowDetails(this);
	// this.addRowdetailsData = impl.Bank_detail(this);
	}else{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Enter Valid GSTIN Number .  "+this.gstin_no+" is invalid "));	
	}
}
	}


//=======================update ==============================================================

public void update(){
	impl.update(this);
	
}
	


//==================================== validation ==========================================================
private boolean validateInput;

public boolean isValidateInput() {
	System.out.println("----"+this.gstin_no.trim());
	validateInput = true;
	// FL41_Validation val = new FL41_Validation();

	if (validateInput) {
		 if (this.importer_exporter_code == null  || this.importer_exporter_code.length() == 0  || this.importer_exporter_code.trim().equalsIgnoreCase("")) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Enter Importer Exporter Code"));
		}
		else if (this.icd_certificate_issue_date == null) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Select ICD certificate Issue Date"));
		}else if (this.upload_certificate_export_1 == null) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Upload ICD Certificate"));
		} else if (this.regis_cum_membership_no == null || this.regis_cum_membership_no.length() == 0 || this.regis_cum_membership_no.trim().equalsIgnoreCase("")) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Enter Registration cum Membership"));
		} else if (this.membership_certificate_issue_date == null) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Membership Certificate Issue Date"));
		} else if (this.upload_rcmc_2 == null) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Upload RCMC certificate "));
		} else if (this.house_no.trim().length() < 0  || this.house_no.trim().equalsIgnoreCase("")  || this.house_no.trim() == null) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Enter House NO."));
		}
		else if (this.export_certificate_issue_date == null) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Enter House NO."));
		}
		else if (this.gstin_no.trim().length() < 0  || this.gstin_no.trim().equalsIgnoreCase("")  || this.gstin_no.trim() == null) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Enter GSTIN"));
		} 
		
		else if (this.upload_certi_export_3 == null) {
			this.validateInput = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please Upload Certificate Export 3"));
		} 
		else if(this.getAddRowdetailsData().size()>0){
			for (int i = 0; i < this.getAddRowdetailsData().size(); i++) {
				dist_exp_uni_reg_dt dt = new dist_exp_uni_reg_dt();
			dt = (dist_exp_uni_reg_dt) this.getAddRowdetailsData().get(i);
			
			 System.out.println("---validate enter in for loop  ===");
			 
			if(!(Validate.validateStrReq("Bank",dt.getBank_name())))validateInput=false;
			else if(!(Validate.validateStrReq("Branch",dt.getBranch())))validateInput=false;
			else if(!(Validate.validateStrReq("Account",dt.getAccount_no())))validateInput=false;	
			break;
				}}
		     else{
					this.validateInput = false;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Please Add Atleast One Bank Detail"));
				}
		
		

	}
	return validateInput;
}

public void setValidateInput(boolean validateInput) {
	this.validateInput = validateInput;
}
public void reset(){
	this.icd_id = null ;
	this.importer_exporter_code = null ;
	this.regis_cum_membership_no = null ;
	this.distillery_id = 0 ;
	this.gstin_no = null ;
	this.membership_certificate_issue_date = null ;	
	this.icd_certificate_issue_date = null ;
	this.upload_certi_export_3 = null ;
	this.house_no = null ;
	this.hidden = null ;
	this.icd_list = null ;
	this.doc1upload = false ;
	this.doc2upload = false ;
	this.doc3upload = false ;
	this.addRowdetailsData.clear();
	this.export_certificate_issue_date = null ;
	 listflag = "T";
	
}

public void deleteRowRowdetails(ActionEvent e) {                                      
    
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()                       
			.getParent();                                                                  
	dist_exp_uni_reg_dt dt = (dist_exp_uni_reg_dt) this.addRowdetailsData.get(uiTable               
			.getRowIndex());     
	this.addRowdetailsData.remove(dt);                                                           
} 



public String replyObjection()
{
	try{
		PortletSession psi = null;
		ServletSessionWrapper ssw = null;

		Object o = FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true);

		if (o instanceof ServletSessionWrapper) {

			ssw = (ServletSessionWrapper) o;
			 
		ssw.setAttribute("app_id",this.app_id);
			  
		} else if (o instanceof PortletSession) {

			psi = (PortletSession) o;
			 
		 psi.setAttribute("app_id",this.app_id);
			  
		}
	}catch(Exception e)
	{
		e.printStackTrace();
	}
return "reply_obj_exp";
}


}
			
		

	
	
	
	


