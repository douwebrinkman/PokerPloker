public class Bots {
    
    void birgin() {

    }

    void raiser() {
        
    }

    static String[] botNames() {
        String[] botNames = {"select a bot", " Birgin", " Raiser"}; //space before name so no confusion
        return botNames;                                 //if player name == botname
    }

    public static boolean checkBot(String a) {
        if (a.equals(" Birgin")) {
            //return or run birgin()
            return true;
        } else if (a.equals(" Raiser")) {
            //raiser();
            return true;
        } else {
            return false;
        }
    }
}
