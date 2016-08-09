package ApiHack;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;

import ApiHack.ExtendedSpotifyApi;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import java.util.ArrayList;


public class APIAuthentication {
	
	private static ArrayList<NewTokenListener> listeners = new ArrayList<NewTokenListener>();
	
	public static void setup(ArrayList<NewTokenListener> listener) {
		for (NewTokenListener newTokenListener : listener) {
			listeners.add(newTokenListener);
		}
		
		/* Application details necessary to get an access token */
		final String clientId = "<342da21c17ca497d8940f73ecfc3a88f>";
		final String clientSecret = "<78c8cdbe253649f0b28335a6e35bb18a>";
		final String code = "<42>";
		final String redirectUri = "<CajunQueue://callback>";

		/* Create a default API instance that will be used to make requests to Spotify */
		final Api api = Api.builder()
				.clientId(clientId)
				.clientSecret(clientSecret)
				.redirectURI(redirectUri)
				.build();
		final ExtendedSpotifyApi otherApi = ExtendedSpotifyApi.builder()
				.clientId2(clientId)
				.clientSecret2(clientSecret)
				.redirectURI2(redirectUri)
				.build2();

		/* Make a token request. Asynchronous requests are made with the .getAsync method and synchronous requests
		 * are made with the .get method. This holds for all type of requests. */
		final SettableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = api.authorizationCodeGrant(code).build().getAsync();

		/* Add callbacks to handle success and failure */
		Futures.addCallback(authorizationCodeCredentialsFuture, new FutureCallback<AuthorizationCodeCredentials>() {
			public void onSuccess(AuthorizationCodeCredentials authorizationCodeCredentials) {
				/* The tokens were retrieved successfully! */
				System.out.println("Successfully retrieved an access token! " + authorizationCodeCredentials.getAccessToken());
				System.out.println("The access token expires in " + authorizationCodeCredentials.getExpiresIn() + " seconds");
				System.out.println("Luckily, I can refresh it using this refresh token! " + authorizationCodeCredentials.getRefreshToken());
				
				for (NewTokenListener newTokenListener : listeners) {
					newTokenListener.onNewTokensReceived(authorizationCodeCredentials.getAccessToken(), authorizationCodeCredentials.getRefreshToken());
				}
			}

			public void onFailure(Throwable throwable) {
				/* Let's say that the client id is invalid, or the code has been used more than once,
				 * the request will fail. Why it fails is written in the throwable's message. */
				fail(throwable.getMessage());
			}

		});

	}
}