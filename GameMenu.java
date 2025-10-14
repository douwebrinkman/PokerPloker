import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JPanel {
    JFormattedTextField raiseAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());

    GameMenu() {
        super();
        super.add(raiseAmount);
        super.add(raiseButton(turn));
        super.add(foldButton(turn));
        super.add(checkButton(turn));
        for (int i = 0; i < CreateMenu.playerList.size(); i++) {
            JLabel name = new JLabel(CreateMenu.playerList.get(i));
            //JLabel chips = new JLabel(CreateMenu.money.get(i).toString()); crasht
            super.add(name);
            //System.out.println((String) CreateMenu.money.get(i));
            JLabel chipsText = new JLabel("Chips:");
            super.add(chipsText);
            //super.add(chips);
        }
        
    }

    JButton raiseButton(int turn) {
        JButton button = new JButton("Raise");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int raise = (int) raiseAmount.getValue();
                if (raise <= CreateMenu.money.get(turn)) {
                    CreateMenu.money.set(turn, CreateMenu.money.get(turn) - raise);
                    CreateMenu.bet.set(turn, CreateMenu.bet.get(turn) + raise); // also add to pot?
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
                if (CreateMenu.bet.get(turn) == highestBet()) {
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
        int max = CreateMenu.bet.get(0);
        for (int i = 0; i < CreateMenu.bet.size(); i++) {
            if (max < CreateMenu.bet.get(i)) {
                max = CreateMenu.bet.get(i);
            }
            
        }
        return max;
    }
}