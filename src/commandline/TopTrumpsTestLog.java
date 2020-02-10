package commandline;
/* Everything in this file is to be written to a file called toptrumps.log */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TopTrumpsTestLog {

    private Players playersList;
    private DeckOfCards allCards;
    private Player winnerOfRound;
    private DeckOfCards drawPile;
    private int totalNumberOfDraws = 0;
    private static int gameId = 0;


    public static void gameLoop(GameLogic game) {
        game.displayRoundNumber();
        game.displayUserTopCard();
        game.playRound();
        game.transferCards();
        game.lostPlayer();
        game.displayRoundWinners();
    }


    public TopTrumpsTestLog() throws IOException {
        Players players = new Players();
        Player p1 = new HumanPlayer("User");
        Player p2 = new CompPlayer("AI 1");
        Player p3 = new CompPlayer("AI 2");
        Player p4 = new CompPlayer("AI 3");
        Player p5 = new CompPlayer("AI 4");

        players.addPlayer(p1);
        players.addPlayer(p2);
        players.addPlayer(p3);
        players.addPlayer(p4);
        players.addPlayer(p5);

        GameLogic game = new GameLogic(players);


        // In addition to the functionality described above, you should implement the following to allow for program debugging
        // when in command line mode only. When the program is started, if a ‘-t’ flag is set on the command line, then the
        // program should write out an extensive log of its operation to a ‘toptrumps.log’ file in the same directory as the
        // program is run, e.g.:
        // • java -jar TopTrumps.jar -c -t
        // If a toptrumps.log file already exists, your program should overwrite that file. Your program should print the
        // following information to that file, separated by a line containing dashes “----------“ at the appropriate times as
        // mentioned below:

        // • The contents of the complete deck once it has been read in and constructed

        FileWriter fw=new FileWriter("src\\toptrumps.log");

        game.dealDeck();

        fw.write("\nWhole deck:\n");
        try
        {
            for (Card c : game.getAllCards().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        // • The contents of the complete deck after it has been shuffled

        game.shuffleDeck();

        fw.write("\nWhole deck (shuffled):\n");
        try
        {
            for (Card c : game.getAllCards().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}


        // • The contents of the user’s deck and the computer’s deck(s) once they have been allocated. Be sure to
        // indicate which the user’s deck is and which the computer’s deck(s) is.

        try
        {
            fw.write("\n\n User Deck:\n");
            for (Card c : p1.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        try
        {
            fw.write("\n\n AI 1 Deck:\n");
            for (Card c : p2.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        try
        {
            fw.write("\n\n AI 2 Deck:\n");
            for (Card c : p3.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        try
        {
            fw.write("\n\n AI 3 Deck:\n");
            for (Card c : p4.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        try
        {
            fw.write("\n\n AI 4 Deck:\n");
            for (Card c : p5.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}


        // • The contents of the communal pile when cards are added or removed from it

        Card testCard = new Card();
        Card testCard2 = new Card();
        testCard.fillCard("Mr.Test", 1, 5, 16, 41, 2);
        testCard2.fillCard("Mrs.Test", 17, 9, 15, 23, 49);

        game.addToDrawPile(testCard);
        game.addToDrawPile(testCard2);


        try
        {
            fw.write("\n\n Draw Pile:\n");
            for (Card c : game.getDrawPile().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception ignored) {}


        game.getDrawPile().removeCard(testCard);

        try
        {
            fw.write("\n\n Draw Pile (1 card removed):\n");
            for (Card c : game.getDrawPile().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception ignored) {}

        game.getDrawPile().removeCard(testCard2);


        // • The contents of the current cards in play (the cards from the top of the user’s deck and the computer’s
        // deck(s))
        fw.write("\nUSER TOP CARD:\n");
        fw.write(p1.topCard());
        fw.write("\n\nAI 1 TOP CARD:\n");
        fw.write(p2.topCard());
        fw.write("\n\nAI 2 TOP CARD:\n");
        fw.write(p3.topCard());
        fw.write("\n\nAI 3 TOP CARD:\n");
        fw.write(p4.topCard());
        fw.write("\n\nAI 4 TOP CARD:\n");
        fw.write(p5.topCard());
        fw.write("\n--------------------\n");

        fw.write("\nAI 1 TOP CARD:\n");
        fw.write(p2.topCard());

        int displayCategory = p2.chooseCategory();
        int userValue = p1.getPlayerDeck().getTopCardValue(displayCategory);
        int ai2Value = p3.getPlayerDeck().getTopCardValue(displayCategory);
        int ai3Value = p4.getPlayerDeck().getTopCardValue(displayCategory);
        int ai4Value = p5.getPlayerDeck().getTopCardValue(displayCategory);

        // category value index starts at 0, category choice starts at 1
        // so +=1 will show correct category number to screen
        displayCategory += 1;

        fw.write("\n\nChosen category by AI 1: " + displayCategory);
//        System.out.println("\nChosen category by AI 1 :" + displayCategory);
        if (displayCategory == 1)
        {
            fw.write("\nCategory chosen is: Intelligence");
        } else if (displayCategory == 2)
        {
            fw.write("\nCategory chosen is: Speed");
        } else if (displayCategory == 3)
        {
            fw.write("\nCategory chosen is: Strength");
        } else if (displayCategory == 4)
        {
            fw.write("\nCategory chosen is: Agility");
        } else if (displayCategory == 5)
        {
            fw.write("\nCategory chosen is: Combat");
        } else
        {
            fw.write("\nNo category chosen");
        }
        fw.write("\n\nOther users' values for same category:\n");
        fw.write("\nUser: " + userValue);
        fw.write("\nAI 2: " + ai2Value);
        fw.write("\nAI 3: " + ai3Value);
        fw.write("\nAI 4: " + ai4Value);

        fw.write("\n\nOther users' top cards:\n");
        fw.write("\nUSER TOP CARD:\n");
        fw.write(p1.topCard());
        fw.write("\n\nAI 2 TOP CARD:\n");
        fw.write(p3.topCard());
        fw.write("\n\nAI 3 TOP CARD:\n");
        fw.write(p4.topCard());
        fw.write("\n\nAI 4 TOP CARD:\n");
        fw.write(p5.topCard());
        fw.write("\n--------------------\n");

        // • The contents of each deck after a round

        // a drawn round is unsuitable for testing
        // so the test while loop will play rounds until one is not a draw
        boolean roundComplete = false;
        while (roundComplete = false)
        {
            fw.write("Playing a round ...");
            fw.write("...");
            fw.write("..");
            fw.write(".");
            // for the test round, set the category chooser to AI
            // OR make human chooseCategory different in this test version
            // game.setPlayerWinnerOfRound(p2);
            gameLoop(game);
            if (!game.getRoundDrawn())
            {
                roundComplete = true;
            }
        }

        fw.write("\n\nDeck contents for each player after a round:\n");

        try
        {
            fw.write("\n\n User Deck:\n");
            for (Card c : p1.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        try
        {
            fw.write("\n\n AI 1 Deck:\n");
            for (Card c : p2.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        try
        {
            fw.write("\n\n AI 2 Deck:\n");
            for (Card c : p3.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        try
        {
            fw.write("\n\n AI 3 Deck:\n");
            for (Card c : p4.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}

        try
        {
            fw.write("\n\n AI 4 Deck:\n");
            for (Card c : p5.getPlayerDeck().getDeck())
            {
                fw.write("\n" + c.toString());
            }
            fw.write("\n\n--------------------\n");
        } catch(Exception e) {}


        // • The winner of the game
        while(!game.lastPlayerLeft())
        {
            gameLoop(game);
        }
        int gameWinnerID = 0;

        if (gameWinnerID == 0)
        {
            fw.write("\n\nGAME OVER\n\nThe Winner is: User");
        } else if (gameWinnerID == 1)
        {
            fw.write("\n\nGAME OVER\n\nThe Winner is: AI 1");
        } else if (gameWinnerID == 2)
        {
            fw.write("\n\nGAME OVER\n\nThe Winner is: AI 1");
        } else if (gameWinnerID == 3)
        {
            fw.write("\n\nGAME OVER\n\nThe Winner is: AI 1");
        } else if (gameWinnerID == 4)
        {
            fw.write("\n\nGAME OVER\n\nThe Winner is: AI 1");
        }

    fw.close();
    }
}
