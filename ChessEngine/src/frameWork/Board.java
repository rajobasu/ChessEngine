package frameWork;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

import id.MoveType;
import id.PieceColor;
import id.PieceID;
import pieces.King;
import pieces.Piece;
import pieces.Queen;

public class Board {
	private Spot[] spots = new Spot[64];
	private King black_king;
	private King white_king;
	private boolean blackKingSet;
	private boolean whiteKingSet;
	// public Move lastMove;
	public static int posMvCtr;
	public static int pmx;
	private Stack<Move> moveStack;
	public static long aaaa;
	public static long bbbb;

	private LinkedList<Piece> whitePieces = new LinkedList<>();
	private LinkedList<Piece> blackPieces = new LinkedList<>();

	// private HashMap<String, LinkedList<Move>> wh_ml;
	// private HashMap<String, LinkedList<Move>> bl_ml;

	// private HashMap<Move, LinkedList<Move>> preCalcMoves=new HashMap<>();

	public Board() {
		moveStack = new Stack<>();
		// wh_ml = new HashMap<>();
		// bl_ml = new HashMap<>();

		// INIT BOARD
		for (int i = 0; i < spots.length; i++) {
			spots[i] = new Spot(new Location(i / 8, i % 8));
		}
		////
	}

	public void addPiece(Piece piece, Location location) {
		getSpot(location).occupy(piece);

	}

	public void removePiece(Location location) {
		getSpot(location).vacate();
	}

	public Spot getSpot(Location location) {

		try {
			return spots[location.getRow() * 8 + location.getCol()];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(location);
		} finally {

		}
		return null;
	}

	public Spot getSpot(int sqr) {
		return spots[sqr];
	}

	public String toString() {
		String out = "";
		for (Spot s : spots) {
			out += s + " , " + "\t ";
		}
		out += "\n";

		out += " \n \n ";
		out += whitePieces;
		out += blackPieces;
		out += " \n ";

		return out;
	}

	public LinkedList<Piece> getBlackPieces() {
		LinkedList<Piece> pieces = new LinkedList<>();
		for (int i = 0; i < 64; i++) {
			if (spots[i].isOccupied()) {
				if (spots[i].getOccupant().getColor() == PieceColor.BLACK) {
					pieces.add(spots[i].getOccupant());
				}
			}

		}

		return pieces;
	}

	public LinkedList<Piece> getWhitePieces() {
		LinkedList<Piece> pieces = new LinkedList<>();
		for (int i = 0; i < 64; i++) {

			if (spots[i].isOccupied()) {
				if (spots[i].getOccupant().getColor() == PieceColor.WHITE) {
					pieces.add(spots[i].getOccupant());
				}

			}
		}
		return pieces;

	}

	public boolean contains(Location to) {
		if (to.getCol() >= 0 && to.getCol() < 8 && to.getRow() >= 0 && to.getRow() < 8) {
			return true;
		}
		return false;
	}

	public boolean contains(int sqr) {
		return (sqr > 0 && sqr < 64);
	}

	private void executeIndividualMove(Move move) {
		Piece piece = move.getPiece();
		Piece p = move.getCapturedPiece();

		piece.moved();

		int from = move.getFrom2();
		int to = move.getTo2();

		Spot spotFrom = getSpot(from);
		Spot spotTo = getSpot(to);

		spotFrom.vacate();
		if (p != null) {
			if (p.getColor() == PieceColor.WHITE) {
				whitePieces.remove(p);
			} else {
				blackPieces.remove(p);
			}

		}

		spotTo.occupy(piece);

	}

	private void executeEnpassentMove(Move move) {
		Piece piece = move.getPiece();
		Piece p = move.getCapturedPiece();

		piece.moved();

		int from = move.getFrom2();
		int to = move.getTo2();

		Spot spotFrom = getSpot(from);
		Spot spotTo = getSpot(to);

		spotFrom.vacate();
		int pwnSqrLocation = (move.getFrom().NumberForm() / 8) * 8 + move.getTo().NumberForm() % 8;
		Location ll = null;
		Spot pwnSqr = getSpot(ll = new Location(move.getFrom().getRow(), move.getTo().getCol()));
		if (ll.NumberForm() != pwnSqrLocation) {
			throw new IllegalArgumentException(ll.toString() + "   " + pwnSqrLocation);
		}
		pwnSqr.vacate();

		if (p.getColor() == PieceColor.WHITE) {
			whitePieces.remove(p);
		} else {
			blackPieces.remove(p);
		}

		spotTo.occupy(piece);

	}

	public boolean isStaleMate(PieceColor color) {
		if (getPossibleMoves(color, false).size() == 0 && !isCheckMate(color)) {
			return true;
		} else {
			return false;
		}
	}

	public void executeMove(Move move) {
		if (move.getType() == MoveType.NORMAL || move.getType() == MoveType.CAPTURE) {
			executeIndividualMove(move);
			if (move.getPiece().getID() == PieceID.PAWN) {
				if (move.getTo().getRow() == 0 || move.getTo().getRow() == 7) {
					this.removePiece(move.getTo());
					Queen q = null;
					this.addPiece(q = new Queen(move.getPiece().getColor()), move.getTo());

					if (move.getPiece().getColor() == PieceColor.WHITE) {
						whitePieces.remove(move.getPiece());
						whitePieces.add(q);
					} else {
						blackPieces.remove(move.getPiece());
						blackPieces.add(q);
					}
				}
			}
		} else if (move.getType() == MoveType.EN_PASSENT) {
			executeEnpassentMove(move);
		} else {

			executeIndividualMove(move.getIndividualMoves()[0]);

			executeIndividualMove(move.getIndividualMoves()[1]);

		}

		moveStack.push(move);
	}

	public void setBlackKing(Piece king) {
		if (!blackKingSet) {
			if (king instanceof King) {
				black_king = (King) king;
				blackKingSet = false;
			}
		}
	}

	public void setWhiteKing(Piece king) {
		if (!whiteKingSet) {
			if (king instanceof King) {
				white_king = (King) king;
				whiteKingSet = false;
			}
		}
	}

	/**
	 * specify the colour for which the move list is to be generated. the
	 * boolean... b paramater is to be passed a 'false' if the kingsafety is NOT
	 * to be checked. This is used especially to generate castling moves. This
	 * prevents a recursion from taking place as for king safety too, this
	 * method is called.
	 * 
	 * @param color
	 * @param b
	 * @return
	 */
	public LinkedList<Move> getPossibleMoves(PieceColor color, boolean... b) {
		// if (color == PieceColor.WHITE && wh_ml.containsKey(getBoardConfig()))
		// {
		// return wh_ml.get(getBoardConfig());
		//
		// } else if (bl_ml.containsKey(getBoardConfig())) {
		// return bl_ml.get(getBoardConfig());
		// }

		long t1 = System.currentTimeMillis();
		pmx++;
		// Testing.addPos(getBoardConfig());
		LinkedList<Move> possibleMoves = new LinkedList<>();
		LinkedList<Piece> pieces = getPieces(color);

		for (int i = 0; i < pieces.size(); i++) {
			Piece piece = pieces.get(i);
			for (Move move : piece.getMoveList(this)) {
				executeMove(move);

				if (!isKingInCheck(color)) {
					// System.out.println(move);
					possibleMoves.add(move);
				} else {
					// hmp.add(getBoardConfig());
				}
				undoMove(move);
			}
		}
		if (b.length == 0) {
			if (getKing(color).canCastleShort(this)) {
				possibleMoves.add(new Move(
						(color == PieceColor.WHITE) ? MoveType.SHORTCASTLE_WHITE : MoveType.SHORTCASTLE_BLACK));
			}
			if (getKing(color).canCastleLong(this)) {
				possibleMoves.add(
						new Move((color == PieceColor.WHITE) ? MoveType.LONGCASTLE_WHITE : MoveType.LONGCASTLE_BLACK));
			}

		}

		Collections.sort(possibleMoves, new Comparator<Move>() {
			@Override
			public int compare(Move o1, Move o2) {
				int n1 = 0;
				if (o2.getType() == MoveType.NORMAL) {
					n1 = o2.getPiece().getID().getPrecedence();
				} else if (o2.getType() == MoveType.CAPTURE) {
					n1 = o2.getPiece().getID().getPrecedence() + o2.getCapturedPiece().getID().getPrecedence();
				} else {
					n1 = 11;
				}
				int n2 = 0;
				if (o1.getType() == MoveType.NORMAL) {
					n2 = o1.getPiece().getID().getPrecedence();
				} else if (o1.getType() == MoveType.CAPTURE) {
					n2 = o1.getPiece().getID().getPrecedence() + o1.getCapturedPiece().getID().getPrecedence();
				} else {
					n2 = 11;
				}

				return n1 - n2;
			}

		});
		posMvCtr += System.currentTimeMillis() - t1;
		// if (color == PieceColor.WHITE) {
		// wh_ml.put(getBoardConfig(), possibleMoves);
		// } else {
		// bl_ml.put(getBoardConfig(), possibleMoves);
		// }

		return possibleMoves;
	}

	public boolean isKingInCheck(PieceColor color) {

		// LinkedList<Move> moves = new LinkedList<>();
		if (color == PieceColor.WHITE) {
			for (Piece piece : blackPieces) {
				if (piece.isLegalMove(this, white_king.getLocation())) {
					return true;
				}
			}
		} else if (color == PieceColor.BLACK) {
			for (Piece piece : whitePieces) {
				if (piece.isLegalMove(this, black_king.getLocation())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isCheckMate(PieceColor color) {
		if (getPossibleMoves(color).isEmpty() && isKingInCheck(color)) {
			return true;
		} else {
			return false;
		}
	}

	private void undoIndividualMove(Move move) {
		Spot to = getSpot(move.getTo());
		Spot from = getSpot(move.getFrom());

		Piece p = move.getCapturedPiece();
		if (p != null) {
			if (p.getColor() == PieceColor.WHITE) {
				whitePieces.add(p);
			} else {
				blackPieces.add(p);
			}
		}

		to.vacate();
		move.getPiece().unmoved();

		to.occupy(move.getCapturedPiece());
		from.occupy(move.getPiece());
		if (move.getCapturedPiece() != null)
			move.getCapturedPiece().unCaptured();
	}

	public void undoMove(Move move) {
		if (move.getType() == MoveType.CAPTURE || move.getType() == MoveType.NORMAL) {

			if (move.getPiece().getID() == PieceID.PAWN) {
				if (move.getTo().getRow() == 0 || move.getTo().getRow() == 7) {
					if (move.getPiece().getColor() == PieceColor.WHITE) {
						whitePieces.add(move.getPiece());
						whitePieces.remove(getSpot(move.getTo()).getOccupant());
					} else {
						blackPieces.add(move.getPiece());
						blackPieces.remove(getSpot(move.getTo()).getOccupant());
					}
				}
			}
			undoIndividualMove(move);
		} else if (move.getType() == MoveType.EN_PASSENT) {
			undoEnpassentMove(move);
		} else {
			undoIndividualMove(move.getIndividualMoves()[0]);
			undoIndividualMove(move.getIndividualMoves()[1]);

		}
		moveStack.pop();
	}

	private void undoEnpassentMove(Move move) {
		Spot to = getSpot(move.getTo());
		Spot from = getSpot(move.getFrom());

		Piece p = move.getCapturedPiece();

		if (p.getColor() == PieceColor.WHITE) {
			whitePieces.add(p);
		} else {
			blackPieces.add(p);
		}

		to.vacate();
		move.getPiece().unmoved();

		to = getSpot(new Location(move.getFrom().getRow(), move.getTo().getCol()));
		to.occupy(move.getCapturedPiece());

		from.occupy(move.getPiece());

		if (move.getCapturedPiece() != null)
			move.getCapturedPiece().unCaptured();
	}

	public LinkedList<Piece> getPieces(PieceColor color) {

		return (color == PieceColor.WHITE) ? whitePieces : blackPieces;
	}

	public LinkedList<Piece> getAllPieces() {
		LinkedList<Piece> p = new LinkedList<>();
		for (Piece fp : whitePieces) {
			p.add(fp);
		}
		for (Piece fp : blackPieces) {
			p.add(fp);
		}

		return p;
	}

	public King getKing(PieceColor clr) {
		if (clr == PieceColor.BLACK)
			return black_king;
		if (clr == PieceColor.WHITE)
			return white_king;
		return null;
	}

	public LinkedList<Piece> getColumnOccupants(int col) {
		LinkedList<Piece> pieces = new LinkedList<>();
		Piece p = null;
		for (int i = 0; i < 8; i++) {
			if ((p = getSpot(new Location(i, col)).getOccupant()) != null) {
				pieces.add(p);
			}
		}

		return pieces;
	}

	public LinkedList<Piece> getRowOccupants(int row) {
		LinkedList<Piece> pieces = new LinkedList<>();
		Piece p = null;
		for (int i = 0; i < 8; i++) {
			if ((p = getSpot(new Location(row, i)).getOccupant()) != null) {
				pieces.add(p);
			}
		}

		return pieces;
	}

	public LinkedList<Move> trimIllegalMoves(LinkedList<Move> mv) {
		LinkedList<Move> mvfr = new LinkedList<>();
		for (Move move : mv) {

			executeMove(move);
			if (!isKingInCheck(move.getPiece().getColor())) {
				// System.out.println(move);
				mvfr.add(move);
			}
			undoMove(move);
		}
		return mvfr;
	}

	public void formPieceList() {
		whitePieces = getWhitePieces();
		blackPieces = getBlackPieces();
	}

	public String getBoardConfig() {
		String out = "";
		for (Spot s : spots) {
			String s2 = s.toString();
			out += s2;
		}

		return out;
	}

	public Move getLastMove() {
		if (moveStack.isEmpty()) {
			return null;
		}
		return moveStack.peek();
	}

	public void printMoveStack() {
		System.out.println(moveStack);

	}

}
