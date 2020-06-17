<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="menuBarPage.jsp" />
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

		<h2>View All Events</h2>

		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Title</th>
					<th>City</th>
					<th>Zip</th>
					<th>Helpers Needed</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="event" items="${allEvent}">
					<tr>
						<td><c:out value="${event.title}" /></td>
						<td><c:out value="${event.city}" /></td>
						<td><c:out value="${event.zip}" /></td>
						<td><c:out value="${event.number_helpers_needed}" /></td>
						<td>

							<form action="ReadAllEventPage" method="post">
								<input type="hidden" name="action" value="DeleteEvent" /> <input
									type="hidden" name="titleToDelete" value="${event.title}" />
								<input type="submit" value="Delete" />
							</form>
						</td>
						<td>
							<form action="ReadAllEventPage" method="post">
								<input type="hidden" name="action" value="ReadEventPage" /> <input
									type="hidden" name="titleToView" value="${event.title}" />
								<input type="submit" value="Details" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</header>