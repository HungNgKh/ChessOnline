/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Lobby.LobbyUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Doan Phuc
 */
public class Login_frame extends JFrame{
    private Client_server client;
    public Login_frame(Client_server client)
    {
        this.client = client;
        init();
    }
    private void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null); setResizable(false);
        setSize(300, 500); setLocationRelativeTo(null);
        
        JLabel lab1 = new JLabel("CHESS ONLINE"); lab1.setBounds(0, 0, 300, 80); 
        lab1.setBackground(new Color(0, 191, 255));lab1.setOpaque(true);
        lab1.setForeground(new Color(238, 238, 238));lab1.setFont(new java.awt.Font("Sylfaen", 1, 35));
        lab1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);add(lab1);
        
        JLabel lab2 = new JLabel("LOGIN"); lab2.setBounds(0, 80, 300, 60);
        lab2.setForeground(new Color(0, 191, 255));lab2.setFont(new java.awt.Font("Aria", 1, 25));
        lab2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);add(lab2);
        
        JLabel lab3 = new JLabel("User Name"); lab3.setBounds(40, 140, 70, 12);
        lab3.setFont(new java.awt.Font("Aria", 0, 12));add(lab3);
        
        UserName.setBounds(40, 160, 220, 40);UserName.setFont(new java.awt.Font("Verdana", 0, 20));add(UserName);
        
        JLabel lab4 = new JLabel("PassWord"); lab4.setBounds(40, 220, 70, 12);
        lab4.setFont(new java.awt.Font("Aria", 0, 12));add(lab4);
        
        Password.setBounds(40, 240, 220, 40);Password.setFont(new java.awt.Font("Verdana", 0, 20));add(Password);
        
        Forget.setBounds(150, 285, 100, 15);add(Forget);Forget.setForeground(Color.blue);
        login.setUI(new StyledButtonUI());SignUp.setUI(new StyledButtonUI());
        login.setBounds(40, 315, 220, 50);login.setBackground(new java.awt.Color(0x5858FA)); add(login);
        SignUp.setBounds(40, 385, 220, 50);SignUp.setBackground(new java.awt.Color(0x5858FA)); add(SignUp);
        login.setForeground(new Color(238, 238, 238));SignUp.setForeground(new Color(238, 238, 238));
        login.setFont(new java.awt.Font("Verdana", 1, 15));SignUp.setFont(new java.awt.Font("Verdana", 1, 15));
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LoginButtonAction();
            }
        });
        
        SignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SignUpAction();
            }
        });
    }
    
    private void LoginButtonAction()
    {
        client.getTransferMethod().SEND("LOGIN");
        client.getTransferMethod().SEND(UserName.getText());
        client.setWaitting();
        client.setClientName(UserName.getText());
        new LobbyUI(client).setVisible(true);
        this.dispose();
    }   
    
    private void SignUpAction()
    {
        new SignUp_frame(client).setVisible(true);
        this.dispose();
    }
    private JPasswordField Password = new JPasswordField(); 
    private JTextField UserName = new JTextField();
    private JButton login = new JButton("Sign In");
    private JButton SignUp = new JButton("Sign Up");
    private JLabel Forget = new JLabel("Forgot password");
}
