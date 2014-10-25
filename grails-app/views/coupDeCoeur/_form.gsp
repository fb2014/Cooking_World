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

<div class="fieldcontain ${hasErrors(bean: coupDeCoeurInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="coupDeCoeur.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${coupDeCoeurInstance?.date}"  />

</div>

