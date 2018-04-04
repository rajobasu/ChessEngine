package pieces;

import java.util.LinkedList;

import frameWork.Board;
import frameWork.Location;
import frameWork.Move;
import id.PieceColor;
import id.PieceID;

public class Rook extends Piece {

	public Rook(PieceColor color) {
		super(PieceID.ROOK, color);
	}

	private Rook(Rook rook) {
		super(PieceID.ROOK, rook.getColor());
		moves = rook.moves;
		setLocation(rook.location.copy());
	}

	public LinkedList<Move> getMoveList(Board board) {

		LinkedList<Move> possibleMoves = new LinkedList<Move>();
		// check to left
		int l=0;
		for (int i = location.getCol() - 1; i >= 0; i--) {
			l=location.getRow()*8+i;

			if (!(board.getSpot(l).isOccupied())) {
				possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
			} else {
				if (board.getSpot(l).getOccupant().getColor() != getColor())
					possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
				break;
			}

		}

		//
		// CHECK TO RIGHT
		for (int i = location.getCol() + 1; i < 8; i++) {
		l=location.getRow()*8+i;

			if (!(board.getSpot(l).isOccupied())) {
				possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
			} else {
				if (board.getSpot(l).getOccupant().getColor() != getColor())
					possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
				break;
			}

		}

		//
		// CHECK TO UP
		for (int i = location.getRow() - 1; i >= 0; i--) {
			l=i*8+location.getCol();

			if (!(board.getSpot(l).isOccupied())) {
				possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
			} else {
				if (board.getSpot(l).getOccupant().getColor() != getColor())
					possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
				break;
			}

		}
		//
		/// CHECK TO DOWN
		for (int i = location.getRow() + 1; i < 8; i++) {
			l=i*8+location.getCol();

			if (!(board.getSpot(l).isOccupied())) {
				possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
			} else {
				if (board.getSpot(l).getOccupant().getColor() != getColor())
					possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
				break;
			}

		}

		return possibleMoves;
	}

	

	@Override
	public boolean isLegalMove(Board board, Location to) {

		// checks if given location is within the board
		if (!board.contains(to)) {
			
			return false;
		}
		// check if proposed location  is on the same file or rank
		if (to.getRow() != location.getRow() && to.getCol() != location.getCol()) {
		
			return false;
		}

		// check if proposed location is already blocked by a piece of same
		// color
		if (board.getSpot(to).isOccupied()) {
			
			if (board.getSpot(to).getOccupant().getColor() == getColor()) {
				
				return false;
			}
		}
		// this is where it is checked whether there is any piece in between the
		// two squares
		if (to.getCol() != location.getCol()) {
			if (to.getCol() > location.getCol()) {
				for (int i = location.getCol() + 1; i < to.getCol(); i++) {
					Location intermediateLocation = new Location(location.getRow(), i);
					if (board.getSpot(intermediateLocation).isOccupied()) {
						return false;
					}
				}
			} else {
				for (int i = location.getCol() - 1; i > to.getCol(); i--) {
					Location intermediateLocation = new Location(location.getRow(), i);
					if (board.getSpot(intermediateLocation).isOccupied()) {
						return false;
					}
				}
			}
		} else if (to.getRow() != location.getRow()) {
			
		
			if (to.getRow() > location.getRow()) {
	
				for (int i = location.getRow() + 1; i < to.getRow(); i++) {
					Location intermediateLocation = new Location(i, location.getCol());
					if (board.getSpot(intermediateLocation).isOccupied()) {
						return false;
					}
				}
			} else {
				for (int i = location.getRow() - 1; i > to.getRow(); i--) {
					Location intermediateLocation = new Location(i, location.getCol());
					if (board.getSpot(intermediateLocation).isOccupied()) {
						return false;
					}
				}
			}
		}

		return true;
	}

}
