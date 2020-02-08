package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
	
	private Players playersList;
	private DeckOfCards allCards;
	private Player winnerOfRound;
	private DeckOfCards drawPile = new DeckOfCards();
	private int totalNumberOfDraws = 0;
	private static int gameId = 0;
	private int roundNumber = 1;
	private boolean roundDrawn = false;
	private boolean deckPileWaiting = false;
	

	public GameLogic(Players players)
	{
		this.playersList = players;
		allCards = new DeckOfCards();
		try
		{
	    	FileReader fr = new FileReader("src\\commandline\\final_MarvelDeck.txt");
	    	loadCards(fr);
	    }
		catch (FileNotFoundException e) 
		{
	      System.out.print("File not found.");
	    }
		winnerOfRound = playersList.getPlayers().get(0);
	
		gameId++;
		
	}
	
	 public void loadCards(FileReader fr) 
	 {
		    Scanner s = new Scanner(fr);
		    // use nextLine to skip the first line
		    s.nextLine();
		    int counter = 0;
		    while (counter < 40)
		    { 
		    	Card c = new Card();
		        String line = s.nextLine();
		        String[] values = line.split(" ");
		        String name = values[0];
		        int intelligence = Integer.parseInt(values[1]);
		        int speed = Integer.parseInt(values[2]);
		        int strength = Integer.parseInt(values[3]);
		        int agility = Integer.parseInt(values[4]);
		        int combat = Integer.parseInt(values[5]);
		        allCards.addCard(c);
		        // store card objects in the deck, increment a counter for position
		       
		        this.allCards.getDeck().get(counter).fillCard(name, intelligence, speed, strength, agility, combat);
		        counter++;
		    }
		    s.close();
		}
	 
	public void dealDeck()
	{
		int deckPosition = 0;
		int count = allCards.getDeck().size() / playersList.getPlayers().size(); //2
		while(count>0 && deckPosition < allCards.getDeck().size())
		{
			for(int i=0; i<playersList.getPlayers().size(); i++)
			{
				playersList.getPlayers().get(i).getPlayerDeck().addCard(allCards.getDeck().get(deckPosition++));
			}
			count--;
		}
		
	}
	
	
	public void shuffleDeck()
	{
		Random randomInteger = new Random();
		for(int i=0; i<allCards.getDeck().size(); i++)
		{
			int randomIndexPosition = randomInteger.nextInt(allCards.getDeck().size());
			Card temp = allCards.getDeck().get(randomIndexPosition);
			allCards.getDeck().set(randomIndexPosition, allCards.getDeck().get(i));
			allCards.getDeck().set(i, temp);
		}
	}
	
	public void roundWinner()
	{
		int category = winnerOfRound.chooseCategory();
		int temp = 0;
		if(lastPlayerLeft() == false)
		{
			for(int i=0; i<playersList.getPlayers().size(); i++)
			{
				if(playersList.getPlayers().get(i).getLost() == false)
				{
					for(int j=i+1 ; j<playersList.getPlayers().size(); j++)
					{
						if(playersList.getPlayers().get(j).getLost() == false)
						{
							if((this.getPlayersTopCardValue(i, category) > this.getPlayersTopCardValue(j, category)) && (this.getPlayersTopCardValue(i, category)>temp))
							{
								temp = this.getPlayersTopCardValue(i, category);
								winnerOfRound = playersList.getPlayers().get(i);
								roundDrawn = false;
							}
							
							else if(this.getPlayersTopCardValue(i, category) < this.getPlayersTopCardValue(j, category) && this.getPlayersTopCardValue(j, category)>temp)
							{
								temp = this.getPlayersTopCardValue(i, category);
								winnerOfRound = playersList.getPlayers().get(j);
								roundDrawn = false;
							}
							else if(this.getPlayersTopCardValue(i, category) == this.getPlayersTopCardValue(j, category))
							{
								System.out.println("\nThe round was a draw. Cards added to Draw Deck.");
								totalNumberOfDraws++;
								roundDrawn = true;
							}
						}
					}
				}
				
			}
		}
		if (roundDrawn == false)
		{
		winnerOfRound.incNumberOfRoundsWon();
		}
	}
	

	
	
	public void whoChooseCategory()
	{
		winnerOfRound.chooseCategory();
	}
	
	public void printDeck()
	{
		allCards.displayDeck();
	}
	
	public String getWinnerOfRound()
	{
		if (roundDrawn == false)
		{
		return winnerOfRound.getName() + " won the round with this card:\n" +  winnerOfRound.getPlayerDeck().getTopCard();
		}
		else
		{
			return " ";
		}
	}
	
	// for TestLog
	public void setPlayerWinnerOfRound(Player p)
	{
		this.winnerOfRound = p;
	}
	
	public Player getPlayerWinnerOfRound() 
	{
		return this.winnerOfRound;
	}
	
	public void displayAllPlayersTopCard()
	{
		int sizeOfList = playersList.getPlayers().size();
		for(int i=0; i<sizeOfList; i++)
		{
			if(playersList.getPlayers().get(i).getLost() == false)
			{
				System.out.println("\n" + playersList.getPlayers().get(i).getName());
				System.out.println(playersList.getPlayers().get(i).getPlayerDeck().getTopCard());
		
			}
		}
	}
	
	public void displayUserTopCard()
	{
			if(playersList.getPlayers().get(0).getLost() == false) 
			{
				System.out.println("\n" + playersList.getPlayers().get(0).getName() + "'s current card:");
				System.out.println(playersList.getPlayers().get(0).getPlayerDeck().getTopCard());
				displayUserDeckSize();
			}
		}
	
	public void displayRoundWinners() 
	{
		if (roundDrawn == false)
		{
		 System.out.println("\n- - - - - - - - - - -");	
		 for(int i =0; i<playersList.getPlayers().size(); i++)
			{
				System.out.println(playersList.getPlayers().get(i).getName() + " has won " + playersList.getPlayers().get(i).getNumberOfRoundsWon() + " rounds");
			}
		 System.out.println("- - - - - - - - - - -");
		 System.out.println(totalNumberOfDraws + " rounds drawn");
		 System.out.println("- - - - - - - - - - -\n");
		} else
		{
			System.out.println(" ");
			deckPileWaiting = true;
		}
	}
	
	public void displayUserDeckSize() 
	{
		System.out.printf("%n" + playersList.getPlayers().get(0).getName() + " has " + playersList.getPlayers().get(0).getPlayerDeck().getDeck().size() + " cards in their deck");
	}

	
	public void playRound()
	{
		roundWinner();
		System.out.println("\n\n" + getWinnerOfRound());
	}
	
	public void lostPlayer()
	{
		for(int i=0; i<playersList.getPlayers().size(); i++)
		{
			if(playersList.getPlayers().get(i).getPlayerDeck().getDeck().isEmpty() == true)
			{
				playersList.getPlayers().get(i).setLost(true);
			}
		}
	}

	public Players getPlayersList() {
		return playersList;
	}
	

	public void transferCards()
	{
		int playersListSize = playersList.getPlayers().size();
		int tempSize = 0;
		DeckOfCards temp = new DeckOfCards();
		for(int i=0; i<playersListSize; i++)
		{
			if(playersList.getPlayers().get(i).getLost() == false)
			{
				temp.addCard(playersList.getPlayers().get(i).getPlayerDeck().getDeck().get(0));
				playersList.getPlayers().get(i).getPlayerDeck().getDeck().remove(0);
			}
		}
		
		this.shuffleHand(temp);
		tempSize = temp.getDeck().size();
		
		// added if (rD == false)
		if (roundDrawn == false)
		{
		for(int i=0; i<playersListSize; i++) //5
		{
			if(playersList.getPlayers().get(i).equals(winnerOfRound)) 
			{
				for(int j=0; j<tempSize; j++) //5
				{
					playersList.getPlayers().get(i).getPlayerDeck().getDeck().add(temp.getDeck().get(0));
					temp.getDeck().remove(0);
				}
			}
			
		}
		} 
		else if (roundDrawn == true)
		{ 
			for(int k=0; k<tempSize; k++)
			{
				if (temp.getDeck().get(k) != null)
				{
					drawPile.addCard(temp.getDeck().get(k));
				}
			}
		}
		dealDrawPile();
	}
	
	public void shuffleHand(DeckOfCards cards)
	{
		Random randomInteger = new Random();
		for(int i=0; i<cards.getDeck().size(); i++)
		{
			int randomIndexPosition = randomInteger.nextInt(cards.getDeck().size());
			Card temp = cards.getDeck().get(randomIndexPosition);
			cards.getDeck().set(randomIndexPosition, cards.getDeck().get(i));
			cards.getDeck().set(i, temp);
		}
	}

	public int getTotalNumberOfDraws() {
		return totalNumberOfDraws;
	}

	public static int getGameId() {
		return gameId;
	}
	
	public boolean getRoundDrawn()
	{
		return roundDrawn;
	}
	
	public int getPlayersTopCardValue(int index,int category)
	{
		return this.playersList.getPlayers().get(index).getPlayerDeck().getTopCardValue(category);
	}
	
	public boolean lastPlayerLeft()
	{
		boolean result = false; 
		int playerCount=0;
		for(int i=0; i<playersList.getPlayers().size(); i++)
		{
			if(playersList.getPlayers().get(i).getLost() == false)
			{
				playerCount++;
			}
		}
		if(playerCount==1)
		{
			System.out.println("\n- - - - - - - - - - -\nThe winner is: " 
		+ winnerOfRound.getName() + "\n- - - - - - - - - - -\n");
			result = true;
		}
		return result;
	}
	
	public void displayWinnerDeck()
	{
		for(int i=0; i<winnerOfRound.getPlayerDeck().getDeck().size(); i++)
		{
			System.out.println(winnerOfRound.getPlayerDeck().getDeck().get(i).toString());
		}
	}
	
	public DeckOfCards getDrawPile() 
	{
		return drawPile;
	}
	
	public void addToDrawPile(Card card) 
	{
		this.drawPile.addCard(card);
	}
	
	public void removeFromDrawPile(Card card) 
	{
		for (Card c : drawPile.getDeck()) 
		{
			if (card == c) 
			{
				drawPile.getDeck().remove(c);
				break;
			}
		}
	}
	
	public void displayRoundNumber() {
		System.out.println("Round #" + roundNumber + " = = = = = =");
		roundNumber++;
	}
	
//	public void addToDrawPile()
//	{
//		if (roundDrawn == true)
//		{
//		for(int i=0; i<playersList.getPlayers().size(); i++)
//		{
//			if(playersList.getPlayers().get(i).getLost() == false)
//			{
//			System.out.println(playersList.getPlayers().get(i).getPlayerDeck().getDeck().get(0)));
//			drawPile.addCard(playersList.getPlayers().get(i).getPlayerDeck().getDeck().get(0));
//			}
//		}
//		}
//	}
//	
	public void dealDrawPile()
	{
		if(roundDrawn == false && deckPileWaiting == true)
		{
			for(int i=0; i<drawPile.getDeck().size(); i++)
			{
				winnerOfRound.getPlayerDeck().getDeck().add(drawPile.getDeck().get(i));
			}
		}
		deckPileWaiting = false;
	}
	
	// muted this method while it waits for the winnerCard attribute
//	public ArrayList<String> getWinnerAttribute()
//	{
//		ArrayList<String> result = new ArrayList<String>(); 
//		
//		if(roundDrawn == false)
//		{
//			result.add(winnerOfRound.getName());
//			result.add(winnerCard.getName());
//			result.add(Integer.toString(winnerCard.getIntelligence()));
//			result.add(Integer.toString(winnerCard.getSpeed()));
//			result.add(Integer.toString(winnerCard.getStrength()));
//			result.add(Integer.toString(winnerCard.getAgility()));
//			result.add(Integer.toString(winnerCard.getCombat()));
//		}
//		
//		
//		return result;
//	}
	
	public ArrayList<String> getHumanPlayerTopCardAttributes()
	{
		ArrayList<String> result = new ArrayList<String>();
		if(playersList.getPlayers().get(0).getLost() == false)
		{
			result.add(playersList.getPlayers().get(0).getPlayerDeck().getDeck().get(0).getName());
			result.add(Integer.toString(playersList.getPlayers().get(0).getPlayerDeck().getDeck().get(0).getIntelligence()));
			result.add(Integer.toString(playersList.getPlayers().get(0).getPlayerDeck().getDeck().get(0).getSpeed()));
			result.add(Integer.toString(playersList.getPlayers().get(0).getPlayerDeck().getDeck().get(0).getStrength()));
			result.add(Integer.toString(playersList.getPlayers().get(0).getPlayerDeck().getDeck().get(0).getAgility()));
			result.add(Integer.toString(playersList.getPlayers().get(0).getPlayerDeck().getDeck().get(0).getCombat()));
		}
		
		return result;
	}
	public ArrayList<Integer> getNumberOfCards()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0; i<playersList.getPlayers().size(); i++)	
		{
			result.add(playersList.getPlayers().get(i).getPlayerDeck().getNumberOfCards());
		}
		return result;				
	}
	

}

