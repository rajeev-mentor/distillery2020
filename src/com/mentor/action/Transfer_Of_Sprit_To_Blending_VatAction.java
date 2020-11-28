package com.mentor.action;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.mentor.impl.PD2recievingImpl;
import com.mentor.impl.Transfer_Of_Sprit_To_Blending_VatImpl;

public class Transfer_Of_Sprit_To_Blending_VatAction {
	
	Transfer_Of_Sprit_To_Blending_VatImpl impl =new Transfer_Of_Sprit_To_Blending_VatImpl();
	
	
	private String hidden;
	ArrayList spirt_noList = new ArrayList ();
	ArrayList blend_vat_noList = new ArrayList ();
	private String spirt_no;
	private String blend_vat_no;
	private double spirt_bl;
	private double spirt_al;
	private double blending_bl;
	private double blending_al;
	private double blending_bl_bfr;
	private double blending_strngth_bfr;
	private double blending_al_bfr;
	private double blending_bl_aftr;
	private double blending_strnght_aftr;
	private double blending_al_aftr;
	private double blending_bl_wast;
	private double blending_al_wast;
	private double blending_bl_trnfr;
	private double blending_al_trnfr;
	private int distillri_id;
	private String distillri_nm;
	private String distillri_addrss;
	private Date currentDate=new Date();
	
	public Date getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}


	public int getDistillri_id() {
		return distillri_id;
	}


	public void setDistillri_id(int distillri_id) {
		this.distillri_id = distillri_id;
	}


	public String getDistillri_nm() {
		return distillri_nm;
	}


	public void setDistillri_nm(String distillri_nm) {
		this.distillri_nm = distillri_nm;
	}


	public String getDistillri_addrss() {
		return distillri_addrss;
	}


	public void setDistillri_addrss(String distillri_addrss) {
		this.distillri_addrss = distillri_addrss;
	}


	public String getHidden() {
		try{
			impl.getSugarmill(this);
		}
		catch(Exception e){
			
		}
		return hidden;
	}


	public void setHidden(String hidden) {
		this.hidden = hidden;
	}


	public ArrayList getSpirt_noList() {
		this.spirt_noList=impl.getSpiritVatList(this);
		return spirt_noList;
	}


	public void setSpirt_noList(ArrayList spirt_noList) {
		this.spirt_noList = spirt_noList;
	}


	public ArrayList getBlend_vat_noList() {
		this.blend_vat_noList=impl.getblendingStoragelist(this);
		return blend_vat_noList;
	}


	public void setBlend_vat_noList(ArrayList blend_vat_noList) {
		this.blend_vat_noList = blend_vat_noList;
	}


	public String getSpirt_no() {
		return spirt_no;
	}


	public void setSpirt_no(String spirt_no) {
		this.spirt_no = spirt_no;
	}


	public String getBlend_vat_no() {
		return blend_vat_no;
	}


	public void setBlend_vat_no(String blend_vat_no) {
		this.blend_vat_no = blend_vat_no;
	}


	public double getSpirt_bl() {
		return spirt_bl;
	}


	public void setSpirt_bl(double spirt_bl) {
		this.spirt_bl = spirt_bl;
	}


	public double getSpirt_al() {
		return spirt_al;
	}


	public void setSpirt_al(double spirt_al) {
		this.spirt_al = spirt_al;
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


	public double getBlending_bl_bfr() {
		return blending_bl_bfr;
	}


	public void setBlending_bl_bfr(double blending_bl_bfr) {
		this.blending_bl_bfr = blending_bl_bfr;
	}


	public double getBlending_strngth_bfr() {
		return blending_strngth_bfr;
	}


	public void setBlending_strngth_bfr(double blending_strngth_bfr) {
		this.blending_strngth_bfr = blending_strngth_bfr;
	}


	public double getBlending_al_bfr() {
		 try {
				
				
			 blending_al_bfr=((blending_bl_bfr * blending_strngth_bfr) / 100);     
	            
			} catch (Exception e) {
				// TODO: handle exception
			}
		return blending_al_bfr;
	}


	public void setBlending_al_bfr(double blending_al_bfr) {
		this.blending_al_bfr = blending_al_bfr;
	}


	public double getBlending_bl_aftr() {
		return blending_bl_aftr;
	}


	public void setBlending_bl_aftr(double blending_bl_aftr) {
		this.blending_bl_aftr = blending_bl_aftr;
	}


	public double getBlending_strnght_aftr() {
		return blending_strnght_aftr;
	}


	public void setBlending_strnght_aftr(double blending_strnght_aftr) {
		this.blending_strnght_aftr = blending_strnght_aftr;
	}


	public double getBlending_al_aftr() {
		 try {
				
				
			 blending_al_aftr=((blending_bl_aftr * blending_strnght_aftr) / 100);     
	            
			} catch (Exception e) {
				// TODO: handle exception
			}
		return blending_al_aftr;
	}


	public void setBlending_al_aftr(double blending_al_aftr) {
		this.blending_al_aftr = blending_al_aftr;
	}


	public double getBlending_bl_wast() {
		this.blending_bl_wast=this.blending_bl-this.blending_bl_bfr;
		return blending_bl_wast;
	}


	public void setBlending_bl_wast(double blending_bl_wast) {
		this.blending_bl_wast = blending_bl_wast;
	}


	public double getBlending_al_wast() {
		this.blending_al_wast=this.blending_al-this.blending_al_bfr;
		return blending_al_wast;
	}


	public void setBlending_al_wast(double blending_al_wast) {
		this.blending_al_wast = blending_al_wast;
	}


	public double getBlending_bl_trnfr() {
		this.blending_bl_trnfr=this.blending_bl_aftr-this.blending_bl_bfr;
		return blending_bl_trnfr;
	}


	public void setBlending_bl_trnfr(double blending_bl_trnfr) {
		this.blending_bl_trnfr = blending_bl_trnfr;
	}


	public double getBlending_al_trnfr() {
		this.blending_al_trnfr=this.blending_al_aftr-this.blending_al_bfr;
		return blending_al_trnfr;
	}


	public void setBlending_al_trnfr(double blending_al_trnfr) {
		this.blending_al_trnfr = blending_al_trnfr;
	}


	public void spirit_vatListner(ValueChangeEvent ae) {
		try {
			String val = (String) ae.getNewValue();
			int vat_id=Integer.parseInt(String.valueOf(val));
			new Transfer_Of_Sprit_To_Blending_VatImpl().getQuantityStrength(vat_id,this);
			//impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}
	public void blendinglistner(ValueChangeEvent vat) {
		try {
			String val = (String) vat.getNewValue();
			int vat_id=Integer.parseInt(String.valueOf(val));
			new Transfer_Of_Sprit_To_Blending_VatImpl().getBlendingdata(vat_id,this);
			//impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}
	//blendinglistner
	
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
			if (this.spirt_no.length()!=0 && this.spirt_no == null) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Spirit Vat !", "Please select Spirit Vat  !")); 
			}
			else if (this.blend_vat_no.length()!=0 && this.blend_vat_no == null) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Blending Vat !", "Please select Blending Vat  !"));
				 
			}
			
			else if (this.blending_bl_wast == 0 &&   this.blending_al_wast == 0 ) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wastage cannot be Zero !", "Wastage cannot be Zero !"));
				 
			}
			else if (this.blending_bl_trnfr == 0 &&   this.blending_al_trnfr == 0 ) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Quantity to be transfered cannot be Zero !", "Quantity to be transfered cannot be Zero !"));
				 
			}
			/*else if (this.toVatBl_trnfr != this.dt_bl && this.toVatAl_trnfr != this.dt_al ) {
				this.validateInput = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Quantity To Be transfered Al & Bl Should Be Equal to Gatapass AL & BL"));	
			}*/
		
		}
		return validateInput;
	}


	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}
    
	public void saveMethod(){
		try{
			
			double totalbl=this.blending_bl_wast+this.blending_bl_trnfr;
			double spiritBL=this.getSpirt_bl();
			
		
			if (isValidateInput()) {
				if(totalbl <= spiritBL){
				
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
	
	/*public void saveMethod(){
		try{
			if (isValidateInput()) {
				if(  this.blending_bl_trnfr>0 &&   this.blending_al_trnfr>0 ){
		     
		   new Transfer_Of_Sprit_To_Blending_VatImpl().saveData(this);
				}
				else{
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,"Quantity to be transfered cannot be Zero !", "Quantity to be transfered cannot be Zero !"));
				}
		     
			}
			}
			catch(Exception e)
			{
				
			}
		
	}*/
	
	public void reset(){
		this.blend_vat_no=null;
		this.spirt_no=null;
		this.spirt_noList.clear();
		this.blend_vat_noList.clear();
		this.spirt_bl=0.0;
		this.spirt_al=0.0;
		this.blending_bl=0.0;
		this.blending_al=0.0;
		this.blending_bl_bfr=0.0;
		this.blending_al_bfr=0.0;
		this.blending_strngth_bfr=0.0;
		this.blending_bl_aftr=0.0;
		this.blending_al_aftr=0.0;
		this.blending_strnght_aftr=0.0;
		this.blending_bl_wast=0.0;
		this.blending_al_wast=0.0;
		this.blending_bl_trnfr=0.0;
		this.blending_al_trnfr=0.0;
		
		
		
	}
	

	private double spritvat_al_up;
	private double spritvat_bl_up;
	private double blending_vat_bl_up;
	private double blending_vat_al_up;

	public double getBlending_vat_bl_up() {
		this.blending_vat_bl_up=this.getBlending_bl() + this.getBlending_bl_trnfr() ;
		return blending_vat_bl_up;
	}


	public void setBlending_vat_bl_up(double blending_vat_bl_up) {
		this.blending_vat_bl_up = blending_vat_bl_up;
	}


	public double getBlending_vat_al_up() {
		this.blending_vat_al_up=this.getBlending_al() +this.getBlending_al_trnfr() ;
		return blending_vat_al_up;
	}


	public void setBlending_vat_al_up(double blending_vat_al_up) {
		this.blending_vat_al_up = blending_vat_al_up;
	}


	public double getSpritvat_al_up() {
		this.spritvat_al_up=this.getSpirt_al() + this.getBlending_al_wast() +this.getBlending_al_trnfr();
		return spritvat_al_up;
	}


	public void setSpritvat_al_up(double spritvat_al_up) {
		this.spritvat_al_up = spritvat_al_up;
	}


	public double getSpritvat_bl_up() {
		this.spritvat_bl_up=this.getSpirt_bl() + this.getBlending_bl_wast() +this.getBlending_bl_trnfr();
		return spritvat_bl_up;
	}


	public void setSpritvat_bl_up(double spritvat_bl_up) {
		this.spritvat_bl_up = spritvat_bl_up;
	}

	
	//////////////////////////////fl3 3a/////////////////////////////////////////////////
	
	private String fl3_hidden;
	ArrayList      fl3_spirt_noList = new ArrayList ();
	ArrayList      fl3_blend_vat_noList = new ArrayList ();
	private String fl3_spirt_no;
	private String fl3_blend_vat_no;
	private String fl3_spirt_bl;
	private String fl3_spirt_al;
	private int    fl3_blending_bl;
	private int    fl3_blending_al;
	private int    fl3_blending_bl_bfr;
	private int    fl3_blending_strngth_bfr;
	private int    fl3_blending_al_bfr;
	private int    fl3_blending_bl_aftr;
	private int    fl3_blending_strnght_aftr;
	private int    fl3_blending_al_aftr;
	private int    fl3_blending_bl_wast;
	private int    fl3_blending_al_wast;
	private int    fl3_blending_bl_trnfr;
	private int    fl3_blending_al_trnfr;
	private int    fl3_distillri_id;
	private String fl3_distillri_nm;
	private String fl3_distillri_addrss;
	
	private Date   fl3_currentDate=new Date();

	public String getFl3_hidden() {
		
		fl3_hidden=impl.getDetails(this);
		return fl3_hidden;
	}


	public void setFl3_hidden(String fl3_hidden) {
		this.fl3_hidden = fl3_hidden;
	}


	public ArrayList getFl3_spirt_noList() {
		this.fl3_spirt_noList=impl.getfl3_SpiritVatList(this);
		return fl3_spirt_noList;
	}


	public void setFl3_spirt_noList(ArrayList fl3_spirt_noList) {
		this.fl3_spirt_noList = fl3_spirt_noList;
	}


	public ArrayList getFl3_blend_vat_noList() {
		fl3_blend_vat_noList=impl.getfl3_blendingStoragelist(this);
		return fl3_blend_vat_noList;
	}


	public void setFl3_blend_vat_noList(ArrayList fl3_blend_vat_noList) {
		this.fl3_blend_vat_noList = fl3_blend_vat_noList;
	}


	public String getFl3_spirt_no() {
		return fl3_spirt_no;
	}


	public void setFl3_spirt_no(String fl3_spirt_no) {
		this.fl3_spirt_no = fl3_spirt_no;
	}


	public String getFl3_blend_vat_no() {
		return fl3_blend_vat_no;
	}


	public void setFl3_blend_vat_no(String fl3_blend_vat_no) {
		this.fl3_blend_vat_no = fl3_blend_vat_no;
	}


	public String getFl3_spirt_bl() {
		return fl3_spirt_bl;
	}


	public void setFl3_spirt_bl(String fl3_spirt_bl) {
		this.fl3_spirt_bl = fl3_spirt_bl;
	}


	public String getFl3_spirt_al() {
		return fl3_spirt_al;
	}


	public void setFl3_spirt_al(String fl3_spirt_al) {
		this.fl3_spirt_al = fl3_spirt_al;
	}


	public int getFl3_blending_bl() {
		return fl3_blending_bl;
	}


	public void setFl3_blending_bl(int fl3_blending_bl) {
		this.fl3_blending_bl = fl3_blending_bl;
	}


	public int getFl3_blending_al() {
		return fl3_blending_al;
	}


	public void setFl3_blending_al(int fl3_blending_al) {
		this.fl3_blending_al = fl3_blending_al;
	}


	public int getFl3_blending_bl_bfr() {
		return fl3_blending_bl_bfr;
	}


	public void setFl3_blending_bl_bfr(int fl3_blending_bl_bfr) {
		this.fl3_blending_bl_bfr = fl3_blending_bl_bfr;
	}


	public int getFl3_blending_strngth_bfr() {
		return fl3_blending_strngth_bfr;
	}


	public void setFl3_blending_strngth_bfr(int fl3_blending_strngth_bfr) {
		this.fl3_blending_strngth_bfr = fl3_blending_strngth_bfr;
	}


	public int getFl3_blending_al_bfr() {
		 try {
				
				
			 fl3_blending_al_bfr=((fl3_blending_bl_bfr * fl3_blending_strngth_bfr) / 100);     
	            
			} catch (Exception e) {
				// TODO: handle exception
			}
		return fl3_blending_al_bfr;
	}


	public void setFl3_blending_al_bfr(int fl3_blending_al_bfr) {
		this.fl3_blending_al_bfr = fl3_blending_al_bfr;
	}


	public int getFl3_blending_bl_aftr() {
		return fl3_blending_bl_aftr;
	}


	public void setFl3_blending_bl_aftr(int fl3_blending_bl_aftr) {
		this.fl3_blending_bl_aftr = fl3_blending_bl_aftr;
	}


	public int getFl3_blending_strnght_aftr() {
		return fl3_blending_strnght_aftr;
	}


	public void setFl3_blending_strnght_aftr(int fl3_blending_strnght_aftr) {
		this.fl3_blending_strnght_aftr = fl3_blending_strnght_aftr;
	}


	public int getFl3_blending_al_aftr() {
		 try {
				
				
			 fl3_blending_al_aftr=((fl3_blending_bl_aftr * fl3_blending_strnght_aftr) / 100);     
	            
			} catch (Exception e) {
				// TODO: handle exception
			}
		return fl3_blending_al_aftr;
	}


	public void setFl3_blending_al_aftr(int fl3_blending_al_aftr) {
		this.fl3_blending_al_aftr = fl3_blending_al_aftr;
	}


	public int getFl3_blending_bl_wast() {
		this.fl3_blending_bl_wast=this.fl3_blending_bl-this.fl3_blending_bl_bfr;
		return fl3_blending_bl_wast;
	}


	public void setFl3_blending_bl_wast(int fl3_blending_bl_wast) {
		this.fl3_blending_bl_wast = fl3_blending_bl_wast;
	}


	public int getFl3_blending_al_wast() {
		this.fl3_blending_al_wast=this.fl3_blending_al-this.fl3_blending_al_bfr;
		return fl3_blending_al_wast;
	}


	public void setFl3_blending_al_wast(int fl3_blending_al_wast) {
		this.fl3_blending_al_wast = fl3_blending_al_wast;
	}


	public int getFl3_blending_bl_trnfr() {
		this.fl3_blending_bl_trnfr=this.fl3_blending_bl_aftr-this.fl3_blending_bl_bfr;
		return fl3_blending_bl_trnfr;
	}


	public void setFl3_blending_bl_trnfr(int fl3_blending_bl_trnfr) {
		this.fl3_blending_bl_trnfr = fl3_blending_bl_trnfr;
	}


	public int getFl3_blending_al_trnfr() {
		this.fl3_blending_al_trnfr=this.fl3_blending_al_aftr-this.fl3_blending_al_bfr;
		return fl3_blending_al_trnfr;
	}


	public void setFl3_blending_al_trnfr(int fl3_blending_al_trnfr) {
		this.fl3_blending_al_trnfr = fl3_blending_al_trnfr;
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


	public Date getFl3_currentDate() {
		return fl3_currentDate;
	}


	public void setFl3_currentDate(Date fl3_currentDate) {
		this.fl3_currentDate = fl3_currentDate;
	}
	
	public void fl3_spirit_vatListner(ValueChangeEvent ae) {
		try {
			String val = (String) ae.getNewValue();
			int vat_id=Integer.parseInt(String.valueOf(val));
			new Transfer_Of_Sprit_To_Blending_VatImpl().getfl3_QuantityStrength(vat_id,this);
			//impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}
	public void fl3_blendinglistner(ValueChangeEvent vat) {
		try {
			String val = (String) vat.getNewValue();
			int vat_id=Integer.parseInt(String.valueOf(val));
			new Transfer_Of_Sprit_To_Blending_VatImpl().getfl3_Blendingdata(vat_id,this);
			//impl.getQuantity(this, val);

		} catch (Exception e) {

		}
	}
	
private boolean validateInputfl3;
	
	
	public boolean isvalidateInputfl3() {
		validateInputfl3 = true;
		//FL41_Validation val = new FL41_Validation();
		
		
		if (validateInputfl3) {
			if (this.fl3_spirt_no.length()!=0 && this.fl3_spirt_no == null) {
				this.validateInputfl3 = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Spirit Vat !", "Please select Spirit Vat  !")); 
			}
			else if (this.fl3_blend_vat_no.length()!=0 && this.fl3_blend_vat_no == null) {
				this.validateInputfl3 = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please select Blending Vat !", "Please select Blending Vat  !"));
				 
			}
			/*else if (this.toVatBl_trnfr != this.dt_bl && this.toVatAl_trnfr != this.dt_al ) {
				this.validateInputfl3 = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Quantity To Be transfered Al & Bl Should Be Equal to Gatapass AL & BL"));	
			}*/
		
		}
		return validateInputfl3;
	}


	public void setValidateInputfl3(boolean validateInputfl3) {
		this.validateInputfl3 = validateInputfl3;
	}
	public void saveMethodfl3(){
		try{
			if (isvalidateInputfl3()) {
				if(  this.fl3_blending_bl_trnfr>0 &&   this.fl3_blending_al_trnfr>0 ){
		     
		   new Transfer_Of_Sprit_To_Blending_VatImpl().saveDatafl3(this);
				}
				else{
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,"Quantity to be transfered cannot be Zero !", "Quantity to be transfered cannot be Zero !"));
				}
		     
			}
			}
			catch(Exception e)
			{
				
			}
		
	}
ArrayList fl3_saveData = new ArrayList ();
	
	public ArrayList getfl3_saveData() {
		try{
		this.fl3_saveData =	impl.getsaveDatafl3(this);
		}
		catch(Exception e)
		{
			
		}
		return fl3_saveData;
	}


	public void setFl3_saveData(ArrayList fl3_saveData) {
		this.fl3_saveData = fl3_saveData;
	}

	public void fl3_reset(){
		this.blend_vat_no=null;
		this.spirt_no=null;
		this.spirt_noList.clear();
		this.blend_vat_noList.clear();
		this.spirt_bl=0.0;
		this.spirt_al=0.0;
		this.blending_bl=0;
		this.blending_al=0;
		this.blending_bl_bfr=0;
		this.blending_al_bfr=0;
		this.blending_strngth_bfr=0;
		this.blending_bl_aftr=0;
		this.blending_al_aftr=0;
		this.blending_strnght_aftr=0;
		this.blending_bl_wast=0;
		this.blending_al_wast=0;
		this.blending_bl_trnfr=0;
		this.blending_al_trnfr=0;
		
		
		
	}
}
