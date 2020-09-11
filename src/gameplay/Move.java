package gameplay;

public abstract class Move {
	protected int desx;
	protected int desy;
	protected int currentx;
	protected int currenty;
	protected PieceType promotion;
	
	public Move(int currentx,int currenty,int desx,int desy){
		this.currentx = currentx;
		this.currenty = currenty;
		this.desx = desx;
		this.desy = desy;
	}
	
	public void setpromote(PieceType type){
		this.promotion = type;
	}
        public void convertInfo()
        {
            desx = 7-desx;
            desy = 7-desy;
            currentx = 7 - currentx;
            currenty = 7 - currenty;
        }
	
	abstract void execute(Board board);
	abstract void undo(Board board);
}
