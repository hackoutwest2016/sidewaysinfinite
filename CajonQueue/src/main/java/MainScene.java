
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.models.Track;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MainScene extends Application implements NewMessageListener {
	static MediaView mediaView;
	static MediaController controller;
	static List<Track> playlistTracks; 
	private CajonOSCInPort inPort = new CajonOSCInPort(this);
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		controller = MediaController.getInstance();
		playlistTracks = TrackLister.getTracksFromPlaylist("hannamaterne", "7nLf3xNGvhPwkbuOUmHfaq");
        
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("MainScene.fxml")
        );
        loader.setController(MediaController.getInstance());
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 800, 564);
        primaryStage.setTitle("BeatRamble");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        mediaView = new MediaView(null);
		((AnchorPane)scene.getRoot()).getChildren().add(mediaView);
	
        onNewMessage(null);
        
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
	
		int tempo = Integer.parseInt(tmp.substring(1, tmp.indexOf(".")));
		
		MediaController.getInstance().getDetectedTempo().setText(tempo + "");
		
		Track track = TrackLister.getClosestTempoTrack(playlistTracks, tempo);
		MediaController.getInstance().getSongName().setText(track.getName());
		MediaController.getInstance().getSongName().setText(track.getArtists().get(0).getName());
		MediaController.getInstance().queueNewTrack(track);
		
		playNextTrack();
		
	}
}
