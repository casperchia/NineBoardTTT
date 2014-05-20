package Interfaces;

import java.util.List;



public interface BoardI {
	public int[][] getState();
	
	public void printBoard();
	
	public void play(int _x, int _y, int _player);
	
	public List<BoardI> getChildren(int _player);
	
	public boolean isOver();
	
	public int getWinner();
	
	public void setState(int[][] _state);
	
}
