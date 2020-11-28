<ui:composition
       xmlns="http://www.w3.org/1999/xhtml"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:a4j="http://richfaces.org/a4j"
	  xmlns:rich="http://richfaces.org/rich">

   
   <f:view>
   <h:form>
   <style>
    .table1 TD{ width:200px;}
    
    .dropdown-menu {
	border-radius: 6px;
	padding: 5px 5px;
	height: 30px;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	
}
    
    
    textArea{ border-radius: 3px;
	border-style: none;
	width: 100%;
	box-shadow: 1px 1px 15px lightsteelblue;
	border: 1px solid #669999;
	height:30px;
     }
     .main{ margin: 20px;
     background-color:#f2f2f2;
     padding:20px;
     border-radius:10px;
     shadow: 5px #888888;
     box-shadow: 5px 4px 10px grey;}
   
   </style>
   
   
   
     <div class="row">
       <rich:spacer height="30"></rich:spacer>
       
     </div>
     
      <h:messages errorStyle="color:red" layout="table"
														id="messages" infoStyle="color:green">
													</h:messages>
       <rich:spacer  height="5"></rich:spacer>
      
                <h:inputHidden value="#{stock_Transfer_Accidental_returnAction.hidden}" />
                            <div align="center" style="background-color:#0000a0 "><h:outputText
										value="Distillery Stock Transfer From - Accidental Return Stock To FL1/FL1A Main Stock"
										
										style="FONT-STYLE: italic; COLOR:#ffffff ; FONT-WEIGHT: bold; FONT-SIZE: x-large;"></h:outputText>
										</div>
							
							
							 
							 
						
							 
							 <rich:spacer height="50px"></rich:spacer>
					<div align="center">
					<rich:dataTable id="table221" rows="15" var="list"
						value="#{stock_Transfer_Accidental_returnAction.update_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2"  style="width:95%;">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.srno}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Brand Name "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.brand_name}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Size "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.size}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Bottle No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.bottle_no}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" FL1/FLA "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.fl1_fla}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Transfer Bottle "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:inputText value="#{list.transfer_bottle}" />
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								
							</f:facet>
							<h:commandButton actionListener="#{stock_Transfer_Accidental_returnAction.update}" value="Transfer" />
							
						</rich:column>
						
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table221"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>
					</div>
					 <rich:spacer height="20px"></rich:spacer>
					 
					 	<div align="center">
					<rich:dataTable id="table22" rows="15" var="list"
						value="#{stock_Transfer_Accidental_returnAction.select_list}" styleClass="table table-hover"
						width="100%" headerClass="TableHead" footerClass="TableHead"
						rowClasses="TableRow1,TableRow2"  style="width:95%;">

						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Sr.No."
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.srno}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Brand Name "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.brand_name}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Size "
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.size}">


							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value=" Date"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.date}">
							</h:outputText>
						</rich:column>
						<rich:column align="center">
							<f:facet name="header">
								<h:outputText value="Bottle Number"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.bottle_no}">
							</h:outputText>
						</rich:column>
						
						<rich:column align="center">
	l						<f:facet name="header">
								<h:outputText value=" Licence"
									
									styleClass="generalHeaderOutputTable"></h:outputText>
							</f:facet>
							<h:outputText value="#{list.licence_no}" />
						</rich:column>
						
						
						
					   <f:facet name="footer">
							<rich:datascroller for="table22"></rich:datascroller>
						</f:facet> 

					</rich:dataTable>
					</div>
   </h:form>
   </f:view>
</ui:composition>