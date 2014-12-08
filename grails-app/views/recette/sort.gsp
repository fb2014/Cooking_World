<%@ page import="cooking_world.Recette" %>
<%@ page import="cooking_world.Utilisateur" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'recette.label', default: 'Recette')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-recette" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>
<h1 style="padding-left: 30px;"><g:message code="Top des recettes" args="[entityName]"/></h1>

<div style="display:table-cell;padding-left: 284px">
    <g:form url="[resource: it, action: 'sort']" method="DELETE">
        <fieldset>
            <g:if test="${session.utilisateur != null}"> <g:actionSubmit controller="recette" class="create" value="Créer une recette" action="create" /></g:if>
            <g:select name="tri" from="${['Date', 'Durée', 'Titre']}" />
            <g:actionSubmit controller="recette" class="sort" action="sort" value="Trier"/>
            <g:textField name="tf_titre" placeholder="Recette" />
            <g:actionSubmit controller="recette" class="search" action="search" value="Rechercher"/>
        </fieldset>
    </g:form>
</div>




<div style="padding-left: 660px">

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
</div>

<div id="list-recette" class="content scaffold-list" role="main">


    <tbody>
    <g:each in="${recetteInstanceList}" status="i" var="recetteInstance">

        <g:render template="vignetteRecette" collection="${recetteInstance}" />
    </g:each>
    </tbody>


</div>
</body>
</html>
