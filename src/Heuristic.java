import Interfaces.BoardI;
import Interfaces.EvaluatorI;


public class Heuristic implements EvaluatorI {
	
	private int EMPTY = -1;
	private int DIMENSION = 3;
	private int WON = 100;

	private int checkLine(int _line[], int _player) {
		int player = -2;
		int numPlayer = 0;
		for (int val : _line) {
			if (player == -2 && val != EMPTY) {
				player = val;
				numPlayer++;
			} else if (val == player) {
				numPlayer++;
			} else if (val != player && val != EMPTY) {
				return 0;
			}
		}
		if (numPlayer == 3) {
			return _player == player ? WON : -WON;
		} else if (player == -2) {
			return 0;
		} else {
			return _player == player ? 1 : -1;
		}
	}
	
	@Override
	public int evaluate(BoardI _board, int _player) {
		int[][] board = _board.getState();
		int value = 0;
		int temp = 0;

		//Check horizontal value
		for (int i = 0; i < DIMENSION; i++) {
			if (Math.abs(temp = checkLine(board[i], _player)) == WON) {
				return temp;
			} else {
				value += temp;
			}
		}
		

		//check vertical value		
		int[][] invBoard = new int[DIMENSION][DIMENSION];
		//Inverse array
		for (int row=0; row<DIMENSION; row++) {
			for (int col=0; col<DIMENSION; col++) {
				invBoard[col][row] = board[row][col];
			}
		}

		for (int i=0; i<DIMENSION; i++) {
			if (Math.abs(temp = checkLine(invBoard[i], _player)) == WON) {
				return temp;
			} else {
				value += temp;
			}
		}
		
		//check diagonal value
		int[] diag1 = new int[DIMENSION];
		int[] diag2 = new int[DIMENSION];
		for (int i=0; i<DIMENSION; i++) {		
			diag1[i] = board[i][i];
			diag2[i] = board[DIMENSION - i - 1][i];
		}
		
		if (Math.abs(temp = checkLine(diag1, _player)) == WON) {
			return temp;
		} else {
			value += temp;
		}
		if (Math.abs(temp = checkLine(diag2, _player)) == WON) {
			return temp;
		} else {
			value += temp;
		}
		return value;
	}

}
