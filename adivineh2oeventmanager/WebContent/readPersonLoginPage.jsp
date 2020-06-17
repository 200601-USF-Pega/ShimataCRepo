
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/styles/masterStyle.css">

<header>
	<div>


		<c:choose>
			<c:when test="${not empty message }">
				<p>${message}</p>
				<%
					session.setAttribute("message", null);
				session.setAttribute("messageClass", null);
				%>
			</c:when>
		</c:choose>

		<h1>
			A Divine H2O Event Management <small>Login</small>
		</h1>

		<form name="loginReadPerson" action="ReadPersonLoginPage" method="post">
			<div>
				<label for="phone_number">Phone Number</label>
				<div>
					<input type="text" id="phone" name="phone"
						required="required"
						value="${param.phone}" />
				</div>
			</div>
			<div>
				<label for="pass">Password</label>
				<div>
					<input type="password" id="pass" name="pass"
						required="required"
						value="${param.pass}" />
				</div>
			</div>
			<div>
				<div>
					<input type="submit" value="Login"/>
				</div>
			</div>
		</form>
	</div>
</header>
