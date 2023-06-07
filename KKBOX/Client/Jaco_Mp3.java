import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import jaco.mp3.player.MP3Player;
//import com.mpatric.mp3agic.Mp3File;
// com.stefan.jaco.JacoMP3Player;

public class Jaco_Mp3 {
	String filePath;
	MP3Player mp3Player;
	boolean isPlaying = false;
	BufferedInputStream bis;
	
	public Jaco_Mp3(String filePath) {
        this.filePath = filePath;
    }
	public void play() {
        if (!isPlaying) {
			try {
				mp3Player = new MP3Player(new File(filePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			new Thread() {
            public void run() {
                try { mp3Player.play(); }
                catch (Exception e) { System.out.println(e); }
				}
			}.start();
			isPlaying = true;
		}
	}
	public void pause(){
		mp3Player.pause();
		isPlaying = false;
	}
	public void resume(){
		mp3Player.play();
		isPlaying = true;
	}
	public void stop(){
		if (mp3Player != null)
		mp3Player.stop();
	}
}
