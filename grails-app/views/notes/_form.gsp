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

<div class="fieldcontain ${hasErrors(bean: notesInstance, field: 'difficulte', 'error')} required">
	<label for="difficulte">
		<g:message code="notes.difficulte.label" default="Difficulte" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="difficulte" type="number" min="0" max="5" value="${notesInstance.difficulte}" required=""/>

</div>

