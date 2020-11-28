package com.mentor.action;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.richfaces.component.UIDataTable; 

import com.mentor.Datatable.fl3_3a_vat_dt;
import com.mentor.impl.fl3_3a_vat_impl;
import com.mentor.utility.Validate;

public class fl3_3a_vat_action {
	fl3_3a_vat_impl impl=new fl3_3a_vat_impl();
    
	//-------------------hidden variable------------------------------------
	private String hidden;
	private boolean hidden_flag=true;
	public boolean isHidden_flag() {
		return hidden_flag;
	}
    public void setHidden_flag(boolean hidden_flag) {
		this.hidden_flag = hidden_flag;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public String getHidden() {
		if (this.hidden_flag){
		//System.out.println("========hidden======"+this.hidden_flag);	
		impl.getUserDetails(this);
	
		this.hidden_flag=false;
		//System.out.println("hidden flag======="+this.hidden_flag);

		}
		return hidden;
	}
//----------------------------------------------variable login------------------------------------------------------------------------------------
	private int int_app_id;
	private int int_dist_id;
	private String lic_no;
	private String lic_type;
	private String unit_name;
	private String unit_address;

	public int getInt_app_id() {
		return int_app_id;
	}
	public void setInt_app_id(int int_app_id) {
		this.int_app_id = int_app_id;
	}
	public int getInt_dist_id() {
		return int_dist_id;
	}
	public void setInt_dist_id(int int_dist_id) {
		this.int_dist_id = int_dist_id;
	}
	public String getLic_no() {
		return lic_no;
	}
	public void setLic_no(String lic_no) {
		this.lic_no = lic_no;
	}
	public String getLic_type() {
		return lic_type;
	}

	public void setLic_type(String lic_type) {
		this.lic_type = lic_type;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getUnit_address() {
		return unit_address;
	}
	public void setUnit_address(String unit_address) {
		this.unit_address = unit_address;
	}
	
	//-----------------------------------------------------close-------------------------------------------------------------------------
	//-------------------------radio variable------------------------------------------
	private String radio="BT";
	public String getRadio() {
	//	dt.setRadio(this.getRadio());
		return radio;
	}
	
	
	public void setRadio(String radio) {
		this.radio = radio;
	}
	fl3_3a_vat_dt dt;
	
	public fl3_3a_vat_dt getDt() {
		return dt;
	}

	public void setDt(fl3_3a_vat_dt dt) {
		this.dt = dt;
	}

	public void radioListener(ValueChangeEvent e) {
		try {
			String radio = (String) e.getNewValue();
	//		System.out.println("-----------------radio-----------111--"+this.getRadio());
			this.setRadio(radio);
			
			this.displaylist=impl.displaylistImpl2(radio, dt);
	//		System.out.println("===============radio----"+radio);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	//--------------------------------radio--methods ----close------------------------------------------------------------------------------
    //--------------------------------table -displaylist--------------------------------------------------------------------------------------
	private ArrayList displaylist=new ArrayList();
	private boolean displayflag=true;

	public ArrayList getDisplaylist() {
		if (displayflag) {
			this.displaylist=impl.displaylistImpl2(radio ,dt);
			this.setDisplayflag(false);
		}
		
		return displaylist;
	}

	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}
	public boolean isDisplayflag() {
		return displayflag;
	}

	public void setDisplayflag(boolean displayflag) {
		this.displayflag = displayflag;
	}
	//-------------------------------------------------------------displaylist---close------------------------------------------------------
        //-----------------add row methods-----------------------------------------------------

private boolean addflg=true;
	public String addRowMethod() {
			//if (addflg=true) {
				fl3_3a_vat_dt dt = new fl3_3a_vat_dt();
				dt.setSno(this.displaylist.size() + 1);
				displaylist.add(dt);
		//		this.addflg=false;
		//	}
			
		///	this.setDisplayflag(false);
		
		return "";
	}
	//-------------------------------Delete Row Methods------------------------------------------------------------------------------------
	public void deleteRowMethod(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		fl3_3a_vat_dt dt = (fl3_3a_vat_dt) this.displaylist.get(uiTable.getRowIndex());
		this.displaylist.remove(dt);
	}
	
	//--------------------------------------Save Methods------------------------------------------------------------------------------
   
	private boolean validateReg_packaging;
	private boolean validateReg ;
	public boolean isValidateReg() {
		validateReg = true;
		
		if (validateReg) {
			if (this.displaylist.size() > 0) {
				for (int i = 0; i < this.displaylist.size(); i++) {
					fl3_3a_vat_dt table = new fl3_3a_vat_dt();
						table = (fl3_3a_vat_dt) this.displaylist.get(i);
					 
					if (!(Validate.validateStrReq("vat_name", String.valueOf(table.getVat_name()))))validateReg=false;
					else if (!(Validate.validateOnlyDouble("cap", String.valueOf(table.getCap()))))validateReg=false; 
					 
				}
			}
		}
		return validateReg;
	}

	public boolean isValidateReg_packaging() {
		return validateReg_packaging;
	}
	public void setValidateReg_packaging(boolean validateReg_packaging) {
		this.validateReg_packaging = validateReg_packaging;
	}
	public void setValidateReg(boolean validateReg) {
		this.validateReg = validateReg;
	}
	
	
	
	public void save(){
    	int k=0;
    	int j=0;
    	if (isValidateReg()) {
			
		
    	for (int i = 0; i < this.getDisplaylist().size(); i++) {
    		fl3_3a_vat_dt dt=(fl3_3a_vat_dt) this.getDisplaylist().get(i);
    		/*if (!(Validate.validateStrReq("Vat Name ", dt.getVat_name())))validateReg_packaging = false;
			else if (!(Validate.validateDouble("Quantity", dt.getCap())))validateReg_packaging = false;*/
    		
    		if (impl.select(dt.getStorage_id())) {
    				k+=	impl.update(dt.getStorage_id() ,this ,dt);
					}else {
					j+=	impl.save1(this ,dt);
					}
		}
    	this.setValidateReg(false);
    	}
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved"+j+"  New Row Successfully And Updated "+k+" Row Successfully", "Saved"+j+"  New Row Successfully And Updated "+k+" Row Successfully"));
  //  this.displaylist=impl.displaylistImpl2(radio ,dt);
    
	this.displayflag=false;
	}
	
}
