<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>
	

	

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
						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD align="center"></TD>
						</TR>
						<TR>
							<TD align="center" width="100%">
								<TABLE align="center" width="100%">
									<TBODY>
										<TR align="center">

											<TD align="center" width="100%" class="pghead"><h:inputHidden
													value="#{receivefromPlantGinAction.hidden}"></h:inputHidden>
												<h2>
													<h:outputText value=" Received From Plant "
													style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;">
													</h:outputText>
												</h2></TD>
										</TR>
										
										<tr>
										<td><rich:separator lineType="dashed"></rich:separator></td>
										</tr>
										
										<tr>
										<TD><rich:spacer height="30px"></rich:spacer></TD>
									</tr>
										
										<tr>
										<TD align="center" colspan="2"><h:outputLabel
												value="#{receivefromPlantGinAction.distillery_nm}"
												style="COLOR: #000000; FONT-SIZE: x-large;"></h:outputLabel></TD>
												</tr>
										<tr>
										<TD><rich:spacer height="5px"></rich:spacer></TD>
									</tr>
									
										<TR>
										<TD align="center" colspan="2"><h:outputLabel
												value="#{receivefromPlantGinAction.distillery_adrs}"
												style="COLOR: #000000; FONT-SIZE: medium;">
												</h:outputLabel></TD>
												</TR>
										
										<tr>
										<TD><rich:spacer height="10px"></rich:spacer></TD>
									</tr>
										
										<TR align="center"
										style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
										<TD><rich:separator lineType="dashed"></rich:separator></TD>
									</TR>
										
										<tr>
										<TD><h:outputText style="COLOR: green; FONT-SIZE: large;" value="** Kindly update your Vat Type from Vat Entry screen given inside Distillery Information Menu."></h:outputText></TD>
									</tr>
										
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
						<TR>
							<TD colspan="1" align="center">
								<div class="panel panel-default">
									<div class="panel-body">
									
									
									
										<TABLE width="80%">
											<TBODY>

												
												
												<tr height="15px"></tr>
												
												
												<TR>


													<TD width="150px"><h5>
															<h:outputText value="*Plant Total  (BL)"></h:outputText>
														</h5></TD>
													<TD>
															<h:inputText styleClass="form-control"  
																value="#{receivefromPlantGinAction.plant_Total_Balance_BL}" disabled="true">
																 <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
																</h:inputText>
																
																<!-- <h:inputHidden styleClass="form-control"  
																value="#{reDistillationOfSpiritAction.strength}">
																</h:inputHidden> -->
														</TD>


													<TD width="150px"><h5>
															<h:outputText value="* Plant Total  (AL)"></h:outputText>
														</h5></TD>
													<TD><h:inputText styleClass="form-control"
															value="#{receivefromPlantGinAction.plant_Total_balance_AL}" disabled="true">
															 <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
															</h:inputText></TD>

												</TR>
												
												
												
												<tr height="15px"></tr>
												
												
												<TR>


													<TD width="150px"><h5>
															<h:outputText value="*Received From Plant (BL)"></h:outputText>
														</h5></TD>
													<TD>
															<h:inputText styleClass="form-control"  
																value="#{receivefromPlantGinAction.received_Frm_Pant_Balance_BL}" disabled="false">
																 <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
																</h:inputText>
																
																
														</TD>


													<TD width="150px"><h5>
															<h:outputText value="* Received From Plant (AL)"></h:outputText>
														</h5></TD>
													<TD><h:inputText styleClass="form-control"
															value="#{receivefromPlantGinAction.received_Frm_Pant_balance_AL}" disabled="false">
															 <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
															</h:inputText></TD>

												</TR>
												
												<tr height="10px"></tr>
												
												
												<TR>
													<TD width="150px" colspan="5" align="center"><h5>
															<h:outputText value="  Quantity Transfer To GIN Receiver Vat  " 
															style="FONT-STYLE: italic; COLOR: #00; FONT-WEIGHT: bold; FONT-SIZE: large;" ></h:outputText>
														</h5></TD>
															</TR>
												
												
												
												<tr height="10px"></tr>
												
												<TR>

													<TD><h5>
															<h:outputText value="* GIN Receiver Vat"></h:outputText>
														</h5></TD>
													<TD><h:selectOneMenu styleClass="dropdown-menu"
															value="#{receivefromPlantGinAction.vat_id}" onchange="this.form.submit();" 
															 >
															<f:selectItems value="#{receivefromPlantGinAction.vat_list}" />
															 
														</h:selectOneMenu>
															
															</TD>
															
															
															<TD width="150px"><h5>
															<h:outputText value="* Date"></h:outputText>
														</h5></TD>
													<TD><rich:calendar 
															value="#{receivefromPlantGinAction.dtDate}" inputStyle="height:25px"
															datePattern="dd/MM/yyyy" ></rich:calendar></TD>
													
												</TR>
												
												<tr height="15px"></tr>
												
												

												<TR>


													<TD width="150px"><h5>
															<h:outputText value="* Quantity (BL)"></h:outputText>
														</h5></TD>
													<TD>
															<h:inputText   onblur="this.form.submit();" styleClass="form-control"  
																value="#{receivefromPlantGinAction.quantity_Balance_BL}" disabled="false">
																 <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
																<a4j:support event="onkeyup" reRender="wastal,netal,netbl,trn_qty_al">
																</a4j:support>
																
																</h:inputText>
																
																<!-- <h:inputHidden styleClass="form-control"  
																value="#{reDistillationOfSpiritAction.strength}">
																</h:inputHidden> -->
														</TD>


													<TD width="150px"><h5>
															<h:outputText value="* Quantity Strength"></h:outputText>
														</h5></TD>
													<TD><h:inputText   onblur="this.form.submit();" styleClass="form-control"
															value="#{receivefromPlantGinAction.quantity_Strength}" disabled="false">
															 <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
															<a4j:support event="onkeyup" reRender="wastal,netal,netbl,trn_qty_al">
																</a4j:support>
															
															</h:inputText></TD>

												</TR>
												
												
												<tr height="15px"></tr>
												
												
												
												<TR>
													<TD width="150px"><h5>
															<h:outputText value="* Quantity (AL)"></h:outputText>
														</h5></TD>
													<TD>
															<a4j:outputPanel id="trn_qty_al">
													<h:inputText styleClass="form-control"
															value="#{receivefromPlantGinAction.quantity_Balance_AL}" disabled="true">
															
															 <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
															</h:inputText>
															</a4j:outputPanel>
														</TD>


													<TD width="150px"><h5>
															<h:outputText value="* Transfer Quantity (AL)" rendered="false"></h:outputText>
														</h5></TD>
													<TD>
													 <a4j:outputPanel id="trn_qty_al_old">
													<!-- <h:inputText styleClass="form-control"
															value="#{reDistillationOfSpiritAction.transfer_AL}" disabled="true" rendered="false">
															
															 <f:convertNumber maxFractionDigits="2" pattern="#############0.00" />
															</h:inputText> -->
															</a4j:outputPanel>
															</TD>

												</TR>
												
												<tr height="10px"></tr>
												
												
												

											</TBODY>
										</TABLE>
									</div>
								</div>
							</TD>
						</TR>





						<TR>
							<TD></TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>
						<tr align="center">
							<td colspan="4">
								<table width="100%">
									<tr>
										<td width="100%"><div class="panel-footer clearfix">
												<div align="center">
													<a4j:outputPanel id="save_btn">
													<h:commandButton value="Save"
														onclick="if (! confirm('From Vat-#{receivefromPlantGinAction.vat_f} 
														 Recieved(AL)-#{receivefromPlantGinAction.quantity_Balance_AL}, Recieved(BL)-#{receivefromPlantGinAction.quantity_Balance_BL},
														ALERT : The request will be SAVE DATA. Do you wish to continue?') ) 
														 { return false;}; return true;"
														action="#{receivefromPlantGinAction.save}"
														styleClass="btn btn-success btn-sm" style="FONT-WEIGHT: bold;">
														
													</h:commandButton>
												</a4j:outputPanel>

										

													<rich:spacer width="10px"></rich:spacer>
													<h:commandButton class="btn-sm btn-default"
														action="#{receivefromPlantGinAction.reset}" value="Reset"></h:commandButton>
												</div>
											</div></td>

									</tr>
								</table>
							</td>
						</tr>
						<TR>
							<TD>
								<TABLE width="100%">
									<TBODY>
										<TR>
											<TD><rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead"
													rowClasses="TableRow1,TableRow2" styleClass="DataTable"
													id="table2" rows="10" width="100%"
													value="#{receivefromPlantGinAction.showDataList}" var="list" >
													 <rich:column>
														<f:facet name="header">
															<h:outputText value="Date">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.dtDate_Data_Table}"  styleClass="generalHeaderStyleOutput" >
						<f:convertDateTime pattern="dd/MM/yyyy"	timeZone="GMT+5:30" />
														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Received From Plant (BL)">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.received_Frm_Pant_Balance_BL_Data_Table}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value="Received From Plant (AL)" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.received_Frm_Pant_balance_AL_Data_Table}"  styleClass="generalHeaderStyleOutput" >

														</h:outputText>
													</rich:column>
													
													
													<rich:column>
														<f:facet name="header">
															<h:outputText value=" GIN Receiver vat Name" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.vat_Name_Data_Table}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													
													
													<rich:column>
														<f:facet name="header">
															<h:outputText value=" Quantity (BL)" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.quantity_Balance_BL_Data_Table}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>
													<rich:column>
														<f:facet name="header">
															<h:outputText value=" Quantity (AL)" >
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.quantity_Balance_AL_Data_Table}"  styleClass="generalHeaderStyleOutput" >
															 
														</h:outputText>
													</rich:column>

													<f:facet name="footer">
														<rich:datascroller for="table2"></rich:datascroller>
													</f:facet>
												</rich:dataTable></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR>
							<TD></TD>
						</TR>

						<tr>
							<td colspan="4" align="center"></td>
						</tr>
						<tr>
							<td width="100%"><div class="panel-footer clearfix">
									<div align="right"></div>
								</div></td>

						</tr>

					</TABLE>
				</div>
			</div>
		</h:form>

	</f:view>
</ui:composition>
