package gameplay;

import java.awt.Image;
import java.util.ArrayList;

public class Piece{

	private PieceMovable rule; // piece rule	
	private Board board;
	private ArrayList<Move> Movables = new ArrayList<Move>();
	
	public Image image;
	public PieceType type;
	public final PieceColor color;
	public boolean Available;
	public boolean hasMoved = false;
	public boolean canbeEnPassant = false;
	public int x, y; // position
	
	public Piece(PieceType type,PieceColor color) {
		super();
		this.type = type;
		this.color = color;
		this.x = -1;
		this.y = -1;
		Available = false;
	}
	

	public void setRule(PieceMovable rule) {
		this.rule = rule;
	}
	
	public ArrayList<Move> getMoves(){
		return Movables;
	}
	
	public void updateMovable(Board board){
		Movables = rule.getLegalMoves(this, board);
	}
}
