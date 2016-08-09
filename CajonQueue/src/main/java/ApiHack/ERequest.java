package ApiHack;

import com.wrapper.spotify.HttpManager;
import com.wrapper.spotify.UtilProtos.Url;

public interface ERequest {

  public static interface Builder {
    Builder httpManager(HttpManager httpManager);
    Builder host(String host);
    Builder port(int port);
    Builder scheme(Url.Scheme scheme);
    EAbstractRequest build();
  }

  Url toUrl();

}
