import java.util.List;

import Interfaces.BoardI;
import Interfaces.EvaluatorI;
import Interfaces.MinMaxI;
import Interfaces.NineBoardI;


public class Main {
	
	public static void main (String[] args) {
		int[][] state = {
				{1, -1, -1},
				{0, -1, -1},
				{1, -1, 0},
		};
		BoardI board = new Board(state);
		board.printBoard();
		AI X = new AI(1, 0);
		AI O = new AI(0, 1);
		int i=1;
		while (!board.isOver()) {
			System.out.println("Turn " + i);
			X.play(board);
			board.printBoard();
			System.out.println("Turn " + (i+1));
			O.play(board);
			board.printBoard();
			i++;
		}
		
	}
	
}
