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
	
	@PUT
	@Path("/startnewgame")
	public void newGame(@QueryParam("numberofplayers") int numberofplayers) //should get playernum from javascript function
		{
		game = new GameLogic(numberofplayers);
		game.shuffleDeck();
		game.dealDeck();
				
		//setupplayer method in logic will be added.
		
		//starting a new game with Model
		
		}
	@GET
	@Path("/getHumanCard")
	public String getHumanCard() throws IOException {
		
		return oWriter.writeValueAsString(game.getHumanPlayerTopCardAttributes());
	}
		
	@GET
	@Path("/getWinnerCard")
	public String getWinnerCard() throws IOException {
		
		return oWriter.writeValueAsString(game.getWinnerAttribute());	
	
	}
	
	@PUT
	@Path("/humanCat")
	public void humanPlayerChosenCategory(@QueryParam("category") String category) throws IOException {
		game.setOnlineCat(Integer.parseInt(category));
	}
	
	@GET
	@Path("/nextRound")
	public void nextRound() throws IOException {
		
		game.playRound();
		game.transferCards();
		game.lostPlayer();
		game.displayRoundWinners();
		
	}
	
	@GET
	@Path("/skipGame")
	public void skipGame() throws IOException {
		while(game.lastPlayerLeft() == false)
		{
			game.playRound();
			game.transferCards();
			game.lostPlayer();
			game.displayRoundWinners();
		}


	}	
	
	@GET
	@Path("/getRoundNumber")
	public String getRoundNumber() throws IOException {
		return oWriter.writeValueAsString(game.getRoundNumber());	
	}
	
	@GET
	@Path("/checkHumanWinner")
	public String checkHumanWinner() throws IOException {
		System.out.println(game.getWinnerOfRound());
		System.out.println(game.isWinnerAHuman());
		return oWriter.writeValueAsString(game.isWinnerAHuman());	
	
	}
	
	@GET
	@Path("/getCardNumbers")
	public String getCardNumbers() throws IOException {
		
		return oWriter.writeValueAsString(game.getNumberOfCards());	
	
	}
	
	@GET
	@Path("/getDrawpile")
	public String getDrawpile() throws IOException {
		return oWriter.writeValueAsString(game.getNumberOfCardsCommunalPile());	
	
	}
	
	@GET
	@Path("/getGameWinner")
	public String getGameWinner() throws IOException {
		if(game.lastPlayerLeft() == true)
		{
		return oWriter.writeValueAsString(game.getPlayersList().getPlayers().get(game.getGameWinnerID()-1).getName());	
		}
		return "null";
	}
	
	@GET
	@Path("/write2database")
	public void write2database()
	{
		connect=new Connect();
		int gameid = connect.numberOfGames()+1;
		connect.gamerecords(gameid, game.getTotalNumberOfDraws(), game.getGameWinnerID(), game.getRoundNumber(), game.getAllPlayersID(), game.getnumberOfRoundsWonByEachPlayer());
	}
	
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

		return oWriter.writeValueAsString(statList);
		
	
	}
	
	
	//@GET
	//@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	//public String helloJSONList() throws IOException {
		
	//	List<String> listOfWords = new ArrayList<String>();
	//	listOfWords.add("Hello");
	//	listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		//String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		//return listAsJSONString;
	//}
	
	//@GET
	//@Path("/helloWord")
	///**
	// * Here is an example of how to read parameters provided in an HTML Get request.
	// * @param Word - A word
	// * @return - A String
	// * @throws IOException
	// */
	//public String helloWord(@QueryParam("Word") String Word) throws IOException {
	//	return "Hello "+Word;
	//}
	
}
