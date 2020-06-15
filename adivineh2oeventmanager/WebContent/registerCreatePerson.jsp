
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<header>
	<div>


		<c:choose>
			<c:when test="${not empty message }">
				<p class="alert ${messageClass}">${message}</p>
				<%
					session.setAttribute("message", null);
				session.setAttribute("messageClass", null);
				%>
			</c:when>
		</c:choose>

		<h1>
			A Divine H2O Event Management <small>Register</small>
		</h1>

		<form action="registerCreatePerson" method="post">
			<div>
				<label for="phone_number">Phone Number</label>
				<div>
					<input type="text" id="phone_number" name="phone_number"
						required="required"
						value="${param.phone_number}" />
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
					<button type="submit">Register</button>
				</div>
			</div>
		</form>
	</div>
</header>
