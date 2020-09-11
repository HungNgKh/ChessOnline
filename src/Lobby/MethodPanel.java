package Lobby;

import Client.Client_server;
import Client.StyledButtonUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
//import Client.;

public class MethodPanel extends JPanel implements ActionListener{
    private static final int width = 200;
    private static final int height = 500;
    private Client_server client;
    public JButton create_button = new JButton("CREATE ROOM");
    public JButton join_button = new JButton("JOIN");
    public JButton back_button = new JButton("BACK");

    public MethodPanel(Client_server client){
        super();
        this.client= client;
        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(width, height));
        create_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        join_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        back_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        create_button.setUI(new StyledButtonUI());create_button.setBackground(new java.awt.Color(0x525395));
        back_button.setUI(new StyledButtonUI());back_button.setBackground(new java.awt.Color(0x525395));
        join_button.setUI(new StyledButtonUI());join_button.setBackground(new java.awt.Color(0x525395));
        create_button.setForeground(new Color(238, 238, 238));create_button.setFont(new java.awt.Font("Verdana", 1, 15));
        join_button.setForeground(new Color(238, 238, 238));join_button.setFont(new java.awt.Font("Verdana", 1, 15));
        back_button.setForeground(new Color(238, 238, 238));back_button.setFont(new java.awt.Font("Verdana", 1, 15));

        this.add(Box.createVerticalStrut(100));
        this.add(create_button);
        this.add(Box.createVerticalStrut(100));
        this.add(join_button);
        this.add(Box.createVerticalStrut(100));
        this.add(back_button);
        this.add(Box.createVerticalStrut(100));

        join_button.setEnabled(false);

        create_button.addActionListener(this);
        join_button.addActionListener(this);
    }
    
    private void Create_Room()
    {
        client.getTransferMethod().SEND("CREATE_ROOM");
        client.setWaitting();
    }
    
    private void Join_Room(String room)
    {
        if(client.getchoosedRoom()!=null)
            if(client.getchoosedRoom().compareTo("")!=0)
            {
                client.getTransferMethod().SEND("JOIN_ROOM");
                client.getTransferMethod().SEND(room);
                client.setWaitting();
            }
    }
    
    public void setJoinButton(boolean isActive)
    {
        join_button.setEnabled(isActive);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object ob = ae.getSource();
        if(ob == create_button)
            Create_Room();
        if(ob == join_button)
            Join_Room(client.getchoosedRoom());
    }
	
}
