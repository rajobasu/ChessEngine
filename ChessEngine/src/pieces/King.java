package pieces;

import static java.lang.Math.abs;

import java.util.LinkedList;

import frameWork.Board;
import frameWork.Location;
import frameWork.Move;
import frameWork.Spot;
import id.PieceColor;
import id.PieceID;

public class King extends Piece {

	public King(PieceColor color) {
		super(PieceID.KING, color);

	}

	private King(King king) {
		super(PieceID.KING, king.getColor());
		moves = king.moves;
		setLocation(king.location.copy());
	}

	public LinkedList<Move> getMoveList(Board board) {
		LinkedList<Move> possibleMoves = new LinkedList<>();
		Location to = null;

		// check all the squares around the king

		if (board.contains((to = new Location(location.getRow(), location.getCol() - 1)))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
				}
			} else {
				possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains((to = new Location(location.getRow(), location.getCol() + 1)))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
				}
			} else {
				possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains((to = new Location(location.getRow() - 1, location.getCol() - 1)))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
				}
			} else {
				possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains((to = new Location(location.getRow() - 1, location.getCol())))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
				}
			} else {
				possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains((to = new Location(location.getRow() - 1, location.getCol() + 1)))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
				}
			} else {
				possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
			}
		}

		if (board.contains(to = new Location(location.getRow() + 1, location.getCol() - 1))) {

			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
				}
			} else {
				possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains((to = new Location(location.getRow() + 1, location.getCol())))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
				}
			} else {
				possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
			}
		}
		if (board.contains((to = new Location(location.getRow() + 1, location.getCol() + 1)))) {
			if (board.getSpot(to).isOccupied()) {
				if (board.getSpot(to).getOccupant().getColor() != this.getColor()) {
					possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
				}
			} else {
				possibleMoves.add(new Move(this, location, to, board.getSpot(to).getOccupant()));
			}
		}
		return possibleMoves;
	}

	public boolean canCastleShort(Board board) {
		if (board.isKingInCheck(getColor())) {
			return false;
		}

		Location location = this.location;
		// return false;
		Spot spot = null;
		if (getColor() == PieceColor.WHITE) {
			if (location.equals(new Location(7, 4)) && moves == 0) {
				if ((spot = board.getSpot(new Location(7, 7))).isOccupied()) {
					if (spot.getOccupant().getID() == PieceID.ROOK) {
						if (spot.getOccupant().getColor() == PieceColor.WHITE) {
							if (spot.getOccupant().moves == 0) {
								if (!(board.getSpot(new Location(7, 5)).isOccupied()
										|| board.getSpot(new Location(7, 6)).isOccupied())) {
									boolean can = true;
									for (Move m : board.getPossibleMoves(getColor().reverse(), false)) {
										if (m.getTo2() == 61) {
											can = false;
										}
										if (m.getTo2() == 62) {
											can = false;
										}

									}
									if (can == true) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		} else {
			if (location.equals(new Location(0, 4)) && moves == 0) {
				if ((spot = board.getSpot(new Location(0, 7))).isOccupied()) {
					if (spot.getOccupant().getID() == PieceID.ROOK) {
						if (spot.getOccupant().getColor() == PieceColor.BLACK) {
							if (spot.getOccupant().moves == 0) {
								if (!(board.getSpot(new Location(0, 5)).isOccupied()
										|| board.getSpot(new Location(0, 6)).isOccupied())) {
									boolean can = true;
									for (Move m : board.getPossibleMoves(getColor().reverse(), false)) {
										if (m.getTo2() == 5) {
											can = false;
										}
										if (m.getTo2() == 6) {
											can = false;
										}

									}
									if (can == true) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	public boolean canCastleLong(Board board) {
		if (board.isKingInCheck(getColor())) {
			return false;
		}
		Spot spot = null;
		if (getColor() == PieceColor.WHITE) {
			if (location.equals(new Location(7, 4)) && moves == 0) {
				if ((spot = board.getSpot(new Location(7, 0))).isOccupied()) {
					if (spot.getOccupant().getID() == PieceID.ROOK) {
						if (spot.getOccupant().getColor() == PieceColor.WHITE) {
							if (spot.getOccupant().moves == 0) {
								if (!(board.getSpot(new Location(7, 3)).isOccupied()
										|| board.getSpot(new Location(7, 2)).isOccupied()
										|| board.getSpot(new Location(7, 1)).isOccupied())) {
									boolean can = true;
									for (Move m : board.getPossibleMoves(getColor().reverse(), false)) {
										if (m.getTo2() > 56 && m.getTo2() < 60) {
											can = false;
										}
									}
									if (can == true) {
										return true;
									}
								}
							}
						}
					}
				}
			}
		} else {
			if (location.equals(new Location(0, 4)) && moves == 0) {
				if ((spot = board.getSpot(new Location(0, 0))).isOccupied()) {
					if (spot.getOccupant().getID() == PieceID.ROOK) {
						if (spot.getOccupant().getColor() == PieceColor.BLACK) {
							if (spot.getOccupant().moves == 0) {

								if (!(board.getSpot(new Location(0, 3)).isOccupied()
										|| board.getSpot(new Location(0, 2)).isOccupied()
										|| board.getSpot(new Location(0, 1)).isOccupied())) {
									boolean can = true;
									for (Move m : board.getPossibleMoves(getColor().reverse(), false)) {
										if (m.getTo2() > 0 && m.getTo2() < 4) {
											can = false;
										}
									}
									if (can == true) {
										return true;
									}
								}

							}
						}
					}
				}
			}

		}

		return false;
	}

	@Override
	public boolean isLegalMove(Board board, Location to) {
		// TODO Auto-generated method stub
		if (to.equals(location)) {
			return false;
		}
		if ((abs(to.getRow() - this.location.getRow()) > 1)) {
			return false;
		}
		if ((abs(to.getCol() - this.location.getCol()) > 1)) {
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
