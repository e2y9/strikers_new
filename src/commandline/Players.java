/* Group Name: Strikers */

/*This class is used to store the players playing the game using an ArrayList.*/
package commandline;

import java.util.ArrayList;

public class Players {
	
	private ArrayList<Player> players; //Create a players variable to store the ArrayList of Player object.
	private int playerID =1; //Variable to assign the PlayerID to each Player.
	
	//Constructor for the Players Class.
	public Players()
	{
		players = new ArrayList<Player>(); //Creating a new Player object list for the players variable.
	}
	
	//Method to add the Player to the list.
	public void addPlayer(Player p)
	{
		players.add(p); //Add Player to the list
		p.setPlayerID(playerID++); //Assign the playerID to the added player. First player Gets a PlayerID 1.
	}
	
	//Getter Method to access the players list from other classes.
	public ArrayList<Player> getPlayers() {
		return players;
	}

	//Below method returns the Name of all the players in the players list as a String. 
	public String allPlayersName()
	{
		//If the list is empty it returns a "There are no Players", otherwise returns the name of all Players.
		String result = "";
		if(players.isEmpty() == false)
		{
			for(Player element:players)
			{
				result += element.getName() + "\n";
			}
		}
		else
		{
			result = "There are no Players";
		}
		
		return result;
	}
	
	//This method returns the value of card for the passed playerIndex and category.
	public int getCardValue(int playerIndex, int category)
	{
		return players.get(playerIndex).getPlayerDeck().getTopCardValue(category);
	}
}
