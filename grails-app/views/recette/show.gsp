<%@ page import="java.text.DecimalFormat; cooking_world.Recette" %>
<%@ page import="cooking_world.Notes" %>
<%@ page import="cooking_world.Utilisateur" %>
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
        <!-- afficher le bouton de creation de recette seuleument lorsqu'on est connecté -->
        <% if (session['utilisateur']!=null){ %>
         <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
         <% } %>
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

     <h5 align="right">
        <g:if test="${recetteInstance?.notes}"  >

                   <% def totalSimplicite=0  %>
            <% DecimalFormat df = new DecimalFormat("0.00"); %><!-- afficher la moyenne de chaque note deux chiffres apres la virgule -->
                    <% def totalGout=0  %>
                    <% def totalClarte=0  %>
                    <% def nbNote=Notes.findAllByRecette(recetteInstance).size()  %>
                <g:each in="${Notes.findAllByRecette(recetteInstance)}" var="n" >
                    <% totalSimplicite = totalSimplicite + n.simplicite%>
                    <% totalGout = totalGout + n.gout%>
                    <% totalClarte = totalClarte + n.clarte%>
                </g:each>
            Clarté : <%=df.format(totalClarte/ nbNote)%>/5
            &nbsp;&nbsp; Simplicité : <%=df.format(totalSimplicite/ nbNote) %>/5
            &nbsp;&nbsp; Gout :  <%=df.format(totalGout/ nbNote) %>/5

        </g:if>

        <g:if test="${recetteInstance?.coupDeCoeur}">
            &nbsp;&nbsp;   ${recetteInstance.coupDeCoeur.size()}
            <img align="right" src="${resource(dir: 'images', file: 'heart.png')}" width="13" height="13"  />

        </g:if></h5>
         </p>
     </li>
    <table><tr><td>
        <!--  afficher la photo -->
         <img align="left" src="${createLink(controller:'Recette', action:'showPayload', id:"${recetteInstance.id}")}" width="350" height="400" />
          <!-- fin  -->



        <li class="fieldcontain">
               <span id="tempsPreparation-label" class="property-label"><g:message
                       code="recette.tempsPreparation.label" default="Préparation : "/></span>

               <span class="property-value" aria-labelledby="tempsPreparation-label"><g:fieldValue
                       bean="${recetteInstance}" field="tempsPreparation"/></span>
       </li>

        <li class="fieldcontain">
                <span id="tempsCuisson-label" class="property-label"><g:message code="recette.tempsCuisson.label"
                                                                                default="Cuisson : "/></span>

                <span class="property-value" aria-labelledby="tempsCuisson-label"><g:fieldValue
                        bean="${recetteInstance}" field="tempsCuisson"/></span>
        </li>


        <g:if test="${recetteInstance?.ingredients}">
            <li class="fieldcontain">
                <span id="ingredients-label" class="property-label"><g:message code="recette.ingredients.label"
                                                                               default="Ingredients :"/></span>

                <span class="property-value" aria-labelledby="ingredients-label"><g:fieldValue bean="${recetteInstance}"
                                                                                               field="ingredients"/></span>

            </li>
        </g:if></td></tr>
        <tr ><td >
            <g:if test="${recetteInstance?.description}">
                <li class="fieldcontain">
                    <span id="description-label" class="property-label"><g:message code="recette.description.label"
                                                                                   default="Description : "/></span>

                    <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${recetteInstance}"
                                                                                                   field="description"/></span>

                </li>
            </g:if>
            <!--Noter une recette -->
            <li class="fieldcontain">
                <span  class="property-label"> Noter la recette : </span>
            <g:form url="[resource: recetteInstance, action: 'addNote']" method="post">
            &nbsp;Clarte <g:select id="clarte" name="clarte" from="${0..5}" />
            &nbsp;&nbsp;  Simplicité <g:select id="simplicite" name="simplicite" from="${0..5}" />
            &nbsp;&nbsp; Gout <g:select id="gout" name="gout" from="${0..5}" />
            &nbsp;&nbsp;
               <input type="submit" name="valider" value="Noter" />

            </g:form>
            </li>
            <!--fin traitement noter recette-->
            <li class="fieldcontain">
             <g:form url="[resource: recetteInstance, action: 'addCoupDeCoeur']" method="post">
                <span  class="property-label"> J'aime :</span>&nbsp;
              <a href="" >  <img name="cdc" id="cdc" src="${resource(dir: 'images', file: 'heart_empty.png')}"
                                 onclick="this.src='${resource(dir: 'images', file: 'heart.png')}'; document.getElementById('valImg').value='heart.png'; return false;"
                                 width="13" height="13"    /></a>

             &nbsp;&nbsp;

             <a href="javascript:img()" >  <img  src="${resource(dir: 'images', file: 'heart_b.jpg')}"
                                 width="13" height="13"    /></a>

                 &nbsp;&nbsp;    <input type="submit" name="validercdc" value="Ok" />
                 <input type="hidden" id="valImg" name="valImg" value="" />
             </g:form>

            </li>
            <!-- gestion changement etat coup de coeur -->
            <script language="javascript" type="text/javascript">
                function img(){

                    var srce=document.getElementById("cdc").src.split('/');
                    var fic= srce[srce.length-1];
                    var replacefic='heart_empty.png';
                    fic=replacefic;
                    var newSrce="";
                    var i;
                    for (i=0; i<srce.length-1;i++){
                        newSrce +=srce[i]+"/";
                    }
                    newSrce +=fic;
                    document.getElementById("cdc").src=newSrce;
                    document.getElementById("valImg").value="";

                }

            </script>
        <!-- fin gestion changement etat coup de coeur -->


        <!--Ajouter un commentaire -->
            <li class="fieldcontain">
                <span  class="property-label"> Mon commentaire : </span>
                <g:form url="[resource: recetteInstance, action: 'addCommentaire']" method="post">
                    <g:textArea id="monCommentaire" name="monCommentaire"/>
                    <input type="submit" name="validerCommentaire" value="Commenter" />
                </g:form>
            </li>
        <!--fin traitement ajout de commentaire-->

            <g:if test="${recetteInstance?.commentaire}">
                <li class="fieldcontain">
                    <span id="commentaire-label" class="property-label"><g:message code="recette.commentaire.label"
                                                                                   default="Commentaire :"/></span>

                    <g:each in="${recetteInstance.commentaire}" var="c">
                        <span class="property-value" aria-labelledby="commentaire-label"><g:link controller="commentaire"
                                                                                                 action="show"
                                                                                                 id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
                    </g:each>

                </li>
            </g:if>


       </td></tr>
        </table>
    </ol>

<!-- afficher les  boutons edit et delete seuleument lorsqu'on est connecté et qu'on est l'auteur de la recette -->

    <g:form url="[resource: recetteInstance, action: 'delete']" method="DELETE">
        <% def getSession=session['utilisateur']
           if (getSession!=null){
              def currUser=Utilisateur.get(session.utilisateur.id)
              if ((currUser.pseudo).equals(recetteInstance.utilisateur.pseudo)){
                 %>
                  <fieldset class="buttons">
                        <g:link class="edit" action="edit" resource="${recetteInstance}"><g:message code="default.button.edit.label"
                                                                                                    default="Edit"/></g:link>
                        <g:actionSubmit class="delete" action="delete"
                                        value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                  </fieldset>
        <% }} %>

    </g:form>


</div>
</body>
</html>
