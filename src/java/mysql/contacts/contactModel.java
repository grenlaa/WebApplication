/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql.contacts;

import java.io.Serializable;

/**
 *
 * @author Fascan
 */
public class contactModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String FIO;
    private String address;
    private String number;

    public contactModel() {
    }

    public contactModel(String FIO, String address, String number) {
        this.FIO = FIO;
        this.address = address;
        this.number = number;
    }

    public contactModel(int id, String FIO, String address, String number) {
        this.id = id;
        this.FIO = FIO;
        this.address = address;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getnumber() {
        return number;
    }

    public void setnumber(String number) {
        this.number = number;
    }

}
