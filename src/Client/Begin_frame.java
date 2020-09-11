
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Doan Phuc
 */
public class Begin_frame extends JFrame{
    
    public Begin_frame()
    {
        init();
    }
    
    private void init()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500); setResizable(false); setLayout(null); setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        new Begin_frame().setVisible(true);
    }
    
    private JButton but_play = new JButton("Play now");
}
