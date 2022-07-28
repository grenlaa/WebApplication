/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mysql.contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class contactCRUD {
    //
    //
    // Добавил библиотеку postgresql-42.4.0.jar
    // использовал драйвер org.postgresql.Driver
    //
    //
    // БД таблица-contact
    // столбцы id_cont,fio,numbers,address     
    //    
    private static String url = "jdbc:postgresql://localhost:5432/contacts";
    private static String username = "postgres";
    private static String password = "admin";

    public static ArrayList<contactModel> select() {

        ArrayList<contactModel> contacts = new ArrayList<contactModel>();
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try ( Connection conn = DriverManager.getConnection(url, username, password)) {

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM contact");
                while (resultSet.next()) {

                    int id = resultSet.getInt(1);
                    String FIO = resultSet.getString(2);
                    String number = resultSet.getString(3);
                    String address = resultSet.getString(4);
                    contactModel contact = new contactModel(id, FIO, address, number);
                    contacts.add(contact);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return contacts;
    }

    public static contactModel selectOne(int id) {

        contactModel contact = null;
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try ( Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "SELECT * FROM contact WHERE id_cont=?";
                try ( PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int contid = resultSet.getInt(1);
                        String FIO = resultSet.getString(2);
                        String number = resultSet.getString(3);
                        String address = resultSet.getString(4);
                        contact = new contactModel(contid, FIO, address, number);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return contact;
    }

    public static int insert(contactModel contact) {

        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try ( Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "INSERT INTO contact (fio, address, numbers) Values (?, ?, ?)";
                try ( PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, contact.getFIO());
                    preparedStatement.setString(2, contact.getaddress());
                    preparedStatement.setString(3, contact.getnumber());

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(contactModel contact) {

        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try ( Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "UPDATE contact SET fio = ?, address = ?, numbers=? WHERE id_cont = ?";
                try ( PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, contact.getFIO());
                    preparedStatement.setString(2, contact.getaddress());
                    preparedStatement.setString(3, contact.getnumber());
                    preparedStatement.setInt(4, contact.getId());

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int delete(int id) {

        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try ( Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "DELETE FROM contact WHERE id_cont = ?";
                try ( PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }
}
