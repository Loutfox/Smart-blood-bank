/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adddonor;

import animatefx.animation.Bounce;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import managedb.ManageDB;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Label addDonor;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField age;
    @FXML
    private TextField gender;
    @FXML
    private TextField bloodGroup;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField city;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Bounce(addDonor).play();
    }    

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/bloodbankmenu/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    @FXML
    private void addDonor(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        if(!age.getText().equals("") && !lastName.getText().equals("") && !firstName.getText().equals("") && !gender.getText().equals("") && !bloodGroup.getText().equals("") && !phoneNumber.getText().equals("") && !city.getText().equals(""))
        {
        if(ManageDB.insertDonor(lastName.getText(), firstName.getText(), Integer.parseInt(age.getText()), gender.getText(), bloodGroup.getText(), phoneNumber.getText(), city.getText()))
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
        alert.setTitle("New Donor");
        alert.setContentText("Donor added successfully !");
        Image image = new Image(getClass().getResourceAsStream("yes.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(64);
        imageView.setFitHeight(64);
        alert.setGraphic(imageView);
        alert.setHeaderText(null);
        alert.showAndWait();
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/bloodbankmenu/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Donor");
            alert.setContentText("Donor add failed !");
            Image image = new Image(getClass().getResourceAsStream("no.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            alert.setGraphic(imageView);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setHeaderText(null);
            alert.showAndWait();
            lastName.setText("");
            firstName.setText("");
            age.setText("");
            gender.setText("");
            bloodGroup.setText("");
            phoneNumber.setText("");
            city.setText("");
           
            
            
        }
    }
    
}
