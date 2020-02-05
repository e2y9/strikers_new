/*This method implements Player interface, Choose Category method is still incomplete*/
package commandline;

import java.util.ArrayList;

public class CompPlayer implements Player {

	 private String name;
	 private DeckOfCards playerDeck;
	 private int playerID;
	 private int numberOfRoundsWon = 0;
	 private int numberOfDraws = 0;
	 private boolean lost = false;


	public CompPlayer(String name) 
	  {		  
		  this.name = name;
		  playerDeck = new DeckOfCards();
	  }
	

	  public String getName() {
		  return name;
	  }


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
		 // this method is called by whoChoosesCategory, so the return value is passed back to that method to set category
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
	
	 public DeckOfCards getPlayerDeck() {
			return playerDeck;
		}

	public int getPlayerID() {
		return playerID;
	}


	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}


	public int getNumberOfRoundsWon() {
		return numberOfRoundsWon;
	}


	public void incNumberOfRoundsWon() {
		this.numberOfRoundsWon++;
	}


	public boolean getLost() {
		return lost;
	}


	public void setLost(boolean lost) {
		this.lost = lost;
	}
	
	public void incNumberOfDraws()
	{
		numberOfDraws++;
	}
}