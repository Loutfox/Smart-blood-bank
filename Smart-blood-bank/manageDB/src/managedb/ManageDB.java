/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;

/**
 *
 * @author asus
 */
public class ManageDB {

    static Connection conex;
    static PreparedStatement ps;

    public static boolean createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conex = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank", "root", "");
        return true;
    }

    public static boolean insertDonor(String lastName, String firstName, int Age, String Gender, String bloodGroup, String phoneNumber, String City) throws ClassNotFoundException, SQLException {
        boolean result = false;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("INSERT INTO Donor(lastName,firstName,Age,Gender,bloodGroup,phoneNumber,City) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, lastName);
            ps.setString(2, firstName);
            ps.setDouble(3, Age);
            ps.setString(4, Gender);
            ps.setString(5, bloodGroup);
            ps.setString(6, phoneNumber);
            ps.setString(7, City);
            int update = ps.executeUpdate();
            if (update == 0) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    public static ResultSet fetchDonors() throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            Statement statement = conex.createStatement();
            rs = statement.executeQuery("SELECT * FROM DONOR");
        }
        return rs;
    }

    public static boolean deleteDonor(int idDonor) throws ClassNotFoundException, SQLException {
        boolean result = false;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("DELETE FROM DONOR WHERE idDonor=?");
            ps.setInt(1, idDonor);
            int update = ps.executeUpdate();
            if (update == 0) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    public static boolean deleteHospital(int idHospital) throws ClassNotFoundException, SQLException {
        boolean result = false;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("DELETE FROM HOSPITAL WHERE idHospital=?");
            ps.setInt(1, idHospital);
            int update = ps.executeUpdate();
            if (update == 0) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    public static ResultSet fetchAdmin(String username, String password) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("SELECT idHospital FROM ADMINISTRATOR WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
        }
        return rs;
    }

    public static ResultSet fetchSudo(String username, String password) throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("SELECT * FROM SUPERADMIN WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
        }
        return rs;
    }

    public static boolean updateSudo(String username, String actualPassword, String newPassword) throws ClassNotFoundException, SQLException {
        boolean result = false;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("UPDATE SUPERADMIN SET password=? WHERE username=? and password=?");
            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.setString(3, actualPassword);
            int update = ps.executeUpdate();
            if (update == 0) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    public static boolean updateBloodStock(int idHospital, double Aplus, double Amoins, double Bplus, double Bmoins, double Oplus, double Omoins, double ABplus, double ABmoins) throws ClassNotFoundException, SQLException {
        boolean result = false;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("UPDATE BLOODSTOCK SET Aplus=?,Amoins=?,Bplus=?,Bmoins=?,Oplus=?,Omoins=?,ABplus=?,ABmoins=? WHERE idHospital=?");
            ps.setDouble(1, Aplus);
            ps.setDouble(2, Amoins);
            ps.setDouble(3, Bplus);
            ps.setDouble(4, Bmoins);
            ps.setDouble(5, Oplus);
            ps.setDouble(6, Omoins);
            ps.setDouble(7, ABplus);
            ps.setDouble(8, ABmoins);
            ps.setInt(9, idHospital);
            int update = ps.executeUpdate();
            if (update == 0) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    public static boolean insertHospital(String name, String address, String phoneNumber, String email, String city) throws ClassNotFoundException, SQLException {
        boolean result = false;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("INSERT INTO Hospital(name,address,phoneNumber,email,city) VALUES(?,?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phoneNumber);
            ps.setString(4, email);
            ps.setString(5, city);
            int update = ps.executeUpdate();
            if (update == 0) {
                result = false;
            } else {
                result = true;
            }
        }
        return result;
    }

    public static ResultSet fetchHospitals() throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            Statement statement = conex.createStatement();
            rs = statement.executeQuery("SELECT * FROM HOSPITAL");
        }
        return rs;
    }

    public static double fetchBlood(String name, String bloodType) throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        double result = 0;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("SELECT " + bloodType + " from BLOODSTOCK WHERE idHospital=(SELECT idHospital FROM HOSPITAL WHERE name=?)");
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.next();
            result = rs.getDouble(bloodType);

        }
        return result;
    }

    public static ResultSet fetchBloodStocks(String query) throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            Statement statement = conex.createStatement();
            rs = statement.executeQuery(query);
        }
        return rs;
    }

    public static String fetchCity(int id) throws SQLException, ClassNotFoundException {
        String city = "";
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("SELECT city from HOSPITAL WHERE idHospital=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            city = rs.getString("city");

        }
        return city;
    }

    public static int fetchHospital(int bsID) throws SQLException, ClassNotFoundException {
        int id = 0;
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("SELECT idHospital from BLOODSTOCK WHERE idBloodStock=?");
            ps.setInt(1, bsID);
            rs = ps.executeQuery();
            rs.next();
            id = rs.getInt("idHospital");

        }
        return id;
    }

    public static String fetchHospitalName(int id) throws SQLException, ClassNotFoundException {
        String name = "";
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("SELECT name from HOSPITAL WHERE idHospital=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            name = rs.getString("name");

        }
        return name;
    }

    public static ResultSet fetchEmails(String city, String bloodGroup) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        if (ManageDB.createConnection()) {
            ps = conex.prepareStatement("SELECT gmail from EMAIL WHERE idDonor in(SELECT idDonor from DONOR WHERE City=? and bloodGroup=?)");
            ps.setString(1, city);
            ps.setString(2, bloodGroup);
            rs = ps.executeQuery();;
        }
        return rs;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //System.out.println(ManageDB.createConnection());
        //System.out.println(ManageDB.insertDonor("ok", "Loutfi", 21, "Male", "B-", "0610108175", "Rabat"));
        /*ResultSet rs = ManageDB.fetchAdmin("FoxLout", "fox0610108175");
        while(rs.next())
            System.out.println(rs.getInt("idHospital"));*/
        //ResultSet rs = ManageDB.fetchSudo("Loutfox", "fox0610108175");
        //System.out.println(rs.first());
        // System.out.println(ManageDB.updateSudo("Loutfox", "fox0610108175", "0610108175"));
        //System.out.println(ManageDB.deleteDonor(13));
        //System.out.println(ManageDB.deleteHospital(1));
        //System.out.println(ManageDB.updateBloodStock(1, 20, 20, 20, 20, 20, 20, 20, 20));
        //System.out.println(ManageDB.fetchBlood("Moulay youssef", "Aplus"));
        //System.out.println(ManageDB.fetchCity(1));
        int idHospital = ManageDB.fetchHospital(1);
       System.out.println(idHospital);
       System.out.println(ManageDB.fetchHospitalName(idHospital));
       String city = ManageDB.fetchCity(idHospital);
       System.out.println(city);
       ResultSet rs = ManageDB.fetchEmails(city,"A-");
       while(rs.next())
           System.out.println(rs.getString("gmail"));
        /*String query = "SELECT Aplus,Amoins,Bplus,Bmoins,Oplus,Omoins,ABplus,ABmoins FROM BLOODSTOCK WHERE idHospital=1";
        ResultSet result = ManageDB.fetchBloodStocks(query);
        while(result.next())
        {
            System.out.println(result.getDouble("Aplus"));
            System.out.println(result.getDouble("Amoins"));
            System.out.println(result.getDouble("Bplus"));
            System.out.println(result.getDouble("Bmoins"));
            System.out.println(result.getDouble("Oplus"));
            System.out.println(result.getDouble("Omoins"));
            System.out.println(result.getDouble("ABplus"));
            System.out.println(result.getDouble("ABmoins"));
            
        }*/
    }

}
