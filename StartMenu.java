/*
 * startscreen with logo and play button.
 * leads to option menu
 * 
 * 
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu extends JPanel {

    public StartMenu(Menu menu) {
        super();
        ImageIcon blah = new ImageIcon("logo.jpg");
        JLabel pic = new JLabel(blah);
        super.add(pic);
        JPanel pPanel = new JPanel(new GridBagLayout());
        //JButton pleeButton = new JButton();
        //pleeButton = playButton();
        //pPanel.add(pleeButton);
        JButton pButton = new JButton("Play"); //make text bigger and fonts and stuf
        pButton.setMargin(new Insets(50, 200, 50, 200));
        pPanel.add(pButton);
        super.add(pPanel);
        StartMenu self = this;
        pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    menu.close(self);
                    menu.open(new OptionMenu());
                
            }
        });
    }
    
    /*public static JButton playButton() {
        JButton pButton = new JButton("Play"); //make text bigger and fonts and stuf
        pButton.setMargin(new Insets(50, 200, 50, 200));
        pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Menu.close(startMenu());
                
            }
        });
        return pButton;
    } */
}