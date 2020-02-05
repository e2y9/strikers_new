package commandline;

interface Player { 
	  public String getName();
	  public int chooseCategory();
	  public DeckOfCards getPlayerDeck();
	  public void setPlayerID(int playerID);
	  public int getPlayerID();
	  public int getNumberOfRoundsWon();
	  public void incNumberOfRoundsWon();
	  public boolean getLost();
	  public void setLost(boolean lost);
	  public void incNumberOfDraws();
	  
}


