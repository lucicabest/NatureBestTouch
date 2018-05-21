<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css
/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1 class="alert alert-danger">There is no product found with
				the Product id ${invalidProductId}</h1>
		</div>
	</div>
	</section>
	<section>
	<div class="container">
		<p>${url}</p>
		<p>${exception}</p>
	</div>
	<div class="container">
		<p>
			<a href="<spring:url value="/market/products" />"
				class="btn btn-primary"> <span
				class="glyphicon-hand-left glyphicon"> </span> products
			</a>
		</p>
	</div>
	</section>
</body>
</html>