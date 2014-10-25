
<%@ page import="cooking_world.CoupDeCoeur" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'coupDeCoeur.label', default: 'CoupDeCoeur')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-coupDeCoeur" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-coupDeCoeur" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list coupDeCoeur">
			
				<g:if test="${coupDeCoeurInstance?.utilisateur}">
				<li class="fieldcontain">
					<span id="utilisateur-label" class="property-label"><g:message code="coupDeCoeur.utilisateur.label" default="Utilisateur" /></span>
					
						<span class="property-value" aria-labelledby="utilisateur-label"><g:link controller="utilisateur" action="show" id="${coupDeCoeurInstance?.utilisateur?.id}">${coupDeCoeurInstance?.utilisateur?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${coupDeCoeurInstance?.recette}">
				<li class="fieldcontain">
					<span id="recette-label" class="property-label"><g:message code="coupDeCoeur.recette.label" default="Recette" /></span>
					
						<span class="property-value" aria-labelledby="recette-label"><g:link controller="recette" action="show" id="${coupDeCoeurInstance?.recette?.id}">${coupDeCoeurInstance?.recette?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${coupDeCoeurInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="coupDeCoeur.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${coupDeCoeurInstance?.date}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:coupDeCoeurInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${coupDeCoeurInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
