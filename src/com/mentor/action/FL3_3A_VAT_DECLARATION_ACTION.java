package com.mentor.action;

import java.util.ArrayList;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.richfaces.component.UIDataTable;
import com.mentor.Datatable.FL3_3A_VAT_DECLARATION_DT;
import com.mentor.impl.FL3_3A_DECLARATION_IMPL;

import com.mentor.utility.Validate;


public class FL3_3A_VAT_DECLARATION_ACTION {	
	
	FL3_3A_VAT_DECLARATION_DT dt = new FL3_3A_VAT_DECLARATION_DT();
FL3_3A_DECLARATION_IMPL impl= new FL3_3A_DECLARATION_IMPL();
	private ArrayList addRowBranding = new ArrayList();
	private ArrayList storage_id= new ArrayList();
	private String hidden;
	private String dissteleryName;
	private int dissleryId;
	private boolean hiddenFlag=true;
	


	public String getHidden() {
		if (isHiddenFlag()){
		System.out.println("========hidden======");
		impl.getSugarmill(this);
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getDissteleryName() {
		return dissteleryName;
	}

	public void setDissteleryName(String dissteleryName) {
		this.dissteleryName = dissteleryName;
	}

	public int getDissleryId() {
		return dissleryId;
	}

	public void setDissleryId(int dissleryId) {
		this.dissleryId = dissleryId;
	}

	public boolean isHiddenFlag() {
		return hiddenFlag;
	}

	public void setHiddenFlag(boolean hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public FL3_3A_VAT_DECLARATION_DT getDt() {
		return dt;
	}

	public void setDt(FL3_3A_VAT_DECLARATION_DT dt) {
		this.dt = dt;
	}

	public ArrayList getStorage_id() {
		return storage_id;
	}

	public void setStorage_id(ArrayList storage_id) {
		this.storage_id = storage_id;
	}

	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}



	private String radio;
	public String getRadio() {
		return radio;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public ArrayList getAddRowBranding() {
		return addRowBranding;
	}

	public void setAddRowBranding(ArrayList addRowBranding) {
		this.addRowBranding = addRowBranding;
	}
	
	
	public String addRowMethodBranding() {
		
			FL3_3A_VAT_DECLARATION_DT dt = new FL3_3A_VAT_DECLARATION_DT();
			dt.setSrNo(addRowBranding.size() + 1);
			//dt.setTankid(impl.max());
			 
			addRowBranding.add(dt);
			
			 
		
		return "";
	}
	
	public void deleteRowMethodBranding(ActionEvent e) {
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
				.getParent();
		FL3_3A_VAT_DECLARATION_DT dt = (FL3_3A_VAT_DECLARATION_DT) this
				.getAddRowBranding().get(uiTable.getRowIndex());
		impl.deleterow(this,dt.getTankid());
		this.addRowBranding.remove(dt);
		this.addRowBranding=impl.spiritDetails(this);
	}
	
	public void radiomethod(ValueChangeEvent e){
		String val=(String) e.getNewValue();
		this.setRadio(val);
		//this.radio=val;
		this.addRowBranding=impl.spiritDetails(this);
		System.out.println("=======radio==========="+radio);
	
	}
	
public String save() {
   if (isValidateInput()) {
		FL3_3A_DECLARATION_IMPL impl=new FL3_3A_DECLARATION_IMPL();
		
			impl.updateData(this);
		
	}
		return "";

	}



private boolean validateInput;

public boolean isValidateInput() {
	validateInput = true;
//addRowBranding
    
    for (int i = 0; i < this.getAddRowBranding().size(); i++) {
    	FL3_3A_VAT_DECLARATION_DT dt = (FL3_3A_VAT_DECLARATION_DT) this.getAddRowBranding().get(i);
             
                     if (!(Validate.validateStrReq("Vate_Name", dt.getTankName())))
                            validateInput = false;
                     else if (!(Validate.validateDouble("capacity", dt.getCapacity())))
                                    validateInput = false;
                    
    }
	
	return validateInput;
} 





}
