/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess_online_project;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doan Phuc
 */
public class Server_client implements Runnable{
    private SocketID SOCK;
    private Room_Client_list room_client_list;
    
    public Server_client(SocketID SOCK, Room_Client_list room_client_list)
    {
        this.SOCK=SOCK;
        this.room_client_list = room_client_list;
    }
    
    @Override
    public void run() {
        String REQUIRE;
        while(true)
        {
            try {
                REQUIRE = SOCK.RECEIVE_s();
                if(REQUIRE == null) return;
                System.out.println(REQUIRE);
                switch(REQUIRE)
                {
                    case "LOGIN":
                        LOGIN();
                        break;
                    case "CREATE_ROOM":
                        if(Create_room()==true)
                            req_update_all(0);
                        else req_update_all(1);
                        break;
                    case "JOIN_ROOM":
                        Join_in_room(SOCK.RECEIVE_s());
                        break;
                    case "OUT_ROOM":
                        OUT_ROOM(true);
                        break;
                    case "SIGN_UP":
                        SIGN_UP();
                        break;
                    case "CHANGE_COLOR":
                        CHANGE_COLOR();
                        break;
                    case "READY":
                        READY(SOCK.RECEIVE_s());
                        break;
                    case "START":
                        room_client_list.getRoom(SOCK).SEND_both("START");
                        break;
                    case "MOVE":
                        MOVE(SOCK.RECEIVE_s());
                    default:break;
                }
            }
            catch (IOException ex) {
                OUT_ROOM(false);
                System.out.println("disconnect");
//                Logger.getLogger(Server_client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void SIGN_UP() throws IOException
    {
        String s1 = SOCK.RECEIVE_s();String s2 = SOCK.RECEIVE_s();String s3 = SOCK.RECEIVE_s();
        if(new DatabaseMethod().SignUp(s1, s3, s2)==true) SOCK.SEND("SIGN_UP_SUCCESS");
        else SOCK.SEND("SIGN_UP_FALSE");
    }
    
    private void Join_in_room(String key)
    {
        RoomManager rm = room_client_list.getRoom(key);
        if(rm==null) return ;
        if(rm.joinGame(SOCK)==true)
        {
            room_client_list.ClientToRoom(SOCK.getName(), rm);
            SOCK.SEND("JOIN_SUCCESS");
            SOCK.SEND((rm.getColorPlayer1()+1)%2);
            rm.getEnemy(SOCK).SEND("JOIN_ROOM");
            rm.getEnemy(SOCK).SEND(SOCK.getName());
        }
        else SOCK.SEND("JOIN_FALSE");
    }
    
    private boolean Create_room()
    {
        if(room_client_list.getClient(SOCK.getName())==null) return false;
        RoomManager room = new RoomManager(SOCK);
        room_client_list.ClientToRoom(SOCK.getName(),room);
        return true;
    }
    
    private void req_update(SocketID SOCK)
    {
        String[] hostnames = room_client_list.getAllRoomName();
        Set r_keys = room_client_list.getRoom_list().keySet();
        SOCK.SEND(hostnames.length);
        for (String hostname : hostnames)
            SOCK.SEND(hostname);
    }
    // 0 khi tao phong thanh cong, 1 khi tao phong khong thanh cong, 2 khi out khoi vao
    private void req_update_all(int require) 
    {
        if(require == 0 || require == 2)
        {
            Set keys = room_client_list.getClient_list().keySet();
            for(Iterator i = keys.iterator(); i.hasNext();)
            {
                String key = (String) i.next();
                SocketID soc_tem = (SocketID) room_client_list.getClient_list().get(key);
                soc_tem.SEND("UPDATE_ROOM");
                req_update(soc_tem);
            }
            if(require == 0)
                SOCK.SEND("CREATE_SUCCESS");
        }
        if(require == 1)
            SOCK.SEND("CREATE_FALSE");
    }
    private void LOGIN() throws IOException///////////////////////////
    {
        String user_name = SOCK.RECEIVE_s();
        //check database ok -> cho login
        SOCK.setName(user_name);
        room_client_list.addClient_list(user_name, SOCK);
        SOCK.SEND("LOGIN_SUCCESS");
        req_update(SOCK);
    }
    
    private void OUT_ROOM(boolean boo) // true if isConnecting, false if not
    {
        RoomManager rm = room_client_list.getRoom(SOCK);
        int mems = rm.outRoom(SOCK);
        room_client_list.RoomToClient(SOCK,boo);
        if(mems == 1)
            rm.getBoss().SEND("OUT_ROOM");
        req_update_all(2);
    }
    
    private void CHANGE_COLOR()
    {
        RoomManager rm = room_client_list.getRoom(SOCK);
        if(rm == null) return;
        rm.setColorforPlayer1((rm.getColorPlayer1()+1)%2);
        rm.SEND_both("CHANGE_COLOR");
        rm.SEND_both_color();
    }
    private void READY(String s)
    {
        int i = Integer.parseInt(s);
        RoomManager rm = room_client_list.getRoom(SOCK);
        if(rm == null) System.out.println("null");
        rm.getEnemy(SOCK).SEND("READY");
        rm.getEnemy(SOCK).SEND(i);
    }
    private void MOVE(String s)
    {
        RoomManager rm = room_client_list.getRoom(SOCK);
        if(rm == null) return;
        rm.getEnemy(SOCK).SEND("MOVE");
        rm.getEnemy(SOCK).SEND(s);
    }
}
