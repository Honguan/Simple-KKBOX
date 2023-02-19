import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Server_KKBOX 
{
static String str;
static int point = -1;
boolean isPlaying = false;
public Server_KKBOX() throws IOException 
	{
		Server S1 = new Server(3200);
		S1.accept();
		File f = new File("music");
		File[] musicName = f.listFiles();
		while(true)
		{
			S1.send("1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			for(int i = 0; i < f.listFiles().length; i++)
			{
				//System.out.println("["+i+"]"+musicName[i].getName());
				S1.send("1["+i+"]"+musicName[i].getName());
			}
			S1.send("1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			S1.send("2/over(�פ�{��)�A/pause(�Ȱ�)�A/resume(�~��έ��Y����)�A/next(�U�@��)�A/back(�W�@��)�A/rand(�H������)\n�п�ܰʧ@�ο�J���ֽs��:");
			str = S1.receive();
			System.out.println(str);
			Pattern pattern = Pattern.compile("[0-9]*");
			if(pattern.matcher(str).matches())
            {    
				if(Integer.parseInt(str) > f.listFiles().length)
				{
					S1.send("1�п�J�s�b�����ֽs��");
					continue;
				}
				point = Integer.parseInt(str);
				byte[] inByte = new byte[8192];
				int in;
				FileInputStream fis = new FileInputStream(musicName[point]);
				S1.send("3");
				S1.send(String.valueOf(musicName[point].length()));
				while((in = fis.read(inByte)) > 0)
				{	
					S1.outstream.write(inByte);
					//System.out.println("in = "+in);						
				}  
				fis.close();
				isPlaying = true;
            }
			else if(str.equals("/next") || str.equals("/back") || str.equals("/rand") )
            {    
				if(str.equals("/next"))
				{    
					point = point+1;
					if(point >= f.listFiles().length) point = 0;
				}
				if(str.equals("/back"))
				{    
					point = point-1;
					if(point <= 0) point = f.listFiles().length-1;
				}
				if(str.equals("/rand"))
				{    
					Random rand = new Random();
					point = rand.nextInt(f.listFiles().length);
					if(point >= f.listFiles().length) point = 0;
				}
				byte[] inByte = new byte[8192];
				int in;
				FileInputStream fis = new FileInputStream(musicName[point]);
				S1.send("3");
				S1.send(String.valueOf(musicName[point].length()));
				while((in = fis.read(inByte)) > 0)
				{	
					S1.outstream.write(inByte);
					//System.out.println("in = "+in);						
				}  
				fis.close();
				isPlaying = true;
            }
			else if(str.equals("/pause") || str.equals("/resume"))
            {   
				if(!isPlaying){
					S1.send("1�Х���ܭ���");
					continue;
				}
				S1.send("5"+str);
            }
			else if(str.equals("/over"))
            {    
				S1.send("5"+str);
				S1.close();
                break;
            }
			else {
				S1.send("1�п�J���T�����ֽs���A�άO���֫��O");
			}
		}
	}
public static void main(String args[]) throws IOException
{
    Server_KKBOX ServerStart=new Server_KKBOX();
}
}
//FileOutputStream fos = new FileOutputStream("b.mp3");
//
//while(messagein != -1){ 
//	messagein = instream.readInt();	
//	if(messagein != -1)
//		fos.write(messagein);
//} 
//fos.close();
//	MP3 a = new MP3("b.mp3");
//	a.playsong();