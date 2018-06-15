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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author Jose
 */
public class LoadsController implements Initializable{
    
    @FXML
    private AnchorPane loadsHome;

    @FXML
    private JFXTextField tfPcustomer;

    @FXML
    private JFXTextField tfPaddress;

    @FXML
    private JFXTextField tfPcity;

    @FXML
    private JFXTextField tfPstate;
    
    @FXML
    private JFXTextField tfPzip;
    
    @FXML
    private JFXTextField tfPdate;

    @FXML
    private JFXTextField tfPtime;

    @FXML
    private JFXTextField loadNum;

    @FXML
    private JFXTextField tfPEquip;

    @FXML
    private JFXTextField tfPweight;

    @FXML
    private JFXTextField tfPprice;

    @FXML
    private JFXTextField tfDcustomer;

    @FXML
    private JFXTextField tfDaddress;

    @FXML
    private JFXTextField tfDcity;

    @FXML
    private JFXTextField tfDstate;
    
    @FXML 
    private JFXTextField tfDzip;

    @FXML
    private JFXTextField tfDdriver;

    @FXML
    private JFXTextField tfDCarrier;

    @FXML
    private CheckBox cbAssigned;

    @FXML
    private CheckBox cbDispatched;

    @FXML
    private JFXButton btSubmitLoad;
    
    @FXML
    private JFXButton searchLoad;
    
    @FXML
    private JFXButton editLoad;
    
    @FXML
    private JFXButton saveLoad;

    @FXML
    private JFXTextArea unassignedLoads;
    

    @FXML
    private JFXTextArea assignedLoads;
    
    @FXML
    private JFXButton back;
    
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    
    int[] loadId = new int[100];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        generateLoadNum();
        
        handler = new DBHandler();
        
        
    }
    
    /*private Tab createTab(String title, String text){
        
        assignedLoads = (JFXTextArea) new TextArea(text);
        assignedLoads.setWrapText(true);
        
        Tab tab = new Tab(title);
        tab.setContent(assignedLoads);
        
        
    }*/
    
    int assigned=0;
    
    public void loadUnassigned(){
        
        
    }
    
    public void loadAssigned(){
        
        connection = handler.getConnection();
        
         String view = "SELECT loadNum, pCustomer, pAddress, pCity, pState, pZip, pDate, pTime, pEquip, pWeight, pPrice, assigned,"
                    + "dCustomer, dAddress, dCity, dState, dZip, dDriver, dCarrier FROM Loads ORDER BY pDate DEC";
        
      
        try {
            System.out.println("153");
            pst = connection.prepareStatement(view);

            ResultSet rs = pst.executeQuery();

            
            while(rs.next()){
                
                int assigned = rs.getInt("assigned");
                
                if(assigned==1){
                    cbAssigned.setSelected(true);
                    
               
            
                String load = loadNum.getText();
                String customer = tfPcustomer.getText();
                String address = tfPaddress.getText();
                String city = tfPcity.getText();
                String state = tfPstate.getText();
                String zip = tfPzip.getText();
                String date = tfPdate.getText();
                String time = tfPtime.getText();
                String weight = tfPweight.getText();
                String equip = tfPEquip.getText();
                String price = tfPprice.getText();
                String dCustomer = tfDcustomer.getText();
                String dAddress = tfDaddress.getText();
                String dCity = tfDcity.getText();
                String dState = tfDstate.getText();
                String dZip = tfDzip.getText();
                String dDriver = tfDdriver.getText();
                String dCarrier = tfDCarrier.getText();
               
            
            assignedLoads.appendText(load+customer+assigned);
                }
            }
    
        }   catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void submitLoad(ActionEvent event){

        String load = loadNum.getText();
        String customer = tfPcustomer.getText();
        String address = tfPaddress.getText();
        String city = tfPcity.getText();
        String state = tfPstate.getText();
        String zip = tfPzip.getText();
        String date = tfPdate.getText();
        String time = tfPtime.getText();
        String weight = tfPweight.getText();
        String equip = tfPEquip.getText();
        String price = tfPprice.getText();
        String dCustomer = tfDcustomer.getText();
        String dAddress = tfDaddress.getText();
        String dCity = tfDcity.getText();
        String dState = tfDstate.getText();
        String dZip = tfDzip.getText();
        String dDriver = tfDdriver.getText();
        String dCarrier = tfDCarrier.getText();
        
        
        if(cbAssigned.isSelected()){
            
            assigned = 1;
            
        }
        
        if(cbDispatched.isSelected()){
            
            assigned = 2;
            
        }
            
            String insert = "INSERT INTO Loads(loadNum, pCustomer, pAddress, pCity, pState, pZip, pDate, pTime, pEquip, pWeight, pPrice, assigned,"
                    + "dCustomer, dAddress, dCity, dState, dZip, dDriver, dCarrier)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            
            
            connection = handler.getConnection();

            try {
                    pst = connection.prepareStatement(insert);
                    System.out.println("success loads");
                    generateLoadNum();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
            try {   
                    pst.setString(1, load);
                    pst.setString(2, customer);
                    pst.setString(3, address);
                    pst.setString(4, city);
                    pst.setString(5, state);
                    pst.setString(6, zip);
                    pst.setString(7, date);
                    pst.setString(8, time);
                    pst.setString(9, equip);
                    pst.setString(10, weight);
                    pst.setString(11, price);
                    pst.setInt(12, assigned);
                    pst.setString(13, dCustomer);
                    pst.setString(14, dAddress);
                    pst.setString(15, dCity);
                    pst.setString(16, dState);
                    pst.setString(17, dZip);
                    pst.setString(18, dDriver);
                    pst.setString(19, dCarrier);
                    pst.executeUpdate();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
            
                
                tfPcustomer.setText("");
                tfPaddress.setText("");
                tfPcity.setText("");
                tfPstate.setText("");
                tfPzip.setText("");
                tfPdate.setText("");
                tfPtime.setText("");
                tfPweight.setText("");
                tfPEquip.setText("");
                tfPprice.setText("");
                tfDcustomer.setText("");
                tfDaddress.setText("");
                tfDcity.setText("");
                tfDstate.setText("");
                tfDzip.setText("");
                tfDdriver.setText("");
                tfDCarrier.setText("");
                cbAssigned.setSelected(false);
                cbDispatched.setSelected(false);

    }
    
    @FXML
    public void loadEdit(ActionEvent event){
        
                loadNum.setEditable(false);
                tfPcustomer.setEditable(true);
                tfPaddress.setEditable(true);
                tfPcity.setEditable(true);
                tfPstate.setEditable(true);
                tfPzip.setEditable(true);
                tfPdate.setEditable(true);
                tfPtime.setEditable(true);
                tfPweight.setEditable(true);
                tfPEquip.setEditable(true);
                tfPprice.setEditable(true);
                tfDcustomer.setEditable(true);
                tfDaddress.setEditable(true);
                tfDcity.setEditable(true);
                tfDstate.setEditable(true);
                tfDzip.setEditable(true);
                tfDdriver.setEditable(true);
                tfDCarrier.setEditable(true);
                
        
    }
    
    @FXML
    public void loadSave(ActionEvent event){
        
        if(cbAssigned.isSelected()){
            
            assigned = 1;
            
        }
        
        if(cbDispatched.isSelected()){
            
            assigned = 2;
            
        }
        
        String load = loadNum.getText();
        String customer = tfPcustomer.getText();
        String address = tfPaddress.getText();
        String city = tfPcity.getText();
        String state = tfPstate.getText();
        String zip = tfPzip.getText();
        String date = tfPdate.getText();
        String time = tfPtime.getText();
        String weight = tfPweight.getText();
        String equip = tfPEquip.getText();
        String price = tfPprice.getText();
        String dCustomer = tfDcustomer.getText();
        String dAddress = tfDaddress.getText();
        String dCity = tfDcity.getText();
        String dState = tfDstate.getText();
        String dZip = tfDzip.getText();
        String dDriver = tfDdriver.getText();
        String dCarrier = tfDCarrier.getText();
        
        connection = handler.getConnection();
        
        String update = "UPDATE Loads SET loadNum=?, pCustomer=?, pAddress=?, pCity=?, pState=?, pZip=?, pDate=?, pTime=?, pEquip=?, pWeight=?, pPrice=?, assigned=?,"
                    + "dCustomer=?, dAddress=?, dCity=?, dState=?, dZip=?, dDriver=?, dCarrier=? WHERE loadNum";
        
        try {
                    pst = connection.prepareStatement(update);
                    System.out.println("success loads");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Successfully Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("Load was updated successfully!");
                    alert.showAndWait();
                    
                    generateLoadNum();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
        try {
                    pst.setString(1, load);
                    pst.setString(2, customer);
                    pst.setString(3, address);
                    pst.setString(4, city);
                    pst.setString(5, state);
                    pst.setString(6, zip);
                    pst.setString(7, date);
                    pst.setString(8, time);
                    pst.setString(9, equip);
                    pst.setString(10, weight);
                    pst.setString(11, price);
                    pst.setInt(12, assigned);
                    pst.setString(13, dCustomer);
                    pst.setString(14, dAddress);
                    pst.setString(15, dCity);
                    pst.setString(16, dState);
                    pst.setString(17, dZip);
                    pst.setString(18, dDriver);
                    pst.setString(19, dCarrier);
                    pst.executeUpdate();
                    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }          
        
                tfPcustomer.setText("");
                tfPaddress.setText("");
                tfPcity.setText("");
                tfPstate.setText("");
                tfPzip.setText("");
                tfPdate.setText("");
                tfPtime.setText("");
                tfPweight.setText("");
                tfPEquip.setText("");
                tfPprice.setText("");
                tfDcustomer.setText("");
                tfDaddress.setText("");
                tfDcity.setText("");
                tfDstate.setText("");
                tfDzip.setText("");
                tfDdriver.setText("");
                tfDCarrier.setText("");
                cbAssigned.setSelected(false);
                cbDispatched.setSelected(false);
                
                loadNum.setEditable(true);
                tfPcustomer.setEditable(true);
                tfPaddress.setEditable(true);
                tfPcity.setEditable(true);
                tfPstate.setEditable(true);
                tfPzip.setEditable(true);
                tfPdate.setEditable(true);
                tfPtime.setEditable(true);
                tfPweight.setEditable(true);
                tfPEquip.setEditable(true);
                tfPprice.setEditable(true);
                tfDcustomer.setEditable(true);
                tfDaddress.setEditable(true);
                tfDcity.setEditable(true);
                tfDstate.setEditable(true);
                tfDzip.setEditable(true);
                tfDdriver.setEditable(true);
                tfDCarrier.setEditable(true);
                cbAssigned.setSelected(true);
                cbDispatched.setSelected(true);
        
    }
    
    @FXML
    public void loadSearch(ActionEvent e){
        
                loadNum.setEditable(true);
                tfPcustomer.setEditable(false);
                tfPaddress.setEditable(false);
                tfPcity.setEditable(false);
                tfPstate.setEditable(false);
                tfPzip.setEditable(false);
                tfPdate.setEditable(false);
                tfPtime.setEditable(false);
                tfPweight.setEditable(false);
                tfPEquip.setEditable(false);
                tfPprice.setEditable(false);
                tfDcustomer.setEditable(false);
                tfDaddress.setEditable(false);
                tfDcity.setEditable(false);
                tfDstate.setEditable(false);
                tfDzip.setEditable(false);
                tfDdriver.setEditable(false);
                tfDCarrier.setEditable(false);
                cbAssigned.setSelected(false);
                cbDispatched.setSelected(false);
        
        connection = handler.getConnection();
        
        String insert = "SELECT loadNum, pCustomer, pAddress, pCity, pState, pZip, pDate, pTime, pEquip, pWeight, pPrice, assigned,"
                    + "dCustomer, dAddress, dCity, dState, dZip, dDriver, dCarrier from Loads where loadNum=?";
                       
        
        try {
            pst = connection.prepareStatement(insert);
            pst.setString(1, loadNum.getText());
            
        
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                
                String ID = rs.getString("loadNum");
                loadNum.setText(ID);
                String CNAME = rs.getString("pCustomer");
                tfPcustomer.setText(CNAME);
                String CADD = rs.getString("pAddress");
                tfPaddress.setText(CADD);
                String CCITY = rs.getString("pCity");
                tfPcity.setText(CCITY);
                String CSTATE = rs.getString("pState");
                tfPstate.setText(CSTATE);
                String CZIP = rs.getString("pZip");
                tfPzip.setText(CZIP);
                String CDATE = rs.getString("pDate");
                tfPdate.setText(CDATE);
                String CTIME = rs.getString("pTime");
                tfPtime.setText(CTIME);
                String CWEIGHT = rs.getString("pEquip");
                tfPweight.setText(CWEIGHT);
                String CEQUIP = rs.getString("pWeight");
                tfPEquip.setText(CEQUIP);
                String CPRICE = rs.getString("pPrice");
                tfPprice.setText(CPRICE);
                String DCUST = rs.getString("dCustomer");
                tfDcustomer.setText(DCUST);
                String DADD = rs.getString("dAddress");
                tfDaddress.setText(DADD);
                String DCITY = rs.getString("dCity");
                tfDcity.setText(DCITY);
                String DSTATE = rs.getString("dState");
                tfDstate.setText(DSTATE);
                String DZIP = rs.getString("dZip");
                tfDzip.setText(DZIP);              
                String DDRIVER = rs.getString("dDriver");
                tfDdriver.setText(DDRIVER);
                String DCARRIER = rs.getString("dCarrier");
                tfDCarrier.setText(DCARRIER);
                
                int assigned = rs.getInt("assigned");
                
                if(assigned==1){
                    cbAssigned.setSelected(true);
                }
                if(assigned==2){
                    cbDispatched.setSelected(true);
                }
                
                
            }
        
        }catch (SQLException e1){
            e1.printStackTrace();
        }
        
    }
    
    
    @FXML
    public void backAction(ActionEvent e){
        
        back.getScene().getWindow().hide();
        
    }

    private void generateLoadNum() {
        
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);

        
        for(int i = 0; i<=loadId.length; i++){
            
            if (num != i ){
               String formatted = String.format("%05d", num);
               loadNum.setText(formatted);
            }
        
        }
    }
}
    

