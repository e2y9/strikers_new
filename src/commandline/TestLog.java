/* Everything in this file is to be written to a file called toptrumps.log */
package commandline;

import java.util.Random;

public class TestLog {
	
	private Players playersList;
	private DeckOfCards allCards;
	private Player winnerOfRound;
	private DeckOfCards drawPile;
	private int totalNumberOfDraws = 0;
	private static int gameId = 0;
	
	
	public static void gameLoop(GameLogic game) {
		game.displayRoundNumber();
		game.displayUserTopCard();
		game.playRound();
		game.transferCards();
		game.lostPlayer();
		game.displayRoundWinners();
	}
	

		public TestLog() {
			Players players = new Players();
			Player p1 = new HumanPlayer("User");
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
	System.out.println("\nAI 1 DECK:\n");
	p2.getPlayerDeck().displayDeck();
	System.out.println("\nAI 2 DECK:\n");
	p3.getPlayerDeck().displayDeck();
	System.out.println("\nAI 3 DECK:\n");
	p4.getPlayerDeck().displayDeck();
	System.out.println("\nAI 4 DECK:\n");
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
	System.out.println("\n--------------------\n");

	
	// • The contents of the current cards in play (the cards from the top of the user’s deck and the computer’s
	// deck(s))
	System.out.println("\nUSER TOP CARD:\n");
	System.out.println(p1.topCard());
	System.out.println("\nAI 1 TOP CARD:\n");
	System.out.println(p2.topCard());
	System.out.println("\nAI 2 TOP CARD:\n");
	System.out.println(p3.topCard());
	System.out.println("\nAI 3 TOP CARD:\n");
	System.out.println(p4.topCard());
	System.out.println("\nAI 4 TOP CARD:\n");
	System.out.println(p5.topCard());
	System.out.println("\n--------------------\n");
	
	// • The category selected and corresponding values when a user or computer selects a category
	
	System.out.println("AI 1 TOP CARD:");
	System.out.println(p2.topCard());
	int displayCategory = p2.chooseCategory();
	int userValue = p1.getPlayerDeck().getTopCardValue(displayCategory);
	int ai2Value = p3.getPlayerDeck().getTopCardValue(displayCategory);
	int ai3Value = p4.getPlayerDeck().getTopCardValue(displayCategory);
	int ai4Value = p5.getPlayerDeck().getTopCardValue(displayCategory);
	
	// category value index starts at 0, category choice starts at 1
	// so +=1 will show correct category number to screen
	displayCategory += 1;
	
	System.out.println("\nChosen category by AI 1 :" + displayCategory);
	if (displayCategory == 1) 
	{
	System.out.println("Category chosen is: intelligence");	
	} else if (displayCategory == 2)
	{
		System.out.println("Category chosen is: speed");	
	} else if (displayCategory == 3)
	{
		System.out.println("Category chosen is: strength");	
	} else if (displayCategory == 4)
	{
		System.out.println("Category chosen is: agility");	
	} else if (displayCategory == 5)
	{
		System.out.println("Category chosen is: combat");	
	} else 
	{
		System.out.println("No category chosen");
	}
	System.out.println("\nOther users' values for same category:\n");
	System.out.println("User: " + userValue);	
	System.out.println("AI 2: " + ai2Value);	
	System.out.println("AI 3: " + ai3Value);	
	System.out.println("AI 4: " + ai4Value);	
	System.out.println("\n--------------------\n");
	
	System.out.println("\nOther users' top cards:\n");
	System.out.println("USER TOP CARD:");
	System.out.println(p1.topCard());
	System.out.println("AI 2 TOP CARD:");
	System.out.println(p3.topCard());
	System.out.println("AI 3 TOP CARD:");
	System.out.println(p4.topCard());
	System.out.println("AI 4 TOP CARD:");
	System.out.println(p5.topCard());
	
	// • The contents of each deck after a round
	
	// a drawn round is unsuitable for testing
	// so the test while loop will play rounds until one is not a draw
	boolean roundComplete = false;
	while (roundComplete = false)
	{
	System.out.println("Playing a round ...");
	System.out.println("...");
	System.out.println("..");
	System.out.println(".");
	// for the test round, set the category chooser to AI 1
	game.setPlayerWinnerOfRound(p2);
	gameLoop(game);
	if (game.getRoundDrawn() == false)
	{
		roundComplete = true;
	}
	}
	
	System.out.println("\n\nDeck contents for each player after a round:\n");
	
	System.out.println("\nUSER DECK:\n");
	p1.getPlayerDeck().displayDeck();
	System.out.println("\nAI 1 DECK:\n");
	p2.getPlayerDeck().displayDeck();
	System.out.println("\nAI 2 DECK:\n");
	p3.getPlayerDeck().displayDeck();
	System.out.println("\nAI 3 DECK:\n");
	p4.getPlayerDeck().displayDeck();
	System.out.println("\nAI 4 DECK:\n");
	p5.getPlayerDeck().displayDeck();
	System.out.println("\n--------------------\n");
	
	// • The winner of the game
	


	}
	}
