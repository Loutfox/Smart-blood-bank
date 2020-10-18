/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mostbloodused;

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
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
    private BarChart<?, ?> bloodChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    int i = 0;
    double Aplus;
    double Amoins;
    double Bplus;
    double Bmoins;
    double Oplus;
    double Omoins;
    double ABplus;
    double ABmoins;
    XYChart.Series series;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        // setup a scheduled executor to periodically put data into the chart
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        series = new XYChart.Series<>();
        series.setName("Blood");
        bloodChart.getData().addAll(series);
        // put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            // Update the chart
            Platform.runLater(() -> {
                ResultSet result = null;
                try {
                    String query = "SELECT Aplus,Amoins,Bplus,Bmoins,Oplus,Omoins,ABplus,ABmoins FROM BLOODSTOCK WHERE idHospital=1";
                    result = ManageDB.fetchBloodStocks(query);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                // get current time
                Date now = new Date();
                try {
                    // put random number with current time
                    while (result.next()) {
                        i++;
                        Aplus += result.getDouble("Aplus");
                        Amoins += result.getDouble("Amoins");
                        Bplus += result.getDouble("Bplus");
                        Bmoins += result.getDouble("Bmoins");
                        Oplus += result.getDouble("Oplus");
                        Omoins += result.getDouble("Omoins");
                        ABplus += result.getDouble("ABplus");
                        ABmoins += result.getDouble("ABmoins");
                        
                        series.getData().add(new XYChart.Data<>("Aplus", Aplus/i));
                        series.getData().add(new XYChart.Data<>("Amoins", Amoins/i));
                        series.getData().add(new XYChart.Data<>("Bplus", Bplus/i));
                        series.getData().add(new XYChart.Data<>("Bmoins", Bmoins/i));
                        series.getData().add(new XYChart.Data<>("Oplus", Oplus/i));
                        series.getData().add(new XYChart.Data<>("Omoins", Omoins/i));
                        series.getData().add(new XYChart.Data<>("ABplus", ABplus/i));
                        series.getData().add(new XYChart.Data<>("ABmoins", ABmoins/i));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }, 0, 8, TimeUnit.SECONDS);
    }

}
