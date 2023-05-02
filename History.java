import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class History extends MasterMind {

    private final HashMap<ArrayList<String>, String> history = new HashMap<>();

    History(){

    }

    public void add(ArrayList<String> guess, String response){
        history.put(guess, response);
    }


    //Prints from the HashMap of previous inputs so the user can see what they have input
    public void getHistory(){
        System.out.println("Feedback: 'h 'means hit -> a peg was the correct color in the correct position.\n" +
                           "          'm' means hiss -> a peg was the correct color in the wrong position.\n " +
                           "          No feedback for a peg means the peg was the wrong color (it was not in the secret code).");
        for(Map.Entry<ArrayList<String>, String> attempt: history.entrySet()){
            ArrayList<String> guess = attempt.getKey();
            String response = attempt.getValue();

            System.out.println("Guess: " + guess + " Response: " + response);
        }
    }
}
