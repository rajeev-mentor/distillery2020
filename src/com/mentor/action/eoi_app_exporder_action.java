package com.mentor.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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

import com.mentor.Datatable.eoi_app_exporder_dt;
import com.mentor.impl.eoi_app_exporder_impl;
import com.mentor.utility.Constants;
import com.mentor.utility.Validate;

public class eoi_app_exporder_action {

	//===========================================variable ===============================================
	private String import_order_no ;
	private Date import_order_date ;
	private String name_of_importing_unit ;
	private String icd_id ="0";
	private String performa_invoice_no ;
	private String upload_performa_invoice ;
	private Date validity_demanded ;
	private ArrayList icd_list = new ArrayList();
	private ArrayList brand_detaiil_list = new ArrayList();	
	private ArrayList export_order_list = new ArrayList();
	private ArrayList import_order_no_list = new ArrayList();
	private String hidden ;
	private int distillery_id ;
	private boolean validate_input ;
	private boolean flag_brand_table ;
     private boolean save_flag ;
     private boolean pending_flag;
    private String puc_no ;
    private String upload_puc_certificate ;


	public String getPuc_no() {
	return puc_no;
}

public void setPuc_no(String puc_no) {
	this.puc_no = puc_no;
}

public String getUpload_puc_certificate() {
	return upload_puc_certificate;
}

public void setUpload_puc_certificate(String upload_puc_certificate) {
	this.upload_puc_certificate = upload_puc_certificate;
}

	public boolean isPending_flag() {
		return pending_flag;
	}

	public void setPending_flag(boolean pending_flag) {
		this.pending_flag = pending_flag;
	}

	public boolean isSave_flag() {
		return save_flag;
	}

	public void setSave_flag(boolean save_flag) {
		this.save_flag = save_flag;
	}

	public boolean isFlag_brand_table() {
		return flag_brand_table;
	}

	public void setFlag_brand_table(boolean flag_brand_table) {
		this.flag_brand_table = flag_brand_table;
	}

	eoi_app_exporder_impl  impl = new eoi_app_exporder_impl();
	
	//=========================================  Getter and setter  ========================================
	
	
	


	public boolean isValidate_input() {
	   long	count_qty = 0;
		
		validate_input = true ;
		
		if (validate_input) {
			     if (this.import_order_no == null  || this.import_order_no.length() == 0  || this.import_order_no.trim().equalsIgnoreCase("")) {
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
			} else if (this.puc_no == null || this.puc_no.length() == 0 || this.puc_no.trim().equalsIgnoreCase("")) {
				this.validate_input = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Please Upload PUC Number "));
			}
			else if (this.upload_puc_certificate == null || this.upload_puc_certificate.length() == 0 || this.upload_puc_certificate.trim().equalsIgnoreCase("")) {
				this.validate_input = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Please Enter PUC pdf Copy"));
			}	
			else if (this.validity_demanded == null) {
				this.validate_input = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Select Validity Demanded "));
			}			  
           else if(this.getBrand_detaiil_list().size()>0){
				
				for (int i = 0; i < this.getBrand_detaiil_list().size(); i++) {
					eoi_app_exporder_dt 
				dt = (eoi_app_exporder_dt) this.getBrand_detaiil_list().get(i);
				count_qty = count_qty + (int)dt.getRequested_qty();
				if(dt.getRequested_qty() > dt.getBalance_qty()){
					this.validate_input = false;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Please Enter Requested Qty less than Balance Qty."));
					break;
					
				}				
				
	
					}
				System.out.println("====count_qty====="+count_qty);
				if(count_qty==0){
					this.validate_input = false;
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Please Enter Atlest One Requested Qty."));
					
				}
				
				
				
				}	
        
			  
			  
			  
 
		}
		
		return validate_input;
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


	public Date getImport_order_date() {
		return import_order_date;
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


	public void setImport_order_date(Date import_order_date) {
		this.import_order_date = import_order_date;
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


	public Date getValidity_demanded() {
		return validity_demanded;
	}


	public void setValidity_demanded(Date validity_demanded) {
		this.validity_demanded = validity_demanded;
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
		return export_order_list;
	}


	public void setExport_order_list(ArrayList export_order_list) {
		this.export_order_list = export_order_list;
	}

	public String getHidden() {
		impl.getDetails(this);
		
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

			String recordFile = "performa_certificate_"+n+"-"+distillery_id+ ".pdf";
					

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
	
	
//======================================puc certificate ===================================
	
	private boolean pdfUploaderFlag4;
	private String upload4 = null;
	private boolean flagupload4 ;
	public boolean doc4upload = false;



	
	public boolean isDoc4upload() {
		return doc4upload;
	}

	public void setDoc4upload(boolean doc4upload) {
		this.doc4upload = doc4upload;
	}

	public boolean isPdfUploaderFlag4() {
		return pdfUploaderFlag4;
	}

	public void setPdfUploaderFlag4(boolean pdfUploaderFlag4) {
		this.pdfUploaderFlag4 = pdfUploaderFlag4;
	}

	public String getUpload4() {
		return upload4;
	}

	public void setUpload4(String upload4) {
		this.upload4 = upload4;
	}

	public boolean isFlagupload4() {
		return flagupload4;
	}

	public void setFlagupload4(boolean flagupload4) {
		this.flagupload4 = flagupload4;
	}

	public void doc4uploadMethod(UploadEvent event) throws Exception {

		try {

			UploadItem item = event.getUploadItem();
			String FullfileName = item.getFileName();
			this.upload4 = item.getFile().getPath();

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							e.getMessage(), e.getMessage()));
			e.printStackTrace();
		}

	}
	
	//===================================pdf 4 puc number ==================================================

		public void pdf4() {
			
			Random rand = new Random();
			int n = rand.nextInt(99999) + 1;
			

			try {
				
				String mypath = Constants.JBOSS_SERVER_PATH
						+ Constants.JBOSS_LINX_PATH + File.separator
						+ "ExciseUp" + File.separator + "ExportOutsideIndia"+ File.separator + "SEH";
				InputStream io = new FileInputStream(this.upload4);

				String recordFile = "puc_"+n+"-"+distillery_id+ ".pdf";
						

				this.upload_puc_certificate = recordFile;

				FileOutputStream out = new FileOutputStream(mypath
						+ File.separator + recordFile);

				BufferedOutputStream outb = new BufferedOutputStream(out);
				int z = 0;
				while ((z = io.read()) != -1) {
					outb.write(z);
					doc4upload = true;

				}
				//System.out.println("doc3 uploaded success fully");
				this.flagupload4 = false;
				outb.flush();
				outb.close();
				io.close();
				this.upload4 = "";
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		
//==============================   change listener ============================================
	
	public void change_import_order_list(ValueChangeEvent vce){
		String value_import = (String) vce.getNewValue() ;	
		this.import_order_no = value_import ;
		flag_brand_table = true ;
		this.brand_detaiil_list = impl.brand_display_detail(this);
		this.export_order_list = impl.export_order_display_detail(this);
	     impl.imorting_name_order_date(this);
	
		 
	}
	
public void change_icd_list(ValueChangeEvent vce){
		String val_icd =  (String) vce.getNewValue();	
		this.icd_id = val_icd ;
	
			
	}
	
	
//=========================================save ===============================================
public void save(){
	
	if(isValidate_input()){
	impl.savedetails(this);
	this.detail_list = impl.app_detail(this);
	}
	
}




//=================================================reset ===================================

public void reset(){

	this.import_order_no            = null ;
	this.import_order_date          = null ;
	this.name_of_importing_unit     = null ;
	this.icd_id                     = "0" ;
	this.performa_invoice_no        = null ;
	this.distillery_id              = 0 ;
	this.validity_demanded          = null ;
	this.icd_list                   = null ;
	this.puc_no                     = null ;
	this.upload_puc_certificate     = null ;
	this.brand_detaiil_list.clear();     
	this.import_order_no_list.clear();        
	this.export_order_list.clear(); 
	this.upload_performa_invoice = null ;
	this.flag_brand_table = false ;
	this.doc3upload = false ;
	this.doc4upload = false ;
	
}                                 


    public void delete(ActionEvent e) {
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
	eoi_app_exporder_dt dt = (eoi_app_exporder_dt) 
	this.getDetail_list().get(uiTable.getRowIndex());
	impl.deleterow(this,dt);
	this.detail_list.remove(dt);
	}
	
        public void finalize(ActionEvent e) {
    	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
    	eoi_app_exporder_dt dt = (eoi_app_exporder_dt) 
    	this.getDetail_list().get(uiTable.getRowIndex());
    	impl.finalize(this,dt);
    	this.detail_list = impl.app_detail(this);
    }
        private boolean flg;

    	public boolean isFlg() {
    		return flg;
    	}
    	public void setFlg(boolean flg) {
    		this.flg = flg;
    	}

    	private ArrayList detail_list = new ArrayList();


		public ArrayList getDetail_list() 
		
		{
			this.detail_list = impl.app_detail(this);
			return detail_list;
		}

		public void setDetail_list(ArrayList detail_list) {
			this.detail_list = detail_list;
		}     
}
