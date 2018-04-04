package frameWork;

import id.MoveType;
import id.PieceColor;
import pieces.Piece;

public class Move {

	private int searchVal;
	private final Location from;
	private final Location to;
	private final MoveType mvType;
	private final Piece piece;
	private final Piece capture;
	private final Move[] individualMoves;

	public Move(Piece piece, Location from, Location to, Piece capture) {
		this.from = from;
		this.to = to;
		this.piece = piece;
		if (capture != null) {
			mvType = MoveType.CAPTURE;
		} else {
			mvType = MoveType.NORMAL;
		}
		individualMoves = null;
		this.capture = capture;
	}

	public Move(Piece piece, int from, int to, Piece capture, MoveType mvType) {
		this.from = new Location(from);
		this.to = new Location(to);
		this.piece = piece;

		this.mvType = mvType;

		individualMoves = null;
		this.capture = capture;
	}

	public Move(Piece piece, Location from, Location to, Piece capture, MoveType mvType) {
		this.from = from;
		this.to = to;
		this.piece = piece;

		this.mvType = mvType;

		individualMoves = null;
		this.capture = capture;
	}

	public Move(Piece piece, int from, int to, Piece capture) {
		this.from = new Location(from);
		this.to = new Location(to);
		this.piece = piece;
		if (capture != null) {
			mvType = MoveType.CAPTURE;
		} else {
			mvType = MoveType.NORMAL;
		}
		individualMoves = null;
		this.capture = capture;
	}

	public Move(MoveType mvType) {
		this.mvType = mvType;
		switch (mvType) {
		case SHORTCASTLE_BLACK: {
			individualMoves = new Move[2];
			individualMoves[0] = new Move(Game.getCurrentPosition().getKing(PieceColor.BLACK), new Location(0, 4),
					new Location(0, 6), null);
			individualMoves[1] = new Move(Game.getCurrentPosition().getSpot(new Location(0, 7)).getOccupant(),
					new Location(0, 7), new Location(0, 5), null);
			break;
		}
		case SHORTCASTLE_WHITE: {

			individualMoves = new Move[2];
			individualMoves[0] = new Move(Game.getCurrentPosition().getKing(PieceColor.WHITE), new Location(7, 4),
					new Location(7, 6), null);
			individualMoves[1] = new Move(Game.getCurrentPosition().getSpot(new Location(7, 7)).getOccupant(),
					new Location(7, 7), new Location(7, 5), null);
			break;
		}
		case LONGCASTLE_BLACK: {
			individualMoves = new Move[2];
			individualMoves[0] = new Move(Game.getCurrentPosition().getKing(PieceColor.BLACK), new Location(0, 4),
					new Location(0, 2), null);
			individualMoves[1] = new Move(Game.getCurrentPosition().getSpot(new Location(0, 0)).getOccupant(),
					new Location(0, 0), new Location(0, 3), null);
			break;
		}
		case LONGCASTLE_WHITE: {
			individualMoves = new Move[2];
			individualMoves[0] = new Move(Game.getCurrentPosition().getKing(PieceColor.WHITE), new Location(7, 4),
					new Location(7, 2), null);
			individualMoves[1] = new Move(Game.getCurrentPosition().getSpot(new Location(7, 0)).getOccupant(),
					new Location(7, 0), new Location(7, 3), null);
			break;
		}
		default:
			individualMoves=null;
			break;
		}
		
		capture=null;
		from=null;
		piece=null;
		to=null;
		

	}

	public int getFrom2() {
		return from.NumberForm();
	}

	public int getTo2() {
		return to.NumberForm();
	}

	public Location getFrom() {
		return from;
	}

	public Location getTo() {
		return to;
	}

	public Piece getPiece() {
		return piece;
	}

	public String toString() {
		String ret = "";
		ret += piece + " :" + from + "  >>  " + to + " :: Type  " + mvType;

		return ret;
	}

	public MoveType getType() {
		return mvType;
	}

	public int getSearchVal() {
		return searchVal;
	}

	public void setSearchVal(int searchVal) {
		this.searchVal = searchVal;
	}

	public Piece getCapturedPiece() {
		return capture;
	}

	public boolean equals(Object mv) {
		if (!(mv instanceof Move)) {
			return false;
		}
		if (mv.toString().toLowerCase().trim().equals(this.toString().toLowerCase().trim())) {
			return true;
		}

		return false;
	}

	public Move[] getIndividualMoves() {
		return individualMoves;
	}

}
