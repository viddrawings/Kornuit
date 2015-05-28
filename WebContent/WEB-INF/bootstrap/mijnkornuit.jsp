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

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="panel panel-info">
		<div class="panel-heading"><%@ include file="facebookHeader.jsp"%></div>

		<div class="panel-body">
			<div class="col-md-3 well" style="margin: 5px;">
				<h4 style="color: darkblue">Groepen</h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Vestibulum sagittis pharetra vehicula. Morbi quis vehicula odio.
					Cras eget nunc ullamcorper, placerat mauris vitae, lobortis magna.
					Quisque eu purus nec lectus congue condimentum. Maecenas porttitor
					iaculis neque, tincidunt pulvinar nulla rhoncus posuere. Sed
					volutpat metus eget turpis fermentum, mollis dignissim diam
					ullamcorper</p>
					<br></br>
					<a href="newgroup"><button type="submit" form="login" class="btn btn-info col-md-10">Maak een nieuwe groep aan</button></a>
					<br></br>
					<a href="managegroups"><button type="submit" form="login" class="btn btn-info col-md-10">Wijzig een bestaande groep</button></a>
			</div>

			<div class="col-md-3 well" style="margin: 5px;">
				<h4 style="color: darkblue">Afspraken</h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Vestibulum sagittis pharetra vehicula. Morbi quis vehicula odio.
					Cras eget nunc ullamcorper, placerat mauris vitae, lobortis magna.
					Quisque eu purus nec lectus congue condimentum. Maecenas porttitor
					iaculis neque, tincidunt pulvinar nulla rhoncus posuere. Sed
					volutpat metus eget turpis fermentum, mollis dignissim diam
					ullamcorper</p>
					<br></br>
					<a href="manageafspraak"><button type="submit" form="login" class="btn btn-info col-md-10">Wijzig een bestaande afspraak</button></a>
			</div>
		</div>
	</div>





</body>
</html>