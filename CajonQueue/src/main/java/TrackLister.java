import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.PlaylistTrack;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.SimpleTrack;
import com.wrapper.spotify.models.Track;

import ApiHack.APIAuthentication;
import ApiHack.AudioFeatures;
import ApiHack.ExtendedSpotifyApi;
import ApiHack.NewTokenListener;

public class TrackLister {
	private static ArrayList<NewTokenListener> listeners = new ArrayList<NewTokenListener>();
	
	
	public static List<Track> getTracksAboveTempo(List<Track> tracks, int tempo) {
		final ExtendedSpotifyApi api = ExtendedSpotifyApi.DEFAULT_API;
		listeners.add(api);
		APIAuthentication.setup(listeners);
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Track> tempoTracks = new ArrayList<Track>();
		
		for(Track track: tracks) {
			System.out.println(track.getName());
			try{
				AudioFeatures features = api.getAudioFeatures(track.getId()).build().get();
				System.out.println(features.getTempo());
				if(features.getTempo()>=tempo){
					tempoTracks.add(track);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return tempoTracks;
	}
	
	public static List<Track> getTracksFromPlaylist(String userId, String playlistId){
		final ExtendedSpotifyApi api = ExtendedSpotifyApi.DEFAULT_API;
		listeners.add(api);
		APIAuthentication.setup(listeners);
		List<Track> tracks = new ArrayList<Track>();
		try{
			List<PlaylistTrack> temp = api.getPlaylistTracks(userId, playlistId).build().get().getItems();
			
			for (PlaylistTrack playlistTrack : temp) {
				tracks.add(playlistTrack.getTrack());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return tracks;
	}
	
	
	/**
	 * Get the list of tracks for a particular artist. 
	 * @param artistId
	 * @return
	 */
	public static List<SimpleTrack> getTrackListFromArtist(String artistId) {
		final ExtendedSpotifyApi api = ExtendedSpotifyApi.DEFAULT_API;
		listeners.add(api);
		List<SimpleTrack> tracks = new ArrayList<SimpleTrack>();
		try{
			
			List<SimpleAlbum> albums = api.getAlbumsForArtist(artistId).build().get().getItems();
			for(SimpleAlbum album: albums){
				System.out.println(album.getName());
				tracks.addAll(getTrackListFromAlbum(album.getId()));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(tracks.size());
		return tracks;
		
	}
	
	/**
	 * Get the list of tracks for a particular album.
	 * @param albumId
	 * @return
	 */
	public static List <SimpleTrack> getTrackListFromAlbum(String albumId) {
		final ExtendedSpotifyApi api = ExtendedSpotifyApi.DEFAULT_API;
		listeners.add(api);
		List<SimpleTrack> tracks = new ArrayList<SimpleTrack>();
		try{
			Album fullAlbum = api.getAlbum(albumId).build().get();
		
			tracks.addAll(fullAlbum.getTracks().getItems());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return tracks;
	}
}
