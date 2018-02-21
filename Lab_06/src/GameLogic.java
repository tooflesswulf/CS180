import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 *
 */
public class GameLogic {
    private Cell[][] grid;
    private Cell[][] prevGrid;
    private Cell[][] gridUpdate;
    private int size;

    public GameLogic() {
        this(100);
    }

    public GameLogic(int size) {
        this.size = size;

        grid = new Cell[size][size];
        prevGrid = new Cell[size][size];
        gridUpdate = new Cell[size][size];

        initGrids(size, 3);
    }

    private void initGrids(int size, int seed) {
        Random rand;
        if(seed < 0) rand = new Random();
        else rand = new Random(seed);

        int bound=10; //1 in 10 chance to intialize live.

        for(int i=0; i<size; ++i) {
            for(int j=0; j<size; ++j) {
                boolean initLive = rand.nextInt(bound) < 1;
                grid[i][j] = new Cell(initLive);
                prevGrid[i][j] = new Cell(initLive);
                gridUpdate[i][j] = new Cell(initLive);
            }
        }
    }

    private boolean inGrid(int i, int j) {
        return 0<=i && i<size && 0<=j && j<size;
    }

    public boolean liveToNext(int i, int j) {
        int[] dis = {-1,0,1};
        int[] djs = {-1,0,1};

        int neighbors = 0;
        for(int di:dis) { //Process over counts center (0, 0), subtract later.
            for(int dj:djs) {
                int rcheck = i+di;
                int ccheck = j+dj;
                if(inGrid(rcheck, ccheck)) {
                    neighbors += grid[rcheck][ccheck].getLiving() ? 1 : 0;
                }
            }
        }
        neighbors -= grid[i][j].getLiving() ? 1 : 0; //Over counts center.

        return grid[i][j].cellLogic(neighbors);
    }

    /*You do not need to modify any of the below code!*/
    // Updates the grid by storing the output grid in `gridUpdate`
    // Then copies all of `gridUpdate` into `grid`
    public void update() {
        for(int i=0; i<size; ++i) {
            for(int j=0; j<size; ++j) {
                prevGrid[i][j].setLiving(grid[i][j].getLiving());
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (liveToNext(i, j)) {
                    gridUpdate[i][j].setLiving(true);
                } else {
                    gridUpdate[i][j].setLiving(false);
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j].setLiving(gridUpdate[i][j].getLiving());
            }
        }
    }

    public static JFrame frame = new JFrame();
    public static JPanel[][] gridGUI;

    public void initGridGUI() {
        gridGUI = new JPanel[grid.length][grid[0].length]; //allocate the size of grid
        frame.setLayout(new GridLayout(grid.length, grid[0].length)); //set layout
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                gridGUI[i][j] = new JPanel(); //creates new panel
                gridGUI[i][j].setBackground(Color.white);
                frame.add(gridGUI[i][j]);
            }
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); //sets appropriate size for frame
        frame.setLocationRelativeTo(null);
        frame.setTitle("Conway's Game of Life");
        frame.setVisible(true);
    }

    public void printGridGUI() {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j].getLiving() != prevGrid[i][j].getLiving()) {
                    if (grid[i][j].getLiving()) {
                        gridGUI[i][j].setBackground(Color.black);
                    } else {
                        gridGUI[i][j].setBackground(Color.white);
                    }
                }
            }
        }
    }

    public void printGrid() {

        for (int i = 0; i < grid.length; i++) {
            if (i == 0) {
                for (int k = 0; k < grid[i].length + 1; k++) {
                    System.out.print("-");
                }
                System.out.println("-");
            }
            for (int j = 0; j < grid[i].length; j++) {
                if (j == 0) {
                    System.out.print("|");
                }
                if (j == grid[i].length - 1) {
                    if (grid[i][j].getLiving()) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                } else {
                    if (grid[i][j].getLiving()) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            if (i == grid.length - 1) {
                for (int k = 0; k < grid[i].length; k++) {
                    System.out.print("-");
                }
                System.out.println("-");
            }
        }
    }
}
