<jsp:include page="menuBarPage.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/styles/masterStyle.css">

<script>
	function hideDeleteShowEdit(index) {
		var x = document.getElementById("edit" + index);
		var y = document.getElementById("delete" + index);
		x.style.display = "inline";
		y.style.display = "none";
	}
	function showDeleteHideEdit(index) {
		var delayInMilliseconds = 1000; //1 second

		setTimeout(function() {
			var x = document.getElementById("edit" + index);
			var y = document.getElementById("delete" + index);

			x.style.display = "none";
			y.style.display = "inline";
		}, delayInMilliseconds);
	}
	function showAddTextShowEnterHidePlus() {
		var x = document.getElementById("addText");
		var y = document.getElementById("newPersonButton");
		var z = document.getElementById("plus");
		x.style.display = "inline";
		y.style.display = "inline";
		z.style.display = "none";
	}
</script>

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

		<h2>View Event</h2>

		<form action="ReadEventPage" method="post">

			<input type="hidden" name="action"
				value="UpdateEvent" />

			<div>
				<label for="title">Title</label>
				<div>
					<input type="text" id="title" name="title" placeholder="Title"
						required="required" value="${selectedEvent.title}" />
				</div>
			</div>

			<div>
				<label for="info_url">Event Info Url</label>
				<div>
					<input type="text" id="info_url" name="info_url"
						placeholder="Event Info Url" required="required"
						value="${selectedEvent.info_url}" />
				</div>
			</div>
			<div>
				<label for="address_number">Address Number</label>
				<div>
					<input type="text" id="address_number" name="address_number"
						placeholder="Address Number" required="required"
						value="${selectedEvent.address_number}" />
				</div>
			</div>
			<div>
				<label for="street_name">Street Name</label>
				<div>
					<input type="text" id="street_name" name="street_name"
						placeholder="Street Name" required="required"
						value="${selectedEvent.street_name}" />
				</div>
			</div>
			<div>
				<label for="city">City</label>
				<div>
					<input type="text" id="city" name="city" placeholder="City"
						required="required" value="${selectedEvent.city}" />
				</div>
			</div>
			<div>
				<label for="state">State</label>
				<div>
					<input type="text" id="state" name="state" placeholder="State"
						required="required" value="${selectedEvent.state}" />
				</div>
			</div>
			<div>
				<label for="zip">Zip</label>
				<div>
					<input type="text" id="zip" name="zip" placeholder="Zip"
						required="required" value="${selectedEvent.zip}" />
				</div>
			</div>
			<div>
				<label for="number_helpers_needed">Number of Helpers Needed</label>
				<div>
					<input type="text" id="number_helpers_needed"
						name="number_helpers_needed"
						placeholder="Number of Helpers Needed" required="required"
						value="${selectedEvent.number_helpers_needed}" />
				</div>
			</div>
			<div>
				<label for="event_image_url">Event Image URL</label>
				<div>
					<input type="text" id="event_image_url" name="event_image_url"
						placeholder="Event Image URL" required="required"
						value="${selectedEvent.event_image_url}" />
				</div>
			</div>

			<!-- event people -->
			<div style="padding: 2px;">
				<ul style="list-style: none; display: flex; flex-direction: row;">
					<c:forEach var="person" items="${persons}" varStatus="loop">
						<li
							style="border-radius: 15px; background-color: grey; display: table; margin-right: 20px">
							<input onfocus="hideDeleteShowEdit('${loop.index}')"
							onfocusout="showDeleteHideEdit('${loop.index}')"
							size="${person.getPeronFullName().length()}"
							style="background-color: grey; border-radius: 15px; margin: 0"
							type="text" name="tagTextBox" value="${person.getFullName()}" />
							<button id="delete${loop.index}"
								style="display: inline; background-color: grey; border-radius: 15px; margin: 0"
								type="submit" name="deleteTagButton"
								value="${person.getAuto_id()}/${person.getFullName()}">X</button>
							<button id="edit${loop.index}"
								style="display: none; background-color: grey; border-radius: 15px; margin: 0"
								type="submit" name="editTagButton"
								value="${person.getAuto_id()}/${event.getTitle}">></button>
						</li>
					</c:forEach>
					<li style="display: inline; margin-left: 0"><input
						id="addText"
						style="display: none; background-color: grey; border-radius: 15px; margin: 0"
						type="text" size="25" name="addText" value="" />

						<button id="newPersonButton"
							style="display: none; width: 30px; height: 30px; background-color: white; border-radius: 15px; margin: 0"
							type="submit" name="newPersonButton" value="${event.title}">></button>

						<button id="plus"
							style="display: inline; width: 30px; height: 30px; background-color: white; border-radius: 15px; margin: 0"
							type="button" name="plus"
							onclick="showAddTextShowEnterHidePlus()" value="">+</button></li>
				</ul>
			</div>

			<div>
				<div>
					<input type="submit" value="Update"/>
				</div>
			</div>
		</form>
	</div>
</header>

