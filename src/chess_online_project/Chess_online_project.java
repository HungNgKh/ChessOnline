/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess_online_project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doan Phuc
 */
public class Chess_online_project {

    /**
     * @param args the command line arguments
     */
    static Room_Client_list room_client_list = new Room_Client_list();
    public static void main(String[] args) {
        final int port = 7777;
        try {
            ServerSocket SERVER = new ServerSocket(port);
            System.out.println("waiting client connect !");
            while(true)
            {
                Socket SOCK = SERVER.accept();
                System.out.println("connect thanh cong "+ SOCK.getPort());
                SocketID SOCK_ID = new SocketID();
                SOCK_ID.SERVER_ACCEPT_SocketID(SOCK);
                Server_client ser_cli = new Server_client(SOCK_ID, room_client_list);
                Thread x = new Thread(ser_cli);
                x.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Chess_online_project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
