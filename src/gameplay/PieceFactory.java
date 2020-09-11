package gameplay;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;

public class PieceFactory {

	private static final String imagepaths[][] = {
		{"w_king.png","w_queen.png","w_bishop.png","w_knight.png","w_rook.png","w_pawn.png"},
		{"b_king.png","b_queen.png","b_bishop.png","b_knight.png","b_rook.png","b_pawn.png"}
	};
	
	private static final PieceMovable ruleset[] = {
		new KingMovable(),
		new QueenMovable(),
		new BishopMovable(),
		new KnightMovable(),
		new RookMovable(),
		new PawnMovable()
	};
        
        public Image getPieceImage(PieceType type,PieceColor color){
		String path = new String(imagepaths[color.getvalue()][type.getvalue()]);
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("assets/"+ path));
		return icon.getImage();
	}
	
	public PieceMovable getPieceRule(PieceType type){
		return ruleset[type.getvalue()];
	}

	public Piece getPiece(PieceType type,PieceColor color) {
		Image pieceimage = getPieceImage(type, color);
		PieceMovable rule = getPieceRule(type);
		Piece createdPiece = new Piece(type, color);
		createdPiece.setRule(rule);
		createdPiece.image=pieceimage;
		return createdPiece;
	}
}
