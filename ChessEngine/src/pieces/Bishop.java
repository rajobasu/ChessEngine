package pieces;

import java.util.LinkedList;
import static java.lang.Math.*;
import frameWork.Board;
import frameWork.Location;
import frameWork.Move;
import id.PieceColor;
import id.PieceID;

public class Bishop extends Piece {

	public Bishop(PieceColor color) {
		super(PieceID.BISHOP, color);

	}

	private Bishop(Bishop bishop) {
		super(PieceID.BISHOP, bishop.getColor());
		moves=bishop.moves;
        setLocation(bishop.location.copy());
	}

	public LinkedList<Move> getMoveList(Board board) {
		LinkedList<Move> possibleMoves = new LinkedList<>();
	

		// check to up and left
		int i = location.getRow() - 1, j = location.getCol() - 1;
		int l=0;
		for (; (i >= 0 && j >= 0); i--, j--) {
			l=i*8+j;
			if (board.getSpot(l).isOccupied()) {
				if (board.getSpot(l).getOccupant().getColor() != getColor()) {

					possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
				} 
					break;
			} else {
				possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
			}
		}
		// check to up and right
		i = location.getRow() - 1;
		j = location.getCol() + 1;
		for (; (i >= 0 && j < 8); i--, j++) {
			l=i*8+j;
			if (board.getSpot(l).isOccupied()) {
				if (board.getSpot(l).getOccupant().getColor() != getColor()) {

					possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));

					break;
				} else {
					break;
				}
			} else {

				possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
			}

		}
		// check down and left
		i = location.getRow() + 1;
		j = location.getCol() - 1;
		for (; (i < 8 && j >= 0); i++, j--) {
			l=i*8+j;
			if (board.getSpot(l).isOccupied()) {
				if (board.getSpot(l).getOccupant().getColor() != getColor()) {

					possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
					break;
				} else {
					break;
				}
			} else {
				possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
			}
		}
		// check to down and right
		i = location.getRow() + 1;
		j = location.getCol() + 1;
		for (; (i < 8 && j < 8); i++, j++) {
			l=i*8+j;
			if (board.getSpot(l).isOccupied()) {
				if (board.getSpot(l).getOccupant().getColor() != getColor()) {
				
						possibleMoves.add(new Move(this,location.NumberForm(),l,board.getSpot(l).getOccupant()));
				
					break;
				} else {
					break;
				}
			} else {
			
					possibleMoves.add(new Move(this,location.NumberForm(),l,board.getSpot(l).getOccupant()));
			
			}
		}

		return possibleMoves;
	}

	

	@Override
	public boolean isLegalMove(Board board, Location to) {
		if (!board.contains(to)) {
			return false;
		}
		if (((abs(this.location.getRow() - to.getRow()) != abs(this.location.getCol() - to.getCol())))) {
			return false;
		}
		if (board.getSpot(to).isOccupied()) {
			if (board.getSpot(to).getOccupant().getColor() == this.getColor()) {
				return false;
			}
		}
		// Checks if there is any piece in between
		if (this.location.getRow()< to.getRow()) {
			if (this.location.getCol() < to.getCol()) {
				for (int i = this.location.getRow() + 1, j = this.location.getCol() + 1; i < to.getRow()
						&& j < to.getCol(); i++, j++) {
					if (board.getSpot(new Location(i, j)).isOccupied()) {
						return false;
					}
				}
			} else {
				for (int i = this.location.getRow() + 1, j = this.location.getCol() - 1; i < to.getRow()
						&& j > to.getCol(); i++, j--) {
					if (board.getSpot(new Location(i, j)).isOccupied()) {
						return false;
					}
				}
			}
		} else {
			if (this.location.getCol() < to.getCol()) {
				for (int i = this.location.getRow() - 1, j = this.location.getCol() + 1; i > to.getRow()
						&& j < to.getCol(); i--, j++) {
					if (board.getSpot(new Location(i, j)).isOccupied()) {
						return false;
					}
				}
			} else {
				for (int i = this.location.getRow() - 1, j = this.location.getCol() - 1; i > to.getRow()
						&& j > to.getCol(); i--, j--) {
					if (board.getSpot(new Location(i, j)).isOccupied()) {
						return false;
					}
				}
			}

		}
		
		return true;

	}

}
