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
        if (GameState.partRound == 1) {
            int randomNum = b.nextInt(10); //0-9
            if (randomNum < 2) {
                //fold
            } else if (randomNum == 2 || randomNum == 4) { 
                //check
            } else {
                //raise money/10
            }
        } else if (GameState.partRound == 2) {
            int randomNum = b.nextInt(10); //0-9
            if (GameState.r == 0 && randomNum < 8) {
                //raise money/5
            } else if (randomNum == 8) {
                //check
            } else {
                //fold
            }
        } else if (GameState.partRound == 3) {
            int randomNum = b.nextInt(10); //0-9
            if (GameState.r == 10 && randomNum < 7) {
                //raise
            } else if (randomNum == 7 || randomNum == 8) {
                //check
            } else {
                //fold
            }
        } else {
            int randomNum = b.nextInt(10); //0-9
            if (GameState.r > 10 && randomNum < 6) {
                //raise
            } else if (randomNum == 6 || randomNum == 7) {
                //check
            } else {
                //fold
            }
        }
    }

    void name() {
        if (GameState.partRound == 1) {
            int randomNum = b.nextInt(10); //0-9
            if (randomNum == 0) {
                //fold
            } else if (randomNum > 0 && randomNum < 4) { 
                //raise
            } else {
                //check
            }
        } else if (GameState.partRound == 2) {
            int randomNum = b.nextInt(10); //0-9
            if (GameState.r <= 20 && randomNum >= 6) {
                //check
            } else if ((GameState.r > 20 && Gamestate.r < 40) 
                && (randomNum == 5 || randomNum == 4)) {
                //raise
            } else {
                //fold
            }
        }
    }

    static String[] botNames() {
        String[] botNames = {"select a bot", " Birgin", " Raiser"}; 
        //space before name so no confusion if player name == botname
        return botNames;                                 
    }


}
