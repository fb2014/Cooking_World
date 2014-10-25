<%@ page import="cooking_world.Commentaire" %>



<div class="fieldcontain ${hasErrors(bean: commentaireInstance, field: 'message', 'error')} required">
	<label for="message">
		<g:message code="commentaire.message.label" default="Message" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="message" required="" value="${commentaireInstance?.message}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: commentaireInstance, field: 'recette', 'error')} required">
	<label for="recette">
		<g:message code="commentaire.recette.label" default="Recette" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recette" name="recette.id" from="${cooking_world.Recette.list()}" optionKey="id" required="" value="${commentaireInstance?.recette?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: commentaireInstance, field: 'utilisateur', 'error')} required">
	<label for="utilisateur">
		<g:message code="commentaire.utilisateur.label" default="Utilisateur" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="utilisateur" name="utilisateur.id" from="${cooking_world.Utilisateur.list()}" optionKey="id" required="" value="${commentaireInstance?.utilisateur?.id}" class="many-to-one"/>

</div>

