/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class PropertiesReader {
    private static PropertiesReader pr;
    Properties prop;

    private PropertiesReader() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("db.properties"));
        } catch (IOException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PropertiesReader getPr() {
        if(pr == null)
            pr = new PropertiesReader();
        return pr;
    }
    public String getDriver(){
        return prop.getProperty("driver");
    }
    
    public String getUrl(){
        return prop.getProperty("url");
    }
    public String getUser(){
        return prop.getProperty("user");
    }
    
    public String getPassword(){
        return prop.getProperty("password");
    }
    
}
