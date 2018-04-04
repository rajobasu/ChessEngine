package evaluation.valueBoards;

import frameWork.Location;

class ForBlack {
	private int pawnBoard[] = { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,

			50, 50, 50, 50, 50, 50, 50, 50, 10, 10, 20, 30, 30, 20, 10, 10, 5, 5, 10, 25, 25, 10, 5, 5, 0, 0, 0, 30, 30,
			0, 0, 0, 5, -5, -10, 20, 20, 5, -5, 5, 5, 10, 10, -35, -35, 10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0 };
	private int rookBoard[] = { 0, 0, 0, 0, 0, 0, 0, 0, 5, 10, 10, 10, 10, 10, 10, 5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0,
			0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, 5, 5,
			5, 5, 0, 0 };
	private int knightBoard[] = { -50, -40, -30, -30, -30, -30, -40, -50, -40, -20, 0, 0, 0, 0, -20, -40, -30, 0, 10,
			15, 15, 10, 0, -30, -30, 5, 15, 20, 20, 15, 5, -30, -30, 0, 15, 20, 20, 15, 0, -30, -30, 5, 15, 15, 15, 15,
			5, -30, -40, -20, 0, 5, 5, 0, -20, -40, -50, -50, -30, -30, -30, -30, -50, -50 };
	private int bishopBoard[] = { -20, -10, -10, -10, -10, -10, -10, -20, -10, 0, 0, 0, 0, 0, 0, -10, -10, 0, 5, 10, 10,
			5, 0, -10, -10, 5, 5, 10, 10, 5, 5, -10, -10, 0, 14, 10, 10, 14, 0, -10, -10, 10, 10, 5, 5, 10, 10, -10,
			-10, 5, 0, 0, 0, 0, 5, -10, -20, -40, -50, -40, -40, -50, -40, -20 };

	private int queenBoard[] = { -20, -10, -10, -5, -5, -10, -10, -20, -10, 0, 0, 0, 0, 0, 0, -10, -10, 0, 5, 5, 5, 5,
			0, -10, -5, 0, 5, 5, 5, 5, 0, -5, 0, 0, 5, 5, 5, 5, 0, -5, -10, 5, -5, 5, 5, -5, 0, -10, -10, 0, 5, 0, 0, 0,
			0, -10, -20, -10, -10, -5, -5, -10, -10, -20 };
	private int kingMidBoard[][] = { { -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 }, { -30, -40, -40, -50, -50, -40, -40, -30 },
			{ -30, -40, -40, -50, -50, -40, -40, -30 }, { -40, -40, -40, -40, -40, -30, -30, -20 },
			{ -10, -20, -20, -20, -20, -20, -20, -10 }, { 20, 20, 0, 0, 0, 0, 20, 20 },
			{ 20, 30, 10, 0, 0, 10, 30, 20 } };
	private int kingEndBoard[][] = { { -50, -40, -30, -20, -20, -30, -40, -50 }, { -30, -20, -10, 0, 0, -10, -20, -30 },
			{ -30, -10, 20, 30, 30, 20, -10, -30 }, { -30, -10, 30, 40, 40, 30, -10, -30 },
			{ -30, -10, 30, 40, 40, 30, -10, -30 }, { -30, -10, 20, 30, 30, 20, -10, -30 },
			{ -30, -30, 0, 0, 0, 0, -30, -30 }, { -50, -30, -30, -30, -30, -30, -30, -50 } };

	protected int getValForPawn(Location l) {
		return pawnBoard[(7 - l.getRow()) * 8 + l.getCol()] + 1;
	}

	protected int getValForKnight(Location l) {
		return knightBoard[(7 - l.getRow()) * 8 + l.getCol()] / 3 + 1;
	}

	protected int getValForRook(Location l) {
		return rookBoard[(7 - l.getRow()) * 8 + l.getCol()] / 3 + 1;
	}

	protected int getValForQueen(Location l) {
		return queenBoard[(7 - l.getRow()) * 8 + l.getCol()] / 3 + 1;
	}

	protected int getValForBishop(Location l) {
		return bishopBoard[(7 - l.getRow()) * 8 + l.getCol()] / 3 + 1;
	}

	protected int getValForKing(Location l, boolean endgame) {
		if (!endgame)
			return kingMidBoard[7 - l.getRow()][l.getCol()];

		return kingEndBoard[7 - l.getRow()][l.getCol()];
	}

}
