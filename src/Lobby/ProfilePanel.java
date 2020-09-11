package Lobby;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ProfilePanel extends JPanel{
	private static final int width = 200;
	private static final int height = 500;
	
	public ProfilePanel(){
		super();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(width, height));
                
		
		TitledBorder border = new TitledBorder(new LineBorder(new Color(0, 51, 51), 3, true), "INFORMATION", TitledBorder.CENTER, TitledBorder.TOP, null, null);
		border.setTitleFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 20));
		border.setTitleColor(new Color(204,150,0));
		this.setBorder(border);
		//this.setBackground(new Color(250, 250, 250));
	}
}
