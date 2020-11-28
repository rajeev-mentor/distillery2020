package com.mentor.action;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletSession;
import org.jboss.portletbridge.context.ServletSessionWrapper;
import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;
import com.mentor.Datatable.Replytoobjection_eoi_dt;
import com.mentor.impl.Replytoobjection_eoi_impl;
import com.mentor.utility.Constants;

public class Replytoobjection_eoi_Action {
	
	Replytoobjection_eoi_impl impl = new Replytoobjection_eoi_impl();
	
	
	private String obj_reply;
	private String title;
	private Date obj_date;
	private boolean viewPanelFlg;
	private int appID;
	private int objectionID;
	private String fillComment;
	private String filePath;
	
	


	
	public Replytoobjection_eoi_impl getImpl() {
		return impl;
	}

	public void setImpl(Replytoobjection_eoi_impl impl) {
		this.impl = impl;
	}

	public String getObj_reply() {
		return obj_reply;
	}

	public void setObj_reply(String obj_reply) {
		this.obj_reply = obj_reply;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getObj_date() {
		return obj_date;
	}

	public void setObj_date(Date obj_date) {
		this.obj_date = obj_date;
	}

	public boolean isViewPanelFlg() {
		return viewPanelFlg;
	}

	public void setViewPanelFlg(boolean viewPanelFlg) {
		this.viewPanelFlg = viewPanelFlg;
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	public int getObjectionID() {
		return objectionID;
	}

	public void setObjectionID(int objectionID) {
		this.objectionID = objectionID;
	}

	public String getFillComment() {
		return fillComment;
	}

	public void setFillComment(String fillComment) {
		this.fillComment = fillComment;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	private ArrayList objectionlist=new ArrayList();
	
	public String hidden;
		
	public ArrayList getObjectionlist() {
		
		return objectionlist;
	}

	public String getHidden() {
		
		try {
			PortletSession psi = null;
			ServletSessionWrapper ssw = null;

			Object o = FacesContext.getCurrentInstance().getExternalContext()
					.getSession(true);

			if (o instanceof ServletSessionWrapper) {

				ssw = (ServletSessionWrapper) o;
				if (ssw.getAttribute("app_id") != null) {
					
				//	this.appID = Integer.parseInt((String)ssw.getAttribute("app_id"));
					this.appID = (Integer) ssw.getAttribute("app_id");
					
				} 

			} else if (o instanceof PortletSession) {

				psi = (PortletSession) o;
				if (psi.getAttribute("app_id") != null) {
				
				//	this.appID = Integer.parseInt((String) psi.getAttribute("app_id"));
					this.appID = (Integer) psi.getAttribute("app_id");
				}  
				
			
				

			}this.objectionlist=impl.getobjectionlist(this,this.appID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public void setObjectionlist(ArrayList objectionlist) {
		this.objectionlist = objectionlist;
	}
	
	public void view(ActionEvent e)
	{
		
		UIDataTable uiTable = (UIDataTable) e.getComponent().getParent().getParent();
		Replytoobjection_eoi_dt dt = (Replytoobjection_eoi_dt) this.objectionlist.get(uiTable.getRowIndex());
		
		this.viewPanelFlg = true;
		this.setAppID(dt.getAppid());
		this.setObjectionID(dt.getObjectionID_dt());
		impl.getvalue(this);

	}

	
	public String save() {

		try {
				if (this.fillComment != null && this.fillComment.length() > 0) {
					impl.submitDataImpl(this);
					
				} else {
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Please Fill Comment!!","Please Fill Comment!!"));
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
}	
	
	public String back(){
		this.viewPanelFlg=false;
		return "GO";
	}
	
	public void reset() {
		
		this.viewPanelFlg=false;
		this.fillComment=null;
		this.viewFile = null;
		this.doc2Flg = false;
		this.appID = 0;
		this.objectionID =0;
	}
	
	public boolean doc2Flg = false;
	private String viewFile;

	public boolean isDoc2Flg() {
		return doc2Flg;
	}

	public void setDoc2Flg(boolean doc2Flg) {
		this.doc2Flg = doc2Flg;
	}

	public String getViewFile() {
		return viewFile;
	}

	public void setViewFile(String viewFile) {
		this.viewFile = viewFile;
	}

	public void uploadFile(UploadEvent e) {
		try {


			UploadItem item = e.getUploadItem();
			String FullfileName = item.getFileName();
			String path = item.getFile().getAbsolutePath();
			
			String mypath = Constants.JBOSS_SERVER_PATH + Constants.JBOSS_LINX_PATH;
			String pdfPath = mypath+ File.separator + "ExciseUp" + File.separator + "MIS" + File.separator + "ExportOutsideIndia" + File.separator + "eoiObjection";

			InputStream io = new FileInputStream(path);

			String recordFile = "eoiImportObjectionReply_"+this.appID+"_"+this.objectionID+".pdf";

			this.viewFile = recordFile;
			//this.saveAffidavit = "/doc/ExciseUp/LabelRegistration/pdf/"+ recordFile;
			

			FileOutputStream out = new FileOutputStream(pdfPath + File.separator+ recordFile);
			BufferedOutputStream outb = new BufferedOutputStream(out);
			int z = 0;
			while ((z = io.read()) != -1) {

				outb.write(z);
				doc2Flg = true;


			}
			

			outb.flush();
			outb.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}





}


