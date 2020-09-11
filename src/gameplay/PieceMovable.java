package gameplay;

import java.util.ArrayList;

public interface PieceMovable {
	ArrayList<Move> getLegalMoves(Piece piece, Board board);
}
