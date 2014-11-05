<%@ page import="cooking_world.Recette" %>
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

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-recette" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="titre" title="${message(code: 'recette.titre.label', default: 'Titre')}"/>

            <g:sortableColumn property="photo" title="${message(code: 'recette.photo.label', default: 'Photo')}"/>

            <g:sortableColumn property="tempsPreparation"
                              title="${message(code: 'recette.tempsPreparation.label', default: 'Temps Preparation')}"/>

            <g:sortableColumn property="tempsCuisson"
                              title="${message(code: 'recette.tempsCuisson.label', default: 'Temps Cuisson')}"/>

            <g:sortableColumn property="ingredients"
                              title="${message(code: 'recette.ingredients.label', default: 'Ingredients')}"/>

            <g:sortableColumn property="description"
                              title="${message(code: 'recette.description.label', default: 'Description')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${recetteInstanceList}" status="i" var="recetteInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${recetteInstance.id}">${fieldValue(bean: recetteInstance, field: "titre")}</g:link></td>

                <!-- afficher la photo -->
                <td> <img class="avatar_small" src="${createLink(controller:'Recette', action:'showPayload', id:"${recetteInstance.id}")}" /></td>
                <!-- fin-->
                <td>${fieldValue(bean: recetteInstance, field: "tempsPreparation")}</td>

                <td>${fieldValue(bean: recetteInstance, field: "tempsCuisson")}</td>

                <td>${fieldValue(bean: recetteInstance, field: "ingredients")}</td>

                <td>${fieldValue(bean: recetteInstance, field: "description")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${recetteInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
