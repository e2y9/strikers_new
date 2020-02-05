package commandline;

import java.util.ArrayList;

public class Players {
	
	private ArrayList<Player> players;
	private static int playerID =1;
	public Players()
	{
		players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player p)
	{
		players.add(p);
		p.setPlayerID(playerID++);;
	}
	
	public void removePlayer(Player p)
	{
		players.remove(p);
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public String allPlayersName()
	{
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
	
	public int getCardValue(int playerIndex, int category)
	{
		return players.get(playerIndex).getPlayerDeck().getTopCardValue(category);
	}
}
