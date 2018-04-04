package pieces;

import java.util.LinkedList;
import static java.lang.Math.*;
import frameWork.Board;
import frameWork.Location;
import frameWork.Move;
import id.PieceColor;
import id.PieceID;

public class Knight extends Piece {

	public Knight(PieceColor color) {
		super(PieceID.KNIGHT, color);
	}

	private Knight(Knight knight) {
		super(PieceID.KNIGHT, knight.getColor());
		moves=knight.moves;
	setLocation(knight.location.copy());
	}

	public LinkedList<Move> getMoveList(Board board) {
		LinkedList<Move> possibleMoves = new LinkedList<>();
		Location to;
		if (board.contains(to = new Location(this.location.getRow() - 2, this.location.getCol() + 1))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
				}
			}else{
				possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains(to = new Location(this.location.getRow() - 2, this.location.getCol() - 1))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
				}
			}else{
				possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains(to = new Location(this.location.getRow() + 2, this.location.getCol() + 1))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
				}
			}else{
				possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains(to = new Location(this.location.getRow() + 2, this.location.getCol() - 1))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
				}
			}else{
				possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains(to = new Location(this.location.getRow() - 1, this.location.getCol() + 2))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
				}
			}else{
				possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains(to = new Location(this.location.getRow() - 1, this.location.getCol() - 2))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
				}
			}else{
				possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains(to = new Location(this.location.getRow() + 1, this.location.getCol() + 2))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
				}
			}else{
				possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains(to = new Location(this.location.getRow() + 1, this.location.getCol() - 2))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
				}
			}else{
				possibleMoves.add(new Move(this, location, to,board.getSpot(to).getOccupant()));
			}
		}
		return possibleMoves;
	}

	

	@Override
	public boolean isLegalMove(Board board, Location to) {
		if (!(((abs(location.getCol() - to.getCol()) == 1) && (abs(location.getRow() - to.getRow()) == 2))
				|| ((abs(location.getCol() - to.getCol()) == 2)
						&& (abs(location.getRow() - to.getRow()) == 1)))) {
			return false;
		}
		if (!(board.contains(to))) {
			return false;
		}
		if (board.getSpot(to).isOccupied()) {
			if (board.getSpot(to).getOccupant().getColor() == getColor()) {
				return false;
			}
		}

		return true;
	}

}
