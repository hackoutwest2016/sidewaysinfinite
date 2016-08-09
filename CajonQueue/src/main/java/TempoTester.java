import java.util.List;

import com.wrapper.spotify.models.SimpleTrack;

import ApiHack.APIAuthentication;

public class TempoTester{
	static OSCLoopClass OSCLoop = new OSCLoopClass();

	public static void main(String[] args){
		//OSCLoop.loopetyLoop();
		
		
		List<SimpleTrack> tracks = TrackLister.getTrackListFromArtist("4Z8W4fKeB5YxbusRsdQVPb");
		System.out.println("ALL " + tracks.size());
		List<SimpleTrack> tempoTracks = TrackLister.getTracksAboveTempo(tracks, 100);
		System.out.println("HIGH TEMPO " + tempoTracks.size());
	}
}
