package com.mentor.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.Datatable.UploadShippingAndLadingBillDT;
import com.mentor.impl.UploadShippingAndLadingBillImpl;
import com.mentor.utility.Constants;


public class UploadShippingAndLadingBillAction {
	
	UploadShippingAndLadingBillImpl impl = new UploadShippingAndLadingBillImpl();
	// ================= Variables==========================================
	private String radio="SB";
	private String gatepass_no;
	private String shipping_bil_pdf;
	private String shipping_bill_no;
	private Date shipping_bill_date;
	private String lading_bil_pdf;
	private String lading_bill_no;
	private Date lading_bill_date;
	private String distillery_name;
	private int int_distillery_id;
	private boolean view_flag;
	private String hidden;
	private Date gatepass_date;
	private ArrayList  gatepass_list = new ArrayList();
	private ArrayList  brand_list = new ArrayList();
	private int brc_value;
	private int value_inr;
	private String currency_list;
	private String uploade_certificate_foriegn_exchange_pdf;
	private int currency_id;
	private Date brc_date;
	private String sealradio="P";
	private String seal_no;
	private Date seal_dt;
	//==============Getter setter======================================
	
	
	
	
	
	public Date getBrc_date() {
		return brc_date;
	}



	public String getSeal_no() {
		return seal_no;
	}



	public void setSeal_no(String seal_no) {
		this.seal_no = seal_no;
	}



	public Date getSeal_dt() {
		return seal_dt;
	}



	public void setSeal_dt(Date seal_dt) {
		this.seal_dt = seal_dt;
	}



	public String getSealradio() {
		return sealradio;
	}



	public void setSealradio(String sealradio) {
		this.sealradio = sealradio;
	}



	public void setBrc_date(Date brc_date) {
		this.brc_date = brc_date;
	}



	public String getCurrency_list() {
		return currency_list;
	}







	public int getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(int currency_id) {
		this.currency_id = currency_id;
	}

	public String getUploade_certificate_foriegn_exchange_pdf() {
		return uploade_certificate_foriegn_exchange_pdf;
	}

	public void setUploade_certificate_foriegn_exchange_pdf(
			String uploade_certificate_foriegn_exchange_pdf) {
		this.uploade_certificate_foriegn_exchange_pdf = uploade_certificate_foriegn_exchange_pdf;
	}

	public void setCurrency_list(String currency_list) {
		this.currency_list = currency_list;
	}

	public int getBrc_value() {
		return brc_value;
	}

	
	public void setBrc_value(int brc_value) {
		this.brc_value = brc_value;
	}

	public int getValue_inr() {
		return value_inr;
	}

	public void setValue_inr(int value_inr) {
		this.value_inr = value_inr;
	}
	
	public String getRadio() {
		return radio;
	}
	public Date getGatepass_date() {
		return gatepass_date;
	}
	public void setGatepass_date(Date gatepass_date) {
		this.gatepass_date = gatepass_date;
	}
	public String getHidden() {
		impl.getDetails(this);
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public ArrayList getBrand_list() {
		return brand_list;
	}
	public void setBrand_list(ArrayList brand_list) {
		this.brand_list = brand_list;
	}
	public boolean isView_flag() {
		return view_flag;
	}
	public void setView_flag(boolean view_flag) {
		this.view_flag = view_flag;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getGatepass_no() {
		return gatepass_no;
	}
	public void setGatepass_no(String gatepass_no) {
		this.gatepass_no = gatepass_no;
	}
	public String getShipping_bil_pdf() {
		return shipping_bil_pdf;
	}
	public void setShipping_bil_pdf(String shipping_bil_pdf) {
		this.shipping_bil_pdf = shipping_bil_pdf;
	}
	public String getShipping_bill_no() {
		return shipping_bill_no;
	}
	public void setShipping_bill_no(String shipping_bill_no) {
		this.shipping_bill_no = shipping_bill_no;
	}
	public Date getShipping_bill_date() {
		return shipping_bill_date;
	}
	public void setShipping_bill_date(Date shipping_bill_date) {
		this.shipping_bill_date = shipping_bill_date;
	}
	public String getLading_bil_pdf() {
		return lading_bil_pdf;
	}
	public void setLading_bil_pdf(String lading_bil_pdf) {
		this.lading_bil_pdf = lading_bil_pdf;
	}
	public String getLading_bill_no() {
		return lading_bill_no;
	}
	public void setLading_bill_no(String lading_bill_no) {
		this.lading_bill_no = lading_bill_no;
	}
	public Date getLading_bill_date() {
		return lading_bill_date;
	}
	public void setLading_bill_date(Date lading_bill_date) {
		this.lading_bill_date = lading_bill_date;
	}
	public String getDistillery_name() {
		return distillery_name;
	}
	public void setDistillery_name(String distillery_name) {
		this.distillery_name = distillery_name;
	}
	public int getInt_distillery_id() {
		return int_distillery_id;
	}
	public void setInt_distillery_id(int int_distillery_id) {
		this.int_distillery_id = int_distillery_id;
	}
	
	public ArrayList getGatepass_list() {
		this.gatepass_list=impl.gatepassListImpl(this);
		return gatepass_list;
	}
	public void setGatepass_list(ArrayList gatepass_list) {
		this.gatepass_list = gatepass_list;
	}
	
	
	//======================================================
	private boolean pdfUploaderFlag;
	private String upload1 = null;
	private boolean flagupload ;
	private boolean doc3upload;
	private boolean pdfUploaderFlag1;
	private String upload2 = null;
	private boolean flagupload1 ;
	private boolean doc3upload1;
	private boolean doc4upload2;
	
	
	public boolean isDoc4upload2() {
		return doc4upload2;
	}
	public void setDoc4upload2(boolean doc4upload2) {
		this.doc4upload2 = doc4upload2;
	}
	public boolean isPdfUploaderFlag1() {
		return pdfUploaderFlag1;
	}
	public void setPdfUploaderFlag1(boolean pdfUploaderFlag1) {
		this.pdfUploaderFlag1 = pdfUploaderFlag1;
	}
	public String getUpload2() {
		return upload2;
	}
	public void setUpload2(String upload2) {
		this.upload2 = upload2;
	}
	public boolean isFlagupload1() {
		return flagupload1;
	}
	public void setFlagupload1(boolean flagupload1) {
		this.flagupload1 = flagupload1;
	}
	public boolean isDoc3upload1() {
		return doc3upload1;
	}
	public void setDoc3upload1(boolean doc3upload1) {
		this.doc3upload1 = doc3upload1;
	}
	public boolean isDoc3upload() {
		return doc3upload;
	}
	public void setDoc3upload(boolean doc3upload) {
		this.doc3upload = doc3upload;
	}
	public boolean isPdfUploaderFlag() {
		return pdfUploaderFlag;
	}
	public void setPdfUploaderFlag(boolean pdfUploaderFlag) {
		this.pdfUploaderFlag = pdfUploaderFlag;
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
	
public void pdf3() {
		
		Random rand = new Random();
		int n = rand.nextInt(99999) + 1;
		

		try {
			
			String mypath = Constants.JBOSS_SERVER_PATH
					+ Constants.JBOSS_LINX_PATH + File.separator
					+ "ExciseUp" + File.separator + "ExportOutsideIndia"+ File.separator + "SEH";
			InputStream io = new FileInputStream(this.upload1);

			String recordFile = "shipping_bill"+n+ ".pdf";
					

			this.shipping_bil_pdf = recordFile;

			FileOutputStream out = new FileOutputStream(mypath
					+ File.separator + recordFile);

			BufferedOutputStream outb = new BufferedOutputStream(out);
			int z = 0;
			while ((z = io.read()) != -1) {
				outb.write(z);
				doc3upload = true;
				 this.pdfUploaderFlag=true;
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


public void doc3uploadMethod1(UploadEvent event) throws Exception {

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
				+ "ExciseUp" + File.separator + "ExportOutsideIndia"+ File.separator + "SEH";
		InputStream io = new FileInputStream(this.upload2);

		String recordFile = "lading_bill"+n+ ".pdf";
				

		this.lading_bil_pdf = recordFile;

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

			
		

	//---------------------------------- docuploade 4---------/////////////////

public void doc4uploadMethod2(UploadEvent event) throws Exception {

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

public void pdf32() {
	
	Random rand = new Random();
	int n = rand.nextInt(99999) + 1;
	

	try {
		
		String mypath = Constants.JBOSS_SERVER_PATH
				+ Constants.JBOSS_LINX_PATH + File.separator
				+ "ExciseUp" + File.separator + "ExportOutsideIndia"+ File.separator + "SEH";
		InputStream io = new FileInputStream(this.upload2);

		String recordFile = "uploade_certificate_foriegn_exchange_pdf"+n+ ".pdf";
				

		this.uploade_certificate_foriegn_exchange_pdf = recordFile;

		FileOutputStream out = new FileOutputStream(mypath
				+ File.separator + recordFile);

		BufferedOutputStream outb = new BufferedOutputStream(out);
		int z = 0;
		while ((z = io.read()) != -1) {
			outb.write(z);
			doc4upload2 = true;
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
 
public void radioListener(ValueChangeEvent e){
	String val = (String) e.getNewValue();
	this.radio=val;
	this.gatepass_list=impl.gatepassListImpl(this);
}
public void view(ActionEvent e){
	UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
			.getParent();
	UploadShippingAndLadingBillDT dt = (UploadShippingAndLadingBillDT) this.getGatepass_list()
			.get(uiTable.getRowIndex());
	this.view_flag=true;
	this.gatepass_no=dt.getVch_gatepass_no();
	this.gatepass_date=dt.getDt_date();
	this.brand_list=impl.brand_display_detail(this);
			
		
}



   public void submit(){
	   
	   if(this.radio.equalsIgnoreCase("SB")){
		     if(this.shipping_bil_pdf!=null && this.shipping_bil_pdf.length()>0){
		    	 if(this.shipping_bill_no!=null && this.shipping_bill_no.length()>0){
		    		 if(this.shipping_bill_date!=null){
		    			 impl.savedetails(this);
		    		 }else{
		    			 FacesContext.getCurrentInstance().addMessage(
		    						null,
		    						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select issue date", "Please select issue date"));
		    		 }
		    	 }else{
		    		 FacesContext.getCurrentInstance().addMessage(
	    						null,
	    						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter shipping bill no.", "Please enter shipping bill no."));
		    	 }
		     }else{
	    		 FacesContext.getCurrentInstance().addMessage(
 						null,
 						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please upload shipping bill", "Please upload shipping bill"));
	    	 }
	   }else if(this.radio.equalsIgnoreCase("LB")){
		     if(this.lading_bil_pdf!=null && this.lading_bil_pdf.length()>0){
		    	 if(this.lading_bill_no!=null && this.lading_bill_no.length()>0){
		    		 if(this.lading_bill_date!=null){
		    			 impl.savedetails(this);
		    		 }else{
		    			 FacesContext.getCurrentInstance().addMessage(
		    						null,
		    						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select issue date", "Please select issue date"));
		    		 }
		    	 }else{
		    		 FacesContext.getCurrentInstance().addMessage(
	    						null,
	    						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter lading bill no.", "Please enter lading bill no."));
		    	 }
		     }else{
	    		 FacesContext.getCurrentInstance().addMessage(
 						null,
 						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please upload lading bill", "Please upload lading bill"));
	    	 }
	   }
	
	   
	   if(this.radio.equalsIgnoreCase("B")){
		   if(this.selectCurrency>0){
		     if(this.uploade_certificate_foriegn_exchange_pdf!=null && this.uploade_certificate_foriegn_exchange_pdf.length()>0){
		    	 if(this.brc_value!=0 && this.brc_value!=0){
		    		 if(this.value_inr!=0){
		    			 if(this.brc_date!=null){
		    			 impl.savedetails(this);
		    		 }else{
		    			 FacesContext.getCurrentInstance().addMessage(
		    						null,
		    						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select issue date", "Please select issue date"));
		    		 }
		    			 }else{
		    			 FacesContext.getCurrentInstance().addMessage(
		    						null,
		    						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter BRC Value", "Please enter BRC Value"));
		    		 }
		    	 }else{
		    		 FacesContext.getCurrentInstance().addMessage(
	    						null,
	    						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter Value in INR. .", "Please enter Value in INR.."));
		    	 }
		     }else{
	    		 FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Upload uploade_certificate_foriegn_exchange_pdf", "Please uploade_certificate_foriegn_exchange_pdf"));
	    	 }
		   }
		   else{
	    		 FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Select currency ", "Select currency "));
	    	 }
	   }
   }
 public void submit1(){
	   
	  
		     if(this.seal_dt!=null && this.seal_no.length()>0 && this.seal_no!=null ){
		    	  
		    			 impl.savedetails1(this);
		    		 }  else{
			    		 FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_ERROR,"Enter Seal Details ", "Enter Seal Details"));
				    	 }
   }
   
   
     public void back(){
	 this.view_flag=false;
	 reset();
   }

     
     public void reset(){
    	 sealradio="P";
    	 this.shipping_bil_pdf=null;
    	 this.shipping_bill_date=null;
    	 this.shipping_bill_no=null;
    	 this.lading_bil_pdf=null;
    	 this.lading_bill_date=null;
    	 this.lading_bill_no=null;
    	 this.gatepass_date=null;
    	 this.gatepass_no=null;
    	 seal_dt=null;
    	 seal_no=null;
    	 this.brand_list.clear();
    	 this.upload1=null;
    	 this.upload2=null;
    	 this.doc3upload=false;
    	 this.doc3upload1=false;
    	 this.flagupload=false;
    	 this.flagupload=false;
     }
     
     
 	private int selectCurrency=0;
 	private ArrayList currencyList=new ArrayList();

 	public int getSelectCurrency() {
 		return selectCurrency;
 	}

 	public void setSelectCurrency(int selectCurrency) {
 		this.selectCurrency = selectCurrency;
 	}

 	public String getViewflg() {
 		return viewflg;
 	}

 	public void setViewflg(String viewflg) {
 		this.viewflg = viewflg;
 	}

 	public ArrayList getcurrencyList() {
 		this.currencyList = impl.selectcurrency();
 		return currencyList;
 	}

 	public void setcurrencyList(ArrayList currencyList) {
 		this.currencyList = currencyList;
 	}
 	
 	private String viewflg="F";
 	private String currencyName;

 	public String getCurrencName() {
 		return currencyName;
 	}

 	public void setCurrencName(String currencyName) 
 	{
 		this.currencyName = currencyName;
 	} 
     
     
}
