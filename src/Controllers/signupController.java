/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import DBConnection.DBHandler; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;




/**
 *
 * @author Jose
 */
public class signupController implements Initializable {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField email;
    
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmpass;

    @FXML
    private JFXButton signup;

    @FXML
    private JFXButton login;

    @FXML
    private ImageView progress;
    
    @FXML
    private TextField emailmess;
    
    @FXML
    private ImageView passwordchar;

  
    private Connection connection;
    private DBHandler handler;
    private PreparedStatement pst;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        passwordchar.setVisible(false);
        progress.setVisible(false);
        name.setStyle("-fx-text-inner-color: #000003;");
        email.setStyle("-fx-text-inner-color: #000003;");
        password.setStyle("-fx-text-inner-color: #000003;");
        confirmpass.setStyle("-fx-text-inner-color: #000003;");
        
        handler = new DBHandler();
    }
    
    public static boolean isValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    
    
    
    public static boolean passStrength(String password){
        boolean hasLetter = false;
        boolean hasNumber = false;
        boolean hasUpcase = false;
        boolean passFail = false;
        
        if(password.length()>=8&&password.length()<=14){
            for(int i=0; i < password.length(); i++){
                char letchck = password.charAt(i);
                
                if(Character.isLetter(letchck)){
                    hasLetter = true;
                }
                
                if(Character.isDigit(letchck)){
                    hasNumber = true;
                }
                
                if(Character.isUpperCase(letchck)){
                    hasUpcase = true;
                }
                
                if(hasLetter==true&&hasNumber==true&&hasUpcase){
                    passFail = true;
                    
                }
                
                else{
                    
                    passFail = false;
                }
            }           
        }       
        return passFail;
    }
    
  
    @FXML
    public void signUP(ActionEvent ae1){
        
        String pw,cpw;
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        email.getText();
        pw = password.getText();
        cpw = confirmpass.getText(); 
       
            
        pt.setOnFinished(e -> {
            
            String useremail = email.getText();
            
            if(passStrength(pw)==true){
                System.out.println("Password Strong");
            }
            if(passStrength(pw)==false){
                System.out.println("Password Weak");
            }
            
            
            
            if(isValid(useremail)&&pw.equals(cpw)&&passStrength(pw)){
                    
                
                String insert = "INSERT INTO Users(name, email, password)"
                    + "VALUES(?,?,?)";
                
                    connection = handler.getConnection();
                try {
                    pst = connection.prepareStatement(insert);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
                try {
                    pst.setString(1, name.getText());
                    pst.setString(2, email.getText());
                    pst.setString(3, password.getText());
                    
            
                    pst.executeUpdate();
            
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
                login.getScene().getWindow().hide();
        
                Stage login = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Scene scene = new Scene(root,664,424);
                login.setScene(scene);
                login.show();
                login.setResizable(true);
            }         
            
        if(!pw.equals(cpw)||passStrength(pw)==false){
            
            passwordchar.setVisible(true);
            password.setText("");
            confirmpass.setText("");
        }
                else{
            passwordchar.setVisible(false);
        }
            
        progress.setVisible(false);
            
        });
        
        pt.play();
        
               
    }
    
    @FXML
    public void loginAction(ActionEvent ae2) throws IOException{
        signup.getScene().getWindow().hide();
        
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
        Scene scene = new Scene(root,664,424);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
    
     
    
}
