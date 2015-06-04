<%
				if (session.getAttribute("facebookId") != null) {
			%>
			<img src="//graph.facebook.com/${sessionScope.facebookId}/picture">

			<%
				}
			%>

			&nbsp;&nbsp;Hallo ${sessionScope.name}