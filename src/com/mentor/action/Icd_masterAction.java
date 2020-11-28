package com.mentor.action;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mentor.impl.Icd_masterimpl;



public class Icd_masterAction {
	private String name;
	private String address;
	private String redio="1";
	private String ut;
	private String state;
	private ArrayList state_ind = new ArrayList();
	private ArrayList displaylist = new ArrayList();
    private boolean flag;
	private boolean sflag;
	private String msg;
	private boolean validateInput;
	
	//********************************getter and setter****************************
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRedio() {
		return redio;
	}
	public void setRedio(String redio) {
		this.redio = redio;
	}
	public String getUt() {
		return ut;
	}
	public void setUt(String ut) {
		this.ut = ut;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public ArrayList getState_ind() {
		try{
			this.state_ind =impl.getShowdata(this);
		}
		catch (Exception e){
			e.printStackTrace();			
		}
		return state_ind;
	}
	public void setState_ind(ArrayList state_ind) {
		this.state_ind = state_ind;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isSflag() {
		return sflag;
	}
	public void setSflag(boolean sflag) {
		this.sflag = sflag;
	}
	public ArrayList getDisplaylist() {
		this.displaylist=impl.getdata(this);
		return displaylist;
	}
	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setValidateInput(boolean validateInput) {
		this.validateInput = validateInput;
	}
	

	//********************method*************
	 Icd_masterimpl impl=new Icd_masterimpl();
	 
	 
		public void save(){
			
			 if(isValidateInput()){
				 
			impl.Icdmaster(this); 
			//this.state_ind =impl.getShowdata(this);
			this.displaylist=impl.getdata(this); 
			
			 }
			 
			 }
		
		public void reset(){
			
			this.name=null;
			this.address=null;
			this.ut=null;
		     this.redio="1";
		     this.state="";
			
		}
		
		//********************validation*********************
		
		public boolean isValidateInput() {
			System.out.println("---- validate --");
			
			validateInput=true;
			
			if(validateInput){
					if (this.name == null || this.name.length()==0) {
						System.out.println("---- validate --");
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill The Name","Please Fill The Name"));	
						
					}
								
					else if (this.address == null || this.address.length()==0) {
						System.out.println("---- validate --");
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Fill Address","Please Fill Address"));	
					}
					else if (this.redio.equalsIgnoreCase("1")) {
					 if (this.state == null || this.state.length()==0) {
						System.out.println("---- validate --");
						this.validateInput = false;
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Select State ","Please Select State "));	
					}
					}
					else if (this.redio.equalsIgnoreCase("2")) {
						 if (this.ut == null || this.ut.length()==0) {
							System.out.println("---- validate --");
							this.validateInput = false;
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please Enter UT","Please Enter  UT"));	
						}
						}
					
					
					
			}	 
				
			
			return validateInput;
		}

}
