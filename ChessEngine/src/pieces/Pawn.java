package pieces;

import static java.lang.Math.abs;

import java.util.LinkedList;

import frameWork.Board;
import frameWork.Location;
import frameWork.Move;
import id.MoveType;
import id.PieceColor;
import id.PieceID;

public class Pawn extends Piece {

	public Pawn(PieceColor color) {
		super(PieceID.PAWN, color);

	}

	private Pawn(Pawn pawn) {
		super(PieceID.PAWN, pawn.getColor());
		moves = pawn.moves;
		setLocation(pawn.location.copy());
	}

	public LinkedList<Move> getMoveList(Board board) {
		LinkedList<Move> possibleMoves = new LinkedList<>();
		Location to = null;

		if (getColor() == PieceColor.WHITE) {

			// see if front square is occupied
			if (board.contains((to = new Location(this.location.getRow() - 1, this.location.getCol())))) {
				if (!(board.getSpot(to).isOccupied())) {
					possibleMoves.add(new Move(this, this.location, to, board.getSpot(to).getOccupant()));
					if (moves == 0) {
						if (board.contains((to = new Location(this.location.getRow() - 2, this.location.getCol())))) {
							if (!(board.getSpot(to).isOccupied())) {
								possibleMoves.add(new Move(this, this.location, to, board.getSpot(to).getOccupant()));
							}
						}
					}
				}
			}

			// see if upper left or upper right are free
			if (this.location.getCol() != 0) {
				if (board.contains((to = new Location(this.location.getRow() - 1, this.location.getCol() - 1)))) {
					if (board.getSpot(to).isOccupied()) {
						if (board.getSpot(to).getOccupant().getColor() != getColor()) {
							possibleMoves.add(new Move(this, this.location, to, board.getSpot(to).getOccupant()));
						}
					}
				}
			}
			if (this.location.getCol() != 7) {
				if (board.contains((to = new Location(this.location.getRow() - 1, this.location.getCol() + 1)))) {

					if (board.getSpot(to).isOccupied()) {
						if (board.getSpot(to).getOccupant().getColor() != getColor()) {
							possibleMoves.add(new Move(this, this.location, to, board.getSpot(to).getOccupant()));
						}
					}
				}
			}

		} else {
			// see if front square is occupied
			if (board.contains((to = new Location(this.location.getRow() + 1, this.location.getCol())))) {
				if (!(board.getSpot(to).isOccupied())) {
					possibleMoves.add(new Move(this, this.location, to, board.getSpot(to).getOccupant()));
					if (moves == 0) {
						if (board.contains((to = new Location(this.location.getRow() + 2, this.location.getCol())))) {
							if (!(board.getSpot(to).isOccupied())) {
								possibleMoves.add(new Move(this, this.location, to, board.getSpot(to).getOccupant()));
							}
						}
					}
				}
			}

			// see if upper left or upper right are free
			if (this.location.getCol() != 0) {
				if (board.getSpot((to = new Location(this.location.getRow() + 1, this.location.getCol() - 1)))
						.isOccupied()) {
					if (board.getSpot(to).getOccupant().getColor() != getColor()) {
						possibleMoves.add(new Move(this, this.location, to, board.getSpot(to).getOccupant()));
					}
				}
			}
			if (this.location.getCol() != 7) {
				if (board.getSpot((to = new Location(this.location.getRow() + 1, this.location.getCol() + 1)))
						.isOccupied()) {
					if (board.getSpot(to).getOccupant().getColor() != getColor()) {
						possibleMoves.add(new Move(this, this.location, to, board.getSpot(to).getOccupant()));
					}
				}

			}
			// check for enpassents

			/*
			 * Move m = board.getLastMove(); if(m.getType()==MoveType.NORMAL){
			 * if (m != null) { if (getColor() == PieceColor.WHITE) { if
			 * (location.getRow() == 3 && m.getTo().getRow() == 3) { if
			 * (m.getPiece().getID() == PieceID.PAWN && m.getPiece().moves == 1)
			 * { if (location.getCol() == m.getTo().getCol() + 1) {
			 * 
			 * possibleMoves.add( new Move(this, location, new Location(2,
			 * location.getCol() - 1), m.getPiece())); } else if
			 * ((location.getCol() == m.getTo().getCol() - 1)) {
			 * possibleMoves.add( new Move(this, location, new Location(2,
			 * location.getCol() + 1), m.getPiece()));
			 * 
			 * } } }
			 * 
			 * } else { if (location.getRow() == 4 && m.getTo().getRow() == 4) {
			 * if (m.getPiece().getID() == PieceID.PAWN && m.getPiece().moves ==
			 * 1) { if (location.getCol() == m.getTo().getCol() + 1) {
			 * 
			 * possibleMoves.add( new Move(this, location, new Location(5,
			 * location.getCol() - 1), m.getPiece())); } else if
			 * ((location.getCol() == m.getTo().getCol() - 1)) {
			 * possibleMoves.add( new Move(this, location, new Location(5,
			 * location.getCol() + 1), m.getPiece()));
			 * 
			 * } } }
			 * 
			 * }
			 * 
			 * } }
			 */

			/*
			 * to = new Location(location.getRow(), location.getCol() + 1); if
			 * (board.getSpot(to).isOccupied()) { Piece p =
			 * board.getSpot(to).getOccupant(); if (p.getID() == PieceID.PAWN) {
			 * if (p.getColor() != getColor()) { if (p.moves == 1) {
			 * possibleMoves.add(new Move(this, location, new
			 * Location(location.getRow()+((getColor()==PieceColor.WHITE)?-1:+1)
			 * ,location.getCol()+1), p)); }
			 * 
			 * } } }
			 * 
			 * to = new Location(location.getRow(), location.getCol() - 1); if
			 * (board.getSpot(to).isOccupied()) { Piece p =
			 * board.getSpot(to).getOccupant(); if (p.getID() == PieceID.PAWN) {
			 * 
			 * if (p.getColor() != getColor()) { if (p.moves == 1) {
			 * possibleMoves.add(new Move(this, location, new
			 * Location(location.getRow()+((getColor()==PieceColor.WHITE)?-1:+1)
			 * ,location.getCol()-1), p)); }
			 * 
			 * } } }
			 */
		}
		Move m = board.getLastMove();

		if (m != null)
			if (m.getType() == MoveType.NORMAL) {
				Piece p = m.getPiece();
				if (p.getColor() != getColor()) {

					if (p.getMoves() == 1) {

						if (p.getID() == PieceID.PAWN) {

							if (getColor() == PieceColor.WHITE) {

								if (p.getLocation().getRow() == 3 && location.getRow() == 3) {

									if (abs(p.location.getCol() - location.getCol()) == 1) {

										possibleMoves.add(new Move(this, this.location,
												new Location(2, p.getLocation().getCol()), p, MoveType.EN_PASSENT));

									}
								}
							} else {
								if (p.getLocation().getRow() == 4 && location.getRow() == 4) {

									if (abs(p.location.getCol() - location.getCol()) == 1) {
										possibleMoves.add(new Move(this, this.location,
												new Location(5, p.getLocation().getCol()), p, MoveType.EN_PASSENT));
									}

								}
							}
						}
					}
				}
			}

		return possibleMoves;
	}

	public Piece copy() {
		return new Pawn(this);
	}

	@Override
	public boolean isLegalMove(Board board, Location to) {

		if (getColor() == PieceColor.WHITE) {
			if (to.getRow() >= location.getRow()) {
				return false;
			}
		} else {
			if (to.getRow() <= location.getRow()) {
				return false;
			}
		}
		if ((abs(to.getRow() - location.getRow()) == 2) && (moves != 0))
			return false;
		// cannot go 2 rows in front or stay in the same row
		if (abs(to.getRow() - location.getRow()) > 2 - ((moves == 0) ? 0 : 1)) {
			return false;
		}
		if (abs(to.getRow() - location.getRow()) == 0) {
			return false;
		}

		// cannot go forward if the place is blocked
		if (abs(to.getCol() - location.getCol()) == 0) {
			if (board.getSpot(to).isOccupied()) {
				return false;
			}
		}

		// cannot fo more than 1 col away
		if (abs(to.getCol() - location.getCol()) > 1) {
			return false;
		}

		// cannot go if col diff is 1 and row diff is not 1
		if (abs(to.getCol() - location.getCol()) == 1 && abs(to.getRow() - location.getRow()) != 1)
			return false;

		if (board.getSpot(to).isOccupied()) {

			if ((this.location.getCol() == to.getCol())) {
				return false;
			} else if (board.getSpot(to).getOccupant().getColor() == this.getColor()) {
				return false;
			}
		} else {
			if (abs(to.getCol() - location.getCol()) == 1 && abs(to.getRow() - location.getRow()) == 1) {
				Location l1 = new Location(location.getRow(), location.getCol() - 1);
				Location l2 = new Location(location.getRow(), location.getCol() + 1);

				if (!(board.getSpot(l1).isOccupied() || board.getSpot(l1).isOccupied())) {
					return false;
				} else if (board.getSpot(l1).isOccupied()) {
					if (!(board.getSpot(l1).getOccupant().moves == 1
							&& board.getSpot(l1).getOccupant().getID() == PieceID.PAWN)) {
						return false;
					}
				} else if (board.getSpot(l2).isOccupied()) {
					if (!(board.getSpot(l2).getOccupant().moves == 1
							&& board.getSpot(l2).getOccupant().getID() == PieceID.PAWN)) {
						return false;
					}
				}
			}

		}

		return true;
	}

}
