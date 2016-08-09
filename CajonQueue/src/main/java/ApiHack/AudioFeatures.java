package ApiHack;

public class AudioFeatures {
	private float danceability;
	private float energy;
	private float key;
	private float loudness;
	private int mode;
	private float speechiness;
	private float acousticness;
	private float instrumentalness;
	private float liveness;
	private float valence;
	private float tempo;
	private String id;
	private String uri;
	private String track_href;
	private String analysis_url;
	private int duration_ms;
	private int time_signature;
	
	public float getDanceability() {
		return danceability;
	}
	public void setDanceability(float danceability) {
		this.danceability = danceability;
	}
	public float getTempo() {
		return tempo;
	}
	public void setTempo(float tempo) {
		this.tempo = tempo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
