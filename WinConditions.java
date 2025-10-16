/*Check the highest card of each player.
 * Compare the highest cards of the players and check which one is the highest card of those.
 */

public class WinConditions {
    String input = Game.drawnCards.toString();
    String numbers = input.substring(1);
    int number = Integer.parseInt(numbers);

    void CheckHighest(int i) {
        int highest = number;
        for (i = 5; i < Game.drawnCards.size(); i++) {
            if (highest < ) {

            }
        }
    }

   /*  int highest; //Your personal highest card.
    int otherHighest; //Highest card of other players/bots.
    int reallyHighest; //Highest card of all the highest cards.

    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 13; j++) {
            if (... > ...) {
                ... = highest;
            }
            if (highest > otherHighest) {
                highest = reallyHighest;
                System.out.println("Congratulations! You won!");
            } else {
                System.out.println("Sorry, you lost.");
            }
        }
    }*/
}