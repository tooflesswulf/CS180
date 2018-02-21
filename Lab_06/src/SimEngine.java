/**
 *
 */
public class SimEngine {

 
 
    public static void main(String[] args) {
        GameLogic game = new GameLogic();
        game.initGridGUI();

        while(true) {
            game.printGridGUI();
            game.update();
            sleepMethod(100);
        }
    }

 
	/*You do not need to modify any of the below code!*/
    // Sleeps in milliseconds
    public static void sleepMethod(int duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }


}