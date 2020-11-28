 <ui:composition
       xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">

 <f:view>
   <h:form>
   <rich:spacer  height="20"></rich:spacer>
   <h:messages errorStyle="color:red" layout="table"
														id="messages" infoStyle="color:green">
													</h:messages>
       <rich:spacer  height="30"></rich:spacer>
       <h:inputHidden value="#{uploadShippingAndLadingBillAction.hidden}" />
                            <div align="center"><h:outputText
										value="Marking Seal For Part Container"
										 
										style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
										</div>
							 <rich:spacer  height="30"></rich:spacer>
							 <rich:separator lineType="dashed"></rich:separator>
							 <rich:spacer  height="30"></rich:spacer>
							 
							 <div align="center" style="FONT-SIZE: large; FONT-WEIGHT: bold;">
							   <h:outputText value="#{uploadShippingAndLadingBillAction.distillery_name}"></h:outputText>
							 </div>	
							 <rich:spacer  height="30"></rich:spacer>
									<rich:separator lineType="dashed"></rich:separator>	
		<h:panelGroup rendered="#{!uploadShippingAndLadingBillAction.view_flag}">						
			 <rich:spacer  height="30"></rich:spacer>
       <div align="center">
         <h:selectOneRadio value="#{uploadShippingAndLadingBillAction.sealradio}" 
          valueChangeListener="#{uploadShippingAndLadingBillAction.radioListener}"
         onchange="this.form.submit();">
          <f:selectItem itemLabel="Pending" itemValue="P"/>
           <f:selectItem itemLabel="Marked Seal" itemValue="M"/> 
         </h:selectOneRadio>
       </div>
        <rich:spacer  height="20"></rich:spacer>
       <div>
       <rich:dataTable align="center" id="table55" rows="20"
									width="100%" var="list"
									value="#{uploadShippingAndLadingBillAction.gatepass_list}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list.sno}" />
										</center>
									</rich:column>


									<rich:column sortBy="#{list.vch_gatepass_no}"
										filterBy="#{list.vch_gatepass_no}">
										<f:facet name="header">
											<h:outputLabel value="Gate Pass No" />
										</f:facet>
										<center>
											<h:outputText value="#{list.vch_gatepass_no}" />
										</center>
									</rich:column>
									
									<rich:column sortBy="#{list.dt_date}">
										<f:facet name="header">
											<h:outputLabel value="Date " />
										</f:facet>
										<center>
											<h:outputText value="#{list.dt_date}" />
										</center>
									</rich:column>

									
									
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Boxes" />
										</f:facet>
										<center>
											<h:outputText
												value="#{list.total_dis_box}" />
										</center>
									</rich:column>

                                      <rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Bottles" />
										</f:facet>
										<center>
											<h:outputText
												value="#{list.total_dis_bottles}" />
										</center>
									</rich:column>
									<rich:column rendered="#{uploadShippingAndLadingBillAction.radio eq 'P'}">
										<f:facet name="header">
											<h:outputLabel value="Container No." />
										</f:facet>
										<center>
										<h:outputText
												value="#{list.container_no}" />
										</center>
									</rich:column>
									<rich:column rendered="#{uploadShippingAndLadingBillAction.sealradio eq 'M'}">
										<f:facet name="header">
											<h:outputLabel value="Seal No." />
										</f:facet>
										<center>
										<h:outputText
												value="#{list.seal_no}" />
											
										</center>
									</rich:column>
									<rich:column rendered="#{uploadShippingAndLadingBillAction.sealradio eq 'M' }">
										<f:facet name="header">
											<h:outputLabel value="Seal Dt" />
										</f:facet>
										<center>
										<h:outputText
												value="#{list.seal_dt}" />
										</center>
									</rich:column>
									
									

									<rich:column rendered="#{uploadShippingAndLadingBillAction.sealradio eq 'P' }">
										<f:facet name="header">
											<h:outputLabel value="Action" />
										</f:facet>
										<center>
											 
												<h:commandButton styleClass="btn btn-primary btn-sm"
												actionListener="#{uploadShippingAndLadingBillAction.view}"
												value="Mark Seal" />
										</center>
									</rich:column>


									<f:facet name="footer">
										<rich:datascroller for="table55" />
									</f:facet>
								</rich:dataTable>
       </div>
       </h:panelGroup>	
       <div class="row">
						<rich:spacer height="20"></rich:spacer>
						</div>
						
						
       <h:panelGroup rendered="#{uploadShippingAndLadingBillAction.view_flag}">
       <div>
						 <h:outputText value="Brand Details :-" style="COLOR: #0091d7; FONT-STYLE: italic; TEXT-DECORATION: underline; FONT-WEIGHT: bold;"></h:outputText>
						</div>
						  <div class="row">
						<rich:spacer height="10"></rich:spacer>
						</div>
       <div>
       <rich:dataTable align="center" id="table5" rows="20"
									width="100%" var="list11"
									value="#{uploadShippingAndLadingBillAction.brand_list}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list11.srno}" />
										</center>
									</rich:column>


									<rich:column sortBy="#{list11.vch_gatepass_no}"
										filterBy="#{list11.vch_gatepass_no}">
										<f:facet name="header">
											<h:outputLabel value="Brand Name" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.brand_name}" />
										</center>
									</rich:column>
									
									<rich:column sortBy="#{list11.dt_date}">
										<f:facet name="header">
											<h:outputLabel value="Package " />
										</f:facet>
										<center>
											<h:outputText value="#{list11.package_name}" />
										</center>
									</rich:column>

									
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Boxes" />
										</f:facet>
										<center>
											<h:outputText value="#{list11.dispatch_box}" />
										</center>
									</rich:column>

									

                                      <rich:column>
										<f:facet name="header">
											<h:outputLabel value="Dispatched Bottles" />
										</f:facet>
										<center>
											<h:outputText
												value="#{list11.dispatch_bottle}" />
										</center>
									</rich:column>
									
									

									

									<f:facet name="footer">
										<rich:datascroller for="table5" />
									</f:facet>
								</rich:dataTable>
       </div>
       <div class="row">
       <rich:spacer  height="20"></rich:spacer>
       </div>
       <div class="col-md-12" align="center">
      
       <div class="col-md-3" align="right">
        <h:outputText value="Gatepass No. :" style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
							<h:outputText value="#{uploadShippingAndLadingBillAction.gatepass_no}"></h:outputText>
						</div>
						
						<div class="col-md-3" align="right">
						<h:outputText value="Gatepass Date" style="FONT-WEIGHT: bold;"></h:outputText>
						</div>
						<div class="col-md-3" align="left">
						<rich:calendar value="#{uploadShippingAndLadingBillAction.gatepass_date}" disabled="true"></rich:calendar>
						</div>
						</div>
        
		
					<div class="col-md-12" align="center">
      
       <div class="col-md-3" align="right">
        <h:outputText value="Seal No. :" style="FONT-WEIGHT: bold;" rendered="#{uploadShippingAndLadingBillAction.sealradio eq 'P' }"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
							<h:inputText value="#{uploadShippingAndLadingBillAction.seal_no}" rendered="#{uploadShippingAndLadingBillAction.sealradio eq 'P' }"></h:inputText>
						</div>
						
						<div class="col-md-3" align="right">
						<h:outputText value="Seal Date" style="FONT-WEIGHT: bold;" rendered="#{uploadShippingAndLadingBillAction.sealradio eq 'P' }"></h:outputText>
						</div>
						<div class="col-md-3" align="left">
						<rich:calendar value="#{uploadShippingAndLadingBillAction.seal_dt}"  rendered="#{uploadShippingAndLadingBillAction.sealradio eq 'P' }"></rich:calendar>
						</div>
						</div>	
							
						
						
						<div class="row">
						<rich:spacer height="10"></rich:spacer>
						</div>
						<div align="center">
						 <h:commandButton action="#{uploadShippingAndLadingBillAction.submit1}" 
						 value="Submit" styleClass="btn btn-success btn-sm" 
						 rendered="#{uploadShippingAndLadingBillAction.sealradio eq 'P'}"/>
						 <rich:spacer width="5"></rich:spacer>
						 <h:commandButton action="#{uploadShippingAndLadingBillAction.back}" value="Back" styleClass="btn btn-warning btn-sm" />
						</div>
						</h:panelGroup>
   </h:form>
 
 </f:view>
</ui:composition>