import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel {
    JFormattedTextField raiseAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    static ArrayList<JLabel> p = points();
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
            p.get(i).setText(text); 
            super.add(p.get(i));
        }
        state.middleCards.setBounds(700, 400, 300, 300);
        super.add(state.middleCards);
        state.itsTurn.setBounds(700, 600, 200, 100);
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
        winnerList = new JComboBox<>(state.winnerList());
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
                if (state.roundRunning) {
                    int turn = state.turnNum;
                    int raise = ((Number) raiseAmount.getValue()).intValue();;
                    if (raise <= state.money.get(turn)) {
                        state.money.set(turn, state.money.get(turn) - raise);
                        state.bet.set(turn, state.bet.get(turn) + raise); // also add to pot?
                        state.setPlayerText(turn); 
                        state.r = turn;
                        state.cancelTimer(turn);
                        System.out.println("raised"); 
                    }
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
                if (state.roundRunning) {
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
                    } else if (diff > state.money.get(turn)) { // all in
                        state.bet.set(turn, state.bet.get(turn) + state.money.get(turn));
                        state.money.set(turn, 0);
                        state.cancelTimer(turn);
                        System.out.println("all in");
                    }
                    state.setPlayerText(turn); 
                }
            }
        });
        return button;
    }

    JButton foldButton() {
        JButton button = new JButton("Fold");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state.roundRunning) {
                    int turn = state.turnNum;
                    state.folded.add(turn);
                    System.out.println(turn + " folded");
                    int length = state.folded.size();
                    if (length == (state.playerList.size() - 1)) {
                        Collections.sort(state.folded);
                        if (state.folded.get(0) != 0) {
                            state.playerWon(0);
                        } else if (state.folded.get(length - 1) != (length)) {
                            state.playerWon(length);
                        } else {
                            for (int i = 0; i < length - 1; i++) {
                                int current = state.folded.get(i);
                                int next = state.folded.get(i + 1);
                                if (next - current > 1) {
                                    state.playerWon(i + 1);
                                }
                            }
                        }
                    } else {
                        state.cancelTimer(turn);
                    }
                }  
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
                    state.playerWon(player);
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
                if (state.roundRunning) {
                    int turn = state.turnNum;
                    state.hand.setText(state.getPlayerCards(turn)); 
                } 
            }
            });
        return button;
    }

    static ArrayList<JLabel> points() {
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
}