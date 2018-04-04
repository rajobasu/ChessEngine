package frameWork;

import java.util.LinkedList;

import evaluation.PositionEvaluator;
import id.MoveType;
import id.PieceColor;
import ui.UIBoard;

public class MinMaxAlphaBetaQuiscence {
	private int maxdepth = 0;
	private int tempdepth = 0;
	public static int depth;
	private PieceColor color;
	private Board _board;
	private boolean quisce;
	private PositionEvaluator evaluator;

	public MinMaxAlphaBetaQuiscence(PieceColor color, int depth, Board _board, boolean quisce) {
		this._board = _board;
		evaluator = new PositionEvaluator(color);
		MinMaxAlphaBetaQuiscence.depth = depth;
		this.color = color;
		this.quisce = quisce;

	}

	private int minNode() {

		return 0;
	}

	private int maxNode() {

		return 0;
	}

	// changed without much knowledge, revert to previous versions if necessary.
	private int negaMax(int alpha, int beta, int d, PieceColor color, int sign) {
		PositionEvaluator.positionsEvaluated++;

		if (d == 0) {
			if (!quisce) {
				return evaluator.evaluate(_board) * sign;
			} else {
				return Quiesce(alpha, beta, color, sign);
			}
		}

		tempdepth++;
		maxdepth = Math.max(maxdepth, tempdepth);

		LinkedList<Move> movelist = _board.getPossibleMoves(color);
		// resolve the moves
		if (movelist.size() == 0) {
			if (_board.isStaleMate(color)) {
				tempdepth--;
				return 0;
			} else {
				tempdepth--;
				return (-1_000_000_000 + d) * sign;
			}
		}

		for (Move m : movelist) {

			_board.executeMove(m);

			String s = "";
			Game.nextPos(s = _board.getBoardConfig());
			int value = 0;

			if (!(Game.numRepeats(s) >= 3)) {
				// if (true && tt.containsKey(s) && tt.get(s).level==d)
				// value = tt.get(s).value;
				// else {
				value = -negaMax(-beta, -alpha, d - 1, color.reverse(), -sign);
				// tt.put(s, new Pair(value, d));
				// }
			}
			Game.removePrevPos();

			_board.undoMove(m);

			if (alpha < value) {
				alpha = value;
			}

			if (alpha >= beta) {
				tempdepth--;
				return alpha;
			}
		}
		tempdepth--;
		return alpha;
	}

	public Move evaluateBestMove(Board board) {
		// initializing the variables required
		// tt.clear();
		long t1 = System.currentTimeMillis();
		int alpha = -1_000_000_000;
		int beta = 1_000_000_000;
		PositionEvaluator.positionsEvaluated = 0;
		LinkedList<Move> moves = board.getPossibleMoves(color);
		this._board = board;
		int temp = alpha - 1;
		Move mv = null;

		try {
			mv = moves.getFirst();
		} catch (NullPointerException e) {
			if (!_board.isCheckMate(color)) {
				throw new StalemateException();
			} else {
				throw new NullPointerException();
			}
		}
		// actual algo starts.
		for (Move m : moves) {

			maxdepth = 0;
			board.executeMove(m);
			String s = "";
			Game.nextPos(s = _board.getBoardConfig());
			if (Game.numRepeats(s) >= 3)
				temp = 0;
			else {
				temp = -negaMax(-beta, -alpha, depth - 1, color.reverse(), -1);
			}
			if (temp > alpha) {
				alpha = temp;
				mv = m;
			}
			board.undoMove(m);
			Game.removePrevPos();
			UIBoard.println(m + "  :" + temp + " ::: max depth:" + maxdepth);

		}
		UIBoard.println("");
		UIBoard.println("number of times getPosMoves called :: " + Board.pmx);
		UIBoard.println("Time spent in generating moves :: " + Board.posMvCtr);
		UIBoard.println("Time spent in evaluating moves :: " + PositionEvaluator.time);
		PositionEvaluator.time = 0;
		UIBoard.println("Positions Evaluated " + Integer.toString(PositionEvaluator.positionsEvaluated));

		UIBoard.println("Total Time taken :: " + (System.currentTimeMillis() - t1));
		UIBoard.println("NPS ::" + (double) PositionEvaluator.positionsEvaluated
				/ (((double) (System.currentTimeMillis() - t1)) / 1000));
		Board.posMvCtr = 0;
		Board.pmx = 0;

		return mv;
	}

	private int Quiesce(int alpha, int beta, PieceColor color, int sign) {
		PositionEvaluator.positionsEvaluated++;
		tempdepth++;
		maxdepth = Math.max(maxdepth, tempdepth);

		int stand_pat = evaluator.evaluate(_board) * sign;

		if (tempdepth > depth * 3) {
			tempdepth--;
			return stand_pat;
		}

		if (stand_pat >= beta) {
			tempdepth--;
			return beta;
		}
		if (alpha < stand_pat)
			alpha = stand_pat;

		for (Move m : _board.getPossibleMoves(color)) {
			if (m.getType() == MoveType.CAPTURE) {
				// if (stand_pat + m.getCapturedPiece().getID().getVal() <=
				// alpha) {
				// continue;
				// }
				_board.executeMove(m);
				int score = -Quiesce(-beta, -alpha, color.reverse(), -sign);
				_board.undoMove(m);

				if (score >= beta) {
					tempdepth--;
					return beta;
				}
				if (score > alpha)
					alpha = score;
			}
		}

		tempdepth--;
		return alpha;

	}

}

class Pair {
	int value;
	int level;

	public Pair(int value, int level) {
		super();
		this.value = value;
		this.level = level;
	}

}