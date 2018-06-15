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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jose
 */
public class CustomersController implements Initializable {
    
    @FXML
    private AnchorPane customerAnchor;

    @FXML
    private JFXTextField customerId;

    @FXML
    private JFXTextField customerName;

    @FXML
    private JFXTextField customerAddress;

    @FXML
    private JFXTextField customerCity;

    @FXML
    private JFXTextField customerState;

    @FXML
    private JFXTextField customerZip;

    @FXML
    private JFXTextField contactName;

    @FXML
    private JFXTextField contactPhone;

    @FXML
    private JFXTextField taxId;

    @FXML
    private JFXTextField mcNumber;

    @FXML
    private JFXTextField usdot;

    @FXML
    private JFXTextArea customersView;

    @FXML
    private JFXButton newCust;

    @FXML
    private JFXButton editCust;

    @FXML
    private JFXButton searchCust;
    
    @FXML
    private JFXButton saveCustomer;

    @FXML
    private JFXButton back;
    
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
      
        generateCustNum();
        handler = new DBHandler();
     
    }

    @FXML
    public void addCustomer(ActionEvent event) {
        
           
        
        String Id = customerId.getText();
        String custName = customerName.getText();
        String custAddress = customerAddress.getText();
        String custCity = customerCity.getText();
        String custState = customerState.getText();
        String custZip = customerZip.getText();
        String contName = contactName.getText();
        String contPhone = contactPhone.getText();
        String tax = taxId.getText();
        String mc = mcNumber.getText();
        String dot = usdot.getText();
        
        if(customerId!=null && customerName!=null && customerAddress!=null && customerCity!=null && customerState!=null && customerZip!=null && 
                contactName!=null && contactPhone!=null){
        
        String insert = "INSERT INTO Customers(idCustomers, customerName, customerAddress, customerCity, customerState, customerZip, customerContact, customerPhone,"
                + "customerTaxid, customerMcnum, customerUSDOT)" 
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
         
        connection = handler.getConnection();
        
        try {
                    pst = connection.prepareStatement(insert);
                    System.out.println("success loads");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Successfully Added");
                    alert.setHeaderText(null);
                    alert.setContentText("New customer was added successfully!");
                    alert.showAndWait();
                    
                    generateCustNum();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        try {
            
            pst.setString(1, Id);
            pst.setString(2, custName);
            pst.setString(3, custAddress);
            pst.setString(4, custCity);
            pst.setString(5, custState);
            pst.setString(6, custZip);
            pst.setString(7, contName);
            pst.setString(8, contPhone);
            pst.setString(9, tax);
            pst.setString(10, mc);
            pst.setString(11, dot);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        customersView.appendText(Id + " " + custName + "\n");
        
        
        customerName.setText("");
        customerAddress.setText("");
        customerCity.setText("");
        customerState.setText("");
        customerZip.setText("");
        contactName.setText("");
        contactPhone.setText("");
        taxId.setText("");
        mcNumber.setText("");
        usdot.setText("");
        
        }
        
        else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Missing Info");
                    alert.setContentText("Name, Address, City, State, Zip, Contact, \n and Phone must be filled out");

                    alert.showAndWait();
                    
                    customerName.setText("");
                    customerAddress.setText("");
                    customerCity.setText("");
                    customerState.setText("");
                    customerZip.setText("");
                    contactName.setText("");
                    contactPhone.setText("");
                    taxId.setText("");
                    mcNumber.setText("");
                    usdot.setText("");
        }

    }

    @FXML
    public void backAction(ActionEvent event) {
        
        back.getScene().getWindow().hide();

    }

    @FXML
    public void editCustomer(ActionEvent event) {
        
        customerId.setEditable(false);
        customerName.setEditable(true);
        customerAddress.setEditable(true);
        customerCity.setEditable(true);
        customerState.setEditable(true);
        customerZip.setEditable(true);
        contactName.setEditable(true);
        contactPhone.setEditable(true);
        taxId.setEditable(true);
        mcNumber.setEditable(true);
        usdot.setEditable(true);
        
       
    }
    
    @FXML
    public void saveCust(ActionEvent event){
        
        String Id = customerId.getText();
        String custName = customerName.getText();
        String custAddress = customerAddress.getText();
        String custCity = customerCity.getText();
        String custState = customerState.getText();
        String custZip = customerZip.getText();
        String contName = contactName.getText();
        String contPhone = contactPhone.getText();
        String tax = taxId.getText();
        String mc = mcNumber.getText();
        String dot = usdot.getText();
        
        connection = handler.getConnection();
        
        String update = "UPDATE Customers set customerName=?, customerAddress=?, customerCity=?, customerState=?, customerZip=?, "
                + "customerContact=?, customerPhone=?, customerTaxid=?, customerMcnum=?, customerUSDOT=? WHERE idCustomers ";
        

        try {
                    pst = connection.prepareStatement(update);
                    System.out.println("success loads");
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Successfully Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("New customer was updated successfully!");
                    alert.showAndWait();
                    
                    generateCustNum();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        try {
            
            
            pst.setString(1, custName);
            pst.setString(2, custAddress);
            pst.setString(3, custCity);
            pst.setString(4, custState);
            pst.setString(5, custZip);
            pst.setString(6, contName);
            pst.setString(7, contPhone);
            pst.setString(8, tax);
            pst.setString(9, mc);
            pst.setString(10, dot);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        customersView.appendText(Id + " " + custName + "\n");
        
        
        customerName.setText("");
        customerAddress.setText("");
        customerCity.setText("");
        customerState.setText("");
        customerZip.setText("");
        contactName.setText("");
        contactPhone.setText("");
        taxId.setText("");
        mcNumber.setText("");
        usdot.setText("");
    }

    @FXML
    void searchCustomer(ActionEvent event) {
        
        customerId.setEditable(false);
        customerName.setEditable(false);
        customerAddress.setEditable(false);
        customerCity.setEditable(false);
        customerState.setEditable(false);
        customerZip.setEditable(false);
        contactName.setEditable(false);
        contactPhone.setEditable(false);
        taxId.setEditable(false);
        mcNumber.setEditable(false);
        usdot.setEditable(false);
        
        connection = handler.getConnection();
        
        String search = "SELECT idCustomers, customerName, customerAddress, customerCity, customerState, customerZip, "
                + "customerContact, customerPhone, customerTaxid, customerMcnum, customerUSDOT from Customers where idCustomers=?";
        
        try {
            pst = connection.prepareStatement(search);
            pst.setString(1, customerId.getText());
            
        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                String ID = rs.getString("idCustomers");
                customerId.setText(ID);
                String NAME = rs.getString("customerName");
                customerName.setText(NAME);
                String CADD = rs.getString("customerAddress");
                customerAddress.setText(CADD);
                String CCITY = rs.getString("customerCity");
                customerCity.setText(CCITY);
                String CSTATE = rs.getString("customerState");
                customerState.setText(CSTATE);
                String CZIP = rs.getString("customerZip");
                customerZip.setText(CZIP);
                String CONT = rs.getString("customerContact");
                contactName.setText(CONT);
                String PHONE = rs.getString("customerPhone");
                contactPhone.setText(PHONE);
                String TAX = rs.getString("customerTaxid");
                taxId.setText(TAX);
                String MC = rs.getString("customerMcnum");
                mcNumber.setText(MC);
                String USDOT = rs.getString("customerUSDOT");
                usdot.setText(USDOT);
            }
        
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
   

    int[] custId = new int[1000];
    
    
    private void generateCustNum() {
        
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);

        
        for(int i = 0; i<=custId.length; i++){
            
            if (num != i ){
               String formatted = String.format("%05d", num);
               customerId.setText(formatted);
            }
        
        }
    }
    
}
