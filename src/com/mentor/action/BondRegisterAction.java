package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import com.mentor.impl.BondRegisterImpl;


public class BondRegisterAction {
	
	BondRegisterImpl impl = new BondRegisterImpl();
			
	private String hidden;
	private String bondId;
	private ArrayList bondList = new ArrayList();
	private ArrayList registerNm_List = new ArrayList();
	private String regisId;
	private int bond_sbmisn;
	public String dist_name;
	public String getRadio_type() {
		return radio_type;
	}

	public void setRadio_type(String radio_type) {
		this.radio_type = radio_type;
	}

	private int dist_id;
	public String radio_type;
	
	public void radioVal(ValueChangeEvent ee) {

		String val = (String) ee.getNewValue();
		this.setRadio_type(val);
		this.bondList=impl.getBondlist(this);

	}

	public String getDist_name() {
		return dist_name;
	}

	public void setDist_name(String dist_name) {
		this.dist_name = dist_name;
	}

	public int getDist_id() {
		return dist_id;
	}

	public void setDist_id(int dist_id) {
		this.dist_id = dist_id;
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

	public String getBondId() {
		return bondId;
	}

	public void setBondId(String bondId) {
		this.bondId = bondId;
	}

	public ArrayList getBondList() {
		try{
			if(this.radio_type != null){
		this.bondList=impl.getBondlist(this);
			}
			else {
				
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Please Select Radio"));
			} 
		}
		catch(Exception e){
			
		}
		return bondList;
	}

	public void setBondList(ArrayList bondList) {
		this.bondList = bondList;
	}

	public ArrayList getRegisterNm_List() {
		//this.registerNm_List=impl.getRegisterlist(this);
		return registerNm_List;
	}

	public void setRegisterNm_List(ArrayList registerNm_List) {
		this.registerNm_List = registerNm_List;
	}

	public String getRegisId() {
		return regisId;
	}

	public void setRegisId(String regisId) {
		this.regisId = regisId;
	}

	public int getBond_sbmisn() {
		return bond_sbmisn;
	}

	public void setBond_sbmisn(int bond_sbmisn) {
		this.bond_sbmisn = bond_sbmisn;
	}

	public void bondlistner(ValueChangeEvent ae)
	{
		
		try {
			//if(this.bondId != null){
			System.out.println("=+bondlistner===");
			String val = (String) ae.getNewValue();
			this.setBond_id(Integer.parseInt(val));
			System.out.println("=+bond-val==="+val);
			System.out.println("=+getBond_id-val==="+this.getBond_id());
			this.registerNm_List=impl.getRegisterlist(this);
			//}

		} catch (Exception e) {

		}
	
		
	}
	
	private boolean storageValidate;

	public boolean isStorageValidate() {
		this.storageValidate = true;

		// ====country_id=====null ====unitNm
		// ====purchaseOrdrNo====0===date===Thu Sep 03 00:00:00 IST
		// 2020===recordFile===NA===validtyDt===Thu Sep 03 00:00:00 IST 2020
		if (storageValidate) {

			if (this.regDescription == null || this.regDescription.length() == 0) {
				this.storageValidate = false;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Please Enter register Name"));
			} else if (this.openValue == 0) {
				this.storageValidate = false;
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Please Enter openValue "));
			} 

		}

		return storageValidate;
	}

	public void setStorageValidate(boolean storageValidate) {
		this.storageValidate = storageValidate;
	}

	public String saveStorage() {

		try {
			System.out.print("ertetet");
			if (isStorageValidate()) {
				System.out.print("ertetet5767");
				new BondRegisterImpl().saveStorage(this);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	  public void reset1()
	  {   
		//  this.bondList=impl.getBondlist(this);
		  this.registerNm_List=impl.getRegisterlist(this);
		  this.regDescription=null;
		  this.openValue=0;
		 
	  }   
	
	private String regDescription;
	public String getRegDescription() {
		return regDescription;
	}

	public void setRegDescription(String regDescription) {
		this.regDescription = regDescription;
	}

	public int getOpenValue() {
		return openValue;
	}

	public void setOpenValue(int openValue) {
		this.openValue = openValue;
	}

	private int openValue;
	//********************validation*********************
	private boolean validateInput;
	
	
			public boolean isValidateInput() {
				System.out.println("---- validate --");
				
				validateInput=true;
				
				if(validateInput){
						if (this.bondId.equalsIgnoreCase("NA") || this.bondId.length()==0 || this.bondId ==null ) {
							System.out.println("---- validate --");
							this.validateInput = false;
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Select Bond Drop Down","Please Select Bond Drop Down"));	
							
						}
									
						else 	if (this.regisId.equalsIgnoreCase("NA") || this.regisId.length()==0 || this.regisId ==null ) {
							System.out.println("---- validate --");
							this.validateInput = false;
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Select Register Drop Down","Please Select Register Drop Down"));	
							
						}
						
						
						else 	if (this.bond_sbmisn ==0 ) {
							System.out.println("---- validate --");
							this.validateInput = false;
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Bond Submission","Please Fill Bond Submission"));	
							
						}
					
					
						
						
						
				}	 
					
				
				return validateInput;
			}
			public void setValidateInput(boolean validateInput) {
				this.validateInput = validateInput;
			}
	
	public void save(){
		
		 if(isValidateInput()){
			 
		impl.BondRegister(this); 
        }
		 
		 }
	
	private int bond_id;
	public int getBond_id() {
		return bond_id;
	}

	public void setBond_id(int bond_id) {
		this.bond_id = bond_id;
	}

	public void reset(){
		
		this.bondId="NA";
		this.regisId="NA";
		this.bond_sbmisn=0;
	    this.registerNm_List=impl.getRegisterlist(this);
	  
		
	}
	
	private ArrayList datalist = new ArrayList();

	public ArrayList getDatalist() {
		try {
			this.datalist = impl.getDataList(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datalist;
	}

	public void setDatalist(ArrayList datalist) {
		this.datalist = datalist;
	}
}
