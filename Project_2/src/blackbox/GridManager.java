package blackbox;

import java.util.ArrayList;
import java.util.Random;

/**
 * GridManager
 *
 * description goes here.
 *
 * @author Albert Xu
 *
 * @version 2/19/18
 *
 */

class GridManager {
    private static final boolean ONE_BOUNCE = false;
    
    public int size;
    private int[][] grid;
    private int[][] ports; //Reflections are `-1`, Hits are `-2`
    
    private int cur_r;
    private int cur_c;
    private double dir;
    private boolean bounced=false;
    private boolean going;
    
    public GridManager(int size) {
        this.size = size;
        this.grid = new int[size][size];
        this.ports = new int[size+2][size+2];
        
        cur_r = -1; cur_c = -1;
        dir = 0;
        going = false;
    }
    
    public String[][] getPorts(boolean reveal) {
        String[][] ret = new String[size+2][size+2];
        
        for(int r=0; r<size+2; ++r) {
            for(int c=0; c<size+2; ++c) {
                if(inBounds(r-1, c-1)) {
                    ret[r][c] = (reveal && grid[r-1][c-1]==1) ? "0" : "";
                } else {
                    int to_put = ports[r][c];
                    if(to_put==0) ret[r][c] = "#";
                    else if(to_put==-1) ret[r][c] = "R";
                    else if(to_put==-2) ret[r][c] = "H";
                    else ret[r][c] = String.valueOf(to_put);
                }
            }
        }
        ret[0][0] = "X";
        ret[size+1][0] = "X";
        ret[0][size+1] = "X";
        ret[size+1][size+1] = "X";
        
        return ret;
    }
    
    /**
     * Finds the number of linked ports. Reflections are `-1`.
     * @return the number of linked ports.
     */
    public int getNumlink() {
        int max=Integer.MIN_VALUE;
        for (int[] j : ports)
            for (int i : j)
                if (i > max) max=i;
        return max;
    }
    
    /**
     * Clears the grid then places n balls onto the grid.
     * @param n the number of balls to place
     */
    public void resetBalls(int n) {
        Random rand = new Random();
        grid = new int[size][size];
        
        int ballCount = 0;
        while(ballCount < n) {
            int r=rand.nextInt(size);
            int c=rand.nextInt(size);
            if(grid[r][c] == 0) {
                grid[r][c] = 1;
                ballCount++;
            }
        }
    }
    
    public void placeBalls(ArrayList<Integer[]> balls) {
        grid = new int[size][size];

        for(Integer[] pts:balls) {
            grid[pts[0]][pts[1]] = 1;
        }
    }
    
    /**
     * Sets the current position to one of the edges, and sets `dir` appropriately. Cannot start in bounds.
     * @param r Starting row
     * @param c Starting col
     * @return whether (r, c) was set successfully.
     */
    public boolean setStart(int r, int c) {
        if(inBounds(r, c)) return false;
        
        if(r == -1) {
            if(!inBounds(0, c)) return false;
            dir = 0;
        }
        else if(c == -1) {
            if(!inBounds(r,0)) return false;
            dir = 1;
        } else if(r == size) {
            if(!inBounds(0, c)) return false;
            dir = 2;
        } else if(c == size) {
            if(!inBounds(r,0)) return false;
            dir = 3;
        } else {
            return false;
        }
    
        cur_r=r; cur_c=c;
        bounced = false;
        going = true;
        return true;
    }
    
    /**
     * Tells this object to solve the ray problem from whatever start location.
     * End point is stored as (cur_r, cur_c).
     * Also sets the ports accordingly.
     */
    public void getEndPoint() {
        int sr = cur_r+1; int sc = cur_c+1;
        
        while(going) {
            going = takeStep();
        }
        
        int end_r = cur_r+1; int end_c = cur_c+1;
        if(inBounds(cur_r, cur_c)) {
            ports[sr][sc] = -2; //'H'
        } else {
            if(sr==end_r && sc==end_c) ports[sr][sc] = -1; //'R'
            else {
                int label = getNumlink()+1;
                ports[sr][sc] = label;
                ports[end_r][end_c] = label;
            }
        }
    }
    
    /**
     * From the current location, takes a single step following the rules of BlackBox.
     * @return Whether the ray has been stopped after taking this step.
     */
    private boolean takeStep() {
        int dr = (int) Math.cos(dir * Math.PI/2);
        int dc = (int) Math.sin(dir * Math.PI/2);
        assert(Math.abs(dr)==1 ^ Math.abs(dc)==1); //assert that one is one, the other is zero.
        
        boolean front = checkObstacle(cur_r+dr, cur_c+dc);
        
        int dflr = dr-dc; int dflc = dr+dc;
        boolean front_left = checkObstacle(cur_r+dflr, cur_c +dflc);
        
        int dfrr =  dr+dc; int dfrc = -dr+dc;
        boolean front_right = checkObstacle(cur_r+dfrr, cur_c +dfrc);
        
        if(!(ONE_BOUNCE && bounced)) {
            if(!front && (front_left||front_right)) {
//                if (front_left && front_right) {
//                    dir+=2; //reflect if 2 balls ahead
//                } else if (front_right) {
//                    dir+=1; //If there's something front right, turn left.
//                } else if (front_left) {
//                    dir-=1; //If there's something front left, turn right.
//                }
                
                //Did some math and this works out. just trust.
                dir+=2;
                if(front_left) dir+=1;
                if(front_right) dir-=1;
                bounced = true;
                return inBounds(cur_r, cur_c);
            }
        }
        cur_r+=dr;
        cur_c+=dc;
        return inBounds(cur_r, cur_c) && grid[cur_r][cur_c]==0;
    }
    
    /**
     * Checks for an obstacle at (r, c). Out of bounds counts as empty.
     * @return whether there is a ball there.
     */
    public boolean checkObstacle(int r, int c) {
        return inBounds(r,c) && grid[r][c]==1;
    }
    
    /**
     * Checks whether (r, c) is inside the grid.
     */
    public boolean inBounds(int r, int c) {
        return 0<=r && r<size && 0<=c && c<size;
    }
}