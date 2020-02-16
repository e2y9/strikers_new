/* Group Name: Strikers */

/*This method implements the Player interface and is used to create a Human Player.*/
package commandline;

import java.util.Scanner;

public class HumanPlayer implements Player {

	  private String name; //Private string variable to store the name of the Player.
	  private DeckOfCards playerDeck; //DeckOfCards variable to store the Deck for the Player.
	  private int playerID; //Variable to store the Player's ID
	  private int numberOfRoundsWon = 0; //Variable to store the total number of rounds won by the Player
	  private boolean lost = false; //This boolean variable is used as a Flag that determines if the player has Lost the game i.e. player has 0 Cards in the deck.

	  /*Constructor for the HumanPlayer takes a string (name) as in Argument*/
	  public HumanPlayer(String name) 
	  {
		  this.name = name;
		  playerDeck = new DeckOfCards(); //Creating a new DeckOfCards Object for the playerDeck variable.
	  }
	  
	  /*Below method is used for choosing the category This method is called from GameLogic*/
	@Override
	  public int chooseCategory() {
		  int catNum = 0;
		  Scanner s1 = new Scanner(System.in); //Scanner to scan the category chosen by the human Player.
		  boolean inputValidity = false; //This boolean variable is used as a flag to determine if the user input is correct.
		  do
		  {	  /*Below statements are used to prompt the user to choose the category.*/
			  System.out.println("\n\n- - - - - - - - - -\n\nWhich category would you like to choose?\n");
			  System.out.println("1 - Intelligence");
			  System.out.println("2 - Speed");
			  System.out.println("3 - Strength");
			  System.out.println("4 - Agility");
			  System.out.println("5 - Combat");
			  System.out.println("");
			  if(s1.hasNextInt()) //Check if the user input has a integer.
			  {
				  catNum = s1.nextInt();
				  catNum--; //Decremented, because the indexing starts for 0.
				  if(catNum >= 0 && catNum <=4) //Check if the input is between 1 and 5.
				  {
					  inputValidity = true; //if the input is between 1 and 5 set the input validity flag to true.
				  }
				  else
				  {
					  /*If the "if" condition is false display the following message.*/
					  System.out.println("Please enter a number between 1 and 5.");
					  inputValidity = false; //Set the input validity flag to false.
				  }
			  	}
			  else
			  { 
				  /*If the entered value is not an integer display the following message*/
				  System.out.println("Please enter a number between 1 and 5.");
				  inputValidity = false; //Set input validity flag to false.
				  s1.nextLine(); //Clear the Scanner for the next input.
			  }
			}while(inputValidity==false); //If the input validity flag is false loop back and ask for the input again otherwise break the loop.
			  
		  return catNum; //Return the Chosen category.
	  }
	
	/*Method to return the top card in the deck as a String.*/
	public String topCard()
	{
		/*If there is no card in the deck return an empty string, otherwise return the top card*/
		String result = "";
		if(playerDeck.getDeck().isEmpty()) 
		{
			result = ""; 
		}
		else
		{
			result = playerDeck.getTopCard(); 
		}
		return result;
	}
	
	//Getter Method for the name of the Player.
	public String getName() {
		  return name;
	  }

	//Getter method to access the player's deck from other classes.
	public DeckOfCards getPlayerDeck() {
		return playerDeck;
	}

	//Getter method to access the player's ID from other classes.
	public int getPlayerID() {
		return playerID;
	}
	
	//Setter method to set the player's ID from other classes.
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	//Getter Method for the Number of Rounds Player has won.
	public int getNumberOfRoundsWon() {
		return numberOfRoundsWon;
	}
	
	//Method to increment the number of Rounds Player has won.
	public void incNumberOfRoundsWon() {
		this.numberOfRoundsWon++;
	}

	//Getter Method to access the lost flag.
	public boolean getLost() {
		return lost;
	}

	//Setter Method for the lost flag.
	public void setLost(boolean lost) {
		this.lost = lost;
	}	
}