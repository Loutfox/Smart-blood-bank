/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realtimebloodlinechart;

import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import managedb.ManageDB;

/**
 *
 * @author asus
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private JFXTextField searchBar;
    @FXML
    private FontAwesomeIconView searchIcon;
    @FXML
    private LineChart<?, ?> bloodLineChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;

    XYChart.Series series;
    int i = 0;
    XYChart.Series series1;
    @FXML
    private JFXTextField bloodType;
    @FXML
    private BarChart<?, ?> bloodChart;
    @FXML
    private NumberAxis y1;
    @FXML
    private CategoryAxis x1;
    
    double Aplus;
    double Amoins;
    double Bplus;
    double Bmoins;
    double Oplus;
    double Omoins;
    double ABplus;
    double ABmoins;
    boolean temp = false;

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void search(MouseEvent event) throws IOException {
        String hospital = searchBar.getText();
        String blood = bloodType.getText();
        if ((!hospital.equals("")) && (!blood.equals(""))) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            // setup a scheduled executor to periodically put data into the chart
            ScheduledExecutorService scheduledExecutorService;
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            series = new XYChart.Series<>();
            series.setName("Blood");
            bloodLineChart.getData().addAll(series);
            series1 = new XYChart.Series<>();
            series1.setName("Blood");
            bloodChart.getData().addAll(series1);

            // put dummy data onto graph per second
            scheduledExecutorService.scheduleAtFixedRate(() -> {

                // Update the chart
                Platform.runLater(() -> {
                    String bloodC = "";
                    if (blood.equals("A+")) {
                        bloodC = "Aplus";
                    }
                    if (blood.equals("A-")) {
                        bloodC = "Amoins";
                    }
                    if (blood.equals("B+")) {
                        bloodC = "Bplus";
                    }
                    if (blood.equals("B-")) {
                        bloodC = "Bmoins";
                    }
                    if (blood.equals("O+")) {
                        bloodC = "Oplus";
                    }
                    if (blood.equals("O-")) {
                        bloodC = "Omoins";
                    }
                    if (blood.equals("AB+")) {
                        bloodC = "ABplus";
                    }
                    if (blood.equals("AB-")) {
                        bloodC = "ABmoins";
                    }
                    ResultSet res = null;
                    try {
                        
                        String query = "SELECT Aplus,Amoins,Bplus,Bmoins,Oplus,Omoins,ABplus,ABmoins FROM BLOODSTOCK WHERE idHospital=(SELECT idHospital FROM HOSPITAL WHERE name='"+hospital+"')";
                        res = ManageDB.fetchBloodStocks(query);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    double result = 0;
                    try {
                        result = ManageDB.fetchBlood(hospital, bloodC);
                        System.out.println(result);
                        if (result < 20 && (temp==false)) {
                            temp=true;
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Real time tracking of blood");
                            alert.setHeaderText("Critical situation : Shortage of blood " + blood);
                            alert.setContentText("The blood device is sending emails to donors ...");
                            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
                            alert.showAndWait();
                        }
                        if(result>=20)
                            temp=false;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // get current time
                    Date now = new Date();
                    try {
                    // put random number with current time
                    while (res.next()) {
                        i++;
                        Aplus += res.getDouble("Aplus");
                        Amoins += res.getDouble("Amoins");
                        Bplus += res.getDouble("Bplus");
                        Bmoins += res.getDouble("Bmoins");
                        Oplus += res.getDouble("Oplus");
                        Omoins += res.getDouble("Omoins");
                        ABplus += res.getDouble("ABplus");
                        ABmoins += res.getDouble("ABmoins");
                        
                        series1.getData().add(new XYChart.Data<>("A+", Aplus/i));
                        series1.getData().add(new XYChart.Data<>("A-", Amoins/i));
                        series1.getData().add(new XYChart.Data<>("B+", Bplus/i));
                        series1.getData().add(new XYChart.Data<>("B-", Bmoins/i));
                        series1.getData().add(new XYChart.Data<>("O+", Oplus/i));
                        series1.getData().add(new XYChart.Data<>("O-", Omoins/i));
                        series1.getData().add(new XYChart.Data<>("AB+", ABplus/i));
                        series1.getData().add(new XYChart.Data<>("AB-", Omoins/i));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(mostbloodused.FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), result));
                    if (series.getData().size() > 6) {
                        series.getData().remove(0);
                    }
                });
            }, 0, 4, TimeUnit.SECONDS);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Real time tracking of blood");
            alert.setContentText("Hospital's name or blood type is missing !");
            Image image = new Image(getClass().getResourceAsStream("no.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(64);
            imageView.setFitHeight(64);
            alert.setGraphic(imageView);
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(getClass().getResourceAsStream("bloodbankicon.png")));
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/bloodbankmenu/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.centerOnScreen();
    }

}
