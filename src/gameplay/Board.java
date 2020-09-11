package gameplay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/*
 * Author : Nguyen Khanh Hung
 * Description :
 * - This is a part of audio player that adjust volume and toggle mute on/off
 */
public class Board extends JPanel {
	
	public static MouseListener moverable = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			Object source = arg0.getSource();
			if(source instanceof Square)
			((Square)source).setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(51, 102, 102)), new LineBorder(new Color(255, 204, 0), 4)));
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			Object source = arg0.getSource();
			if(source instanceof Square)
			((Square)source).setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(51, 102, 102)), new LineBorder(new Color(0, 51, 51), 4)));
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};

	private ArrayList<Piece> Availablepieces = new ArrayList<Piece>();
	
	public Square squares[][];
	public PieceColor playercolor;

	public Board(int x, int y) {
		this.setLayout(new GridLayout(8, 8));
		this.setMaximumSize(new Dimension(640, 640));
		this.setBounds(x, y, 640, 640);
		initComponent();
	}

	private void initComponent() {
		squares = new Square[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0)
					squares[i][j] = new Square(new Color(230, 230, 250), i, j);
				else
					squares[i][j] = new Square(new Color(51, 153, 102), i, j);
				squares[i][j].setFocusPainted(false);
				squares[i][j].removeActionListener(null);
				//squares[i][j].removeMouseListener(squares[i][j].getMouseListeners()[0]);
				this.add(squares[i][j]);
			}
		}
	}

	public void addPiece(Piece piece, int x, int y) {
		squares[x][y].setOcupyingPiece(piece);
		piece.x = x;
		piece.y = y;
		piece.Available = true;
		piece.hasMoved = false;
		Availablepieces.add(piece);
		piece.canbeEnPassant = false;
	}
	
	public void removePiece(Piece piece){
		if(Availablepieces.contains(piece)){
			Availablepieces.remove(piece);
			int x = piece.x;
			int y = piece.y;
			squares[x][y].unsetOcupyingPiece();
			piece.Available = false;
		}	
	}

	// initialize all pieces to start game
	public void LoadPieces(PieceColor color) {
		PieceFactory factory = new PieceFactory();
		PieceColor mycolor = color;
		PieceColor opcolor = mycolor == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;
		PieceType queen_kingleft = mycolor == PieceColor.WHITE ? PieceType.QUEEN : PieceType.KING;
		PieceType queen_kingright = queen_kingleft == PieceType.KING ? PieceType.QUEEN : PieceType.KING;

		// Opponent side
		addPiece(factory.getPiece(PieceType.PAWN, opcolor), 1, 0);
		addPiece(factory.getPiece(PieceType.PAWN, opcolor), 1, 1);
		addPiece(factory.getPiece(PieceType.PAWN, opcolor), 1, 2);
		addPiece(factory.getPiece(PieceType.PAWN, opcolor), 1, 3);
		addPiece(factory.getPiece(PieceType.PAWN, opcolor), 1, 4);
		addPiece(factory.getPiece(PieceType.PAWN, opcolor), 1, 5);
		addPiece(factory.getPiece(PieceType.PAWN, opcolor), 1, 6);
		addPiece(factory.getPiece(PieceType.PAWN, opcolor), 1, 7);
		addPiece(factory.getPiece(PieceType.KNIGHT, opcolor), 0, 1);
		addPiece(factory.getPiece(PieceType.KNIGHT, opcolor), 0, 6);
		addPiece(factory.getPiece(PieceType.ROOK, opcolor), 0, 0);
		addPiece(factory.getPiece(PieceType.ROOK, opcolor), 0, 7);
		addPiece(factory.getPiece(PieceType.BISHOP, opcolor), 0, 2);
		addPiece(factory.getPiece(PieceType.BISHOP, opcolor), 0, 5);
		addPiece(factory.getPiece(queen_kingleft, opcolor), 0, 3);
		addPiece(factory.getPiece(queen_kingright, opcolor), 0, 4);

		// My side
		addPiece(factory.getPiece(PieceType.PAWN, mycolor), 6, 0);
		addPiece(factory.getPiece(PieceType.PAWN, mycolor), 6, 1);
		addPiece(factory.getPiece(PieceType.PAWN, mycolor), 6, 2);
		addPiece(factory.getPiece(PieceType.PAWN, mycolor), 6, 3);
		addPiece(factory.getPiece(PieceType.PAWN, mycolor), 6, 4);
		addPiece(factory.getPiece(PieceType.PAWN, mycolor), 6, 5);
		addPiece(factory.getPiece(PieceType.PAWN, mycolor), 6, 6);
		addPiece(factory.getPiece(PieceType.PAWN, mycolor), 6, 7);
		addPiece(factory.getPiece(PieceType.KNIGHT, mycolor), 7, 1);
		addPiece(factory.getPiece(PieceType.KNIGHT, mycolor), 7, 6);
		addPiece(factory.getPiece(PieceType.ROOK, mycolor), 7, 0);
		addPiece(factory.getPiece(PieceType.ROOK, mycolor), 7, 7);
		addPiece(factory.getPiece(PieceType.BISHOP, mycolor), 7, 2);
		addPiece(factory.getPiece(PieceType.BISHOP, mycolor), 7, 5);
		addPiece(factory.getPiece(queen_kingleft, mycolor), 7, 3);
		addPiece(factory.getPiece(queen_kingright, mycolor), 7, 4);
		
		playercolor = mycolor;
		update();

	}
	
	public void update(){
		for(Piece p : Availablepieces){
			p.updateMovable(this);
		}
	}
}
