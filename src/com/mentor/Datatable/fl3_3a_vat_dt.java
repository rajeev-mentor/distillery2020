package com.mentor.Datatable;

import java.util.ArrayList;

import com.mentor.impl.fl3_3a_vat_impl;
 

public class fl3_3a_vat_dt {
	fl3_3a_vat_impl impl=new fl3_3a_vat_impl();
	private int sno;
	private String vat_name;
	private double cap;
	private String sprit_name;
	private String sprit_id;
	private boolean updateflg=true;
	private boolean sprit_flag=true;
	
	
	public boolean isSprit_flag() {
		return sprit_flag;
	}
	public void setSprit_flag(boolean sprit_flag) {
		this.sprit_flag = sprit_flag;
	}
	public boolean isUpdateflg() {
		return updateflg;
	}
	public void setUpdateflg(boolean updateflg) {
		this.updateflg = updateflg;
	}
	public ArrayList getSprit_name_list() {
		if (sprit_flag==true) {
			//if (this.getRadio().equalsIgnoreCase("S")) {
				this.sprit_name_list=impl.getPackagingName(this);
				sprit_flag=false;
			//}
			
		}
	
	//	impl.data(sprit_id ,this);

		return sprit_name_list;
	}
	public void setSprit_name_list(ArrayList sprit_name_list) {
		this.sprit_name_list = sprit_name_list;
	}
	
	public String getSprit_id() {
		return sprit_id;
	}
	public void setSprit_id(String sprit_id) {
		this.sprit_id = sprit_id;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getVat_name() {
		return vat_name;
	}
	public void setVat_name(String vat_name) {
		this.vat_name = vat_name;
	}
	public double getCap() {
		return cap;
	}
	public void setCap(double cap) {
		this.cap = cap;
	}
	public String getSprit_name() {
		return sprit_name;
	}
	public void setSprit_name(String sprit_name) {
		this.sprit_name = sprit_name;
	}
	
	private ArrayList sprit_name_list = new ArrayList();

	public ArrayList getSpritname() {
		
		return sprit_name_list;
	}
	public void setSpritname(ArrayList spritname) {
		this.sprit_name_list = sprit_name_list;
	}
	
	private int storage_id;



	public int getStorage_id() {
		return storage_id;
	}



	public void setStorage_id(int storage_id) {
		this.storage_id = storage_id;
	}
	private String radio;


	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	
	
}
