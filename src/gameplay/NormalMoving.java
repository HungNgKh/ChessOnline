package gameplay;

public class NormalMoving extends Move {

	public NormalMoving(int currentx,int currenty,int desx,int desy) {
		super(currentx, currenty, desx, desy);
	}
	
	@Override
	public void execute(Board board) {
		Piece commandpiece = board.squares[currentx][currenty].getOccupyingPiece();
		Piece attackedpiece = board.squares[desx][desy].getOccupyingPiece();
		
		if(promotion!=null){
			PieceFactory factory = new PieceFactory();
			commandpiece.image = factory.getPieceImage(promotion, commandpiece.color);
			commandpiece.setRule(factory.getPieceRule(promotion));
			commandpiece.type = promotion;
		}
		
		if(commandpiece!=null){
			if(attackedpiece!=null)
				board.removePiece(attackedpiece);
			board.squares[currentx][currenty].unsetOcupyingPiece();
			board.squares[desx][desy].setOcupyingPiece(commandpiece);
			if(commandpiece.type == PieceType.PAWN && !commandpiece.hasMoved)
				commandpiece.canbeEnPassant = true;
			else
				commandpiece.canbeEnPassant = false;
			commandpiece.hasMoved = true;
			commandpiece.x = desx;
			commandpiece.y = desy;
		}
		
		
		
		board.update();
	}

	@Override
	public void undo(Board board) {
		// TODO Auto-generated method stub

	}

}
