/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalview;

import donorview.Donor;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Hospital> table;
    @FXML
    private TableColumn<Hospital, String> col_name;
    @FXML
    private TableColumn<Hospital, String> col_phonenumber;
    @FXML
    private TableColumn<Hospital, String> col_email;
    @FXML
    private TableColumn<Hospital, String> col_city;
    @FXML
    private TableColumn<Hospital, Integer> col_id;
    @FXML
    private TableColumn<Hospital, String> col_address;

    ObservableList<Hospital> oblist = FXCollections.observableArrayList();

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rs = null;
        try {
            rs = ManageDB.fetchHospitals();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                oblist.add(new Hospital(rs.getInt("idHospital"), rs.getString("name"), rs.getString("address"), rs.getString("phoneNumber"), rs.getString("email"), rs.getString("city")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        table.setItems(oblist);

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/bloodbankmenu/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

    @FXML
    private void deleteHospital(ActionEvent event) throws ClassNotFoundException, SQLException {
        Hospital hospitalSelected = table.getSelectionModel().getSelectedItem();
        if (hospitalSelected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hospital Deletion");
            alert.setContentText("No hospital is selected !");
            Image image = new Image(getClass().getResourceAsStream("no.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            alert.setGraphic(imageView);
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setTitle("Hospital deletion");
            alert.setContentText("Are you sure want to delete the hospital : "+hospitalSelected.getName()+" ?");
            Image image = new Image(getClass().getResourceAsStream("yes.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            alert.setGraphic(imageView);
            alert.setHeaderText(null);
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.YES) {
                try{
                if (ManageDB.deleteHospital(hospitalSelected.getId())) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Hospital Deletion");
                    alert2.setContentText("Hospital deleted sucessfully!");
                    Image image2 = new Image(getClass().getResourceAsStream("yes.png"));
                    ImageView imageView2 = new ImageView(image2);
                    imageView2.setFitWidth(64);
                    imageView2.setFitHeight(64);
                    alert2.setGraphic(imageView2);
                    ((Stage) alert2.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
                    alert2.setHeaderText(null);
                    alert2.showAndWait();
                    oblist.remove(hospitalSelected);

                } else {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Hospital Deletion");
                    alert2.setContentText("Hospital deletion failed !");
                    Image image2 = new Image(getClass().getResourceAsStream("no.png"));
                    ImageView imageView2 = new ImageView(image2);
                    imageView2.setFitWidth(64);
                    imageView2.setFitHeight(64);
                    alert2.setGraphic(imageView2);
                    ((Stage) alert2.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
                    alert2.setHeaderText(null);
                    alert2.showAndWait();
                    oblist.remove(hospitalSelected);

                }
                }catch(Exception e)
                {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Hospital Deletion");
                    alert2.setContentText("You can't delete this hospital due to foreign keys reasons !");
                    Image image2 = new Image(getClass().getResourceAsStream("no.png"));
                    ImageView imageView2 = new ImageView(image2);
                    imageView2.setFitWidth(64);
                    imageView2.setFitHeight(64);
                    alert2.setGraphic(imageView2);
                    ((Stage) alert2.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
                    alert2.setHeaderText(null);
                    alert2.showAndWait();
                }
            }
        }
    }

}
