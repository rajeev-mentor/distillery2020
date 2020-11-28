<ui:composition xmlns="http://www.w3.org/1999/xhtml"
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

<script>
	Dropzone.options.myAwesomeDropzone = {

		maxFiles : 1,
		accept : function(file, done) {
			console.log("uploaded");
			done();
		},
		acceptedFiles : "application/pdf",
		init : function() {

			this.on("maxfilesexceeded", function(file) {
				this.removeFile(file);

			});

		}
	};
</script>
		</head>
		<h:form>
			<div class="container">
				<div class="row">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<div class="row">
					<div class="col-md-12 wow shake" align="center">
						<a4j:outputPanel id="msg">
							<h:messages errorStyle="color:red" layout="TABLE" id="messages1"
								infoStyle="color:green"
								style="font-size:20px; background-color:#e1fcdf; font-weight: bold">
							</h:messages>
						</a4j:outputPanel>
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
														value="Reply To Given Objections For Belowgrade Indent Applications"
														style="COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Monotype Corsiva;">
													</h:outputText>
													<h:inputHidden value="#{replyForObjectionExportUnitAction.hidden}" />
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
						<rich:dataTable id="table22" rows="10" width="100%"
							value="#{replyForObjectionExportUnitAction.displayObjections}" var="list"
							headerClass="TableHead" footerClass="TableHead"
							styleClass="DataTable" rowClasses="TableRow1,TableRow2">


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Sr.No">
									</h:outputText>
								</f:facet>
								<h:outputText style="margin-left: 20px;" value="#{list.srNo}"></h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Application No" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.showApplicationID_dt}" />
								</center>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Objection Raised By" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.objectionRaisedBy_dt}" />
								</center>
							</rich:column>
							<rich:column>
								<f:facet name="header">
									<h:outputText value="Objection Date">
									</h:outputText>
								</f:facet>
								<h:outputText styleClass="generalExciseStyle"
									value="#{list.objectionDate_dt}">
								</h:outputText>
							</rich:column>



							<rich:column>
								<f:facet name="header">
									<h:outputText value="Objection For" />
								</f:facet>
								<center>
									<h:outputText styleClass="generalExciseStyle"
										value="#{list.objectionFor_dt}" />
								</center>
							</rich:column>

							<rich:column id="column3">
								<f:facet name="header">
									<h:outputText value="Description">
									</h:outputText>
								</f:facet>

								<h:outputText value="#{list.description_dt}"></h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="Reply" />
								</f:facet>
								<center>
									<h:commandButton value="Reply To Objection"
										disabled="#{list.disableFlg}"
										actionListener="#{replyForObjectionExportUnitAction.viewDetails}"
										styleClass="btn btn-info btn-sm">
									</h:commandButton>
								</center>
							</rich:column>

							<f:facet name="footer">
								<rich:datascroller for="table22"></rich:datascroller>
							</f:facet>
						</rich:dataTable>

					</div>
				</div>

				<div class="row" align="center">
					<rich:spacer height="20px"></rich:spacer>
				</div>
				<h:panelGroup rendered="#{replyForObjectionExportUnitAction.viewPanelFlg}">
					<div class="row" align="center" style="background-color: #e6ffff">
						<div class="col-md-12">
							<h:outputText
								value="Application No.:#{replyForObjectionExportUnitAction.vch_application_no} "
								style="COLOR: #2952a3; font-weight:bold; FONT-SIZE: x-large; font-family:Times New Roman" />
						</div>
					</div>
					<div class="row" align="center">
						<rich:spacer height="20px" />
					</div>



				</h:panelGroup>

			</div>
		</h:form>
	</f:view>
	
	
	<h:panelGroup rendered="#{replyForObjectionExportUnitAction.viewPanelFlg}">
	<h:form>
		<div class="row" style="BACKGROUND-COLOR: #5bc0de; padding-top: 10px;">
			<div class="col-sm-3">
				<rich:spacer height="10px;" />
				<b>Upload pdf (if any):</b>
			</div>
			<div class="col-md-3" align="left">


									<rich:fileUpload listHeight="40px" listWidth="225px"
										fileUploadListener="#{replyForObjectionExportUnitAction.doc1uploadMethod1}"
										maxFilesQuantity="1"
										ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
										sizeErrorLabel="" acceptedTypes="pdf"
										addControlLabel="Add PDF">

										<a4j:support event="onuploadcomplete" reRender="renpdftrue1"></a4j:support>
									</rich:fileUpload>





								</div>
								<div class="col-md-1 img-responsive">
									<a4j:outputPanel id="renpdftrue1">
										<h:outputLink
											rendered="#{replyForObjectionExportUnitAction.pdfDone}"
											target="_blank"
											value="/doc/ExciseUp/ExportOutsideIndia/Objection/#{replyForObjectionExportUnitAction.recordFile}">

											<h:graphicImage value="/img/download.gif" alt="view document"
												style="width : 60px; height : 35px;"></h:graphicImage>
										</h:outputLink>

									</a4j:outputPanel>
								</div>

								<div class="col-md-2 " align="left" style="FONT-SIZE: xx-small;">
									<h:commandButton styleClass="btn btn-default"
										action="#{replyForObjectionExportUnitAction.resetpage}"
										value="Upload Pdf" />
								</div>

								

		</div>
		</h:form>
		<f:view>
			<h:form>
				<div class="row"
					style="BACKGROUND-COLOR: #5bc0de; padding: 0px 0px 15px 0px;">
					<div class="row">
						<div class="col-md-3 col-sm-4"></div>

						
					</div>
					<div class="col-md-12">
						<div class="col-md-3 col-sm-4">

							<b>* Comment:</b>
						</div>
						<div class="col-md-6 col-sm-8">

							<div class="row">
								<h:inputTextarea value="#{replyForObjectionExportUnitAction.fillComment}"
									styleClass="form-control"
									style="FONT-STYLE: italic;width: 100%;"></h:inputTextarea>
							</div>
						</div>

					</div>
				</div>
				<div class="row" align="center">

					<h:commandButton value="Submit" styleClass="btn btn-success"
						action="#{replyForObjectionExportUnitAction.save}" />
					<h:commandButton value="Close" styleClass="btn btn-warning"
						action="#{replyForObjectionExportUnitAction.closeApplication}" />
				</div>
				 
			</h:form>
		</f:view>
	</h:panelGroup>
<f:view>
		<h:form>
			<div class="row" align="center">
				<h:commandButton
					action="#{replyForObjectionExportUnitAction.backToRegistration}"
					value="BACK" class="btn btn-default" />
			</div>
		</h:form>
	</f:view>

</ui:composition>