<%@ page import="cooking_world.Utilisateur" %>



<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'pseudo', 'error')} required">
    <label for="pseudo">
        <g:message code="utilisateur.pseudo.label" default="Pseudo"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="pseudo" required="" value="${utilisateurInstance?.pseudo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'motDePasse', 'error')} required">
    <label for="motDePasse">
        <g:message code="utilisateur.motDePasse.label" default="Mot De Passe"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="motDePasse" required="" value="${utilisateurInstance?.motDePasse}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'commentaire', 'error')} ">
    <label for="commentaire">
        <g:message code="utilisateur.commentaire.label" default="Commentaire"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${utilisateurInstance?.commentaire ?}" var="c">
            <li><g:link controller="commentaire" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="commentaire" action="create"
                    params="['utilisateur.id': utilisateurInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'commentaire.label', default: 'Commentaire')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'coupDeCoeur', 'error')} ">
    <label for="coupDeCoeur">
        <g:message code="utilisateur.coupDeCoeur.label" default="Coup De Coeur"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${utilisateurInstance?.coupDeCoeur ?}" var="c">
            <li><g:link controller="coupDeCoeur" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="coupDeCoeur" action="create"
                    params="['utilisateur.id': utilisateurInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'coupDeCoeur.label', default: 'CoupDeCoeur')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'notes', 'error')} ">
    <label for="notes">
        <g:message code="utilisateur.notes.label" default="Notes"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${utilisateurInstance?.notes ?}" var="n">
            <li><g:link controller="notes" action="show" id="${n.id}">${n?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="notes" action="create"
                    params="['utilisateur.id': utilisateurInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'notes.label', default: 'Notes')])}</g:link>
        </li>
    </ul>

</div>

<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'recette', 'error')} ">
    <label for="recette">
        <g:message code="utilisateur.recette.label" default="Recette"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${utilisateurInstance?.recette ?}" var="r">
            <li><g:link controller="recette" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
        </g:each>
        <li class="add">
            <g:link controller="recette" action="create"
                    params="['utilisateur.id': utilisateurInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'recette.label', default: 'Recette')])}</g:link>
        </li>
    </ul>

</div>

