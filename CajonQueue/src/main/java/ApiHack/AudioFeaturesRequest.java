package ApiHack;

import java.io.IOException;

import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.UtilProtos.Url;
import com.wrapper.spotify.exceptions.WebApiException;


public class AudioFeaturesRequest extends EAbstractRequest{
	public AudioFeaturesRequest(Builder builder){
		super(builder);
	}
	
	  public AudioFeatures get() throws IOException, WebApiException {
		    final String jsonString = getJson();
		    return ExtendedJsonUtil.createAudioFeatures(jsonString);
		  }
	
	  public SettableFuture<AudioFeatures> getAsync() {
		    SettableFuture<AudioFeatures> audioFeatureFuture = SettableFuture.create();

		    try {
		      final String jsonString = getJson();
		      audioFeatureFuture.set(ExtendedJsonUtil.createAudioFeatures(jsonString));
		    } catch (Exception e) {
		    	audioFeatureFuture.setException(e);
		    }

		    return audioFeatureFuture;
		  }
	  
	public static Builder builder() {
	    return new Builder();
	}
	
	public static final class Builder extends EAbstractRequest.Builder<Builder> {
			
		    /**
		     * The track with the given id.
		     *
		     * @param id The id for the track.
		     * @return Audio Features Request
		     */
		    public Builder id(String id) {
		      assert (id != null);
		      return path(String.format("/v1/audio-features/%s", id));
		    }

		    public AudioFeaturesRequest build() {
		    	this.scheme = Url.Scheme.HTTPS;
		    	
		      return new AudioFeaturesRequest(this);
		    }

		  }

}
