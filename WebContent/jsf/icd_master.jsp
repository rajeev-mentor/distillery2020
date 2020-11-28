 <ui:composition
      
    xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
<f:view>
    <h:form>
    <rich:spacer height="20px"></rich:spacer>
     <div class="row " align="center">
						<div class="col-md-2" align="center"></div>
						<div class="col-md-10" align="left"
							style="FONT-SIZE: large; FONT-WEIGHT: bold;">

							<h:messages errorStyle="color:red"
								style="FONT-STYLE: italic; font-size: 26; margin-top: 15px;"
								styleClass="generalExciseStyle" layout="table" id="messages"
								infoStyle="color:green">
							</h:messages>

						</div>
					</div>
					<rich:spacer height="10px"></rich:spacer>
						<div class="col-md-12" align="center"><h:outputText value="#{wholesaleUplodingbyDEOAction.msg}" style="COLOR: #00ff00;"></h:outputText></div>
					
	
    
 <div align="center">
   <div class="rowhead " style="background-color:#d9b3ff; width:1200px"  align = "center" >
					<div align="center" width="100%" class="pghead">
						<h2>
							<h:outputText value="ICD MASTER" style="FONT-FAMILY: 'Gill Sans MT'; FONT-SIZE: xx-large;"/>
						</h2>
					</div>
				</div>
				 </div>
				<rich:spacer height="30px"></rich:spacer>
				<div class="row" >
				<div class="col-md-6" align="right"><b>Name Of ICD:</b></div>
				<div class="col-md-6" align="left"><h:inputText value="#{icd_masterAction.name }"></h:inputText></div>
				</div> 
				<rich:spacer height="30px"></rich:spacer>
				<div class="row" >
				<div class="col-md-6" align="right"><b>Address:</b></div>
				<div class="col-md-6" align="left"><h:inputTextarea value="#{icd_masterAction.address }"></h:inputTextarea></div>
				</div>
				
				<rich:spacer height="30px"></rich:spacer>
				<div class="row" >
				<div class="col-md-6" align="right"><h:selectOneRadio 
				value="#{icd_masterAction.redio}" 
				onchange="this.form.submit()">
				<f:selectItem itemValue="1" itemLabel="State" />
				<f:selectItem itemValue="2" itemLabel="Union Territory"/>
				</h:selectOneRadio></div>
				<div class="col-md-3">
				<h:selectOneMenu value="#{icd_masterAction.state }" 
				rendered="#{icd_masterAction.redio eq '1'}" >
					<f:selectItems value="#{icd_masterAction.state_ind }" />
				</h:selectOneMenu>
				
				 <h:inputText value="#{icd_masterAction.ut }" 
			    rendered="#{icd_masterAction.redio eq '2'}" >
			    </h:inputText>
				</div>
			    
				</div> 
				<rich:spacer height="30px"></rich:spacer>
				<div class="row" align="center">
				<h:commandButton action="#{icd_masterAction.save}" value="Save"  styleClass="btn btn-success"/>
				<rich:spacer width="20px"></rich:spacer>
				<h:commandButton action="#{icd_masterAction.reset}" value="Reset"  styleClass="btn btn-warning"/>
				</div>
				
				
				<rich:spacer height="50px"></rich:spacer>
	
	
		<div class="row" align="center">
		                      <rich:dataTable columnClasses="columnClass1"
													headerClass="TableHead" footerClass="TableHead" id="rentopiccat"
													rowClasses="TableRow1,TableRow2" styleClass="table table-hover" rows="10"
													 width="80%"
													value="#{icd_masterAction.displaylist}" var="list">

                                                   <rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Serial No">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.sno}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>

													</rich:column>
													<rich:column align="center" width="250px">
														<f:facet name="header">
															<h:outputText value="Name of ICD">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.nicd}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>

													</rich:column>
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Address"
																style=" white-space: normal;width : 70px;">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.addressicd}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>
													  </rich:column>


													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="State/UT">
															</h:outputText>
														  </f:facet>
						    
											<h:outputText value="#{list.stateicd}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>
											</rich:column>
													
													<rich:column align="center">
														<f:facet name="header">
															<h:outputText value="Creation Date"
																style=" white-space: normal;width : 70px;">
															</h:outputText>
														</f:facet>
														<h:outputText value="#{list.cdate}"
															styleClass="generalHeaderStyleOutput">
														</h:outputText>
													  </rich:column>
													<f:facet name="footer">
								                    <rich:datascroller for="rentopiccat" />
							                        </f:facet>
													
													
												</rich:dataTable>
												</div> 
										 
												
				
				
				   </h:form>
</f:view>
</ui:composition>