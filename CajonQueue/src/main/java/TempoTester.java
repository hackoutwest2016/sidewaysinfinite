import java.util.List;

import com.wrapper.spotify.models.SimpleTrack;
import com.wrapper.spotify.models.Track;

import ApiHack.APIAuthentication;
import IO.IO;


public class TempoTester{
	static OSCLoopClass OSCLoop = new OSCLoopClass();

	public static void main(String[] args){
		//OSCLoop.loopetyLoop();
		IO.downloadMP3("http://d318706lgtcm8e.cloudfront.net/mp3-preview/f454c8224828e21fa146af84916fd22cb89cedc6", "/file.mp3");
		
		List<Track> tracks = TrackLister.getTracksFromPlaylist("hannamaterne", "7nLf3xNGvhPwkbuOUmHfaq");//.getTrackListFromArtist("4Z8W4fKeB5YxbusRsdQVPb");
		System.out.println("ALL " + tracks.size());
		List<Track> tempoTracks = TrackLister.getTracksAboveTempo(tracks, 100);
		System.out.println("HIGH TEMPO " + tempoTracks.size());
	}
}
