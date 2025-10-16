import javax.swing.*;

public class Main {
    JFrame frame = new JFrame("JOKER POKER");

    void run() {
        frame.add(new CreateMenu(this, GameState state));
        
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

    public static void main(String[] args) {
        new Main().run();
    }

}
