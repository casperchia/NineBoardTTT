import Interfaces.BoardI;


public class Tuple {
	private BoardI board;
	private int score;
	public Tuple(BoardI board, int score) {
		this.board = board;
		this.score =score;
	}
	
	public BoardI getBoard() {
		return this.board;
	}
	
	public int getScore() {
		return this.score;
	}
}
