<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
<script src="/webstore/resources/js/controllers.js"></script>
<title><spring:message code="product.form.pageTitle.label" /></title>
</head>
<body>
	<section>
	<div class="pull-right" style="padding-right: 50px">
		<a href="product?id=${product.productId}&language=en">English</a>|<a
			href="product?id=${product.productId}&language=ro">Romana</a>
	</div>
	</section>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>
				<spring:message code="product.form.pageName.label" />
			</h1>
		</div>
	</div>
	</section>
	<section class="container" ng-app="cartApp">
	<div class="row">
		<div class="col-md-5">
			<img src="<c:url value="/img/${product.productId}.jpg"></c:url>"
				alt="image" style="width: 100%" />
		</div>
		<div class="col-md-5">
			<h3>${product.name}</h3>
			<p>${product.description}</p>
			<p>
				<strong><spring:message code="product.form.productId.label" /></strong><span
					class="label label-warning">${product.productId} </span>
			</p>
			<%-- <p>	<strong>manufacturer</strong> : ${product.manufacturer}	</p> --%>
			<p>
				<strong><spring:message code="product.form.category.label" /></strong>
				: ${product.category}
			</p>
<%--			<section class="container"> <form:form method="POST"
				modelAttribute="productSPQ" class="form-horizontal"> --%>
				<c:forEach items="${product.unitSPQ}" var="product_unitSPQ">

<%--					<div class="form-group">

						<label class="control-label col-lg-2" for="condition">Condition</label>
						<div class="col-lg-10">
							<form:radiobutton path="condition" value="New" />
							New
							<form:radiobutton path="condition" value="Old" />
							Old
							<form:radiobutton path="condition" value="Refurbished" />
							Refurbished
						</div>
					</div>
--%>

					<div class="col-ms-6">
						<p>
							<strong><spring:message code="product.form.size.label" /></strong>
							${product_unitSPQ.size}oz .................
							${product_unitSPQ.price}$ ..... ${product_unitSPQ.unitsInStock}
							<spring:message code="product.form.available.label" />
						</p>
					</div>
				</c:forEach>
<%--			</form:form> </section> --%>
			<%-- <p>
				<strong>Available units in stock </strong> : ${product.unitsInStock}
			</p>
			<h4>${product.unitPrice}USD</h4>
			<p> --%>
			<p ng-controller="cartCtrl">
				<a href="<spring:url value="/market/products" />"
					class="btn btn-default"> <span
					class="glyphicon-hand-left glyphicon"></span> <spring:message
						code="product.form.back.label" />
				</a> <a href="#" class="btn btn-warning btn-large"
					ng-click="addToCart('${product.productId}')"><span
					class="glyphicon-shopping-cart glyphicon"> </span> <spring:message
						code="product.form.orderNow.label" /> </a> <a
					href="<spring:url value="/cart" />" class="btn btn-default"> <span
					class="glyphicon-hand-right glyphicon"></span> View Cart
				</a>
			</p>
		</div>
	</div>
	</section>
</body>
</html>