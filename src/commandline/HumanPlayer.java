package commandline;

import java.util.Scanner;

public class HumanPlayer implements Player {

	  private String name;
	  private DeckOfCards playerDeck;
	  private int playerID;
	  private int numberOfRoundsWon = 0;
	  private int numberOfDraws = 0;
	  private boolean lost = false;


	  public HumanPlayer(String name) 
	  {
		  this.name = name;
		  playerDeck = new DeckOfCards();
	  }
	  

	@Override
	  public int chooseCategory() {
		  int catNum = 0;
		  Scanner s1 = new Scanner(System.in);
		  boolean inputValidity = false;
		  do
		  {
			  System.out.println("\n\n- - - - - - - - - -\n\nWhich category would you like to choose?\n");
			  System.out.println("1 - Intelligence");
			  System.out.println("2 - Speed");
			  System.out.println("3 - Strength");
			  System.out.println("4 - Agility");
			  System.out.println("5 - Combat");
			  System.out.println("");
			  if(s1.hasNextInt())
			  {
				  catNum = s1.nextInt();
				  catNum--;
				  if(catNum >= 0 && catNum <=4)
				  {
					  inputValidity = true;
				  }
				  else
				  {
					  System.out.println("Please enter a number between 1 and 5.");
					  inputValidity = false;
				  }
			  	}
			  else
			  {
				  System.out.println("Please enter a number between 1 and 5.");
				  inputValidity = false;
				  s1.nextLine();
			  }
			}while(inputValidity==false);
			  
		  return catNum;
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
	
	public String getName() {
		  return name;
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