package pieces;

import java.util.LinkedList;

import frameWork.Board;
import frameWork.Location;
import frameWork.Move;

interface Movable {
	LinkedList<Move> getMoveList(Board board);
	boolean isLegalMove(Board board,Location to);

}
