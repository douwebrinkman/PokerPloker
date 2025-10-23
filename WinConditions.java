import java.util.*;

public class WinConditions {
    //String input = Game.drawnCards.get(0);  //converts string into int.
    //String numbers = input.substring(1);
    //int number = Integer.parseInt(numbers);

    void checkHighest(int i, int j) {
        List<String> tableCards = GameState.drawnCards.subList(0, 5); //get's tablecards
        int players = (GameState.drawnCards.size() - 5) / 2; //calculates number of players.

        int highestPair = -1;
        int winner = -1;

        //calculates and get's the players two cards
        for (int player = 0; player < players; player++) {  
            int firstCard = 5 + (player * 2);
            int secondCard = firstCard + 1;
            String playerCard1 = GameState.drawnCards.get(firstCard);
            String playerCard2 = GameState.drawnCards.get(secondCard);
            int numberPlayerCard1 = Integer.parseInt(playerCard1.substring(1));
            int numberPlayerCard2 = Integer.parseInt(playerCard2.substring(1));

            //makes list of two player cards.
            List<String> playerCards = new ArrayList<>();
            playerCards.add(playerCard1);
            playerCards.add(playerCard2);

            //makes list with playerCard numbers.
            List<Integer> playerCardNumbers = new ArrayList<>();
            playerCardNumbers.add(numberPlayerCard1);
            playerCardNumbers.add(numberPlayerCard2);

            //makes list with all 7 cards (5 table + 2 player).
            List<String> cards = new ArrayList<>(tableCards);   
            cards.add(playerCard1);
            cards.add(playerCard2);

            int numberPair = -1;
            boolean pair = false;
            if (numberPlayerCard1 == numberPlayerCard2) {
                numberPair = numberPlayerCard1;
            } else {
                pair = false;

                for (j = 0; j < tableCards.size(); j++) {
                    String table = tableCards.get(j);
                    String tableCardNumbers = table.substring(1);
                    int tableCardNumber = Integer.parseInt(tableCardNumbers);

                    if (playerCardNumbers.contains(tableCardNumber)) {
                        numberPair = tableCardNumber;
                        pair = true;
                        break;
                    }
                }
            }

            //check which pair is the highest.
            if (numberPair > highestPair) {
                highestPair = numberPair;
                winner = player;
            } else if (numberPair == highestPair) {
                int highest = Collections.max(playerCardNumbers);
                int lowest = Collections.min(playerCardNumbers);
                        
                for (i = 0; i < playerCardNumbers.size(); i++) {
                    int currentCard = playerCardNumbers.get(i);
                            
                    if (highest < currentCard) {
                        highest = currentCard;
                    }
                    if (highest == currentCard) {
                        if (lowest < currentCard) {
                            lowest = currentCard;
                        }
                    }
                }
                winner = player;
            }
        }
        System.out.println(winner + "has won the game!");
    }
}  