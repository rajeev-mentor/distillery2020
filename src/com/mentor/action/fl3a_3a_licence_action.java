package com.mentor.action;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.richfaces.component.UIDataTable;


import com.mentor.Datatable.fl3a_3a_licence_dt;
import com.mentor.impl.fl3a_3a_licence_impl;
import com.mentor.utility.Validate;


public class fl3a_3a_licence_action {
	//--------------------------------------------------create impl class object----------------Created by Arvind-------------Verma----------------------------------------------------
	fl3a_3a_licence_impl impl=new fl3a_3a_licence_impl();
	
	//--------------------------Open ------------------------------------hidden variable--------------Created by Arvind-------------Verma---------------------------------------------------------
	private String hidden;
	private boolean hflag=true;
	public String getHidden() {
		if (hflag) {
		//	System.out.println("-------------------------hidden getter------------------------");
			impl.hidden(this);
			this.setHflag(false);
		}
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public boolean isHflag() {
		return hflag;
	}
	public void setHflag(boolean hflag) {
		this.hflag = hflag;
	}
	
	//---------------------------Close hidden -------------------------------------Created by Arvind-------------Verma----------------------------------------------------------------------
    //--------------------------------------------------------login------------------Created by Arvind-------------Verma------------------------------------
	private int brewery_id;
	private String lic_no;
	private String lic_type;
	public int getBrewery_id() {
		return brewery_id;
	}
	public void setBrewery_id(int brewery_id) {
		this.brewery_id = brewery_id;
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
	
	
	//-------------------------------------------------------------------radio-----------------Created by Arvind-------------Verma------------------------------------------------------------
	private String radio="BT";
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	
	public void radiomethods(ValueChangeEvent e) {
		try {
			String radio = (String) e.getNewValue();
			//System.out.println("-----------------radio-----------111--"+this.getRadio());
			this.setRadio(radio);
			this.displaylist=impl.displaylistImpl(radio);
		///	System.out.println("===============radio----"+radio);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	//---------------------------------------------displaylist---------------------------------Created by Arvind-------------Verma------------
	private ArrayList displaylist=new ArrayList();
	private boolean displaylist_flag=true;
	

	fl3a_3a_licence_dt dt;
	
	public fl3a_3a_licence_dt getDt() {
		return dt;
	}
	public void setDt(fl3a_3a_licence_dt dt) {
		this.dt = dt;
	}
	public ArrayList getDisplaylist() {
		if (displaylist_flag) {
			this.displaylist=impl.displaylistImpl(radio );
	        this.setDisplaylist_flag(false);
		}
		
		return displaylist;
	}
	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}
	public boolean isDisplaylist_flag() {
		return displaylist_flag;
	}
	public void setDisplaylist_flag(boolean displaylist_flag) {
		this.displaylist_flag = displaylist_flag;
	}

	  //-----------------add row methods-------------------------------------------------------Created by Arvind-------------Verma-------------------------------


		public String addRowMethod() {
				
				fl3a_3a_licence_dt dt = new fl3a_3a_licence_dt();
				dt.setSno(this.displaylist.size() + 1);
				displaylist.add(dt);
			
			return "";
		}
		//-------------------------------Delete Row Methods----------------------------------------Created by Arvind-------------Verma-----------------------------------------------
		public void deleteRowMethod(ActionEvent e) {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
			fl3a_3a_licence_dt dt = (fl3a_3a_licence_dt) this.displaylist.get(uiTable.getRowIndex());
			this.displaylist.remove(dt);
		}

	//--------------------------------------------Save-------by Arvind-Verma---------------------------------------------------------------------------------
	

		private boolean validateReg_packaging;
		private boolean validateReg ;
		public boolean isValidateReg() {
			validateReg = true;
			
			if (validateReg) {
				if (this.displaylist.size() > 0) {
					for (int i = 0; i < this.displaylist.size(); i++) {
						fl3a_3a_licence_dt table = new fl3a_3a_licence_dt();
							table = (fl3a_3a_licence_dt) this.displaylist.get(i);
						System.out.println("========before if =validateReg======");
						if (!(Validate.validateStrReq("vat_name", String.valueOf(table.getVat_name()))))validateReg=false;
						//else if (!(Validate.validateOnlyDouble("cap", String.valueOf(table.getCap()))))validateReg=false; 
						//else if (!(Validate.validateOnlyDouble("cap", table.getCap())))validateReg=false; 
						
						System.out.println("========after if and befor else =validateReg======");
					}
				}
			}
			return validateReg;
		}

		public boolean isValidateReg_packaging() {
			return validateReg_packaging;
		}
		public void save(){
			
			
			
			int k=0;
			int p=0;
	
	      for (int i = 0; i < this.getDisplaylist().size(); i++) {
	    	 
				fl3a_3a_licence_dt dt=(fl3a_3a_licence_dt) this.getDisplaylist().get(i);
				if (isValidateReg()) {
					if (impl.select(dt)) {
						 k+=impl.update(dt ,this);
						
						}else {
						 p+=	impl.save(this,dt);
					
						}
				}
			
			}
	     
	      
	      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved"+p+"  New Row Successfully And Updated "+k+" Row Successfully", "Saved"+k+"  New Row Successfully And Updated "+p+" Row Successfully"));
			this.displaylist=impl.displaylistImpl(radio);
		
	}

	
		
		
		
		
		
		
		
		
		
		


}
