/* Group Name: Strikers */
/* This class controls the game's logic, containing methods for accessing/changing the main deck of cards. */
/* It also contains attributes used for tracking the game's stats (number of rounds/draws etc.) */

package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
	
	// Attributes for GameLogic
	
	// GameLogic class holds a list of players in the game
	private Players playersList;
	// Stores the initial deck of cards & the drawPile
	private DeckOfCards allCards;
	private DeckOfCards drawPile = new DeckOfCards();
	// boolean to signal when drawPile can be accessed
	private boolean deckPileWaiting = false;
	private Player winnerOfRound;
	private int totalNumberOfDraws = 0;
	private int gameId = 0;
	private int roundNumber = 1;
	private boolean roundDrawn = false;
	// Stores winnerCard for displaying it at end of each round
	private Card winnerCard;
	// Stores previousWinner for use in draw cases
	private Player previousWinner;
	private int gameWinnerID = 0;
	private int numOfPlayers;
	// Attributes for online game
	private int onlineCat;
	private int cat;

	
	// GameLogic constructor
	public GameLogic(int numOfPlayers)  
	{
		// Set number of players
		this.numOfPlayers = numOfPlayers; 
		// Initialise a new list of players and deck of cards
		playersList = new Players(); 
		allCards = new DeckOfCards();
		// Initialise a new FileReader to read card attributes from txt file
		try
		{
	    	FileReader fr = new FileReader("src\\commandline\\MarvelDeck.txt");
	    	loadCards(fr);
	    }
		// Catch exception if file is not found
		catch (FileNotFoundException e) 
		{
	      System.out.print("File not found.");
	    }	
		// Add the human user to the game
		playersList.addPlayer(new HumanPlayer("User"));	
		// Loop to add AI players based on numOfPlayers
		for(int i=1; i<numOfPlayers; i++)		
		{
			String name = "AI "+ i;
			playersList.addPlayer(new CompPlayer(name));
		}
		// Set a random player as the initial winner so that it can choose the category.
		
		Random randomPlayer = new Random();
		winnerOfRound = playersList.getPlayers().get(randomPlayer.nextInt(numOfPlayers));
		previousWinner = winnerOfRound;
		
	}
	
	// Load cards into main deck
	 public void loadCards(FileReader fr) 
	 {
		    Scanner s = new Scanner(fr);
		    // Uses nextLine to skip the first line
		    s.nextLine();
		    int counter = 0;
		    // Uses a counter to stop adding cards when deck is full
		    while (counter < 40)
		    { 
		    	// Initialise new card
		    	Card c = new Card();
		    	// Capture next line as a String
		        String line = s.nextLine();
		        // Split the values on the line based on spaces " " between each value
		        String[] values = line.split(" ");
		        // First value is the character name, store as a String
		        String name = values[0];
		        // Store next 5 values as integers 
		        int intelligence = Integer.parseInt(values[1]);
		        int speed = Integer.parseInt(values[2]);
		        int strength = Integer.parseInt(values[3]);
		        int agility = Integer.parseInt(values[4]);
		        int combat = Integer.parseInt(values[5]);
		        // Add the new card to the deck
		        allCards.addCard(c);
		        // Add the int values to the new card
		        this.allCards.getDeck().get(counter).fillCard(name, intelligence, speed, strength, agility, combat);
		        // Increment the counter for the next card
		        counter++;
		    }
		    // Close the scanner
		    s.close();
		}
	 
		public void dealDeck()
		{
			int deckPosition = 0;
			int count = allCards.getDeck().size();
			while(count>0)
			{
				for(int i=0; i<playersList.getPlayers().size(); i++)
				{
					if(deckPosition == allCards.getDeck().size())
					{
						break;
					}
					playersList.getPlayers().get(i).getPlayerDeck().addCard(allCards.getDeck().get(deckPosition++));
				}
				count--;
			}
			
		}
		
	
	
	public void shuffleDeck()
	{
		Random randomInteger = new Random();
		// Loop through card deck
		for(int i=0; i<allCards.getDeck().size(); i++)
		{
			// Make the rIP a random number within the deck's range
			int randomIndexPosition = randomInteger.nextInt(allCards.getDeck().size());
			// Create a temp Card and make a copy of the card at the rIP in the deck
			Card temp = allCards.getDeck().get(randomIndexPosition);
			// Set the card at the ith position to the rIP position
			allCards.getDeck().set(randomIndexPosition, allCards.getDeck().get(i));
			// Set the temp card (holding copy of card in rIP position) to the ith position 
			allCards.getDeck().set(i, temp);
		}
	}
	
	public void roundWinner() {
        // Create category integer
        int category;
        // if online version is being used, then use that to set category
        if(onlineCat !=0 && winnerOfRound.equals(playersList.getPlayers().get(0)))
        {
            category = onlineCat-1;
            onlineCat = 0;
        }
        // else use the chooseCategory method of the previous round winner
        else
        {
            category = winnerOfRound.chooseCategory();
            cat = category;
        }


        int playerCounter = 0;
        // Loop through playerList to count number of active players
        for(int i=0; i<playersList.getPlayers().size(); i++)
        {
        	// Check the players don't have "lost = true" (updated when players have 0 cards)
            if(!playersList.getPlayers().get(i).getLost())
            {
                playerCounter++;
            }
        }

        // Use playerCounter to set array lengths of fixed-length arrays
        Player[] tempPlayerList = new Player[playerCounter];
        int[] tempValues = new int[playerCounter];

        // Loop again through same playerList, but now add the active players to tempPlayerList[]
        int tempCounter = 0;
        for(int i=0; i<playersList.getPlayers().size(); i++)
        {
            if(!playersList.getPlayers().get(i).getLost())
            {
                tempPlayerList[tempCounter] = playersList.getPlayers().get(i);
                tempCounter++;
            }
        }

        // Take each player's top card's category value and add it to the tempValues list
        for(int i=0; i<tempPlayerList.length; i++) {
                tempValues[i] = tempPlayerList[i].getPlayerDeck().returnTopCard().getValue(category);
        }

        // Set max to first value in tempValues
        int max = tempValues[0];
        // Compare max to all other values in tempValues, updating max with the highest value
        for (int i = 1; i < tempValues.length; i++)
        {
            if (tempValues[i] > max)
            {
                max = tempValues[i];
            }
        }

        // Now check for draws in same list
        int drawChecker = 0;
        // To do this, compare max to all values in tempValues *again*, but for equality
        // if max is the same as more than 1 value, it's a draw case
        for (int value : tempValues)
        {
        	// if value and max are the same, increment drawChecker
            if (value == max)
            {
                drawChecker++;
            }
        }

        // Check if it's a draw case (max matches more than 1 value in tempValues)
        if (drawChecker > 1)
        {
            System.out.println("\nThe round was a draw. Cards added to Draw Deck.");
            totalNumberOfDraws++;
            // Set roundDrawn for use in other GameLogic methods
            roundDrawn = true;
        }
        else
        {
            // Now we know it's not a draw, loop through active players list
            for (Player player : tempPlayerList) {
                // if max value is same as that player's value (and we know by now it's not a draw)
                if (max == player.getPlayerDeck().returnTopCard().getValue(category))
                {
                    // Update winnerOfRound
                    winnerOfRound = player;
                    winnerOfRound.incNumberOfRoundsWon();
                    // Set roundDrawn for use in other GameLogic methods
                    roundDrawn = false;
                }
            }
        }
        // Increment roundNumber for game
        roundNumber++;
    }
	

	public void whoChooseCategory()
	{
		// CompPlayer and HumanPlayer both implement the Player interface & use chooseCategory
		// So, the chooseCategory method can be called no matter if the winnerOfRound is a Human or Comp
		winnerOfRound.chooseCategory();
	}
	
	public String getWinnerOfRound()
	{
		// Check if round has been drawn (don't display winning card in a drawn round)
		if (roundDrawn == false)
		{		
			return winnerOfRound.getName() + " won the round with this card:\n" +  winnerOfRound.getPlayerDeck().getTopCard();
		}
		else
		{
			return " ";
		}
	}
	
	// Used for testing, loops through playersList and prints each player's top card (always position 0)
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
		// Check if players are still in the game
			if(playersList.getPlayers().get(0).getLost() == false) 
			{
				// Display the top card (card 0 in each player's deck) & the size of the user's deck
				System.out.println("\n" + playersList.getPlayers().get(0).getName() + "'s current card:");
				System.out.println(playersList.getPlayers().get(0).getPlayerDeck().getTopCard());
				displayUserDeckSize();
			}
		}
	
	public void displayRoundWinners() 
	{
		// if the round wasn't a draw, display a list of the rounds won by each player and total draws
		if (roundDrawn == false)
		{
		 System.out.println("\n- - - - - - - - - - -");	
		 // Loop through playerList
		 for(int i =0; i<playersList.getPlayers().size(); i++)
			{
				System.out.println(playersList.getPlayers().get(i).getName() + " has won " + playersList.getPlayers().get(i).getNumberOfRoundsWon() + " rounds");
			}
		 System.out.println("- - - - - - - - - - -");
		 System.out.println(totalNumberOfDraws + " rounds were draw");
		 System.out.println("- - - - - - - - - - -\n");
		} else
		{
			System.out.println(" ");
			// If the game was drawn, update deckPileWaiting for next round
			deckPileWaiting = true;
		}
	}
	
	public void displayUserDeckSize() 
	{
		// Access player name and deck size and display them on screen
		System.out.printf("%n" + playersList.getPlayers().get(0).getName() + " has " + playersList.getPlayers().get(0).getPlayerDeck().getDeck().size() + " cards in their deck");
	}

	
	public void playRound()
	{
		// Call roundWinner method to compare card values
		roundWinner();
		// Use the cat integer to get the correct category name for the display
		System.out.println("\n\n" + previousWinner.getName() + " chose " + allCards.getDeck().get(0).getCategories(cat));
		// Print game winner and update previousWinner for next round
		System.out.println("\n\n" + getWinnerOfRound());
		previousWinner = winnerOfRound;

	}
	
	public void lostPlayer()
	{
		// Loop through player list
		for(int i=0; i<playersList.getPlayers().size(); i++)
		{
			// if player's deck is empty, set lost = true
			if(playersList.getPlayers().get(i).getPlayerDeck().getDeck().isEmpty() == true)
			{
				playersList.getPlayers().get(i).setLost(true);
			}
		}
	}

	public void transferCards()
	{
		// Set the round's winning card before transferring cards
		winnerCard = winnerOfRound.getPlayerDeck().getDeck().get(0);
		
		int playersListSize = playersList.getPlayers().size();
		int tempSize = 0;
		DeckOfCards temp = new DeckOfCards();
		
		// Loop through playerList, adding each player's top card to temp deck
		for(int i=0; i<playersListSize; i++)
		{
			if(playersList.getPlayers().get(i).getLost() == false)
			{
				// if the player has not lost, add their top (0) card to the temp deck
				temp.addCard(playersList.getPlayers().get(i).getPlayerDeck().getDeck().get(0));
				// Then remove it from their own deck
				playersList.getPlayers().get(i).getPlayerDeck().getDeck().remove(0);
			}
		}
		// Shuffle the temp deck
		this.shuffleHand(temp);
		tempSize = temp.getDeck().size();
		
		// if round was not a draw, compare players to winnerOfRound
		if (roundDrawn == false)
		{
			for(int i=0; i<playersListSize; i++) 
			{
				if(playersList.getPlayers().get(i).equals(winnerOfRound)) 
				{
					for(int j=0; j<tempSize; j++) 
					{
						// Once winner is found, add each card from temp deck to winner's deck
						playersList.getPlayers().get(i).getPlayerDeck().getDeck().add(temp.getDeck().get(0));
						// Then remove card from temp deck
						temp.getDeck().remove(0);
					}
				}
				
			}
		} 
		// if the round was drawn, then add temp cards to drawPile for next round
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
		// Try to use dealDrawPile (only runs if the last round was a draw and this round isn't)
		dealDrawPile();
	}
	
	
	public void shuffleHand(DeckOfCards cards)
	{
		// Same way of swapping cards as used in shuffleDeck
		Random randomInteger = new Random();
		for(int i=0; i<cards.getDeck().size(); i++)
		{
			int randomIndexPosition = randomInteger.nextInt(cards.getDeck().size());
			Card temp = cards.getDeck().get(randomIndexPosition);
			cards.getDeck().set(randomIndexPosition, cards.getDeck().get(i));
			cards.getDeck().set(i, temp);
		}
	}
	
	public boolean lastPlayerLeft()
	{
		boolean result = false; 
		int playerCount=0;
		// Loop through playersList and count active players
		for(int i=0; i<playersList.getPlayers().size(); i++)
		{
			if(playersList.getPlayers().get(i).getLost() == false)
			{
				playerCount++;
			}
		}
		// If there is only 1 active player, the game is over
		if(playerCount==1)
		{
			gameWinnerID = winnerOfRound.getPlayerID();
			System.out.println("\n- - - - - - - - - - -\nThe winner is: " 
		+ winnerOfRound.getName() + "\n- - - - - - - - - - -\n");
			result = true;
		}
		return result;
	}
	
	// For testing, display winner's whole deck
	public void displayWinnerDeck()
	{
		for(int i=0; i<winnerOfRound.getPlayerDeck().getDeck().size(); i++)
		{
			System.out.println(winnerOfRound.getPlayerDeck().getDeck().get(i).toString());
		}
	}
	
	
	public void dealDrawPile()
	{
		// if the current round is not drawn, but the last round was (indicated by dPW == true)
		// Then the drawPile should deal cards to the current round winner
		if(roundDrawn == false && deckPileWaiting == true)
		{
			// Loop through drawPile, adding each card to the winner's deck
			for(int i=0; i<drawPile.getDeck().size(); i++)
			{
				winnerOfRound.getPlayerDeck().getDeck().add(drawPile.getDeck().get(i));
			}
			// Set dPW back to false
			deckPileWaiting = false;
			// Empty drawPile to avoid duplicate cards
			this.clearDrawPile();
		}

	}
	
	// Wipe the draw pile after its cards have been dealt
	public void clearDrawPile()
	{
		while(drawPile.getDeck().isEmpty() == false)
		{
			drawPile.getDeck().remove(0);
		}
	}
	
	public boolean isWinnerAHuman()
	{
		if(winnerOfRound.equals(playersList.getPlayers().get(0)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public ArrayList<String> getWinnerAttribute()
	{
		ArrayList<String> result = new ArrayList<String>(); 
		
		// if the round was not a draw, pass winning player and card values to the database
		if(roundDrawn == false)
		{
			result.add(winnerOfRound.getName());
			result.add(winnerCard.getName());
			result.add(Integer.toString(winnerCard.getIntelligence()));
			result.add(Integer.toString(winnerCard.getSpeed()));
			result.add(Integer.toString(winnerCard.getStrength()));
			result.add(Integer.toString(winnerCard.getAgility()));
			result.add(Integer.toString(winnerCard.getCombat()));
		}
		return result;
	}

	public ArrayList<String> getHumanPlayerTopCardAttributes()
	{
		ArrayList<String> result = new ArrayList<String>();
		// if the human player has not lost, pass their player and card values to the database
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
	
	// Getter for players ID used for database
	public ArrayList<Integer> getAllPlayersID()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i<playersList.getPlayers().size(); i++)
		{
			result.add(playersList.getPlayers().get(i).getPlayerID());
		}
		return result;
	}

	// Getter for number of rounds won for database
	public ArrayList<Integer> getnumberOfRoundsWonByEachPlayer()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i<playersList.getPlayers().size(); i++)
		{
			result.add(playersList.getPlayers().get(i).getNumberOfRoundsWon());
		}
		return result;
	}

	// Getter for number of cards in each player's deck
	public ArrayList<Integer> getNumberOfCards()
	{
		// Return player's number of cards for the database
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i=0; i<playersList.getPlayers().size(); i++)	
		{
			result.add(playersList.getPlayers().get(i).getPlayerDeck().getNumberOfCards());
		}
		return result;				
	}
	
	// Getters and Setters/Adders for the class

	public int getGameWinnerID() {
		return gameWinnerID;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setOnlineCat(int onlineCat) 
	{
		this.onlineCat = onlineCat;
	}
	
	public DeckOfCards getDrawPile() 
	{
		return drawPile;
	}
	
	public void addToDrawPile(Card card) 
	{
		this.drawPile.addCard(card);
	}
	
	public void displayRoundNumber() {
		System.out.println("= = = = = = = Round #" + roundNumber + " = = = = = = =");
	}
	
	public void printDeck()
	{
		allCards.displayDeck();
	}
	
	public int getOnlineCat() 
	{
		return onlineCat;
	}
	
	public int getNumberOfCardsCommunalPile()
	{
		return drawPile.getNumberOfCards();
	}
	
	public int getNumberOfTotalRounds()
	{
		return roundNumber;
	}

	public DeckOfCards getAllCards() {
		return allCards;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public Players getPlayersList() 
	{
		return playersList;
	}
	
	public Player getPlayerWinnerOfRound() 
	{
		return this.winnerOfRound;
	}
	
	public int getTotalNumberOfDraws() {
		return totalNumberOfDraws;
	}

	public int getGameId() {
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
	
	// For use in TestLog
	public void setPlayerWinnerOfRound(Player p)
	{
		this.winnerOfRound = p;
	}
	
}