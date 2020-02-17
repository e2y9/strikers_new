<!DOCTYPE html>
<html lang="en">
<head>
  <title>TopTrump</title>
        	
	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

	<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
	<!-- <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css"> -->
	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	 <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/starter-template/">

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="starter-template.css" rel="stylesheet">
    
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="https://getbootstrap.com/docs/4.0/assets/js/vendor/popper.min.js"></script>
    <script src="https://getbootstrap.com/docs/4.0/dist/js/bootstrap.min.js"></script>

<!-- Styling for interface -->
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0px;
      border-radius: 0px;
	  min-height: 80px;
		padding-top: 0px;
		background-color: #555;
		
    }
	
	.navbar-brand {
	
	padding-top: 0px;
	padding-left: 0px;
	padding-right: 0px;
	}
	
	.dropdown-toggle
	{
	font-size: 30px;
	}
	
	#dropdown-item
	{
	font-size: 30px;
	}
	
	.nav-link
	{
	font-size: 30px;
	display: inline-block;
	color: #FFFFFF;
	}
	
	.navbar-nav {
    	display: inline-block;
		font-size: 30px;
		padding-top: 0px;
		background-color: #555;
		color: #FFFFFF;
		position: relative;
	}
    
    /* Set height of the grid */
    .row.content {
    height: 500px;
    padding-top: 200px;}
    
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }

    }
  </style>
</head>
<body>
<!-- Display navigation bar, "start new game" and "see statistics" options-->
<nav class="navbar">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand navbar-left" href="http://localhost:7777/toptrumps">
	  <img src="https://upload.wikimedia.org/wikipedia/commons/0/04/MarvelLogo.svg" width="140" height="60" alt="Logo">
	  </a>
    </div>
	<!-- Dropdown menu to choose number of players to play against -->
    <ul class="navbar-nav mr-auto" >
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Start Game</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <a class="dropdown-item" href="http://localhost:7777/toptrumps/game" onclick="newGame(5)">Against 4 players</a>
              <a class="dropdown-item" href="http://localhost:7777/toptrumps/game" onclick="newGame(4)">Against 3 players</a>
              <a class="dropdown-item" href="http://localhost:7777/toptrumps/game" onclick="newGame(3)">Against 2 players</a>
              <a class="dropdown-item" href="http://localhost:7777/toptrumps/game" onclick="newGame(2)">Against 1 players</a>
            </div>
            <a class="nav-link" href="http://localhost:7777/toptrumps/stats">Show Stats</a>
        </li>
    </ul>
  </div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-12 text-center"> 
      <h1>TopTrump for Marvel Heroes</h1>
    </div>
  </div>
</div>

<footer class="container-fluid text-center" onclick="window.location.href = 'https://github.com/e2y9/strikers_new';">
    <h3>Designed by Strikers</h3>
    <p>Ali Utku Demir - Ashwin Bhatnagar - Emmet Young - Pranav Dadhich</p>
</footer>


<script type="text/javascript">
	//Start the new game and pass number of player according to chosen option in dropdown menu
	function newGame(numberofplayers) {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/startnewgame?numberofplayers="+numberofplayers); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                };
                xhr.send();
            }

            // This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		</script>
</body>
</html>
