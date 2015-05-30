<!DOCTYPE html>
<html>
<head>

<!-- Author 1: Mark Willem Johan Stroeven -->
<!-- Author 2: Tim Ijpenga -->
<!-- Date: April 3 -->

<title>Kornuit | Home</title>
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

<script src="http://localhost:8080/Kornuit/code.js"></script>
<style>
@
-webkit-keyframes fadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
@
-moz-keyframes fadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
@
keyframes fadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
.fade-in {
	opacity: 0; /* make things invisible upon start */
	-webkit-animation: fadeIn ease-in 1;
	/* call our keyframe named fadeIn, use animattion ease-in and repeat it only 1 time */
	-moz-animation: fadeIn ease-in 1;
	animation: fadeIn ease-in 1;
	-webkit-animation-fill-mode: forwards;
	/* this makes sure that after animation is done we remain at the last keyframe value (opacity: 1)*/
	-moz-animation-fill-mode: forwards;
	animation-fill-mode: forwards;
	-webkit-animation-duration: 1s;
	-moz-animation-duration: 1s;
	animation-duration: 1s;
}

.fade-in.one {
	-webkit-animation-delay: 0.7s;
	-moz-animation-delay: 0.7s;
	animation-delay: 0.7s;
}
</style>



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
					<li class="active"><a href="index">Home <span
							class="sr-only">(current)</span></a></li>
					<li><a href="mijnkornuit">Mijn kornuit</a></li>
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

		<div class="panel-heading">

			<%@ include file="facebookHeader.jsp"%>


		</div>
		<div class="panel-body">
			<div class="col-md-3 well">

				<div class="col-md-12 column input-group">
					<span class="input-group-addon" id="sizing-addon2"></span> <input
						type="text" class="form-control" placeholder="Username"
						aria-describedby="sizing-addon2" onclick="expand_username_info()"
						onblur="collapse_username_info()">
				</div>
				<br></br>
				<div class="col-md-12 column input-group">
					<span class="input-group-addon" id="sizing-addon2"></span> <input
						type="password" class="form-control" placeholder="Wachtwoord"
						aria-describedby="sizing-addon2" onclick="expand_password_info()"
						onblur="collapse_password_info()">
				</div>
				<br></br>
				<div class="col-md-12 column input-group">
					<span class="input-group-addon" id="sizing-addon2"></span> <input
						type="text" class="form-control" placeholder="Mail adres"
						aria-describedby="sizing-addon2" onclick="expand_mail_info()"
						onblur="collapse_mail_info()">
				</div>
				<br></br>
				<div class="col-md-12 column input-group">
					<span class="input-group-addon" id="sizing-addon2"></span> <input
						type="text" class="form-control" placeholder="Secret question"
						aria-describedby="sizing-addon2" onclick="expand_question_info()"
						onblur="collapse_question_info()">
				</div>
				<br></br>
				<center>
					<button type="button" class="btn btn-info">Wijzigen</button>
				</center>
			</div>


			<div id="username" class="col-md-3 alert alert-info" role="alert"
				style="display: none;">
				<h4>Username</h4>
				<p>U gebruikt uw gebruikersnaam om in te loggen op Kornuit. U
					kunt dit ten alle tijdne wijzigen. Let er wel op dat als u dit
					wijzigt u opnieuw uw social media accounts dient te koppelen.</p>
			</div>


			<div id="password" class="col-md-3 alert alert-info" role="alert"
				style="display: none;">
				<h4>Password</h4>
				<p>U gebruikt uw password om op Kornuit in te loggen. U kunt dit
					ten alle tijden wijzigen zonder verdere acties te ondernemen.</p>
			</div>


			<div id="mail" class="col-md-3 alert alert-info" role="alert"
				style="display: none;">
				<h4>Mail</h4>
				<p>U gebruikt uw e-mail om suggesties van kornuit te ontvangen.
					Ook wordt uw mail gebruikt om service berichten naar toe te sturen
					of om gegevens over uw account op te vragen, bijvoorbeeld het
					aanvragen van een nieuw wachtwoord. U kunt dit ten alle tijden
					wijzigen, echter dient u wel een verificatiecode in te voeren
					tijden het wijzigingsproces.</p>
			</div>


			<div id="question" class="col-md-3 alert alert-info" role="alert"
				style="display: none;">
				<h4>Question</h4>
				<p>U gebruikt uw geheime vraags om uw identiteit te verifieren
					zoals bij het aanvragen van een nieuw wachtwoord. Vertel niemand
					het antwoord van uw geheime vraag. U kunt dit ten alle tijden
					wijzigen, echter dient u wel een verificatiecode in te voeren
					tijden het wijzigingsproces.</p>
			</div>


		</div>
	</div>
	</div>

</body>
</html>