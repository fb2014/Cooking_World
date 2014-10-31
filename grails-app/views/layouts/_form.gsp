<%@ page import="cooking_world.Utilisateur" %>

<div>
    <!--
    <label for="login">
        <g:message code="session.login.label" default="login"/>
    </label>
    -->
    <div style="display:table-cell" ${hasErrors(bean: sessionInstance, field: 'pseudo', 'error')} ">
        <input style="width:100px" text="login" name="pseudo" default="Login" value="${sessionInstance?.pseudo}"/>
    </div>
    <p style="display:table-cell; width:5px"></p>
    <!--
    <label for="password">
        <g:message code="session.password.label" default="mot de passe"/>
    </label>
    -->
    <div style="display:table-cell" ${hasErrors(bean: sessionInstance, field: 'motDePasse', 'error')} ">
        <input style="width:100px" name="motDePasse" type="password" value="${sessionInstance?.motDePasse}"/>
    </div>
</div>