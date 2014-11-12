<%@ page import="cooking_world.Recette" %>
<%@ page import="cooking_world.Utilisateur" %>

<div class="vignette">

    <div style="float:left;padding-left: 10px;">
        <g:link style="display:table; padding-bottom: 10px;color : #787878;" controller="recette"
        action="show"
        id="${it.id}">${it.titre}</g:link>

        <img class="avatar_small" style="display:table;padding-left: 10px;" src="${createLink(controller:'Recette', action:'showPayload', id:"${it.id}")}" />
    </div>
    <div>
        <p style="text-align:center; padding-top: 50px;">${it.description}</p>
    </div>
    <div style="float: right;display:table-cell;">
        <div style="display:table-cell;padding-right: 5px;">
            <h5 align="right">
                <g:if test="${it?.coupDeCoeur}">
                    &nbsp;&nbsp;   ${it.coupDeCoeur.size()}
                    <img align="right" src="${resource(dir: 'images', file: 'heart.png')}" width="13" height="13"  />
                </g:if>
            </h5>
        </div>
        <div style="display:table-cell;padding-right: 25px;">
        <g:link controller="recette" class="edit" action="edit" id="${it.id}"><g:message
        code="default.button.edit.label" default="Edit"/></g:link> <g:link controller="Recette" action="delete" id="${it.id}">Delete</g:link>
        </div>

    </div>
</div>

