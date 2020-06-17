<jsp:include page="menuBarPage.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

		<table>
			<thead>
				<tr>
					<td>Title</td>
					<td>City</td>
					<td>Zip</td>
					<td>Helpers Needed</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="event" items="${events}">
					<tr>
						<td><c:out value="${event.title}" /></td>
						<td><c:out value="${event.city}" /></td>
						<td><c:out value="${event.zip}" /></td>
						<td><c:out value="${event.number_helpers_needed}" /></td>
						<td><form action="deleteEvent?title=${event.title}"
								method="get">
								<input type="hidden" name="deleteEvent" value="${event.title}">
								<button>Delete</button>
							</form></td>
						<td><form action="readEvent?title=${event.title}"
								method="get">
								<input type="hidden" name="readEvent" value="${event.title}">
								<button>View</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</header>