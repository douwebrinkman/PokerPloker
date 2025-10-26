import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
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
    int pot;
    ArrayList<String> playerList = new ArrayList<>();
    ArrayList<Integer> money = new ArrayList<>();
    ArrayList<Integer> bet = new ArrayList<>();
    ArrayList<Integer> folded;
    JFormattedTextField startAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    JFormattedTextField startingBet = new JFormattedTextField(NumberFormat.getIntegerInstance());
    ArrayList<String> drawnCards;
    ArrayList<String> cards;
    boolean foldedPlayer;
    boolean roundEnded = false;
    boolean roundRunning = false;
    JLabel middleCards = new JLabel("[?][?][?][?][?]");
    JLabel hand = new JLabel("[?][?]");
    JLabel itsTurn = new JLabel("its ... turn");
    JLabel potLabel = new JLabel("Pot: 0");
    ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> f;

    public void calcPot() {
        pot = 0;
        for (int i = 0; i < playerList.size(); i++) {
            pot = pot + bet.get(i);
        }
        potLabel.setText("Pot: " + pot);
    }

    public boolean checkBot(String a) {
        if (a.equals(" Birgin")) {
            birgin();
            return true;
        } else if (a.equals(" Raiser")) {
            //raiser();
            return true;
        } else {
            return false;
        }
    }

    public void setPlayerText(int n) {
        String betNum = bet.get(n).toString(); 
        String chips = money.get(n).toString();
        String name = playerList.get(n);
        String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Bet: " + betNum + "</html>";
        Game.p.get(n).setText(text);  
    }

    public void round() {
        roundRunning = true;
        roundEnded = false;
        folded = new ArrayList<>();
        drawnCards = new ArrayList<String>();
        cards = cards();
        middleCards.setText("[?][?][?][?][?]");
        int ante = ((Number) startingBet.getValue()).intValue();
        for (int i = 0; i < playerList.size(); i++) {
            if (money.get(i) >= ante) {
                money.set(i, (money.get(i) - ante)); 
                bet.set(i, ante);
                setPlayerText(i); 
            } else { //if ante cannot be paid, player will not play and be folded;
                folded.add(i);
            }
        }
        calcPot();
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
                
            } else {
                System.out.println("turn: " + k);
                f = timer.schedule(() -> {
                    System.out.println("timeOut");
                    timeOutCheck(k); // just do a check if no input
                    timer.shutdown();
                }, 400, TimeUnit.SECONDS); //400 sec time     
            }
        }

    }

    void cancelTimer(int turn) {
        if (!f.isDone()) {
            f.cancel(true);
            //timer.shutdown();
            nextTurn(turn);
            System.out.println("Button pressed timer canceled.");
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
                partRound = 3;
                turn2(0);
            } else if (partRound == 3) {
                calcPot();
                middleCards.setText("[" + a + "][" + b + "][" + c + "][" + d + "][" + e + "]");
                partRound = 4;
                turn2(0);
            } else if (partRound == 4) {
                calcPot();
                roundEnded = true;
                System.out.println("choose winner");
                showAllCards();
            }
        } else if (k + 1 < playerList.size()) {
            turn2(k + 1);
        } else {
            turn2(0);
        }
    }

    void timeOutCheck(int turn) {
        int diff = highestBet() - bet.get(turn);
        if (diff == 0) {
            //next turn
        } else if (diff < money.get(turn)) {
            bet.set(turn, highestBet());
            money.set(turn, money.get(turn) - diff);
        } else if (diff > money.get(turn)) {
            bet.set(turn, bet.get(turn) + money.get(turn));
        } 
        setPlayerText(turn);
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

    int highestBet() {
        int max = bet.get(0);
        for (int i = 0; i < bet.size(); i++) {
            if (max < bet.get(i)) {
                max = bet.get(i);
            }
            
        }
        return max;
    }

    public String[] winnerList() {
        String[] l = new String[playerList.size()];
        for (int i = 0; i < playerList.size(); i++) {
            l[i] = (playerList.get(i));
        }
        System.out.println(l);
        return l;
    }

    public void playerWon(int player) {
        money.set(player, (pot + money.get(player)));
        for (int i = 0; i < playerList.size(); i++) {
            bet.set(i, 0);
            setPlayerText(i);
        }
        itsTurn.setText(playerList.get(player) + " won, start new round");
        roundRunning = false;
    }

    public String getPlayerCards(int player) {
        String a = drawnCards.get((player * 2) + 5);
        String b = drawnCards.get((player * 2) + 6);
        String hand = "[" + a + "]" + "[" + b + "]";
        return hand;
    }

    public void showAllCards() {
        for (int n = 0; n < playerList.size(); n++) {
            String betNum = bet.get(n).toString(); 
            String chips = money.get(n).toString();
            String name = playerList.get(n);
            String handCards = getPlayerCards(n);
            String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Bet: " + betNum 
                + "<br/>Cards: " + handCards + "</html>";
            Game.p.get(n).setText(text);
        }  
    }
    void check() {
        int turn = turnNum;
        int diff = highestBet() - bet.get(turn);
        if (diff == 0) {
            cancelTimer(turn);
            System.out.println("check1");
        } else if (diff < money.get(turn)) {
            bet.set(turn, highestBet());
            money.set(turn, money.get(turn) - diff);
            cancelTimer(turn);
            System.out.println("check2");
        } else if (diff > money.get(turn)) { // all in
            bet.set(turn, bet.get(turn) + money.get(turn));
            money.set(turn, 0);
            cancelTimer(turn);
            System.out.println("all in");
        }
        setPlayerText(turn); 
    }

    void raise(int raise) {
        int turn = turnNum;
        if (raise <= money.get(turn)) {
            money.set(turn, money.get(turn) - raise);
            bet.set(turn, bet.get(turn) + raise); // also add to pot?
            setPlayerText(turn); 
            r = turn;
            cancelTimer(turn);
            System.out.println("raised"); 
        }
    }

    void fold() {
        int turn = turnNum;
        folded.add(turn);
        System.out.println(turn + " folded");
        int length = folded.size();
        if (length == (playerList.size() - 1)) {
            Collections.sort(folded);
            if (folded.get(0) != 0) {
                playerWon(0);
            } else if (folded.get(length - 1) != (length)) {
                playerWon(length);
            } else {
                for (int i = 0; i < length - 1; i++) {
                    int current = folded.get(i);
                    int next = folded.get(i + 1);
                    if (next - current > 1) {
                        playerWon(i + 1);
                    }
                }
            }
        } else {
            cancelTimer(turn);
        }
    }

    static String[] botNames() {
        String[] botNames = {"select a bot", " Birgin", " Raiser"}; 
        //space before name so no confusion if player name == botname
        return botNames;                                 
    }

    void birgin() {
        Random b = new Random();
        if (partRound == 1) {
            int randomNum = b.nextInt(10); //0-9
            if (randomNum < 7) {
                fold();
            } else if (randomNum == 7 || randomNum == 8) { 
                check();
            } else {
                raise(money.get(turnNum)/10);
            }
        } else {
            int randomNum = b.nextInt(10); //0-9
            if (r == 0 && randomNum < 7) {
                fold();
            } else if (randomNum < 5) {
                raise(money.get(turnNum/5));
            } else {
                check();
            }
        }
    }
}
