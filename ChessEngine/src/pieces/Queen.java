package pieces;

import static java.lang.Math.abs;

import java.util.LinkedList;

import frameWork.Board;
import frameWork.Location;
import frameWork.Move;
import id.PieceColor;
import id.PieceID;

public class Queen extends Piece {

	public Queen(PieceColor color) {
		super(PieceID.QUEEN, color);
	}

	private Queen(Queen queen) {
		super(PieceID.QUEEN, queen.getColor());
		moves=queen.moves;

		setLocation(queen.location.copy());
	}

	public LinkedList<Move> getMoveList(Board board) {
		LinkedList<Move> possibleMoves = new LinkedList<>();

	
		/**
		 *
		 * moves that are diagonal in nature
		 *
		 */
		// check to up and left
		int i = location.getRow() - 1, j = location.getCol() - 1;
		int l=0;
		for (; (i >= 0 && j >= 0); i--, j--) {
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
		// check to up and right
		i = location.getRow() - 1;
		j = location.getCol() + 1;
		l=0;
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
		l=0;
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
		l=0;
		for (; (i < 8 && j < 8); i++, j++) {
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

		/*
		 *
		 * Moves that are linear in nature
		 *
		 */
		for (i = location.getCol() - 1; i >= 0; i--) {
			l=location.getRow()*8 +i;

			if (!(board.getSpot(l).isOccupied())) {
				possibleMoves.add(new Move (this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
			} else {
				if (board.getSpot(l).getOccupant().getColor() != getColor())
					possibleMoves.add(new Move(this, location.NumberForm(), l,board.getSpot(l).getOccupant()));
				break;
			}

		}

		//
		// CHECK TO RIGHT
		for (i = location.getCol() + 1; i < 8; i++) {
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
		for (i = location.getRow() - 1; i >= 0; i--) {
			
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
		for (i = location.getRow() + 1; i < 8; i++) {
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
		boolean result=true;
		
		if (!board.contains(to)) {
			return false;
		}
        if(((abs(this.location.getRow()-to.getRow())!=abs(this.location.getCol()-to.getCol())))&&(to.getRow() != location.getRow() && to.getCol() != location.getCol())){return false;}
        
		// check if proposed location is already blocked by a piece of same
		// color
		if (board.getSpot(to).isOccupied()) {
	
			if (board.getSpot(to).getOccupant().getColor() == getColor()) {
	
				return false;
			}
		}
		// this is where it is checked whether there is any piece in between the
		// two squares
		if((abs(this.location.getRow()-to.getRow())!=abs(this.location.getCol()-to.getCol()))){
		if (to.getCol() != location.getCol()) {
			if (to.getCol() > location.getCol()) {
				for (int i = location.getCol() + 1; i < to.getCol(); i++) {
					Location intermediateLocation = new Location(location.getRow(), i);
					if (board.getSpot(intermediateLocation).isOccupied()) {
						result= false;
					}
						
					
				}
			} else {
				for (int i = location.getCol() - 1; i > to.getCol(); i--) {
					Location intermediateLocation = new Location(location.getRow(), i);
					if (board.getSpot(intermediateLocation).isOccupied()) {
						result= false;
					}
				}
			}
		} else if (to.getRow() != location.getRow()) {
			if (to.getRow() > location.getRow()) {
				for (int i = location.getRow() + 1; i < to.getRow(); i++) {
					Location intermediateLocation = new Location(i, location.getCol());
					if (board.getSpot(intermediateLocation).isOccupied()) {
						result= false;
					}
				}
			} else {
				for (int i = location.getRow() - 1; i > to.getRow(); i--) {
					Location intermediateLocation = new Location(i, location.getCol());
					if (board.getSpot(intermediateLocation).isOccupied()) {
						result= false;
					}
				}
			}
		}
		} 
		//Checks if there is any piece in between
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
		
		
		return result;
	}

}
