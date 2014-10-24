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

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'commentaire', 'error')} ">
	<label for="commentaire">
		<g:message code="utilisateur.commentaire.label" default="Commentaire" />
		
	</label>
	<g:select name="commentaire" from="${cooking_world.Commentaire.list()}" multiple="multiple" optionKey="id" size="5" value="${utilisateurInstance?.commentaire*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'coupDeCoeur', 'error')} ">
	<label for="coupDeCoeur">
		<g:message code="utilisateur.coupDeCoeur.label" default="Coup De Coeur" />
		
	</label>
	<g:select name="coupDeCoeur" from="${cooking_world.CoupDeCoeur.list()}" multiple="multiple" optionKey="id" size="5" value="${utilisateurInstance?.coupDeCoeur*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="utilisateur.notes.label" default="Notes" />
		
	</label>
	<g:select name="notes" from="${cooking_world.Notes.list()}" multiple="multiple" optionKey="id" size="5" value="${utilisateurInstance?.notes*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'recette', 'error')} ">
	<label for="recette">
		<g:message code="utilisateur.recette.label" default="Recette" />
		
	</label>
	<g:select name="recette" from="${cooking_world.Recette.list()}" multiple="multiple" optionKey="id" size="5" value="${utilisateurInstance?.recette*.id}" class="many-to-many"/>

</div>

