/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess_online_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doan Phuc
 */
public class SocketID{
    private BufferedWriter os ;
    private BufferedReader is;
    private Socket SOCK =null;
    private String ID_name = null;
    public void SERVER_ACCEPT_SocketID(Socket SOCK)
    {
        try {
            this.SOCK = SOCK;
            os = new BufferedWriter(new OutputStreamWriter(SOCK.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(SOCK.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(SocketID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Socket getSOCK()
    {
        return SOCK;
    }
    public String getName()
    {
        return ID_name;
    }
    public void setName(String s)
    {
        this.ID_name = s;
    }
    
    public void SEND(String s)
    {
        try {
            os.write(s);
            os.newLine();
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(SocketID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void SEND(int i)
    {
        try {
            os.write(i);
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(SocketID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String RECEIVE_s() throws IOException
    {
        return is.readLine();
    }
    public int RECEIVE_i() throws IOException
    {
        return is.read();
    }
    
    public void closePort()
    {
        try {
            SOCK.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

