/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbankmain;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import managedb.ManageDB;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Pane leftSide;
    @FXML
    private Pane bloodBank;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginButton;
    
    private static String login;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        login = username.getText();
        String pass = password.getText();
        ResultSet rs = ManageDB.fetchSudo(login,pass);
        if(rs.first())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setTitle("Login");
            alert.setContentText("Logging successfully !");
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
            alert.setTitle("Login");
            alert.setContentText("Username or password incorrect !");
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
    private void keyPressed(javafx.scene.input.KeyEvent event) {
        if(event.getCode()==KeyCode.ENTER)
            loginButton.fire();
    }
    public String getLogin()
    {
        return login;
    }
    
}
