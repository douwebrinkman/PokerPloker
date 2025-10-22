/*
 * in the create menu you will create a game. you can add bots, you can maybe add players
 * 
 * 
 */
import graphics.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CreateMenu extends JPanel {
    JComboBox<String> botList = new JComboBox<>(Bots.botNames());
    JTextField nameBox = new JTextField("Player 1");
    JLabel error = new JLabel("");
    JLabel list = new JLabel("Players: ");
    GameState state = null;

    CreateMenu(Window window, GameState state) {
        super();
        this.state = state;
        super.add(botList);
        super.add(botButton());
        JLabel name = new JLabel("Name: ");
        super.add(name);
        super.add(nameBox);
        super.add(playerButton());
        super.add(list);
        JLabel amountText = new JLabel("Starting Amount: ");
        super.add(amountText);
        state.startAmount.setValue(1000);
        state.startAmount.setColumns(10);
        super.add(state.startAmount);
        JLabel betText = new JLabel("Starting Bet: ");
        super.add(betText);
        state.startingBet.setValue(10);
        state.startingBet.setColumns(5);
        super.add(state.startingBet); // werkt ook nog niet
        if (state.playerList.size() == 7) {
            JLabel max = new JLabel("Max Players is 8");
            super.add(max); // werkt nog niet
        }
        super.add(startButton(window));
        super.add(error);
        //add startingBet input, add start button that will start if there are 2 or more players 
        // and correct startAmount and startingBet
    

    }

    JButton botButton() {
        JButton button = new JButton("Add Bot");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!botList.getSelectedItem().equals("select a bot")) && (state.playerList.size() < 8)) {
                    state.playerList.add((String) botList.getSelectedItem());
                    list.setText("Players: " + state.playerList.toString());
                }
            }
        });
        return button;
    }

    JButton playerButton() {
        JButton button = new JButton("Add Player");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state.playerList.size() < 8) {
                    state.playerList.add(nameBox.getText());
                    list.setText("Players: " + state.playerList.toString());
                }  
            }
        });
        return button;
    }

    JButton startButton(Window window) {
        JButton button = new JButton("START");
        CreateMenu self = this;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = ((Number) state.startAmount.getValue()).intValue();
                int bet = ((Number) state.startingBet.getValue()).intValue();
                if ((state.playerList.size() < 2)) {
                    error.setText("The game should have at least 2 players");
                } else if (amount <= 0) {
                    error.setText("The start amount should me higher than 0");
                } else if (bet <= 0) {
                    error.setText("The starting bet value should be higher than 0");
                } else if (bet >= amount) {
                    error.setText("The starting bet value should be lower than the start amount");
                } else {
                    for (int i = 0; i < state.playerList.size(); i++) {
                        state.money.add(amount);
                        
                    }
                    window.close(self);
                    window.open(new Game(state));
                    //state.round();
                  
                }
            }
        });
        return button;
    }
}