/**
 * Cell is just a boolean.
 */
public class Cell {
    private boolean living;
    public Cell(boolean living) {
        this.living = living;
    }

    public boolean getLiving() {
        return living;
    }
    public void setLiving(boolean live) {
        living = live;
    }

    public boolean cellLogic(int numNeighbors) {
        if(getLiving()) {
            return numNeighbors==2 || numNeighbors==3;
        } else {
            return numNeighbors == 3;
        }
    }
}
