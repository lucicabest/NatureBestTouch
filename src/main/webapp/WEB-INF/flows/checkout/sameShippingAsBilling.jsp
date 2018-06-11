<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Order Confirmation</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Shipping Address</h1>
			<p>Same as Billing Address?</p>
		</div>
	</div>
	</section>
	<div class="container">
		<div class="row">
			<form:form modelAttribute="order" class="form-horizontal">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />
				<div
					class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
					<div class="text-center">
						<h1>Shipping Address</h1>
					</div>
										
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
					<div class="row">
						
						<button id="back" class="btn btn-default"
							name="_eventId_backToUpdateBillingDetail">back</button>
						<div class="btn btn-default">
						Same as Billing?
						</div>
						<button type="submit" class="btn btn-success"
							name="_eventId_sameAsShipping">
							Yes <span class="glyphicon glyphicon-chevron-right"></span>
						</button>
						<button id="No" class="btn btn-success"
							name="_eventId_needsShippingAddress">
							No <span class="glyphicon glyphicon-chevron-right"></span>
						</button>
						<button id="btnCancel" class="btn btn-default"
							name="_eventId_cancel">Cancel</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>
