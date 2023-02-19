

import java.net.*;
import java.io.*;
//import java.util.*;

public class Server {
  ServerSocket  SS; 
  DataOutputStream   outstream;
  DataInputStream    instream;
  Socket  socket;
public Server(int port) {
     try{
         SS = new ServerSocket(port);
         System.out.println("waiting for client to connect...");			
      }
      catch(IOException e){
           System.out.println(e.toString());
           e.printStackTrace();
           System.exit(1);
      }		
  }
  
public void accept() throws IOException
 {
    socket = SS.accept();
    instream = new DataInputStream(socket.getInputStream());
    outstream = new DataOutputStream(socket.getOutputStream());   
 } 
  
public void send(String s) throws IOException
 {
    outstream.writeUTF(s);
 } 

public String receive1() throws IOException
 {
    String messagein = instream.readUTF();
	System.out.println("message: " + messagein);
	return messagein;
 } 
 
 public String receive() throws IOException
 {
    String messagein = instream.readUTF();
	return messagein;
 } 
 
public void close() throws IOException
 {
    socket.close(); 
 }   
  
  
}
