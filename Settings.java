import graphics.Window;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Settings extends JPanel {
    
    Settings(Window window) {
        super(new GridLayout());
        JButton back = new JButton("Back");
        JLabel text = new JLabel("Settings");
        JPanel settings = new JPanel();
        JLabel textS = new JLabel("Sounds");
        JRadioButton soundOn = new JRadioButton("On");
        JRadioButton soundOff = new JRadioButton("Off");
        ButtonGroup groupS = new ButtonGroup();
        groupS.add(soundOn);
        groupS.add(soundOff);
        JLabel textM = new JLabel("Music");
        JRadioButton musicOn = new JRadioButton("On");
        JRadioButton musicOff = new JRadioButton("Off");
        ButtonGroup groupM = new ButtonGroup();
        groupM.add(musicOn);
        groupM.add(musicOff);
        settings.setLayout(new BoxLayout(settings, BoxLayout.Y_AXIS));
        settings.add(back);
        settings.add(text);
        settings.add(textS);
        settings.add(soundOn);
        settings.add(soundOff);
        settings.add(textM);
        settings.add(musicOn);
        settings.add(musicOff);
        super.add(settings);
        Settings self = this;

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.close(self);
                window.open(new OptionMenu(window));
            }
        });
    }
}
