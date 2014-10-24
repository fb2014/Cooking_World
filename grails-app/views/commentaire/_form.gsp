<%@ page import="cooking_world.Commentaire" %>



<div class="fieldcontain ${hasErrors(bean: commentaireInstance, field: 'message', 'error')} required">
	<label for="message">
		<g:message code="commentaire.message.label" default="Message" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="message" required="" value="${commentaireInstance?.message}"/>

</div>

