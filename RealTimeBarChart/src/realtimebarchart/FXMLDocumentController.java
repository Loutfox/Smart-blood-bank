/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package realtimebarchart;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
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
    private BarChart<?, ?> realTimeBarChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    XYChart.Series series;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        x.setAnimated(false);
        y.setAnimated(false);
        realTimeBarChart.setAnimated(false);

    }

    @FXML
    private void track(ActionEvent event) {
        // this is used to display time in HH:mm:ss format

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // setup a scheduled executor to periodically put data into the chart
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        series = new XYChart.Series<>();
        realTimeBarChart.getData().addAll(series);

        // put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            // Update the chart
            Platform.runLater(() -> {
                
                double result = 0;
                try {
                    result = ManageDB.fetchBlood("Moulay youssef", "Aplus");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                // get current time
                Date now = new Date();
                // put random number with current time
                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), result));
            });
            System.out.println("Hello");
        }, 0, 1, TimeUnit.SECONDS);

    }

}
