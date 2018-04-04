package frameWork;

import java.util.LinkedList;

import evaluation.PositionEvaluator;
import id.PieceColor;

public class MinMaxWithAlphaBeta {

	private int maxdepth = 0;
	private int tempdepth = 0;
	// private Board board;
	public static int depth;
	private PieceColor min_color;
	private PieceColor max_color;

	private PositionEvaluator evaluator;

	public MinMaxWithAlphaBeta(PieceColor color, int depth) {
		evaluator = new PositionEvaluator(color);
		MinMaxWithAlphaBeta.depth=depth;
		max_color = color;
		min_color = color.reverse();

	}

	private int min(int alpha, int beta, int d, Board _board) {
		tempdepth++;
		maxdepth = Math.max(maxdepth, tempdepth);
		LinkedList<Move> movelist = _board.getPossibleMoves(min_color);
		if (d <= 0) {

			boolean has = false;
			/*
			for (Move m : movelist) {

				if (m.getType() == MoveType.CAPTURE) {
				
						_board.executeMove(m);
						has = true;
						int value = max(alpha, beta, d, _board);
					_board.undoMove(m);
						if (value < beta) {
							beta = value;
						}
						if (alpha >= beta)
							break;
					
				}
			}*/
			if (!has) {
				tempdepth--;
				return evaluator.evaluate(_board);
			}
			tempdepth--;
			return beta;
		}

		for (Move m : movelist) {

			_board.executeMove(m);
			int value = max(alpha, beta, d - 1, _board);
			_board.undoMove(m);

			if (value < beta) {
				beta = value;
			}

			if (alpha >= beta)
				break;

		}
		tempdepth--;
		return beta;
	}

	private int max(int alpha, int beta, int d, Board _board) {
		tempdepth++;
		maxdepth = Math.max(maxdepth, tempdepth);
		LinkedList<Move> movelist = _board.getPossibleMoves(max_color);
		if (d == 0) {
			boolean has = false;
/*
			for (Move m : movelist) {

				if (m.getType() == MoveType.CAPTURE) {
					has = true;
					_board.executeMove(m);
					int value = min(alpha, beta, d, _board);
					_board.undoMove(m);
					if (value > alpha) {
						alpha = value;
					} 
						 if (alpha >= beta) break;
						 
				}

			}*/
			if (!has) {
				tempdepth--;
				return evaluator.evaluate(_board);
			}
			tempdepth--;
			return alpha;
		}

		// resolve the moves

		for (Move m : movelist) {
			_board.executeMove(m);

			int value = min(alpha, beta, d - 1, _board);
			_board.undoMove(m);
			if (value > alpha) {
				alpha = value;
			}
			if (alpha >= beta)
				break;
		}
		tempdepth--;
		return alpha;
	}

	public Move evaluateBestMove(Board board) {
System.out.println(depth);
		int alpha = -1_000_000_000;
		int beta = 1_000_000_000;
		PositionEvaluator.positionsEvaluated = 0;
		LinkedList<Move> moves = board.getPossibleMoves(max_color);

		int temp = alpha-1;

		Move mv = null;

		for (Move m : moves) {
			maxdepth = 0;
			board.executeMove(m);
			if ((temp = min(alpha, beta, depth - 1, board)) > alpha) {
				alpha = temp;
				mv = m;
			}
			board.undoMove(m);

			System.out.println(m + "  :" + temp + " ::: max depth:" + maxdepth);
		}
		System.out.println(PositionEvaluator.positionsEvaluated);
		return mv;
	}

	

}

