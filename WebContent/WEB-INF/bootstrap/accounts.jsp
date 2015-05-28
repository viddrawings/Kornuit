<!DOCTYPE html>
<html>
<head>
<!-- Author 1: Mark Willem Johan Stroeven -->
<!-- Author 2: Tim Ijpenga -->
<!-- Date: April 3 -->
<title>Kornuit</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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
				<a class="navbar-brand" href="index">Kornuit</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index">Home <span
							class="sr-only">(current)</span></a></li>
					<li class=""><a href="mijnkornuit">Mijn kornuit</a></li>
					<li><a href="suggestie">Suggesties</a></li>
					<li class="active"><a href="#" class="dropdown-toggle"
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
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="panel panel-info">
  <div class="panel-heading"><%@ include file="facebookHeader.jsp"%></div>
		<div class="panel-body">
			<%
				boolean faceBookLink = (boolean) request.getSession().getAttribute(
						"facebooktoken");
				boolean googleLink = (boolean) request.getSession().getAttribute(
						"googletoken");
				boolean twitterLink = (boolean) request.getSession().getAttribute(
						"twittertoken");
				if (faceBookLink) {
			%>
			<div class="col-md-3 well" style="margin: 5px;" id="facebook">
				<h4 style="color: darkblue">Facebook</h4>
				<p>Door uw Facebook account aan Kornuit te koppelen biedt u ons
					de mogelijkheid om een accurate analyse te doen en u van logische
					suggesties te voorzien. Binnen Facebook maken wij gebruik van data
					zoals Likes, Events, Statussen en Pokes. U kunt ten alle tijden de
					toegang tot uw Facebook account verbieden door dit account te
					ontkoppelen.</p>
				<div class="btn-group">
					<button type="button" class="btn btn-success">Facebook
						account gekoppeld</button>
					<button type="button" class="btn btn-success dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="deletefacebookaccount">Account ontkoppellen</a></li>
						<li class="divider"></li>
						<li><a href="switchfacebookaccount">Account verwisselen</a></li>
					</ul>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="col-md-3 well" style="margin: 5px;" id="facebook">
				<h4 style="color: darkblue">Facebook</h4>
				<p>Door uw Facebook account aan Kornuit te koppelen biedt u ons
					de mogelijkheid om een accurate analyse te doen en u van logische
					suggesties te voorzien. Binnen Facebook maken wij gebruik van data
					zoals Likes, Events, Statussen en Pokes. U kunt ten alle tijden de
					toegang tot uw Facebook account verbieden door dit account te
					ontkoppelen.</p>
				<div class="btn-group">
					<button type="button" class="btn btn-danger">Facebook
						account niet gekoppeld</button>
					<button type="button" class="btn btn-danger dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="newfacebookaccount">Nieuw account koppelen</a></li>
					</ul>
				</div>
			</div>
			<%
				}
				if (googleLink) {
			%>
			<div class="col-md-3 well" style="margin: 5px;" id="google">
				<h4 style="color: darkblue">Google+</h4>
				<p>Door uw Google+ account aan Kornuit te koppelen biedt u ons
					de mogelijkheid om zeer accuraat te analyseren wat de
					vriendschapsverhoudingen binnen uw kringen zijn om zo een goed
					onderbouwd en logische suggestie te doen. Binnen Google+ maken wij
					gebruik van voornamelijk uw Kringen. U kunt ten alle tijden de
					toegang tot uw Google+ account verbieden door dit account te
					ontkoppelen.</p>
				<div class="btn-group">
					<button type="button" class="btn btn-success">Google+
						account gekoppeld</button>
					<button type="button" class="btn btn-success dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="deletefacebookaccount">Account ontkoppellen</a></li>
						<li class="divider"></li>
						<li><a href="switchfacebookaccount">Account verwisselen</a></li>
					</ul>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="col-md-3 well" style="margin: 5px;" id="google">
				<h4 style="color: darkblue">Google+</h4>
				<p>Door uw Google+ account aan Kornuit te koppelen biedt u ons
					de mogelijkheid om zeer accuraat te analyseren wat de
					vriendschapsverhoudingen binnen uw kringen zijn om zo een goed
					onderbouwd en logische suggestie te doen. Binnen Google+ maken wij
					gebruik van voornamelijk uw Kringen. U kunt ten alle tijden de
					toegang tot uw Google+ account verbieden door dit account te
					ontkoppelen.</p>
				<div class="btn-group">
					<button type="button" class="btn btn-danger">Google+
						account niet gekoppeld</button>
					<button type="button" class="btn btn-danger dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Nieuw account koppelen</a></li>
					</ul>
				</div>
			</div>
			<%
				}
				if (twitterLink) {
			%>
			<div class="col-md-3 well" style="margin: 5px;" id="twitter">
				<h4 style="color: darkblue">Twitter</h4>
				<p>Door uw Twitter account aan Kornuit te koppelen biedt u ons
					de mogelijkheid om een diepgaande analyse te doen naar uw
					tweetgedrag en dat van uw vrienden. Hierdoor kunnen wij
					onderliggende relaties onderkennen en accurater een suggestie voor
					u doen. Binnen Twitter gebruiken wij uw Tweets of te scannen, uw
					reTweets en uw Persoonlijke berichten. U kunt ten alle tijden de
					toegang tot uw Twitter account verbieden door dit account te
					ontkoppelen.</p>
				<div class="btn-group">
					<button type="button" class="btn btn-success">Twitter
						account gekoppeld</button>
					<button type="button" class="btn btn-success dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Account ontkoppellen</a></li>
						<li class="divider"></li>
						<li><a href="#">Account verwisselen</a></li>
					</ul>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="col-md-3 well" style="margin: 5px;" id="twitter">
				<h4 style="color: darkblue">Twitter</h4>
				<p>Door uw Twitter account aan Kornuit te koppelen biedt u ons
					de mogelijkheid om een diepgaande analyse te doen naar uw
					tweetgedrag en dat van uw vrienden. Hierdoor kunnen wij
					onderliggende relaties onderkennen en accurater een suggestie voor
					u doen. Binnen Twitter gebruiken wij uw Tweets of te scannen, uw
					reTweets en uw Persoonlijke berichten. U kunt ten alle tijden de
					toegang tot uw Twitter account verbieden door dit account te
					ontkoppelen.</p>
				<div class="btn-group">
					<button type="button" class="btn btn-danger">Twitter
						account niet gekoppeld</button>
					<button type="button" class="btn btn-danger dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Nieuw account koppelen</a></li>
					</ul>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>
	<div class="panel-footer">
		<a href="privacy">Privacyverklaring</a>
	</div>

</body>
</html>