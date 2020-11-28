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
       
                            <div align="center"><h:outputText
										value="Penality For Late Receiving And Bill of Lading"
										
										style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
										</div>
							 <rich:spacer  height="30"></rich:spacer>
							 <rich:separator lineType="dashed"></rich:separator>
							 <rich:spacer  height="30"></rich:spacer>
							 
							
							 <rich:spacer  height="30"></rich:spacer>
									
		<h:panelGroup rendered="#{!penalityForLateRecevingAction.viewflag}">						
			 <rich:spacer  height="30"></rich:spacer>
       <div align="center">
         <h:selectOneRadio value="#{penalityForLateRecevingAction.radio}"
        
         onchange="this.form.submit();">
          <f:selectItem itemLabel="Pending" itemValue="P"/>
           <f:selectItem itemLabel="Approved" itemValue="A"/>
          
         </h:selectOneRadio>
       </div>
       
        <rich:spacer  height="20"></rich:spacer>
       <div>
       <rich:dataTable align="center" id="table55" rows="20"
									width="100%" var="list"
									value="#{penalityForLateRecevingAction.datalist}"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2">

									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="Sr.No." />
										</f:facet>
										<center>
											<h:outputText value="#{list.srno}" />
										</center>
									</rich:column>


									<rich:column sortBy="#{list.gatepass}"
										filterBy="#{list.gatepass}" >
										<f:facet name="header">
											<h:outputLabel value="Gate Pass No" />
										</f:facet>
										<center>
											<h:outputText value="#{list.gatepass}" />
										</center>
									</rich:column>
									
									<rich:column sortBy="#{list.gatepass_date}">
										<f:facet name="header">
											<h:outputLabel value="Date " />
										</f:facet>
										<center>
											<h:outputText value="#{list.gatepass_date}" />
										</center>
									</rich:column>

									
									
									
                                      <rich:column>
										<f:facet name="header">
											<h:outputLabel value="Duty Value" />
										</f:facet>
										<center>
											<h:outputText
												value="#{list.bond_value}" />
										</center>
									</rich:column>
								
                                   <rich:column >
										<f:facet name="header">
											<h:outputLabel value="Bill Of Lading Date " />
										</f:facet>
										<center>
											<h:outputText value="#{list.date_of_lading}" />
										</center>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputLabel value="" />
										</f:facet>
										<center>
											<h:commandButton styleClass="btn btn-primary btn-sm"
												actionListener="#{penalityForLateRecevingAction.view}"
												value="View" />
										</center>
									</rich:column>
                                

									

									<f:facet name="footer">
										<rich:datascroller for="table55" />
									</f:facet>
								</rich:dataTable>
       </div>
       </h:panelGroup>	
       
       <h:panelGroup rendered="#{penalityForLateRecevingAction.viewflag}">
       
       <div class="col-md-12">
       
       <div class="col-md-3" align="right">
          <h:outputText value="Gatepass No." style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
          <h:inputText value="#{penalityForLateRecevingAction.gatepass_no}" readonly="true"
          styleClass="form-control"></h:inputText>
       </div>
        <div class="col-md-3" align="right">
          <h:outputText value="Bill Of Lading Date." style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
          <h:inputText value="#{penalityForLateRecevingAction.laading_date}" readonly="true"
          styleClass="form-control"></h:inputText>
       </div>
       </div>
       <div class="row">
       <rich:spacer height="20"></rich:spacer>
       </div>
        <div class="col-md-12">
       <div class="col-md-3" align="right">
          <h:outputText value="Receiving of duty for bill of lading :" style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
          <h:inputText value="#{penalityForLateRecevingAction.bond_value}" readonly="true"
          styleClass="form-control"></h:inputText>
       </div>
       <div class="col-md-3" align="right">
          <h:outputText value="Penality Value :" style="FONT-WEIGHT: bold;"></h:outputText>
       </div>
       <div class="col-md-3" align="left">
          <h:inputText value="#{penalityForLateRecevingAction.penality}"
          styleClass="form-control"></h:inputText>
       </div>
       
       </div>
       
        <div class="row">
       <rich:spacer height="20"></rich:spacer>
       </div>
       
       <div align="center">
          <h:commandButton value="Forward" action="#{penalityForLateRecevingAction.forward}" style="margin-left: 5px; margin-right: 5px; "
          rendered="#{penalityForLateRecevingAction.btnflg and penalityForLateRecevingAction.radio eq 'P'}" styleClass="btn btn-primary"></h:commandButton>
          <h:commandButton value="Approve" action="#{penalityForLateRecevingAction.approve}" style="margin-left: 5px; margin-right: 5px; "
           rendered="#{!penalityForLateRecevingAction.btnflg and penalityForLateRecevingAction.radio eq 'P'}" styleClass="btn btn-success"></h:commandButton>
          <h:commandButton value="Back" action="#{penalityForLateRecevingAction.back}" style="margin-left: 5px; margin-right: 5px; "
          styleClass="btn btn-danger"></h:commandButton>
       </div>
       </h:panelGroup>
       <div class="row">
       <rich:spacer height="30"></rich:spacer>
       </div>

</h:form>
</f:view>
</ui:composition>