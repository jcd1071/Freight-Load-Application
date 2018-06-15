/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Jose
 */
public class DBHandler {
    
    Connection dbconnection;
    
    public Connection getConnection(){
        
        String connectionString = "jdbc:mysql://" + Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname 
                + "?autoReconnect=true&useSSL=false";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        
        try {
            dbconnection = DriverManager.getConnection(connectionString,Configs.dbuser,Configs.dbpass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dbconnection;
    }
    
}
