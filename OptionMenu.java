/*
 * Option menu with 4 options, online and offline and for those options normal poker or jokerpoker.
 * for now and propably :( always, 
 * there will be an in contruction/coming soon over online and en jokerpoker
 * so for now offline normal poker will lead to the createmenu
 * also add button to setting menu for sound/music and idk
 */
import graphics.Window;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionMenu extends JPanel {

    OptionMenu(Window window) {
        super(new GridLayout());
        JButton poker = new JButton("Poker");
        JButton jokerpoker = new JButton("JokerPoker");
        JButton poker2 = new JButton("Poker");
        JButton jokerpoker2 = new JButton("JokerPoker2");
        JButton settings = new JButton("Settings");
        JLabel text = new JLabel("offline");
        JLabel textOn = new JLabel("online");
        JPanel online = new JPanel();
        online.setLayout(new BoxLayout(online, BoxLayout.Y_AXIS));
        JPanel offline = new JPanel();
        offline.setLayout(new BoxLayout(offline, BoxLayout.Y_AXIS));
        JPanel setting = new JPanel();
        setting.setLayout(new BoxLayout(setting, BoxLayout.Y_AXIS));
        //Color onlineRed = new Color(200, 0, 0);
        //online.setBackground(onlineRed);
        //Color offlineGreen = new Color(0, 200, 0);
        //offline.setBackground(offlineGreen);
        offline.add(text);
        offline.add(poker2);
        offline.add(jokerpoker);
        online.add(textOn);
        online.add(poker);
        online.add(jokerpoker2);
        setting.add(settings);
        super.add(online);
        super.add(offline);
        super.add(setting);
        OptionMenu self = this;

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.close(self);
                window.open(new Settings(window));
            }
        });
    }
}