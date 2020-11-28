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
														id="messages" infoStyle="color:red">
													</h:messages>
												</h3></TD>
										</TR>
									</TBODY>
								</TABLE>
							</TD>
						</TR>
						<TR align="center" style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
							<TD><h:inputHidden
									value="#{imflOldStockFl11Action.hidden}"></h:inputHidden>
								<h5>
									<h:outputText
										value="Stock Verification"
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
										<td colspan="4" align="center">
											<table width="90%" align="center">

											</table>
										</td>
									</tr>
									 
									 
									<tr align="center">
										<td align="center">
											<table align="center" width="100%">

												<tr>
													<td align="center"><h:selectOneRadio
																value="#{imflOldStockFl11Action.vch_from}"
																onchange="this.form.submit()"
																valueChangeListener="#{imflOldStockFl11Action.fromListMethod}">
																<f:selectItem itemValue="2019" itemLabel="2019-20" />
																<f:selectItem itemValue="2020" itemLabel="2020-21" />

															</h:selectOneRadio></td>
												</tr>
											</table>  
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
									id="table3" rows="10" width="100%"
									value="#{imflOldStockFl11Action.displaylist}"
									var="list">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sno" styleClass="generalHeaderStyle" />
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.slno}" />
										</center>
									</rich:column>
<rich:column>
										<f:facet name="header">
											<h:outputText value="Licence Type"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.db_duty}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Licence No."
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.db_add_duty}">
										</h:outputText>
									</rich:column>
									 

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.vch_brand}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Size"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.size}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Bottle"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.int_bottle_avaliable}">
										</h:outputText>
									</rich:column>

									<rich:column>
										<f:facet name="header">
											<h:outputText value="Available Box"
												styleClass="generalHeaderOutputTable" />
										</f:facet>
										<h:outputText value="#{list.boxAvailable}">
										</h:outputText>
									</rich:column>

									

									<f:facet name="footer">
										<rich:datascroller for="table3"></rich:datascroller>
									</f:facet>
								</rich:dataTable>
						</TD></TR>						<tr>
							<td style="text-align: center;">
								<div>
									<div
										style="width: 150px; margin-left: auto; margin-right: auto;">
										<h:commandButton styleClass="btn btn-primary"
											action="#{imflOldStockFl11Action.saveMethod}"
											value="Verify and Finalise " ></h:commandButton>
										 

										<rich:spacer width="10px"></rich:spacer>



									</div>
								</div>
							</td>
						</tr>
						 
						 
					</TABLE>


					
					

					
				</div>
			</div>
		</h:form>
	</f:view>
</ui:composition>