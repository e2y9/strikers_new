package commandline;

public class TopTrumpsCLIApplication {

	public static void main(String[] args) {
		boolean userWantsToQuit = false;
		
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
		for(int i =0; i<players.getPlayers().size(); i++)
		{
			System.out.println("PLayer ID " + players.getPlayers().get(i).getPlayerID());
		}

		
		
		GameLogic game = new GameLogic(players);
		
		 System.out.print("Welcome to Top Trumps!\n");
		 
		 game.shuffleDeck();
		 game.dealDeck();
//		 game.printDeck();
//		 System.out.println("Player 1 deck");
		 p1.getPlayerDeck().displayDeck();
//		 game.displayAllPLayersTopCard();
		 
		 
		 
		 while(game.lastPlayerLeft() == false)
		 {
			 if(userWantsToQuit == false)
			 {
				 game.displayAllPLayersTopCard();
//				 System.out.println("GameID  " +GameLogic.getGameId());
				
				 
//				 p1.getPlayerDeck().displayDeck();
				 game.playRound();
				 game.transferCards();
				 game.lostPlayer();
//				 game.displayAllPLayersTopCard();
				 System.out.println("Winner  deck");
				 game.displayWinnerDeck();
				 for(int i =0; i<players.getPlayers().size(); i++)
					{
						System.out.println("Player Name " + players.getPlayers().get(i).getName() + " won " + players.getPlayers().get(i).getNumberOfRoundsWon() + " rounds");
					}
			 }
			 else
			 {
				 System.out.println("Thank you for Playing the game.");
//				 break;
			 }
		 }
			

	}

}
