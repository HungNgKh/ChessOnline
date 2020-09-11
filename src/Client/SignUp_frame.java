/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Doan Phuc
 */
public class SignUp_frame extends JFrame{
    private Client_server client;
    private boolean Success = false;
    public SignUp_frame(Client_server client)
    {
        this.client = client;
        this.client.remote_SignUp_frame(this);
        init();
    }
    public void SignUpSuccess(boolean s)
    {
        Success = s;
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
        
        JLabel lab3 = new JLabel("User Name"); lab3.setBounds(40, 95, 70, 12);
        lab3.setFont(new java.awt.Font("Aria", 0, 12));add(lab3);
        UserName.setBounds(40, 115, 220, 35);UserName.setFont(new java.awt.Font("Verdana", 0, 20));add(UserName);
        
        JLabel lab4 = new JLabel("Email"); lab4.setBounds(40, 165, 70, 12);
        lab4.setFont(new java.awt.Font("Aria", 0, 12));add(lab4);
        Email.setBounds(40, 185, 220, 35);Email.setFont(new java.awt.Font("Verdana", 0, 20));add(Email);
        
        JLabel lab5 = new JLabel("PassWord"); lab5.setBounds(40, 235, 70, 12);
        lab5.setFont(new java.awt.Font("Aria", 0, 12));add(lab5);
        Password.setBounds(40, 255, 220, 35);Password.setFont(new java.awt.Font("Verdana", 0, 20));add(Password);
        
        JLabel lab6 = new JLabel("Confirm PassWord"); lab6.setBounds(40, 305, 170, 12);
        lab6.setFont(new java.awt.Font("Aria", 0, 12));add(lab6);
        ConfirmPass.setBounds(40, 325, 220, 35);ConfirmPass.setFont(new java.awt.Font("Verdana", 0, 20));add(ConfirmPass);
        
        SignUp.setBounds(40, 375, 220, 40); SignUp.setUI(new StyledButtonUI());
        SignUp.setBackground(new java.awt.Color(0x5858FA));SignUp.setForeground(new Color(238, 238, 238));
        SignUp.setFont(new java.awt.Font("Verdana", 1, 15));add(SignUp);
        
        Cancel.setBounds(40, 420, 220, 20); Cancel.setUI(new StyledButtonUI());
        Cancel.setBackground(new java.awt.Color(0x5858FA));Cancel.setForeground(new Color(238, 238, 238));
        Cancel.setFont(new java.awt.Font("Verdana", 1, 14));add(Cancel);
        
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {CancelAction();}
        });
        SignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {SignUpAction();}
        });
    }
    
    private void CancelAction()
    {
        new Login_frame(client).setVisible(true);
        client.remote_SignUp_frame(null);
        this.dispose();
    }
    
    private void SignUpAction()
    {
        if(!isCompleteSignUp())
        {
            ShowMessage();
            return;
        }
        String s1 = UserName.getText();
        String s2 = Email.getText();
        String s3 = Password.getText();
        client.getTransferMethod().SEND("SIGN_UP");
        client.getTransferMethod().SEND(s1);
        client.getTransferMethod().SEND(s2);
        client.getTransferMethod().SEND(s3);
        client.setWaitting();
        if(Success == false) ShowMessage();
        else CancelAction();
    }
    
    public void ShowMessage()
    {
        JOptionPane.showMessageDialog(null, "Sign Up failed");
    }
    
    private boolean isCompleteSignUp()
    {
        String s1 = UserName.getText();
        String s2 = Email.getText();
        String s3 = Password.getText();
        String s4 = ConfirmPass.getText();
        if(!isCompletedPass()) return false;
        if(isBlank(s1)||isBlank(s2)||isBlank(s3)||isBlank(s4)) return false;
        if(s3.compareTo(s4)!=0) return false;
        return true;
    }
    private boolean isBlank(String s)
    {
        return s.compareTo("")==0;
    }
    private boolean isCompletedPass()
    {
        String s = Password.getText();
        return s.length()>=6;
    }
    
    private JButton SignUp = new JButton("Sign Up");
    private JButton Cancel = new JButton("Cancel");
    private JTextField UserName = new JTextField();
    private JTextField Email = new JTextField();
    private JPasswordField Password = new JPasswordField();
    private JPasswordField ConfirmPass = new JPasswordField();
}
