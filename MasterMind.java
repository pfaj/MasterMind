
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MasterMind implements Play {
    //The main method which keeps the game playing till won or reaches the max guess count
    public static void play() {
        int count = 0;
        GamePegs rPeg = new GamePegs(4, false);//Initializes the hidden order of pegs
        History history = new History();
        int guessCount = difficultySelect();
        startPrompt(guessCount);
        while(!rPeg.getWin() && count < guessCount) {
            String guessInput = guess(history);//Take input of text to make the guess order
            Peg guess = new Peg(guessInput);
            String response = check(rPeg,guess);
            history.add(guess.getGuessOrder(), response);
            count++; //Counts up for every guess
        }
        if(count == guessCount){
            gameLost(rPeg);
        }
    }

    //Initializes the game and prints the starting prompts
    public static void startPrompt(int guessCount){
        System.out.println("\n\n\n\n");
        System.out.println("Welcome to Mastermind you will have " + guessCount + " tries to get the right order.");
        System.out.println("To make your guess enter: " +
                "'g' for green 'b' for blue 'a' for gray " +
                "'r' for red 'y' for yellow 'p' for purple");
        System.out.println("If you want to guess red blue green gray then enter 'rbga'");
    }
    //This is used for when the game is lost to prompt the user that the game is over.
    public static void gameLost(GamePegs peg){
        System.out.println("You Lost!");
        System.out.println("The correct order was " + peg.getGuessOrder());
        playAgain();
    }

      public static void playAgain(){
        boolean valid = false;
        String input = null;
        while(!valid){
            System.out.println("\nWould you like to play again (y)es/(n)o:");
            Scanner scnr = new Scanner(System.in);
            input = scnr.nextLine();
            if(input == null){
                System.out.println("Invalid input!");
            }
            else if(input.equals("n")){
                valid = true;
                break;
            }
            else if(input.equals("y")){
                valid = true;
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");//clear console I couldn't find another way :(
                play();
            }
            else{
                System.out.println("Invalid input!");
            }
        }
    }
    //This takes the input from the user and checks the input
    //This method goes through and checks letter by letter to label
    //whether the guess was right or wrong returning what the response is
    public static String check(GamePegs rPegs,Peg gamePeg) {
        String response = "";
        ArrayList<String> random = rPegs.copyOrder();
        ArrayList<String> guess = gamePeg.copyOrder();
        if (random.equals(guess))    {
            rPegs.setWin(true);
            return "hhhh";
        }
        for (int i = 0; i < guess.size(); i++) {
            if (random.get(i).equals(guess.get(i))) {
                random.set(i,"");
                guess.set(i,"");
                response += "h";
            }
        }
            for (int i = 0; i < guess.size(); i++) {
                if (!(guess.get(i).equals("")) && random.contains(guess.get(i))){
                    response += "m";
                }
            }
            if(response.equals("")){
                response = "No hits.";
                System.out.println("No hits.");
            }else {
                System.out.println(response);
            }
        return response;
    }
    public static String guess(History history) {
        boolean valid = false;
        String guessInput = null;
        while  (!(valid)) {
            System.out.println("\nMake your guess (or type 'history'):");
            Scanner scnr = new Scanner(System.in);
            guessInput = scnr.nextLine();
            valid = guessCheck(guessInput, history);
        }
        return guessInput;
    }
    public static boolean guessCheck(String guessInput, History history){
        guessInput = guessInput.toLowerCase();
        if(guessInput.equals("history")){
            history.getHistory();
            return false;
        }
        else if(!(guessInput.length() == 4)){
            System.out.println("Invalid input! Please input a only 4 characters.");
            return false;
        }
        String[] guess = guessInput.split("");
        for(String color : guess){
            ArrayList<String> list = colorList();
            if(!(list.contains(color))){
                System.out.println("Invalid input! Please enter a correct color.");
                return false;
            }

        }
        return true;
    }
    //Takes the input from users and makes it a guess for the system to compare to the answer
    //This method is a redundancy check for the inputs to make sure that they will not cause an error.
    //Provides a list of valid colors, so they can be used for checking
    public static ArrayList<String> colorList(){
        ArrayList<String> map = new ArrayList<>();
        map.add("g");
        map.add("b");
        map.add("a");
        map.add("r");
        map.add("y");
        map.add("p");
        return map;
    }
    //Diffuculty Selector which alters the amount of guesses you will have
    public static int difficultySelect() {
        boolean valid = false;
        String input = null;
        while  (!(valid)) {
            System.out.println("\n\nSelect your difficulty:" +
                    "\n (e)Easy Mode: 15 attempts" +
                    "\n (n)Normal Mode: 10 attempts" +
                    "\n (h)Hard Mode: 5 attempts");
            Scanner scnr = new Scanner(System.in);
            input = scnr.nextLine();
            valid = difficultyCheck(input);
        }

        return difficultyList(input);
    }
    //This method is a redundancy check for the inputs to make sure that they will not cause an error.
    public static boolean difficultyCheck(String input) {
        input = input.toLowerCase();
        if (!(input.length() == 1)) {
            System.out.println("Invalid input! Please input a only 1 character.");
            return false;
        }
        String[] difficulty = input.split("");
        for (String selection : difficulty) {
            ArrayList<String> list = difficultyList();
            if (!(list.contains(selection))) {
                System.out.println("Invalid input! Please enter a correct selection.");
                return false;
            }
        }
        return true;
    }
    //Used for the difficulty check
    public static ArrayList<String> difficultyList(){
        ArrayList<String> map = new ArrayList<>();
        map.add("e");
        map.add("n");
        map.add("h");
        return map;
    }
    //Used to set the amount of guesses that were selected
    public static int difficultyList(String selection){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("e",15);
        map.put("n", 10);
        map.put("h", 5);

        return map.get(selection);
    }
}
