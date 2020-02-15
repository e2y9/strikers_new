package commandline;

import java.io.IOException;
import java.util.Scanner;

public class TopTrumpsCLIApplication {
	
	public boolean startGame = false;
	Connect c=new Connect();
	
	
	public void gameLoop(GameLogic game) {
		game.displayRoundNumber();
		game.displayUserTopCard();
		game.playRound();
		game.transferCards();
		game.lostPlayer();
		game.displayRoundWinners();
	}
	
	public void gameMenu(){
		
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
			if(s.hasNextInt())
			{
				int choiceNum= s.nextInt();
				if (choiceNum == 1) 
				{
					System.out.println("Here are the game stats: ");
					displayStats();
				} 
				else if (choiceNum == 2)
				{
					startGame = true;
				} 
				else 
				{
					System.out.println("Please choose either 1 or 2");
				}
			}
			else
			{
				 System.out.println("Please choose either 1 or 2");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		TopTrumpsCLIApplication app = new TopTrumpsCLIApplication();		
		boolean userWantsToQuit = false;	
		int numberOfPlayers = 5; //Added this line
//		GameLogic game = new GameLogic(numberOfPlayers);//edited this line
		while (userWantsToQuit == false) 
		{		
			GameLogic game = new GameLogic(numberOfPlayers);
			System.out.print("\n- - - - - - - - - - -\nWelcome to Top Trumps!\n- - - - - - - - - - -\n");
			System.out.println("\n- - - - - - - - - - -\nPlayer List:");
			for(int i =0; i<numberOfPlayers; i++) //edited this line 
			{
				System.out.println(game.getPlayersList().getPlayers().get(i).getName());	//edited this line 
			}
			System.out.println("- - - - - - - - - - -\n");
			
			if (app.startGame == false) 
			{
			app.gameMenu();
			}
			app.startGame = false;
			game.setGameId(app.c.numberOfGames()+1);  //read from data base
			System.out.println("GameID: " + game.getGameId());
			game.shuffleDeck();
			game.dealDeck();
			System.out.println("\n= = = = = = = = = = =\nWelcome to a new game!\n= = = = = = = = = = =\n");
			
			while(game.lastPlayerLeft() == false)
			 {
				app.gameLoop(game);
			 }
			System.out.println("Winner ID: "+ game.getGameWinnerID());
			app.connect(game); //added this line but comment it while testing because it will give a server connection error
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
//				game = null;
			}
		}
		System.out.println("\n- - - - - - - - - - -\nThanks for playing!\n- - - - - - - - - - -\n");
	}
	//added this method 
	public void connect(GameLogic game)
	{
		
		c.gamerecords(game.getGameId(), game.getTotalNumberOfDraws(), game.getGameWinnerID(), game.getRoundNumber(), game.getAllPlayersID(), game.getnumberOfRoundsWonByEachPlayer());
	}
	
	public void displayStats()
	{
		System.out.println("-----------------------------------------------");
		System.out.println("Total number of Games played: " + c.numberOfGames());
		System.out.println("Number of Human wins: " + c.numberofHumanWin());
		System.out.println("Number of AI wins: " + c.numberofAIwin());
		System.out.printf("Average number of Draws: %.2f\n", c.averageOfDraws());
		System.out.println("Longest Game: " + c.maxRoundInGame() + " rounds");
	}
}
