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
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 400px}
    
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    .marvel_jpg {
    	cursor: pointer;
    	width: 100%;
    	height: 100%;
    }

    .centered {
	  position: absolute;
	  cursor: pointer;
	  top: 100px;
	  left: 50%;
	  transform: translate(-50%, -50%);
	  font-size: 25px;
	  color:white;
	}
	
	table, th, td {
	  border: 1px solid black;
	  font-size: 30px;
	  text-align: center;
	}
  </style>
</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	<nav class="navbar">
			  <div class="container-fluid">
				<div class="navbar-header">
				  <a class="navbar-brand navbar-left" href="http://localhost:7777/toptrumps">
				  <img src="https://upload.wikimedia.org/wikipedia/commons/0/04/MarvelLogo.svg" width="80" height="50" alt="Logo">
				  </a>
				</div>
			  </div>
		</nav>
		
		<div class="container">
			<div class="stats">
                    <img class="marvel_jpg" src="https://upload.wikimedia.org/wikipedia/commons/0/04/MarvelLogo.svg" onclick="window.location.href = 'http://localhost:7777/toptrumps';" width="300" height="300" alt="Card image cap">
                    <div class="centered" onclick="window.location.href = 'http://localhost:7777/toptrumps';">Return to Main Menu </div>
                   
                   <table style="width:100%">
					  <tr>
					    <td>Total number of Games played</td>
					    <td><bold id="stats1"></bold></td>
					  </tr>
					  <tr>
					    <td>Number of Human wins:</td>
					    <td><bold id="stats2"></bold></td>
					  </tr>
					  <tr>
					    <td>Number of AI wins:</td>
					    <td><bold id="stats3"></bold></td>
					  </tr>
					  <tr>
					    <td>Average number of Draws:</td>
					    <td><bold id="stats4"></bold></td>
					  </tr>
					  <tr>
					    <td>Longest Game:</td>
					    <td><bold id="stats5"></bold></td>
					  </tr>
					</table>
                </div>
         </div>    
		
			
		<footer class="container-fluid text-center">
			<h3>Designed by Strikers</h3>
			<p>Ali Utku Demir - Ashwin Bhatnagar - Emmet Young - Pranav Dadhich</p>
		</footer>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {

				getStats();
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				//helloJSONList();
				//helloWord("Student");
				
			}
			
			
			function getStats() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getStats"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                     for(var i=0; i<responseText.length; i++) {
                     	withDecimals=responseText[i];
                     	if(parseFloat(responseText[i]) % 1 != 0)
                     	{
                     	var withDecimals=parseFloat(responseText[i]).toFixed(2);
                     	}	
                        $("#stats"+(i+1)).text(withDecimals);
                    
                    }

                };
                xhr.send();
            }
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>