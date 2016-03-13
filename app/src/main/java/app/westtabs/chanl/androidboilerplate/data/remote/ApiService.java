package app.westtabs.chanl.androidboilerplate.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
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
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(User.class, new MyDeserializer<User>())
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.ENDPOINT)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }

    class MyDeserializer<T> implements JsonDeserializer<T> {
        @Override
        public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException {
            // Get the "content" element from the parsed JSON
            JsonElement content = je.getAsJsonObject().get("owner");
            // Deserialize it. You use a new instance of Gson to avoid infinite recursion
            // to this deserializer
            return new Gson().fromJson(content, type);

        }
    }
}
