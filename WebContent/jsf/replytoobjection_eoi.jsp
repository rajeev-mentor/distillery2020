  <ui:composition
      xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

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
}

.dropdown-menu1 {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 75%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}

.textarea1 {
	border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
}
</style>
		</head>
		<h:form>
			<div class="container">
				<div class="row">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<div class="row">
					<div class="col-md-12 wow shake" align="center">
						<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
							infoStyle="color:green"
							style="font-size:20px; background-color:#e1fcdf; font-weight: bold">
						</h:messages>

					</div>
				</div>

				<div class="row" align="center">
					<TABLE width="100%" align="center">
						<TR>
							<TD align="center" width="100%">
								<TABLE align="center" width="100%">
									<TBODY>

										<tr>
											<td><rich:separator lineType="dashed"></rich:separator>
											</td>
										</tr>

										<tr>
											<TD align="center" colspan="2"><h2>
													<h:outputText
														value="Reply To Given Objections For Applications from  Export order by distillery"
														style="COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Monotype Corsiva;">
													</h:outputText>
													<h:inputHidden value="#{replytoobjection_eoi_Action .hidden}" />
												</h2></TD>
										</tr>

										<tr>
											<td><rich:separator lineType="dashed"></rich:separator>
											</td>
										</tr>
									</TBODY>
								</TABLE>
							</TD>
						</TR>

					</TABLE>
				</div>
				<div class="row">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<div class="row" align="center">
					<div class="col-md-12">
						<rich:dataTable align="center" id="table1" rows="10" width="100%"
							var="list" value="#{replytoobjection_eoi_Action .objectionlist}"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2"
							style="FONT-SIZE: medium; FONT-FAMILY: 'Arial Black';">

							<rich:column>
								<f:facet name="header">
									<h:outputText value="* Sr.No. "
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText disabled="true" value="#{list.srNo}"
									styleClass="form-control" />

							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="* App id"
										styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:outputText value="#{list.appid}" styleClass="form-control" />

							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Objection By"
										styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:outputText value="#{list.objectionRaisedBy_dt}"
									styleClass="form-control" />

							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="* Objection Title"
										styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:outputText value="#{list.title}" styleClass="form-control" />

							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="* Objection Description"
										styleClass="generalHeaderOutputTable" />
								</f:facet>

								<h:outputText value="#{list.description_dt}"
									styleClass="form-control" />

							</rich:column>



							<rich:column>
								<f:facet name="header">
									<h:outputText value="* Status"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<h:outputText value="#{list.status}" styleClass="form-control" />

							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Objection"
										styleClass="generalHeaderOutputTable" />
								</f:facet>
								<center>
									<h:commandButton styleClass="btn btn-primary btn-sm"
										actionListener="#{replytoobjection_eoi_Action .view}"
										value="Reply" disabled="#{list.disableFlg }" />
								</center>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="table1"></rich:datascroller>
							</f:facet>

						</rich:dataTable>

					</div>
				</div>

				<div class="row" align="center">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<h:panelGroup rendered="#{replytoobjection_eoi_Action .viewPanelFlg}">
					<div class="row" align="center" style="background-color: #e6ffff">
						<div class="col-md-12">
							<h:outputText
								value="Application No.:#{replytoobjection_eoi_Action .appID} "
								style="COLOR: #2952a3; font-weight:bold; FONT-SIZE: x-large; font-family:Times New Roman" />
						</div>
					</div>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div align="center" class="row">
						<div class="col-md-3" align="right">
							<b><h:outputText value="Objection Title :" /></b>
						</div>
						<div class="col-md-3" align="left">
							<h:inputText readonly="true" style="width: 400px; height: 30px;"
								value="#{replytoobjection_eoi_Action .title}" />
						</div>
						<div class="col-md-3" align="right">
							<b><h:outputText value="Objection Date :" /></b>
						</div>
						<div class="col-md-3" align="left">
							<rich:calendar value="#{replytoobjection_eoi_Action .obj_date }"
								readonly="true" />
						</div>

					</div>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="col-md-12">
						<div class="col-md-4" align="right">
							<b>Upload PDF (If Any)</b>
						</div>
						<div class="col-md-3" align="left">
							<rich:fileUpload clearControlLabel="clear"
								stopControlLabel="Stop"
								ontyperejected="if (!confirm('Only pdf allowed !')) return false"
								acceptedTypes="pdf" clearAllControlLabel="Clear All"
								listWidth="220" listHeight="30" maxFilesQuantity="1"
								fileUploadListener="#{replytoobjection_eoi_Action.uploadFile}">
								<a4j:support event="onuploadcomplete"
									reRender="renpdffalse2 , renpdftrue2"></a4j:support>
							</rich:fileUpload>

						</div>
						<div class="col-md-5 img-responsive" align="left">

							<a4j:outputPanel id="renpdftrue2">
								<h:outputLink rendered="#{replytoobjection_eoi_Action.doc2Flg}"
									target="_blank"
									value="/doc/ExciseUp/MIS/ExportOutsideIndia/eoiObjection/#{replytoobjection_eoi_Action.viewFile}" style="FONT-SIZE: xx-small;">

									<h:graphicImage value="/img/download.gif"
										style="width : 60px; height : 35px;"></h:graphicImage>
								</h:outputLink>
							</a4j:outputPanel>
							<a4j:outputPanel id="renpdffalse2">
								<a4j:outputPanel rendered="#{!replytoobjection_eoi_Action .doc2Flg}">
									<h:graphicImage value="/img/nodoc.png"
										style="width : 60px; height : 35px;"></h:graphicImage>

								</a4j:outputPanel>
							</a4j:outputPanel>
						</div>

					</div>
					<div class="row">
						<rich:spacer height="10px"></rich:spacer>
					</div>
					<div class="col-md-12">
						<div class="col-md-3" align="right">
							<b><h:outputText value="Remarks:" /></b>
						</div>
						<div class="col-md-7" align="left">
							<h:inputTextarea value="#{replytoobjection_eoi_Action .fillComment}"
								styleClass="form-control"
								style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
						</div>

					</div>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div class="row" align="center">

						<h:commandButton value="Submit" styleClass="btn btn-success btn-sm"
							action="#{replytoobjection_eoi_Action.save}" />
						<h:commandButton value="Close" styleClass="btn btn-warning btn-sm"
							action="#{replytoobjection_eoi_Action.reset}" />
					</div>

				</h:panelGroup>
				<div class="row" align="center">
					<h:commandButton value="Back" styleClass="btn btn-secondary btn-sm"
						action="#{replytoobjection_eoi_Action.back}" />
				</div>
			</div>
		</h:form>
	</f:view>
  
   
   
   
   
   
   
</ui:composition>