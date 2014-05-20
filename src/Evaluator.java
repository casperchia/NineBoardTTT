import Interfaces.BoardI;
import Interfaces.EvaluatorI;


public class Evaluator implements EvaluatorI {
	private int DIMENSION = 3;
	private int EMPTY = -1;

	//Evaluates a single line to check for win/loss/draw
	private int checkLine(int[] _row, int _player) {
		int temp;
		if ((temp = _row[0]) != EMPTY) {
			for (int val : _row) {
				if (val != temp) {
					return 0;
				}
			}
		} else {
			return 0;
		}
		return temp == _player ? 1 : -1;
	}
	
	@Override
	public int evaluate(BoardI _board, int _player) {
		
		int[][] board = _board.getState();
		int value = 0;

		//Check horizontal value
		for (int i = 0; i < DIMENSION; i++) {
			if ((value = checkLine(board[i], _player)) != 0) {
				return value;
			}
		}
		

		//check vertical value		
		int[][] temp = new int[DIMENSION][DIMENSION];
		//Inverse array
		for (int row=0; row<DIMENSION; row++) {
			for (int col=0; col<DIMENSION; col++) {
				temp[col][row] = board[row][col];
			}
		}

		for (int i=0; i<DIMENSION; i++) {
			if ((value = checkLine(temp[i], _player)) != 0) {
				return value;
			}
		}
		
		//check diagonal value
		int[] diag1 = new int[DIMENSION];
		int[] diag2 = new int[DIMENSION];
		for (int i=0; i<DIMENSION; i++) {		
			diag1[i] = board[i][i];
			diag2[i] = board[DIMENSION - i - 1][i];
		}
		
		if ((value = checkLine(diag1, _player)) != 0 || (value = checkLine(diag2, _player)) != 0) {
			return value;
		}
		

		return 0;
	}
	
}
