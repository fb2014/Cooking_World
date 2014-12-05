<%@ page import="cooking_world.Recette" %>
<%@ page import="cooking_world.Utilisateur" %>
<%@ page import="java.text.DecimalFormat; cooking_world.Recette" %>
<%@ page import="cooking_world.Notes" %>

<div class="vignette">

    <div style="float:left;padding-left: 10px;">
        <div style="display:table-cell;">
            <g:link style="display:table-cell; padding-bottom: 10px;color : #787878;" controller="recette"
                    action="show"
                    id="${it.id}">${it.titre}
            </g:link>
        </div>



        <img class="avatar_small" style="display:table;padding-left: 10px;"
             src="${createLink(controller: 'Recette', action: 'showPayload', id: "${it.id}")}"/>
    </div>

    <div style="padding-left: 210px">
        <p style="display:table;font-size: 12px;">
            <g:if test="${it?.notes}">

                <% def totalSimplicite = 0 %>
                <% DecimalFormat df = new DecimalFormat("0.00"); %><!-- afficher la moyenne de chaque note deux chiffres apres la virgule -->
                <% def totalGout = 0 %>
                <% def totalClarte = 0 %>
                <g:each in="${it.notes}" var="n">
                    <% totalSimplicite = totalSimplicite + n.simplicite %>
                    <% totalGout = totalGout + n.gout %>
                    <% totalClarte = totalClarte + n.clarte %>
                </g:each>
                Clarté : <%=df.format(totalClarte / it.notes.size())%>/5
            &nbsp;&nbsp; Simplicité : <%=df.format(totalSimplicite / it.notes.size())%>/5
            &nbsp;&nbsp; Gout :  <%=df.format(totalGout / it.notes.size())%>/5
            </g:if></p><g:if test="${it?.coupDeCoeur}"><p
            style="float:right; padding-right: 5px; font-size: 12px">${it.coupDeCoeur.size()}<img
                src="${resource(dir: 'images', file: 'heart.png')}" width="13" height="13"/></p></g:if>

        <p style="display:table;text-align:center;padding-top: 15px">${it.description}</p>
        <g:link  controller="recette"
                action="show"
                id="${it.id}"> Lire la suite ...
        </g:link>
    </div>

    <div style="float: right;display:table-cell;">

        <g:if test="${it?.utilisateur == session.utilisateur}">

            <div style="display:table-cell;">
                <g:form url="[resource: it, action: 'delete']" method="DELETE">
                    <fieldset>
                        <g:actionSubmit controller="recette" class="edit" action="edit" id="${it.id}" value="Editer"/>
                        <g:actionSubmit action="delete"
                                        value="Supprimer"
                                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                    </fieldset>
                </g:form>
            </div>
        </g:if>

    </div>
</div>

