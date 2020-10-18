/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package changepassword;

import animatefx.animation.Bounce;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    private Label changePassword;
    @FXML
    private JFXPasswordField actualPassword;
    @FXML
    private JFXPasswordField newPassword;
    @FXML
    private JFXPasswordField newPassword2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Bounce(changePassword).play();
    }    

    @FXML
    private void change(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/bloodbankmain/FXMLDocument.fxml"));
        Loader.load();
        bloodbankmain.FXMLDocumentController display = Loader.getController();
        String login = display.getLogin();
        if(newPassword.getText().equals(newPassword2.getText()))
        {
         if(ManageDB.updateSudo(login, actualPassword.getText(), newPassword.getText()))
         {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setTitle("Changing password");
            alert.setContentText("Password changed successfully !");
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
         else
         {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Changing password");
            alert.setContentText("Changing password process failed !");
            Image image = new Image(getClass().getResourceAsStream("no.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            alert.setGraphic(imageView);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setHeaderText(null);
            alert.showAndWait();
         
         }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Changing password");
            alert.setContentText("The first password and the second password doesn't match !");
            Image image = new Image(getClass().getResourceAsStream("no.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            alert.setGraphic(imageView);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setHeaderText(null);
            alert.showAndWait();
        }
            
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/bloodbankmenu/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }
    
}
