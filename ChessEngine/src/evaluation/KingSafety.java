package evaluation;

import java.util.LinkedList;

import frameWork.Board;
import frameWork.Location;
import frameWork.Move;
import id.PieceColor;
import id.PieceID;
import pieces.Piece;

public class KingSafety {

	public int kingSafeValue(Board board, PieceColor color) {
		int attackingMoves = 30;
		int attackVal = 0;
		PieceColor oppColor = color.reverse();
		LinkedList<Move> moveList = board.getPossibleMoves(oppColor);
		for (Move m : moveList) {
			if (canAttackKing(board, board.getKing(color).getLocation(), m)) {
				attackVal += m.getPiece().getID().getVal() / 15;
				attackingMoves++;
			}
		}
		if (attackingMoves > 3) {
			switch (attackingMoves) {
			case 3: {
				attackVal += 5;
				break;
			}
			case 4: {
				attackVal += 20;
				break;
			}
			case 5: {
				attackVal += 25;
				break;
			}
			case 6: {
				attackVal += 30;
				break;
			}
			case 7: {
				attackVal += 35;
				break;
			}
			case 8: {
				attackVal += 40;
				break;
			}
			default: {
				attackVal += 60;
				break;
			}
			}

		} else {
			attackVal -= 40;
		}

		return attackVal;
	}

	private boolean canAttackKing(Board board, Location kingLocation, Move move) {
		Location temp = null;

		temp = kingLocation;
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		temp = new Location(kingLocation.getRow(), kingLocation.getCol() - 1);
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		temp = new Location(kingLocation.getRow(), kingLocation.getCol() + 1);
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		temp = new Location(kingLocation.getRow() - 1, kingLocation.getCol());
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		temp = new Location(kingLocation.getRow() - 1, kingLocation.getCol() + 1);
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		temp = new Location(kingLocation.getRow() - 1, kingLocation.getCol() - 1);
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		temp = new Location(kingLocation.getRow() + 1, kingLocation.getCol());
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		temp = new Location(kingLocation.getRow() + 1, kingLocation.getCol() + 1);
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		temp = new Location(kingLocation.getRow() + 1, kingLocation.getCol() - 1);
		if (board.contains(temp)) {
			if (temp.equals(move.getTo()))
				return true;
		}
		return false;
	}

	public int kingSafetyMeasure(Board board, PieceColor color) {
		Piece king = board.getKing(color);
		int val=0;
		int pn_ctr=0;
		// check column to the right
		try {
			for (Piece p : board.getColumnOccupants(king.getLocation().getCol() + 1)) {
				if(p.getID()==PieceID.PAWN)pn_ctr++;
			}
		} catch (NullPointerException e) {
		}
		
		if(pn_ctr<2)val-=20;
		// check column to the left
		try {
			for (Piece p : board.getColumnOccupants(king.getLocation().getCol() - 1)) {
				if(p.getID()==PieceID.PAWN)pn_ctr++;
			}
		} catch (NullPointerException e) {
		}

		if(pn_ctr<2)val-=20;
		// check current column
		try {
			for (Piece p : board.getColumnOccupants(king.getLocation().getCol())) {
				if(p.getID()==PieceID.PAWN)pn_ctr++;
			}
		} catch (NullPointerException e) {
		}

		if(pn_ctr<2)val-=20;

        for(Piece p:board.getPieces(color.reverse())){
        	for(Move mv:p.getMoveList(board)){
        		if(canAttackKing(board, king.getLocation(),mv)){val-=10;}
        	}
        } 
		
		return val;
	}

}
