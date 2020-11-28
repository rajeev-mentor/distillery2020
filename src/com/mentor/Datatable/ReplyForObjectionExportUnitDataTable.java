package com.mentor.Datatable;

public class ReplyForObjectionExportUnitDataTable {

	private int srNo;
	private int regID_dt;
	private int appID_dt;
	private int objectionID_dt;
	private String objectionDate_dt;
	private String objectionRaisedBy_dt;
	private String objectionFor_dt;
	private String description_dt;
	private boolean disableFlg=false;

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public int getRegID_dt() {
		return regID_dt;
	}

	public void setRegID_dt(int regID_dt) {
		this.regID_dt = regID_dt;
	}

	public int getAppID_dt() {
		return appID_dt;
	}

	public void setAppID_dt(int appID_dt) {
		this.appID_dt = appID_dt;
	}

	
	public int getObjectionID_dt() {
		return objectionID_dt;
	}

	public void setObjectionID_dt(int objectionID_dt) {
		this.objectionID_dt = objectionID_dt;
	}

	public String getObjectionDate_dt() {
		return objectionDate_dt;
	}

	public void setObjectionDate_dt(String objectionDate_dt) {
		this.objectionDate_dt = objectionDate_dt;
	}

	public String getObjectionRaisedBy_dt() {
		return objectionRaisedBy_dt;
	}

	public void setObjectionRaisedBy_dt(String objectionRaisedBy_dt) {
		this.objectionRaisedBy_dt = objectionRaisedBy_dt;
	}

	public String getObjectionFor_dt() {
		return objectionFor_dt;
	}

	public void setObjectionFor_dt(String objectionFor_dt) {
		this.objectionFor_dt = objectionFor_dt;
	}

	public String getDescription_dt() {
		return description_dt;
	}

	public void setDescription_dt(String description_dt) {
		this.description_dt = description_dt;
	}

	public boolean isDisableFlg() {
		return disableFlg;
	}

	public void setDisableFlg(boolean disableFlg) {
		this.disableFlg = disableFlg;
	}
	
	
	private String showApplicationID_dt;

	public String getShowApplicationID_dt() {
		return showApplicationID_dt;
	}

	public void setShowApplicationID_dt(String showApplicationID_dt) {
		this.showApplicationID_dt = showApplicationID_dt;
	}

}
