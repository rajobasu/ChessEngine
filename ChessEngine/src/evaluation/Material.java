package evaluation;

import java.util.LinkedList;

import frameWork.Board;
import id.PieceColor;
import pieces.Piece;

class Material {
	private LinkedList<Piece> whitePieces;
	private LinkedList<Piece> blackPieces;

	private PieceColor color;

	Material(PieceColor color) {
		this.color = color;
	}

	int getMaterialValue(Board board) {
		extractWhitePiece(board);
		extractBlackPiece(board);

		int whiteVal = 0;
		int blackVal = 0;

		for (Piece p : whitePieces) {
			whiteVal += p.getID().getVal();
		}
		for (Piece p : blackPieces) {
			blackVal += p.getID().getVal();
		}
		PositionEvaluator.endgame = blackVal + whiteVal < 24000;

		if (color == PieceColor.BLACK)
			return blackVal - whiteVal;
		
		return whiteVal - blackVal;
	}

	private void extractWhitePiece(Board board) {
		whitePieces = board.getWhitePieces();
	}

	private void extractBlackPiece(Board board) {
		blackPieces = board.getBlackPieces();
	}

}
