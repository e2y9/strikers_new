/* Group Name: Strikers */
/*This class is used to create the Deck of cards in the Game. It uses Card class in ArrayList*/
package commandline;

import java.util.ArrayList; 

public class DeckOfCards {
		
	private ArrayList<Card> deck;  //Array list variable to store the deck set to private.
	
	/*Constructor for the class DeckOfCards*/
	public DeckOfCards()
	{
		this.deck = new ArrayList<Card>(); //Creating the DeckOfCards object with deck variable.
	}

	/*Method to display all the cards in the deck */
	public void displayDeck()
	{ int i = 0;
		for(Card element:deck)
		{
			System.out.println(element.toString() + "card " + i++);
		}
	}
	
	//Method to add a card to the deck ArrayList. Created for the ease of adding cards from other classes. 
	public void addCard(Card cardToAdd) //Takes as Card Object that needs to be added to the deck.
	{
		deck.add(cardToAdd);
	}
	
	//Method to remove a card to the deck ArrayList. Created for the ease of adding cards from other classes.
	public void removeCard(Card cardToRemove) //Takes as Card Object that needs to be removed from the deck.
	{
		deck.remove(cardToRemove);
	}
	
	//This method returns the top card in the deck as a string.
	public String getTopCard()
	{
		return deck.get(0).toString();
	}
	
	//This method is used to get the top card's value for a particular category.
	public int getTopCardValue(int category)
	{
		return deck.get(0).getValue(category);
	}
	
	//Getter Method for the deck variable.
	public ArrayList<Card> getDeck() 
	{
		return deck;
	}
	
	//Getter method to return the number of cards in the deck.
	public int getNumberOfCards()
	{
		return deck.size();
	}
	
	//This method the Card object for the top Card in the deck.
    public Card returnTopCard() {
        return deck.get(0);
    }

	
}
