/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Jose
 */
public class HomeController implements Initializable{

    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private JFXButton btnLoads;

    @FXML
    private JFXButton btnDrivers;

    @FXML
    private JFXButton btnCarriers;

    @FXML
    private JFXButton btnCustomers;


    
    HomePageController homePage;
    AnchorPane u;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    public void loadsAction(ActionEvent event){
        
        System.out.println("loads");
        
        try {
            
            loadFXML("/FXML/Load.fxml","Loads");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
       @FXML
       public void carriersAction(ActionEvent event) {
           
           System.out.println("carriers");
           
            try {
            
            loadFXML("/FXML/Carriers.fxml","Carriers");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

        @FXML
        public void customersAction(ActionEvent event) {
            
            System.out.println("customers");
            
             try {
            
            loadFXML("/FXML/Customers.fxml","Customers");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

        @FXML
        public void driversAction(ActionEvent event) {
            
            System.out.println("drivers");
            
             try {
            
            loadFXML("/FXML/Drivers.fxml","Drivers");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void loadFXML(String loc, String title) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource(loc));
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.setX(491);
        stage.setY(129);
        stage.show();
        
    }
    
}
