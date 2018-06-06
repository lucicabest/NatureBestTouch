<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Invalid cart</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1 class="alert alert-danger">Thank you</h1>
			<p>
				Thanks for the order. your order will be delivered to you on
				<fmt:formatDate type="date"
					value="${order.shippingDetail.shippingDate}" />	!
			</p>
			<p>Your Order Number is ${order.orderId}</p>
		</div>
	</div>
	</section>
	<section>
	<div class="container">
		<p>
			<a href="<spring:url value="/login?logout" />"
				class="btn btn-primary"> <span
				class="glyphicon-hand-left glyphicon"> </span> Done
			</a>
		</p>
	</div>
	</section>
</body>
</html>