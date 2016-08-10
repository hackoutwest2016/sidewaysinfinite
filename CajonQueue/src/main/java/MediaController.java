import java.util.ArrayList;

import com.wrapper.spotify.models.Track;

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
		String url = "http://d318706lgtcm8e.cloudfront.net/mp3-preview/f454c8224828e21fa146af84916fd22cb89cedc6";//track.getPreviewUrl();
		Media preview = new Media(url);
		MediaPlayer mediaPlayer = new MediaPlayer(preview);
		queue.add(mediaPlayer);
	}
	
	public MediaPlayer getNextTrack(){
		if(queue.isEmpty()) return null;
		
		return queue.remove(queue.size()-1);
	}
}
