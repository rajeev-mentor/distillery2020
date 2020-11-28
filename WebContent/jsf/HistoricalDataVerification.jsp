 <ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich">
	<f:view>

		<h:form style="background-color:whitesmoke;">
			<head>
<style>
.striped-border {
	border: 1px dashed #000;
	width: 50%;
	margin: auto;
	margin-top: 5%;
	margin-bottom: 5%;
}

.example3 {
	border: 0;
	height: 1px;
	background: #000066;
}
</style>


			</head>
			<div class="form-group">
				<div class="row">
					<a4j:outputPanel id="msg">
						<div class="row col-md-12 wow shake" style="margin-top: 10px;">
							<h:messages errorStyle="color:red" style="font-size: 18px;"
								styleClass="generalExciseStyle" layout="table"
								infoStyle="color:green" />
						</div>
					</a4j:outputPanel>
				</div>
				<div class="row " align="center">
					<rich:spacer height="10px"></rich:spacer>
				</div>
				<div class="row">
<h:panelGroup>
					<div align="center">
						<h:outputText value="Historical Data Verification"
						
							style="COLOR: #000080; FONT-WEIGHT: bold; FONT-SIZE: 35px;font-family:Comic Sans MS;"></h:outputText>
		
						<h:inputHidden
							value="#{historicalDataVerificationAction.hidden}"></h:inputHidden>
			</div>
						<div class="row" align="center">
					<h:selectOneRadio value="#{historicalDataVerificationAction.radio}"
						onchange="this.form.submit();"
						valueChangeListener="#{historicalDataVerificationAction.radioListener1}">
						 
						 
						<f:selectItem itemValue="S" itemLabel="Pending Shipping Bill" />
						<f:selectItem itemValue="B" itemLabel="Pending BRC" />
						<f:selectItem itemValue="V" itemLabel="Verified" />
		
					</h:selectOneRadio>
					</div>
					
					<rich:dataTable columnClasses="columnClass1" headerClass="TableHead"
					rowClasses="TableRow1,TableRow2" styleClass="DataTable" id="table2"
					rows="10" width="100%"
					value="#{historicalDataVerificationAction.showData}" var="list">
				
					<rich:column>
					
								<f:facet name="header">
									<h:outputText value="Sr.No."
										styleClass="generalHeaderOutputTable"
										style=" FONT-WEIGHT: bold;"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.sr_no}" readonly="true">

								</h:outputText>
							</rich:column>


							<rich:column>
								<f:facet name="header">
									<h:outputText value="Gatepass NO."
										styleClass="generalHeaderOutputTable"
										style=" FONT-WEIGHT: bold;"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.getpass_no}" readonly="true"></h:outputText>
							</rich:column>
							

                               <rich:column>
								<f:facet name="header">
									<h:outputText value="Gatepass Date"  styleClass="generalHeaderOutputTable"
									style=" FONT-WEIGHT: bold;">
									</h:outputText>
								
								</f:facet>
								<h:outputText value="#{list.gatepass_dt}" readonly="true">
								</h:outputText>
							</rich:column>
							
					<rich:column>
						<f:facet name="header">
							<h:outputText value="From "
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.from}"
							readonly="true">

						</h:outputText>
					</rich:column>

					<rich:column>
						<f:facet name="header">
							<h:outputText value="To"
								style="FONT-FAMILY: 'Times New Roman'; FONT-WEIGHT: bold; FONT-SIZE: small;">
							</h:outputText>
						</f:facet>
						<h:outputText value="#{list.to}"
							readonly="true">

						</h:outputText>
					</rich:column>
					<rich:column>
					<f:facet name="header">
					
					</f:facet>
					 
					<h:commandButton value="View" styleClass="btn btn-primary" actionListener="#{historicalDataVerificationAction.viewdetail}" >
					 
					 </h:commandButton>
					
					</rich:column>


					<f:facet name="footer">
						<rich:datascroller for="table2" />
					</f:facet>
				</rich:dataTable>
		
				</h:panelGroup>
				</div>
							     
							</div>
					

					<h:panelGroup
						rendered="#{historicalDataVerificationAction.viewflg eq 'T'}">

                <div class="row" align="center">
				
						<div class="col-md-4" align="right">
									<h:outputText value="Gatepass_No:  "
										style="FONT-WEIGHT: bold; font-size: 15px"></h:outputText>
								</div>
								<div class="col-md-4" align="left">
									<h:inputText
										value="#{historicalDataVerificationAction.gatapassno}"
										style="COLOR: #0000ff;" readonly="true"
										styleClass="form-control"></h:inputText>
					</div>
				</div>
          	<rich:spacer height="10px"></rich:spacer>
						
								<rich:dataTable id="table22" rows="10" width="100%"
									value="#{historicalDataVerificationAction.viewdetail1}"
									var="list" headerClass="TableHead" footerClass="TableHead"
									styleClass="DataTable" rowClasses="TableRow1,TableRow2"
									style="FONT-SIZE: small;">

	<rich:column>
								<f:facet name="header">
									<h:outputText value="Sr.No."
										styleClass="generalHeaderOutputTable"
										style=" FONT-WEIGHT: bold;"></h:outputText>
								</f:facet>
								<h:outputText value="#{list.sr_no1}" readonly="true">

								</h:outputText>
							</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Brand">

											</h:outputText>
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.brand}" />
										</center>

									</rich:column>

	<rich:column>
										<f:facet name="header">
											<h:outputText value="Size">

											</h:outputText>
										</f:facet>
										<center>
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.size}" />
										</center>

									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="Bottel" />
										</f:facet>
										<center>
									
												
											<h:outputText styleClass="generalExciseStyle"
												value="#{list.bottel}" />
										
										</center>
									</rich:column>
									<rich:column>
										<f:facet name="header">
											<h:outputText value="cases" />
										</f:facet>
										<center>
												<h:outputText styleClass="generalExciseStyle"
												value="#{list.box}" />
										</center>
									</rich:column>
									</rich:dataTable>

								

					 <rich:spacer height="10px"></rich:spacer>
                         	<div align="left">
						<h:outputText value="*Basic Detail:"
							
							style=" text-shadow: 2px 2px 4px #000000;   FONT-WEIGHT: bold;font-family:Comic Sans MS;"></h:outputText>
				
						
					</div>
					<h:panelGroup rendered="#{historicalDataVerificationAction.radio eq 'S'}">
					<div class="row">
					<div class="row" >
   <div class="col-md-2" align="right">
   <h:outputText value=" Shipping Date"></h:outputText>
   </div>
   <div>
     <div class="col-md-2" align="left">
      <h:inputText value="#{historicalDataVerificationAction.ship_date}"		style="COLOR: #0000ff;" readonly="true"
										styleClass="form-control"></h:inputText>
      </div>
 
   </div>
  
      <div>
      <div class="col-md-2" align="right">
      <h:outputText value="Shipping Number"></h:outputText>
      </div>
      <div class="col-md-2" align="left">
      <h:inputText value="#{historicalDataVerificationAction.ship_number}"	style="COLOR: #0000ff;" readonly="true"
										styleClass="form-control"></h:inputText>
      </div>
 <div class="col-md-2" align="right">
      	<h:outputText value="Shipping Of Bottles">	</h:outputText>
      	</div>
      <div class="col-md-2" align="left">
        <h:inputText value="#{historicalDataVerificationAction.ship_no_of_bottel}"	style="COLOR: #0000ff;" readonly="true"
										styleClass="form-control" ></h:inputText>
      </div>
     </div> 
 </div>

       <rich:spacer height="20px"></rich:spacer>
      <!-- fisrt uploader -->
							<div class="col-md-12 row">
								<div class="col-md-3" align="right">
									<h:outputLabel value="Upload Shipping Copy Pdf Only :"
										style="FONT-STYLE: italic; FONT-WEIGHT:" />

								</div>
										<div class="col-md-2" align="left">

									<h:outputLink  
										value="/doc/ExciseUp/HistoricalExport/pdf/#{historicalDataVerificationAction.recordFile}"
										target="_blank">

										<h:outputText value="Shipping Copy Pdf" 
											style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000ff;"></h:outputText>

									</h:outputLink>

								</div>
							

							

							

							</div>
							
							<!-- end of first uploader  -->
							
							  
      <div class="col-md-3" align="right">
     
      	<h:outputText value="Date Of Receipt Of FLB11 for Which the has Bond has Received Date"	></h:outputText>
      	</div>
<div>
     <div class="col-md-3" align="left">
           <h:inputText value="#{historicalDataVerificationAction.riceipt_date}"	style="COLOR: #0000ff;" readonly="true"
										styleClass="form-control"></h:inputText>
     </div>
     </div>
  </div>
  </h:panelGroup>
     
     <h:panelGroup rendered="#{historicalDataVerificationAction.radio eq 'B'}">
     <div class="row">
     <div>
       <div class="col-md-3" align="right">
      	<h:outputText value="BRC Date">	</h:outputText>
      </div>
      	</div>
   
     <div class="col-md-3" align="left">
      <h:inputText value="#{historicalDataVerificationAction.brc_dt}" 	style="COLOR: #0000ff;" readonly="true"
										styleClass="form-control" ></h:inputText>
    
      </div>
 
    
  <rich:spacer height="30px"></rich:spacer>
     <div class="row"  > 
             <div class="col-md-3" align="right">
      	<h:outputText value="BRC No">	</h:outputText>
 
     </div>
     <div class="col-md-3" align="left">
     <h:inputText value="#{historicalDataVerificationAction.brc_no}"		style="COLOR: #0000ff;" readonly="true"
										styleClass="form-control" ></h:inputText>
     </div>
    
     <div class="col-md-3" align="right">
      	<h:outputText value="BRC No Of Bottles">	</h:outputText>
    </div>
    <div class="col-md-3" align="left">
      <h:inputText value="#{historicalDataVerificationAction.brc_no_bottel}" 		style="COLOR: #0000ff;" readonly="true"
										styleClass="form-control"></h:inputText>
      </div>
      
     </div> 
	              
					<div class="row">
				<rich:spacer height="20px"></rich:spacer>
				</div>
					<!-- Second uploader -->
							<div class="col-md-12 row">
								<div class="col-md-3" align="right">
									<h:outputLabel value="Upload BRC Copy Pdf Only:"
										style="FONT-STYLE: italic; FONT-WEIGHT: " />
<rich:spacer height="20px"></rich:spacer>
								</div>
								<div class="col-md-2" align="left">
								
								<h:outputLink  
										value="/doc/ExciseUp/HistoricalExport/pdf/#{historicalDataVerificationAction.recordFile1}"
										target="_blank">

										<h:outputText value="BRC Copy Pdf" 
											style="TEXT-DECORATION: underline; FONT-STYLE: italic; COLOR: #0000ff;"></h:outputText>

									</h:outputLink>
								</div>
							
								

						

							

							</div>
						</div> </h:panelGroup>

							<!-- end of Second uploader  -->
					
		<div>			
  <rich:spacer height="30px"></rich:spacer>
     <div class="row"  > 
             <div class="col-md-3" align="right">
      	<h:outputText value="Bond">	</h:outputText>
 
     </div>
     <div class="col-md-3" align="left">
     <h:inputText value="#{historicalDataVerificationAction.vch_bond}" disabled="#{historicalDataVerificationAction.radio eq 'V'}"></h:inputText>
     </div>
    
     <div class="col-md-3" align="right">
      	<h:outputText value="Current Balance Value">	</h:outputText>
    </div>
    <div class="col-md-3" align="left">
      <h:inputText value="#{historicalDataVerificationAction.current_balance}" disabled="#{historicalDataVerificationAction.radio eq 'V'}">
      </h:inputText>
      </div>
         <rich:spacer height="20px"></rich:spacer>
     </div> 
        <div class="col-md-3" align="right">
      	<h:outputText value="Total Bond Value Which is in Transit">	</h:outputText>
    </div>
    <div class="col-md-3" align="left">
      <h:inputText value="#{historicalDataVerificationAction.bond_utilization_value}" disabled="#{historicalDataVerificationAction.radio eq 'V'}" >
      </h:inputText>
      </div>
      </div>
     
      <rich:spacer height="40px"></rich:spacer>
      	<div align="center">
      	
        <h:commandButton value="Verify" rendered="#{historicalDataVerificationAction.radio eq 'S' or historicalDataVerificationAction.radio eq 'B' }" styleClass="btn-sm btn-success"
									action="#{historicalDataVerificationAction.save}" style=" height : 30px;"/>
									
									<rich:spacer width="10px"></rich:spacer>
									 <h:commandButton value="Close" styleClass="btn-sm btn-warning"
									action="#{historicalDataVerificationAction.close}" style="COLOR: #0000a0;"/>

</div>

</h:panelGroup>
		</h:form>
	</f:view>

</ui:composition>