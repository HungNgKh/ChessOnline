package gameplay;

import java.util.ArrayList;

public class KingMovable implements PieceMovable {
	private static final int offsets[][] ={
			{0,1},
			{0,-1},
			{1,0},
			{-1,0},
			{1,1},
			{1,-1},
			{-1,1},
			{-1,-1},
	};
	
	private boolean canCastling(Piece piece,int x,int y,Board board){
		return false;
	}
	
	@Override
	public ArrayList<Move> getLegalMoves(Piece piece, Board board){
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		int x = piece.x;
		int y = piece.y;
		Piece checkpiece;

		for (int[] offset : offsets) {
			int i = offset[0];
			int j = offset[1];
			if (0 <= x + i && x + i <= 7 && 0 <= y + j && y + j <= 7) {
				checkpiece = board.squares[x + i][y + j].getOccupyingPiece();
				if (checkpiece == null || checkpiece.color != piece.color)
					legalMoves.add(new NormalMoving(x, y, x+i, y+j));
			}
		}

		return legalMoves;
	}
}
