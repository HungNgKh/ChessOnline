package gameplay;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Square extends JButton {
	
	private static final int FIXED_WIDTH = 60;
	private static final int FIXED_HEIGHT = 60;
	
	public final int x,y;
	public Move availableMove;
	private Piece occupyingPiece;
	
	
	public Square(Color color,int x,int y) {
		super();
		occupyingPiece = null;
		this.setSize(FIXED_WIDTH, FIXED_HEIGHT);
		this.setBackground(color);
		this.x = x;
		this.y = y;
	}
	
	public Piece getOccupyingPiece(){
		return occupyingPiece;
	}
	
	private void setImage(Image image){
		int iwidth = image.getWidth(null);
		int iheight = image.getHeight(null);
		int fit_height = (FIXED_HEIGHT * 9)/10;
		int fit_width = (fit_height * iwidth)/iheight;
		Image fit = image.getScaledInstance(fit_width, fit_height, Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(fit));
	}
	
	public void setOcupyingPiece(Piece piece){
		this.occupyingPiece = piece;
		this.setImage(piece.image);
		this.setToolTipText(piece.color.toString()+' '+piece.type.toString());
	}
	
	public void unsetOcupyingPiece(){
		this.occupyingPiece = null;
		this.setIcon(null);
		this.removeActionListener(null);
		this.setToolTipText(null);
	}
	
}
