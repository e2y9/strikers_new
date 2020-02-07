package commandline;

import java.util.Scanner;

public class TopTrumpsCLIApplication {
	
	public static boolean startGame;
	
	public static void gameLoop(GameLogic game) {
		game.displayRoundNumber();
		game.displayUserTopCard();
		game.playRound();
		game.transferCards();
		// try to move dealer cards (if there is a game winner & deckPileWaiting = true), deal cards
		game.lostPlayer();
		game.displayRoundWinners();

	}
	
	public static void gameMenu() {
		
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
		} else if (choiceNum == 2) {
			startGame = true;
		} else { System.out.println("Please choose either 1 or 2");}
		}
	}

	public static void main(String[] args) {
				
		boolean userWantsToQuit = false;
		
		while (userWantsToQuit == false) 
		{
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
			
			System.out.print("\n- - - - - - - - - - -\nWelcome to Top Trumps!\n- - - - - - - - - - -\n");
			System.out.println("\n- - - - - - - - - - -\nPlayer List:");
			for(int i =0; i<players.getPlayers().size(); i++) 
			{
				System.out.println(players.getPlayers().get(i).getName());	
			}
			System.out.println("- - - - - - - - - - -\n");
			
			if (startGame == false) 
			{
			gameMenu();
			}
			startGame = false;
			game.shuffleDeck();
			game.dealDeck();
			System.out.println("\n= = = = = = = = = = =\nWelcome to a new game!\n= = = = = = = = = = =\n");
			
		while(game.lastPlayerLeft() == false)
		 {
			gameLoop(game);
		 }
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
			}
		}
		System.out.println("\n- - - - - - - - - - -\nThanks for playing!\n- - - - - - - - - - -\n");
			

	}

}
