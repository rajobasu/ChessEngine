package evaluation.valueBoards;

import id.PieceColor;
import id.PieceID;
import pieces.Piece;

public class PeicePosVal {
	private ForBlack b;
	private ForWhite w;
	
	public PeicePosVal() {
		w=new ForWhite();
		b=new ForBlack();
	}
 
	public int getPiecePosVal(Piece p,boolean endgame){
		if(p.getColor()==PieceColor.WHITE){
			if(p.getID()==PieceID.PAWN){
				return w.getValForPawn(p.getLocation());
			}
			if(p.getID()==PieceID.BISHOP){
				return w.getValForBishop(p.getLocation());
			}
			if(p.getID()==PieceID.KING){
				return w.getValForKing(p.getLocation(),endgame);
			}
			if(p.getID()==PieceID.KNIGHT){
				return w.getValForKnight(p.getLocation());
			}
			if(p.getID()==PieceID.QUEEN){
				return w.getValForQueen(p.getLocation());
			}
			if(p.getID()==PieceID.ROOK){
				return w.getValForRook(p.getLocation());
			}
		}
		if(p.getColor()==PieceColor.BLACK){
			if(p.getID()==PieceID.PAWN){
				return b.getValForPawn(p.getLocation());
			}
			if(p.getID()==PieceID.BISHOP){
				return b.getValForBishop(p.getLocation());
			}
			if(p.getID()==PieceID.KING){
				return b.getValForKing(p.getLocation(),endgame);
			}
			if(p.getID()==PieceID.KNIGHT){
				return b.getValForKnight(p.getLocation());
			}
			if(p.getID()==PieceID.QUEEN){
				return b.getValForQueen(p.getLocation());
			}
			if(p.getID()==PieceID.ROOK){
				return b.getValForRook(p.getLocation());
			}
		}
		return 1;
	}
	
}
