<!DOCTYPE html>
<html>
<%
String token = null;
if(null!=request.getSession().getAttribute("fbTokenValue")){
	 token = (String) request.getSession().getAttribute("fbTokenValue");
}

%>
    <head>
    <!-- Author 1: Mark Willem Johan Stroeven -->
<!-- Author 2: Tim Ijpenga -->
<!-- Date: April 3 -->
        <title>Kornuit | Suggesties</title>
        <meta charset="ISO-8859-1">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="code.js"></script>
        <script>
            var is_loading = false;
            var cloned_item;
        </script>
        <style>
            @-webkit-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
            @-moz-keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
            @keyframes fadeIn { from { opacity:0; } to { opacity:1; } }
            .fade-in {
            opacity:0;  /* make things invisible upon start */
            -webkit-animation:fadeIn ease-in 1;  /* call our keyframe named fadeIn, use animattion ease-in and repeat it only 1 time */
            -moz-animation:fadeIn ease-in 1;
            animation:fadeIn ease-in 1;
            -webkit-animation-fill-mode:forwards;  /* this makes sure that after animation is done we remain at the last keyframe value (opacity: 1)*/
            -moz-animation-fill-mode:forwards;
            animation-fill-mode:forwards;
            -webkit-animation-duration:1s;
            -moz-animation-duration:1s;
            animation-duration:1s;
            }
            .fade-in.one {
            -webkit-animation-delay: 0.7s;
            -moz-animation-delay: 0.7s;
            animation-delay: 0.7s;
            }
            .animated { 
            -webkit-animation-duration: 2s; 
            animation-duration: 2s; 
            -webkit-animation-fill-mode: both; 
            animation-fill-mode: both; 
            -webkit-animation-timing-function: ease-out; 
            animation-timing-function: ease-out; 
            -webkit-animation-play-state: paused;  /* Chrome, Safari, Opera */
            animation-play-state: paused;
            } 
            @-webkit-keyframes fadeOutRightBig { 
            0% { 
            opacity: 1; 
            -webkit-transform: translateX(0); 
            } 
            100% { 
            opacity: 0; 
            -webkit-transform: translateX(500px); 
            } 
            } 
            @keyframes fadeOutRightBig { 
            0% { 
            opacity: 1; 
            transform: translateX(0); 
            } 
            100% { 
            opacity: 0; 
            transform: translateX(500px); 
            } 
            } 
            .fadeOutRightBig { 
            -webkit-animation-name: fadeOutRightBig; 
            animation-name: fadeOutRightBig; 
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-default" style="margin: 0px;">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Kornuit</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="index">Home <span class="sr-only">(current)</span></a></li>
                        <li class=""><a href="mijnkornuit">Mijn kornuit</a></li>
                        <li class="active"><a href="suggestie">Suggesties</a></li>
                        <li class="">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Instellingen <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Notificaties</a></li>
                                <li><a href="#">Vrienden</a></li>
                                <li><a href="#">Overig</a></li>
                                 <li><a href="accounts">Accounts</a></li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
        <div class="panel panel-info">
            <div class="panel-heading">Suggesties</div>
            <div class="panel-body">
                <div id="item_container" class="box fade-in one">
                    <div id="animated-example" class="animated fadeOutRightBig">
                        <div class="col-md-3 well" style="margin: 0 auto; display: block;">
                            
                            <h4 id="sug_head" style="color: darkblue">Suggestie $user-friend</h4>
                           <br> </br>
                            <div id="container_picture">
                            <img src="http://static1.squarespace.com/static/52cd93f2e4b0f7eaa440bd2f/t/53f11166e4b020d5c7fc813a/1389303414935/placeholder.gif" id="picture_content"/>
                            </div>
                           <br> </br>
                            <p id="sug_content">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sagittis pharetra vehicula. Morbi quis vehicula odio. Cras eget nunc ullamcorper, placerat mauris vitae, lobortis magna. Quisque eu purus nec lectus congue condimentum. Maecenas porttitor iaculis neque, tincidunt pulvinar nulla rhoncus posuere. Sed volutpat metus eget turpis fermentum, mollis dignissim diam ullamcorper
                            </p>
                            <button id="json" type="button" class="btn btn-info" onclick="play_animation()">Nieuwe suggestie</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    
    <script>
    function play_animation() {

        is_loading = true;

        var item = document.getElementById("animated-example");
        item.addEventListener('webkitAnimationEnd', function() {
            this.style.webkitAnimationName = '';
            console.log('Animation has ended');
			var rem = document.getElementById("picture_content");
			rem.parentNode.removeChild(rem);
            cloned_item = item.cloneNode(true);
            
 
            cloned_item.style.webkitAnimationPlayState = "paused"
            cloned_item.style.animationPlayState = "paused"

            var x = document.getElementById("item_container");

            x.removeChild(item);
			
            load_new_item();
        }, false);

        var currentItem = document.getElementById('animated-example');
        currentItem.style.animationPlayState = "running";
        currentItem.style.webkitAnimationPlayState = "running";

        if (is_loading) {
            var loadImage = document.createElement("img");
            loadImage.setAttribute("src", "http://2.bp.blogspot.com/-T3whmFR9AHc/UeJp7HjiaXI/AAAAAAAAHoY/9aoe-xvtfFI/s1600/blue.gif");
            loadImage.setAttribute("id", "loader");
            document.getElementById("item_container").appendChild(loadImage);
            is_loading = false;
        }

    }

    function load_new_item() {
        var x = document.getElementById("item_container");
        makeCorsRequest();
        if (!is_loading) {
            document.getElementById("item_container").removeChild(document.getElementById("loader"));
        }




    }

    // Create the XHR object.
    function createCORSRequest(method, url) {

        console.log('IKBEN HIER');
        var xhr = new XMLHttpRequest();
        if ("withCredentials" in xhr) {
            console.log('IK DOE DEZER');
            // XHR for Chrome/Firefox/Opera/Safari.
            xhr.open(method, url, true);
        } else if (typeof XDomainRequest != "undefined") {
            // XDomainRequest for IE.
            xhr = new XDomainRequest();
            xhr.open(method, url);
        } else {
            console.log('IIK GEEF NIIIIIKS');
            // CORS not supported.
            xhr = null;
        }
        console.log('IK GA NU TERUGGGEVENS SWA');
        return xhr;
    }



    // Make the actual CORS request.
    function makeCorsRequest() {
        // All HTML5 Rocks properties support CORS.

    	
    	var url = 'http://localhost:8080/Kornuit/rest/SuggestionProvider/';
        url = url.concat(<%=token%>);
		var finalUrl = url;
		console.log(finalUrl);
		
        var xhr = createCORSRequest('GET', finalUrl);
        if (!xhr) {
            alert('CORS not supported');
            return;
        }

        // Response handlers.
        xhr.onload = function() {
            var x = document.getElementById("item_container");
            var text = xhr.responseText;
            var json = JSON.parse(text.toString());

            var a = document.createElement("img");
            a.setAttribute("id", "picture_content");
            a.setAttribute("src", json.payload);
            a.setAttribute("style", "position: relative;");


            x.appendChild(cloned_item);
            document.getElementById("container_picture").appendChild(a);
            document.getElementById("sug_head").innerHTML = json.name;
            
            console.log(json.payload);


        };

        xhr.onerror = function() {
            alert('Woops, there was an error making the request.');
        };

        xhr.send();
    }
    </script>
</html>