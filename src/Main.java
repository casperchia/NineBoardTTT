import java.util.List;

import Interfaces.BoardI;
import Interfaces.EvaluatorI;
import Interfaces.MinMaxI;
import Interfaces.NineBoardI;


public class Main {
	
	public static void main (String[] args) {
		int[][] state = {
				{-1, -1, -1},
				{-1, 1, 0},
				{-1, -1, -1},
		};
		BoardI board = new Board(state);
		board.printBoard();
		Heuristic h = new Heuristic();
		System.out.println(h.evaluate(board, 1));
	}
	
}
