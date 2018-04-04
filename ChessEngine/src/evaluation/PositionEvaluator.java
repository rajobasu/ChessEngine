package evaluation;

import java.util.LinkedList;

import evaluation.valueBoards.PeicePosVal;
import frameWork.Board;
import id.PieceColor;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import ui.UIBoard;

public class PositionEvaluator {

	private int positionValue;
	private Material materialCkecker;
	private Options opt;
	public static int positionsEvaluated;
	private PeicePosVal ppv;

	private PieceColor color;
	public static long time;
	static boolean endgame;

	public PositionEvaluator(PieceColor clr) {
		this.color = clr;
		positionValue = 0;
		materialCkecker = new Material(clr);
		opt = new Options(color);
		ppv = new PeicePosVal();
	}

	// change evaluation to make it suit to the piece color given.
	public int evaluate(Board board) {
		// check for boundary conditions
		if (board.isStaleMate(PieceColor.BLACK) || board.isCheckMate(PieceColor.WHITE))
			return 0;
		if (board.isCheckMate(color))
			return -1000000000;
		if (board.isCheckMate(color.reverse()))
			return 1000000000;

		long t1 = System.currentTimeMillis();
		LinkedList<Piece> pawns = new LinkedList<>();

		// current position value to return;
		positionValue = 0;

		// get the relative materialistic value
		int m1 = materialCkecker.getMaterialValue(board);
		positionValue += m1;
		// get the mobility of the engine pieces
		positionValue += opt.getOptionsVal(board);

		int a1 = 0;
		for (Piece p : board.getPieces(color)) {

			a1 += ppv.getPiecePosVal(p, endgame);
			if (p instanceof Pawn) {
				pawns.add(p);
			}
			if (p instanceof Queen) {
				if (UIBoard.moves < 7 && p.hasMoved()) {
					a1 -= 150;
				}
			}
		}

		positionValue += a1;

		int res = checkDoubleOrIsolatedPawns(board, pawns);
		positionValue -= res;
		pawns.clear();

		int a2 = 0;
		for (Piece p : board.getPieces(color.reverse())) {
			a2 += ppv.getPiecePosVal(p, endgame);
			if (p instanceof Pawn) {
				pawns.add(p);
			}
			if (p instanceof Queen) {
				if (UIBoard.moves < 7 && p.hasMoved()) {
					a2 -= 150;
				}
			}
		}
		positionValue -= a2;

		positionValue += checkDoubleOrIsolatedPawns(board, pawns);
		pawns.clear();

		positionsEvaluated++;
		time += System.currentTimeMillis() - t1;
		// System.out.println(positionsEvaluated);

		return positionValue;
	}

	private int checkDoubleOrIsolatedPawns(Board board, LinkedList<Piece> pawns) {
		// System.out.println();
		// System.out.println(" Starting pawn structure eval ...");
		// System.out.println();
		//
		int result = 0;
		boolean isolated = true;
		boolean backward = true;
		for (Piece pawn : pawns) {
			// System.out.println("pawn found :::" + pawn);
			for (Piece p : board.getColumnOccupants(pawn.getLocation().getCol())) {

				if (p instanceof Pawn) {
					if (!pawn.equals(p)) {
						if (p.getColor() == pawn.getColor()) {
							result += 5;
						}
					}
				}
			}
			if (pawn.getLocation().getCol() > 0) {
				for (Piece p : board.getColumnOccupants(pawn.getLocation().getCol() - 1)) {
					if (p instanceof Pawn) {
						if (p.getColor() == pawn.getColor()) {
							isolated = false;
							if (p.getColor() == PieceColor.WHITE) {
								if (p.getLocation().getRow() <= pawn.getLocation().getRow()) {
									backward = false;
								}
							} else {
								if (p.getLocation().getRow() >= pawn.getLocation().getRow()) {
									backward = false;
								}

							}
						}
					}
				}
			}
			if (pawn.getLocation().getCol() < 7) {
				for (Piece p : board.getColumnOccupants(pawn.getLocation().getCol() + 1)) {

					if (p instanceof Pawn) {
						if (p.getColor() == pawn.getColor()) {
							isolated = false;
							if (p.getColor() == PieceColor.WHITE) {
								if (p.getLocation().getRow() <= pawn.getLocation().getRow()) {
									backward = false;
								}
							} else {
								if (p.getLocation().getRow() >= pawn.getLocation().getRow()) {
									backward = false;
								}

							}
						}
					}
				}
			}
			if (isolated) {
				result += 8;
			} else if (backward) {
				result += 10;
			}
			// System.out.println(pawn + " :: isolated :" + isolated + " ::
			// backward :" + backward);
			isolated = true;
			backward = true;

		}
		return result;
	}
}
