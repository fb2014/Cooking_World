<%@ page import="cooking_world.Notes" %>



<div class="fieldcontain ${hasErrors(bean: notesInstance, field: 'clarte', 'error')} required">
	<label for="clarte">
		<g:message code="notes.clarte.label" default="Clarte" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="clarte" type="number" min="0" max="5" value="${notesInstance.clarte}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: notesInstance, field: 'gout', 'error')} required">
	<label for="gout">
		<g:message code="notes.gout.label" default="Gout" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="gout" type="number" min="0" max="5" value="${notesInstance.gout}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: notesInstance, field: 'simplicite', 'error')} required">
	<label for="simplicite">
		<g:message code="notes.simplicite.label" default="Simplicite" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="simplicite" type="number" min="0" max="5" value="${notesInstance.simplicite}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: notesInstance, field: 'recette', 'error')} required">
	<label for="recette">
		<g:message code="notes.recette.label" default="Recette" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recette" name="recette.id" from="${cooking_world.Recette.list()}" optionKey="id" required="" value="${notesInstance?.recette?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: notesInstance, field: 'utilisateur', 'error')} required">
	<label for="utilisateur">
		<g:message code="notes.utilisateur.label" default="Utilisateur" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="utilisateur" name="utilisateur.id" from="${cooking_world.Utilisateur.list()}" optionKey="id" required="" value="${notesInstance?.utilisateur?.id}" class="many-to-one"/>

</div>

