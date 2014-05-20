import Interfaces.BoardI;
import Interfaces.EvaluatorI;
import Interfaces.MinMaxI;


public class MinMaxAlphaBeta implements MinMaxI {

	private EvaluatorI eval;
	private int currplayer;
	private int oppplayer;
	
	public MinMaxAlphaBeta() {
		eval = new Evaluator();
	}
	
	private int miniMax(BoardI _board, int _player, int _alpha, int _beta) {
		int bestScore = 0;
		if (_board.isOver()) {
			return eval.evaluate(_board, currplayer);
		} else {
			if (_player == currplayer) {
				for (BoardI child : _board.getChildren(currplayer)) {
					_alpha = Math.max(_alpha, miniMax(child, oppplayer, _alpha, _beta));
					if (_beta <= _alpha) break;
				}
				bestScore = _alpha;
			} else {
				for (BoardI child : _board.getChildren(oppplayer)) {
					_beta = Math.min(_beta, miniMax(child, currplayer, _alpha, _beta));
					if (_beta <= _alpha) break;
				}
				bestScore = _beta;
			}
		}
		
		return bestScore;
	}
	
	@Override
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
				if ((val = miniMax(child, oppplayer, Integer.MIN_VALUE, Integer.MAX_VALUE)) > bestScore) {
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
