import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFormattedTextField;

public class GameState {
    int turnNum;
    int pot = 0;
    ArrayList<String> playerList = new ArrayList<>();
    ArrayList<Integer> money = new ArrayList<>();
    ArrayList<Integer> bet = new ArrayList<>();
    JFormattedTextField startAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    JFormattedTextField startingBet = new JFormattedTextField(NumberFormat.getIntegerInstance());
    static ArrayList<String> drawnCards = new ArrayList<String>();
    ArrayList<String> cards = cards();


    public boolean checkBot(String a) {
        if (a.equals(" Birgin")) {
            //birgin()
            return true;
        } else if (a.equals(" Raiser")) {
            //raiser();
            return true;
        } else {
            return false;
        }
    }

    public void round() {
        int anti = (int) startingBet.getValue();
        for (int i = 0; i < playerList.size(); i++) {
            money.set(i, (money.get(i) - anti));
            bet.add(anti);
        }
        pot = pot + (playerList.size() * anti);
        // sleep 2 sec
        turn();

    }

    public void turn() {
        for (int i = 0; i < playerList.size(); i++) { 
            turnNum = i;
            //turn label(turnNum) green
            if (checkBot(playerList.get(i))) {
                //(buttons should be gray)turn buttons gray
                //sleep for 2 sec
                //turn label back,
            } else {
                //turn buttons green
                //wait for input, max wait time = ...
                //if wait time is over just check.
                //turn label back

            }
        }
        //if there is a raise set raiseValue to i, 
        //then at end of array if raiseValue > 0, 
        //run array again till raiseValue - 1. but if raised again?
        //maybe need to loop the array 
        
    }

    ArrayList<String> cards() {
        ArrayList<String> cardDeck = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                if (i == 0) {
                    cardDeck.add("H" + (j + 2));
                } else if (i == 1) {
                    cardDeck.add("S" + (j + 2));
                } else if (i == 2) {
                    cardDeck.add("D" + (j + 2));
                } else if (i == 3) {
                    cardDeck.add("C" + (j + 2));
                }
            }
            
        }
        return cardDeck;
    }
    
    void drawCards(int numBots) {
        Random r = new Random();
        for (int i = 0; i < (7 + (2 * numBots)); i++) {
            int randomNum = r.nextInt(52); // 0 t/m 51 
            //System.out.println(randomNum);
            drawnCards.add(cards.get(randomNum));
            cards.remove(randomNum);
        }
        
    }

    /*public static ArrayList<Integer> money() {
        ArrayList<Integer> money = new ArrayList<>();
        for (int i = 0; i < CreateMenu.playerList.size(); i++) {
            money.add((int) CreateMenu.startAmount.getValue());
        }
        return money;
    }*/
        

    


  
}
