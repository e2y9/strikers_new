/* Everything in this file is to be written to a file called toptrumps.log */
package commandline;

public class TestLog {
	
	private Players playersList;
	private DeckOfCards allCards;
	private Player winnerOfRound;
	private DeckOfCards commonPile;
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
	p1.getPlayerDeck().displayDeck();
	System.out.println("\n--------------------\n");
	// • The contents of the communal pile when cards are added or removed from it
	Card testCard = new Card(); // *** need to add values to each card or they'll be null ***
	Card testCard2 = new Card();
	// // // // card values added // // // //
	game.getCommonPile().addCard(testCard); // *** need to add a getCommonPile method to GameLogic ***
	game.getCommonPile().addCard(testCard2);
	game.getCommonPile().displayDeck();
	game.getCommonPile().removeCard(testCard2);
	// • The contents of the current cards in play (the cards from the top of the user’s deck and the computer’s
	// deck(s))
	System.out.println(p1.topCard());
	System.out.println(p2.topCard());
	System.out.println(p3.topCard());
	System.out.println(p4.topCard());
	System.out.println(p5.topCard());
	// • The category selected and corresponding values when a user or computer selects a category

	// • The contents of each deck after a round

	// • The winner of the game

	}
	}
