<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Users</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Users</h1>
			<p>Add users</p>
		</div>
	</div>
	</section>
	<section class="container"> <form:form method="POST"
		modelAttribute="newUser" class="form-horizontal">
		<fieldset>
			<legend>Add new user</legend>
			<%-- <div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="productId">Product
					Id</label>
				<div class="col-lg-10">
					<form:input id="productId" path="productId" type="text"
						class="form:input-large" />
				</div>
			</div> --%>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="name">First
					Name</label>
				<div class="col-lg-10">
					<form:input id="firstName" path="firstName" type="text"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="category">Last
					Name</label>
				<div class="col-lg-10">
					<form:input id="lastName" path="lastName" type="text"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2" for="description">Email</label>
				<div class="col-lg-10">
					<form:input id="email" path="email" rows="2" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="name">Username</label>
				<div class="col-lg-10">
					<form:input id="username" path="username" type="text"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="category">Password</label>
				<div class="col-lg-10">
					<form:input id="password" path="password" type="text"
						class="form:input-large" />
				</div>
			</div>

			<div class="form-group">
					<label class="control-label col-lg-2" for="isAdmin">Admin</label>
					<div class="col-lg-10">
						<form:checkbox id="isAdmin" path="is_admin" />
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
</body>
</html>