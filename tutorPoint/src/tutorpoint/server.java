/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;
import javax.swing.JOptionPane;

// Under the assumption that the user is using linux based operating system .........

/**
 *
 * @author Avishek
 */

public class server {
    
    public static String path;
    public static String user = "", password = "";
    public static String IPAddress = "";
    
    public static ServerSocketChannel serverSocketChannel;
    
    static{
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main( String args[] ) throws IOException, ClassNotFoundException
    {
        ServerSocket ss = new ServerSocket( 3000 );
        Runtime run = Runtime.getRuntime();
        try {
            user = JOptionPane.showInputDialog("Enter user name");
            if (user != null) {
                password = JOptionPane.showInputDialog("Enter root password");
                String command = "echo " + password + " | sudo -S /etc/init.d/vsftpd restart";
                String[] cmd = {
                    "/bin/bash",
                    "-c",
                    command
                };
                Process proc = run.exec(cmd, null, new java.io.File("/home/" + user));
                Scanner scan = new Scanner(proc.getInputStream());
                proc.waitFor();
                if (!scan.hasNext()) {
                    JOptionPane.showMessageDialog(null, "Sorry you entered wrong username or password.");
                    System.exit(0);
                }
            } else {
                JOptionPane.showMessageDialog(null, "You must specify a user name");
                System.exit(0);
            }
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(null, "Please check that you have entered right username and password and that your PC conforms to requirements.");
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e + "Error Occurred");
            System.exit(0);
        }
        // replace 127.0.0.1 with network IP address here .........
        try {
                // TODO add your handling code here:
                InetAddress addr = server.getFirstNonLoopbackAddress(true, false);
                IPAddress = addr.toString().substring(1);
                JOptionPane.showMessageDialog(null, "IP Address is: " + IPAddress + "\nPort is: 3000");
            } catch (SocketException ex) {
                System.out.println(ex);
            }
        path = "ftp://" + user + ":" + password + "@" + IPAddress + "/TutorVideo/";
        System.out.println(path);
        while(true){
            Thread t = new listenThread(ss.accept( ));
            t.start();
        }
    }
    
        private static InetAddress getFirstNonLoopbackAddress(boolean preferIpv4, boolean preferIPv6) throws SocketException {
        Enumeration en = NetworkInterface.getNetworkInterfaces();
        while (en.hasMoreElements()) {
            NetworkInterface i = (NetworkInterface) en.nextElement();
            for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements();) {
                InetAddress addr = (InetAddress) en2.nextElement();
                if (!addr.isLoopbackAddress()) {
                    if (addr instanceof Inet4Address) {
                        if (preferIPv6) {
                            continue;
                        }
                        return addr;
                    }
                    if (addr instanceof Inet6Address) {
                        if (preferIpv4) {
                            continue;
                        }
                        return addr;
                    }
                }
            }
        }
        return null;
    }
    
}

class listenThread extends Thread{
    
    public java.net.Socket sock;
    public static final Connection c = mySqlConnect.connection("root","uselesscoder");
    
    public listenThread(Socket sock){
        this.sock = sock;
    }
    
    @Override
    public void run(){
        System.out.println("Client connected.");
        ObjectOutputStream out = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new ObjectOutputStream(sock.getOutputStream());
            while(true){
                String query = br.readLine();
                if(query == null)
                    break;
                System.out.println(query);
                if(query != null &&  query.substring(0,4).compareTo("@@@@") == 0){
                    // Update Query .....
                    PreparedStatement pst = c.prepareStatement(query.substring(4));
                    pst.executeUpdate();
                    ArrayList<ArrayList<String> > result = new ArrayList<>();
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add("DONE");
                    result.add(temp);
                    out.writeObject(result);
                    out.flush();
                }
                else if(query != null && query.substring(0,4).compareTo("####") == 0){
                    // Execute Query ....
                    PreparedStatement pst = c.prepareStatement(query.substring(4));
                    ResultSet rs = pst.executeQuery();
                    ArrayList<ArrayList<String> > result = new ArrayList<>();
                    while(rs.next()){
                        ResultSetMetaData meta = rs.getMetaData();
                        ArrayList<String> temp = new ArrayList<>();
                        int colCount = meta.getColumnCount();
                        for(int i = 1 ; i <= colCount ; i ++){
                            temp.add(rs.getString(i));
                        }
                        result.add(temp);
                    }
                    out.writeObject(result);
                    out.flush();
                }
                else if(query != null && query.substring(0,4).compareTo("$$$$") == 0){
                    ArrayList<ArrayList<String>> rs = new ArrayList<>();
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(server.path);
                    rs.add(temp);
                    out.writeObject(rs);
                    out.flush();
                }
                else if(query != null && query.substring(0,4).compareTo("!!!!") == 0){
                    String fileName = query.substring(4);
                    Thread t = new transferFile(fileName);
                    t.start();
                }
                else if(query != null && query.substring(0,4).compareTo("%%%%") == 0){
                    String fileName = query.substring(4);
                    String path = "/home/" + server.user + "/TutorVideo/" + fileName;
                    String command = "rm -f " + path;
                    Process proc = Runtime.getRuntime().exec(command);
                }
            }
        } catch (SQLException ex) {
            ArrayList<ArrayList<String> > result = new ArrayList<>();
            ArrayList<String> temp = new ArrayList<>();
            temp.add("FAIL") ;
            result.add(temp) ;
            try {
                out.writeObject(result);
                out.flush();
            } catch (IOException ex1) {
                System.out.println(ex);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}

class transferFile extends Thread{
    
    public String fileName = "";
    
    public transferFile(String fileName){
        this.fileName = fileName;
    }
    
    @Override
    public void run(){
        FileReceiver nioServer = new FileReceiver();
        SocketChannel socketChannel = nioServer.createServerSocketChannel(server.serverSocketChannel);
        String path = "/home/" + server.user + "/TutorVideo/" + this.fileName;
        if(socketChannel != null)
            nioServer.readFileFromSocket(socketChannel,path);
        else
            System.out.println("Error Occurred here ..");
    }
    
}