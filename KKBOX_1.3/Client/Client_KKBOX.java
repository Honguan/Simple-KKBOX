import java.io.*;
import java.net.*;

public class Client_KKBOX {
static BufferedReader br;
static String servername;
static int port;
boolean isPlaying = false;
Jaco_Mp3 Music;
public Client_KKBOX() throws IOException 
{
	String str,code;
	BufferedInputStream bis;   
    BufferedReader br;
    Client C1 = new Client(servername,port);
    br = new BufferedReader(new InputStreamReader (System.in));
    while(true)
    {
        str = C1.receive();
        code = str.substring(0,1);
        str = str.substring(1);
        if(code.equals("2")) 
        {
            System.out.println(str);
            str = br.readLine();
            C1.send(str);
        }
		
        else 
        {
            if(code.equals("3")) 
            {
				long fileSize = Long.parseLong(C1.receive());
				byte[] inByte = new byte[8192];
				int in;
				long receivedSize = 0;
				FileOutputStream fos = new FileOutputStream("temp.mp3");
            	if(isPlaying)Music.stop();
				while(receivedSize < fileSize)
				{
					in = C1.instream.read(inByte);
					fos.write(inByte, 0, in);
					receivedSize += in;
					//System.out.println("in = "+in);
				}
				fos.close();
				//FileInputStream fis = new FileInputStream("temp.mp3");
				//bis = new BufferedInputStream(fis);
				Music = new Jaco_Mp3("temp.mp3");
				Music.play();
				isPlaying = true;
            }
			else if(code.equals("5"))
			{
				if(str.equals("/pause"))
				{   
					if (Music != null)
					Music.pause();
				}
				else if(str.equals("/resume"))
				{   
					if (Music != null)
					Music.resume();
					isPlaying = true;
				}
				else if(str.equals("/over"))
				{   
					if (Music != null)
					Music.stop();
					C1.close();
					break;
				}
			}
            else
                System.out.println(str);
        }
    
    }
}
public static void main(String args[]) throws IOException {
		if (args.length < 2){
			System.out.println("USAGE: java Client_KKBOX [servername] [port]");	
			System.exit(1);
		}

		servername= args[0];
		port=Integer.parseInt(args[1]);
		Client_KKBOX ClientStart=new Client_KKBOX();
  }
}
