<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:a4j="http://richfaces.org/a4j"
	xmlns:rich="http://richfaces.org/rich"
	xmlns:h="http://java.sun.com/jsf/html">
<h:form>
       <f:view>
       <head>
       </head>
       <div align="center">
       <h:outputLabel style="FONT-SIZE: xx-large; FONT-FAMILY: 'Bodoni MT Condensed';">Searching Of Registered Brand</h:outputLabel>
       </div>
       <rich:spacer height="10px"></rich:spacer>
       <div class="row" style="margin-left: 10rem;">
       <h:outputLabel value="Brand Search   :   "></h:outputLabel>
      <span class="autocomplete form-control" style="width:300px;">      
            <rich:autocomplete mode="cachedAjax" minChars="2" value="#{brand_autocomplete.brand_name}"
        autocompleteMethod="#{brand_autocomplete.brand_list}" />
       </span>     
       </div>
      
       </f:view>
</h:form>
  
</ui:composition>