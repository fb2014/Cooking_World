<%@ page import="cooking_world.CoupDeCoeur" %>



<div class="fieldcontain ${hasErrors(bean: coupDeCoeurInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="coupDeCoeur.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${coupDeCoeurInstance?.date}"  />

</div>

