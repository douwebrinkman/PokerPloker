import java.util.ArrayList;
import java.util.Random;

public class Game {
    static ArrayList<String> drawnCards = new ArrayList<String>();
    ArrayList<String> cards = new ArrayList<String>();
    

    Game() {
        cards = cards();
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
