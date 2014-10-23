<%@ page import="cooking_world.Utilisateur" %>



<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'pseudo', 'error')} required">
	<label for="pseudo">
		<g:message code="utilisateur.pseudo.label" default="Pseudo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="pseudo" required="" value="${utilisateurInstance?.pseudo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'motDePasse', 'error')} required">
	<label for="motDePasse">
		<g:message code="utilisateur.motDePasse.label" default="Mot De Passe" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="motDePasse" required="" value="${utilisateurInstance?.motDePasse}"/>

</div>

