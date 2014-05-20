

import java.util.ArrayList;
import java.util.List;

import Interfaces.BoardI;
import Interfaces.EvaluatorI;


public class Board implements BoardI{

	private int[][] board;
	private static final int EMPTY = -1;
	private static final int CROSS = 1;
	private static final int NOUGHT = 0;
	private static final int DIMENSION = 3;
	private boolean isOver = false;
	private EvaluatorI eval;
	private int winner = -1;
	
	public Board() {
		this.board = new int[DIMENSION][DIMENSION];
		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {
				board[row][col] = EMPTY;
			}
		}
		eval = new Evaluator();
	}
	
	public Board(BoardI _board) {
		this.board = new int[DIMENSION][DIMENSION];
		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {
				board[row][col] = _board.getState()[row][col];
			}
		}
		eval = new Evaluator();
	}
	
	public Board(int[][] _state) {
		this.board = new int[DIMENSION][DIMENSION];
		for (int row=0; row<DIMENSION; row++) {
			for (int col=0; col<DIMENSION; col++) {
				board[row][col] = _state[row][col];
			}
		}
		eval = new Evaluator();
	}
	
	@Override
	public int[][] getState() {
		return board;
	}

	@Override
	public void printBoard() {
		String borderl = "[";
		String borderr = "]";
		for (int[] i : board) {
			for (int val : i) {
				if (val == EMPTY) {System.out.print(borderl + " " + borderr);}
				if (val == CROSS) {System.out.print(borderl + "X" + borderr);} 
				if (val == NOUGHT) {System.out.print(borderl + "O" + borderr);}
			}
			System.out.println();
		}
		System.out.println("---------");
	}

	@Override
	public void play(int _x, int _y, int _player) {
		if (board[_x][_y] == EMPTY && 
				_x < DIMENSION && _y < DIMENSION &&
				_x >= 0 && _y >= 0 && !isOver) {
			board[_x][_y] = _player;
		} else {
			System.out.println("Invalid Move");
		}
	}

	public List<BoardI> getChildren(int _player) {
		BoardI temp;
		List<BoardI> children = new ArrayList<BoardI>();
		
		for (int row=0; row<DIMENSION; row++) {
			for (int col=0; col<DIMENSION; col++) {
				if (board[row][col] == EMPTY) {
					temp = new Board(this);
					temp.play(row, col, _player);
					children.add(temp);
				}
			}
		}
		return children;
	}
	
	
	public boolean isOver() {
		int temp=0;
		for (int[] row : board) {
			for (int val : row) {
				if (val == EMPTY) temp++;
			}
		}
		if (temp == 0) isOver = true;
		if (eval.evaluate(this, 1) == 1 || eval.evaluate(this, 0) == 1) isOver = true;
		return isOver;
	}
	
	public int getWinner() {
		return winner;
	}
	
	public void setState(int[][] _state) {
		for (int row=0; row<DIMENSION; row++) {
			for (int col=0; col<DIMENSION; col++) {
				board[row][col] = _state[row][col];
			}
		}
	}
}
