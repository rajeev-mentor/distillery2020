package com.mentor.Datatable;

public class Replytoobjection_eoi_dt {
	
	

	private int srNo;
	private int appid;
	private String lic_type;
	private String title;
	private String Status;
	private boolean disableFlg = false;
	private String objectionRaisedBy_dt;
	private int objectionID_dt;
	private String description_dt;
	public int getSrNo() {
		return srNo;
	}
	
	
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public String getLic_type() {
		return lic_type;
	}
	public void setLic_type(String lic_type) {
		this.lic_type = lic_type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public boolean isDisableFlg() {
		return disableFlg;
	}
	public void setDisableFlg(boolean disableFlg) {
		this.disableFlg = disableFlg;
	}
	public String getObjectionRaisedBy_dt() {
		return objectionRaisedBy_dt;
	}
	public void setObjectionRaisedBy_dt(String objectionRaisedBy_dt) {
		this.objectionRaisedBy_dt = objectionRaisedBy_dt;
	}
	public int getObjectionID_dt() {
		return objectionID_dt;
	}
	public void setObjectionID_dt(int objectionID_dt) {
		this.objectionID_dt = objectionID_dt;
	}
	public String getDescription_dt() {
		return description_dt;
	}
	public void setDescription_dt(String description_dt) {
		this.description_dt = description_dt;
	}


}
