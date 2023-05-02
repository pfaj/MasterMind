import java.util.ArrayList;
import java.util.HashMap;

public class Peg {

    private ArrayList<String> guessOrder;


    //Constructors
    Peg(){
        this("");
    }
    Peg(String guess){
        setGuess(guess);
    }

    Peg(ArrayList<String> guessOrder){
        this.guessOrder = guessOrder;
    }
    //Setters
    public void setGuess(String guess){
        String[] x = guess.split("");
        ArrayList<String> guessArray = new ArrayList<String>();
        for(String color: x){
            guessArray.add(color);
        }
        this.guessOrder = guessArray;
    }


    //Getters
    public ArrayList<String> getGuessOrder() {
        return guessOrder;
    }

    //Methods
    //Used to create a copy of the order so the main one is not tampered with
    public ArrayList<String> copyOrder() {
        ArrayList<String> copy = new ArrayList<>();
        for (String color : guessOrder) {
            copy.add(color);
        }
        return copy;
    }


}
