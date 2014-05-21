import Interfaces.BoardI;
import Interfaces.MinMaxI;
import Interfaces.NineBoardI;


public class AI {
	
	private MinMaxI minmax;
	private int computer, player;

	public AI(int _computer, int _player, NineBoardI _nineBoard) {
		this.computer = _computer;
		this.player = _player;
		this.minmax = new MinMaxAlphaBeta(_nineBoard);
	}
	
	public void play(BoardI _board) {
		BoardI bestMove = _board;
		if (!_board.isOver()) {
			bestMove = minmax.getBestMove(_board, computer, player);
		}
		_board.setState(bestMove.getState());
	}
}
