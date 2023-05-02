import java.util.ArrayList;
import java.util.HashMap;

public class GamePegs extends Peg{

    //Fields
    private boolean win;


    //Constructors
    GamePegs(){
        this(4,false);
    }
    GamePegs(int pegs, boolean win){
        super();
        String order = rColor(pegs);
        super.setGuess(order);
        setWin(false);

    }

    GamePegs(ArrayList<String> pegs){
        super(pegs);
    }

    //Setters
    public void setWin(boolean win) {
        if(win){
            System.out.println("You won! \nThe order was:" + getGuessOrder());
        }
        this.win = win;
    }

    //This method creates a list of random numbers and then calls colorConvert
    // to then set the color order to a random order for guessing
    public String rColor(int pegCount){
        ArrayList<Integer> rOrder =new ArrayList<>(pegCount);
        for(int i=0; i < pegCount; i++){
            int randResult = (int)(Math.random() * 6);
            rOrder.add(randResult);
        }
        return colorConvert(rOrder);

    }

    //Getters
    public boolean getWin(){
        return win;
    }

    //Methods

    //Color convert takes the random list of integers and corresponds it to a color value
    private String colorConvert(ArrayList<Integer> rPeg){
        String rOrder ="";
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(0,"g");
        map.put(1,"b");
        map.put(2,"a");
        map.put(3,"r");
        map.put(4,"y");
        map.put(5,"p");
        for(int i = 0; i < rPeg.size(); i++){
            rOrder  += map.get(rPeg.get(i));
        }
        return rOrder;
    }
}
