<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Products</h1>
		</div>
	</div>
	</section>
	<section class="container">
	<div class="row">
		<div class="col-md-5">
			<h3>${product.name}</h3>
			<p>${product.description}</p>
			<p>
				<strong>Item Code : </strong><span class="label label-warning">${product.productId}
				</span>
			</p>
			<%-- <p>	<strong>manufacturer</strong> : ${product.manufacturer}	</p> --%>
			<p>
				<strong>category</strong> : ${product.category}
			</p>
			<c:forEach items="${product.unitSPQ}" var="product_unitSPQ">
				<div class="col-ms-6">
					<p>
						<strong>Size </strong> ${product_unitSPQ.size}oz .................
						${product_unitSPQ.price}$ ${product_unitSPQ.unitsInStock}
						available
					</p>
				</div>
			</c:forEach>
			<%-- <p>
				<strong>Available units in stock </strong> : ${product.unitsInStock}
			</p>
			<h4>${product.unitPrice}USD</h4>
			<p> --%>
			<strong>Add an extra size, price and stock </strong>

		</div>
	</div>
	</section>

	<section class="container"> <form:form method="POST"
		modelAttribute="newProductSPQ" class="form-horizontal">
		<fieldset>
			<legend>Add new size, price, stock</legend>
			<%-- <div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="productId">Product
					Id</label>
				<div class="col-lg-10">
					<form:input id="productId" path="productId" type="text"
						class="form:input-large" />
				</div>
			</div> --%>

			<div class="form-group">
				<label class="control-label col-lg-2" for="size">Size</label>
				<div class="col-lg-10">
					<%--	<form action="/action_page.php"> --%>
					<select name="size">
						<option value="0.5">1/2 oz</option>
						<option value="1">1 oz</option>
						<option value="3.3">3.3 oz</option>
						<option value="16.6">16.6 oz</option>
					</select>
					<%--	</form> --%>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="price">Price</label>
				<div class="col-lg-10">
					<form:input id="price" path="price" type="text"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="unitsInStock">Stock</label>
				<div class="col-lg-10">
					<form:input id="unitsInStock" path="unitsInStock" type="text"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnAdd" class="btn btn-primary"
						value="Add" />
				</div>
			</div>

		</fieldset>
	</form:form> </section>
	<section class="container">
	<div class="row">
		<a
			href="<spring:url value="/market/product?id=${product.productId}" />"
			class="btn btn-default"> <span
			class="glyphicon-hand-left glyphicon"></span> back
		</a>
		<%--		<a href="#" class="btn btn-warning btn-large"> <span
				class="glyphicon-shopping-cart glyphicon"> </span> Add
				size/price/stock
			</a>  --%>
	</div>
	</section>

</body>
</html>