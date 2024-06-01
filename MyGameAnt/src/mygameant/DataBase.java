/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygameant;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Miklos Bolarde
 */
public class DataBase {
    static final String DB_url="jdbc:mysql://localhost:3306/user_database",userr="root",passs="";
    public static Connection connection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(DB_url,userr,passs);  
            return con;
        }catch(Exception e){
            System.out.println("No Connection! "+e.getMessage());
            return null;
        }
    }
}
