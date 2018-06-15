/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import DBConnection.DBHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 *
 * @author Jose
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton signup;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXCheckBox remember;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton forgotpassword;

    @FXML
    private ImageView progress;

    @FXML
    private JFXPasswordField password;
   
    @FXML
    private ImageView credentialerror;
    
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        credentialerror.setVisible(false);
        progress.setVisible(false);
        username.setStyle("-fx-text-inner-color: #000003;");
        password.setStyle("-fx-text-inner-color: #000003;");
        
        handler = new DBHandler();
        
    }
    
    @FXML
    public void loginAction(ActionEvent e) throws IOException{
       
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev ->{
        
            
            progress.setVisible(false);
    });
        pt.play();
        
        connection = handler.getConnection();
        String q1 = "SELECT * from Users where email=? and password=?";
        
        try {
            pst = connection.prepareStatement(q1);
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            
            ResultSet rs = pst.executeQuery();
            
            int count = 0;
            
            while(rs.next()){
                count = count+1;
            }
            
            if(count==1){
                credentialerror.setVisible(false);
                
                login.getScene().getWindow().hide();
        
                Stage dash = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/DashBoard.fxml"));
        
                Scene scene = new Scene(root,684,441);
                dash.setScene(scene);
                dash.show();
                dash.setResizable(false);
                
                
                System.out.println("Login Successful");
            }
            else{
                credentialerror.setVisible(true);
                System.out.println("Username and Password is not Correct");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        finally{
            try{
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    @FXML
    public void signUp(ActionEvent e1) throws IOException{
        login.getScene().getWindow().hide();
        
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SignUpMain.fxml"));
        
        Scene scene = new Scene(root,664,424);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
    
    @FXML
    public void forgotPassword(ActionEvent e2) throws IOException{
        login.getScene().getWindow().hide();
        
        Stage forgotpassword = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ForgotPassword.fxml"));
        Scene scene = new Scene(root, 664, 424);
        forgotpassword.setScene(scene);
        forgotpassword.show();
        forgotpassword.setResizable(false);
        
    }
    
    
}
