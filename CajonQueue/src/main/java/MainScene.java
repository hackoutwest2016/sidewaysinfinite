
import java.util.List;

import com.wrapper.spotify.models.Track;

import IO.IO;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MainScene extends Application {
	final String fileName = "http://d318706lgtcm8e.cloudfront.net/mp3-preview/f454c8224828e21fa146af84916fd22cb89cedc6";
	@Override
	public void start(Stage primaryStage) {
        primaryStage.setTitle("Embedded Media Player");
        Group root = new Group();
        Scene scene = new Scene(root, 540, 210);

		Media hit = new Media(fileName);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setAutoPlay(true);
		
		MediaView mediaView = new MediaView(mediaPlayer);
		((Group)scene.getRoot()).getChildren().add(mediaView);
        
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
		//OSCLoop.loopetyLoop();

		//IO.downloadMP3("http://d318706lgtcm8e.cloudfront.net/mp3-preview/f454c8224828e21fa146af84916fd22cb89cedc6", fileName);
		

		
		List<Track> tracks = TrackLister.getTracksFromPlaylist("hannamaterne", "7nLf3xNGvhPwkbuOUmHfaq");//.getTrackListFromArtist("4Z8W4fKeB5YxbusRsdQVPb");
		System.out.println("ALL " + tracks.size());
		List<Track> tempoTracks = TrackLister.getTracksAboveTempo(tracks, 100);
		System.out.println("HIGH TEMPO " + tempoTracks.size());
		

	}
}
