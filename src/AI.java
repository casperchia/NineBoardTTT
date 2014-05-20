import Interfaces.BoardI;
import Interfaces.MinMaxI;


public class AI {
	
	private MinMaxI minmax;
	private int computer, player;

	public AI(int _computer, int _player) {
		this.computer = _computer;
		this.player = _player;
		this.minmax = new MinMaxAlphaBeta();
	}
	
	public void play(BoardI _board) {
		BoardI bestMove = _board;
		if (!_board.isOver()) {
			bestMove = minmax.getBestMove(_board, computer, player);
		}
		_board.setState(bestMove.getState());
	}
}
