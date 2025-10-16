package graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Window {
    JFrame frame = new JFrame("JOKER POKER");

    public Window() {
        frame.setSize(500, 600);
        frame.setVisible(true); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public void close(JPanel v) {
        frame.remove(v);
    }
    
    public void open(JPanel q) {
        frame.add(q);
        updateFrame();
    }

    public void updateFrame() {
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
