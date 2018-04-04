package evaluation;

import frameWork.Board;
import id.PieceColor;
import pieces.Piece;

public class Options {
	private PieceColor color;
	public Options(PieceColor color){
		this.color=color;
	}
	
	
	public int getOptionsVal(Board board){
		int ret_w=0;
		int ret_b=0;
	
		for(Piece p:board.getPieces(color)){
			ret_w+=p.getMoveList(board).size();
		}
		for(Piece p:board.getPieces(color.reverse())){
			ret_b+=p.getMoveList(board).size();
		}
		
		return (ret_w-ret_b);
	}
}
