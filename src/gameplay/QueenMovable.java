package gameplay;

import java.util.ArrayList;

public class QueenMovable implements PieceMovable {
	private static final int offsets[][] = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { 1, 0 },
			{ 0, -1 }, { 0, 1 }, };

	@Override
	public ArrayList<Move> getLegalMoves(Piece piece, Board board) {
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		int x = piece.x;
		int y = piece.y;
		Piece checkpiece;

		for (int[] direction : offsets) {
			int checkx = x + direction[0];
			int checky = y + direction[1];

			while (0 <= checkx && checkx <= 7 && 0 <= checky && checky <= 7) {
				checkpiece = board.squares[checkx][checky].getOccupyingPiece();
				if (checkpiece != null) {
					if (checkpiece.color != piece.color)
						legalMoves.add(new NormalMoving(x, y, checkx, checky));
					break;
				}
				legalMoves.add(new NormalMoving(x, y, checkx, checky));
				checkx += direction[0];
				checky += direction[1];
			}

		}

		return legalMoves;
	}
}
