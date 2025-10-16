import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JPanel {
    JFormattedTextField raiseAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    GameState state = null;

    GameMenu(GameState state) {
        super();
        super.add(raiseAmount);
        this.state = state;
        super.add(raiseButton(state.turnNum));
        super.add(foldButton(state.turnNum));
        super.add(checkButton(state.turnNum));
        for (int i = 0; i < state.playerList.size(); i++) {
            JLabel name = new JLabel(state.playerList.get(i));
            JLabel chips = new JLabel(state.money.get(i).toString());
            super.add(name);
            JLabel chipsText = new JLabel("Chips:");
            super.add(chipsText);
            super.add(chips);
        }
        
    }

    JButton raiseButton(int turn) {
        JButton button = new JButton("Raise");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int raise = (int) raiseAmount.getValue();
                if (raise <= state.money.get(turn)) {
                    state.money.set(turn, state.money.get(turn) - raise);
                    state.bet.set(turn, state.bet.get(turn) + raise); // also add to pot?
                    //end turn 
                }
            }
        });
        return button;
    }

    JButton checkButton(int turn) {
        JButton button = new JButton("Check");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state.bet.get(turn) == highestBet()) {
                    //mnext turn
                }
            }
        });
        return button;
    }

    JButton foldButton(int turn) {
        JButton button = new JButton("Fold");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //remove turn from turn()
                //if turn().size is 1 --> win turn.get(0)
                //else next turn
            }
            });
    
        return button;
    }

    int highestBet() {
        int max = state.bet.get(0);
        for (int i = 0; i < state.bet.size(); i++) {
            if (max < state.bet.get(i)) {
                max = state.bet.get(i);
            }
            
        }
        return max;
    }
}