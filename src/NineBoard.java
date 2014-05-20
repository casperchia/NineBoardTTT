import Interfaces.BoardI;
import Interfaces.EvaluatorI;
import Interfaces.NineBoardI;


public class NineBoard implements NineBoardI {

	private BoardI[][] board;
	private int DIMENSION = 3;
	private BoardI currentBoard;
	private int NOUGHT = 0;
	private int CROSS = 1;
	private int EMPTY = -1;
	private int winner = -1;
	private int player;
	private EvaluatorI eval;
	
	public NineBoard(int _player) {
		board = new Board[DIMENSION][DIMENSION];
		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {
				board[row][col] = new Board();
			}
		}
		currentBoard = board[1][1];
		player = _player;
		eval = new Evaluator();
	}
	@Override
	public BoardI getBoard(int _x, int _y) {
		if (_x < DIMENSION && _x >= 0 && 
				_y < DIMENSION && _y >=0) {
			return board[_x][_y];
		} else {
			System.out.println("Invalid board");
			return null;
		}
	}

	public BoardI getCurrentBoard() {
		return currentBoard;
	}
	
	@Override
	public void play(int _x, int _y) {
		if (winner == EMPTY) {
			currentBoard.play(_x, _y, player);
			if (eval.evaluate(currentBoard, player) == 3) {
				winner = player;
			} else {
				currentBoard = board[_x][_y];
				changePlayer();
			}
		}
	}

	@Override
	public void printBoard(int _x, int _y) {
		System.out.println("Board: " + "[" + _x + "]" + "[" + _y + "]");
		board[_x][_y].printBoard();
	}

	@Override
	public BoardI getWin() {
		return currentBoard;
	}
	
	public int getPlayer() {
		return player;
	}
	
	private void changePlayer() {
		player = player == NOUGHT ? CROSS : NOUGHT;
	}

}
