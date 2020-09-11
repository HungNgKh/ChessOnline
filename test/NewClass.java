
import com.google.gson.Gson;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Doan Phuc
 */
public class NewClass {
    public String s = "Doan Minh Phuc";
    public int i=1;
    public ArrayList ar = new ArrayList();
    
    public NewClass()
    {
        ar.add("abcd");
    }
    public static void main(String[] args) {
        NewClass newClass = new NewClass();
        Gson gson = new Gson();
        String json = gson.toJson(newClass);
    }
}
