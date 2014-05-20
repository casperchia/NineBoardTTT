import Interfaces.BoardI;
import Interfaces.EvaluatorI;
import Interfaces.MinMaxI;


public class MinMax implements MinMaxI{
	private EvaluatorI eval;
	private int currplayer;
	private int oppplayer;
	
	public MinMax() {
		eval = new Evaluator();
	}
	
	private int miniMax(BoardI _board, int _player) {
		int score = 0;
		int bestScore = 0;
		if (_board.isOver()) {
			return eval.evaluate(_board, currplayer);
		} else {
			if (_player == currplayer) {
				bestScore = Integer.MIN_VALUE;
				for (BoardI child : _board.getChildren(currplayer)) {
					score = miniMax(child, oppplayer);
					bestScore = Math.max(bestScore, score);
				}
			} else {
				bestScore = Integer.MAX_VALUE;
				for (BoardI child : _board.getChildren(oppplayer)) {
					score = miniMax(child, currplayer);
					bestScore = Math.min(bestScore, score);
				}
			}
		}
		
		return bestScore;
	}
	
	
	public BoardI getBestMove(BoardI _board, int _currentplayer, int _opposingplayer) {
		
		this.currplayer = _currentplayer;
		this.oppplayer = _opposingplayer;
		BoardI bestMove = _board;
		int bestScore = Integer.MIN_VALUE;
		int val;
		
		long time = System.currentTimeMillis();
		//Initial iteration of minimax (the first maximising iteration)
		for (BoardI child : _board.getChildren(currplayer)) {
			if (eval.evaluate(child, currplayer) == 1) {
				return child;
			} else {
				if ((val = miniMax(child, oppplayer)) > bestScore) {
					bestScore = val;
					bestMove = child;
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("Time to run: " + (end-time));
		return bestMove;
	}
}
