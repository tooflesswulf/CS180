/**
 * Lab10: Concurrency
 * A simple Player class which may cause race conditions in multi-thread program.
 * @author Muyuan Li
 * @version 02/28/2018
 * 
 */
public class Player {
	private int x;	//x position of the player
	private int y;	//y position of the player
	private int hp;		//health point of the player
	
	public Player(int x, int y, int hp){
		this.x = x;
		this.y = y;
		this.hp = hp;
	}
	
	public void printPlayer(){
		System.out.printf("x position:\t%d\ny position:\t%d\nhealth point:\t%d\n", x, y, hp);
	}
	
	public void moveLeft(){
		x --;
	}
	public void moveRight(){
		x ++;
	}
	
	public void moveUp(){
		y --;
	}
	public void moveDown(){
		y ++;
	}
	
	public void loseHealth(){
		hp --;
	}
	public void gainHealth(){
		hp ++;
	}
	
}
