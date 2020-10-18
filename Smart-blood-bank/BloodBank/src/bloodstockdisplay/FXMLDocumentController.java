/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodstockdisplay;

import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
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
    private BarChart<?, ?> bloodChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private FontAwesomeIconView searchIcon;
    @FXML
    private FontAwesomeIconView refreshIcon;
    
    XYChart.Series set1;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bloodChart.setManaged(false);
        bloodChart.setVisible(false);
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/bloodbankmenu/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
    }

    @FXML
    private void search(MouseEvent event) throws ClassNotFoundException, SQLException {
        String hospital = searchBar.getText();
        if(!hospital.equals(""))
        {
        String query = "SELECT * FROM BLOODSTOCK WHERE idHospital=(SELECT idHospital FROM HOSPITAL WHERE NAME='"+hospital+"')";
        ResultSet rs = ManageDB.fetchBloodStocks(query);
        double Aplus=0;
        double Amoins=0;
        double Bplus=0;
        double Bmoins=0;
        double Oplus=0;
        double Omoins=0;
        double ABplus=0;
        double ABmoins=0;
        while(rs.next())
        {
        int idBloodStock = rs.getInt("idBloodStock");
        int idHospital = rs.getInt("idHospital");
        Aplus = rs.getDouble("Aplus");
        Amoins = rs.getDouble("Amoins");
        Bplus = rs.getDouble("Bplus");
        Bmoins = rs.getDouble("Bplus");
        Oplus = rs.getDouble("Oplus");
        Omoins = rs.getDouble("Oplus");
        ABplus = rs.getDouble("ABplus");
        ABmoins = rs.getDouble("ABplus");
        }
        set1 = new XYChart.Series<>();
        set1.setName("Blood");
        set1.getData().add(new XYChart.Data("A+",Aplus));
        set1.getData().add(new XYChart.Data("A-",Amoins));
        set1.getData().add(new XYChart.Data("B+",Bplus));
        set1.getData().add(new XYChart.Data("B-",Bmoins));
        set1.getData().add(new XYChart.Data("O+",Oplus));
        set1.getData().add(new XYChart.Data("O-",Omoins));
        set1.getData().add(new XYChart.Data("AB+",ABplus));
        set1.getData().add(new XYChart.Data("AB-",ABmoins));
        bloodChart.getData().addAll(set1);
        bloodChart.setManaged(true);
        bloodChart.setVisible(true);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setContentText("Hospital's name is missing !");
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
    private void refresh(MouseEvent event) throws ClassNotFoundException, SQLException {
        bloodChart.getData().clear();
        set1.getData().clear();
        String hospital = searchBar.getText();
        String query = "SELECT * FROM BLOODSTOCK WHERE idHospital=(SELECT idHospital FROM HOSPITAL WHERE NAME='"+hospital+"')";
        ResultSet rs = ManageDB.fetchBloodStocks(query);
        double Aplus=0;
        double Amoins=0;
        double Bplus=0;
        double Bmoins=0;
        double Oplus=0;
        double Omoins=0;
        double ABplus=0;
        double ABmoins=0;
        while(rs.next())
        {
        int idBloodStock = rs.getInt("idBloodStock");
        int idHospital = rs.getInt("idHospital");
        Aplus = rs.getDouble("Aplus");
        Amoins = rs.getDouble("Amoins");
        Bplus = rs.getDouble("Bplus");
        Bmoins = rs.getDouble("Bmoins");
        Oplus = rs.getDouble("Oplus");
        Omoins = rs.getDouble("Omoins");
        ABplus = rs.getDouble("ABplus");
        ABmoins = rs.getDouble("ABmoins");
        }
        set1 = new XYChart.Series<>();
        set1.setName("Blood");
        set1.getData().add(new XYChart.Data("A+",Aplus));
        set1.getData().add(new XYChart.Data("A-",Amoins));
        set1.getData().add(new XYChart.Data("B+",Bplus));
        set1.getData().add(new XYChart.Data("B-",Bmoins));
        set1.getData().add(new XYChart.Data("O+",Oplus));
        set1.getData().add(new XYChart.Data("O-",Omoins));
        set1.getData().add(new XYChart.Data("AB+",ABplus));
        set1.getData().add(new XYChart.Data("AB-",ABmoins));
        bloodChart.getData().addAll(set1);
        bloodChart.setManaged(true);
        bloodChart.setVisible(true);
        
        
    }
    
}
