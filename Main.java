import graphics.Window;

public class Main {
    Window window = new Window();
    GameState state = new GameState();

    void run() {
        window.open(new CreateMenu(window, state));
    }

    public static void main(String[] args) {
        new Main().run();
    }

}
