/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freight;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;




/**
 *
 * @author Jose
 */
public class Freight extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try{
           
           Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
           Scene scene = new Scene(root,664,471);
           primaryStage.setScene(scene);
          
           primaryStage.setResizable(true);
            primaryStage.show();
       }
       catch(Exception e){
           e.printStackTrace();
       }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/Freight","root","Jlkkinm2160");
        System.out.println("Connected to Database");*/
        launch(args);
    }
    
}
