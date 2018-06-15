/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DBConnection.DBHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jose
 */
public class DriversController implements Initializable {
    
    @FXML
    private AnchorPane customerAnchor;

    @FXML
    private JFXTextField driverId;

    @FXML
    private JFXTextField driverName;

    @FXML
    private JFXTextField driverCompany;
    
    @FXML
    private JFXTextField companyNumber;

    @FXML
    private JFXTextField driverLicense;

    @FXML
    private JFXTextField contactPhone;

    @FXML
    private JFXTextField mcNumber;

    @FXML
    private JFXTextField usdot;

    @FXML
    private JFXTextArea customersView;

    @FXML
    private JFXButton newDriver;

    @FXML
    private JFXButton editDriver;

    @FXML
    private JFXButton searchDriver;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton saveDriver;
    
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    @FXML
    void addDriver(ActionEvent event) {
      
        
        if(driverId.equals("") && driverName.equals("") && driverLicense.equals("")  
               && contactPhone.equals("") && mcNumber.equals("")){
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Missing Info");
                    alert.setContentText("Name, Address, City, State, Zip, Contact, \n and Phone must be filled out");

                    alert.showAndWait();
                    
                    driverName.setText("");
                    driverName.setText("");
                    driverLicense.setText("");
                    contactPhone.setText("");
                    mcNumber.setText("");                 
                    
        }
        
        else{
        String Id = driverId.getText();
        String name = driverName.getText();
        String company = driverCompany.getText();
        String number = companyNumber.getText();
        String license = driverLicense.getText();
        String phone = contactPhone.getText();
        String mc = mcNumber.getText();
        String dot = usdot.getText();
        
        
        
        
        String insert = "INSERT INTO Drivers (idDrivers, dName, dCompany, dCnum, dLicense, dPhone, dMc, dDot)"
                + "VALUES(?,?,?,?,?,?,?,?)";
         
        connection = handler.getConnection();
        
        try {
                    pst = connection.prepareStatement(insert);
                    System.out.println("success loads");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successfully Added");
                    alert.setHeaderText(null);
                    alert.setContentText("New driver was added successfully!");
                    alert.showAndWait();
                    
                    
                    generateDriverNum();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        try {
            
            pst.setString(1, Id);
            pst.setString(2, name);
            pst.setString(3, company);
            pst.setString(4, number);
            pst.setString(5, license);
            pst.setString(6, phone);
            pst.setString(7, mc);
            pst.setString(8, dot);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        
        driverName.setText("");
        driverCompany.setText("");
        companyNumber.setText("");
        driverLicense.setText("");
        contactPhone.setText("");
        mcNumber.setText("");
        usdot.setText("");
        
        }
        
    }

    

    @FXML
    void backAction(ActionEvent event) {
        
        back.getScene().getWindow().hide();

    }

    @FXML
    void editDriver(ActionEvent event) {
        
        driverId.setEditable(false);
        driverName.setEditable(true);
        driverCompany.setEditable(true);
        companyNumber.setEditable(true);
        driverLicense.setEditable(true);
        contactPhone.setEditable(true);
        mcNumber.setEditable(true);
        usdot.setEditable(true);
        

    }

    @FXML
    void saveDriver(ActionEvent event) {
        
        String Id = driverId.getText();
        String name = driverName.getText();
        String company = driverCompany.getText();
        String number = companyNumber.getText();
        String license = driverLicense.getText();
        String phone = contactPhone.getText();
        String mc = mcNumber.getText();
        String dot = usdot.getText();
        
        connection = handler.getConnection();
        
        String update = "UPDATE Drivers set dName=?, dCompany=?, dCnum=?, dLicense=?, dPhone=?, "
                + "dMc=?, dDot=? WHERE idDrivers ";
        

        try {
                    pst = connection.prepareStatement(update);
                    System.out.println("success loads");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successfully Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("New driver was updated successfully!");
                    alert.showAndWait();
                    
                    generateDriverNum();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        try {
            
            
            pst.setString(1, name);
            pst.setString(2, company);
            pst.setString(3, number);
            pst.setString(4, license);
            pst.setString(5, phone);
            pst.setString(6, mc);
            pst.setString(7, dot);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
        driverName.setText("");
        driverCompany.setText("");
        companyNumber.setText("");
        driverLicense.setText("");
        contactPhone.setText("");
        mcNumber.setText("");
        usdot.setText("");
        
        driverId.setEditable(true);
        driverName.setEditable(true);
        driverCompany.setEditable(true);
        companyNumber.setEditable(true);
        driverLicense.setEditable(true);
        contactPhone.setEditable(true);
        mcNumber.setEditable(true);
        usdot.setEditable(true);

    }

    @FXML
    void searchDriver(ActionEvent event) {
        
        driverName.setEditable(false);
        driverCompany.setEditable(false);
        companyNumber.setEditable(false);
        driverLicense.setEditable(false);
        contactPhone.setEditable(false);
        mcNumber.setEditable(false);
        usdot.setEditable(false);
        
        connection = handler.getConnection();
        
        String search = "SELECT idDrivers, dName, dCompany, dCnum, dLicense, dPhone, "
                + "dMc, dDot from Drivers where idDrivers=?";
        
        try {
            pst = connection.prepareStatement(search);
            pst.setString(1, driverId.getText());
            
        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                String ID = rs.getString("idDrivers");
                driverId.setText(ID);
                String NAME = rs.getString("dName");
                driverName.setText(NAME);
                String COMP = rs.getString("dCompany");
                driverCompany.setText(COMP);
                String NUM = rs.getString("dCnum");
                companyNumber.setText(NUM);
                String LIC = rs.getString("dLicense");
                driverLicense.setText(LIC);
                String PHONE = rs.getString("dPhone");
                contactPhone.setText(PHONE);
                String MC = rs.getString("dMc");
                mcNumber.setText(MC);
                String DOT = rs.getString("dDot");
                usdot.setText(DOT);
                
            }
        
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    
    private void loadView() {
        
        connection = handler.getConnection();
        
        String view = "SELECT idCustomers, customerName FROM Customers ORDER BY customerName ASC";
        System.out.println("195");
        try {

            pst = connection.prepareStatement(view);

            ResultSet rs = pst.executeQuery();

            
            while(rs.next()){
            String ID = rs.getString("idCustomers");
            String NAME = rs.getString("customerName");
                System.out.println(ID);
            customersView.appendText("Customer ID: " + ID + " Customer Name: " + NAME + "\n" );
            }     
    
        }   catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        driverId.setEditable(true);
        driverName.setEditable(true);
        driverCompany.setEditable(true);
        companyNumber.setEditable(true);
        driverLicense.setEditable(true);
        contactPhone.setEditable(true);
        mcNumber.setEditable(true);
        usdot.setEditable(true);
        
        generateDriverNum();
        handler = new DBHandler();
        loadView();
       
    }

    
    int[] idNum = new int[1000];
    
    private void generateDriverNum() {
        
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);

        
        for(int i = 0; i<=idNum.length; i++){
            
            if (num != i ){
               
               String formatted = String.format("%05d", num);
               driverId.setText(formatted);
            }
        
        }
    }
    
}
