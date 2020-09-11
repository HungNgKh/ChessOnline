/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Lobby.LobbyUI;
import Room.RoomUI;
import com.google.gson.Gson;
import gameplay.Game;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doan Phuc
 */
public class Client_server implements Runnable{
    private TransferMethod transferMethod;
    private String choosedRoom = null;
    public Client_server(Socket SOCK)
    {
        this.SOCK = SOCK;
        transferMethod = new TransferMethod(SOCK);
    }
    public void remote_roomUI(RoomUI roomUI)
    {
        this.roomUI = roomUI;
    }
    public void remote_lobby(LobbyUI Lobby)
    {
        this.Lobby = Lobby;
    }
    public void remote_SignUp_frame(SignUp_frame signUp_frame)
    {
        this.signUp_frame = signUp_frame;
    }
    public void remote_Game(Game game)
    {
        this.game = game;
    }
    public String getchoosedRoom()
    {
        return choosedRoom;
    }
    public void setChoosedRoom(String s)
    {
        choosedRoom = s;
    }
    public TransferMethod getTransferMethod()
    {
        return transferMethod;
    }
    
    public boolean isWaittingServer()
    {
        return waitting;
    }
    
    public void setNotWaitting()
    {
        waitting = false;
    }
    
    public void setWaitting()
    {
        waitting = true;
        while(isWaittingServer())System.out.print("");
    }
    public void setClientName(String s)
    {
        client_name =s;
    }
    public String getName()
    {
        return client_name;
    }
    public void update_room() throws IOException
    {
        int ii = transferMethod.RECEIVE_i();
        listRoom.clear();
        for(int i=0;i<ii;i++)
            listRoom.add(transferMethod.RECEIVE_s());
        setNotWaitting();
    }
    
    public void setEnableJoinButton(boolean active)
    {
        Lobby.setEnableJoinButton(active);
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                String REQUIRE = transferMethod.RECEIVE_s();
                if(REQUIRE== null) return;
                System.out.println(REQUIRE);
                switch(REQUIRE)
                {
                    case "LOGIN_SUCCESS":
                    case "UPDATE_ROOM":
                        update_room();
                        while(Lobby == null) System.out.print("");
                        Lobby.update_list_room();
                        break;
                    case "CREATE_SUCCESS":
                        setNotWaitting();
                        Lobby.openRoomFrame(0,true);
                        break;
                    case "JOIN_SUCCESS":
                        int temp = transferMethod.RECEIVE_i();
                        setNotWaitting();
                        Lobby.openRoomFrame(temp,false);
                        break;
                    case "SIGN_UP_SUCCESS":
                        signUp_frame.SignUpSuccess(true); 
                        setNotWaitting();
                        break;
                    case "SIGN_UP_FALSE": 
                        signUp_frame.SignUpSuccess(false);
                    case "CREATE_FALSE":
                    case "JOIN_FALSE": 
                        setNotWaitting();
                        break;
                    case "JOIN_ROOM":
                        roomUI.setEnemyInfo(transferMethod.RECEIVE_s());
                        break;
                    case "OUT_ROOM":
                        OUT_ROOM();
                        break;
                    case "CHANGE_COLOR":
                        setNotWaitting();
                        roomUI.setIcon(transferMethod.RECEIVE_i());
                        break;
                    case "READY":
                        roomUI.setStartButtonEnable(transferMethod.RECEIVE_i());
                        break;
                    case "START":
                        setNotWaitting();
                        roomUI.PlayGame();
                        break;
                    case "MOVE":
                        MOVE(transferMethod.RECEIVE_s());
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client_server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void OUT_ROOM()
    {
        roomUI.setEnemyInfo(null);
        roomUI.checkBoss(true);
    }
    private void MOVE(String s)
    {
        Gson gson = new Gson();
        gameplay.NormalMoving mo = gson.fromJson(s, gameplay.NormalMoving.class);
        mo.convertInfo();
        game.updateBoard(mo);
        game.canMove(true);
    }
    
    
    public ArrayList listRoom = new ArrayList();
    private LobbyUI Lobby;
    private SignUp_frame signUp_frame;
    private RoomUI roomUI;
    private boolean waitting = true;
    private Socket SOCK;
    private String client_name;
    private Game game;
}
