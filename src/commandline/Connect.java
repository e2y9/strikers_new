package commandline;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class Connect{
	Connection connection;
	Statement statement;
		
    public Connect() {
    	//Connecting to server for postgresql database
    	try {
    		connection=DriverManager.getConnection("jdbc:postgresql://52.24.215.108:5432/", "Strikers", "Strikers");
    		statement = connection.createStatement();
    	} catch (SQLException e) { 
    	    System.out.println("Connection failure.");
    	    e.printStackTrace();
    		}
    		
        	
        	}
        	
    	//Passing variable to record into database
    	//gameid:number of games played
    	//numberofdraws:number of draws per game
    	//winner: winner player. It should be an integer where 0=Human_1, 1=AI_1, 2=AI_2, 3=AI_3, 4=AI_4
    	//numberofrounds: number of rounds played per game 
    	//playerid: An array of players. It should be defined as an Integer array where 0=Human_1, 1=AI_1, 2=AI_2, 3=AI_3, 4=AI_4
    	//roundsperplater: number of rounds per game per player
    
       public void gamerecords(int gameid,int numberofdraws,int winner,int numberofrounds,ArrayList<Integer> playerid, ArrayList<Integer> roundsperplayer ) {	
            String query1 = " insert into gameplay(gameid, no_draws, winner, total_rounds) values (?, ?, ?, ?)";
            String query2 = " insert into rounds(gameid, playerid, rounds) values (?, ?, ?)";
            PreparedStatement prepst;
			try {
				prepst = connection.prepareStatement(query1);
				prepst.setInt (1, gameid);
	            prepst.setInt (2, numberofdraws);
	            prepst.setInt (3, winner);
	            prepst.setInt (4, numberofrounds);
	            prepst.execute();
	            for(int i=0;i<playerid.size();i++)
	            {      
	            prepst = connection.prepareStatement(query2);
	            prepst.setInt (1, gameid);
	            prepst.setInt (2, playerid.get(i));
	            prepst.setInt (3, roundsperplayer.get(i));
	            prepst.execute();
	            }
			} catch (SQLException e) {
				System.out.println("Database insertion failed.");
				e.printStackTrace();
			}
            
       }
       //Number of games played overall 
       public int numberOfGames() {
    	   
    	   try {
    	   ResultSet resultSet = statement.executeQuery("SELECT Count(*) as TotalNoOfGames FROM gameplay");
    	   resultSet.next();
    	   return resultSet.getInt("TotalNoOfGames");
    	   } catch (SQLException e) {
    		   	System.out.println("Can't bring TotalNoOfGames");
    		   	e.printStackTrace();
				return 0;
			}
    	   
       }
       //How many times the computer has won
       public int numberofHumanWin() {
    	   
    	   try {
    	     	   ResultSet resultSet = statement.executeQuery("SELECT COUNT(gameplay.*) as NoOfHumanWin FROM gameplay,player WHERE playerid=winner and humantest=true");
    	     	   resultSet.next();
    	     	   return resultSet.getInt("NoOfHumanWin");
    	   } catch (SQLException e) {
    		   	System.out.println("Can't bring NoOfHumanWin");
    		   	e.printStackTrace();
				return 0;
			}
    	   
       }
       //How many times the human has won 
       public int numberofAIwin() {
    	   
    	   try {
    	     	   ResultSet resultSet = statement.executeQuery("SELECT COUNT(gameplay.*) as NoOfAIWin FROM gameplay,player WHERE playerid=winner and humantest=false");
    	     	   resultSet.next();
    	     	   return resultSet.getInt("NoOfAIWin");
    	   } catch (SQLException e) {
    		   	System.out.println("Can't bring NoOfAIWin");
    		   	e.printStackTrace();
				return 0;
			}
    	   
       }
       //The average number of draws 
       public double averageOfDraws() {
    	   
    	   try {
    	     	   ResultSet resultSet = statement.executeQuery("SELECT AVG(gameplay.no_draws) as AverageOfDraws FROM gameplay");
    	     	   resultSet.next();
    	     	   return resultSet.getDouble("AverageOfDraws");
    	   } catch (SQLException e) {
    		   	System.out.println("Can't bring AverageOfDraws");
    		   	e.printStackTrace();
				return 0;
			}
    	   
       }
       //The largest number of rounds played in a single game
       public int maxRoundInGame() {
    	   
    	   try {
    	     	   ResultSet resultSet = statement.executeQuery("SELECT MAX(gameplay.total_rounds) as MaxRound FROM gameplay");
    	     	   resultSet.next();
    	     	   return resultSet.getInt("MaxRound");
    	   } catch (SQLException e) {
    		   	System.out.println("Can't bring MaxRound");
    		   	e.printStackTrace();
				return 0;
			}
    	   
       }
       //Disconnect database server when write/read operations are carried out.
       public void disconnect()
   	{
   		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	}
}