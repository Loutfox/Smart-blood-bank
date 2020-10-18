/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbankmenu;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private JFXButton newDonor;
    @FXML
    private JFXButton newHospital;
    @FXML
    private JFXButton logOut;
    @FXML
    private Pane saveLife;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/bloodbankmain/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
        
    }

    @FXML
    private void addDonor(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/adddonor/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    @FXML
    private void addHospital(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/addhospital/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    @FXML
    private void changePassword(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/changepassword/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    @FXML
    private void donorsInfo(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/donorview/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    @FXML
    private void hospitalsInfo(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/hospitalview/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    @FXML
    private void bloodStocksinfo(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/bloodstockdisplay/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    @FXML
    private void realTimeBloodInfo(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/realtimebloodlinechart/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }
    
}
