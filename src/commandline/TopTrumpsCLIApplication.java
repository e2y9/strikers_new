/* Group Name: Strikers */
/*This class contains the main method for the Command Line Version.*/

package commandline;

import java.io.IOException;
import java.util.Scanner;

public class TopTrumpsCLIApplication {
	
	public boolean startGame = false; //This variable is used as a Flag which is set to true if the user selects "Start Game". 
//	Connect c=new Connect(); //Create a Connect object which is used for the database.
	
	public static void main(String[] args) throws IOException {
		
		TopTrumpsCLIApplication app = new TopTrumpsCLIApplication(); //To use the non-static methods in the main method.		
		boolean userWantsToQuit = false; //Used as a flag if the user wants to quit.
		boolean inputValidity = false; //Flag to check the validitiy of user input
		int numberOfPlayers = 5;  //Number of Players including the 1 human player. initially set to 5 as per the course work requirement
		Scanner s2 = new Scanner(System.in);
		while (userWantsToQuit == false) //Loop until the userWantsToQuit = false.
		{		
			GameLogic game = new GameLogic(numberOfPlayers); //Creates the GameLogic object.
			/*Display welcome message and all players name*/
			System.out.print("\n- - - - - - - - - - -\nWelcome to Top Trumps!\n- - - - - - - - - - -\n");
			System.out.println("\n- - - - - - - - - - -\nPlayer List:");
			for(int i =0; i<numberOfPlayers; i++)  
			{
				System.out.println(game.getPlayersList().getPlayers().get(i).getName());
			}
			System.out.println("- - - - - - - - - - -\n");
			
			if (app.startGame == false) 
			{
				app.gameMenu(); //If startGame = false execute the gameMenu method.
			}
			app.startGame = false;
//			game.setGameId(app.c.numberOfGames()+1);
			System.out.println("GameID: " + game.getGameId()); //Print the Game ID 
			game.shuffleDeck(); //Shuffle the deck
			game.dealDeck(); //Deal the Deck to the players
			System.out.println("\n= = = = = = = = = = =\nWelcome to a new game!\n= = = = = = = = = = =\n");
			
			while(game.lastPlayerLeft() == false) //play the game until there is a winner using the gameLoop method.
			 {
				app.gameLoop(game);
			 }
			System.out.println("Winner ID: "+ game.getGameWinnerID()); //Display winner name and ID.
//			app.connect(game); //Send the data to the database.
			/*Prompt user to play again or quit*/
			do {
				System.out.println("Do you want to Quit the game? Type 0 to Quit, or 1 to continue");
				/*Scan user input*/
				
				String quit = s2.nextLine();
		
				int quitNum = Integer.parseInt(quit);
				/*If user enters 0 exit the game, if 1 is entered display the Game Menu, otherwise prompt user for the 
				 * correct input and ask again*/
				if (quitNum == 0) 
				{
					userWantsToQuit = true; 
					inputValidity = true;
				} 
				else if(quitNum == 1)
				{ 
					userWantsToQuit = false; 
					inputValidity = true;
				}
				else
				{
					inputValidity = false;
					System.out.println("Please enter the correct value!");
				}
			}while(!inputValidity);
		}
		System.out.println("\n- - - - - - - - - - -\nThanks for playing!\n- - - - - - - - - - -\n");
	}

	public void gameLoop(GameLogic game) {
		
		game.displayRoundNumber(); //Display the Number of Rounds
		System.out.println("\n- - - - - - - - - - - - - - -");
		for(int i=0; i<game.getNumberOfCards().size(); i++)
		{
			System.out.println("Number of cards " + game.getPlayersList().getPlayers().get(i).getName() +" has : " + game.getNumberOfCards().get(i));
		}
		System.out.println("- - - - - - - - - - - - - - -\n");
		game.displayUserTopCard(); // Display the top card of the humanPlayer
		game.playRound(); //Play the round.
		game.transferCards(); //Transfer the cards to winner of draw pile if the round was draw.
		System.out.println("------------------");
		System.out.println("Draw Pile: " + game.getNumberOfCardsCommunalPile()); //Display the number of Cards in Draw Pile.
		System.out.println("------------------\n");
		
		game.lostPlayer(); //Remove the players, who has 0 cards left.
		game.displayRoundWinners(); //Display the winner of the round.
	}
	
	public void gameMenu(){
		
		while (startGame == false) 
		{
			//Display the game menu.
			System.out.println("\n= = = = = = = = = = =");
			System.out.println("      GAME MENU      ");
			System.out.println("1 : See Game Stats");
			System.out.println("2 : Play a Game");
			System.out.println("= = = = = = = = = = =\n");
			System.out.println("\nEnter 1 or 2:\n");
			Scanner s = new Scanner(System.in);		
			//Scan for the user input.
			if(s.hasNextInt())
			{
				int choiceNum= s.nextInt();
				if (choiceNum == 1)  //If user enters 1 display the Game Stats.
				{
					System.out.println("Here are the game stats: ");
					displayStats();
				} 
				else if (choiceNum == 2) //If user enters 2 set the start game flag to true.
				{
					startGame = true;
				} 
				else  //otherwise display the message "Please enter either 1 or 2".
				{
					System.out.println("Please enter either 1 or 2");
				}
			}
			else //If the user enters something else than the integer display the message "Please enter either 1 or 2".
			{
				 System.out.println("Please enter either 1 or 2");
			}
		}
	}
	
	public void connect(GameLogic game)
	{
		
	//	c.gamerecords(game.getGameId(), game.getTotalNumberOfDraws(), game.getGameWinnerID(), game.getRoundNumber(), game.getAllPlayersID(), game.getnumberOfRoundsWonByEachPlayer());
	}
	
	public void displayStats()
	{
	//	System.out.println("-----------------------------------------------");
	//	System.out.println("Total number of Games played: " + c.numberOfGames());
	//	System.out.println("Number of Human wins: " + c.numberofHumanWin());
	//	System.out.println("Number of AI wins: " + c.numberofAIwin());
	//	System.out.printf("Average number of Draws: %.2f\n", c.averageOfDraws());
	//	System.out.println("Longest Game: " + c.maxRoundInGame() + " rounds");
	}
}
