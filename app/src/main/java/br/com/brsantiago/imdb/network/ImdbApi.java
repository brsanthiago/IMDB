package br.com.brsantiago.imdb.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bruno on 23/07/17.
 */

public class ImdbApi {
    private final String BASE_URL = "http://www.omdbapi.com";
    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    public Retrofit provideApi() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient())
                .build();
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request original = chain.request();
                        HttpUrl url = original.url().newBuilder()
                                .addQueryParameter("apikey", "ec6483bd")
                                .addQueryParameter("plot", "full")
                                .build();
                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url)
                                .method(original.method(), original.body());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }
}
