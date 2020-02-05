/* Everything in this file is to be written to a file called toptrumps.log */
package commandline;

public class TestLog {
	
	private Players playersList;
	private DeckOfCards allCards;
	private Player winnerOfRound;
	private DeckOfCards drawPile;
	private int totalNumberOfDraws = 0;
	private static int gameId = 0;
	

		public TestLog() {
			Players players = new Players();
			Player p1 = new HumanPlayer("Ashwin");
			Player p2 = new CompPlayer("AI 1");
			Player p3 = new CompPlayer("AI 2");
			Player p4 = new CompPlayer("AI 3");
			Player p5 = new CompPlayer("AI 4");
			  
			players.addPlayer(p1);
			players.addPlayer(p2);
			players.addPlayer(p3);
			players.addPlayer(p4);
			players.addPlayer(p5);
		
			GameLogic game = new GameLogic(players);		
		  

	// In addition to the functionality described above, you should implement the following to allow for program debugging
	// when in command line mode only. When the program is started, if a ‘-t’ flag is set on the command line, then the
	// program should write out an extensive log of its operation to a ‘toptrumps.log’ file in the same directory as the
	// program is run, e.g.:
	// • java -jar TopTrumps.jar -c -t
	// If a toptrumps.log file already exists, your program should overwrite that file. Your program should print the
	// following information to that file, separated by a line containing dashes “----------“ at the appropriate times as
	// mentioned below:

	// • The contents of the complete deck once it has been read in and constructed
			
	game.dealDeck();
	game.printDeck();
	System.out.println("\n--------------------\n");
	
	// • The contents of the complete deck after it has been shuffled
	
	game.shuffleDeck();
	game.printDeck();
	System.out.println("\n--------------------\n");
	
	// • The contents of the user’s deck and the computer’s deck(s) once they have been allocated. Be sure to
	// indicate which the user’s deck is and which the computer’s deck(s) is.
	
	System.out.println("\nUSER DECK:\n");
	p1.getPlayerDeck().displayDeck();
	System.out.println("\nCOMP1 DECK:\n");
	p2.getPlayerDeck().displayDeck();
	System.out.println("\nCOMP2 DECK:\n");
	p3.getPlayerDeck().displayDeck();
	System.out.println("\nCOMP3 DECK:\n");
	p4.getPlayerDeck().displayDeck();
	System.out.println("\nCOMP4 DECK:\n");
	p5.getPlayerDeck().displayDeck();
	System.out.println("\n--------------------\n");
	
	// • The contents of the communal pile when cards are added or removed from it
	// // // // card values added // // // //
	
	Card testCard = new Card();
	Card testCard2 = new Card();
	testCard.fillCard("Mr.Test", 1, 5, 16, 41, 2);
	testCard2.fillCard("Mrs.Test", 17, 9, 15, 23, 49);
	
	game.addToDrawPile(testCard);
	game.addToDrawPile(testCard2);
	System.out.println("\nDraw Pile:\n");
	game.getDrawPile().displayDeck();
	game.getDrawPile().removeCard(testCard);
	System.out.println("\nDraw Pile (1 card removed):\n");
	game.getDrawPile().displayDeck();
	game.getDrawPile().removeCard(testCard2);

	
	// • The contents of the current cards in play (the cards from the top of the user’s deck and the computer’s
	// deck(s))
	System.out.println("\nUSER TOP CARD:\n");
	System.out.println(p1.topCard());
	System.out.println("\nCOMP1 TOP CARD:\n");
	System.out.println(p2.topCard());
	System.out.println("\nCOMP2 TOP CARD:\n");
	System.out.println(p3.topCard());
	System.out.println("\nCOMP3 TOP CARD:\n");
	System.out.println(p4.topCard());
	System.out.println("\nCOMP4 TOP CARD:\n");
	System.out.println(p5.topCard());
	
	// • The category selected and corresponding values when a user or computer selects a category
	
	// display the user topcard again and print the int category choice
	// same for computer
	// compare values, print all card values to screen
	
	// p1.chooseCategory(); //v2 with no input
	// p2.chooseCategory(); // special test versions that don't change the rest of the code?

	// • The contents of each deck after a round
	
	// start a game loop, but have it run the first round separately before entering the loop
	// print the contents of each deck after that round

	// • The winner of the game
	
	// this might happe automatically when the game code loop is run

	}
	}
