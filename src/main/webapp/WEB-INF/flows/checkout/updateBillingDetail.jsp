<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Customer</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Customer</h1>
			<p>Customer details</p>
		</div>
	</div>
	</section>
	<section class="container"> <form:form
		modelAttribute="order.customer" class="form-horizontal">
		<fieldset>
			<legend>Customer Details</legend>
			${order.customer.name}<br>
			<div class="form-group">
				<label class="control-label col-lg-2" for="doorNo">Door No</label>
				<div class="col-lg-10">
					<form:input id="doorNo" path="billingAddress.doorNo" type="text"
						class="form:input-large" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="streetName">Street
					Name</label>
				<div class="col-lg-10">
					<form:input id="streetName" path="billingAddress.streetName."
						type="text" class="form:input-large" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="areaName">Area
					Name</label>
				<div class="col-lg-10">
					<form:input id="areaName" path="billingAddress.areaName"
						type="text" class="form:input-large" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="state">State</label>
				<div class="col-lg-10">
					<form:input id="state" path="billingAddress.state" type="text"
						class="form:input-large" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="country">country</label>
				<div class="col-lg-10">
					<form:input id="country" path="billingAddress.country" type="text"
						class="form:input-large" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="zipCode">Zip Code</label>
				<div class="col-lg-10">
					<form:input id="zipCode" path="billingAddress.zipCode" type="text"
						class="form:input-large" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-lg-2" for="phoneNumber">Phone
					Number</label>
				<div class="col-lg-10">
					<form:input id="phoneNumber" path="phoneNumber" type="text"
						class="form:input-large" />
				</div>
			</div>
			<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button id="back" class="btn btn-default"
							name="_eventId_backToUpdateYesOrNoCustomerInfo">back</button>
					<input type="submit" id="btnAdd" class="btn btn-primary"
						value="Update" name="_eventId_customerInfoUpdated" />
					<button id="btnCancel" class="btn btn-default"
						name="_eventId_cancel">Cancel</button>
				</div>
			</div>
			
		</fieldset>
	</form:form> </section>
</body>
</html>