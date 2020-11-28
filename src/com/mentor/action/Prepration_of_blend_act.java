package com.mentor.action;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.mentor.Datatable.Prepration_of_blend_dt;
import com.mentor.impl.Prepration_of_blend_impl;
import com.mentor.utility.Validate;

public class Prepration_of_blend_act {
	Prepration_of_blend_impl impl=new Prepration_of_blend_impl();
	
	
	
	private String login;
	private String hidden;
	private boolean hidden_flg=true;
	public String getHidden() {
		if (hidden_flg) {
			impl.hidden(this);
		}
		this.setHidden_flg(false);
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
	public boolean isHidden_flg() {
		return hidden_flg;
	}
	public void setHidden_flg(boolean hidden_flg) {
		this.hidden_flg = hidden_flg;
	}
	
	
	private String dis_name_str;
	private int dis_id_int;
	private String dis_add_str;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getDis_name_str() {
		return dis_name_str;
	}
	public void setDis_name_str(String dis_name_str) {
		this.dis_name_str = dis_name_str;
	}
	public int getDis_id_int() {
		return dis_id_int;
	}
	public void setDis_id_int(int dis_id_int) {
		this.dis_id_int = dis_id_int;
	}
	public String getDis_add_str() {
		return dis_add_str;
	}
	public void setDis_add_str(String dis_add_str) {
		this.dis_add_str = dis_add_str;
	}
	
	
	//-----storageId--------------------
	private int storageId;
	
	public int getStorageId() {
		return storageId;
	}
	public void setStorageId(int storageId) {
		this.storageId = storageId;
	}


	private double blendingbl,blendingal,blendingstrength;
	
	
	
	
	public double getBlendingbl() {
		impl.getBlendingdata(storageId,this);
		return blendingbl;
	}
	public void setBlendingbl(double blendingbl) {
		this.blendingbl = blendingbl;
	}
	public double getBlendingal() {
		impl.getBlendingdata(storageId,this);
		return blendingal;
	}
	public void setBlendingal(double blendingal) {
		this.blendingal = blendingal;
	}
	public double getBlendingstrength() {
		
		return blendingstrength;
	}
	public void setBlendingstrength(double blendingstrength) {
		this.blendingstrength = blendingstrength;
	}
	
	
	
	
	 public void blntanklistnerMF4(ValueChangeEvent event)
		{
	//	 System.out.println("==========blntanklistnerMF4=============");
			Object obj=event.getNewValue();
		///	System.out.println("------obj----"+obj);
			int storageId=Integer.parseInt(String.valueOf(obj));
		///	System.out.println("-------vat--------"+storageId);
			impl.getBlendingdata(storageId,this);
			
			
		}
	 
	 
	 //-----------------------storage ---------------------
	 
	 private ArrayList storage=new ArrayList();
	 private double recievedQuantityBl ,strength ,recievedQuantityAl;
	 
	 
	 public double getRecievedQuantityAl() {
		return recievedQuantityAl;
	}
	public void setRecievedQuantityAl(double recievedQuantityAl) {
		this.recievedQuantityAl = recievedQuantityAl;
	}
	public double getRecievedQuantityBl() {
		return recievedQuantityBl;
	}
	public void setRecievedQuantityBl(double recievedQuantityBl) {
		this.recievedQuantityBl = recievedQuantityBl;
	}
	public double getStrength() {
		return strength;
	}
	public void setStrength(double strength) {
		this.strength = strength;
	}
	public ArrayList getStorage() {
			
			try{
			//	System.out.println("========getStorage======");
				this.storage= impl.getStorage(this);
				
			}catch(Exception e)
			{
			//	e.printStackTrace();
			}
			
			return storage;
		}
		public void setStorage(ArrayList storage) {
			this.storage = storage;
		}
///--------------------cal al----- public void calculateBillAmt(ActionEvent event)
		public void calculateBillAmt(ActionEvent event)
		  {
			 
			  try{
				 
					NumberFormat formatter = new DecimalFormat("#0.00"); 
					
					this.setRecievedQuantityAl(Double.parseDouble(formatter.format((this.getRecievedQuantityBl()*this.getStrength())/100)));
				  
				  
				    
					
					}catch (Exception e) {
					}	 
			  
		  }
		
		
		
		private double bl,blwater;
		public double getBl() {
			return bl;
		}
		public void setBl(double bl) {
			this.bl = bl;
		}
		public double getBlwater() {
			return blwater;
		}
		public void setBlwater(double blwater) {
			this.blwater = blwater;
		}
		
		
		
	private double tot_bl ,tot_al ,tot_str;
	public double getTot_bl() {

		  try{
			 
			
				
				NumberFormat formatter = new DecimalFormat("#0.00"); 
				this.setTot_bl(Double.parseDouble(formatter.format((this.getBlendingbl()+this.getBl())+(this.getBlwater()))));
			  
			 
				
				}catch (Exception e) {
			///		e.printStackTrace();
				}	
		return tot_bl;
	}
	public void setTot_bl(double tot_bl) {
		this.tot_bl = tot_bl;
	}
	public double getTot_al() {
		try{
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		this.setTot_al(Double.parseDouble(formatter.format((this.getTot_bl()*this.getTot_str())/100)));
		}catch(Exception e){
		//	e.printStackTrace();
		}
		return tot_al;
	}
	public void setTot_al(double tot_al) {
		this.tot_al = tot_al;
	}
	public double getTot_str() {
		return tot_str;
	}
	public void setTot_str(double tot_str) {
		this.tot_str = tot_str;
	}	
		
		
	
   public void save(){
	   
	///   System.out.println("----save-------");
	   if (isValidateReg()) {
	   impl.save(this);
	   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Saved Successfully"));
	   }
	   this.setValidateReg(false);
			this.displaylist=impl.displaylistImpl2(this );
			
   }
		
	public void reset()
	{
		this.bl=0.0;
		this.blendingal=0.0;
		this.blendingbl=0.0;
		this.blendingstrength=0.0;
		this.blwater=0.0;
		this.tot_al=0.0;
		this.tot_bl=0.0;
		this.tot_str=0.0;
		
	}
		
		
	private ArrayList displaylist=new ArrayList();
	private boolean displayflag=true;
     Prepration_of_blend_dt dt;
  
	public Prepration_of_blend_dt getDt() {
	return dt;
}
public void setDt(Prepration_of_blend_dt dt) {
	this.dt = dt;
}
	public boolean isDisplayflag() {
		return displayflag;
	}
	public void setDisplayflag(boolean displayflag) {
		this.displayflag = displayflag;
	}
	public void setDisplaylist(ArrayList displaylist) {
		this.displaylist = displaylist;
	}
	public ArrayList getDisplaylist() {
		if (displayflag) {
			this.displaylist=impl.displaylistImpl2(this );
			//this.setDisplayflag(true);
		}
		
		return displaylist;
	}
		
		
	
	//------------------------validation private boolean validateReg_packaging;
	private boolean validateReg=true ;
	public boolean isValidateReg() {
		validateReg = true;
		
		if (validateReg) {
			
					if (!(Validate.validateOnlyDoubleRequired1("bl", this.getBl())))validateReg=false;
					else if (!(Validate.validateOnlyDoubleRequired2("blwater", this.getBlwater())))validateReg=false; 
					else if (!(Validate.validateOnlyDoubleRequired3("tot_str", this.getTot_str())))validateReg=false; 
				//	else if (!(Validate.validateOnlyDouble("blwater", String.valueOf(this.getBlwater()))))validateReg=false; 
					
				//	System.out.println("========after if and befor else =validateReg======");
				
		}
		return validateReg;
	}

	public void setValidateReg(boolean validateReg) {
		this.validateReg = validateReg;
	}
	
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
