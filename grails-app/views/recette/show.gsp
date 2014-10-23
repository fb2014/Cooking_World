
<%@ page import="cooking_world.Recette" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recette.label', default: 'Recette')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-recette" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-recette" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list recette">
			
				<g:if test="${recetteInstance?.titre}">
				<li class="fieldcontain">
					<span id="titre-label" class="property-label"><g:message code="recette.titre.label" default="Titre" /></span>
					
						<span class="property-value" aria-labelledby="titre-label"><g:fieldValue bean="${recetteInstance}" field="titre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recetteInstance?.photo}">
				<li class="fieldcontain">
					<span id="photo-label" class="property-label"><g:message code="recette.photo.label" default="Photo" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recetteInstance?.tempsPreparation}">
				<li class="fieldcontain">
					<span id="tempsPreparation-label" class="property-label"><g:message code="recette.tempsPreparation.label" default="Temps Preparation" /></span>
					
						<span class="property-value" aria-labelledby="tempsPreparation-label"><g:fieldValue bean="${recetteInstance}" field="tempsPreparation"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recetteInstance?.tempsCuisson}">
				<li class="fieldcontain">
					<span id="tempsCuisson-label" class="property-label"><g:message code="recette.tempsCuisson.label" default="Temps Cuisson" /></span>
					
						<span class="property-value" aria-labelledby="tempsCuisson-label"><g:fieldValue bean="${recetteInstance}" field="tempsCuisson"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recetteInstance?.ingredients}">
				<li class="fieldcontain">
					<span id="ingredients-label" class="property-label"><g:message code="recette.ingredients.label" default="Ingredients" /></span>
					
						<span class="property-value" aria-labelledby="ingredients-label"><g:fieldValue bean="${recetteInstance}" field="ingredients"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recetteInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="recette.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${recetteInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recetteInstance?.dateCreation}">
				<li class="fieldcontain">
					<span id="dateCreation-label" class="property-label"><g:message code="recette.dateCreation.label" default="Date Creation" /></span>
					
						<span class="property-value" aria-labelledby="dateCreation-label"><g:formatDate date="${recetteInstance?.dateCreation}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:recetteInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${recetteInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
