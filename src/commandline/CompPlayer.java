/*Group Name: Strikers*/
/*This method implements Player interface and is used in GameLogic to Create an AI or Computer Player*/
package commandline;

import java.util.ArrayList;

public class CompPlayer implements Player {

	 private String name; 	//name variable stores the name of the player.
	 private DeckOfCards playerDeck; //This object is used to store the cards a player has.
	 private int playerID; //Each player is assigned a playerID which is stored in database.
	 private int numberOfRoundsWon = 0; //This variable is used as the counter to count the total number of rounds won by the player.
	 private boolean lost = false; //This variable is used as a flag which helps in determine if the player still has cards left.

/*Constructor for the CompPlayer Class*/
	public CompPlayer(String name) 
	  {		  
		  this.name = name; 
		  playerDeck = new DeckOfCards(); //Creates a playerDeck as the DeckOfCards object.
	  }
	
	/*Getter Method for the name of the player.*/
	  public String getName() {
		  return name;
	  }

	  /*Below method is used to Choose the category if the player is a Computer Player. The difficulty level is set to hard, so the computer player
	   * picks a category which has the highest value for the drawn card.*/
	@Override
	public int chooseCategory()
	  {
		  // store the card values from the player's 0 card in an array (value 0 = intelligence, 1 = agility ..)
		  int[] tempValues = new int[5];
		  for (int i = 0; i < 5; i++)
		  {
			  tempValues[i] = playerDeck.getTopCardValue(i);
		  }

		  int max = tempValues[0];	
		 // set starting index position to be 1 higher than max's [0] position in the array
		 // loop through array until end, comparing max to other numbers and storing highest number as max
		  for (int i = 1; i < tempValues.length; i++) 
		  {
			 if (tempValues[i] > max) 
			 {
				 max = tempValues[i];
			 }
		 }
		 // now check which value (still in order 0-4, intelligence to combat) matches max
		 if (max == tempValues[0])
		 {
			 return 0;
		 }
		 else if (max == tempValues[1])
		 {
			 return 1;
		 } 
		 else if (max == tempValues[2]) 
		 {
			 return 2;
		 } 
		 else if (max == tempValues[3]) 
		 {
			 return 3;
		 } 
		 else if (max == tempValues[4]) 
		 {
			 return 4;	 
		 }
		 else 
		 { 
			 return 99;
		 } // return 99 (out of bounds) in result of an error 
	  }
	
	/*This method returns the topCard the player has in the deck. If there is no card in the deck it simply returns an empty string*/
	public String topCard()
	{
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
	
	/*Getter Method which returns the playerDeck Object reference.*/
	 public DeckOfCards getPlayerDeck() {
			return playerDeck;
		}
	
	 /*Getter Method for the playerID*/
	public int getPlayerID() {
		return playerID;
	}

	/*Setter Method for the playerID*/
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/*Getter Method for numberOFRoundsWon*/
	public int getNumberOfRoundsWon() {
		return numberOfRoundsWon;
	}

	/*This method increments the number of rounds.*/
	public void incNumberOfRoundsWon() {
		this.numberOfRoundsWon++;
	}

	/*Getter Method for lost flag*/
	public boolean getLost() {
		return lost;
	}

	/*Setter Method for the lost flag*/
	public void setLost(boolean lost) {
		this.lost = lost;
	}
}