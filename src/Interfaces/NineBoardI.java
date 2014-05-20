package Interfaces;

public interface NineBoardI {
	public BoardI getBoard(int _x, int _y);
	
	public BoardI getCurrentBoard();
	
	public void play(int _x, int _y);
	
	public void printBoard(int _x, int _y);
	
	public BoardI getWin();
	
	public int getPlayer();
	
}
