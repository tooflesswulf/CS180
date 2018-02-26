package blackbox;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * blackbox.BlackBox
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 2/16/18
 *
 */


public class BlackBox {
    public static int highScore = -1;
    
    public static char[][] box;
    public static int score;
    public static int numball;
    public static int numlink;
    public static boolean end;
    public static int size;
    
    private GridManager gm;
    private GuessManager guesses;
    
    public BlackBox() {
        this(0, 0, 0);
    }
    
    public BlackBox(int size, int numball, int score) {
        this(size+2, numball, 0, true, score);
    }
    
    public BlackBox(int size, int numball, int numlink, boolean end, int score) {
        setNumball(numball);
        setSize(size-2);
        setEnd(end);
        setScore(score);
        numlink = gm.getNumlink();
    }

    public char[][] getBox() {return box;}
    public int getScore() {return score;}
    public int getNumball() {return numball;}
    public int getNumlink() {return numlink;}
    public boolean getEnd() {return end;}
    public int getSize() {return size;}
    
    public int getGuessLeft() {
        return guesses.getGuessLeft();
    }
    
    public boolean checkGuesses() {
        if(!getEnd()) return false;
        for(Integer[] pos:guesses.getGuesses()) {
            if(!gm.checkObstacle(pos[0], pos[1])) return false;
        }
        return true;
    }
    
    public void setScore(int score) {this.score = score;}
    public void setNumball(int nball) {
        numball = nball;
        guesses = new GuessManager(nball);
    }
    public void setEnd(boolean end) {this.end = end;}
    public void setSize(int size) {
        gm = new GridManager(size);
        loadBox();
    }
    
    /**
     * Sets the internal game field to be the same as `box`.
     * Ignores the ports specified by `box`.
     * @param box the preset field to copy.
     */
    public void setBox(char[][] box) {
        ArrayList<Integer[]> balls = new ArrayList<>();
        for(int r=0; r<box.length; ++r) {
            for(int c=0; c<box[r].length; ++c) {
                if(box[r][c]=='0') {
                    Integer[] pt = {r-1,c-1};
                    balls.add(pt);
                }
            }
        }
        
        setNumball(balls.size());
        setSize(box.length-2);
        gm.placeBalls(balls);
        
        loadBox();
    }
    
    private void loadBox() {
        box = gm.getGridChr(true);
        for(Integer[] pt:guesses.getGuesses()) {
            box[pt[0]][pt[1]] = '*';
        }
    }
    
    public void printbox() {
        StringBuilder sb = new StringBuilder("  ");
        int col_spacing = 3;
        
        //Print header
        for(int i=1; i<=gm.size+2; ++i) {
            sb.append(i);
    
            for(int j=String.valueOf(i).length(); j<col_spacing; ++j) sb.append(" ");
        }
        sb.append("\n ");
        
        //Add horizontal line
        for(int i=0; i<gm.size+2; ++i) for(int j=0; j<col_spacing; ++j) sb.append("=");
        sb.append("=\n");
        
        String[][] data = gm.getGrid(getEnd());
        
        for(Integer[] coords: guesses.getGuesses()) {
            int gr = coords[0]; int gc = coords[1];
            if(gr!=-1 && gc!=-1)
                data[gr+1][gc+1] = "*";
        }
        
        for(int r=0; r<gm.size+2; ++r) {
            sb.append(r+1);
            for(int c=0; c<gm.size+2; ++c) {
                sb.append("|");
                String to_app = data[r][c];
                sb.append(to_app);
                for(int k=1+to_app.length(); k<col_spacing; ++k) sb.append(" ");
            }
            sb.append("|\n");
        }
    
        //Add horizontal line
        sb.append(" ");
        for(int i=0; i<gm.size+2; ++i) for(int j=0; j<col_spacing; ++j) sb.append("=");
        sb.append("=\n");
    
        System.out.println();
        System.out.print(sb.toString());
    }
    
    public void initialize() {
        setScore(0);
        setNumball(guesses.getNball());
        gm.resetBalls(guesses.getNball());
        loadBox();
        end = false;
    }
    
    public void check(int r, int c) {
        if(gm.setStart(r-2, c-2)) {
            gm.getEndPoint();
            printbox();
            ++score;
        } else if(gm.inBounds(r-2, c-2)) {
            boolean success = guesses.addGuess(r-2, c-2);
    
            if(!success){
                System.out.println(String.format("You've already made %d guesses", guesses.getNball()));
                return;
            }
            
            printbox();
        } else {
            System.out.println("Bad input. Choose numbers that are in bound.");
        }
        loadBox();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BlackBox bb = new BlackBox();
        
        while(true) {
            int difficulty=getDifficulty(sc);
            if (difficulty < 0) break;
    
            bb.setSize(5+difficulty);
            bb.setNumball(3);
            bb.initialize();
            bb.printbox();
    
            int qcode = 0;
            while (true) {
                String in = getVerifiedInput(sc);
                if(in.equals("submit")) {
                    if(bb.getGuessLeft()==0) {
                        break;
                    } else {
                        System.out.println("You haven't guessed enough times yet.");
                        continue;
                    }
                }
                if(in.equals("quit")) {
                    qcode=1;
                    break;
                }
                
                String[] coords=in.split(",");
                try {
                    bb.check(Integer.valueOf(coords[0]), Integer.valueOf(coords[1]));
                } catch (Exception e) {
                    throw (e);
                }
            }
            bb.setEnd(true);
            bb.printbox();
            if(qcode==1) break;
            
            if(bb.checkGuesses()) {
                int score = bb.getScore();
                System.out.println(String.format("Success! Score: %d", score));
                if(highScore==-1 || score < highScore) highScore=score;
            } else {
                System.out.println("Fail");
            }
        }
        String toPrint = String.valueOf(highScore);
        if(highScore==-1) toPrint = "none";
        System.out.println("Highest score => " + toPrint);
    }
    
    private static int getDifficulty(Scanner sc) {
        while(true) {
            System.out.println("Welcome to the BlackBox game. Please choose the difficulty level (easy/medium/hard) or type quit to end the game.");
            String resp=sc.nextLine().toLowerCase();
            switch (resp) {
                case "easy":
                    return 0;
                case "medium":
                    return 1;
                case "hard":
                    return 2;
                case "quit": case "q":
                    return -1;
            }
            System.out.println("Please type only (easy/medium/hard/quit).");
        }
    }
    
    private static String getVerifiedInput(Scanner sc) {
        while(true) {
            String in=sc.nextLine().toLowerCase();
            if (in.matches("[0-9]+,[0-9]+")) return in;
            if (in.equals("submit")) return in;
            if (in.equals("quit")||in.equals("q")) return in;
    
            System.out.println("Please enter a valid input. (%d,%d/submit/quit)");
        }
    }
}
