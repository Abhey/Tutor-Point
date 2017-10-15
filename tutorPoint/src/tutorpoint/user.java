/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorpoint;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Avishek
 */
public class user 
{
    public String ip ;
    public int port ;
    public String email ;
    public Socket s ;
    public int type ;
    
    private ObjectInputStream in;
    private PrintStream p;
    
    user( String email , int type ) throws IOException
    {
        this.s = new Socket( "127.0.0.1" , 3000 ) ;
        this.email = email ;
        this.type = type ;
        this.p =  new PrintStream(s.getOutputStream());
        this.in = new ObjectInputStream(s.getInputStream());
    }
    
    user(String ip,String email ,int type) throws IOException
    {
        this.s = new Socket(ip , 3000);
        this.email = email;
        this.type = type;
        this.p =  new PrintStream(s.getOutputStream());
        this.in = new ObjectInputStream(s.getInputStream());
        this.ip = ip;
        this.port = 3000;
    }
    
    public ArrayList<ArrayList<String>> sendQuery( String query , int type ) throws IOException
    {
        ArrayList<ArrayList<String>> rs = new ArrayList<>();
        if(type == 1){
            p.println("@@@@" + query );
            try {
                rs = (ArrayList<ArrayList<String>>) in.readObject();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        }
        if(type == 2){
            p.println("####" + query);
            try {
                rs = (ArrayList<ArrayList<String>>) in.readObject();
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        }
        if(type == 3){
            p.println("$$$$");
            try{
                rs = (ArrayList<ArrayList<String>>) in.readObject();
            }catch(ClassNotFoundException  ex){
                System.out.println(ex);
            }
        }
        if(type == 4){
            p.println("!!!!" + query);
        }
        if(type == 5){
            p.println("%%%%"+query);
        }
        return rs;
    }
    
}
