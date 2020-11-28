<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>

		<h:form style="background-color:whitesmoke;">
			<head>
<style>
.striped-border {
	border: 1px dashed #000;
	width: 50%;
	margin: auto;
	margin-top: 5%;
	margin-bottom: 5%;
}

.example3 {
	border: 0;
	height: 1px;
	background: #000066;
}
</style>


			</head>
			<div class="form-group">
				<div class="row">
					<a4j:outputPanel id="msg">
						<div class="row col-md-12 wow shake" style="margin-top: 10px;">
							<h:messages errorStyle="color:red" style="font-size: 18px;"
								styleClass="generalExciseStyle" layout="table"
								infoStyle="color:green" />
						</div>
					</a4j:outputPanel>
				</div>
				<div class="row " align="center">
					<rich:spacer height="10px"></rich:spacer>
				</div>
				<div class="row">
					<h:panelGroup>
						<div align="center">
							<h:outputText value="Update Historical Data"
								style="COLOR: #000080; FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>

							<h:inputHidden
								value="#{historicalforDistrictasExportUnitAction.hidden}"></h:inputHidden>
						</div>
						<div class="row" align="center">
							<h:selectOneRadio
								value="#{historicalforDistrictasExportUnitAction.radio}"
								onchange="this.form.submit();"
								valueChangeListener="#{historicalforDistrictasExportUnitAction.radioListener1}">


								<f:selectItem itemValue="S" itemLabel="Upload Shipping Bill" />
								<f:selectItem itemValue="BRC" itemLabel="Upload BRC" />
								<f:selectItem itemValue="A" itemLabel="Uploaded" />

							</h:selectOneRadio>
						</div>

						<rich:dataTable columnClasses="columnClass1"
							headerClass="TableHead" rowClasses="TableRow1,TableRow2"
							styleClass="DataTable" id="table2" rows="10" width="100%"
							value="#{historicalforDistrictasExportUnitAction.showData}"
							var="list">

							<rich:column>

								<f:facet name="header">
									<h:outputText value="Sr.No."
										styleClass="generalHeaderOutputTable"
										style=" FONT-WEIGHT: bold;"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.sr_no}" readonly="true">

								</h:outputText>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Gatepass NO."
										styleClass="generalHeaderOutputTable"
										style=" FONT-WEIGHT: bold;"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.gatapass_no}" readonly="true"></h:outputText>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Gatepass Date"
										styleClass="generalHeaderOutputTable"
										style=" FONT-WEIGHT: bold;">
									</h:outputText>

								</f:facet>
								<h:outputText value="#{list.gatepass_dt}" readonly="true">
								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="From "
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.from}" readonly="true">

								</h:outputText>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputText value="To"
										style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
									</h:outputText>
								</f:facet>
								<h:outputText value="#{list.to}" readonly="true">

								</h:outputText>
							</rich:column>
							<rich:column>
								<f:facet name="header">

								</f:facet>

								<h:commandButton value="View"
									action="#{historicalforDistrictasExportUnitAction.viewdetail}">
									<f:setPropertyActionListener
										target="#{historicalforDistrictasExportUnitAction.prt}"
										value="#{list}" />
								</h:commandButton>

							</rich:column>


							<f:facet name="footer">
								<rich:datascroller for="table2" />
							</f:facet>
						</rich:dataTable>

					</h:panelGroup>
				</div>

			</div>


			<h:panelGroup
				rendered="#{historicalforDistrictasExportUnitAction.viewflg eq 'T'}">

				<div class="row" align="center">

					<div class="col-md-4" align="right">
						<h:outputText value="FLB11_No:  "
							style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
					</div>
					<div class="col-md-4" align="left">
						<h:inputText
							value="#{historicalforDistrictasExportUnitAction.getpass_no}"
							style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>
					</div>
				</div>
				<rich:spacer height="10px"></rich:spacer>

				<rich:dataTable id="table22" rows="10" width="100%"
					value="#{historicalforDistrictasExportUnitAction.viewdetail1}"
					var="list" headerClass="TableHead" footerClass="TableHead"
					styleClass="DataTable" rowClasses="TableRow1,TableRow2"
					style="FONT-SIZE: small;">

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Sr.No."
								styleClass="generalHeaderOutputTable"
								style=" FONT-WEIGHT: bold;"></h:outputText>
						</f:facet>
						<h:outputText value="#{list.sr_no1}" readonly="true">

						</h:outputText>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Brand">

							</h:outputText>
						</f:facet>
						<center>
							<h:outputText styleClass="generalExciseStyle"
								value="#{list.brand}" />
						</center>

					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Size">

							</h:outputText>
						</f:facet>
						<center>
							<h:outputText styleClass="generalExciseStyle"
								value="#{list.size}" />
						</center>

					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="Bottel" />
						</f:facet>
						<center>


							<h:outputText styleClass="generalExciseStyle"
								value="#{list.bottel}" />

						</center>
					</rich:column>
					<rich:column>
						<f:facet name="header">
							<h:outputText value="cases" />
						</f:facet>
						<center>
							<h:outputText styleClass="generalExciseStyle" value="#{list.box}" />
						</center>
					</rich:column>
				</rich:dataTable>



				<rich:spacer height="10px"></rich:spacer>
				<div align="left">
					<h:outputText value="*All Fields Are Mandatory:"
						style=" text-shadow: 2px 2px 4px #000000;   FONT-WEIGHT: bold;font-family:Comic Sans MS;"></h:outputText>


				</div>
<h:panelGroup  rendered="#{historicalforDistrictasExportUnitAction.radio eq 'S' or  historicalforDistrictasExportUnitAction.radio eq 'A' }">
				<div>
					<div class="col-md-2" align="right">
						<h:outputText value=" Shipping Bill Date"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<rich:calendar
							value="#{historicalforDistrictasExportUnitAction.leo_date}"
							disabled="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"></rich:calendar>
					</div>
				</div>

				<div>
					<div class="col-md-2" align="right">
						<h:outputText value=" Shipping Bill Number"></h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:inputText
							value="#{historicalforDistrictasExportUnitAction.leo_number}"
							disabled="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"></h:inputText>
					</div>
					<div class="col-md-2" align="right">
						<h:outputText value="Shipping Bill No Of Bottles">
						</h:outputText>
					</div>
					<div class="col-md-2" align="left">
						<h:inputText
							value="#{historicalforDistrictasExportUnitAction.leo_no_of_bottel}"
							disabled="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"></h:inputText>
					</div>
				</div>


				<rich:spacer height="20px"></rich:spacer>
				<!-- fisrt uploader -->
				<div class="col-md-12 row">
					<div class="col-md-3" align="right">
						<h:outputLabel value="Upload Shipping Bill Copy Pdf Only :"
							style="FONT-STYLE: italic; FONT-WEIGHT:" />

					</div>
					<div class="col-md-2" align="left">

						<h:outputLink
							rendered="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"
							value="/doc/ExciseUp/HistoricalExport/pdf/#{historicalforDistrictasExportUnitAction.recordFile}"
							target="_blank">

							<h:outputText value="LEO Copy Pdf"
								style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000ff;"></h:outputText>

						</h:outputLink>

					</div>
					<div class="col-md-3" align="left">


						<rich:fileUpload listHeight="40px" listWidth="225px"
							rendered="#{historicalforDistrictasExportUnitAction.radio eq 'S'}"
							fileUploadListener="#{historicalforDistrictasExportUnitAction.doc1uploadMethod}"
							maxFilesQuantity="1"
							ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
							sizeErrorLabel="" acceptedTypes="pdf" addControlLabel="Add PDF">

							<a4j:support event="onuploadcomplete" reRender="renpdftrue2"></a4j:support>
						</rich:fileUpload>
						<rich:spacer height="40px"></rich:spacer>
					</div>

					<div class="col-md-3 " align="left" style="FONT-SIZE: xx-small;">
						<h:commandButton styleClass="btn btn-default"
							rendered="#{historicalforDistrictasExportUnitAction.radio eq 'S'}"
							action="#{historicalforDistrictasExportUnitAction.resetpage}"
							value="Upload Pdf" />
					</div>

					<div class="col-md-1 img-responsive">
						<a4j:outputPanel id="renpdftrue2">
							<h:outputLink
								rendered="#{historicalforDistrictasExportUnitAction.doc1upload and historicalforDistrictasExportUnitAction.radio eq 'S'}"
								target="_blank"
								value="/doc/ExciseUp/HistoricalExport/pdf/#{historicalforDistrictasExportUnitAction.recordFile}">

								<h:graphicImage value="/img/download.gif" alt="view document"
									style="width : 60px; height : 35px;"></h:graphicImage>
							</h:outputLink>

						</a4j:outputPanel>
					</div>

				</div>
				
</h:panelGroup>
				<!-- end of first uploader  -->

<h:panelGroup  rendered="#{historicalforDistrictasExportUnitAction.radio eq 'BRC' or  historicalforDistrictasExportUnitAction.radio eq 'A' }">
				<div class="col-md-3" align="right">

					<h:outputText
						value="Date Of Receipt Of FLB11 for Which the has Bond has Received Date"></h:outputText>
				</div>
				<div>
					<div class="col-md-3" align="left">
						<rich:calendar
							value="#{historicalforDistrictasExportUnitAction.riceipt_date}"
							disabled="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"></rich:calendar>
					</div>
				</div>

				<div>
					<div class="col-md-3" align="right">
						<h:outputText value="BRC Date">
						</h:outputText>
					</div>
				</div>

				<div class="col-md-3" align="left">
					<rich:calendar
						value="#{historicalforDistrictasExportUnitAction.brc_dt}"
						disabled="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"></rich:calendar>
				</div>

				<div>
					<rich:spacer height="30px"></rich:spacer>
					<div class="row">
						<div class="col-md-3" align="right">
							<h:outputText value="BRC No">
							</h:outputText>

						</div>
						<div class="col-md-3" align="left">
							<h:inputText
								value="#{historicalforDistrictasExportUnitAction.brc_no}"
								disabled="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"></h:inputText>
						</div>

						<div class="col-md-3" align="right">
							<h:outputText value="BRC No Of Bottles">
							</h:outputText>
						</div>
						<div class="col-md-3" align="left">
							<h:inputText
								value="#{historicalforDistrictasExportUnitAction.brc_no_bottel}"
								disabled="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"></h:inputText>
						</div>

					</div>

					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<!-- Second uploader -->
					<div class="col-md-12 row">
						<div class="col-md-3" align="right">
							<h:outputLabel value="Upload BRC Copy Pdf Only:"
								style="FONT-STYLE: italic; FONT-WEIGHT: " />
							<rich:spacer height="20px"></rich:spacer>
						</div>
						<div class="col-md-2" align="left">

							<h:outputLink
								rendered="#{historicalforDistrictasExportUnitAction.radio eq 'A'}"
								value="/doc/ExciseUp/HistoricalExport/pdf/#{historicalforDistrictasExportUnitAction.recordFile1}"
								target="_blank">

								<h:outputText value="BRC Copy Pdf"
									style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000ff;"></h:outputText>

							</h:outputLink>
						</div>

						<div class="col-md-3" align="left">


							<rich:fileUpload listHeight="40px" listWidth="225px"
								rendered="#{historicalforDistrictasExportUnitAction.radio eq 'BRC'}"
								fileUploadListener="#{historicalforDistrictasExportUnitAction.doc1uploadMethod1}"
								maxFilesQuantity="1"
								ontyperejected="if (!confirm('Only pdf files are accepted')) return false"
								sizeErrorLabel="" acceptedTypes="pdf" addControlLabel="Add PDF">

								<a4j:support event="onuploadcomplete" reRender="renpdftrue2"></a4j:support>
							</rich:fileUpload>
							<rich:spacer height="40px"></rich:spacer>




						</div>

						<div class="col-md-3 " align="left" style="FONT-SIZE: xx-small;">
							<h:commandButton styleClass="btn btn-default"
								rendered="#{historicalforDistrictasExportUnitAction.radio eq 'BRC'}"
								action="#{historicalforDistrictasExportUnitAction.resetpage1}"
								value="Upload Pdf" />
						</div>

						<div class="col-md-1 img-responsive">
							<a4j:outputPanel id="renpdftrue1">
								<h:outputLink
									rendered="#{historicalforDistrictasExportUnitAction.doc1upload1  and  historicalforDistrictasExportUnitAction.radio eq 'BRC'}"
									target="_blank"
									value="/doc/ExciseUp/HistoricalExport/pdf/#{historicalforDistrictasExportUnitAction.recordFile1}">

									<h:graphicImage value="/img/download.gif" alt="view document"
										style="width : 60px; height : 35px;"></h:graphicImage>
								</h:outputLink>

							</a4j:outputPanel>
						</div>

					</div>



</div>
					</h:panelGroup >
					<!-- end of Second uploader  -->
					
					
					<div class="row">
						 
						
						 
						<div class="col-md-3" align="right">
							<h:outputText value="Amount:" rendered="#{historicalforDistrictasExportUnitAction.radio eq 'BRC' or historicalforDistrictasExportUnitAction.radio eq 'A'}"
								style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
						 </div>
						 <div class="col-md-2" align="right">
							<h:selectOneMenu onchange="this.form.submit();" rendered="#{historicalforDistrictasExportUnitAction.radio eq 'BRC' or historicalforDistrictasExportUnitAction.radio eq 'A'}"
								styleClass="form-control"
								style="border-radius: 6px;padding: 5px 5px;height: 30px;width: 30%;box-shadow: 1px 1px 15px lightsteelblue;border: 1px solid #669999;"
								value="#{historicalforDistrictasExportUnitAction.currency_id }">
								<f:selectItems
									value="#{historicalforDistrictasExportUnitAction.currency_list}"></f:selectItems>
							</h:selectOneMenu>
</div>

						  <div class="col-md-6" align="right">
							<h:inputText
								value="#{historicalforDistrictasExportUnitAction.amount}" 
								disabled="#{historicalforDistrictasExportUnitAction.radio eq 'A'}" rendered="#{historicalforDistrictasExportUnitAction.radio eq 'BRC'or historicalforDistrictasExportUnitAction.radio eq 'A'}"
								style="border-radius: 6px;padding: 5px 5px;height: 30px;width: 300px;box-shadow: 1px 1px 4px 4px gray;border: 1px solid #669999;"></h:inputText>


						</div>

					</div>
					<div align="center">
				
						<h:commandButton value="Save" 
							
							rendered="#{historicalforDistrictasExportUnitAction.radio eq 'S' or  historicalforDistrictasExportUnitAction.radio eq 'BRC' }"
							
							styleClass="btn-sm btn-success"
							action="#{historicalforDistrictasExportUnitAction.save}"  ></h:commandButton>


						<rich:spacer width="10px"></rich:spacer>
						<h:commandButton value="Close" styleClass="btn-sm btn-warning"
							action="#{historicalforDistrictasExportUnitAction.close}"
							style="COLOR: #0000a0;" />
					</div>
		


			</h:panelGroup>

		</h:form>
	</f:view>

</ui:composition>