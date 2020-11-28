<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

	<f:view>

		<h:form>
			<head>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.0.0/animate.min.css"
	rel="stylesheet" type="text/css" />

 
			</head>
			<div class="row">
				<rich:spacer height="15px;"></rich:spacer>
			</div>
			<div class="row">
				<a4j:outputPanel id="msg">
					<div class="row col-md-12  " style="margin-top: 10px;">
						<h:messages errorStyle="color:red" style="font-size: 18px;"
							styleClass="generalExciseStyle" layout="table"
							infoStyle="color:green" />
					</div>
				</a4j:outputPanel>
			</div>

			<div class="row" align="center" style="background-color: #253f8a;">
				<h:outputText
					value="  Export Overseas GatePass 2020-21"
					style="FONT-SIZE: xx-large; FONT-FAMILY: 'Agency FB'; COLOR: #ffffff; TEXT-DECORATION: underline;" />
				<h:inputHidden value="#{fLB11_EOIAction.hidden}"></h:inputHidden>
			</div>

			<div class="row">
				<rich:spacer height="15px;"></rich:spacer>
			</div>
			<rich:separator lineType="dashed"></rich:separator>
			<div align="center">
				<h:outputLabel value="#{fLB11_EOIAction.dissteleryName}"
					style="COLOR: #000000; FONT-SIZE:  large;"></h:outputLabel>
			</div>

			<div align="center">
				<h:outputLabel value="#{fLB11_EOIAction.disslryAdd}"
					style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel>
			</div>
			<rich:separator lineType="dashed"></rich:separator>
			<rich:spacer height="15px;"></rich:spacer>

			<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="Date :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">


					<rich:calendar value="#{fLB11_EOIAction.dt_date}" readonly="true"
						styleClass="form-control"></rich:calendar>
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Valid Till :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">

					<rich:calendar value="#{fLB11_EOIAction.validtilldt_date}"
						styleClass="form-control"></rich:calendar>
				</div>


			</div>
			<rich:spacer height="15px;"></rich:spacer>

			<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="FROM :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
				<h:selectOneRadio
								value="#{fLB11_EOIAction.vch_from}"
								onchange="this.form.submit();"
								valueChangeListener="#{fLB11_EOIAction.fromListMethod}">

	<f:selectItem itemValue="FL3" itemLabel="FL3" />
						<f:selectItem itemValue="FL3A" itemLabel="FL3A" />

							</h:selectOneRadio>

				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="License No."
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
					<h:selectOneMenu
						
						value="#{fLB11_EOIAction.vch_from_lic_no}"
						onchange="this.form.submit()"
						disabled="#{fLB11_EOIAction.lic_disable_flag2}"
						style="border-radius: 6px;padding: 5px 5px;height: 30px;width: 300px;border: 1px solid #669999;">
						<f:selectItems value="#{fLB11_EOIAction.fromliclist}" />
					</h:selectOneMenu>
				</div>


			</div>

			<rich:spacer height="15px;"></rich:spacer>

			<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="To :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
					<h:selectOneRadio value="#{fLB11_EOIAction.vch_to}" >
						<f:selectItem itemValue="EOI" itemLabel="EXPORT OUTSIDE INDIA" />

					</h:selectOneRadio>

				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Export Order No."
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
					<h:selectOneMenu
						valueChangeListener="#{fLB11_EOIAction.listMethod}"
						value="#{fLB11_EOIAction.vch_to_lic_no}"
						onchange="this.form.submit()"
						disabled="#{fLB11_EOIAction.lic_disable_flag}"
						style="border-radius: 6px;padding: 5px 5px;height: 30px;width: 300px;box-shadow: 1px 1px 15px lightsteelblue;border: 1px solid #669999;">
					
								<f:selectItems value="#{fLB11_EOIAction.toliclist}"  />
					</h:selectOneMenu>
				</div>


			</div>
			<rich:spacer height="15px;"></rich:spacer>

			<div class="col-md-12 row">
				<div class="col-md-2" align="right"></div>
				<div class="col-md-4" align="left"></div>
				<div class="col-md-2" align="right">
					<h:outputText value="Bond Value."
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-3" align="left">
					<h:inputText value="#{fLB11_EOIAction.bondValue}"
						styleClass="form-control" >

					</h:inputText>
				</div>


			</div>
			<rich:spacer height="15px;"></rich:spacer>

			<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="Import Order No :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
	<h:inputText
									value="#{fLB11_EOIAction.unitNo}"
									style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>
			
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Purchase Order No."
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
					<h:inputText
									value="#{fLB11_EOIAction.purchaseOrderNo}"
									style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>
					
				</div>


			</div>
			<rich:spacer height="15px;"></rich:spacer>

			<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="Country:"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
<h:inputText
									value="#{fLB11_EOIAction.country}"
									style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>
					
			
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Name Of Importing Unit."
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
					
<h:inputText
									value="#{fLB11_EOIAction.unit_nm}"
									style="COLOR: #0000ff;" readonly="true" styleClass="form-control"></h:inputText>
				
				</div>


			</div>
			<rich:spacer height="15px;"></rich:spacer>

			<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="Vehicle No.."
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
					<h:inputText value="#{fLB11_EOIAction.vehicleNo}"
							styleClass="form-control" >

					</h:inputText>
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Route Details:"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">

					<h:inputText value="#{fLB11_EOIAction.routeDtl}"
						styleClass="form-control" >

					</h:inputText>
				</div>
			


			</div>
			<rich:spacer height="15px;"></rich:spacer>

<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="ICD"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
					<h:selectOneMenu  styleClass="form-control"  
										value="#{fLB11_EOIAction.icd}" >
										<f:selectItems
											value="#{eoi_app_exporder_action.icd_list}"></f:selectItems>
									</h:selectOneMenu>
				</div>
				<div class="col-md-2" align="right">
					 
				</div>
				<div class="col-md-4" align="left">

					 
				</div>
			


			</div>


<rich:spacer height="15px;"></rich:spacer>


			<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="Vehicle Driver Name:"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">

					<h:inputText value="#{fLB11_EOIAction.vehicleDrvrName}"
						styleClass="form-control" >

					</h:inputText>
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Vehicle Agency Name And Address"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">
					<h:inputText value="#{fLB11_EOIAction.vehicleAgencyNmAdrs}"
							styleClass="form-control" >

					</h:inputText>
				</div>


			</div>
			<rich:spacer height="15px;"></rich:spacer>

				<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="Gross Weight"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
	        
						<h:inputText value="#{fLB11_EOIAction.grossWeight}"
							styleClass="form-control" >
<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
					</h:inputText>
				
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Tare Weight :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-2" align="left">

					<h:inputText value="#{fLB11_EOIAction.tierWeight}"
							styleClass="form-control" >
<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
					</h:inputText>
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Net Weight :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-2" align="left">
			<a4j:outputPanel id="net-weight">
					<h:inputText value="#{fLB11_EOIAction.netWeight}"
							styleClass="form-control"   disabled="true" >

					</h:inputText>
					</a4j:outputPanel>
				</div>


			</div>
			<rich:spacer height="15px;"></rich:spacer>

		
		<div class="col-md-12 row">
				 
			 
				<div class="col-md-2" align="right">
					<h:outputText value="Container Type :"
						style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
				</div>
				<div class="col-md-4" align="left">

					<h:selectOneRadio  
							style="FONT-WEIGHT: bold;  width: 40%; "
							value="#{fLB11_EOIAction.radio1}"						
							onchange="this.form.submit();">
							<f:selectItem itemValue="FC" itemLabel="Full Container " />
							<f:selectItem itemValue="PC" itemLabel=" Part Container" />
							
							
							
						</h:selectOneRadio>
				</div>
				 


			</div>
		
		<div class="col-md-12 row">
				<div class="col-md-2" align="right">
					<h:outputText value="Container Number:" 	></h:outputText>
				</div>
				<div class="col-md-2" align="left">
	        
						<h:inputText value="#{fLB11_EOIAction.containerno}"  ></h:inputText>
				
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Seal Number:" 	></h:outputText>
				</div>
				<div class="col-md-2" align="left">
<h:inputText value="#{fLB11_EOIAction.sealno}"
				></h:inputText>
				</div>
				<div class="col-md-2" align="right">
					<h:outputText value="Sealing Date:" 	></h:outputText>
				</div>
				<div class="col-md-2" align="left">
			<rich:calendar value="#{fLB11_EOIAction.sealdate }"
				></rich:calendar>
				</div>


			</div>
			<div>	<rich:spacer height="40px;"></rich:spacer></div>
			<div>
				<rich:dataTable columnClasses="columnClass1"  
					  rowClasses="TableRow1,TableRow2"
					styleClass="DataTable" id="table3" width="100%"
					value="#{fLB11_EOIAction.displaylist}"
					var="list">
					<rich:column>
						<f:facet name="header">
							<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
						</f:facet>
						<center>
							<h:outputText value="#{list.slno}" />
						</center>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Brand" styleClass="generalHeaderOutputTable" />
						</f:facet>
						<h:outputText value="#{list.vch_brand}">
						</h:outputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Size" styleClass="generalHeaderOutputTable" />
						</f:facet>
						<h:outputText value="#{list.size}" >

						</h:outputText>
					</rich:column>
<rich:column>
						<f:facet name="header">
							<h:outputText value="Stock Bottle"
								style="white-space: normal" />
						</f:facet>
						<h:outputText value="#{list.stkbtl}">
						</h:outputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="Available Bottle in Selected Order"
								style="white-space: normal" />
						</f:facet>
						<h:outputText value="#{list.int_bottle_avaliable}">
						</h:outputText>
					</rich:column>



					 

					<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.dispatchBoxes}">
											<a4j:support
												reRender="disbtl"
												event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Bottle"
												style="white-space: normal"/>
										</f:facet>
										<h:outputText value="#{list.int_dispatch}" id="disbtl" >
										</h:outputText>
									</rich:column>


	<rich:column>
										<f:facet name="header">
											<h:outputText value="Batch No."
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.batchNo}">
										</h:inputText>
									</rich:column>
									

									      





					<f:facet name="footer">

					</f:facet>
				</rich:dataTable>
			</div>
			<table>
									
									<tr></tr>
								</table>
					<div align="center">
							<rich:spacer height="60px" />
							<h:commandButton
							

								action="#{fLB11_EOIAction.save}"
								onclick="return confirm('ALERT : You are submitting the application. Please Confirm Your Details !!');" 							
								value="Save" styleClass="btn btn-success" />
							<rich:spacer width="15px;"></rich:spacer>
							<h:commandButton
						
								action="#{fLB11_EOIAction.clearAll}"
								value="Reset" styleClass="btn btn-info  active" />
							<rich:spacer width="15px;"></rich:spacer>
							

						</div>	
						
						<TABLE width="100%" align="center">						
<tr>
							<td>
							<div class="row">
						 Dispatch Date :
							<rich:spacer width="5px"></rich:spacer> <rich:calendar valueChangeListener="#{fLB11_EOIAction.listMethod1}"
																 onchanged="this.form.submit();"
																value="#{fLB11_EOIAction.table_dt}" /> 

					</div>
							<rich:dataTable align="center" id="table55" rows="20"
									width="100%" var="list11"
									value="#{fLB11_EOIAction.getListWholesale}"
									 
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.sno}" />
										</center>
									</rich:column>


									<rich:column sortBy="#{list11.vch_gatepass_no}"
										filterBy="#{list11.vch_gatepass_no}">
										<f:facet name="header">
											<h:outputLabel value="Gate Pass No" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_gatepass_no}" />
										</center>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="GTIN No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.gtinno}" />
										</center>
									</rich:column>
									<rich:column sortBy="#{list11.dt_date}">
										<f:facet name="header">
											<h:outputLabel value="Date " />
										</f:facet>
										<center>
											<h:outputText value="#{list11.dt_date}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="License Type" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_to}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="License No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_to_lic_no}" />
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Bond" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_bond}" />
										</center>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Bottles / boxes" />
										</f:facet>
										<center>
											<h:outputText
												value="#{list11.findispatchedbttl} / #{list11.findispatchedbox}" />
										</center>
									</rich:column>


									<rich:column>
										<f:facet name="header">

										</f:facet>
										<center>
											<h:commandButton styleClass="btn btn-success"
												value="Print Draft" rendered="#{list11.printDraft}"
												actionListener="#{fLB11_EOIAction.printDraftReport}">
											</h:commandButton>
											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{fLB11_EOIAction.pdfDraft}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text224"
													value="View Draft Report"
													rendered="#{list11.draftprintFlag}"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>

											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{list11.pdfDraft1}"
												target="_blank">
												<h:outputText styleClass="outputText"
													value="View Draft PD26"
													rendered="#{list11.draftprintFlag1}"
													style="color: blue; font-family: serif; font-size: 12pt" />
											</h:outputLink>

											<h:commandButton value="Print" styleClass="btn btn-success"
												rendered="#{list11.finalizePrint}"
												actionListener="#{fLB11_EOIAction.printReport}">
											</h:commandButton>

											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{fLB11_EOIAction.pdfName}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text223"
													value="View FLB11" rendered="#{list11.myFlagDt}"
													style="color: blue; font-family: serif; font-size: 12pt" />
											</h:outputLink>
											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{list11.pdfNameDt1}"
												target="_blank">
												<h:outputText styleClass="outputText" value="View PD26"
													rendered="#{list11.myFlagDt1}"
													style="color: blue; font-family: serif; font-size: 12pt" />
											</h:outputLink>
											<h:commandButton styleClass="btn btn-success"
												value="Scan and Upload" rendered="#{list11.printDraft}"
												actionListener="#{fLB11_EOIAction.scanAndUpload}">
											</h:commandButton>
										</center>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Cancel gate pass" />
										</f:facet>
										<center>
											<h:commandButton styleClass="btn btn-danger"
												rendered="#{list11.printDraft}" onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true; off()"
												actionListener="#{fLB11_EOIAction.cancel_gatepass}"
												value="Cancel Gatepass" />
										</center>
									</rich:column>


									<f:facet name="footer">
										<rich:datascroller for="table55" />
									</f:facet>
								</rich:dataTable></td>
						</tr>
					</TABLE>
					<table align="center">
						<tr>	 
						 

							<TD align="center">
							<h:selectOneRadio
									value="#{fLB11_EOIAction.exclcsv}"
									rendered="#{fLB11_EOIAction.gatePassFlag}"
									onchange="this.form.submit()">
									 
									<f:selectItem itemValue="C" itemLabel="Upload CSV" />

								</h:selectOneRadio></TD>
						</tr>
						
						<tr>
							<td style="FONT-WEIGHT: bold; color: Green;" align="left"><h:outputText
									value="Upload excel for Gatepass Number::"
									rendered="#{fLB11_EOIAction.gatePassFlag}"
									style="FONT-SIZE: medium;" /></td>
							<td style="FONT-SIZE: large; color: Red"><h:outputText
									value="#{fLB11_EOIAction.scanGatePassNo}"
									rendered="#{fLB11_EOIAction.gatePassFlag}" />
							</td>
							<TD><h:outputLink value="/doc/ExciseUp/ExcelFormat.pdf"
									target="_blank">
									<h:graphicImage value="/img/i.png" style="width:40px;"
										rendered="#{fLB11_EOIAction.gatePassFlag}"></h:graphicImage>
								</h:outputLink></TD>
							 

							 
								<td><rich:fileUpload addControlLabel="Add File" id="link34"
									fileUploadListener="#{fLB11_EOIAction.uploadCsv}"
									rendered="#{fLB11_EOIAction.gatePassFlag and fLB11_EOIAction.exclcsv eq 'C'}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload CSV"
									styleClass="btn btn-primary"
									rendered="#{fLB11_EOIAction.gatePassFlag and fLB11_EOIAction.exclcsv eq 'C'}"
									action="#{fLB11_EOIAction.csvSubmit }">
								</h:commandButton></td>
							<td></td>

							<td></td>
						</tr>
					</table>
					<div>
						<rich:dataTable align="center" id="table57" rows="10" width="100%"
							var="listk" rendered="#{fLB11_EOIAction.tableFlag}"
							value="#{fLB11_EOIAction.getVal }"
							headerClass="TableHead" footerClass="TableHead"
							rowClasses="TableRow1,TableRow2">

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="GatePass.No" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.gatepass}" />
								</center>
							</rich:column>

							<rich:column  filterBy="#{listk.casecode}" sortBy="#{listk.casecode}">
								<f:facet name="header">
									<h:outputLabel value="CaseCode" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.casecode}" />
								</center>
							</rich:column>
                           <rich:column filterBy="#{listk.cascodeMatching}" sortBy="#{listk.cascodeMatching}">
								<f:facet name="header">
									<h:outputLabel value="CaseCodeMatching" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.cascodeMatching}" />
								</center>
							</rich:column>
							  <rich:column >
								<f:facet name="header">
									<h:outputLabel value="Description" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.brand_name}-#{listk.casecoseBrandSize}-#{listk.date_plan}" />
								</center>
							</rich:column>
							
						

						 

							<f:facet name="footer">
								<rich:datascroller for="table57" />
							</f:facet>
						</rich:dataTable>
					</div>
					<table align="center">
						<tr>
							<td><h:commandButton value="Finalise"
									styleClass="btn btn-primary"
									rendered="#{fLB11_EOIAction.tableFlag}" disabled="#{fLB11_EOIAction.bottlingNotDoneFlag }"
									action="#{fLB11_EOIAction.finalSubmit}">

								</h:commandButton> <h:commandButton value="Cancel" styleClass="btn btn-danger"
									rendered="#{fLB11_EOIAction.tableFlag}"
									action="#{fLB11_EOIAction.delete}">
								</h:commandButton></td>

						</tr>
					</table>		
								

		</h:form>
	</f:view>
</ui:composition>