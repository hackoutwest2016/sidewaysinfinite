package ApiHack;
import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.Api.Builder;
import com.wrapper.spotify.UtilProtos.Url.Scheme;
import com.wrapper.spotify.methods.AbstractRequest;
import com.wrapper.spotify.methods.authentication.AuthorizationCodeGrantRequest;
import com.wrapper.spotify.methods.authentication.RefreshAccessTokenRequest;

public class ExtendedSpotifyApi {
	  private HttpManager httpManager = null;
	  private Scheme scheme;
	  private int port;
	  private String host;
	  private String accessToken;
	  private String refreshToken;
	  private final String clientId;
	  private final String clientSecret;
	  private final String redirectURI;
	  
	  public static final String DEFAULT_HOST = "api.spotify.com";

	  public static final ExtendedSpotifyApi DEFAULT_API = ExtendedSpotifyApi.builder().build2();
	  /**
	   * The default port of Spotify API calls.
	   */
	  public static final int DEFAULT_PORT = 443;

	  /**
	   * A HttpManager configured with default settings.
	   */
	  public static final HttpManager DEFAULT_HTTP_MANAGER = SpotifyHttpManager.builder().build();

	  public static final Scheme DEFAULT_SCHEME = Scheme.HTTPS;
	  public static final String DEFAULT_AUTHENTICATION_HOST = "accounts.spotify.com";

	  public static final int DEFAULT_AUTHENTICATION_PORT = 443;

	  public static final Scheme DEFAULT_AUTHENTICATION_SCHEME = Scheme.HTTPS;
	  
	  private ExtendedSpotifyApi(Builder builder) {
		    assert (builder.host != null);
		    assert (builder.port > 0);
		    assert (builder.scheme != null);


		    if (builder.httpManager == null) {
		      this.httpManager = SpotifyHttpManager
		              .builder()
		              .build();
		    } else {
		      this.httpManager = builder.httpManager;
		    }
		    scheme = builder.scheme;
		    host = builder.host;
		    port = builder.port;
		    accessToken = builder.accessToken;
		    refreshToken = builder.refreshToken;
		    clientId = builder.clientId;
		    clientSecret = builder.clientSecret;
		    redirectURI = builder.redirectURI;
	}
	  
	public static Builder builder() {
	    return new Builder();
	}
	public AudioFeaturesRequest.Builder getAudioFeatures(String id){
		AudioFeaturesRequest.Builder builder = AudioFeaturesRequest.builder();
		setDefaults(builder);
		builder.id(id);
		builder.path("/v1/audio-features/" + id);
		return builder;
		
	}
	
	private void setDefaults(EAbstractRequest.Builder builder) {
	    builder.httpManager(httpManager);
	    builder.scheme(scheme);
	    builder.host(host);
	    builder.port(port);
	    if (accessToken != null) {
	    	builder.header("Authorization", "Bearer " + accessToken);
	    }
	}
	
	private void setDefaults(AbstractRequest.Builder builder) {
	    builder.httpManager(httpManager);
	    builder.scheme(scheme);
	    builder.host(host);
	    builder.port(port);
	    if (accessToken != null) {
	    	builder.header("Authorization", "Bearer " + accessToken);
	    }
	}
	
	  public void setAccessToken(String accessToken) {
		    this.accessToken = accessToken;
		  }

		  public void setRefreshToken(String refreshToken) {
		    this.refreshToken = refreshToken;
		  }
		  /**
		   * Returns a builder that can be used to build requests for authorization code
		   * grants.
		   * Requires client ID, client secret, and redirect URI to be set.
		   * @param code An authorization code.
		   * @return A builder that builds authorization code grant requests.
		   */
		  public AuthorizationCodeGrantRequest.Builder authorizationCodeGrant(String code) {
		    AuthorizationCodeGrantRequest.Builder builder = AuthorizationCodeGrantRequest.builder();
		    setDefaults(builder);
		    builder.grantType("authorization_code");
		    builder.basicAuthorizationHeader(clientId, clientSecret);
		    builder.code(code);
		    builder.redirectUri(redirectURI);
		    return builder;
		  }

		  /**
		   * Returns a builder that can be used to build requests to refresh an access token
		   * that has been retrieved using the authorization code grant flow.
		   * @return A builder that builds refresh access token requests.
		   */
		  public RefreshAccessTokenRequest.Builder refreshAccessToken() {
		    RefreshAccessTokenRequest.Builder builder = RefreshAccessTokenRequest.builder();
		    setDefaults(builder);
		    builder.grantType("refresh_token");
		    builder.refreshToken(refreshToken);
		    builder.basicAuthorizationHeader(clientId, clientSecret);
		    return builder;
		  }
	
	public static class Builder extends com.wrapper.spotify.Api.Builder{

		    private String host = DEFAULT_HOST;
		    private int port = DEFAULT_PORT;
		    private HttpManager httpManager = null;
		    private Scheme scheme = DEFAULT_SCHEME;
		    private String accessToken;
		    private String redirectURI;
		    private String clientId;
		    private String clientSecret;
		    private String refreshToken;

		    public Builder scheme(Scheme scheme) {
		      this.scheme = scheme;
		      return this;
		    }
		    
		    public ExtendedSpotifyApi build2() {
		        assert (host != null);
		        assert (port > 0);
		        assert (scheme != null);

		        return new ExtendedSpotifyApi(this);
		      }
		   

		      public Builder host2(String host) {
		        this.host = host;
		        return this;
		      }

		      public Builder port2(int port) {
		        this.port = port;
		        return this;
		      }

		      public Builder httpManager2(HttpManager httpManager) {
		        this.httpManager = httpManager;
		        return this;
		      }

		      public Builder accessToken2(String accessToken) {
		        this.accessToken = accessToken;
		        return this;
		      }

		      public Builder refreshToken(String refreshToken) {
		        this.refreshToken = refreshToken;
		        return this;
		      }

		      public Builder clientId2(String clientId) {
		        this.clientId = clientId;
		        return this;
		      }

		      public Builder clientSecret2(String clientSecret) {
		        this.clientSecret = clientSecret;
		        return this;
		      }

		      public Builder redirectURI2(String redirectURI) {
		        this.redirectURI = redirectURI;
		        return this;
		      }

	}

}
