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
	<section class="container"> <form:form modelAttribute="order" class="form-horizontal">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />
		<fieldset>
			<div class="text-center">
				<h1>Customer Details</h1>
			</div>
			
			<legend>Customer Details</legend>
			
			<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<address>
								<strong>Billing Address</strong> <br>
								${order.customer.name}<br>
								${order.customer.billingAddress.doorNo}
								${order.customer.billingAddress.streetName} <br>
								${order.customer.billingAddress.areaName},
								${order.customer.billingAddress.state} <br>
								${order.customer.billingAddress.country},
								${order.customer.billingAddress.zipCode} <br> <abbr>P:</abbr>
								${order.customer.phoneNumber}
							</address>
						</div>
			</div>
			
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<div id="back" class="btn btn-default">Update? </div>
					<input type="submit" id="btnAdd" class="btn btn-primary"
						value="Yes" name="_eventId_updateCustomerDetail" />
					<button id="btnCancel" class="btn btn-default"
						name="_eventId_customerDetailOK">No</button>
				</div>
			</div>
		</fieldset>
	</form:form> </section>
</body>
</html>