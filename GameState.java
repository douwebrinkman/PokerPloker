import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;

public class GameState {
    int turnNum;
    int pot = 0;
    ArrayList<String> playerList = new ArrayList<>();
    ArrayList<Integer> money = new ArrayList<>();
    ArrayList<Integer> bet = new ArrayList<>();
    JFormattedTextField startAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    JFormattedTextField startingBet = new JFormattedTextField(NumberFormat.getIntegerInstance());


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
}
