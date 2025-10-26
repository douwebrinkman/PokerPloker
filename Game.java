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
                    int raise = ((Number) raiseAmount.getValue()).intValue();
                    state.raise(raise);
                    System.out.println(raise);
                    System.out.println(state.highestBet() + state.bet.get(state.turnNum));
                    //raise <= money.get(turn)) && (raise >= highestBet() + bet.get(turn))
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
                    state.check();
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
                    state.fold();
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
                    state.hand.setText(state.getBothPlayerCards(turn)); 
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