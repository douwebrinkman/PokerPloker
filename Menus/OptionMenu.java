/*
 * Option menu with 4 options, online and offline and for those options normal poker or jokerpoker.
 * for now and propably :( always, 
 * there will be an in contruction/coming soon over online and en jokerpoker
 * so for now offline normal poker will lead to the createmenu
 * also add button to setting menu for sound/music and idk
 */

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class OptionMenu extends JPanel {

    OptionMenu() {
        super(new GridLayout());
        JButton kaas = new JButton("kaas");
        JButton poker = new JButton("Poker");
        JButton jokerpoker = new JButton("JokerPoker");
        JButton poker2 = new JButton("Poker");
        JButton jokerpoker2 = new JButton("JokerPoker2");
        JLabel text = new JLabel("offline");
        JLabel textOn = new JLabel("online");
        JPanel online = new JPanel();
        online.setLayout((new BoxLayout(online, BoxLayout.Y_AXIS)));
        JPanel offline = new JPanel(new BorderLayout());
        offline.add(text, BorderLayout.PAGE_START);
        offline.add(poker, BorderLayout.CENTER);
        offline.add(jokerpoker, BorderLayout.PAGE_END);
        online.add(textOn);
        online.add(poker);
        online.add(jokerpoker2);
        super.add(online);
        super.add(offline);
    }
}