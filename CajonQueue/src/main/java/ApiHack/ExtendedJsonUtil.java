package ApiHack;

import com.wrapper.spotify.JsonUtil;

import net.sf.json.JSONObject;

public class ExtendedJsonUtil extends JsonUtil{
	
	public static AudioFeatures createAudioFeatures(String featuresJson){
		return createAudioFeatures(JSONObject.fromObject(featuresJson));
	}

	private static AudioFeatures createAudioFeatures(JSONObject featuresJSON){
		AudioFeatures features = new AudioFeatures();
		
		features.setTempo(featuresJSON.getInt("tempo"));
		features.setId(featuresJSON.getString("id"));
		
		
		return features;
	}
}
