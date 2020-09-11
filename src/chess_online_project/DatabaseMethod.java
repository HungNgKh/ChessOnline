/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess_online_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


class DatabaseMethod {
    private final Connection con;
    private Statement state;
    private ResultSet resultSet;
    private PreparedStatement prstate;
    public DatabaseMethod()
    {
        con = ConnectDatabase.ConnectDB();
    }
    private boolean excuteDB(String sql) /* dùng hàm này để thực hiện những chức năng như delete or insert
            trả về true nếu thực hiện được else false*/
    {
        try {
            state = con.createStatement();
            state.execute(sql);
            return true;
        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseMethod.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    private ArrayList selectDb(String sql,String select[]) 
            /* Dùng hàm này để thực hiện chức năng select của database, sql là câu lệnh select
            select[] là mảng tên các trường cần select
            kết quả select được cho vào array theo thứ tự select[]*/
    {
        ArrayList array = new ArrayList();
        try {
            prstate = con.prepareStatement(sql);
            resultSet = prstate.executeQuery();
            while(resultSet.next())
            {
                for(int i = 0 ;i < select.length;i++)
                {
                    if(resultSet.getString(select[i])==null)
                        array.add("");
                    else
                        array.add(resultSet.getString(select[i]));
                }
            }
            return array;
        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseMethod.class.getName()).log(Level.SEVERE, null, ex);
            return array;
        }
    }
    
    public boolean SignUp(String UserName,String Password, String Email)
    {
        String sql ="insert into User(UserName,Password,Email,win,lost) values(\""+UserName+"\","
                + "\""+Password+"\",\""+Email+"\",0,0)";
        return excuteDB(sql);
    }
    
    public int[] Login(String UserName, String Password)
    {
        int result[] = new int[2];
        String sql = "select * from User where UserName =\""+UserName+"\" and Password = \""+Password+"\"";
        String cot[] = {"win","lost"};
        ArrayList ar = selectDb(sql, cot);
        if(ar.size()==2)
        {
            result[0]= Integer.parseInt((String) ar.get(0));
            result[1]= Integer.parseInt((String) ar.get(1));
        }
        return result;
    }
}
