package Room;

import Client.Client_server;
import chess_online_project.RoomManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class RoomUI extends JFrame {
    private Client_server client;
    private JPanel contentPane;
    public PlayerPanel playerPanel1;
    public PlayerPanel playerPanel2;
    private JButton Exchangebutton = new JButton("CHANGE COLOR");
    private JButton Start_button = new JButton("START GAME");
    private JButton Exit_button = new JButton("Exit");
    private JToggleButton Ready_button = new JToggleButton("READY");

	/**
	 * Launch the application.
	 */
    private RoomManager roomManager;
    private int color;
    private boolean isBoss = true;
	/**
	 * Create the frame.
	 */
    public RoomUI(Client_server client,int color,boolean isBoss) {
        this.client = client;
        this.color = color;
        init();
        this.client.remote_roomUI(this);
        setInfoPlayer(playerPanel1, client.getName());
        setInfoPlayer(playerPanel2, client.getchoosedRoom());
        this.isBoss = isBoss;
        checkBoss(this.isBoss);
    }
    private void init()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 615, 550);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        playerPanel1 = new PlayerPanel(10, 10);
        contentPane.add(playerPanel1);
        playerPanel2 = new PlayerPanel(400, 10);
        contentPane.add(playerPanel2);
        
        Exchangebutton.setBounds(230, 135, 150, 50);
        contentPane.add(Exchangebutton);

        Start_button.setBounds(230, 210, 150, 150);
        contentPane.add(Start_button);
        Ready_button.setBounds(230, 210, 150, 150);
        contentPane.add(Ready_button);

        Exit_button.setBounds(255, 400, 100, 100);
        contentPane.add(Exit_button);
        this.setLocationRelativeTo(null);
        Exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ExitAction();
            }
        });
        Exchangebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ChangeColor();
            }
        });
        Start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Start_action();
            }
        });
        Ready_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Ready_action();
            }
        }
        );
        setIcon(this.color);
    }
    private void ExitAction()
    {
        client.getTransferMethod().SEND("OUT_ROOM");
        client.setWaitting();
        new Lobby.LobbyUI(client).setVisible(true);
        client.remote_roomUI(null);
        this.dispose();
    }
    private void setInfoPlayer(PlayerPanel player,String name)//////
    {
        if(name == null) player.setName("");
        else player.setName(name);
    }
    public void setEnemyInfo(String name)///////
    {
        setInfoPlayer(playerPanel2, name);
    }
    public void checkBoss(boolean isBoss)
    {
        if(isBoss == true)
        {
            Ready_button.setVisible(false);
            Start_button.setVisible(true);
            Start_button.setEnabled(false);
            Exchangebutton.setVisible(true);
            Exit_button.setEnabled(true);
        }
        else
        {
            Exchangebutton.setVisible(false);
            Start_button.setVisible(false);
            Ready_button.setVisible(true);
        }
    }
    public void ChangeColor()
    {
        client.getTransferMethod().SEND("CHANGE_COLOR");
        client.setWaitting();
    }
    public void setIcon(int color)
    {
        this.color = color;
        playerPanel1.setIcon(color);
        playerPanel2.setIcon((color+1)%2);
    }
    private void Ready_action()
    {
        if(Ready_button.isSelected())
        {
            client.getTransferMethod().SEND("READY");
            client.getTransferMethod().SEND("1");
            Exit_button.setEnabled(false);
        }
        else
        {
            client.getTransferMethod().SEND("READY");
            client.getTransferMethod().SEND("0");
            Exit_button.setEnabled(true);
        }
    }
    private void Start_action()
    {
        client.getTransferMethod().SEND("START");
        client.setWaitting();
    }
    public void setStartButtonEnable(int i)
    {
        if(i==0) Start_button.setEnabled(false); 
        if(i==1) Start_button.setEnabled(true);   
    }
    public void PlayGame()
    {
        new gameplay.Game(client,color,client.getName()).setVisible(true);
        client.remote_roomUI(null);
        this.dispose();
    }
}
