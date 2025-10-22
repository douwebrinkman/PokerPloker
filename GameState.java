import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

public class GameState {
    int turnNum;
    int r = 0;
    int partRound;
    ArrayList<JLabel> p = points();
    ArrayList<String> playerList = new ArrayList<>();
    ArrayList<Integer> money = new ArrayList<>();
    ArrayList<Integer> bet = new ArrayList<>();
    ArrayList<Integer> folded = new ArrayList<>();
    JFormattedTextField startAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    JFormattedTextField startingBet = new JFormattedTextField(NumberFormat.getIntegerInstance());
    static ArrayList<String> drawnCards = new ArrayList<String>();
    ArrayList<String> cards = cards();
    boolean foldedPlayer;
    JLabel middleCards = new JLabel("[?][?][?][?][?]");
    JLabel hand = new JLabel("[?][?]");
    JLabel itsTurn = new JLabel("its ... turn");
    JLabel potLabel = new JLabel("Pot: 0");
    ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> f;

    ArrayList<JLabel> points() {
        ArrayList<JLabel> points = new ArrayList<>();
        JLabel zero = new JLabel();
        zero.setBounds(100, 300, 200, 100);
        JLabel one = new JLabel();
        one.setBounds(200, 300, 100, 100);
        JLabel two = new JLabel();
        two.setBounds(300, 300, 200, 100);
        JLabel three = new JLabel();
        three.setBounds(400, 300, 200, 100);
        JLabel four = new JLabel();
        four.setBounds(500, 300, 200, 100);
        JLabel five = new JLabel();
        five.setBounds(600, 300, 100, 100);
        JLabel six = new JLabel();
        six.setBounds(700, 300, 100, 100);
        JLabel seven = new JLabel();
        seven.setBounds(800, 300, 100, 100);
        points.add(zero);
        points.add(one);
        points.add(two);
        points.add(three);
        points.add(four);
        points.add(five);
        points.add(six);
        points.add(seven);
        return points;
    }

    public void calcPot() {
        int pot = 0;
        for (int i = 0; i < playerList.size(); i++) {
            pot = pot + bet.get(i);
        }
        potLabel.setText("Pot: " + pot);
    }

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
        int anti = ((Number) startingBet.getValue()).intValue();
        for (int i = 0; i < playerList.size(); i++) {
            money.set(i, (money.get(i) - anti));
            bet.add(anti);
            String betNum = bet.get(i).toString(); //we use it 4 times maybe make a method
            String chips = money.get(i).toString();
            String name = playerList.get(i);
            String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Chips: " + betNum + "</html>";
            p.get(i).setText(text);  
        }
        calcPot();
        // sleep 2 sec
        partRound = 1;
        turn2(0);
    }

    public void turn2(int k) {
        for (int j = 0; j < folded.size(); j++) {
            if (k == folded.get(j)) {
                foldedPlayer = true;
                break;
            }
        }
        if (foldedPlayer) {
            foldedPlayer = false;
            nextTurn(k);
        } else {
            turnNum = k;
            hand.setText("[?][?]");
            itsTurn.setText("Its " + playerList.get(k) + " turn");
            if (checkBot(playerList.get(k))) {
                //(buttons should be gray)turn buttons gray
                //sleep for 2 sec
                //turn label back,
                System.out.println("kaas??");
            } else {
                System.out.println("turn: " + k);
                //turn buttons green
                //change (showcards) to k
                f = timer.schedule(() -> {
                    System.out.println("werkt ni");
                    timeOutCheck(k); // just do a check if no input
                    timer.shutdown();
                }, 400, TimeUnit.SECONDS); //400 sec time
                //set turnlabel back       
            }
        }

    }

    void cancelTimer(int turn) {
        if (!f.isDone()) {
            f.cancel(true);
            //timer.shutdown();
            //input = false;
            nextTurn(turn);
            System.out.println("Button pressed — timer canceled.");
        }

    }

    void nextTurn(int k) {
        if (((r == 0) && (k + 1 == playerList.size())) || (r == k + 1)) {
            r = 0;
            String a = drawnCards.get(0);
            String b = drawnCards.get(1);
            String c = drawnCards.get(2);
            String d = drawnCards.get(3);
            String e = drawnCards.get(4);
            if (partRound == 1) {
                System.out.println("ronde 2");
                calcPot();
                middleCards.setText("[" + a + "][" + b + "][" + c + "][?][?]");
                partRound = 2;
                turn2(0);
            } else if (partRound == 2) {
                calcPot();
                middleCards.setText("[" + a + "][" + b + "][" + c + "][" + d + "][?]");
                //reveal 1 more card
                partRound = 3;
                turn2(0);
            } else if (partRound == 3) {
                calcPot();
                middleCards.setText("[" + a + "][" + b + "][" + c + "][" + d + "][" + e + "]");
                partRound = 4;
                turn2(0);
            } else if (partRound == 4) {
                calcPot();
                //wincondition
                //if players fold, win should be faster
            }

            System.out.println("kaas");
        } else if (k + 1 < playerList.size()) {
            System.out.println("kaas1");
            turn2(k + 1);
        } else {
            System.out.println("kaas2");
            turn2(0);
        }
    }

    void timeOutCheck(int turn) {
        int diff = highestBet() - bet.get(turn);
        if (diff == 0) {
            //mnext turn
        } else if (diff < money.get(turn)) {
            bet.set(turn, highestBet());
            money.set(turn, money.get(turn) - diff);
        } else if (diff > money.get(turn)) {
            bet.set(turn, bet.get(turn) + money.get(turn));
        } 
        String betNum = bet.get(turn).toString();
        String chips = money.get(turn).toString();
        String name = playerList.get(turn);
        String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Chips: " + betNum + "</html>";
        p.get(turn).setText(text);  
        //input = false;
        nextTurn(turn);
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
    
    void drawCards(int numPlayers) {
        Random r = new Random();
        for (int i = 0; i < (5 + (2 * numPlayers)); i++) {
            int randomNum = r.nextInt(52 - i); // 0 t/m 51 
            drawnCards.add(cards.get(randomNum));
            cards.remove(randomNum);
        }
        System.out.println(drawnCards);
    } 

    int highestBet() { //int or integer?
        int max = bet.get(0);
        for (int i = 0; i < bet.size(); i++) {
            if (max < bet.get(i)) {
                max = bet.get(i);
            }
            
        }
        return max;
    }
}
