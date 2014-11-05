<%@ page import="cooking_world.Recette" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'recette.label', default: 'Recette')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-recette" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-recette" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list recette">
     <li class="fieldcontain">
         <h2><g:fieldValue bean="${recetteInstance}" field="titre"/></h2>
         <p>Posté par <g:link controller="utilisateur" action="show" id="${recetteInstance?.utilisateur?.id}">${recetteInstance?.utilisateur?.encodeAsHTML()}</g:link>
         , le <g:formatDate format="dd-MM-yyyy" date="${recetteInstance?.dateCreation}"/>
         </p>
     </li>
    <table><tr><td>
        <!--  afficher la photo -->
         <img align="left" src="${createLink(controller:'Recette', action:'showPayload', id:"${recetteInstance.id}")}" width="350" height="400" />
          <!-- fin  -->



        <li class="fieldcontain">
               <span id="tempsPreparation-label" class="property-label"><g:message
                       code="recette.tempsPreparation.label" default="Préparation"/></span>

               <span class="property-value" aria-labelledby="tempsPreparation-label"><g:fieldValue
                       bean="${recetteInstance}" field="tempsPreparation"/></span>
       </li>

        <li class="fieldcontain">
                <span id="tempsCuisson-label" class="property-label"><g:message code="recette.tempsCuisson.label"
                                                                                default="Cuisson"/></span>

                <span class="property-value" aria-labelledby="tempsCuisson-label"><g:fieldValue
                        bean="${recetteInstance}" field="tempsCuisson"/></span>
        </li>


        <g:if test="${recetteInstance?.ingredients}">
            <li class="fieldcontain">
                <span id="ingredients-label" class="property-label"><g:message code="recette.ingredients.label"
                                                                               default="Ingredients"/></span>

                <span class="property-value" aria-labelledby="ingredients-label"><g:fieldValue bean="${recetteInstance}"
                                                                                               field="ingredients"/></span>

            </li>
        </g:if></td></tr>
        <tr ><td >
            <g:if test="${recetteInstance?.description}">
                <li class="fieldcontain">
                    <span id="description-label" class="property-label"><g:message code="recette.description.label"
                                                                                   default="Description"/></span>

                    <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${recetteInstance}"
                                                                                                   field="description"/></span>

                </li>
            </g:if>
            <g:if test="${recetteInstance?.commentaire}">
                <li class="fieldcontain">
                    <span id="commentaire-label" class="property-label"><g:message code="recette.commentaire.label"
                                                                                   default="Commentaire"/></span>

                    <g:each in="${recetteInstance.commentaire}" var="c">
                        <span class="property-value" aria-labelledby="commentaire-label"><g:link controller="commentaire"
                                                                                                 action="show"
                                                                                                 id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
                    </g:each>

                </li>
            </g:if>

            <g:if test="${recetteInstance?.coupDeCoeur}">
                <li class="fieldcontain">
                    <span id="coupDeCoeur-label" class="property-label"><g:message code="recette.coupDeCoeur.label"
                                                                                   default="Coup De Coeur"/></span>

                    <g:each in="${recetteInstance.coupDeCoeur}" var="c">
                        <span class="property-value" aria-labelledby="coupDeCoeur-label"><g:link controller="coupDeCoeur"
                                                                                                 action="show"
                                                                                                 id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
                    </g:each>

                </li>
            </g:if>


        <g:if test="${recetteInstance?.notes}">
            <li class="fieldcontain">
                <span id="notes-label" class="property-label"><g:message code="recette.notes.label"
                                                                         default="Notes"/></span>

                <g:each in="${recetteInstance.notes}" var="n">
                    <span class="property-value" aria-labelledby="notes-label"><g:link controller="notes" action="show"
                                                                                       id="${n.id}">${n?.encodeAsHTML()}</g:link></span>
                </g:each>

            </li>
        </g:if> </td></tr>
        </table>
    </ol>


    <g:form url="[resource: recetteInstance, action: 'delete']" method="DELETE">
        <fieldset class="buttons">
            <g:link class="edit" action="edit" resource="${recetteInstance}"><g:message code="default.button.edit.label"
                                                                                        default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
