<%@ page import="cooking_world.Utilisateur" %>
<%@ page import="cooking_world.Recette" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'utilisateur.label', default: 'Utilisateur')}"/>
    <title>${utilisateurInstance.pseudo}</title>
</head>

<body>
<a href="#show-utilisateur" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="show-utilisateur" class="content scaffold-show" role="main">
    <h1>Bienvenue ${utilisateurInstance.pseudo}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list utilisateur">

        <h1>Coups de coeur</h1>
        <g:if test="${utilisateurInstance?.coupDeCoeur}">
            <li class="fieldcontain">
                <g:each in="${utilisateurInstance.coupDeCoeur}" var="c">
                    <span class="property-value" aria-labelledby="coupDeCoeur-label"><g:link controller="coupDeCoeur"
                                                                                             action="show"
                                                                                             id="${c.id}">${c?.recette?.titre}</g:link></span>
                </g:each>

            </li>
        </g:if>


        <h1>Recettes postées</h1>
        <g:if test="${utilisateurInstance?.recette}">
            <li class="fieldcontain">
                <g:each in="${utilisateurInstance.recette}" var="r">
                    <span class="property-value" aria-labelledby="recette-label"><g:link controller="recette"
                                                                                         action="show"
                                                                                         id="${r.id}">${r?.titre}</g:link>
                        <g:link
                                controller="recette" class="edit" action="edit" id="${r.id}"><g:message
                                code="default.button.edit.label" default="Edit"/></g:link><g:link controller="Recette" action="delete" id="${r.id}">Delete</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form url="[resource: utilisateurInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${utilisateurInstance}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
