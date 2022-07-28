package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Fascan
 */
public final class  PropertiesUtil {
       private static final  Properties properties = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
    // загрузка properties 
    private static void loadProperties() {
        try (FileInputStream in =new FileInputStream(PropertiesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"utils/application.properties")){
//          FileInputStream in =new FileInputStream("C:\\study\\netbeanse\\WebA\\src\\java\\utils\\application.properties")){
            properties.load(in);
        } catch (IOException e) {
            
            System.out.println(e);
            throw new RuntimeException(e);
        }
    } 
}
