<%@ page import="cooking_world.Recette" %>
<%@ page import="cooking_world.Utilisateur" %>
<%@ page import="java.text.DecimalFormat; cooking_world.Recette" %>
<%@ page import="cooking_world.Notes" %>

<div class="vignette" style="min-height: 100px;">
    <div style="float:left;padding-left: 10px;display: table-cell">
        <div style="float:left;">
            <g:link style="color : #0E10F0;" controller="utilisateur"
            action="show"
            id="${it.utilisateur.id}">${it.utilisateur}
            </g:link>
        </div>
        <div style="padding-left: 5px;float:right;">
            <p style="font-size: 11px;">le <g:formatDate format="dd/MM/yyyy" date="${it.dateCom}"/></p>
        </div>
    </div>
    <div style="padding-left: 20px">
        <br/><br/>
        <p style="display:table;text-align:left;padding-top: 5px">${it.message}</p>
    </div>
</div>

