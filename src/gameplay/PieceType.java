package gameplay;

public enum PieceType {
	KING(0), QUEEN(1), BISHOP(2), KNIGHT(3), ROOK(4), PAWN(5);
	
	private int value;
	
	private PieceType(int value){
		this.value = value;
	}
	
	public int getvalue(){
		return value;
	}
}
