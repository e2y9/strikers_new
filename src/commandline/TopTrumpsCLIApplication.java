package commandline;

import java.io.IOException;
import java.util.Scanner;

public class TopTrumpsCLIApplication {
	
	public static boolean startGame = false;
	static Connect c=new Connect();
	
	public static void gameLoop(GameLogic game) {
		game.displayRoundNumber();
		game.displayUserTopCard();
		game.playRound();
		game.transferCards();
		// try to move dealer cards (if there is a game winner & deckPileWaiting = true), deal cards
		game.lostPlayer();
		game.displayRoundWinners();
	}
	
	public static void gameMenu(){
		
//		TestLog test = new TestLog();
		while (startGame == false)
		{
		System.out.println("\n= = = = = = = = = = =");
		System.out.println("      GAME MENU      ");
		System.out.println("1 : See Game Stats");
		System.out.println("2 : Play a Game");
		System.out.println("= = = = = = = = = = =\n");
		System.out.println("\nEnter 1 or 2:\n");
		Scanner s = new Scanner(System.in);
		String choice = s.nextLine();
		int choiceNum = Integer.parseInt(choice);
		if (choiceNum == 1) {
			System.out.println("Here are the game stats: ");
			displayStats();
		} else if (choiceNum == 2) {
			startGame = true;
		} else { System.out.println("Please choose either 1 or 2");}
		}
	}

	public static void main(String[] args) throws IOException {
				
		boolean userWantsToQuit = false;	
		int numberOfPlayers = 5; //Added this line
		GameLogic game = new GameLogic(numberOfPlayers);//edited this line
		while (userWantsToQuit == false) 
		{		
			System.out.print("\n- - - - - - - - - - -\nWelcome to Top Trumps!\n- - - - - - - - - - -\n");
			System.out.println("\n- - - - - - - - - - -\nPlayer List:");
			for(int i =0; i<numberOfPlayers; i++) //edited this line 
			{
				System.out.println(game.getPlayersList().getPlayers().get(i).getName());	//edited this line 
			}
			System.out.println("- - - - - - - - - - -\n");
			
			if (startGame == false) 
			{
			gameMenu();
			}
			startGame = false;
//			game.setGameId(4 + 1);  //read from data base
			GameLogic.incGameID();
			System.out.println("GameID: " + GameLogic.getGameId());
			game.shuffleDeck();
			game.dealDeck();
			System.out.println("\n= = = = = = = = = = =\nWelcome to a new game!\n= = = = = = = = = = =\n");
			
		while(game.lastPlayerLeft() == false)
		 {
			gameLoop(game);
		 }
//		connect(game); //added this line but comment it while testing because it will give a server connection error
		System.out.println("Do you want to Quit the game? Type 0 to Quit, or 1 to continue");
		Scanner s2 = new Scanner(System.in);
		String quit = s2.nextLine();
		int quitNum = Integer.parseInt(quit);
		if (quitNum == 0) 
		{
			userWantsToQuit = true;
		} 
		else 
		{ 
			userWantsToQuit = false; 
			game = new GameLogic(numberOfPlayers);
		}
		}
		System.out.println("\n- - - - - - - - - - -\nThanks for playing!\n- - - - - - - - - - -\n");
	}
	//added this method 
	public static void connect(GameLogic game)
	{
		
		c.gamerecords(GameLogic.getGameId(), game.getTotalNumberOfDraws(), game.getGameWinnerID(), game.getRoundNumber(), game.getAllPlayersID(), game.getnumberOfRoundsWonByEachPlayer());
	}
	
	public static void displayStats()
	{
		System.out.println("-----------------------------------------------");
		System.out.println("Total number of Games played: " + c.numberOfGames());
		System.out.println("Number of Human wins: " + c.numberofHumanWin());
		System.out.println("Number of AI wins: " + c.numberofAIwin());
		System.out.println("Average number of Draws: " + c.averageOfDraws());
		System.out.println("Longest Game: " + c.maxRoundInGame() + " rounds");
	}
}
