import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

public class Game extends JPanel {
    JFormattedTextField raiseAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    GameState state = null;

    Game(GameState state) {
        super();
        this.state = state;
        super.setLayout(null);
        raiseAmount.setBounds(100, 700, 100, 50);
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
            String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Chips: " + bet + "</html>";
            state.p.get(i).setText(text); 
            super.add(state.p.get(i));
        }
        state.middleCards.setBounds(700, 400, 300, 300);
        super.add(state.middleCards);
        state.itsTurn.setBounds(700, 600, 100, 100);
        super.add(state.itsTurn);
        JButton showCardsButton = showCardsButton(state.turnNum);
        showCardsButton.setBounds(1200, 800, 200, 100);
        super.add(showCardsButton);
        JButton startButton = startButton();
        startButton.setBounds(1000, 100, 200, 100);
        super.add(startButton);
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
                int raise = (int) raiseAmount.getValue();
                if (raise <= state.money.get(turn)) {
                    state.money.set(turn, state.money.get(turn) - raise);
                    state.bet.set(turn, state.bet.get(turn) + raise); // also add to pot?
                    String bet = state.bet.get(turn).toString();
                    String chips = state.money.get(turn).toString();
                    String name = state.playerList.get(turn);
                    String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Chips: " + bet + "</html>";
                    state.p.get(turn).setText(text); 
                    state.r = turn;
                    //state.input = true;
                    state.cancelTimer(turn);
                    System.out.println("dit werkt5"); 
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
                    //state.input = true;
                    state.cancelTimer(turn);
                    System.out.println("dit werkt1");
                    //mnext turn
                } else if (diff < state.money.get(turn)) {
                    state.bet.set(turn, state.highestBet());
                    state.money.set(turn, state.money.get(turn) - diff);
                    //state.input = true;
                    state.cancelTimer(turn);
                    System.out.println("dit werkt2");
                } else if (diff > state.money.get(turn)) {
                    state.bet.set(turn, state.bet.get(turn) + state.money.get(turn));
                    state.money.set(turn, 0);
                    //state.input = true;
                    state.cancelTimer(turn);
                    System.out.println("dit werkt3");
                }
                String bet = state.bet.get(turn).toString();
                String chips = state.money.get(turn).toString();
                String name = state.playerList.get(turn);
                String text = "<html>" + name + "<br/>Chips: " + chips + "<br/>Chips: " + bet + "</html>";
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
                //state.input = true;
                System.out.println("dit werkt");
                //remove turn from turn()
                //if turn().size is 1 --> win turn.get(0)
                //else next turn
                state.cancelTimer(turn);
            }
            });
    
        return button;
    }

    JButton startButton() {
        JButton button = new JButton("Start Round");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state.round();
                state.drawCards(state.playerList.size());
            }
            });
    
        return button;
    }

    JButton showCardsButton(int turn) {
        JButton button = new JButton("Show Cards");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = GameState.drawnCards.get(turn + 5);
                String b = GameState.drawnCards.get(turn + 6);
                state.hand.setText("[" + a + "]" + "[" + b + "]");  
            }
            });
    
        return button;
    }

    
}