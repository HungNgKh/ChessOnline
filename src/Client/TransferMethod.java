/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doan Phuc
 */
public class TransferMethod {
    private Socket SOCK;
    private BufferedWriter os ;
    private BufferedReader is;
    public TransferMethod(Socket SOCK)
    {
        try {
            this.SOCK=SOCK;
            os = new BufferedWriter(new OutputStreamWriter(SOCK.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(SOCK.getInputStream()));            
        } catch (IOException ex) {
            Logger.getLogger(TransferMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void SEND(String s)
    {
        try {
            os.write(s);
            os.newLine();
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(TransferMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void SEND(int i)
    {
        try {
            os.write(i);
            os.flush();
        } catch (IOException ex) {
            Logger.getLogger(TransferMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void SEND(Object o)
    {
        Gson gson = new Gson();
        String json = gson.toJson(o);
        SEND(json);
    }
    
    public String RECEIVE_s() throws IOException
    {
        return is.readLine();
    }
    public int RECEIVE_i() throws IOException
    {
        return is.read();
    }
}
