/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.io.Serializable;

/**
 *
 * @author asus
 */
public class bloodStock implements Serializable {
    private int id;
    private double Aplus;
    private double Amoins;
    private double Bplus;
    private double Bmoins;
    private double Oplus;
    private double Omoins;
    private double ABplus;
    private double ABmoins;

    public bloodStock(int id, double Aplus, double Amoins, double Bplus, double Bmoins, double Oplus, double Omoins, double ABplus, double ABmoins) {
        this.id = id;
        this.Aplus = Aplus;
        this.Amoins = Amoins;
        this.Bplus = Bplus;
        this.Bmoins = Bmoins;
        this.Oplus = Oplus;
        this.Omoins = Omoins;
        this.ABplus = ABplus;
        this.ABmoins = ABmoins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAplus() {
        return Aplus;
    }

    public void setAplus(double Aplus) {
        this.Aplus = Aplus;
    }

    public double getAmoins() {
        return Amoins;
    }

    public void setAmoins(double Amoins) {
        this.Amoins = Amoins;
    }

    public double getBplus() {
        return Bplus;
    }

    public void setBplus(double Bplus) {
        this.Bplus = Bplus;
    }

    public double getBmoins() {
        return Bmoins;
    }

    public void setBmoins(double Bmoins) {
        this.Bmoins = Bmoins;
    }

    public double getOplus() {
        return Oplus;
    }

    public void setOplus(double Oplus) {
        this.Oplus = Oplus;
    }

    public double getOmoins() {
        return Omoins;
    }

    public void setOmoins(double Omoins) {
        this.Omoins = Omoins;
    }

    public double getABplus() {
        return ABplus;
    }

    public void setABplus(double ABplus) {
        this.ABplus = ABplus;
    }

    public double getABmoins() {
        return ABmoins;
    }

    public void setABmoins(double ABmoins) {
        this.ABmoins = ABmoins;
    }
    
    
    
}
