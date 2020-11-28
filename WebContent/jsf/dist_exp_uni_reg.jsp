
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:h="http://java.sun.com/jsf/html">

	<h:form>
		<f:view>
			<head>
<style>
.inputtext {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.dropdown-menu {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 30%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	color: maroon;
}

.dropdown-menu1 {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	COLOR: maroon;
	BACKGROUND-COLOR: #0080c0;
}

.textarea1 {
	border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.blinking {
	animation: mymove 2s infinite alternate;
}

@
-webkit-keyframes mymove {
	from {opacity: 0;
}

to {
	opacity: 1;
}
}
</style>
			</head>
<h:inputHidden value="#{dist_exp_uni_reg_action.hidden}"></h:inputHidden>
			<div class="panel panel-default">

				<div class="panel-body">
					<TABLE width="100%" align="center" class="header" id="myHeader">
						<TR>
							<TD align="center" width="100%">
								<TABLE align="center" width="100%">
									<TBODY>

									


										<tr>
											<TD align="center" colspan="2"><h2>
													<h:outputText
														value="Registration Of a Distiillery as Export Unit For Export OutSide India "
														style="FONT-FAMILY: 'Corbel Light'; FONT-WEIGHT: bold; COLOR: #0000a0; FONT-SIZE: 30px;">
													</h:outputText>

												</h2></TD>
										</tr>



									</TBODY>
								</TABLE>
							</TD>
						</TR>

					</TABLE>
					<div class="row">
						<div class="col-md-12 wow shake" align="center">
							<h:messages errorStyle="color:red" layout="TABLE" id="messages"
								infoStyle="color:green"
								style="font-size:15px; font-weight: bold">
							</h:messages>

						</div>
					</div>
					<div class="row">
					<div class="col-md-6" align="center">
					
					<h:outputText value="#{dist_exp_uni_reg_action.status}" style="FONT-FAMILY: 'Corbel Light'; FONT-WEIGHT: bold; COLOR: red; FONT-SIZE: 20px;"></h:outputText>
					
					
					</div>
					<div class="col-md-3" align="right">
					
					<h:commandButton value="Objection Reply" styleClass="btn btn-danger" action="#{dist_exp_uni_reg_action.replyObjection}"  rendered="#{dist_exp_uni_reg_action.objection_reply_button}"></h:commandButton>
					</div>
					<div class="col-md-3" align="right">
						 
					</div>
					
					
					
					</div>
					
					<div class="blinking" align="left">
								<h1>


									<h:outputText value="All "
										style="FONT-SIZE: medium; color:CornflowerBlue;font-family: 'Candara Light';margin-left:30px;font-weight: bold;" />
										<h:outputLabel value="*"
										style="color:red;font-size:medium">
										<h:outputText value=" Fields are neccessary :"
										style="FONT-SIZE: medium; color:CornflowerBlue;font-family: 'Candara Light';font-weight: bold;" />
										</h:outputLabel>
										
								</h1>
							</div>
										
					
					<div class="col-md-12">
					<div class="col-md-3" align="right">
							<h:outputText value="Importer Exporter Code : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							<h:outputLabel value="*" style="color: red;" />
						</div>
						<div class="col-md-3" align="left">
							<h:inputText value="#{dist_exp_uni_reg_action.importer_exporter_code}"
								converterMessage="Please enter only digits." maxlength="12"
								style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>
						</div>
						
						
						<div class="col-md-3" align="right">
							<h:outputText value=" Certificate Issue Date : "
								style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
							<h:outputLabel value="*" style="color: red;" />
						</div>

						<div class="col-md-3" align="left">
							<rich:calendar value = "#{dist_exp_uni_reg_action.icd_certificate_issue_date}">
								
							</rich:calendar>
						</div>
						
					</div>

                 <rich:spacer height="10px" />
					<div class="col-md-12">
						
						<div class="col-md-3" align="right">
							<h:outputText value="Upload Importer Exporter Ceritificate : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							<h:outputLabel value="*" style="color: red;" />
						</div>
						<div class="col-md-3" align="left">
							<rich:fileUpload rendered = "#{dist_exp_uni_reg_action.saveflag == false}" listHeight="30px" listWidth="225px"
								fileUploadListener="#{dist_exp_uni_reg_action.doc1uploadMethod}"
								maxFilesQuantity="1" clearControlLabel="Clear"
								clearAllControlLabel="Clear All"
								ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
								acceptedTypes="pdf" addControlLabel="Add PDF">
								<a4j:support event="onuploadcomplete"
									reRender="renpdffalse1, renpdftrue1"></a4j:support>
							</rich:fileUpload>
							<h:commandButton rendered = "#{dist_exp_uni_reg_action.saveflag == false}"
								action="#{dist_exp_uni_reg_action.pdf1}"
								disabled="#{dist_exp_uni_reg_action.pdfUploaderFlag == true}"
								styleClass="btn btn-info btn-sm" value="confirm PDF" />
							<rich:spacer width="10px;"></rich:spacer>
							<a4j:outputPanel id="renpdftrue1">


								<h:outputLink
									rendered="#{dist_exp_uni_reg_action.doc1upload}"
									target="_blank"
									value="/doc/ExciseUp/ExportOutsideIndia/ICD/#{dist_exp_uni_reg_action.upload_certificate_export_1}">
									<h:graphicImage value="/img/download.gif" alt="view document"
										style="width : 60px; height : 35px;"></h:graphicImage>
								</h:outputLink>
							</a4j:outputPanel>
							<a4j:outputPanel id="renpdffalse1">
								<a4j:outputPanel
									rendered="#{!dist_exp_uni_reg_action.doc1upload}">
									<h:graphicImage value="/img/nodoc.png" alt="no document"
										style="width : 60px; height : 35px;"></h:graphicImage>

								</a4j:outputPanel>
							</a4j:outputPanel>
						</div>
								<div class ="row">					
								<rich:spacer height="20px" />
								</div>

						<div class="col-md-12">
							<div class="col-md-3" align="right">
								<h:outputText value=" Registration Cum Membership No. : "
									style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';margin-top: 20px;"></h:outputText>
								<h:outputLabel value="*" style="color: red;" />
							</div>

							<div class="col-md-3" align="left">
							<h:inputText value = "#{dist_exp_uni_reg_action.regis_cum_membership_no}"
							style="COLOR: #0000ff;" styleClass="form-control"/>	
							</div>
							<div class="col-md-3" align="right">
								<h:outputText value="RCMC Certificate Issue Date : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								<h:outputLabel value="*" style="color: red;" />
							</div>
							<div class="col-md-3" align="left">
								<rich:calendar value = "#{dist_exp_uni_reg_action.membership_certificate_issue_date}">
								
							</rich:calendar>
							</div>
						</div>
		<div class= "row">				
	   <rich:spacer height="20px"/>
	</div>
						<div class="row">
							<div class="col-md-3" align="right">
								<h:outputText value="Upload RCMC Certificate: "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								<h:outputLabel value="*" style="color: red;" />
							</div>
							<div class="col-md-3" align="left">
								<a4j:outputPanel rendered = "#{dist_exp_uni_reg_action.saveflag == false}">
										<rich:fileUpload listHeight="30px" listWidth="225px"
											fileUploadListener="#{dist_exp_uni_reg_action.doc2uploadMethod}"
											maxFilesQuantity="1" clearControlLabel="Clear"
											clearAllControlLabel="Clear All"
											ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
											sizeErrorLabel="" acceptedTypes="pdf"
											addControlLabel="Add PDF">
											<a4j:support event="onuploadcomplete"
												reRender="renpdffalse2, renpdftrue2"></a4j:support>
										</rich:fileUpload>
									</a4j:outputPanel> <h:commandButton rendered = "#{dist_exp_uni_reg_action.saveflag == false}"
										action="#{dist_exp_uni_reg_action.pdf2}"
										disabled="#{dist_exp_uni_reg_action.pdfUploaderFlag == true}"
										styleClass="btn btn-info btn-sm" value="confirm PDF" /> <rich:spacer
										width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue2">
										<h:outputLink
											rendered="#{dist_exp_uni_reg_action.doc2upload}"
											target="_blank"
											value="/doc/ExciseUp/ExportOutsideIndia/RCM/#{dist_exp_uni_reg_action.upload_rcmc_2}">
											<h:graphicImage value="/img/download.gif" alt="view document"
												style="width : 60px; height : 35px;"></h:graphicImage>
										</h:outputLink>
									</a4j:outputPanel> <a4j:outputPanel id="renpdffalse2">
										<a4j:outputPanel
											rendered="#{!dist_exp_uni_reg_action.doc2upload}">
											<h:graphicImage value="/img/nodoc.png" alt="no document"
												style="width : 60px; height : 35px;"></h:graphicImage>
										</a4j:outputPanel>
									</a4j:outputPanel>
							</div>
						</div>

						<div class="row" align="center">
							<h1>
								<h:outputText value="Add Banks Details "
									style="FONT-SIZE: x-large; FONT-WEIGHT: bold; COLOR: #0000a0 ;margin-left:40px; medium;color:CornflowerBlue;font-family: 'Candara Light';" />
							</h1>
						</div>

						<div style="overflow-y: scroll; width: 100%;">
							<rich:dataTable align="center" id="table4" rows="10" width="100%"
							
								var="list"
								value="#{dist_exp_uni_reg_action.addRowdetailsData}"
								headerClass="TableHead" footerClass="TableHead"
								rowClasses="TableRow1,TableRow2">

								<rich:column>
									<f:facet name="header">
										<h:outputText value="* Sr.No. "
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Candara Light';" />
									</f:facet>
									<h:outputText value="#{list.sno_r}" styleClass="form-control" />
								</rich:column>

								<rich:column width="400px" >
									<f:facet name="header">
										<h:outputText value="* Bank Name "
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Candara Light';"></h:outputText>
									</f:facet>

									<h:inputText value="#{list.bank_name}"
							
										style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>

								</rich:column>

								<rich:column width="300px" align="center">
									<f:facet name="header">
										<h:outputText value="* Branch "
											styleClass="generalHeaderOutputTable"
											style="FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Candara Light';" />
									</f:facet>

									<h:inputText value="#{list.branch}"
										converterMessage="Please enter only digits." maxlength="12"
										style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>

								</rich:column>


								<rich:column  width="200px">
									<f:facet name="header">
										<h:outputText value="* Account No."
											style="FONT-SIZE: small; FONT-WEIGHT: bold; FONT-FAMILY: 'Candara Light';" />
									</f:facet>

									<h:inputText value="#{list.account_no}"
										converterMessage="Please enter only digits." maxlength="12"
										style="COLOR: #0000ff;" styleClass="form-control"></h:inputText>

								</rich:column>



								<rich:column align="center">
									<f:facet name="header">
										<h:commandButton value="Add Bank Detail"
											action="#{dist_exp_uni_reg_action.addRowdetails}"
											styleClass="btn btn-primary btn-sm" />
									</f:facet>
									<h:commandButton class="imag"
										actionListener="#{dist_exp_uni_reg_action.deleteRowRowdetails}"
										style="background: transparent;height:30px "
										image="/img/del.png" />
								</rich:column>
								<f:facet name="footer">
									<rich:datascroller for="table4" />
								</f:facet>
							</rich:dataTable>
						</div>
	<rich:spacer height="20px"></rich:spacer>
	

	
						<div class="col-md-12">
							<div class="col-md-3" align="right">
								<h:outputText value=" Star Export House No. : "
									style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
								<h:outputLabel value="*" style="color: red;" />
							</div>

							<div class="col-md-3" align="left">
								<h:inputText value = "#{dist_exp_uni_reg_action.house_no}"
							style="COLOR: #0000ff;" styleClass="form-control" >
							</h:inputText>
							</div>
							<div class="col-md-3" align="right">
								<h:outputText value="House Certificate Issue Date : "
									style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
								<h:outputLabel value="*" style="color: red;" />
							</div>
							<div class="col-md-3" align="left">
             <rich:calendar value = "#{dist_exp_uni_reg_action.export_certificate_issue_date}">
						
							</rich:calendar>
							</div>
						</div>






					</div>
					<div class= "row">				
						<rich:spacer height="20px"/>
					</div>

					<div class="row">
					<div class="col-md-3" align="right">
							<h:outputText value=" GSTIN No. : "
								style="FONT-SIZE: 15px; FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light';"></h:outputText>
							<h:outputLabel value="*" style="color: red;" />
						</div>

						<div class="col-md-3" align="left">
							<h:inputText value = "#{dist_exp_uni_reg_action.gstin_no}"
							style="COLOR: #0000ff;" styleClass="form-control" >
							</h:inputText>
						</div>
						<div class="col-md-3" align="right">
							<h:outputText value="House Upload Certificate : "
								style="FONT-WEIGHT: bold; font-size: 15px;FONT-FAMILY: 'Candara Light';"></h:outputText>
							<h:outputLabel value="*" style="color: red;" />
						</div>
						<div class="col-md-3" align="left">
							<a4j:outputPanel rendered = "#{dist_exp_uni_reg_action.saveflag == false}">
									<rich:fileUpload listHeight="30px" listWidth="225px"
										fileUploadListener="#{dist_exp_uni_reg_action.doc3uploadMethod}"
										maxFilesQuantity="1" clearControlLabel="Clear"
										clearAllControlLabel="Clear All"
										ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
										sizeErrorLabel="" acceptedTypes="pdf"
										addControlLabel="Add PDF">								
											<a4j:support event="onuploadcomplete"
												reRender="renpdffalse3 , renpdftrue3"></a4j:support>
									</rich:fileUpload>
								</a4j:outputPanel> <h:commandButton rendered = "#{dist_exp_uni_reg_action.saveflag == false}"
									action="#{dist_exp_uni_reg_action.pdf3}"
									disabled="#{dist_exp_uni_reg_action.pdfUploaderFlag == true}"
									styleClass="btn btn-info btn-sm" value="confirm PDF" /> <rich:spacer
									width="10px;"></rich:spacer> <a4j:outputPanel id="renpdftrue3">
									<h:outputLink
										rendered="#{dist_exp_uni_reg_action.doc3upload}"
										target="_blank"
										value="/doc/ExciseUp/ExportOutsideIndia/SEH/#{dist_exp_uni_reg_action.upload_certi_export_3}">
										<h:graphicImage value="/img/download.gif" alt="view document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</h:outputLink>
								</a4j:outputPanel> <a4j:outputPanel id="renpdffalse3">
									<a4j:outputPanel
										rendered="#{!dist_exp_uni_reg_action.doc3upload}">
										<h:graphicImage value="/img/nodoc.png" alt="no document"
											style="width : 60px; height : 35px;"></h:graphicImage>
									</a4j:outputPanel>
								</a4j:outputPanel>
						</div>
					</div>
					
					
					<div class= "row"> 
					<div class="col-md-3" align="center">
									<h:outputText value=" Export Overseas Brands:   "
										style="FONT-WEIGHT: bold;FONT-FAMILY: 'Candara Light'; font-size: 15px"></h:outputText>
										<h:outputLabel value="*"
										style="color: red;"/>
								</div>
								
								
				</div>
				<div align="center">
					<rich:dataTable id="table55" rows="15" var="list33"
						value="#{dist_exp_uni_reg_action.display_detail}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.display_srNo}">
							</h:outputText>
						</rich:column>
						<rich:column align="left">
							<f:facet name="header">
								<h:outputText value="Brand Name "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.brand_name}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Size (ML) "
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.size}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" ETIN No."
									style="FONT-SIZE: small; COLOR: SlateBlue;white-space: normal;"
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list33.etin_no}">
							</h:outputText>
						</rich:column>
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table55"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>

				</div>	
				
				
				<div align="center">
							<rich:spacer height="60px" />
							<h:commandButton
							rendered = "#{dist_exp_uni_reg_action.saveflag == false}"
								action="#{dist_exp_uni_reg_action.save}"
								onclick="return confirm('ALERT : You are submitting the application. Please Confirm Your Details !!');" 							
								value="Save" styleClass="btn btn-success" />
							<rich:spacer width="15px;"></rich:spacer>
							<h:commandButton
							rendered = "#{dist_exp_uni_reg_action.saveflag == false}"
								action="#{dist_exp_uni_reg_action.reset}"
								value="Reset" styleClass="btn btn-info  active" />
							<rich:spacer width="15px;"></rich:spacer>
							

						</div>			
								
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					

				</div>
				</div>
		</f:view>
	</h:form>
</ui:composition>