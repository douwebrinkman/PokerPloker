/*
 * in the create menu you will create a game. you can add bots, you can maybe add players
 * 
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class CreateMenu extends JPanel {
    public static ArrayList<String> playerList = new ArrayList<>();
    public static ArrayList<Integer> money = new ArrayList<>();
    public static ArrayList<Integer> bet = new ArrayList<>();
    public static JFormattedTextField startAmount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    public static JFormattedTextField startingBet = new JFormattedTextField(NumberFormat.getIntegerInstance());
    JComboBox<String> botList = new JComboBox<>(Bots.botNames());
    JTextField nameBox = new JTextField("Player 1");
    JLabel list = new JLabel("Players: " + playerList.toString());
    JLabel error = new JLabel("");
    

    

    CreateMenu(Menu menu) {
        super();
        super.add(botList);
        super.add(botButton());
        JLabel name = new JLabel("Name: ");
        super.add(name);
        super.add(nameBox);
        super.add(playerButton());
        super.add(list);
        JLabel amountText = new JLabel("Starting Amount: ");
        super.add(amountText);
        startAmount.setValue(1000);
        startAmount.setColumns(10);
        super.add(startAmount);
        JLabel betText = new JLabel("Starting Bet: ");
        super.add(betText);
        startingBet.setValue(10);
        startingBet.setColumns(5);
        super.add(startingBet); // werkt ook nog niet
        if (playerList.size() == 7) {
            JLabel max = new JLabel("Max Players is 8");
            super.add(max); // werkt nog niet
        }
        super.add(startButton(menu));
        super.add(error);
        //add startingBet input, add start button that will start if there are 2 or more players 
        // and correct startAmount and startingBet
    

    }

    JButton botButton() {
        JButton button = new JButton("Add Bot");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!botList.getSelectedItem().equals("select a bot"))&&(playerList.size() < 8)) {
                    playerList.add((String) botList.getSelectedItem());
                    list.setText("Players: " + playerList.toString());
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
                if (playerList.size() < 8) {
                    playerList.add(nameBox.getText());
                    list.setText("Players: " + playerList.toString());
                }  
            }
        });
        return button;
    }

    JButton startButton(Menu menu) {
        JButton button = new JButton("START");
        CreateMenu self = this;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((playerList.size() < 2)) {
                    error.setText("The game should have at least 2 players");
                } else if ((int) startAmount.getValue() <= 0) {
                    error.setText("The start amount should me higher than 0");
                } else if ((int) startingBet.getValue() <= 0) {
                    error.setText("The starting bet value should be higher than 0");
                } else if ((int) startingBet.getValue() >= (int) startAmount.getValue()) {
                    error.setText("The starting bet value should be lower than the start amount");
                } else {
                    menu.close(self);
                    menu.open(new GameMenu());
                    for (int i = 0; i < CreateMenu.playerList.size(); i++) {
                        money.add((int) CreateMenu.startAmount.getValue());
                        
                    }
                }
            }
        });
        return button;
    }
}