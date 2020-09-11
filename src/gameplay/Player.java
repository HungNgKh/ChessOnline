package gameplay;

import java.util.ArrayList;
import java.util.Set;

public class Player {
	private PieceColor color;
	
	public ArrayList<Piece> Available_Pieces;
	public ArrayList<Piece> PiecesCaptureFromEnemy;
	
	public Player(PieceColor color) {
		this.color = color;
	}
	
	public PieceColor getColor(){
		return color;
	}
}
