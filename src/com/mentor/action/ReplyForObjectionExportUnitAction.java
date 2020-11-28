package com.mentor.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletSession;

import org.apache.commons.io.FilenameUtils;
import org.jboss.portal.portlet.impl.jsr168.api.ActionRequestImpl;
import org.jboss.portletbridge.context.ServletSessionWrapper;
import org.richfaces.component.UIDataTable;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.mentor.Datatable.ReplyForObjectionExportUnitDataTable;
import com.mentor.impl.ReplyForObjectionExportUnitImpl;
import com.mentor.utility.Constants;

public class ReplyForObjectionExportUnitAction {


	ReplyForObjectionExportUnitImpl impl = new ReplyForObjectionExportUnitImpl();

	private String hidden;
	private boolean viewPanelFlg;
	private int regID  ;
	private int appID ;
	private int objectionID  ;
	private String fillComment;
	private ArrayList displayObjections = new ArrayList();
	private String filePathphoto;
	private String recordFile = "NA";
	

	public String getRecordFile() {
		return recordFile;
	}

	public void setRecordFile(String recordFile) {
		this.recordFile = recordFile;
	}

	public String getFilePathphoto() {
		return filePathphoto;
	}

	public void setFilePathphoto(String filePathphoto) {
		this.filePathphoto = filePathphoto;
	}

	public int getObjectionID() {
		return objectionID;
	}

	public void setObjectionID(int objectionID) {
		this.objectionID = objectionID;
	}

	public String getHidden() {
		try {
			PortletSession psi = null;
			ServletSessionWrapper ssw = null;

			Object o = FacesContext.getCurrentInstance().getExternalContext()
					.getSession(true);

			if (o instanceof ServletSessionWrapper) {

				ssw = (ServletSessionWrapper) o;
				 
				this.appID = (Integer) ssw.getAttribute("app_id");
				  
			} else if (o instanceof PortletSession) {

				psi = (PortletSession) o;
				 
				this.appID = (Integer) psi.getAttribute("app_id");
				  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public boolean isViewPanelFlg() {
		return viewPanelFlg;
	}

	public void setViewPanelFlg(boolean viewPanelFlg) {
		this.viewPanelFlg = viewPanelFlg;
	}

	public int getRegID() {
		return regID;
	}

	public void setRegID(int regID) {
		this.regID = regID;
	}

	public int getAppID() {
		return appID;
	}

	public void setAppID(int appID) {
		this.appID = appID;
	}

	public String getFillComment() {
		return fillComment;
	}

	public void setFillComment(String fillComment) {
		this.fillComment = fillComment;
	}

	public ArrayList getDisplayObjections() {
		try {

			this.displayObjections = impl.displayObjectionImpl(this);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return displayObjections;
	}

	public void setDisplayObjections(ArrayList displayObjections) {
		this.displayObjections = displayObjections;
	}

	private String allId;

	public String getAllId() {
		return allId;
	}

	public void setAllId(String allId) {
		this.allId = allId;
	}

	private String vch_application_no;

	public String getVch_application_no() {
		return vch_application_no;
	}

	public void setVch_application_no(String vch_application_no) {
		this.vch_application_no = vch_application_no;
	}

	public void viewDetails(ActionEvent e) {

		try {
			UIDataTable uiTable = (UIDataTable) e.getComponent().getParent()
					.getParent();
			ReplyForObjectionExportUnitDataTable dt = (ReplyForObjectionExportUnitDataTable) this
					.getDisplayObjections().get(uiTable.getRowIndex());
			this.viewPanelFlg = true;
			this.vch_application_no = dt.getShowApplicationID_dt();
			this.setAppID(dt.getAppID_dt());
			this.setObjectionID(dt.getObjectionID_dt());
			this.allId = String.valueOf(dt.getAppID_dt()  + "&objectionID=" + dt.getObjectionID_dt());
			// //System.out.println("allId=" + this.allId);
			this.reset();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public String save() {
		try {

			//System.out.println("fillComment=" + fillComment + " | uploadedPdf="
				//	+ uploadedPdf);

			if (pdfDone) {
				if (this.fillComment != null && this.fillComment.length() > 0) {
					this.valForDb = "/doc/ExciseUp/ExportOutsideIndia/Objection/"
							+ recordFile;
					//System.out.println("recordFile==="+this.valForDb);
					impl.submitDataImpl(this);
					this.reset();
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Please Fill Comment!!",
									"Please Fill Comment!!"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"PDF Not Uploaded", "PDF Not Uploaded"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	// ----------------for uploading-------------------

	private String filePath;
	private static BufferedInputStream apidoc1 = null;
	private String fileNamed;
	private boolean img1 = false;
	public boolean doc1upload = false;
	private String mypathphoto = "";
	private String uploadedPdf;
	private String valForDb;

	public String getValForDb() {
		return valForDb;
	}

	public void setValForDb(String valForDb) {
		this.valForDb = valForDb;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public static BufferedInputStream getApidoc1() {
		return apidoc1;
	}

	public static void setApidoc1(BufferedInputStream apidoc1) {
		ReplyForObjectionExportUnitAction.apidoc1 = apidoc1;
	}

	public String getFileNamed() {
		return fileNamed;
	}

	public void setFileNamed(String fileNamed) {
		this.fileNamed = fileNamed;
	}

	public boolean isImg1() {
		return img1;
	}

	public void setImg1(boolean img1) {
		this.img1 = img1;
	}

	public boolean isDoc1upload() {
		return doc1upload;
	}

	public void setDoc1upload(boolean doc1upload) {
		this.doc1upload = doc1upload;
	}

	public String getMypathphoto() {
		return mypathphoto;
	}

	public void setMypathphoto(String mypathphoto) {
		this.mypathphoto = mypathphoto;
	}

	public String getUploadedPdf() {
		return uploadedPdf;
	}

	public void setUploadedPdf(String uploadedPdf) {
		this.uploadedPdf = uploadedPdf;
	}

	public void doc1uploadMethod(UploadEvent event) throws Exception {

		this.fileNamed = "";
		InputStream inFile = null;
		UploadItem item = event.getUploadItem();
		String FullfileName = item.getFileName();
		String FullfileExt = null;

		String arr[] = FullfileName.split(".pdf");
		this.fileNamed = arr[0];

		if (FullfileName != null && FullfileName.length() > 4) {
			FullfileExt = FullfileName.substring(FullfileName.lastIndexOf("."));
		}

		String recordFile = "Objection_" + this.appID + "_"
				+ this.objectionID + ".pdf";
		fileNamed = recordFile;
		String path = item.getFile().getAbsolutePath();
		filePath = item.getFile().getPath();
		if (filePath != null && (FullfileExt.equalsIgnoreCase(".pdf"))) {

			inFile = new FileInputStream(path);
			boolean success = false;

			try {
				
			
				
				String mypath = Constants.JBOSS_SERVER_PATH
						+ Constants.JBOSS_LINX_PATH + File.separator
						+ "ExciseUp" + File.separator + "ExportOutsideIndia"
						+ File.separator + "Objection" + File.separator;
						

				mypathphoto = mypath + File.separator + recordFile;

				String insertPath = "doc" + File.separator + "ExciseUp"
						+ File.separator + "ExportOutsideIndia" + File.separator
						+ "Objection" 
						+ File.separator + recordFile;

				//System.out.println("insertPath----------------" + insertPath);

				if (!(new File(mypath).exists())) {
					File file = new File(mypath);
					success = file.mkdirs();
				}
				inFile = new FileInputStream(path);
				apidoc1 = new BufferedInputStream(inFile);
				if (apidoc1.available() > 0) {

					FileOutputStream out = new FileOutputStream(mypath
							+ File.separator + recordFile);
					BufferedOutputStream outb = new BufferedOutputStream(out);
					int z = 0;
					while ((z = apidoc1.read()) != -1) {
						outb.write(z);
						doc1upload = true;
					}
					this.uploadedPdf = fileNamed;
					this.valForDb = insertPath;
					outb.flush();
					outb.close();

				} else {
					img1 = true;
					doc1upload = false;
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			// //System.out.println("1 NO FILE READ STARTED.");
			doc1upload = false;
		}

	}

	public void closeApplication() {
		// @rvind
		ActionRequestImpl impl2 = (ActionRequestImpl) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		/*
		 * impl2.getRealRequest().getSession().removeAttribute("fileName");
		 * impl2.getRealRequest().getSession().removeAttribute("recordFile");
		 * //System.out.println("file=" +
		 * impl2.getRealRequest().getSession().getAttribute("fileName"));
		 * 
		 * this.uploadedPdf = null; this.pdfDone = false;
		 */
		//System.out.println("file="
				//+ impl2.getRealRequest().getSession().getAttribute("fileName"));
		this.reset();
		this.viewPanelFlg = false;
		this.regID = 0;
		this.appID = 0;
		this.objectionID = 0;
		this.doc1upload = false;
		this.fillComment = null;
		this.uploadedPdf = null;
		this.displayObjections = impl.displayObjectionImpl(this);
	}

	public String backToRegistration() {
		PortletSession psi = null;
		ServletSessionWrapper ssw = null;
		Object o = FacesContext.getCurrentInstance().getExternalContext()
				.getSession(true);
		if (o instanceof ServletSessionWrapper) {
			ssw = (ServletSessionWrapper) o;
			ssw.removeAttribute("app_id");
		} else if (o instanceof PortletSession) {
			psi = (PortletSession) o;
			psi.removeAttribute("app_id");
		}
		this.reset();
		return "bck";
	}

	
	private boolean pdfDone;

	public boolean isPdfDone() {
		return pdfDone;
	}

	public void setPdfDone(boolean pdfDone) {
		this.pdfDone = pdfDone;
	}

	// impl.checkPDF(this);

	public String save1() {
		try {
			ActionRequestImpl impl = (ActionRequestImpl) FacesContext
					.getCurrentInstance().getExternalContext().getRequest();
			String fileName = (String) impl.getRealRequest().getSession()
					.getAttribute("fileName");
			String s = (String) impl.getRealRequest().getSession()
					.getAttribute("recordFile");

			if (fileName != null) {
				if (FilenameUtils.getExtension(fileName)
						.equalsIgnoreCase("pdf")) {

					this.uploadedPdf = s;
					this.pdfDone = true;
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Only PDF File Will upload",
									"Only PDF File Will upload"));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Choose PDF File From Above Box",
								"Choose PDF File From Above Box"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";

	}

	public void reset() {
		ActionRequestImpl impl2 = (ActionRequestImpl) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		impl2.getRealRequest().getSession().removeAttribute("fileName");
		impl2.getRealRequest().getSession().removeAttribute("recordFile");
		//System.out.println("file="
			//	+ impl2.getRealRequest().getSession().getAttribute("fileName"));

		this.uploadedPdf = null;
		this.pdfDone = false;
	}

	
	
	public void doc1uploadMethod1(UploadEvent event) throws Exception {

		System.out.println("222");
		this.uploadedPdf = "";
		InputStream inFile = null;
		UploadItem item = event.getUploadItem();
		String FullfileName = item.getFileName();
		String FullfileExt = null;

		String arr[] = FullfileName.split(".pdf");
		this.uploadedPdf = arr[0];

		if (FullfileName != null && FullfileName.length() > 4) {
			FullfileExt = FullfileName.substring(FullfileName.lastIndexOf("."));
		}

		System.out.println("FullfileExt==="+FullfileExt);
		// String recordFile = impl.getMaxId() + "_"+ this.distillery_id+
		// "_IMPORT.pdf";
		recordFile = this.appID + "_" + this.objectionID
				+ "_Objection_Rply_bg_indent_application.pdf";

		String path = item.getFile().getAbsolutePath();
		filePathphoto = item.getFile().getPath();
		if (filePathphoto != null && (FullfileExt.equalsIgnoreCase(".pdf"))) {

			inFile = new FileInputStream(path);
			boolean success = false;

			try {
				
				String mypath = Constants.JBOSS_SERVER_PATH
						+ Constants.JBOSS_LINX_PATH + File.separator
						+ "ExciseUp" + File.separator + "ExportOutsideIndia"
						+ File.separator + "Objection" + File.separator;
						

				mypathphoto = mypath + File.separator + recordFile;

				String insertPath = "doc" + File.separator + "ExciseUp"
						+ File.separator + "ExportOutsideIndia" + File.separator
						+ "Objection" 
						+ File.separator + recordFile;
				
				
				
				
	

				if (!(new File(mypath).exists())) {
					File file = new File(mypath);
					success = file.mkdirs();
				}
				inFile = new FileInputStream(path);
				apidoc1 = new BufferedInputStream(inFile);
				if (apidoc1.available() > 0) {

					FileOutputStream out = new FileOutputStream(mypath
							+ File.separator + recordFile);
					BufferedOutputStream outb = new BufferedOutputStream(out);
					int z = 0;
					while ((z = apidoc1.read()) != -1) {
						outb.write(z);
						pdfDone = true;
					}
					outb.flush();
					outb.close();

				} else {
					img1 = true;
					pdfDone = false;
				}
				// System.out.print("doc1 uploaded success fully");
				// this.setCount(this.getCount()+1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		else {
			// System.out.print("1 NO FILE READ STARTED.");
			doc1upload = false;
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" Only .pdf File are allowed !!!!!!",
							" Only .pdf File are allowed   !!!!!!"));
		}

	}
	
	public void resetpage() {

		// System.out.println("------file name----------"+this.getFileName());
		if (this.uploadedPdf != null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Pdf Uploaded  !!!!!", "Pdf Uploaded  !!!!!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							" Upload Pdf first !!!!!!",
							"Upload Pdf first  !!!!!!"));
		}
	}

}
