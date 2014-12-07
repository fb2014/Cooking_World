<%@ page import="cooking_world.Recette" %>
<%@ page import="cooking_world.Utilisateur" %>
<%@ page import="java.text.DecimalFormat; cooking_world.Recette" %>
<%@ page import="cooking_world.Notes" %>

<div class="vignette"; style="min-height: 0px">

    <div style="float:left;padding-left: 10px;">
        <div style="display:table-cell;">
            <g:link style="display:table-cell; color : #0E10F0;" controller="utilisateur"
                    action="show"
                    id="${it.utilisateur.id}">${it.utilisateur}
            </g:link>

        </div>
        <div>
            <p style="font-size: 11px;"> le <g:formatDate format="dd/MM/yyyy" date="${it.dateCom}"/></p>
        </div>

    </div>

    <div style="padding-left: 30px">
        <br/><br/>
        <p style="display:table;text-align:left;padding-top: 15px">${it.message}</p>
    </div>

</div>

