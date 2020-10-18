/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blooddevice;

import hospital.bloodStock;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import mailsender.mailSender;
import managedb.ManageDB;
import message.Message;

/**
 *
 * @author asus
 */
public class bloodDevice {

    private final int numero;
    static private int nb = 0;
    private bloodStock bs;
    final String serverIp = "127.0.0.1";
    final int serverPort = 9999;
    private static Socket socketOfClient = null;
    private static ObjectOutputStream osObject = null;//sortie
    private static ObjectInputStream isObject = null;//entrée
    private static BufferedWriter os = null;//sortie
    private static BufferedReader is = null;//entrée

    public bloodDevice() {
        numero = ++nb;
    }

    public boolean connection() throws IOException {
        socketOfClient = new Socket(serverIp, serverPort);
        System.out.println("Connected successfully !");
        osObject = new ObjectOutputStream(socketOfClient.getOutputStream());
        osObject.flush();
        isObject = new ObjectInputStream(socketOfClient.getInputStream());
        os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
        is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
        return true;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
        bloodDevice bd = new bloodDevice();
        double Aplus, Amoins, Bplus, Bmoins, Oplus, Omoins, ABplus, ABmoins;
        if (bd.connection()) {
            System.out.println("Blood Device : Trying to connect to the hospital !");
            os.write("Blood Device : Trying to connect to the hospital !");
            os.newLine();
            os.flush();
            System.out.println(is.readLine());
            System.out.println("Blood Device : Need to be connected to your blood stock to do my job !");
            os.write("Blood Device : Need to be connected to your blood stock to do my job !");
            os.newLine();
            os.flush();
            System.out.println(is.readLine());
            bd.bs = (bloodStock) isObject.readObject();
            System.out.println("Connected sucessfully to the Blood Stock");
            String str1 = "";
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\asus\\Documents\\NetBeansProjects\\BloodDevice\\src\\blooddevice\\bloodUsage.txt"));
            str1 = br.readLine();
            while (!str1.equals("End")) {
                String[] array = str1.split(", ");
                Aplus = Double.parseDouble(array[0]);
                Amoins = Double.parseDouble(array[1]);
                Bplus = Double.parseDouble(array[2]);
                Bmoins = Double.parseDouble(array[3]);
                Oplus = Double.parseDouble(array[4]);
                Omoins = Double.parseDouble(array[5]);
                ABplus = Double.parseDouble(array[6]);
                ABmoins = Double.parseDouble(array[7]);
                if ((Aplus < 20) || (Amoins < 20) || (Bplus < 20) || (Bmoins < 20) || (Oplus < 20) || (Omoins < 20) || (ABplus < 20) || (ABmoins < 20)) {
                    if (Aplus < 20) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        formatter.format(date);
                        os.write("ALERT at " + date + " : Shortage of blood A+ !");
                        os.newLine();
                        os.flush();
                        int idHospital = ManageDB.fetchHospital(bd.bs.getId());
                        String city = ManageDB.fetchCity(idHospital);
                        ResultSet rs = ManageDB.fetchEmails(city, "A+");
                        String hospitalName = ManageDB.fetchHospitalName(idHospital);
                        while (rs.next()) {
                            mailSender.sendMail(rs.getString("gmail"), hospitalName);
                        }
                    }
                    if (Amoins < 20) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        formatter.format(date);
                        os.write("ALERT at " + date + " : Shortage of blood A- !");
                        os.newLine();
                        os.flush();
                        int idHospital = ManageDB.fetchHospital(bd.bs.getId());
                        String city = ManageDB.fetchCity(idHospital);
                        ResultSet rs = ManageDB.fetchEmails(city, "A-");
                        String hospitalName = ManageDB.fetchHospitalName(idHospital);
                        while (rs.next()) {
                            mailSender.sendMail(rs.getString("gmail"), hospitalName);
                        }
                    }
                    if (Bplus < 20) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        formatter.format(date);
                        os.write("ALERT at " + date + " : Shortage of blood B+ !");
                        os.newLine();
                        os.flush();
                        int idHospital = ManageDB.fetchHospital(bd.bs.getId());
                        String city = ManageDB.fetchCity(idHospital);
                        ResultSet rs = ManageDB.fetchEmails(city, "B+");
                        String hospitalName = ManageDB.fetchHospitalName(idHospital);
                        while (rs.next()) {
                            mailSender.sendMail(rs.getString("gmail"), hospitalName);
                        }
                    }
                    if (Bmoins < 20) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        formatter.format(date);
                        os.write("ALERT at " + date + " : Shortage of blood B- !");
                        os.newLine();
                        os.flush();
                        int idHospital = ManageDB.fetchHospital(bd.bs.getId());
                        String city = ManageDB.fetchCity(idHospital);
                        ResultSet rs = ManageDB.fetchEmails(city, "B-");
                        String hospitalName = ManageDB.fetchHospitalName(idHospital);
                        while (rs.next()) {
                            mailSender.sendMail(rs.getString("gmail"), hospitalName);
                        }
                    }
                    if (Oplus < 20) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        formatter.format(date);
                        os.write("ALERT at " + date + " : Shortage of blood O+ !");
                        os.newLine();
                        os.flush();
                        int idHospital = ManageDB.fetchHospital(bd.bs.getId());
                        String city = ManageDB.fetchCity(idHospital);
                        ResultSet rs = ManageDB.fetchEmails(city, "O+");
                        String hospitalName = ManageDB.fetchHospitalName(idHospital);
                        while (rs.next()) {
                            mailSender.sendMail(rs.getString("gmail"), hospitalName);
                        }
                    }
                    if (Omoins < 20) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        formatter.format(date);
                        os.write("ALERT at " + date + " : Shortage of blood O- !");
                        os.newLine();
                        os.flush();
                        int idHospital = ManageDB.fetchHospital(bd.bs.getId());
                        String city = ManageDB.fetchCity(idHospital);
                        ResultSet rs = ManageDB.fetchEmails(city, "O-");
                        String hospitalName = ManageDB.fetchHospitalName(idHospital);
                        while (rs.next()) {
                            mailSender.sendMail(rs.getString("gmail"), hospitalName);
                        }
                    }
                    if (ABplus < 20) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        formatter.format(date);
                        os.write("ALERT at " + date + " : Shortage of blood AB+ !");
                        os.newLine();
                        os.flush();
                        int idHospital = ManageDB.fetchHospital(bd.bs.getId());
                        String city = ManageDB.fetchCity(idHospital);
                        ResultSet rs = ManageDB.fetchEmails(city, "AB+");
                        String hospitalName = ManageDB.fetchHospitalName(idHospital);
                        while (rs.next()) {
                            mailSender.sendMail(rs.getString("gmail"), hospitalName);
                        }
                    }
                    if (ABmoins < 20) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                        Date date = new Date(System.currentTimeMillis());
                        formatter.format(date);
                        os.write("ALERT at " + date + " : Shortage of blood AB- !");
                        os.newLine();
                        os.flush();
                        int idHospital = ManageDB.fetchHospital(bd.bs.getId());
                        String city = ManageDB.fetchCity(idHospital);
                        ResultSet rs = ManageDB.fetchEmails(city, "AB-");
                        String hospitalName = ManageDB.fetchHospitalName(idHospital);
                        while (rs.next()) {
                            mailSender.sendMail(rs.getString("gmail"), hospitalName);
                        }
                    }
                    ManageDB.updateBloodStock(bd.bs.getId(), Aplus, Amoins, Bplus, Bmoins, Oplus, Omoins, ABplus, ABmoins);
                    str1 = br.readLine();
                } else {
                    ManageDB.updateBloodStock(bd.bs.getId(), Aplus, Amoins, Bplus, Bmoins, Oplus, Omoins, ABplus, ABmoins);
                    str1 = br.readLine();
                    Thread.sleep(7000);
                }
            }
            os.write("End");
            os.newLine();
            os.flush();
            br.close();

        } else {

        }
    }

}
