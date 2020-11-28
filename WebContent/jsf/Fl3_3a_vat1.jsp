 <ui:composition
    xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
     
	
   <h:form>
   	<f:view>
   	 
   	<div class="form-group">
   		
   			<div class="row">
   				<a4j:outputPanel id="msg">
					<div class="row col-md-12 wow shake" style="margin-top: 10px;">
						<h:messages errorStyle="color:red"
							style="font-size: 14px;font-weight: bold"
							styleClass="generalStyle" layout="table"
							infoStyle="color:green">
						</h:messages>	
					</div></a4j:outputPanel>
   			</div>
   			
   			<div class="row " align="center">
					<div align="center" width="100%" class="pghead">
					
					<h:inputHidden value="#{fl3_3a_vat_action.hidden}"></h:inputHidden>
						<h2>
							<h:outputText value="Updation Of Spirit VAT " 
							style="FONT-STYLE: italic; COLOR: #0000a0; FONT-WEIGHT: bold; FONT-SIZE: x-large;" />
						</h2>
					</div>
			</div>
			
			<div class="row">
				<rich:spacer height="20px"></rich:spacer>
			</div>
			
			 <div class="row" align="center"
										style="FONT-SIZE: x-large; FONT-WEIGHT: bold;">
									<rich:separator lineType="dashed"></rich:separator>
									</div>
									
									
									<div>
									<rich:spacer height="20px"></rich:spacer>
									
									</div>
									
   			
   		
			<div class="row">
				<div class="col-md-12">
					<rich:dataTable columnClasses="columnClass1"
									headerClass="TableHead" footerClass="TableHead"
									rowClasses="TableRow1,TableRow2" styleClass="DataTable"
									id="table2"   width="100%" align="center"
									value="#{fl3_3a_vat_action.spiritVatDetails}" var="list" style=" height : 86px;">
									
									<f:facet name="header">
										<h:outputText value="Pending" styleClass="generalHeaderOutputTable"></h:outputText> 
								           			
							<rich:columnGroup>
							<rich:column rowspan="2" style="text-align: center;" >
                                   <h:outputText value="Sr No." /> 
                            </rich:column>
                            
                            
                            
                            
                            <rich:column rowspan="2" style="text-align: center;" >
                                   <h:outputText value="Vat Name" /> 
                            </rich:column>
                            
                             <rich:column rowspan="2" style="text-align: center;" >
                                   <h:outputText value="Vat Type" /> 
                            </rich:column>
                            
                            <rich:column rowspan="2" style="text-align: center;" >
                               <h:outputText value="Capacity" />
                            </rich:column>
                            
                          

							
                            
                            
                        
                            
							<rich:column breakBefore="true">
                                <h:outputText value="BL" />
                            </rich:column>
                            <rich:column>
                                <h:outputText value="Strength " />
                            </rich:column>
                             <rich:column>
                                <h:outputText value="AL " />
                            </rich:column>
                            
                            
                            
                           
                                                             
					      
					         
					      
                          
                             </rich:columnGroup>
                          </f:facet>
                          
                          		<rich:column>
									<h:outputText value="#{list.srNo}"></h:outputText>
								</rich:column>
								
						    	<rich:column>
						    	    <h:outputText value="#{list.tankName}"></h:outputText>
									
								</rich:column>	
								<rich:column>
						    	    <h:outputText value="#{list.vattype}"></h:outputText>
									
								</rich:column>
								<rich:column>
									<h:outputText  value="#{list.capacity}" >
									
															</h:outputText>
								</rich:column>
							
								<rich:column id="total">
									<h:inputText value="#{list.blstock}"  styleClass="form-control"
									 converterMessage="Please Enter Digits Only Otherwise Enter 0(Zero)" >
									 <f:convertNumber maxFractionDigits="2"
															pattern="#############0.00" />
									</h:inputText>
								</rich:column>
								
								<rich:column id="total1">
									<h:inputText value="#{list.strength}"  styleClass="form-control"  converterMessage="Please Enter Digits Only Otherwise Enter 0(Zero)" >
									<a4j:support event="onblur"   reRender="total2,jk"></a4j:support>
<f:convertNumber maxFractionDigits="2"
															pattern="#############0.00" />
									</h:inputText>
									 
								</rich:column >
								<rich:column  >
								<a4j:outputPanel id="jk">
									<h:inputText value="#{list.alstock}"  styleClass="form-control" >
									<f:convertNumber maxFractionDigits="2"
															pattern="#############0.00" />
									</h:inputText></a4j:outputPanel>

								</rich:column>
								
									
					      
					</rich:dataTable></div>
				
					</div>
					<div class="col-md-12">
					</div>
					
						<div class="col-md-12" align="center">
						<h:commandButton styleClass="btn btn-primary"    
																	value="Update "  
																	action="#{fl3_3a_vat_action.save}" />
                     </div>
			
			
   			
   	</div>		
   	
   	</f:view>
   	
   	</h:form>
</ui:composition>