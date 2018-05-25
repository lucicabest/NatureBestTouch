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
<title><spring:message code="addProduct.form.h1Products.label" /></title>
</head>
<body>
	<section>
	<div class="pull-right" style="padding-right: 50px">
		<a href="?language=en">English</a>|<a href="?language=ro">Romana</a>
		<a href="<c:url value="/logout" />">Logout</a>
	</div>
	</section>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>
				<spring:message code="addProduct.form.h1Products.label" />
			</h1>
			<p>
				<spring:message code="addProduct.form.pAddProducts.label" />
			</p>
		</div>
	</div>
	</section>
	<section class="container"> 
		<form:form method="POST" modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
		<form:errors path="*" cssClass="alert alert-danger" element="div"/>
		<fieldset>
			<legend>
				<spring:message code="addProduct.form.legend.label" />
			</legend>
			<%-- <div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="productId">Product
					Id</label>
				<div class="col-lg-10">
					<form:input id="productId" path="productId" type="text"
						class="form:input-large" />
				</div>
			</div> --%>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="name"><spring:message
						code="addProduct.form.name.label" /></label>
				<div class="col-lg-10">
					<form:input id="name" path="name" type="text"
						class="form:input-large" />
					<form:errors path="name" cssClass="text-danger"/>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="category"><spring:message
						code="addProduct.form.category.label" /></label>
				<div class="col-lg-10">
					<form:input id="category" path="category" type="text"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2" for="description"><spring:message
						code="addProduct.form.description.label" /></label>
				<div class="col-lg-10">
					<form:textarea id="description" path="description" rows="2" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2" for="productImage"> <spring:message
						code="addProduct.form.productImage.label" />
				</label>
				<div class="col-lg-10">
					<form:input id="productImage" path="productImage" type="file"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2" for="productDocumentation">
					<spring:message code="addProduct.form.productDocumentation.label" />
				</label>
				<div class="col-lg-10">
					<form:input id="productDocumentation" path="productDocumentation"
						type="file" class="form:input-large" />
				</div>
			</div>


			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" id="btnAdd" class="btn btn-primary"
						value=<spring:message code="addProduct.form.Add.label"/> />
				</div>
			</div>
		</fieldset>
	</form:form> </section>
</body>
</html>