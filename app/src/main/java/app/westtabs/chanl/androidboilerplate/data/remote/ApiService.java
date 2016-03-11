package app.westtabs.chanl.androidboilerplate.data.remote;

import java.util.List;

import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ApiService {

    String ENDPOINT = "https://api.github.com/";

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Observable<List<Repo>> getUserRepos(@Path("username") String username);

    /********
     * Helper class that sets up a new services
     *******/
    class Creator {

        public static ApiService newRibotsService() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}
