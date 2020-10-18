/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addhospital;

import animatefx.animation.Bounce;
import com.jfoenix.controls.JFXButton;
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
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
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
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

    @FXML
    private void addHospital(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        if (!name.getText().equals("") && !address.getText().equals("") && !phoneNumber.getText().equals("") && !email.getText().equals("") && !city.getText().equals("")) {
            if (ManageDB.insertHospital(name.getText(), address.getText(), phoneNumber.getText(), email.getText(), city.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
                alert.setTitle("New Hospital");
                alert.setContentText("Hospital added successfully !");
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
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);

            }
        } else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Hospital");
            alert.setContentText("Hospital add failed !");
            Image image = new Image(getClass().getResourceAsStream("no.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            alert.setGraphic(imageView);
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setHeaderText(null);
            alert.showAndWait();
            name.setText("");
            address.setText("");
            phoneNumber.setText("");
            email.setText("");
            city.setText("");
            phoneNumber.setText("");
            city.setText("");

        }
    }

}
