
<%@ page import="cooking_world.Notes" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'notes.label', default: 'Notes')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-notes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-notes" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list notes">
			
				<g:if test="${notesInstance?.clarte}">
				<li class="fieldcontain">
					<span id="clarte-label" class="property-label"><g:message code="notes.clarte.label" default="Clarte" /></span>
					
						<span class="property-value" aria-labelledby="clarte-label"><g:fieldValue bean="${notesInstance}" field="clarte"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${notesInstance?.gout}">
				<li class="fieldcontain">
					<span id="gout-label" class="property-label"><g:message code="notes.gout.label" default="Gout" /></span>
					
						<span class="property-value" aria-labelledby="gout-label"><g:fieldValue bean="${notesInstance}" field="gout"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${notesInstance?.difficulte}">
				<li class="fieldcontain">
					<span id="difficulte-label" class="property-label"><g:message code="notes.difficulte.label" default="Difficulte" /></span>
					
						<span class="property-value" aria-labelledby="difficulte-label"><g:fieldValue bean="${notesInstance}" field="difficulte"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:notesInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${notesInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
