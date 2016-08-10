
import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.models.Track;

import IO.IO;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MainScene extends Application implements NewMessageListener {
	static MediaView mediaView;
	static MediaController controller;
	static List<Track> playlistTracks; 
	private CajonOSCInPort inPort = new CajonOSCInPort(this);
	
	@Override
	public void start(Stage primaryStage) {
		controller = MediaController.getInstance();
		playlistTracks = TrackLister.getTracksFromPlaylist("hannamaterne", "7nLf3xNGvhPwkbuOUmHfaq");
		
        primaryStage.setTitle("BeatRamble");
        Group root = new Group();
        Scene scene = new Scene(root, 540, 210);
        mediaView = new MediaView(null);
		((Group)scene.getRoot()).getChildren().add(mediaView);
		
        // TODO: Make this actually work.
		root.setStyle("-fx-background-image: url('img/logo.png'); " +
		           "-fx-background-position: center center; " +
		           "-fx-background-repeat: stretch;");
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
		//OSCLoop.loopetyLoop();

		//IO.downloadMP3("http://d318706lgtcm8e.cloudfront.net/mp3-preview/f454c8224828e21fa146af84916fd22cb89cedc6", fileName);
	}
	
	private static void playNextTrack() {
		System.out.println("PLAYING NEXT TRACK!");
		MediaPlayer nextTrack = controller.getNextTrack();
		if(nextTrack!= null) {
			nextTrack.setOnEndOfMedia(new Runnable() {
				
				public void run() {
					playNextTrack();
				}
			});
		}
		if(mediaView.getMediaPlayer() != null) mediaView.getMediaPlayer().stop();
		mediaView.setMediaPlayer(nextTrack);
		nextTrack.setAutoPlay(true);
	}

	public void onNewMessage(ArrayList<MessageObject> l) {
		String tmp = l.get(0).getMessageContent().getArguments().toString();
		System.out.println("THERE BE STUFF HAPPENING YO: " + tmp);
		int tempo = Integer.parseInt(tmp.substring(1, tmp.indexOf(".")));
		System.out.println("THERE BE STUFF HAPPENING YO: " + tempo);
		Track track = TrackLister.getClosestTempoTrack(playlistTracks, tempo);
		System.out.println(track.getName());
		MediaController.getInstance().queueNewTrack(track);
		
		playNextTrack();
		
	}
}
