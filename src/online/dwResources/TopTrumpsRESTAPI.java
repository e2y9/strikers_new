package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//we should import game package. For now let us say "Model"
import commandline.GameLogic;
import commandline.Connect;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	
	GameLogic game;
	Connect connect;
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	/*The below method responds to the http PUT request and is used to start the game. It takes number of players as an argument*/
	@PUT
	@Path("/startnewgame") //URI path for this method
	public void newGame(@QueryParam("numberofplayers") int numberofplayers) //Gets the numofplayers from Javascript.
		{
		game = new GameLogic(numberofplayers); //Creates a GameLogic Object define
		game.shuffleDeck(); //Calls the shuffleDeck method from the GameLogic which shuffles the Marvel deck before dealing it to players.
		game.dealDeck(); //Calls the dealDeck method which deals the cards to the added number of players.		
		}
	
	//getHumanCard method responds to the HTTP GET Requests. It throws IOException
	@GET
	@Path("/getHumanCard")
	public String getHumanCard() throws IOException {
		//It returns the Human Player top Card attribute which is used to Display the human player's top card using the GameScreen.ftl 
		//It uses Object writer to easily convert the java object into JSON string.
		return oWriter.writeValueAsString(game.getHumanPlayerTopCardAttributes());
	}
	
	/*getWinnerCard method responds to the HTTP get request 
	 * Throws IO Exception.
	 * Returns the Winner and winner card attributes using the object writer and GameLogic*/
	@GET
	@Path("/getWinnerCard")
	public String getWinnerCard() throws IOException {
		
		return oWriter.writeValueAsString(game.getWinnerAttribute());	
	
	}
	
	/*Below method responds to the HTTP PUT requests.
	 *Throws IO Exception.
	 *Takes category as the argument using the Javascript in the GameScreen ftl file
	 *This passes the category to the setOnlineCat method in the GameLogic. Created to pass the category to the GameLogic.*/
	@PUT
	@Path("/humanCat") //Defines the URI for the below method 
	public void humanPlayerChosenCategory(@QueryParam("category") String category) throws IOException {
		game.setOnlineCat(Integer.parseInt(category));
	}
	
	/*nextRound method responds to the HTTP GET request.
	 * Throws IO Exception.
	 * Calls various method from the GameLogic to execute the round.*/
	@GET
	@Path("/nextRound") //Defines the URI for the nextRound method.
	public void nextRound() throws IOException {
		
		game.playRound(); //Calls the playRound method of the GameLogic which starts the round.
		game.transferCards(); //Calls the method to transfer the card to the Winner of the round.
		game.lostPlayer(); //Calls the lostPlayer method to check if any player has 0 cards left.
		game.displayRoundWinners(); //Calls the displayRoundWinner method.
		
	}
	
	/*skipGame method responds to the HTTP GET requests.
	 *Throws IO Exception
	 *This method is created to skip the Game to end if the Human Player has Lost.*/
	@GET
	@Path("/skipGame") 
	public void skipGame() throws IOException {
		while(game.lastPlayerLeft() == false) //Loop until there is a winner.
		{
			game.playRound(); 
			game.transferCards();
			game.lostPlayer();
			game.displayRoundWinners();
		}


	}	
	
	/*getRoundNumber responds to the HTTP GET request.
	 * It returns the Number of Round using the GameLogic getRoundNumber method and Object Writer*/
	@GET
	@Path("/getRoundNumber")
	public String getRoundNumber() throws IOException {
		return oWriter.writeValueAsString(game.getRoundNumber());	
	}
	
	/*checkHumanWinner responds to the GET requests.
	 * It returns the true as a string if the winner is human using the GameLogic and ObjectWriter.*/
	@GET
	@Path("/checkHumanWinner")
	public String checkHumanWinner() throws IOException {
		return oWriter.writeValueAsString(game.isWinnerAHuman());	
	}
	
	/*getCardNumbers method reponds to the HTTP GET requests.
	 * Returns the number of rounds from Game logic using the Object Writer*/
	@GET
	@Path("/getCardNumbers")
	public String getCardNumbers() throws IOException {
		
		return oWriter.writeValueAsString(game.getNumberOfCards());	
	
	}
	
	/*This method returns the number of cards in communal pile from GameLogic using the Object writer.*/
	@GET
	@Path("/getDrawpile")
	public String getDrawpile() throws IOException {
		return oWriter.writeValueAsString(game.getNumberOfCardsCommunalPile());	
	
	}
	
	/*This method returns the name of the Winner if only 1 player has the cards, otherwise it returns null.*/
	@GET
	@Path("/getGameWinner")
	public String getGameWinner() throws IOException {
		if(game.lastPlayerLeft() == true)
		{
		return oWriter.writeValueAsString(game.getPlayersList().getPlayers().get(game.getGameWinnerID()-1).getName());	
		}
		return "null";
	}
	
	/*This method is used to write the game details to the database.*/
	@GET
	@Path("/write2database")
	public void write2database()
	{
		connect=new Connect(); //Creates a Connect Object to connect to the database.
		int gameid = connect.numberOfGames()+1; //reads the lastGameid from the database and increment it and set it for the next game.
		/*below line sends the data to write to the database.*/
		connect.gamerecords(gameid, game.getTotalNumberOfDraws(), game.getGameWinnerID(), game.getRoundNumber(), game.getAllPlayersID(), game.getnumberOfRoundsWonByEachPlayer());
		connect.disconnect(); //Disconnect from the Database after the data is transferred.
	}
	
	/*getStats method is used for displaying the stats in online mode it gets the data from the server and save and return it using an ArrayList*/
	@GET
	@Path("/getStats")
	public String getStats() throws IOException {
		connect=new Connect();
		ArrayList<Double> statList=new ArrayList<Double>();
		statList.add((double) connect.numberOfGames());
		statList.add((double) connect.numberofHumanWin());
		statList.add((double) connect.numberofAIwin());
		statList.add(connect.averageOfDraws());
		statList.add((double) connect.maxRoundInGame());
		connect.disconnect();

		return oWriter.writeValueAsString(statList); //Returns the ArrayList using the Object Writer.
	}	
}
