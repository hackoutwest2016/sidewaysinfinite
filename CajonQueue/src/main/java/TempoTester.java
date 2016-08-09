import java.util.List;

import com.wrapper.spotify.models.SimpleTrack;
import com.wrapper.spotify.models.Track;

import ApiHack.APIAuthentication;

public class TempoTester{
	static OSCLoopClass OSCLoop = new OSCLoopClass();

	public static void main(String[] args){
		//OSCLoop.loopetyLoop();
		
		
		List<Track> tracks = TrackLister.getTracksFromPlaylist("hannamaterne", "7nLf3xNGvhPwkbuOUmHfaq");//.getTrackListFromArtist("4Z8W4fKeB5YxbusRsdQVPb");
		System.out.println("ALL " + tracks.size());
		List<Track> tempoTracks = TrackLister.getTracksAboveTempo(tracks, 100);
		System.out.println("HIGH TEMPO " + tempoTracks.size());
	}
}
