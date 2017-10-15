package tutorpoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
 
public class videoSender {

    
    /**
    * Establishes a socket channel connection
    *
    * @return
    */
    String filePath ;
    String ip ;
    int port ;
    public loading load;
    
    
    videoSender( String filePath , String ip )
    {
        this.filePath = filePath ;
        this.ip = ip ;
    }
    
    public boolean startUpload( )
    {
        //videoSender nioClient = new videoSender();
        SocketChannel socketChannel = createChannel();
        if( socketChannel != null )
        {
            return sendFile(socketChannel);
        }
        return false ;
    }
    public SocketChannel createChannel() {

        SocketChannel socketChannel = null;
        try {
        socketChannel = SocketChannel.open();
        System.out.println(ip);
        SocketAddress socketAddress = new InetSocketAddress(ip, 9000);
        socketChannel.connect(socketAddress);
        
        System.out.println("Connected..Now sending the file");
        load = new loading();
        load.setVisible(true);

        } catch (IOException e) {
        e.printStackTrace();
        }
        return socketChannel;
    }


    public boolean sendFile(SocketChannel socketChannel) {
        RandomAccessFile aFile = null;
        try {
            File file = new File( filePath );
            aFile = new RandomAccessFile(file, "r");
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) > 0) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            }
            Thread.sleep(1000);
            System.out.println("End of file reached.") ;
            load.dispose();
            socketChannel.close();
            aFile.close();
            return true ;
        } catch (FileNotFoundException e) {
            return false ;
        } catch (IOException e) {
            return false ;
        } catch (InterruptedException e) {
            return false ;
        }
    }
 
}