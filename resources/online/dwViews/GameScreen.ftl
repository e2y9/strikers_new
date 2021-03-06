<!DOCTYPE html>
<html lang="en">

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
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
	align: left;
	position: relative;
	padding-top: 0px;
	padding-left: 0px;
	padding-right: 0px;
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
      cursor: point;
    
    }
    
    .nextRound input {
    	font-size: 25px;
    }
    
    .winner {
    	font-size: 25px;
    }
    
    .list-group button{
    	width: 100%;
    	border: 0px solid;
  		color: white; /* White text */  
  		cursor: pointer; /* Pointer/hand icon */
		display: block; /* Make the buttons appear below each other */
    }
    
    .list-group-item{

    }
    
    .button1{
      	background-color: red;
    }
    
    .button2{
      	background-color: green;
    }
    
    .button3{
      	background-color: blue;
    }
    
    .button4{
      	background-color: grey;
    }
    
    .button5{
      	background-color: black;
    }
    table, th, td {
	  border: 1px solid black;
	  font-size: 25px;
	  text-align: center;
	}

  </style>
	</head>
	<!-- Display navigation bar -->
    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	<nav class="navbar">
			  <div class="container-fluid">
				<div class="navbar-header">
				  <a class="navbar-brand navbar-left" href="http://localhost:7777/toptrumps">
				  <img src="https://upload.wikimedia.org/wikipedia/commons/0/04/MarvelLogo.svg" width="140" height="50" alt="Logo">
				  </a>
				</div>
			  </div>
			</nav>
			
	<div class="container">
	<!-- Divide the screen into three, one row and three columns -->
        <div class="row">
            <div class="col-sm-3">
                    <h4>Your card</h4>
				<!-- Displaying card details -->
                <div>
                    <img class="card_jpg" src="https://upload.wikimedia.org/wikipedia/commons/0/04/MarvelLogo.svg" width="255" height="300" alt="Marvel_image">
                    <div class="cardFeatures" align="middle">
                        <h4 class="cardName"></h4>
                    </div>
               	
                    <ul class="list-group">
                            <button class="list-group-item button1" type='button' id='AttVal1' onclick="javascript:checkHumanCat('1')" />                 	  
 							<button class="list-group-item button2" type='button' id='AttVal2' onclick="javascript:checkHumanCat('2')" />                      
							<button class="list-group-item button3" type='button' id='AttVal3' onclick="javascript:checkHumanCat('3')" />                      
							<button class="list-group-item button4" type='button' id='AttVal4' onclick="javascript:checkHumanCat('4')" />
							<button class="list-group-item button5" type='button' id='AttVal5' onclick="javascript:checkHumanCat('5')" />
                    </ul>
                </div>
            </div>


            <div class="col-md-5">
            	<!-- Displaying number of rounds -->
            	<div class="showRounds">
		        <h5>Number of rounds: <bold id="numberOfRounds"></bold></h5>
		        </div>
		        <!-- Displaying in case draw happens-->
		        <div class="drawCase">
				<h3>It is a draw. Last round's winner will choose the category.</h3>
				</div> 
		    	<!-- Displaying human turn-->			
				<div class="chosenCat">
				<h3>Your turn. Choose a category.</h3>
				<h3>Chosen Category: <bold id="chosenCategory"></bold> </h3>
				</div>     
                <!-- Displaying in case human player lost-->
                <div class="lostCase">
				<h3>You have lost.</h3>
				<h3 id="skipQuestion">Do you want to skip to the end of the game or continue watching each round? </h3>
				</div> 
				<!-- Displaying in case human player lost-->
				<div align="center" class="winner">
				<h3>Winner is: <bold id="winnerPlayer"></bold> </h3>
				<input type="button" id="return" value="Return to Main Menu" onclick="window.location.href = 'http://localhost:7777/toptrumps';" />
				</div>      
                 
                <!-- Next round buttons-->
                <div align="center" class="nextRound">
                    <input type='button' id='showNextButton' value='Next Round' onclick='javascript:nextRound()' />
                    <input type='button' id='showSkipButton' value='Skip to the End' onclick='javascript:skipGame()' />
                </div>

                <!-- Winner Card-->
                <div align="center" class="wincard">
                    <img class="wincard_jpg" src="https://upload.wikimedia.org/wikipedia/commons/0/04/MarvelLogo.svg" width="250" height="300" alt="Winner_card_image">
                    <div class="wincardFeatures">
                        <h4 class="wincardName"></h4>
                	</div>
                    <ul class="list-group">
           				<li class="list-group-item">
                            <h4> Round Winner:  <bold id='winplayer'></bold>  </h4>      
                        </li>
                        <li class="list-group-item">
                            <h6> Intelligence: <text id='WinAttVal1'> </text> </h6>       
                        </li>
                        <li class="list-group-item">
                            <h6> Speed: <text id='WinAttVal2'> </text> </h6>       
                        </li>                        
                        <li class="list-group-item">
                            <h6> Strength: <text id='WinAttVal3'> </text> </h6>       
                        </li>
                        <li class="list-group-item">
                            <h6> Agility: <text id='WinAttVal4'> </text> </h6>       
                        </li>
                        <li class="list-group-item">
                            <h6> Combat: <text id='WinAttVal5'> </text> </h6>      
                        </li>
                    </ul>
                </div>
			</div>
			
            <div class="col-xs-3">
                
			<!-- Displaying number of cards remaining in game-->
           <div class="cardsOfPlayers">
                    <table style="width:100%">
					  <tr class="rowPlayer1">
					    <td>User</td>
					    <td><bold id="cardsOfPlayer1"></bold></td>
					  </tr>
					  <tr class="rowPlayer2">
					    <td>AI 1</td>
					    <td><bold id="cardsOfPlayer2"></bold></td>
					  </tr>
					  <tr class="rowPlayer3">
					    <td>AI 2</td>
					    <td><bold id="cardsOfPlayer3"></bold></td>
					  </tr>
					  <tr class="rowPlayer4">
					    <td>AI 3</td>
					    <td><bold id="cardsOfPlayer4"></bold></td>
					  </tr>
					  <tr class="rowPlayer5">
					    <td>AI 4</td>
					    <td><bold id="cardsOfPlayer5"></bold></td>
					  </tr>
					</table>
				</div>
				<!-- Displaying number of cards in communal pile-->
				<div class="comPile">
                    <h4>Cards in Communal Pile: <bold id="cardsCommunalPile"></bold></h4>
             	</div>

            </div>
        </div>

    </div>

    </div>

			<footer class="container-fluid text-center" onclick="window.location.href = 'https://github.com/e2y9/strikers_new';">
				<h3>Designed by Strikers</h3>
				<p>Ali Utku Demir - Ashwin Bhatnagar - Emmet Young - Pranav Dadhich</p>
			</footer>
    	<div class="container">

			<!-- Add your HTML Here -->
		
		</div>
		
		<script type="text/javascript">
		
		var notchosen;
		var gameWinner;
		
			// Method that is called on page load
			function initalize() {
			/* Hide buttons -except next round- and visualization for initialization*/
				$(".chosenCat").hide(1); 
                $(".drawCase").hide(1);
                $(".lostCase").hide(1);
                $(".winner").hide(1);
                $(".list-group :button").prop("disabled", true);
                getHumanCard(); //Bring Human card features
                getCardNumbersLeft(); //Bring number of cards remaining per player
                $("#skipToEnd").hide(1);
                $("#showSkipButton").hide(1);
                getRoundNumber(); //Bring number of rounds
                checkHumanWinner(); //To bring who will choose category in the first round
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				//helloJSONList();
				//helloWord("Student");
				
			}
			
			function nextRound() {
			if(notchosen=='true')
				{
 				alert("Category not chosen"); //alert if category is not chosen
 				}	
 			else
 				{
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/nextRound"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                	}
            		xhr.onload = function(e) {
 					
 					$(".chosenCat").hide(1); 
 					$(".drawCase").hide(1);
 					getHumanCard();
 					$(".list-group :button").prop("disabled", true);
 					getCardNumbersLeft();
 					getWinnerCard();
					getDrawpile();
					getRoundNumber();
					getGameWinner();
					checkHumanWinner();

			    	};
			    	
					xhr.send();
				}	
			}
			/*When Player lost, it should prompt a button to skip to end of game*/
			function skipGame() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/skipGame"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                	}
            		xhr.onload = function(e) {
 					getHumanCard();
 					getCardNumbersLeft();
 					getDrawpile();
 					getRoundNumber();
 					getGameWinner();
 					
			    	};
			    	xhr.send();
			    	
			}
			
			
			
					
			/*Bring number of cards Communal pile, Hide winner card and card choose class*/
			function getDrawpile() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getDrawpile"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
            xhr.onload = function(e) {
            		var responseText = xhr.response;
					$("#cardsCommunalPile").text(responseText);
					if(responseText != '0')
					{
					$(".wincard").hide(1);
					$(".chosenCat").hide(1);
					$(".drawCase").show(800);	
 					}
			    };
			xhr.send();
			}
			
			/*Hide winner and draw visualizations, get the game winner variable if there is a game winner according to model*/
			function getGameWinner() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getGameWinner"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
            xhr.onload = function(e) {
            		gameWinner = xhr.response;
            		if(gameWinner!= 'null')
            		{
            		
 					$("#winnerPlayer").text(gameWinner); 
					$(".winner").show(1);
 					$("#showSkipButton").hide(800);
 					$("#showNextButton").hide(800);
 					$(".drawCase").hide(800);
 					$(".wincard").hide(800);
 					$("#skipQuestion").hide(800);
 					write2database();
            		}
									
			    };
			    
			xhr.send();
			}
			
			/*Get number of rounds*/			
			function getRoundNumber() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundNumber"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
            xhr.onload = function(e) {
            		var responseText = xhr.response;
            		$("#numberOfRounds").text(responseText);
			    };
			    
			xhr.send();
			}
			
			/*Returns true if human has won the round, if so display choose category visualization*/
			function checkHumanWinner() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/checkHumanWinner"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
            xhr.onload = function(e) {
            		var responseText = xhr.response;
            		if(responseText == 'true')
            		{
            			$(".list-group :button").prop("disabled", false);
            			$("#chosenCategory").text("");
            			notchosen='true';
            			$(".chosenCat").show(800);
 					}
			    };
			xhr.send();
			}
			
			//Bring and display Human top card features
			function getHumanCard() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanCard"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $(".cardName").text(responseText[0]);
                    $(".card_jpg").attr("src", "https://raw.githubusercontent.com/e2y9/strikers_notes/master/card_pictures/"+responseText[0].toLowerCase()+".jpg")
                    var arrCat = ["Intelligence : ","Speed : ","Strength : ","Agility : ","Combat : "];
                    for(var i=1; i<responseText.length; i++) {
                        $("#AttVal"+(i)).text(arrCat[i-1] + responseText[i]);
                    }
                };
                xhr.send();
            }
            
			//Returns winner card of round
            function getWinnerCard() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getWinnerCard"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $(".wincard").show(800);
                    $("#winplayer").text(responseText[0]);
                    $(".wincardName").text(responseText[1]);
                    $(".wincard_jpg").attr("src", "https://raw.githubusercontent.com/e2y9/strikers_notes/master/card_pictures/"+responseText[1].toLowerCase()+".jpg")
                    //loop to bring features of card
					for(var i=2; i<responseText.length; i++) {
                        $("#WinAttVal"+(i-1)).text(responseText[i]);
                    }
                };
                xhr.send();
            }
            
            //Get number of remaining cards for each player 
			function getCardNumbersLeft() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCardNumbers"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                     for(var i=0; i<responseText.length; i++) { //Loop to return the number of cards per player 
                        $("#cardsOfPlayer"+(i+1)).text(responseText[i]);
                        
                    }
					for(var i=5; i>responseText.length; i--) { //Hide the players who are not playing
						$(".rowPlayer"+i).hide(1);
					}
                    if(responseText[0] == '0')
					{
					$(".lostCase").show(800);
					$("#skipToEnd").show(800);
					$("#showSkipButton").show(800);
					}
                };
                xhr.send();
            }
            
            //Display which category human chosen and pass it to the game model
            function checkHumanCat(category) {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/humanCat?category="+category); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // gets the chosen category back
					$("#chosenCategory").text(document.getElementById("AttVal"+category).innerHTML);
                    notchosen='false';
                };
                xhr.send();
            }
			
			//Write game records to database 
			function write2database() {
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/write2database"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                	}
            		xhr.onload = function(e) {
 					
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
		
		
		</body>
</html>