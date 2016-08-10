import java.util.ArrayList;

import com.wrapper.spotify.models.Track;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaController {

	private static MediaController controller;
	private ArrayList<MediaPlayer> queue;
	
	public static MediaController getInstance(){
		if(controller==null){
			controller = new MediaController();
		}
		
		return controller;
	}
	
	
	private MediaController(){
		queue = new ArrayList<MediaPlayer>();
	}
	
	public void queueNewTrack(Track track){
		String url = track.getPreviewUrl();
		System.out.println(url);
		url = url.substring(0, 4) + url.substring(5);
		System.out.println(url);
		Media preview = new Media(url);
		MediaPlayer mediaPlayer = new MediaPlayer(preview);
		queue.add(mediaPlayer);
	}
	
	public MediaPlayer getNextTrack(){
		if(queue.isEmpty()) return null;
		
		return queue.remove(0);
	}
}
