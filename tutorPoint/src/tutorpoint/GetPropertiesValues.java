package tutorpoint;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shrey
 */
public class GetPropertiesValues {
    static Properties properties;
    public static void getPropValues() throws IOException{
        FileReader fileReader;
        properties=new Properties();
        String propFileName = "config.properties";
        fileReader=new FileReader(propFileName);
        if (fileReader!=null){
            try {
                properties.load(fileReader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            throw new FileNotFoundException("File Not Found");
    }
    public static String getUserName(){
        try {
            getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String user= properties.getProperty("user");
        return user;
    }
    public static String getMysqlPassword(){
        try {
            getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String password= properties.getProperty("password");
        return password;
    }
}
