package gameplay;

import java.util.ArrayList;

public class PawnMovable implements PieceMovable {
	
	private boolean canEnPassant(Piece piece,int x,int y,Board board){
		
		return false;
	}
	
	@Override
	public ArrayList<Move> getLegalMoves(Piece piece, Board board){
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		int x = piece.x;
		int y = piece.y;
		int offset = piece.color == board.playercolor?1:-1;
		Piece check;
		
		Square[][] squares = board.squares;
		if(x-offset>=0 && x-offset<=7){
			if(squares[x-offset][y].getOccupyingPiece()==null){
				legalMoves.add(new NormalMoving(x, y, x-offset, y));
				if(!piece.hasMoved && x-(offset<<1)>=0 && x-(offset<<1)<=7 && squares[x-(offset<<1)][y].getOccupyingPiece()==null){
					legalMoves.add(new NormalMoving(x, y, x-(offset<<1), y));
				}
			}
			if(y-1>=0){
				check = squares[x-offset][y-1].getOccupyingPiece();
				if(check!=null && check.color != piece.color)
					legalMoves.add(new NormalMoving(x, y, x-offset, y-1));
			}
			if(y+1<=7){
				check = squares[x-offset][y+1].getOccupyingPiece();
				if(check!=null && check.color != piece.color)
					legalMoves.add(new NormalMoving(x, y, x-offset, y+1));
			}
		}
		
		
		return legalMoves;
	}
}
