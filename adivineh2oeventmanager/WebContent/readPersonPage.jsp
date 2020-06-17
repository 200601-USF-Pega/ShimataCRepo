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

		<h2>View Person</h2>

		<form action="ReadPersonPage" method="post">

			<input type="hidden" name="action" value="UpdatePerson" />

			<div>
				<label for="first_name">First Name</label> <input type="text"
					name="first_name" placeholder="First Name"
					value="${selectedPerson.first_name}" />
			</div>
			<div>
				<label for="last_name">Last Name</label> <input type="text"
					name="last_name" placeholder="Last Name"
					value="${selectedPerson.last_name}" />
			</div>
			<div>
				<label for="role">Role</label> <input type="text" name="role"
					placeholder="Role" value="${selectedPerson.role}" />
			</div>
			<div>
				<label for="seats_available">Seats Available</label> <input type="text"
					name="seats_available" placeholder="Seats Available"
					value="${selectedPerson.seats_available}" />
			</div>
			<div>
				<label for="phone">Phone</label> <input type="text" name="phone"
					placeholder="Phone" value="${selectedPerson.phone}" />
			</div>
			<div>
				<label for="email">Email</label> <input type="text" name="email"
					placeholder="Email" value="${selectedPerson.email}" />
			</div>
			<div>
				<label for="facebook">Facebook</label> <input type="text"
					name="facebook" placeholder="Facebook"
					value="${selectedPerson.facebook}" />
			</div>
			<div>
				<label for="address_number">Address Number</label> <input type="text"
					name="address_number" placeholder="Address Number"
					value="${selectedPerson.address_number}" />
			</div>
			<div>
				<label for="street_name">Street Name</label> <input type="text"
					name="street_name" placeholder="Street Name"
					value="${selectedPerson.street_name}" />
			</div>
			<div>
				<label for="unit">Unit</label> <input type="text" name="unit"
					placeholder="Unit" value="${selectedPerson.unit}" />
			</div>
			<div>
				<label for="city">City</label> <input type="text" name="city"
					placeholder="City" value="${selectedPerson.city}" />
			</div>
			<div>
				<label for="state">State</label> <input type="text"
					name="state" placeholder="State"
					value="${selectedPerson.state}" />
			</div>
						<div>
				<label for="zip">Zip</label> <input type="text"
					name="zip" placeholder="Zip"
					value="${selectedPerson.zip}" />
			</div>
			<div>
				<label for="profile_image_url">Profile Image Url</label> <input type="text"
					name="profile_image_url" placeholder="Profile Image Url"
					value="${selectedPerson.profile_image_url}" />
			</div>


			<!-- events event -->
			<div style="padding: 2px;">
				<ul style="list-style: none; display: flex; flex-direction: row;">
					<c:forEach var="event" items="${events}" varStatus="loop">
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
								value="${person.getAuto_id()}/${person.getTitle}">></button>
						</li>
					</c:forEach>
					<li style="display: inline; margin-left: 0"><input
						id="addText"
						style="display: none; background-color: grey; border-radius: 15px; margin: 0"
						type="text" size="25" name="addText" value="" />

						<button id="newPersonButton"
							style="display: none; width: 30px; height: 30px; background-color: white; border-radius: 15px; margin: 0"
							type="submit" name="newPersonButton" value="${person.title}">></button>

						<button id="plus"
							style="display: inline; width: 30px; height: 30px; background-color: white; border-radius: 15px; margin: 0"
							type="button" name="plus"
							onclick="showAddTextShowEnterHidePlus()" value="">+</button></li>
				</ul>
			</div>

			<div>
				<div>
					<input type="submit" value="Update" />
				</div>
			</div>
		</form>
	</div>
</header>

