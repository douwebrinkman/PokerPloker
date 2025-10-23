import java.util.Random;

public class Bots {
    Random b = new Random();
    
    void birgin() {
        if (GameState.partRound == 1) {
            int randomNum = b.nextInt(10); //0-9
            if (randomNum < 7) {
                //fold
            } else if (randomNum == 7 || randomNum == 8) { 
                //check
            } else {
                //raise money/10
            }
        } else {
            int randomNum = b.nextInt(10); //0-9
            if (GameState.r == 0 && randomNum < 7) {
                //fold
            } else if (randomNum < 5) {
                //raise money/5
            } else {
                //check
            }
        }
    }

    void raiser() {
        
    }

    static String[] botNames() {
        String[] botNames = {"select a bot", " Birgin", " Raiser"}; 
        //space before name so no confusion if player name == botname
        return botNames;                                 
    }


}
