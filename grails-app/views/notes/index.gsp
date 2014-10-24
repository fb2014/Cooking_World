
<%@ page import="cooking_world.Notes" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'notes.label', default: 'Notes')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-notes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-notes" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="clarte" title="${message(code: 'notes.clarte.label', default: 'Clarte')}" />
					
						<g:sortableColumn property="gout" title="${message(code: 'notes.gout.label', default: 'Gout')}" />
					
						<g:sortableColumn property="difficulte" title="${message(code: 'notes.difficulte.label', default: 'Difficulte')}" />
					
						<th><g:message code="notes.recette.label" default="Recette" /></th>
					
						<th><g:message code="notes.utilisateur.label" default="Utilisateur" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${notesInstanceList}" status="i" var="notesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${notesInstance.id}">${fieldValue(bean: notesInstance, field: "clarte")}</g:link></td>
					
						<td>${fieldValue(bean: notesInstance, field: "gout")}</td>
					
						<td>${fieldValue(bean: notesInstance, field: "difficulte")}</td>
					
						<td>${fieldValue(bean: notesInstance, field: "recette")}</td>
					
						<td>${fieldValue(bean: notesInstance, field: "utilisateur")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${notesInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
