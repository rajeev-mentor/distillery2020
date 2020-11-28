<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
		<style>
#overlay {
	position: fixed; /* Sit on top of the page content */
	display: none; /* Hidden by default */
	width: 100%; /* Full width (cover the whole page) */
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: rgba(0, 0, 0, 0.5);
	/* Black background with opacity */
	z-index: 2;
	/* Specify a stack order in case you're using a different order for other elements */
	cursor: pointer; /* Add a pointer on hover */
}
</style>
		<h:form>
			<div class="panel panel-default">
				<div class="panel-body">
					<TABLE width="100%" align="center">
						<TR>
							<TD>
								<TABLE width="80%">
									<TBODY>
										<TR>
											<TD align="left"><h3>
													<h:messages errorStyle="color:red" layout="table"
														id="messages" infoStyle="color:green">
													</h:messages>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
							<TD><h:inputHidden value="#{cLGatepassAfterReturnAction.hidden}"></h:inputHidden>
								<h5>
									<h:outputText rendered="#{!cLGatepassAfterReturnAction.saveflg}"
										value="Dispatch For Accidental CL PD-25"
										style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
								</h5></TD>
						</TR>
						<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
							<TD><rich:separator lineType="dashed"></rich:separator></TD>
						</TR>
						<TR align="center">

							<TD align="center">
								<TABLE width="100%" align="center">

									<tr>
										<TD><rich:spacer height="10px"></rich:spacer></TD>
									</tr>
									<tr>
										<TD align="center" colspan="2"><h:outputLabel
												rendered="#{!cLGatepassAfterReturnAction.saveflg}"
												value="#{cLGatepassAfterReturnAction.distName}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>



									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												rendered="#{!cLGatepassAfterReturnAction.saveflg}"
												value="#{cLGatepassAfterReturnAction.distAddress}"
												style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel></TD>

									</TR>
									<tr>
										<TD><rich:spacer height="10px"></rich:spacer></TD>
									</tr>
									<TR align="center"
										style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
										<TD><rich:separator lineType="dashed"></rich:separator></TD>
									</TR>



									<TR align="center">
										<TD colspan="1" align="center">
											<TABLE width="80%" align="center"
												style="background-color: #D0D3D4; padding: 5;">
												<TBODY align="center">
													<tr>
														<td><rich:spacer height="10px;"></rich:spacer></td>
													</tr>
													<tr>
														<TD colspan="2" align="center">Date : <rich:calendar
																value="#{cLGatepassAfterReturnAction.dt_date}" />
														</TD>
														<TD colspan="2">Valid Till : <rich:calendar
																value="#{cLGatepassAfterReturnAction.validtill}" />
														</TD>
													</tr>
													<tr>
														<TD><rich:spacer height="10px"></rich:spacer></TD>
													</tr>
													<TR></TR>


													<tr align="center">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="FROM"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<TD align="left"><h:selectOneRadio
																value="#{cLGatepassAfterReturnAction.vch_from}">
																<f:selectItem itemValue="CL" itemLabel="CL" />
															</h:selectOneRadio></TD>
														<td align="right" style="padding-right: 10px;"><h:outputText
																value="" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</td>
														<td align="left"></td>
													</tr>
													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr align="center">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="TO" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<TD align="left"><h:selectOneRadio
																value="#{cLGatepassAfterReturnAction.vch_to}">

																<f:selectItem itemValue="CL2" itemLabel="CL2" />


															</h:selectOneRadio> <h:selectOneMenu
																
																value="#{cLGatepassAfterReturnAction.districtLic}"
																onchange="this.form.submit();"
																style="width: 100px; height: 28px;">
																<f:selectItems value="#{cLGatepassAfterReturnAction.districtList}" />
															</h:selectOneMenu></TD>
														<td align="right" style="padding-right: 10px;"><h:outputText
																value="License No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>

														</td>
														<td align="left"><h:inputText style="width:250px"
																value="#{cLGatepassAfterReturnAction.vch_to_lic_no}" 
																
																/>
																
																</td>


														<td><h:commandButton
														  rendered="#{!cLGatepassAfterReturnAction.licence_disable_flag}"
														   styleClass="btn btn-primary"
																action="#{cLGatepassAfterReturnAction.getLicenseDetails}"
																value="Get License Details"  >
															</h:commandButton></td>
													</tr>


													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Licensee Name"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText disabled="true"
																value="#{cLGatepassAfterReturnAction.licenceeName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Address of Consignee"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea disabled="true"
																value="#{cLGatepassAfterReturnAction.licenceeAdrs}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Route Details"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{cLGatepassAfterReturnAction.routeDtl}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px; height : 61px;">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{cLGatepassAfterReturnAction.vehicleNo}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Vehicle Driver Name"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{cLGatepassAfterReturnAction.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{cLGatepassAfterReturnAction.vehicleAgencyNmAdrs}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px; height : 61px;">

															</h:inputText></td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Major Districts In Route"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:selectOneMenu
																value="#{cLGatepassAfterReturnAction.district1}"
																style="width: 250px; height: 28px;">
																<f:selectItems value="#{cLGatepassAfterReturnAction.districtList}" />
															</h:selectOneMenu></td>


														<td align="left"><h:selectOneMenu
																value="#{cLGatepassAfterReturnAction.district2}"
																style="width: 250px; height: 28px;">
																<f:selectItems value="#{cLGatepassAfterReturnAction.districtList}" />
															</h:selectOneMenu></td>

														<td align="left"><h:selectOneMenu
																value="#{cLGatepassAfterReturnAction.district3}"
																style="width: 250px; height: 28px;">
																<f:selectItems value="#{cLGatepassAfterReturnAction.districtList}" />
															</h:selectOneMenu></td>
													</tr>

													<tr>
														<td colspan="4"
															style="padding-top: 10px; padding-bottom: 10px;"></td>
													</tr>

													<tr style="padding-bottom: 10px; padding-top: 10px;">
														<td></td>
														<TD align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Gross Weight (In Quintals) "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText value="#{cLGatepassAfterReturnAction.grossWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></TD>

														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Tier Weight (In Quintals) "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText value="#{cLGatepassAfterReturnAction.tierWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></td>
														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Net Weight (In Quintals) "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<a4j:outputPanel id="net-weight">
																<h:inputText value="#{cLGatepassAfterReturnAction.netWeight}"
																	disabled="true" styleClass="generalHeaderOutputTable"
																	style="width:250px">
																</h:inputText>
															</a4j:outputPanel></td>
													</tr>
													<tr>
														<td colspan="4"
															style="padding-top: 10px; padding-bottom: 10px;"></td>
													</tr>

												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<tr>
										<td colspan="4" align="center">
											<table width="90%" align="center">

											</table>
										</td>
									</tr>
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD></TD>
									</TR>
									<tr align="center">
										<td colspan="4"></td>
									</tr>
									<tr align="center">
										<td align="center">
											<table align="center" width="80%">

												<tr>
													<td></td>
												</tr>
											</table> <rich:spacer height="30px;"></rich:spacer>
											<table>
												<tr align="center">
													<td width="100%"></td>
												</tr>
												<tr align="center">
												</tr>
											</table>
										</td>
									</tr>
								</TABLE> 
								
									<rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table555" width="100%" 
													value="#{cLGatepassAfterReturnAction.displaylist1}" var="list"
												>

													<rich:column>
														<f:facet name="header">
															<h:outputLabel value="Sno"
																styleClass="generalHeaderStyle" />
														</f:facet>
														<center>
															<h:outputText styleClass="generalExciseStyle"
																value="#{list.snoIndent}" />
														</center>
													</rich:column>

													<rich:column sortBy="#{list.indentNo}"
														filterBy="#{list.indentNo}">
														<f:facet name="header">
															<h:outputText value="Indent No"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText value="#{list.indentNo}">
														</h:outputText>
													</rich:column>

													<rich:column sortBy="#{list.indentDate}" filterBy="#{list.indentDate}">
														<f:facet name="header">
															<h:outputText value="Indent Date"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText value="#{list.indentDate}">
														</h:outputText>
													</rich:column>
												
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Brand Name"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText value="#{list.brandName}">
														</h:outputText>
													</rich:column>


													<rich:column>
														<f:facet name="header">
															<h:outputText value="Size"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText value="#{list.packageSize}">
														</h:outputText>
													</rich:column>










													<rich:column>
														<f:facet name="header">
															<h:outputText value="No of cases"
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:outputText 
														value="#{list.dispatchboxIndent}" 
															>
															
														</h:outputText>
													</rich:column>








													<rich:column>
														<f:facet name="header">
															<h:outputText 
																styleClass="generalHeaderOutputTable" />
														</f:facet>
														<h:selectBooleanCheckbox value="#{list.selected}" disabled="#{cLGatepassAfterReturnAction.getstockflg}">
 															 </h:selectBooleanCheckbox>
													</rich:column>


													 
												</rich:dataTable>
										
										<table>
										<tr><td>	<h:commandButton
									styleClass="btn btn-primary" 
									
									actionListener="#{cLGatepassAfterReturnAction.getData}"
									value="Get Stock Data"  ></h:commandButton></td></tr>
										</table>
									
										
											<rich:spacer height="30px"></rich:spacer>
								
								
								<rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table3" width="100%"
									value="#{cLGatepassAfterReturnAction.displaylist}" var="list">

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.vch_brand}"
											style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Size"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.size}"
											style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
										</h:outputText>
									</rich:column>
									
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Indented Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.indentbox}"
											style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
										</h:outputText>
									</rich:column>
									

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.int_bottle_avaliable}"
											style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.boxAvailable}"
											style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.dispatchBoxes}">
											<a4j:support
												reRender="duty,addduty,disbtl,totalduty,totaladdduty,adduty,cesh,totalcesh"
												event="onblur"></a4j:support>
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<a4j:outputPanel id="disbtl">
											<h:outputText value="#{list.int_dispatch}"
												style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
											</h:outputText>
										</a4j:outputPanel>
									</rich:column>
<rich:column>
										<f:facet name="header">
											<h:outputText value="Batch No."
												styleClass="generalHeaderOutputTable" />
										</f:facet>
									 
											<h:inputText value="#{list.batchNo}">
											</h:inputText>
										 
									</rich:column>
									<!-- <rich:column>
										<f:facet name="header">
											<h:outputText value="Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.db_duty}"
											style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Add Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.db_add_duty}"
											style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
										</h:outputText>
									</rich:column>
<rich:column>
										<f:facet name="header">
											<h:outputText value="Special Additional Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.cesh}"
											 >
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Calculated Duty"
												style="white-space: normal" />
										</f:facet>
										<a4j:outputPanel id="duty">
											<h:outputText value="#{list.calculated_duty}"
												style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
												<f:convertNumber maxFractionDigits="2"
													pattern="#############0.00" />
											</h:outputText>
										</a4j:outputPanel>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<a4j:outputPanel id="addduty">
												<h:outputText value="Calculated Additional Duty"
													style="white-space: normal"/>
											</a4j:outputPanel>
										</f:facet>
										<h:outputText value="#{list.calculated_add_duty}" id="adduty"
											style="#{list.tntflg eq 'N'   ? 'COLOR: #4485F5;FONT-WEIGHT: bold;font-size: 8pt; ' : 'COLOR: #000000; ' }">
											<f:convertNumber maxFractionDigits="2"
												pattern="#############0.00" />
										</h:outputText>
									</rich:column>
<rich:column>
										<f:facet name="header">
											<h:outputText value="Calculated Special Additional Duty"
												style="white-space: normal" />
										</f:facet>
										<h:outputText value="#{list.cesh_tot}" id="cesh"
											>
											<f:convertNumber maxFractionDigits="2"
												pattern="#############0.00" />
										</h:outputText>
									</rich:column> -->
									<f:facet name="footer">

									</f:facet>
								</rich:dataTable>
						<tr align="right">
							<td>
								<table>
									<tr>


										
									</tr>
									<tr></tr>
								</table>
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><h:commandButton
									styleClass="btn btn-primary" reRender="shPopupA4j"
									rendered="#{!cLGatepassAfterReturnAction.saveflg}"
									actionListener="#{cLGatepassAfterReturnAction.saveconfirmation}"
									value="Save" onclick="on()"></h:commandButton> <rich:spacer
									width="10px"></rich:spacer> <h:commandButton
									styleClass="btn btn-warning"
									action="#{cLGatepassAfterReturnAction.clearAll}" value="Reset"></h:commandButton>

							</td>
						</tr>


						</TD>
						</TR>

						<tr>
							<td><rich:dataTable align="center" id="table55" rows="10"
									width="100%" var="list11"
									value="#{cLGatepassAfterReturnAction.getListWholesale}"
									headerClass="TableHead" footerClass="TableHead"
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

									<rich:column sortBy="#{list11.dt_date}">
										<f:facet name="header">
											<h:outputLabel value="Date Created" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.dt_date}" />
										</center>
									</rich:column>

									<rich:column sortBy="#{list11.vch_to}">
										<f:facet name="header">
											<h:outputLabel value="License Type" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_to}" />
										</center>
									</rich:column>

									<rich:column sortBy="#{list11.vch_to_lic_no}"
										filterBy="#{list11.vch_to_lic_no}">
										<f:facet name="header">
											<h:outputLabel value="License No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.vch_to_lic_no}" />
										</center>
									</rich:column>


									<rich:column>
										<f:facet name="header">

										</f:facet>
										<center>
											<h:commandButton styleClass="btn btn-success"
												value="PrintDraft" rendered="#{list11.printDraft}"
												actionListener="#{cLGatepassAfterReturnAction.printDraftreport}"></h:commandButton>

											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{cLGatepassAfterReturnAction.pdfDraftname}"
												target="_blank">
												<h:outputText styleClass="outputText"
													value="View Draft Report"
													rendered="#{list11.draftprintFlag}"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>

											<h:commandButton styleClass="btn btn-success" value="Print"
												rendered="#{list11.finalizePrint}"
												actionListener="#{cLGatepassAfterReturnAction.printreport}"></h:commandButton>

											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{cLGatepassAfterReturnAction.pdfname}"
												target="_blank">
												<h:outputText styleClass="outputText" id="text223"
													value="View Report" rendered="#{list11.printFlag}"
													style="color: blue; font-family: serif; font-size: 12pt"></h:outputText>
											</h:outputLink>
											<h:commandButton styleClass="btn btn-success"
												value="Scan and Upload" rendered="#{list11.printDraft}"
												actionListener="#{cLGatepassAfterReturnAction.scanAndUpload}">
											</h:commandButton>
										</center>
									</rich:column>


									<rich:column>
										<f:facet name="header">

										</f:facet>
										<center>
											<h:commandButton styleClass="btn btn-default"
												rendered="#{list11.printDraft}"
												actionListener="#{cLGatepassAfterReturnAction.cancel_gatepass}"
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
							<td colspan="6" style="color: Green;" align="left"><h:outputText
									rendered="#{cLGatepassAfterReturnAction.gatePassFlag}"
									value="** Please read the instructions carefully before uploading."
									style="color: #ce0000;font-style: italic;font-size: large;text-decoration:blink;" /></td>
						</tr>

						<tr align="left">
							<td style="FONT-WEIGHT: bold; color: Green; width: 348px;"
								align="left"><h:outputText
									rendered="#{cLGatepassAfterReturnAction.gatePassFlag}"
									value="Upload csv for Gatepass Number(Same Pass No. should be entered in the first row of csv.):"
									style="FONT-SIZE: medium;" /></td>
							<td style="FONT-SIZE: large; color: Red"><h:outputText
									rendered="#{cLGatepassAfterReturnAction.gatePassFlag}"
									value="#{cLGatepassAfterReturnAction.scanGatePassNo}"
									style="COLOR: #0000ff;" /></td>
							<TD><h:outputLink value="/doc/ExciseUp/ExcelFormat.pdf"
									target="_blank">
									<h:graphicImage value="/img/i.png" style="width:40px;"
										rendered="#{cLGatepassAfterReturnAction.gatePassFlag}"></h:graphicImage>
								</h:outputLink></TD>



							<td><rich:fileUpload addControlLabel="Add File" id="link34"
									fileUploadListener="#{cLGatepassAfterReturnAction.uploadCsv}"
									rendered="#{cLGatepassAfterReturnAction.gatePassFlag}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload CSV"
									styleClass="btn btn-primary"
									rendered="#{cLGatepassAfterReturnAction.gatePassFlag}"
									action="#{cLGatepassAfterReturnAction.csvSubmit }">
								</h:commandButton></td>

						</tr>
					</table>
					<div class="row">
						<rich:spacer height="20px"></rich:spacer>
					</div>
					<div>
						<rich:dataTable align="center" id="table57" rows="10" width="100%"
							var="listk" rendered="#{cLGatepassAfterReturnAction.tableFlag}"
							value="#{cLGatepassAfterReturnAction.getVal }" headerClass="TableHead"
							footerClass="TableHead" rowClasses="TableRow1,TableRow2">

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="GatePass.No" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.gatepass}" />
								</center>
							</rich:column>

							<rich:column>
								<f:facet name="header">
									<h:outputLabel value="CaseCode" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.casecode}" />
								</center>
							</rich:column>

							<rich:column filterBy="#{listk.cascodeMatching}"
								sortBy="#{listk.cascodeMatching}">
								<f:facet name="header">
									<h:outputLabel value="Status" />
								</f:facet>
								<center>
									<h:outputText value="#{listk.cascodeMatching}" />
								</center>
							</rich:column>



							<rich:column>
								<f:facet name="header">

								</f:facet>
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
									rendered="#{cLGatepassAfterReturnAction.tableFlag}"
									disabled="#{cLGatepassAfterReturnAction.bottlingNotDoneFlag}"
									action="#{cLGatepassAfterReturnAction.finalSubmit}">

								</h:commandButton> <h:commandButton value="Cancel" styleClass="btn btn-danger"
									rendered="#{cLGatepassAfterReturnAction.tableFlag}"
									action="#{cLGatepassAfterReturnAction.delete}">
								</h:commandButton></td>

						</tr>
					</table>



				</div>
			</div>
		</h:form>
		<h:form>




			<a4j:outputPanel id="shPopupA4j">
				<h:panelGroup id="sh-addPopup"
					rendered="#{cLGatepassAfterReturnAction.saveflg}"
					style="#{cLGatepassAfterReturnAction.savecss}">


					<TABLE width="100%" align="center">

						<TR>
							<TD align="center"><h:outputLabel
									rendered="#{cLGatepassAfterReturnAction.saveflg}"
									value="Please verify that the gatepass details entered below are correct and complete."
									style="COLOR: #FFFFFF; FONT-SIZE: x-large;"></h:outputLabel> <rich:spacer
									width="30px"></rich:spacer> <h:commandButton
									styleClass="btn btn-success"
									rendered="#{cLGatepassAfterReturnAction.saveflg}"
									action="#{cLGatepassAfterReturnAction.saveMethod}" value="Confirm Details"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true;off()"></h:commandButton>
								<rich:spacer width="30px"></rich:spacer> <h:commandButton
									styleClass="btn btn-danger"
									rendered="#{cLGatepassAfterReturnAction.saveflg}"
									action="#{cLGatepassAfterReturnAction.cancle}" value="Close"
									onclick="off()"></h:commandButton></TD>
						</TR>

					</TABLE>



				</h:panelGroup>

				<div id="sh-overlay" style="#{cLGatepassAfterReturnAction.shOverlayStyle}"></div>
			</a4j:outputPanel>

		</h:form>
	</f:view>
	<script>
		function on() {
			document.getElementById("overlay").style.display = "block";
		}

		function off() {
			document.getElementById("overlay").style.display = "none";
			document.getElementById("sh-overlay").style.display = "none";
			document.getElementById("sh-addPopup").style.display = "none";
		}
	</script>
</ui:composition>