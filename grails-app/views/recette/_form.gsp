<%@ page import="cooking_world.Recette" %>



<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'titre', 'error')} required">
	<label for="titre">
		<g:message code="recette.titre.label" default="Titre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="titre" required="" value="${recetteInstance?.titre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="recette.photo.label" default="Photo" />
		
	</label>
	<input type="file" id="photo" name="photo" />

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'tempsPreparation', 'error')} required">
	<label for="tempsPreparation">
		<g:message code="recette.tempsPreparation.label" default="Temps Preparation" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tempsPreparation" type="number" min="0" value="${recetteInstance.tempsPreparation}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'tempsCuisson', 'error')} required">
	<label for="tempsCuisson">
		<g:message code="recette.tempsCuisson.label" default="Temps Cuisson" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tempsCuisson" type="number" min="0" value="${recetteInstance.tempsCuisson}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'ingredients', 'error')} required">
	<label for="ingredients">
		<g:message code="recette.ingredients.label" default="Ingredients" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="ingredients" cols="40" rows="5" required="" value="${recetteInstance?.ingredients}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="recette.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" required="" value="${recetteInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'dateCreation', 'error')} required">
	<label for="dateCreation">
		<g:message code="recette.dateCreation.label" default="Date Creation" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateCreation" precision="day"  value="${recetteInstance?.dateCreation}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'commentaire', 'error')} ">
	<label for="commentaire">
		<g:message code="recette.commentaire.label" default="Commentaire" />
		
	</label>
	<g:select name="commentaire" from="${cooking_world.Commentaire.list()}" multiple="multiple" optionKey="id" size="5" value="${recetteInstance?.commentaire*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'coupDeCoeur', 'error')} ">
	<label for="coupDeCoeur">
		<g:message code="recette.coupDeCoeur.label" default="Coup De Coeur" />
		
	</label>
	<g:select name="coupDeCoeur" from="${cooking_world.CoupDeCoeur.list()}" multiple="multiple" optionKey="id" size="5" value="${recetteInstance?.coupDeCoeur*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="recette.notes.label" default="Notes" />
		
	</label>
	<g:select name="notes" from="${cooking_world.Notes.list()}" multiple="multiple" optionKey="id" size="5" value="${recetteInstance?.notes*.id}" class="many-to-many"/>

</div>

