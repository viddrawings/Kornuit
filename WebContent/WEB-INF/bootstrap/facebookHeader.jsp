<<<<<<< HEAD
<%
				if (session.getAttribute("facebookId") != null) {
			%>
			<img src="//graph.facebook.com/${sessionScope.facebookId}/picture">

			<%
				}
			%>

=======
<%
				if (session.getAttribute("facebookId") != null) {
			%>
			<img src="//graph.facebook.com/${sessionScope.facebookId}/picture">

			<%
				}
			%>

>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
			&nbsp;&nbsp;Hallo ${sessionScope.name}