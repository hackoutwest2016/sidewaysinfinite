import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.SimpleTrack;

public class TrackLister {
	public static List<SimpleTrack> getTrackListFromArtist(String id) {
		final Api api = Api.DEFAULT_API;
		ArrayList<SimpleTrack> tracks = new ArrayList<>();
		try{
			
			Page<SimpleAlbum> albums = api.getAlbumsForArtist(id).build().get();
			for(SimpleAlbum album: albums.getItems()){
				System.out.println(album.getName());
				String albumId = album.getId();
				Album fullAlbum = api.getAlbum(albumId).build().get();
				
				tracks.addAll(fullAlbum.getTracks().getItems());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return tracks;
		
	}
}
