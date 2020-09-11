package Lobby;

import Client.Client_server;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class RoomListPanel extends JScrollPane{
	private static final int width = 300;
	private static final int height = 500;
	private Client_server client;
	private GridBagConstraints gbc = new GridBagConstraints();
	private JPanel contain_panel = new JPanel();
	public ArrayList<RoomButton> room_list = new ArrayList<>();
	
	
	
	public RoomListPanel(Client_server client){
            super(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
            this.client = client;
            this.setViewportView(contain_panel);
            this.setPreferredSize(new Dimension(width, height));

            TitledBorder border = new TitledBorder(new LineBorder(new Color(153, 153, 0), 3, true), "ROOMS", TitledBorder.CENTER, TitledBorder.TOP, null, null);
            border.setTitleFont(new Font("Lucida Bright", Font.BOLD | Font.ITALIC, 20));
            border.setTitleColor(new Color(0,153,102));
            this.setBorder(border);
//            contain_panel.setBackground(new Color(255,255,204));

            GridBagLayout layout = new GridBagLayout();
            layout.rowHeights = new int[]{0, 0, 0, 0, 0};
            layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
            contain_panel.setLayout(layout);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.weightx = 0.1;
	}
	
	public void addRoom(RoomButton roombutton){
            room_list.add(roombutton);
            contain_panel.add(roombutton,gbc);
            gbc.gridy++;
            this.revalidate();
            this.repaint();
	}
	
	public void removeRoom(RoomButton roombutton){
            if(room_list.size()>0){
                room_list.remove(roombutton);
                contain_panel.remove(roombutton);
                //gbc.gridy--;
                this.revalidate();
                this.repaint();
            }	
	}
        
        public void removeAllRoom()
        {
            room_list.clear();
            contain_panel.removeAll();
            this.revalidate();
            this.repaint();
        }
        
        public void addRooms(ArrayList<String> listRoom)
        {
            for(String roomname : listRoom)
            {
                RoomButton rb = new RoomButton(roomname,client);
                room_list.add(rb);
                contain_panel.add(rb,gbc);
                gbc.gridy++;
            }
            this.revalidate();
            this.repaint();
        }
}
