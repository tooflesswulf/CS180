package blackbox;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * GuessManager
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 2/23/18
 *
 */

public class GuessManager {
    private ArrayList<Integer[]> guesses;
    private int nball;
    private BlackBox bb;
    
    public GuessManager(int nball, BlackBox bb) {
        this.bb = bb;
        
        setNball(nball);
        this.guesses = new ArrayList<>();
    }
    
    public ArrayList<Integer[]> getGuesses() {
        return guesses;
    }
    
    public int getNball() {return nball;}
    private void setNball(int nball) {
        this.nball = nball;
        guesses = new ArrayList<>();
    }
    
    public int getGuessLeft() {
        return nball - guesses.size();
    }
    
    public boolean addGuess(int r, int c) {
        Integer[] pt_add = {r, c};
        Iterator<Integer[]> it = guesses.iterator();
        while(it.hasNext()) {
            Integer[] pt = it.next();
            if(pt[0].equals(pt_add[0]) && pt[1].equals(pt_add[1])) {
                it.remove();
                return true;
            }
        }
        
        if(getGuessLeft() <= 0) return false;
        
        guesses.add(pt_add);
        bb.loadBox();
        return true;
    }
}