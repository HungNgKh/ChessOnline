/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess_online_project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Doan Phuc
 */
public class ConnectDatabase {
    public static Connection ConnectDB()
    {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:chessUser.s3db");
            return con;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
