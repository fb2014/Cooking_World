<%@ page import="cooking_world.Utilisateur" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Welcome to CookingWorld</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <g:layoutHead/>
    <g:javascript library="application"/>
    <r:layoutResources/>
</head>

<body>
<div id="grailsLogo" role="banner">
    <div float="left" id="logo">
        <img src="${resource(dir: 'images', file: 'CookingWorld_logo.png')}" alt="Grails"/>
    </div>
    <a href="#create-session" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div style="margin-top:40px" float="right" id="create-session" class="content scaffold-create" role="main">
        <g:if test="${session.utilisateur != null}">
            <g:form controller="Utilisateur" action="logout">
                <div style="display:table-cell; padding-right: 50px">
                <g:link class="compte" controller="Utilisateur" action="show" id="${session.utilisateur.id}">${session.utilisateur.pseudo}</g:link>
                <g:submitButton name="logout" value="Log out"/></div>
            </g:form>
        </g:if>
        <g:else>
        <g:form controller="Utilisateur" action="connect">
            <fieldset class="form">
                <div style="padding-left: 20px">
                <div>
                <div style="display:table-cell"><g:render template="/layouts/form"/></div>
                <p style="display:table-cell; width:5px"></p>
                <div style="display:table-cell"><g:submitButton name="connect" value="Login"/></div>
                </div>
                <div>
                <g:link  style="display:table-cell" controller="Utilisateur" action="create" class="login">Créer un compte</g:link>
                    <p style="display:table-cell; width:30px"></p>
                <g:link style="display:table-cell" class="login">Mot de passe oublié</g:link>
                </div>
                </div>
            </fieldset>
        </g:form>
        </g:else>
    </div>
</div>
<g:layoutBody/>

<div class="footer" role="contentinfo"></div>

<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<r:layoutResources/>
</body>
</html>
