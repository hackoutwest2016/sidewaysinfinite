package ApiHack;

import com.wrapper.spotify.models.AuthorizationCodeCredentials;

public interface NewTokenListener {

	void onNewTokensReceived(String AccessToken, String RefreshToken);
	
}
