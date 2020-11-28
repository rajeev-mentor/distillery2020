package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import com.mentor.impl.TransferFromBlendingToBottlingVatImpl;

public class TransferFromBlendingToBottlingVatAction {
	
TransferFromBlendingToBottlingVatImpl impl =new TransferFromBlendingToBottlingVatImpl();
	
	
	private String hidden;
	ArrayList bottling_noList = new ArrayList ();
	ArrayList blend_vat_noList = new ArrayList ();
	private String bottling_no;
	private String blend_vat_no;
	private double bottling_bl;
	private double bottling_al;
	private double blending_bl;
	private double blending_al;
	private double bottling_bl_bfr;
	private double bottling_strngth_bfr;
	private double bottling_al_bfr;
	private double bottling_bl_aftr;
	private double bottling_strnght_aftr;
	private double bottling_al_aftr;
	private double bottling_bl_wast;
	private double bottling_al_wast;
	private double bottling_bl_trnfr;
	private double bottling_al_trnfr;
	private int    fl3_distillri_id;
	private String fl3_distillri_nm;
	private String fl3_distillri_addrss;
	private Date currentDate=new Date();
	
	public Date getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}


	public String getHidden() {
		try{
			impl.getDetails(this);
		}
		catch(Exception e){
			
		}
		return hidden;
	}


	public void setHidden(String hidden) {
		this.hidden = hidden;
	}


	


	public ArrayList getBlend_vat_noList() {
		this.blend_vat_noList=impl.getblendingStoragelist(this);
		return blend_vat_noList;
	}


	public void setBlend_vat_noList(ArrayList blend_vat_noList) {
		this.blend_vat_noList = blend_vat_noList;
	}





	public String getBlend_vat_no() {
		return blend_vat_no;
	}


	public void setBlend_vat_no(String blend_vat_no) {
		this.blend_vat_no = blend_vat_no;
	}





	public double getBlending_bl() {
		return blending_bl;
	}


	public void setBlending_bl(double blending_bl) {
		this.blending_bl = blending_bl;
	}


	public double getBlending_al() {
		return blending_al;
	}


	public void setBlending_al(double blending_al) {
		this.blending_al = blending_al;
	}


	


	


	public void blendinglistner(ValueChangeEvent vat) {
		try {
			String val = (String) vat.getNewValue();
			int vat_id=Integer.parseInt(String.valueOf(val));
			new TransferFromBlendingToBottlingVatImpl().getBlendingdata(vat_id,this);
			//impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}
	
	//blendinglistner
	public void bottlinglistner(ValueChangeEvent ae) {
		try {
			String val = (String) ae.getNewValue();
			int vat_id=Integer.parseInt(String.valueOf(val));
			new TransferFromBlendingToBottlingVatImpl().getbottlingQuantityStrength(vat_id,this);
			//impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}
	//bottlinglistner
	
	ArrayList saveData = new ArrayList ();
	
	public ArrayList getSaveData() {
		try{
		this.saveData =	impl.getsaveData(this);
		}
		catch(Exception e)
		{
			
		}
		return saveData;
	}


	public void setSaveData(ArrayList saveData) {
		this.saveData = saveData;
	}

	private int vat_max_id;
	
	
	public int getVat_max_id() {
		return vat_max_id;
	}


	public void setVat_max_id(int vat_max_id) {
		this.vat_max_id = vat_max_id;
	}
private boolean validateInput;
	
	
	public boolean isValidateInput() {
		validateInput = true;
		//FL41_Validation val = new FL41_Validation();
		
		
		if (validateInput) {
			if (this.bottling_no.length()!=0 && this.bottling_no == null) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Bottling  Vat !", "Please select Bottling Vat  !")); 
			}
			else if (this.blend_vat_no.length()!=0 && this.blend_vat_no == null) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Blending Vat !", "Please select Blending Vat  !"));
				 
			}
			
			else if (this.bottling_bl_wast == 0 &&   this.bottling_al_wast == 0 ) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wastage cannot be Zero !", "Wastage cannot be Zero !"));
				 
			}
			else if (this.bottling_bl_trnfr == 0 &&   this.bottling_al_trnfr == 0 ) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Quantity to be transfered cannot be Zero !", "Quantity to be transfered cannot be Zero !"));
				 
			}
		
		
		}
		return validateInput;
	}


	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}
    
	public void saveMethod(){
		try{
			
			double totalbl=this.bottling_bl_wast+this.bottling_bl_trnfr;
			double blendingBL=this.getBlending_bl();
			
		
			if (isValidateInput()) {
				if(totalbl <= blendingBL){
				
			impl.saveData(this);
				}
				else{
						FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sum of Wastage BL & Quantity to be transfered BL less then or equal to Spirit Vat BL  !", "Sum of Wastage BL & Quantity to be transfered BL less then or equal to Spirit Vat BL!"));
				}
			}
			
		}
		catch(Exception e){
			
		}
		
	}
	
	
	public ArrayList getBottling_noList() {
		this.bottling_noList=impl.getbottlingStoragelist(this);
		return bottling_noList;
	}


	public void setBottling_noList(ArrayList bottling_noList) {
		this.bottling_noList = bottling_noList;
	}


	public String getBottling_no() {
		return bottling_no;
	}


	public void setBottling_no(String bottling_no) {
		this.bottling_no = bottling_no;
	}


	public double getBottling_bl() {
		return bottling_bl;
	}


	public void setBottling_bl(double bottling_bl) {
		this.bottling_bl = bottling_bl;
	}


	public double getBottling_al() {
		return bottling_al;
	}


	public void setBottling_al(double bottling_al) {
		this.bottling_al = bottling_al;
	}


	public double getBottling_bl_bfr() {
		return bottling_bl_bfr;
	}


	public void setBottling_bl_bfr(double bottling_bl_bfr) {
		this.bottling_bl_bfr = bottling_bl_bfr;
	}


	public double getBottling_strngth_bfr() {
		return bottling_strngth_bfr;
	}


	public void setBottling_strngth_bfr(double bottling_strngth_bfr) {
		this.bottling_strngth_bfr = bottling_strngth_bfr;
	}


	public double getBottling_al_bfr() {
		try {
			
			
			bottling_al_bfr=((bottling_bl_bfr * bottling_strngth_bfr) / 100);     
	            
			} catch (Exception e) {
				// TODO: handle exception
			}
		return bottling_al_bfr;
	}


	public void setBottling_al_bfr(double bottling_al_bfr) {
		this.bottling_al_bfr = bottling_al_bfr;
	}


	public double getBottling_bl_aftr() {
		return bottling_bl_aftr;
	}


	public void setBottling_bl_aftr(double bottling_bl_aftr) {
		this.bottling_bl_aftr = bottling_bl_aftr;
	}


	public double getBottling_strnght_aftr() {
		return bottling_strnght_aftr;
	}


	public void setBottling_strnght_aftr(double bottling_strnght_aftr) {
		this.bottling_strnght_aftr = bottling_strnght_aftr;
	}


	public double getBottling_al_aftr() {
		 try {
				
				
			 bottling_al_aftr=((bottling_bl_aftr * bottling_strnght_aftr) / 100);     
	            
			} catch (Exception e) {
				// TODO: handle exception
			}
		return bottling_al_aftr;
	}


	public void setBottling_al_aftr(double bottling_al_aftr) {
		this.bottling_al_aftr = bottling_al_aftr;
	}


	public double getBottling_bl_wast() {
		this.bottling_bl_wast=this.bottling_bl-this.bottling_bl_bfr;
		return bottling_bl_wast;
	}


	public void setBottling_bl_wast(double bottling_bl_wast) {
		this.bottling_bl_wast = bottling_bl_wast;
	}


	public double getBottling_al_wast() {
		this.bottling_al_wast=this.bottling_al-this.bottling_al_bfr;
		return bottling_al_wast;
	}


	public void setBottling_al_wast(double bottling_al_wast) {
		this.bottling_al_wast = bottling_al_wast;
	}


	public double getBottling_bl_trnfr() {
		this.bottling_bl_trnfr=this.bottling_bl_aftr-this.bottling_bl_bfr;
		return bottling_bl_trnfr;
	}


	public void setBottling_bl_trnfr(double bottling_bl_trnfr) {
		this.bottling_bl_trnfr = bottling_bl_trnfr;
	}


	public double getBottling_al_trnfr() {
		this.bottling_al_trnfr=this.bottling_al_aftr-this.bottling_al_bfr;
		return bottling_al_trnfr;
	}


	public void setBottling_al_trnfr(double bottling_al_trnfr) {
		this.bottling_al_trnfr = bottling_al_trnfr;
	}


	public int getFl3_distillri_id() {
		return fl3_distillri_id;
	}


	public void setFl3_distillri_id(int fl3_distillri_id) {
		this.fl3_distillri_id = fl3_distillri_id;
	}


	public String getFl3_distillri_nm() {
		return fl3_distillri_nm;
	}


	public void setFl3_distillri_nm(String fl3_distillri_nm) {
		this.fl3_distillri_nm = fl3_distillri_nm;
	}


	public String getFl3_distillri_addrss() {
		return fl3_distillri_addrss;
	}


	public void setFl3_distillri_addrss(String fl3_distillri_addrss) {
		this.fl3_distillri_addrss = fl3_distillri_addrss;
	}


	public double getBottling_al_up() {
		this.bottling_al_up=this.getBottling_al() +this.getBottling_al_trnfr() ;
		return bottling_al_up;
	}


	public void setBottling_al_up(double bottling_al_up) {
		this.bottling_al_up = bottling_al_up;
	}


	public double getBottling_bl_up() {
		this.bottling_bl_up=this.getBottling_bl() +this.getBottling_bl_trnfr() ;
		return bottling_bl_up;
	}


	public void setBottling_bl_up(double bottling_bl_up) {
		this.bottling_bl_up = bottling_bl_up;
	}


	public double getBlending_vat_bl_up() {
		this.blending_vat_bl_up=this.getBlending_bl() + this.getBottling_bl_wast() +this.getBottling_bl_trnfr();
		return blending_vat_bl_up;
	}


	public void setBlending_vat_bl_up(double blending_vat_bl_up) {
		this.blending_vat_bl_up = blending_vat_bl_up;
	}


	public double getBlending_vat_al_up() {
		this.blending_vat_al_up=this.getBlending_al() + this.getBottling_al_wast() +this.getBottling_al_trnfr();
		return blending_vat_al_up;
	}


	public void setBlending_vat_al_up(double blending_vat_al_up) {
		this.blending_vat_al_up = blending_vat_al_up;
	}


	public void reset(){
		this.bottling_no=null;
		this.blend_vat_no=null;
		this.blend_vat_noList.clear();
		this.bottling_bl=0.0;
		this.bottling_al=0.0;
		this.blending_bl=0.0;
		this.blending_al=0.0;
		this.bottling_bl_bfr=0.0;
		this.bottling_al_bfr=0.0;
		this.bottling_strngth_bfr=0.0;
		this.bottling_bl_aftr=0.0;
		this.bottling_al_aftr=0.0;
		this.bottling_strnght_aftr=0.0;
		this.bottling_bl_wast=0.0;
		this.bottling_al_wast=0.0;
		this.bottling_bl_trnfr=0.0;
		this.bottling_al_trnfr=0.0;
		
		
		
	}
	

	private double bottling_al_up;
	private double bottling_bl_up;
	private double blending_vat_bl_up;
	private double blending_vat_al_up;

	

}
