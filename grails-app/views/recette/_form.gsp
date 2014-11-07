<%@ page import="cooking_world.Recette" %>



<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'titre', 'error')} required">
    <label for="titre">
        <g:message code="recette.titre.label" default="Titre"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="titre" required="" value="${recetteInstance?.titre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'photo', 'error')} ">
    <label for="photo">
        <g:message code="recette.photo.label" default="Photo"/>

    </label>

    <input type="file" id="photo" name="photo"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'tempsPreparation', 'error')} required">
    <label for="tempsPreparation">
        <g:message code="recette.tempsPreparation.label" default="Temps Preparation"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="tempsPreparation" type="number" min="0" value="${recetteInstance.tempsPreparation}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'tempsCuisson', 'error')} required">
    <label for="tempsCuisson">
        <g:message code="recette.tempsCuisson.label" default="Temps Cuisson"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="tempsCuisson" type="number" min="0" value="${recetteInstance.tempsCuisson}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'ingredients', 'error')} required">
    <label for="ingredients">
        <g:message code="recette.ingredients.label" default="Ingredients"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textArea name="ingredients" cols="40" rows="5" required="" value="${recetteInstance?.ingredients}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'description', 'error')} required">
    <label for="description">
        <g:message code="recette.description.label" default="Description"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textArea name="description" cols="40" rows="5" required="" value="${recetteInstance?.description}"/>

</div>
%{--<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'dateCreation', 'error')} required">
    <label for="dateCreation">
        <g:message code="recette.dateCreation.label" default="Date Creation"/>
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="dateCreation" precision="day" value="${recetteInstance?.dateCreation}"/>

</div>--}%
%{--
<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'utilisateur', 'error')} required">
    <label for="utilisateur">
        <g:message code="recette.utilisateur.label" default="Utilisateur"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="utilisateur" name="utilisateur.id" from="${cooking_world.Utilisateur.list()}" optionKey="id"
              required="" value="${recetteInstance?.utilisateur?.id}" class="many-to-one"/>

</div>
--}%

%{--
<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'commentaire', 'error')} ">
    <label for="commentaire">
        <g:message code="recette.commentaire.label" default="Commentaire"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${recetteInstance?.commentaire ?}" var="c">
            <li><g:link controller="commentaire" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="commentaire" action="create"
                    params="['recette.id': recetteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'commentaire.label', default: 'Commentaire')])}</g:link>
        </li>
    </ul>

</div>
--}%

%{--<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'coupDeCoeur', 'error')} ">
    <label for="coupDeCoeur">
        <g:message code="recette.coupDeCoeur.label" default="Coup De Coeur"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${recetteInstance?.coupDeCoeur ?}" var="c">
            <li><g:link controller="coupDeCoeur" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="coupDeCoeur" action="create"
                    params="['recette.id': recetteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'coupDeCoeur.label', default: 'CoupDeCoeur')])}</g:link>
        </li>
    </ul>

</div>--}%
%{--
<div class="fieldcontain ${hasErrors(bean: recetteInstance, field: 'notes', 'error')} ">
    <label for="notes">
        <g:message code="recette.notes.label" default="Notes"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${recetteInstance?.notes ?}" var="n">
            <li><g:link controller="notes" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="notes" action="create"
                    params="['recette.id': recetteInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'notes.label', default: 'Notes')])}</g:link>
        </li>
    </ul>

</div>--}%

