<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
				<p>These are all the users with their passwords</p>
			</div>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${users}" var="user">
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<div class="caption">
							<h3>UserID: ${user.userId}</h3>
							<p>First Name: ${user.firstName}</p>
							<p>Last Name: ${user.lastName}</p>
							<p>Username: ${user.username}</p>
							<p>Password: ${user.password}</p>
							<p>Email: ${user.email}</p>
							<p>Is Admin?: ${user.is_admin}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
</body>
</html>