/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXToolbar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author Jose
 */
public class HomePageController implements Initializable {
    
    @FXML 
    private AnchorPane holderpane;
    
    @FXML
    private AnchorPane home;
    
    @FXML
    private Text txtWelcome;

    @FXML
    private AnchorPane anchor;
    
    @FXML
    private JFXToolbar toolbar;

    @FXML
    private HBox toolBarRight;

    @FXML
    private Label lblMenu;

    @FXML
    private VBox overflowContainer;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnExit;
    
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        JFXRippler rippler = new JFXRippler(lblMenu);
        rippler.setMaskType(JFXRippler.RipplerMask.RECT);
        toolBarRight.getChildren().add(rippler);
        
        System.out.println("inin");
        openMenus();
        createPage();
        
    }
    
    
    private void setNode(Node node){
        
        holderpane.getChildren().clear();
        holderpane.getChildren().add((Node) node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void createPage() {
        System.out.println("in create");
       
        try {
            home = FXMLLoader.load(getClass().getResource("/FXML/HOME.fxml"));
            System.out.println("home");
            setNode(home);
        } catch (IOException ex) {
            System.out.println("no home");
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void openMenus() {
        
        JFXPopup pop = new JFXPopup();
        pop.setContent(overflowContainer);
        pop.setPopupContainer(anchor);
        pop.setSource(lblMenu);
        
        lblMenu.setOnMouseClicked(e ->{
            
            pop.show(JFXPopup.PopupVPosition.TOP,JFXPopup.PopupHPosition.RIGHT, -1, 42);
            
            
        });
    }
    
    @FXML
    void exit(ActionEvent event) {

        Platform.exit();
        
    }

    @FXML
    void logOut(ActionEvent event) {
        
        btnLogout.getScene().getWindow().hide();
        
        Stage login = new Stage();
        Parent root;
        
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
            login.setResizable(false);
        
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    

    }

      
}
