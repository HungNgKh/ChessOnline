package gameplay;

import java.util.ArrayList;

public class KnightMovable implements PieceMovable {

	private static final int offsets[][] = { { 2, 1 }, { 2, -1 }, { -2, 1 }, { -2, -1 }, { 1, 2 }, { 1, -2 }, { -1, 2 },
			{ -1, -2 } };

	@Override
	public ArrayList<Move> getLegalMoves(Piece piece, Board board) {
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		int x = piece.x;
		int y = piece.y;

		for (int[] offset : offsets) {
			int i = offset[0];
			int j = offset[1];
			if (0 <= x + i && x + i <= 7 && 0 <= y + j && y + j <= 7) {
				Piece despiece = board.squares[x + i][y + j].getOccupyingPiece();
				if (despiece == null || despiece.color != piece.color)
					legalMoves.add(new NormalMoving(x, y, x+i, y+j));
			}
		}

		return legalMoves;
	}
}
