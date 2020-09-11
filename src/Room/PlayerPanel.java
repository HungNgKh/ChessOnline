package Room;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class PlayerPanel extends JPanel {
	private static final int width = 200;
	private static final int height = 350;
	
	private JLabel PlayerName = new JLabel();
	private JLabel Avatar = new JLabel();
	public JLabel Archievement = new JLabel("Archivement");
	
	public PlayerPanel(int x,int y){
            super();
            this.setLayout(null);
            this.setBounds(x,y,width, height);
            initComponents();
	}
	
	private void initComponents(){
            PlayerName.setBounds(0,0,width,50);
            PlayerName.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(102, 102, 102), new Color(102, 102, 153)));
            PlayerName.setHorizontalAlignment(SwingConstants.CENTER);
            Avatar.setBounds(0, 50, width,200);
            Avatar.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(102, 51, 0), new Color(51, 51, 0)));
            Archievement.setBounds(0,250, width, 100);
            Archievement.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 153, 51), new Color(0, 153, 102)));
            Avatar.setHorizontalAlignment(SwingConstants.CENTER);

            this.add(PlayerName);
            this.add(Avatar);
            this.add(Archievement);
	}
        
        public void setIcon(int i)
        {
            AutoResizeIcon.setIcon(Avatar, i);
        }
        
        public void setName(String Name)
        {
            if(Name == null)
            PlayerName.setText("");
            PlayerName.setText(Name);
        }
}
