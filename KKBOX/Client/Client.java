

import java.io.*;
import java.net.*;

public class Client {
  Socket socket;
  DataOutputStream  outstream;
  DataInputStream  instream;
  String messagein;
  static String messageout;
				
public Client(String ip, int port) 
  {
     try{
         socket=new Socket(InetAddress.getByName(ip),port);
         outstream = new DataOutputStream(socket.getOutputStream());
         instream=new DataInputStream(socket.getInputStream());
      }
      catch(IOException e){
         System.out.println("IOException when connecting Server!"); 
      }
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
public int receive_Int() throws IOException
{
	int temp = instream.readInt();
	return temp;
}
public void close() throws IOException
 {
    socket.close(); 
 }   
  
}
