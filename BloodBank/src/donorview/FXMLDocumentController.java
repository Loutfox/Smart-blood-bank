/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donorview;

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
    private TableView<Donor> table;
    @FXML
    private TableColumn<Donor, Integer> col_id;
    @FXML
    private TableColumn<Donor, String> col_lastname;
    @FXML
    private TableColumn<Donor, String> col_firstname;
    @FXML
    private TableColumn<Donor, Integer> col_age;
    @FXML
    private TableColumn<Donor, String> col_gender;
    @FXML
    private TableColumn<Donor, String> col_bloodgroup;
    @FXML
    private TableColumn<Donor, String> col_phonenumber;
    @FXML
    private TableColumn<Donor, String> col_city;

    ObservableList<Donor> oblist = FXCollections.observableArrayList();

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rs = null;
        try {
            rs = ManageDB.fetchDonors();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while (rs.next()) {
                oblist.add(new Donor(rs.getInt("idDonor"), rs.getString("lastName"), rs.getString("firstName"), rs.getInt("Age"), rs.getString("Gender"), rs.getString("bloodGroup"), rs.getString("phoneNumber"), rs.getString("City")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        col_bloodgroup.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
        col_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("City"));
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
    private void deleteDonor(ActionEvent event) throws ClassNotFoundException, SQLException {
        Donor donorSelected = table.getSelectionModel().getSelectedItem();
        if (donorSelected == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donor Deletion");
            alert.setContentText("No donor is selected !");
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
            alert.setTitle("Deleting Donor");
            alert.setContentText("Are you sure want to delete the donor : " + donorSelected.getLastName() + " " + donorSelected.getFirstName() + " ?");
            Image image = new Image(getClass().getResourceAsStream("yes.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            alert.setGraphic(imageView);
            alert.setHeaderText(null);
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.YES) {
                if (ManageDB.deleteDonor(donorSelected.getId())) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Donor Deletion");
                    alert2.setContentText("Donor deleted sucessfully!");
                    Image image2 = new Image(getClass().getResourceAsStream("yes.png"));
                    ImageView imageView2 = new ImageView(image2);
                    imageView2.setFitWidth(64);
                    imageView2.setFitHeight(64);
                    alert2.setGraphic(imageView2);
                    ((Stage) alert2.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
                    alert2.setHeaderText(null);
                    alert2.showAndWait();
                    oblist.remove(donorSelected);

                } else
                {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Donor Deletion");
                    alert2.setContentText("Donor deletion failed !");
                    Image image2 = new Image(getClass().getResourceAsStream("no.png"));
                    ImageView imageView2 = new ImageView(image2);
                    imageView2.setFitWidth(64);
                    imageView2.setFitHeight(64);
                    alert2.setGraphic(imageView2);
                    ((Stage) alert2.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
                    alert2.setHeaderText(null);
                    alert2.showAndWait();
                    oblist.remove(donorSelected);

                }
            }
        }
    }

}
