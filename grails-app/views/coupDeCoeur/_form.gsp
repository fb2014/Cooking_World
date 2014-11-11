<%@ page import="cooking_world.CoupDeCoeur" %>



<div class="fieldcontain ${hasErrors(bean: coupDeCoeurInstance, field: 'utilisateur', 'error')} required">
	<label for="utilisateur">
		<g:message code="coupDeCoeur.utilisateur.label" default="Utilisateur" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="utilisateur" name="utilisateur.id" from="${cooking_world.Utilisateur.list()}" optionKey="id" required="" value="${coupDeCoeurInstance?.utilisateur?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coupDeCoeurInstance, field: 'recette', 'error')} required">
	<label for="recette">
		<g:message code="coupDeCoeur.recette.label" default="Recette" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="recette" name="recette.id" from="${cooking_world.Recette.list()}" optionKey="id" required="" value="${coupDeCoeurInstance?.recette?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: coupDeCoeurInstance, field: 'dateCoupDeCoeur', 'error')} required">
	<label for="dateCoupDeCoeur">
		<g:message code="coupDeCoeur.dateCoupDeCoeur.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateCoupDeCoeur" precision="day"  value="${coupDeCoeurInstance?.dateCoupDeCoeur}"  />

</div>

