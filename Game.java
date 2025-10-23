import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

public class Game extends JPanel {
    JFormattedTextField raiseAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    JComboBox<String> winnerList;
    GameState state = null;

    Game(GameState state) {
        super();
        this.state = state;
        super.setLayout(null);
        raiseAmount.setBounds(100, 700, 100, 50);
        raiseAmount.setValue(10);
        super.add(raiseAmount);
        JButton raiseButton = raiseButton();
        raiseButton.setBounds(100, 800, 200, 100);
        super.add(raiseButton);
        JButton foldButton = foldButton();
        foldButton.setBounds(300, 800, 200, 100);
        super.add(foldButton);
        JButton checkButton = checkButton();
        checkButton.setBounds(500, 800, 200, 100);
        super.add(checkButton);
        for (int i = 0; i < state.playerList.size(); i++) {
            String bet = "0"; //could make this a method?
            String chips = state.money.get(i).toString();
            String name = state.playerList.get(i);
            String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Bet: " + bet + "</html>";
            state.p.get(i).setText(text); 
            super.add(state.p.get(i));
        }
        state.middleCards.setBounds(700, 400, 300, 300);
        super.add(state.middleCards);
        state.itsTurn.setBounds(700, 600, 100, 100);
        super.add(state.itsTurn);
        JButton showCardsButton = showCardsButton();
        showCardsButton.setBounds(1200, 800, 200, 100);
        super.add(showCardsButton);
        JButton startButton = startButton();
        startButton.setBounds(1000, 100, 200, 100);
        super.add(startButton);
        JButton chooseButton = chooseWinner();
        chooseButton.setBounds(1200, 100, 200, 50);
        super.add(chooseButton);
        JComboBox<String> winnerList = new JComboBox<>(state.winnerList());
        winnerList.setBounds(1200, 150, 200, 50);
        super.add(winnerList);
        state.hand.setBounds(1200, 600, 100, 200);
        super.add(state.hand);
        state.potLabel.setBounds(700, 400, 100, 100);
        super.add(state.potLabel);
        // should add pot, can do it by just adding the bet array every time.
        
    }

    JButton raiseButton() {
        JButton button = new JButton("Raise"); 
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int turn = state.turnNum;
                int raise = ((Number) raiseAmount.getValue()).intValue();;
                if (raise <= state.money.get(turn)) {
                    state.money.set(turn, state.money.get(turn) - raise);
                    state.bet.set(turn, state.bet.get(turn) + raise); // also add to pot?
                    String bet = state.bet.get(turn).toString();
                    String chips = state.money.get(turn).toString();
                    String name = state.playerList.get(turn);
                    String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Bet: " + bet + "</html>";
                    state.p.get(turn).setText(text); 
                    state.r = turn;
                    state.cancelTimer(turn);
                    System.out.println("raise1"); 
                }
            }
        });
        return button;
    }

    JButton checkButton() {
        JButton button = new JButton("Check");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int turn = state.turnNum;
                int diff = state.highestBet() - state.bet.get(turn);
                if (diff == 0) {
                    state.cancelTimer(turn);
                    System.out.println("check1");
                } else if (diff < state.money.get(turn)) {
                    state.bet.set(turn, state.highestBet());
                    state.money.set(turn, state.money.get(turn) - diff);
                    state.cancelTimer(turn);
                    System.out.println("check2");
                } else if (diff > state.money.get(turn)) {
                    state.bet.set(turn, state.bet.get(turn) + state.money.get(turn));
                    state.money.set(turn, 0);
                    state.cancelTimer(turn);
                    System.out.println("check3");
                }
                String bet = state.bet.get(turn).toString();
                String chips = state.money.get(turn).toString();
                String name = state.playerList.get(turn);
                String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Bet: " + bet + "</html>";
                state.p.get(turn).setText(text); 
            }
        });
        return button;
    }

    JButton foldButton() {
        JButton button = new JButton("Fold");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int turn = state.turnNum;
                state.folded.add(turn);
                System.out.println(turn + " folded");
                //if folded.size == playerlist.size - 1, 
                //then check which player not folded, give them pot and end round
                //else :
                state.cancelTimer(turn);
                
            }
            });
    
        return button;
    }

    JButton startButton() {
        JButton button = new JButton("Start New Round");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!state.roundRunning) {
                    state.round();
                    state.drawCards(state.playerList.size());
                }
            }
            });
    
        return button;
    }

    JButton chooseWinner() {
        JButton button = new JButton("Choose Winner");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state.roundEnded) {
                    int player = winnerList.getSelectedIndex();
                    state.money.set(player, (state.pot + state.money.get(player)));
                    state.roundRunning = false;
                } 
            }
            });
    
        return button;
    }

    JButton showCardsButton() {
        JButton button = new JButton("Show Cards");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int turn = state.turnNum;
                String a = GameState.drawnCards.get(turn + 5);
                String b = GameState.drawnCards.get(turn + 6);
                state.hand.setText("[" + a + "]" + "[" + b + "]");  
            }
            });
    
        return button;
    }

    
}