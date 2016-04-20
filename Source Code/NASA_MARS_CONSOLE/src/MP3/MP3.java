package MP3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;


public class MP3 {
    private String filename;
    private Player player; 
    public static int flag = 0;

    // constructor that takes the name of an MP3 file
    public MP3(String filename) {
        this.filename = filename;
    }

    public void close() { 
    	if (player != null) {
    		player.close(); 
    		flag = 0;
    	}
    }

    // play the MP3 file to the sound card
	public void play() {
		if (flag == 0) {

			try {
				FileInputStream fis = new FileInputStream(filename);
				BufferedInputStream bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} catch (Exception e) {
				System.out.println("Problem playing file " + filename);
				System.out.println(e);
			}

			// run in new thread to play in background
			flag = 1;
			new Thread() {
				public void run() {
					try {
						player.play();
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}.start();
		}
       




    }
    
	// public static void main(String[] args) {
	// MP3 mp3 = new MP3("lib/alarm.mp3");
	// mp3.play();
	// }
}

