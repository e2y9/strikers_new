package commandline;

import java.util.Scanner;

public class TopTrumpsCLIApplication {
	
	public static boolean startGame;
	
	public static void gameLoop(GameLogic game) {
		game.displayRoundNumber();
		game.displayUserTopCard();
		game.playRound();
		game.transferCards();
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
		Players players;
		Player p1;
		Player p2;
		Player p3;
		Player p4;
		Player p5;

		GameLogic game;
		
		while (userWantsToQuit == false) 
		{
			players = new Players();
			p1 = new HumanPlayer("User");
			p2 = new CompPlayer("AI 1");
			p3 = new CompPlayer("AI 2");
			p4 = new CompPlayer("AI 3");
			p5 = new CompPlayer("AI 4");

			players.addPlayer(p1);
			players.addPlayer(p2);
			players.addPlayer(p3);
			players.addPlayer(p4);
			players.addPlayer(p5);
			
			game = new GameLogic(players);
			
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
			if (game.lastPlayerLeft() == true) 
			{
			break;	
			}
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
