/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.List;
import message.Message;

/**
 *
 * @author asus
 */
public class Hospital {

    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private bloodStock bs;
    //Sockets part
    private static ServerSocket listener=null;
    private static ObjectInputStream isObject = null;
    private static ObjectOutputStream osObject = null;
    private static BufferedWriter os = null;//sortie
    private static BufferedReader is = null;//entrée
    private static Socket socketOfServer = null;

    public Hospital(int id, String name, String address, String phoneNumber, String email, String city, bloodStock bs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.bs = bs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public bloodStock getBs() {
        return bs;
    }

    public void setBs(bloodStock bs) {
        this.bs = bs;
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
       bloodStock bs = new bloodStock(1, 30, 20, 10, 50, 20, 30, 40, 50);
       Hospital hospital = new Hospital (1, "Moulay youssef", "Akkari", "0610108175", "aliluch.loutfi@gmail.com", "Rabat", bs);
       listener = new ServerSocket(9999);
       System.out.println("Hospital running waiting for the blood device");
       socketOfServer = hospital.listener.accept();
       osObject = new ObjectOutputStream(socketOfServer.getOutputStream());
       osObject.flush();
       isObject = new ObjectInputStream(socketOfServer.getInputStream());
       os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
       is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
       
       System.out.println(is.readLine());
       os.write("Hospital : Blood device connected successfully");
       os.newLine();
       os.flush();
       System.out.println(is.readLine());
       os.write("Hospital : Roger that ! Processing...");
       os.newLine();
       os.flush();
       osObject.writeObject(bs);
       String tmp="";
       while(!(tmp=is.readLine()).equals("End"))
       {
           System.out.println(tmp);
       }
       socketOfServer.close();
       
       
       

       
       
    }
    
}
