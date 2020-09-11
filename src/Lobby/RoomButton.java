package Lobby;

import Client.Client_server;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class RoomButton extends JButton {	
	private String text;
        private Client_server client;
	
	public RoomButton(String text, Client_server client){
            super(text);
            this.client = client;
            this.text = text;
            this.setPreferredSize(new Dimension(50, 100));
            
            EtchedBorder border = new EtchedBorder(EtchedBorder.RAISED, new Color(102, 153, 51), new Color(102, 153, 102));
            this.setBorder(border);
            this.setFont(new java.awt.Font("Verdana", 1, 15));
            
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    client.setChoosedRoom(text);
                    if(text!=null)
                        if(text.compareTo("")!=0)
                            client.setEnableJoinButton(true);
                }
            });
	}
}