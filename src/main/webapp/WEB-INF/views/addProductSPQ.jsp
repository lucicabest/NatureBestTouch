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
<title><spring:message code="addProductSPQ.form.h1Products.label"/></title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1><spring:message code="addProductSPQ.form.h1Products.label"/></h1>
		</div>
	</div>
	</section>
	<section class="container">
	<div class="row">
	<div class="col-md-5">
			<img src="<c:url value="/img/${product.productId}.jpg"></c:url>"alt="image" style="width: 100%" />
		</div>
		<div class="col-md-5">
			<h3>${product.name}</h3>
			<p>${product.description}</p>
			<p>
				<strong><spring:message code="addProductSPQ.form.productId.label"/></strong> <span class="label label-warning">${product.productId}
				</span>
			</p>
			<%-- <p>	<strong>manufacturer</strong> : ${product.manufacturer}	</p> --%>
			<p>
				<strong><spring:message code="addProductSPQ.form.category.label"/></strong> ${product.category}
			</p>
			<c:forEach items="${product.unitSPQ}" var="product_unitSPQ">
				<div class="col-ms-6">
					<p>
						<strong><spring:message code="addProductSPQ.form.size.label"/></strong> ${product_unitSPQ.size}oz .................
						${product_unitSPQ.price}$ .......... <spring:message code="addProductSPQ.form.stock.label"/> ${product_unitSPQ.unitsInStock}
					</p>
				</div>
			</c:forEach>
			<%-- <p>
				<strong>Available units in stock </strong> : ${product.unitsInStock}
			</p>
			<h4>${product.unitPrice}USD</h4>
			<p> --%>
			<strong><spring:message code="addProductSPQ.form.summary.label"/></strong>

		</div>
	</div>
	</section>

	<section class="container"> 
		<form:form method="POST" modelAttribute="newProductSPQ" class="form-horizontal">
		<form:errors path="*" cssClass="alert alert-danger" element="div"/>
		<fieldset>
								
			<legend><spring:message code="addProductSPQ.form.legend.label"/></legend>
			
			
			
			
			<%-- <div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="productId">Product
					Id</label>
				<div class="col-lg-10">
					<form:input id="productId" path="productId" type="text"
						class="form:input-large" />
				</div>
			</div> --%>

			<form:input id="productId" value="${product.productId}" path="productId" type="hidden" />

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
				<label class="control-label col-lg-2 col-lg-2" for="price"><spring:message code="addProductSPQ.form.price.label"/></label>
				<div class="col-lg-10">
					<form:input id="price" path="price" type="text"	class="form:input-large" />
					<form:errors path="price" cssClass="text-danger"/>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="unitsInStock"><spring:message code="addProductSPQ.form.stock.label"/></label>
				<div class="col-lg-10">
					<form:input id="unitsInStock" path="unitsInStock" type="text"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnAdd" class="btn btn-primary"
						value=<spring:message code="addProductSPQ.form.Add.label"/> />
				</div>
			</div>

		</fieldset>
	</form:form> </section>
	<section class="container">
	<div class="row">
		<a
			href="<spring:url value="/market/product?id=${product.productId}" />"
			class="btn btn-default"> <span
			class="glyphicon-hand-left glyphicon"></span> <spring:message code="addProductSPQ.form.back.label"/>
		</a>
		<%--		<a href="#" class="btn btn-warning btn-large"> <span
				class="glyphicon-shopping-cart glyphicon"> </span> Add
				size/price/stock
			</a>  --%>
	</div>
	</section>

</body>
</html>