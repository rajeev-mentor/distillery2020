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
							<TD><h:inputHidden
									value="#{gatepassToWholesale_20_21_active_action.hidden}"></h:inputHidden>
								<h5>
									<h:outputText
										value="GatePass From Distillery To Manaufacture WholeSale 2020-21"
										rendered="#{!gatepassToWholesale_20_21_active_action.saveflg}"
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
												rendered="#{!gatepassToWholesale_20_21_active_action.saveflg}"
												value="#{gatepassToWholesale_20_21_active_action.name}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel>




										</TD>



									</tr>
									<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									<TR>

										<TD align="center" colspan="2"><h:outputLabel
												rendered="#{!gatepassToWholesale_20_21_active_action.saveflg}"
												value="#{gatepassToWholesale_20_21_active_action.address}"
												style="COLOR: #000000; FONT-SIZE: medium;"></h:outputLabel></TD>

									</TR>
									<tr>
										<TD><rich:spacer height="10px"></rich:spacer></TD>
									</tr>
									<TR align="center"
										style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
										<TD><rich:separator lineType="dashed"></rich:separator></TD>
									</TR>
									<tr>
										<TD><rich:spacer height="30px"></rich:spacer></TD>
									</tr>
									<TR>
										<td><table>
												<tr>
													<td></td>
												</tr>
											</table></td>
									</TR>
									<TR>
									</TR>
									<TR>
									</TR>
									<tr>
										<td></td>
										<td></td>
									</tr>
									<TR>
										<TD align="center" width="100%">
											<TABLE align="center" width="100%">
												<TBODY>
													<TR align="center">



													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD></TD>
									</TR>
									<TR>
										<TD></TD>
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
																value="#{gatepassToWholesale_20_21_active_action.dt_date}" />
														</TD>
														<TD colspan="2" align="center">Valid Till : <rich:calendar
																value="#{gatepassToWholesale_20_21_active_action.validtilldt_date}" />
														</TD>
													</tr>
													<tr>
														<TD><rich:spacer height="20px"></rich:spacer></TD>
													</tr>
													<TR></TR>


													<TR></TR>
													<tr align="center">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="FROM"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<TD align="left"><h:selectOneRadio
																value="#{gatepassToWholesale_20_21_active_action.vch_from}"
																onchange="this.form.submit()"
																valueChangeListener="#{gatepassToWholesale_20_21_active_action.fromListMethod}">
																<f:selectItem itemValue="FL3" itemLabel="FL3" />
																<f:selectItem itemValue="FL3A" itemLabel="FL3A" />

															</h:selectOneRadio></TD>
														<td align="right" style="padding-right: 10px;"><h:outputText
																value="License No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</td>
														<td align="left"><h:selectOneMenu
																valueChangeListener="#{gatepassToWholesale_20_21_active_action.listMethod}"
																value="#{gatepassToWholesale_20_21_active_action.vch_from_lic_no}"
																onchange="this.form.submit()"
																disabled="#{gatepassToWholesale_20_21_active_action.lic_disable_flag2}"
																style="width: 250px; height: 28px;"
																>
																<f:selectItems
																	value="#{gatepassToWholesale_20_21_active_action.fromliclist}" />
															</h:selectOneMenu></td>
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
																value="#{gatepassToWholesale_20_21_active_action.vch_to}"
																onchange="this.form.submit()"
																valueChangeListener="#{gatepassToWholesale_20_21_active_action.toListMethod}">
																<f:selectItem itemValue="FL1" itemLabel="FL1"
																	itemDisabled="#{gatepassToWholesale_20_21_active_action.vch_from eq 'FL3A'}" />
																<f:selectItem itemValue="FL1A" itemLabel="FL1A"
																	itemDisabled="#{gatepassToWholesale_20_21_active_action.vch_from eq 'FL3'}" />
																<f:selectItem itemValue="FL2A" itemLabel="FL2A" />
																<f:selectItem itemValue="EXPORT" itemLabel="EXPORT (other state)" />
																 

															</h:selectOneRadio></TD>
														<td align="right" style="padding-right: 10px;"><h:outputText
																rendered="#{gatepassToWholesale_20_21_active_action.licNoflg}"
																value="License No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:outputText
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg}"
																value="Export License No"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</td>
														<td align="left"><h:selectOneMenu
																rendered="#{gatepassToWholesale_20_21_active_action.licNoflg}"
																value="#{gatepassToWholesale_20_21_active_action.vch_to_lic_no}"
																style="width: 250px; height: 28px;"
																disabled="#{gatepassToWholesale_20_21_active_action.lic_disable_flag}"
																valueChangeListener="#{gatepassToWholesale_20_21_active_action.lic_change}"
																onchange="this.form.submit()">
																<f:selectItems
																	value="#{gatepassToWholesale_20_21_active_action.toliclist}" />
															</h:selectOneMenu> <h:inputText
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg}"
																value="#{gatepassToWholesale_20_21_active_action.exportLicenseNumber}"
																styleClass="generalHeaderOutputTable">

															</h:inputText></td>


													</tr>
													<tr align="center">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="" style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>

														<TD align="left"><h:selectOneRadio
																rendered="#{gatepassToWholesale_20_21_active_action.withflag}"
																value="#{gatepassToWholesale_20_21_active_action.vch_bond}">
																<f:selectItem itemValue="1" itemLabel="Without Bond" />
															</h:selectOneRadio> <h:selectOneRadio
																rendered="#{gatepassToWholesale_20_21_active_action.underflag}"
																value="#{gatepassToWholesale_20_21_active_action.undr_bond}">
																<f:selectItem itemValue="2" itemLabel="Under Bond" />
															</h:selectOneRadio></TD>
															<TD align="right" style="padding-right: 10px;">
														</TD>
														<td align="left"></td>
													</tr>

													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr>
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Permit Date :"
																rendered="#{gatepassToWholesale_20_21_active_action.vch_to eq 'FL2A'}"></h:outputText>

														</TD>
														<TD align="left"><rich:calendar
																rendered="#{gatepassToWholesale_20_21_active_action.vch_to eq 'FL2A'}"
																value="#{gatepassToWholesale_20_21_active_action.permitDt}" /></TD>
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Permit Number :"
																rendered="#{gatepassToWholesale_20_21_active_action.vch_to eq 'FL2A'}"></h:outputText>
														</TD>
														<TD align="left"><h:inputText
																rendered="#{gatepassToWholesale_20_21_active_action.vch_to eq 'FL2A'}"
																value="#{gatepassToWholesale_20_21_active_action.permitNmbr}"
																styleClass="generalHeaderOutputTable">

															</h:inputText></TD>
													</tr>
													<tr>
														<TD><rich:spacer height="10px"></rich:spacer></TD>
													</tr>
													<tr align="center">
														<td align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Mode of Transport"
																rendered="#{gatepassToWholesale_20_21_active_action.vch_to eq 'EXPORT'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;">
															</h:outputText></td>
														<td align="left"><h:selectOneRadio
																rendered="#{gatepassToWholesale_20_21_active_action.vch_to eq 'EXPORT'}"
																value="#{gatepassToWholesale_20_21_active_action.transportMode}">
																<f:selectItem itemValue="by_road" itemLabel="By Road" />
																<f:selectItem itemValue="by_rail" itemLabel="By Rail" />
															</h:selectOneRadio></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg}"
																value="Bond Value"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg}"
																value="#{gatepassToWholesale_20_21_active_action.bondValue}"
																styleClass="generalHeaderOutputTable">
															</h:inputText></td>
													</tr>
													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr style="padding-bottom: 10px;">
														<td align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg}"
																value="Name Of Authority"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</td>
														<td align="left"><h:inputText
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg}"
																value="#{gatepassToWholesale_20_21_active_action.authName}"
																styleClass="generalHeaderOutputTable">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Export State"
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg and gatepassToWholesale_20_21_active_action.vch_to eq 'EXPORT'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:outputText value="Export Country"
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg and gatepassToWholesale_20_21_active_action.vch_to eq 'EOI'}"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:selectOneMenu
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg and 
																gatepassToWholesale_20_21_active_action.vch_to eq 'EXPORT'}"
																value="#{gatepassToWholesale_20_21_active_action.districtLic}"
																style="width: 100px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToWholesale_20_21_active_action.districtList}" />
															</h:selectOneMenu> <h:selectOneMenu
																rendered="#{gatepassToWholesale_20_21_active_action.authNmflg and gatepassToWholesale_20_21_active_action.vch_to eq 'EOI'}"
																value="#{gatepassToWholesale_20_21_active_action.countryId}"
																style="width: 100px; height: 28px;">
																<f:selectItems
																	value="#{gatepassToWholesale_20_21_active_action.countryList}" />
															</h:selectOneMenu></td>
													</tr>
													<tr align="center">
														<td style="padding-top: 10px;"></td>
													</tr>
													<tr style="padding-top: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Route Details"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToWholesale_20_21_active_action.routeDtl}"
																styleClass="generalHeaderOutputTable">

															</h:inputTextarea></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle No."
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{gatepassToWholesale_20_21_active_action.vehicleNo}"
																styleClass="generalHeaderOutputTable">

															</h:inputText></td>
													</tr>

													<tr style="padding-bottom: 10px;">
														<TD align="right"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Vehicle Driver Name"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputText
																value="#{gatepassToWholesale_20_21_active_action.vehicleDrvrName}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">

															</h:inputText></td>
														<TD align="right" style="padding-right: 10px;"><h:outputText
																value="Vehicle Agency Name And Address"
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
														</TD>
														<td align="left"><h:inputTextarea
																value="#{gatepassToWholesale_20_21_active_action.vehicleAgencyNmAdrs}"
																styleClass="generalHeaderOutputTable"
																style="width: 250px;">

															</h:inputTextarea></td>
													</tr>
													<tr style="padding-bottom: 10px; padding-top: 10px;">
														<td></td>
														<TD align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Gross Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{gatepassToWholesale_20_21_active_action.grossWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></TD>

														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Tare Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<h:inputText
																value="#{gatepassToWholesale_20_21_active_action.tierWeight}"
																styleClass="generalHeaderOutputTable"
																style="width:250px">
																<a4j:support reRender="net-weight" event="onkeyup"></a4j:support>
															</h:inputText></td>
														<td align="left"
															style="FONT-WEIGHT: bold; padding-right: 10px;"><h:outputText
																value="Net Weight "
																style="FONT-SIZE: small; FONT-WEIGHT: bold;"></h:outputText>
															<a4j:outputPanel id="net-weight">
																<h:inputText
																	value="#{gatepassToWholesale_20_21_active_action.netWeight}"
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
								</TABLE> <rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table3" width="100%"
									value="#{gatepassToWholesale_20_21_active_action.displaylist}" var="list">
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
											<h:outputText value="Type"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.tntflg}" >
										</h:outputText>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.vch_brand}" >
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Size"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.size}" disabled="true" >
										 
										</h:inputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Bottle" style="white-space: normal" />
										</f:facet>
										<h:outputText value="#{list.int_bottle_avaliable}" >
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Box" style="white-space: normal" />
										</f:facet>
										<h:outputText value="#{list.boxAvailable}" id="expbox">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Dispatch Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:inputText value="#{list.dispatchBoxes}">
											<a4j:support
												reRender="duty,addduty,disbtl,totalduty,totaladdduty,expfee,totalexpfee,totalexpfee,cesh,totalcesh"
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
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Duty"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.db_duty}" >
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Add Duty" style="white-space: normal" />
										</f:facet>
										<h:outputText value="#{list.db_add_duty}" >
										</h:outputText>
									</rich:column>
<rich:column>
										<f:facet name="header">
											<h:outputText value="Special Additional Duty" style="white-space: normal"/>
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
										<h:outputText value="#{list.calculated_duty}" id="duty" >
											<f:convertNumber maxFractionDigits="2"
												pattern="#############0.00" />
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Calculated Additional Duty" style="white-space: normal" />
										</f:facet>
										<h:outputText value="#{list.calculated_add_duty}" id="addduty" >
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
									</rich:column>
									<rich:column 	rendered="#{gatepassToWholesale_20_21_active_action.authNmflg and 
																gatepassToWholesale_20_21_active_action.vch_to eq 'EXPORT'}">
										<f:facet name="header">
											<h:outputText value="Export Fee" style="white-space: normal" />
										</f:facet>
										<h:outputText value="#{list.expfee}" id="expfee" >
											<f:convertNumber maxFractionDigits="2"
												pattern="#############0.00" />
										</h:outputText>
									</rich:column>
									

									<f:facet name="footer">

									</f:facet>
								</rich:dataTable>
							</TD>
						</TR>
						<tr align="right">
							<td>
								<table>
									<tr>

										<td align="right">
										<a4j:commandButton
												styleClass="btn btn-danger" id="totalduty"
												actionListener="#{gatepassToWholesale_20_21_active_action.calculateTotalDuty}"
												reRender="totalduty"
												value="Total Duty : #{gatepassToWholesale_20_21_active_action.db_total_value}">
											</a4j:commandButton></td>
										<td align="right"><a4j:commandButton
												styleClass="btn btn-danger" id="totaladdduty"
												actionListener="#{gatepassToWholesale_20_21_active_action.calculateTotalAddDuty}"
												reRender="totalduty"
												value="Total Additional Duty : #{gatepassToWholesale_20_21_active_action.db_total_add_value}">
											</a4j:commandButton>
											</td><td align="right">
											<a4j:commandButton
												styleClass="btn btn-danger" id="totalexpfee"
												actionListener="#{gatepassToWholesale_20_21_active_action.calculateTotalexpfee}"
												reRender="totalexpfee"
												
													rendered="#{gatepassToWholesale_20_21_active_action.authNmflg and 
																gatepassToWholesale_20_21_active_action.vch_to eq 'EXPORT'}"
												
												value="Total Export Fee: #{gatepassToWholesale_20_21_active_action.db_total_value_exp}">
											</a4j:commandButton>
											
											</td><td align="right"><a4j:commandButton disabled="true"
												styleClass="btn btn-danger" id="totalcesh" 
												value="Total Special Additional Duty : #{gatepassToWholesale_20_21_active_action.ceshsum}">
											
											</a4j:commandButton></td>
									</tr>
									<tr></tr>
								</table>
							</td>
						</tr>
						
						<tr>
							<td style="text-align: center;">
								<div>
									<div>
										<h:commandButton styleClass="btn btn-primary"
											reRender="shPopupA4j"
											rendered="#{!gatepassToWholesale_20_21_active_action.saveflg}"
											actionListener="#{gatepassToWholesale_20_21_active_action.saveconfirmation}"
											value="Save" onclick="on()"></h:commandButton>
										<rich:spacer width="10px"></rich:spacer>
										<h:commandButton styleClass="btn btn-warning"
											action="#{gatepassToWholesale_20_21_active_action.clearAll}" value="Reset"></h:commandButton>




									</div>
								</div>
							</td>
						</tr>


						<tr>
							<td>
							<div class="row">
						 Dispatch Date :
							<rich:spacer width="5px"></rich:spacer> <rich:calendar valueChangeListener="#{gatepassToWholesale_20_21_active_action.listMethod1}"
																 onchanged="this.form.submit();"
																value="#{gatepassToWholesale_20_21_active_action.table_dt}" /> 

					</div>
							<rich:dataTable align="center" id="table55" rows="20"
									width="100%" var="list11"
									value="#{gatepassToWholesale_20_21_active_action.getListWholesale}"
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
												actionListener="#{gatepassToWholesale_20_21_active_action.printDraftReport}">
											</h:commandButton>
											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{gatepassToWholesale_20_21_active_action.pdfDraft}"
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
												actionListener="#{gatepassToWholesale_20_21_active_action.printReport}">
											</h:commandButton>

											<h:outputLink styleClass="outputLinkEx"
												value="/doc/ExciseUp/WholeSale/pdf//#{gatepassToWholesale_20_21_active_action.pdfName}"
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
												actionListener="#{gatepassToWholesale_20_21_active_action.scanAndUpload}">
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
												actionListener="#{gatepassToWholesale_20_21_active_action.cancel_gatepass}"
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
									value="#{gatepassToWholesale_20_21_active_action.exclcsv}"
									rendered="#{gatepassToWholesale_20_21_active_action.gatePassFlag}"
									onchange="this.form.submit()">
									 
									<f:selectItem itemValue="C" itemLabel="Upload CSV" />

								</h:selectOneRadio></TD>
						</tr>
						
						<tr>
							<td style="FONT-WEIGHT: bold; color: Green;" align="left"><h:outputText
									value="Upload excel for Gatepass Number::"
									rendered="#{gatepassToWholesale_20_21_active_action.gatePassFlag}"
									style="FONT-SIZE: medium;" /></td>
							<td style="FONT-SIZE: large; color: Red"><h:outputText
									value="#{gatepassToWholesale_20_21_active_action.scanGatePassNo}"
									rendered="#{gatepassToWholesale_20_21_active_action.gatePassFlag}" />
							</td>
							<TD><h:outputLink value="/doc/ExciseUp/ExcelFormat.pdf"
									target="_blank">
									<h:graphicImage value="/img/i.png" style="width:40px;"
										rendered="#{gatepassToWholesale_20_21_active_action.gatePassFlag}"></h:graphicImage>
								</h:outputLink></TD>
							 

							 
								<td><rich:fileUpload addControlLabel="Add File" id="link34"
									fileUploadListener="#{gatepassToWholesale_20_21_active_action.uploadCsv}"
									rendered="#{gatepassToWholesale_20_21_active_action.gatePassFlag and gatepassToWholesale_20_21_active_action.exclcsv eq 'C'}"
									maxFilesQuantity="1" listHeight="40" listWidth="250"
									clearControlLabel="clear" stopControlLabel="Stop"
									ontyperejected="if (!confirm(' ONLY .csv type file ALLOWED !!!')) return false"
									acceptedTypes="csv" clearAllControlLabel="Clear">
								</rich:fileUpload></td>

							<td><h:commandButton value="Upload CSV"
									styleClass="btn btn-primary"
									rendered="#{gatepassToWholesale_20_21_active_action.gatePassFlag and gatepassToWholesale_20_21_active_action.exclcsv eq 'C'}"
									action="#{gatepassToWholesale_20_21_active_action.csvSubmit }">
								</h:commandButton></td>
							<td></td>

							<td></td>
						</tr>
					</table>
					<div>
						<rich:dataTable align="center" id="table57" rows="10" width="100%"
							var="listk" rendered="#{gatepassToWholesale_20_21_active_action.tableFlag}"
							value="#{gatepassToWholesale_20_21_active_action.getVal }"
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
									rendered="#{gatepassToWholesale_20_21_active_action.tableFlag}" disabled="#{gatepassToWholesale_20_21_active_action.bottlingNotDoneFlag }"
									action="#{gatepassToWholesale_20_21_active_action.finalSubmit}">

								</h:commandButton> <h:commandButton value="Cancel" styleClass="btn btn-danger"
									rendered="#{gatepassToWholesale_20_21_active_action.tableFlag}"
									action="#{gatepassToWholesale_20_21_active_action.delete}">
								</h:commandButton></td>

						</tr>
					</table>
				</div>

			</div>
		</h:form>









		<h:form>




			<a4j:outputPanel id="shPopupA4j">
				<h:panelGroup id="sh-addPopup"
					rendered="#{gatepassToWholesale_20_21_active_action.saveflg}"
					style="#{gatepassToWholesale_20_21_active_action.savecss}">


					<TABLE width="100%" align="center">

						<TR>
							<TD align="center"><h:outputLabel
									rendered="#{gatepassToWholesale_20_21_active_action.saveflg}"
									value="Please verify that the gatepass details entered below are correct and complete."
									style="COLOR: #FFFFFF; FONT-SIZE: x-large;"></h:outputLabel> <rich:spacer
									width="30px"></rich:spacer> <h:commandButton
									styleClass="btn btn-success"
									rendered="#{gatepassToWholesale_20_21_active_action.saveflg}"
									action="#{gatepassToWholesale_20_21_active_action.saveMethod}"
									value="Confirm Details"
									onclick="var e=this;setTimeout(function(){e.disabled=true;},0);return true; off()"></h:commandButton>
								<rich:spacer width="30px"></rich:spacer> <h:commandButton
									styleClass="btn btn-danger"
									rendered="#{gatepassToWholesale_20_21_active_action.saveflg}"
									action="#{gatepassToWholesale_20_21_active_action.cancle}" value="Close"
									onclick="off()"></h:commandButton></TD>
						</TR>

					</TABLE>



				</h:panelGroup>

				<div id="sh-overlay"
					style="#{gatepassToWholesale_20_21_active_action.shOverlayStyle}"></div>
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
