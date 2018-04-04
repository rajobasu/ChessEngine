package frameWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import evaluation.KingSafety;
import evaluation.PositionEvaluator;
import id.MoveType;
import id.PieceColor;
import id.PieceID;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
import ui.UIBoard;

public class Game {
	private static Board board;
	private boolean ended = false;
	private MinMaxAlphaBetaQuiscence mnmx;
	private PieceColor color;
	public Move last;
	private static LinkedList<String> boardConfigs = new LinkedList<>();

	{
		boardConfigs.clear();
		board = null;
	}

	public Game(PieceColor color, int depth, boolean quisce, int time) throws IOException {
		new BufferedReader(new InputStreamReader(System.in));
		board = new Board();
		mnmx = new MinMaxAlphaBetaQuiscence(color, depth, board, quisce);

		this.color = color;
	}
	public void testInit(){
		Piece king = new King(PieceColor.WHITE);
		Piece queen = new Queen(PieceColor.WHITE);
		Piece rook1 = new Rook(PieceColor.WHITE);
		Piece rook2 = new Rook(PieceColor.WHITE);
		Piece bishop1 = new Bishop(PieceColor.WHITE);
		Piece bishop2 = new Bishop(PieceColor.WHITE);
		Piece knight1 = new Knight(PieceColor.WHITE);
		Piece knight2 = new Knight(PieceColor.WHITE);

		Piece pawn1 = new Pawn(PieceColor.WHITE);
		Piece pawn2 = new Pawn(PieceColor.WHITE);
		Piece pawn3 = new Pawn(PieceColor.WHITE);
		Piece pawn4 = new Pawn(PieceColor.WHITE);
		Piece pawn5 = new Pawn(PieceColor.WHITE);
		Piece pawn6 = new Pawn(PieceColor.WHITE);
		Piece pawn7 = new Pawn(PieceColor.WHITE);
		Piece pawn8 = new Pawn(PieceColor.WHITE);

		board.addPiece(king, new Location(7, 6));
		board.addPiece(queen, new Location(7, 5));
		board.addPiece(rook1, new Location(6, 7));
		board.addPiece(rook2, new Location(7, 7));
		board.addPiece(bishop1, new Location(5, 7));
		board.addPiece(bishop2, new Location(7, 5));
		board.addPiece(knight1, new Location(7, 6));
		board.addPiece(knight2, new Location(7, 1));

		board.addPiece(pawn1, new Location(6, 7));
		board.addPiece(pawn2, new Location(6, 6));
		board.addPiece(pawn3, new Location(6, 5));
		board.addPiece(pawn4, new Location(6, 4));
		board.addPiece(pawn5, new Location(6, 3));
		board.addPiece(pawn6, new Location(6, 2));
		board.addPiece(pawn7, new Location(6, 1));
		board.addPiece(pawn8, new Location(6, 0));

		// black Pieces
		Piece _king = new King(PieceColor.BLACK);
		Piece _queen = new Queen(PieceColor.BLACK);
		Piece _rook1 = new Rook(PieceColor.BLACK);
		Piece _rook2 = new Rook(PieceColor.BLACK);
		Piece _bishop1 = new Bishop(PieceColor.BLACK);
		Piece _bishop2 = new Bishop(PieceColor.BLACK);
		Piece _knight1 = new Knight(PieceColor.BLACK);
		Piece _knight2 = new Knight(PieceColor.BLACK);

		Piece _pawn1 = new Pawn(PieceColor.BLACK);
		Piece _pawn2 = new Pawn(PieceColor.BLACK);
		Piece _pawn3 = new Pawn(PieceColor.BLACK);
		Piece _pawn4 = new Pawn(PieceColor.BLACK);
		Piece _pawn5 = new Pawn(PieceColor.BLACK);
		Piece _pawn6 = new Pawn(PieceColor.BLACK);
		Piece _pawn7 = new Pawn(PieceColor.BLACK);
		Piece _pawn8 = new Pawn(PieceColor.BLACK);

		board.addPiece(_king, new Location(0, 6));
		board.addPiece(_queen, new Location(0, 5));
		board.addPiece(_rook1, new Location(0, 0));
		board.addPiece(_rook2, new Location(0, 7));
		board.addPiece(_bishop1, new Location(0, 2));
		board.addPiece(_bishop2, new Location(0, 5));
		board.addPiece(_knight1, new Location(0, 6));
		board.addPiece(_knight2, new Location(0, 1));
		
		board.addPiece(_pawn1, new Location(1, 7));
		board.addPiece(_pawn2, new Location(1, 6));
		board.addPiece(_pawn3, new Location(1, 5));
		board.addPiece(_pawn4, new Location(2, 6));
		board.addPiece(_pawn5, new Location(2, 5));
		board.addPiece(_pawn6, new Location(1, 2));
		board.addPiece(_pawn7, new Location(1, 1));
		board.addPiece(_pawn8, new Location(1, 0));
		// add kings to the board
		board.setBlackKing(_king);
		board.setWhiteKing(king);

		board.formPieceList();
	}

	public void init() throws IOException {

/*		testInit();
		if(1<2)return ;
*/		// white Pieces
		Piece king = new King(PieceColor.WHITE);
		Piece queen = new Queen(PieceColor.WHITE);
		Piece rook1 = new Rook(PieceColor.WHITE);
		Piece rook2 = new Rook(PieceColor.WHITE);
		Piece bishop1 = new Bishop(PieceColor.WHITE);
		Piece bishop2 = new Bishop(PieceColor.WHITE);
		Piece knight1 = new Knight(PieceColor.WHITE);
		Piece knight2 = new Knight(PieceColor.WHITE);

		Piece pawn1 = new Pawn(PieceColor.WHITE);
		Piece pawn2 = new Pawn(PieceColor.WHITE);
		Piece pawn3 = new Pawn(PieceColor.WHITE);
		Piece pawn4 = new Pawn(PieceColor.WHITE);
		Piece pawn5 = new Pawn(PieceColor.WHITE);
		Piece pawn6 = new Pawn(PieceColor.WHITE);
		Piece pawn7 = new Pawn(PieceColor.WHITE);
		Piece pawn8 = new Pawn(PieceColor.WHITE);

		board.addPiece(king, new Location(7, 4));
		board.addPiece(queen, new Location(7, 3));
		board.addPiece(rook1, new Location(7, 0));
		board.addPiece(rook2, new Location(7, 7));
		board.addPiece(bishop1, new Location(7, 2));
		board.addPiece(bishop2, new Location(7, 5));
		board.addPiece(knight1, new Location(7, 6));
		board.addPiece(knight2, new Location(7, 1));

		board.addPiece(pawn1, new Location(6, 7));
		board.addPiece(pawn2, new Location(6, 6));
		board.addPiece(pawn3, new Location(6, 5));
		board.addPiece(pawn4, new Location(6, 4));
		board.addPiece(pawn5, new Location(6, 3));
		board.addPiece(pawn6, new Location(6, 2));
		board.addPiece(pawn7, new Location(6, 1));
		board.addPiece(pawn8, new Location(6, 0));

		// black Pieces
		Piece _king = new King(PieceColor.BLACK);
		Piece _queen = new Queen(PieceColor.BLACK);
		Piece _rook1 = new Rook(PieceColor.BLACK);
		Piece _rook2 = new Rook(PieceColor.BLACK);
		Piece _bishop1 = new Bishop(PieceColor.BLACK);
		Piece _bishop2 = new Bishop(PieceColor.BLACK);
		Piece _knight1 = new Knight(PieceColor.BLACK);
		Piece _knight2 = new Knight(PieceColor.BLACK);

		Piece _pawn1 = new Pawn(PieceColor.BLACK);
		Piece _pawn2 = new Pawn(PieceColor.BLACK);
		Piece _pawn3 = new Pawn(PieceColor.BLACK);
		Piece _pawn4 = new Pawn(PieceColor.BLACK);
		Piece _pawn5 = new Pawn(PieceColor.BLACK);
		Piece _pawn6 = new Pawn(PieceColor.BLACK);
		Piece _pawn7 = new Pawn(PieceColor.BLACK);
		Piece _pawn8 = new Pawn(PieceColor.BLACK);

		board.addPiece(_king, new Location(0, 4));
		board.addPiece(_queen, new Location(0, 3));
		board.addPiece(_rook1, new Location(0, 0));
		board.addPiece(_rook2, new Location(0, 7));
		board.addPiece(_bishop1, new Location(0, 2));
		board.addPiece(_bishop2, new Location(0, 5));
		board.addPiece(_knight1, new Location(0, 6));
		board.addPiece(_knight2, new Location(0, 1));
		board.addPiece(_pawn1, new Location(1, 7));
		board.addPiece(_pawn2, new Location(1, 6));
		board.addPiece(_pawn3, new Location(1, 5));
		board.addPiece(_pawn4, new Location(1, 4));
		board.addPiece(_pawn5, new Location(1, 3));
		board.addPiece(_pawn6, new Location(1, 2));
		board.addPiece(_pawn7, new Location(1, 1));
		board.addPiece(_pawn8, new Location(1, 0));
		// add kings to the board
		board.setBlackKing(_king);
		board.setWhiteKing(king);

		board.formPieceList();
	}

	public String playerMove(int val2, int val1) {

		Location to = new Location(val1 / 10, val1 % 10);
		Location from = new Location(val2 / 10, val2 % 10);

		Move mv = null;
		if (board.getSpot(from).getOccupant().getID() == PieceID.KING) {
			if (Math.abs(to.getCol() - from.getCol()) == 2) {
				if (board.getSpot(from).getOccupant().getColor() == PieceColor.WHITE) {
					if (from.getCol() < to.getCol()) {
						mv = new Move(MoveType.SHORTCASTLE_WHITE);
					} else {

						mv = new Move(MoveType.LONGCASTLE_WHITE);

					}
				} else {
					if (board.getKing(PieceColor.BLACK).getLocation().getCol() < to.getCol()) {

						mv = new Move(MoveType.SHORTCASTLE_BLACK);
					} else {
						mv = new Move(MoveType.LONGCASTLE_BLACK);

					}
				}
			} else {
				mv = new Move(board.getSpot(from).getOccupant(), from, to, board.getSpot(to).getOccupant());

			}
		} else {
			if (board.getSpot(from).getOccupant().getID() == PieceID.PAWN) {
				if (from.getRow() != to.getRow() && from.getCol() != to.getCol()) {

					if (!(board.getSpot(to).isOccupied())) {
						System.out.println("reached");
						mv = new Move(board.getSpot(from).getOccupant(), from, to,
								board.getSpot(new Location(from.getRow(), to.getCol())).getOccupant(),
								MoveType.EN_PASSENT);
					}
				}
			}
			if (mv == null)
				mv = new Move(board.getSpot(from).getOccupant(), from, to, board.getSpot(to).getOccupant());
		}

		board.executeMove(mv);
		last = mv;
		String ret = "";
		switch (mv.getType()) {
		case SHORTCASTLE_WHITE: {
			ret = "0-0";
			break;
		}
		case SHORTCASTLE_BLACK: {
			ret = "0-0";
			break;
		}
		case LONGCASTLE_WHITE: {
			ret = "0-0-0";
			break;
		}
		case LONGCASTLE_BLACK: {
			ret = "0-0-0";
			break;
		}
		default: {
			ret = mv.getPiece().getID().stringForm() + mv.getTo();

		}
		}
		return ret;
		// }
		// return null;
	}

	public String compMove() {

		Move mv = null;
		try {
			// board.executeMove(moves.get(new
			// Random().nextInt((moves.size()==1)?moves.size():moves.size()-1)));
			mv = mnmx.evaluateBestMove(board);
			board.executeMove(mv);
			boardConfigs.add(board.getBoardConfig());
			last = mv;
			System.out.println();
			System.out.println();
			String ret = "";
			switch (mv.getType()) {
			case SHORTCASTLE_WHITE: {
				ret = "0-0";
				break;
			}
			case SHORTCASTLE_BLACK: {
				ret = "0-0";
				break;
			}
			case LONGCASTLE_WHITE: {
				ret = "0-0-0";
				break;
			}
			case LONGCASTLE_BLACK: {
				ret = "0-0-0";
				break;
			}
			default: {
				ret = mv.getPiece().getID().stringForm() + mv.getTo();

			}
			}
			return ret;
		} catch (IllegalArgumentException | NullPointerException e) {
			ended = true;
			System.out.println();
			System.out.println(board);
			e.printStackTrace();
			board.printMoveStack();

		} catch (StalemateException e) {
			ended = true;

		} finally {

		}

		return "";
	}

	public int analyze() {
		return new PositionEvaluator(color).evaluate(board);
	}

	public String getK() {
		return board.getKing(PieceColor.BLACK).getLocation().toString();
	}

	public String getAttackVal() {
		return Integer.toString(new KingSafety().kingSafeValue(board, PieceColor.WHITE));
	}

	public static Board getCurrentPosition() {
		return board;
	}

	public boolean hasEnded() {
		return ended;
	}

	public void printPossibleMoves() {
		System.out.println(board.getPossibleMoves(PieceColor.BLACK));
	}

	public void execFirstMove() {
		board.executeMove(new Move(board.getSpot(new Location(6, 4)).getOccupant(), new Location(6, 4),
				new Location(4, 4), null));
	}

	public static void nextPos(String s) {
		boardConfigs.add(s);
	}

	public static void removePrevPos() {
		if (boardConfigs.size() != 0) {
			boardConfigs.removeLast();
		}
	}

	public static int numRepeats(String s) {
		int num = 0;
		for (String sc : boardConfigs) {
			if (sc.equals(s)) {
				num++;
			}
		}
		return num;
	}

	public void debugPrint() {
		UIBoard.println(board.getBoardConfig());
		Testing.display();
	}
}
