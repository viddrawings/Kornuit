<!DOCTYPE html>
<html>
<head>
<!-- Author 1: Mark Willem Johan Stroeven -->
<!-- Author 2: Tim Ijpenga -->
<!-- Date: April 3 -->
<title>Kornuit</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-default" style="margin: 0px;">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Kornuit</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index">Home <span
							class="sr-only">(current)</span></a></li>
					<li class="active"><a href="mijnkornuit">Mijn kornuit</a></li>
					<li><a href="suggestie">Suggesties</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Instellingen
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Notificaties</a></li>
							<li class="divider"></li>
							<li><a href="#">Vrienden</a></li>
							<li class="divider"></li>
							<li><a href="#">Overig</a></li>
							<li class="divider"></li>
							<li><a href="accounts">Accounts</a></li>
						</ul></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="/Kornuit">Uitloggen</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="panel panel-info">
		<div class="panel-heading"><%@ include file="facebookHeader.jsp"%></div>

		<div class="panel-body">
			<div class="col-md-12 well">
				<h4 style="color: darkblue">Groepen</h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Vestibulum sagittis pharetra vehicula. Morbi quis vehicula odio.
					Cras eget nunc ullamcorper, placerat mauris vitae, lobortis magna.
					Quisque eu purus nec lectus congue condimentum. Maecenas porttitor
					iaculis neque, tincidunt pulvinar nulla rhoncus posuere. Sed
					volutpat metus eget turpis fermentum, mollis dignissim diam
					ullamcorper</p>
					<br></br>
					<a href="newgroup"><button type="submit" form="login" class="btn btn-info col-md-4">Maak een nieuwe groep aan</button></a>
					<br></br>
					<a href="managegroups"><button type="submit" form="login" class="btn btn-info col-md-4">Wijzig een bestaande groep</button></a>
			</div><br><br><br>
			
			<div class="col-md-12 well">
				<h4 style="color: darkblue">Afspraken</h4>
				<p>Hier zijn alle afspraken te zien die je heb gemaakt!</p><br>

					<div class="row">
					
			<%@ page import="java.util.List" %>
			<%@ page import="com.kornuit.calendar.Afspraak" %>
			
			<%
				List<Afspraak> alle_afspraken;
				if (request.getAttribute("afspraken") != null) {	
					alle_afspraken = (List<Afspraak>) request.getAttribute("afspraken");
					
					for (Afspraak a : alle_afspraken) { 
						String url = "https://graph.facebook.com/" + a.getFacebookVriendId() + "/picture?type=large";
						String modalId = "modal" + a.getId();
					%>
						
						<div class="col-sm-6 col-md-3">
					    <div class="thumbnail">
					      <img src=<%=url%> alt="thumbnail" style="width: 300px; height: 300px;" data-toggle="modal" data-target=<%="#" + modalId%>>
					      <div class="caption">
					        <h3>Afspraak met:<br>
					        <%=a.getFacebookVriendNaam()%></h3>
					        <p><%=a.getActiviteit()%></p><br>
					        <p>
					        	<button type="button" class="btn btn-primary" data-toggle="modal" data-target=<%="#" + modalId%>>Meer info</button>
				        		<a href="#" class="btn btn-danger" role="button">Afspraak annuleren</a>
				        	</p>
					      </div>
					    </div>
					  </div>
					  
					  
					  <!-- Modal -->
						<div id=<%=modalId%> class="modal fade" role="dialog">
						  <div class="modal-dialog">
						
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						        <h4 class="modal-title">Afspraak met: <%=a.getFacebookVriendNaam()%></h4>
						      </div>
						      <div class="modal-body">
						      	<center>
						      		<img src=<%=url%> alt="thumbnail" style="width: 400px; height: 400px;">
					      		</center><br><br>
						      	<p><b>Activiteit:</b> <%=a.getActiviteit()%></p>
						        <p><b>Locatie:</b> <%=a.getLocatie()%></p>
						        <p><b>Datum & Tijd:</b> <%=a.getDatumTijd()%></p>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Sluit</button>
						      </div>
						    </div>
						
						  </div>
						</div>
					  
			<%
					}
				}
			%>
			
					</div>
					<br>
			</div>
		</div>
	</div>





</body>
</html>