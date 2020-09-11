package gameplay;

public enum PieceColor {
	WHITE(0) ,BLACK(1);
	
	private int value;
	
	private PieceColor(int value) {
		this.value = value;
	}
	
	public int getvalue(){
		return value;
	}
}
