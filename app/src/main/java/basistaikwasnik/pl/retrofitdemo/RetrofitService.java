package basistaikwasnik.pl.retrofitdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://ancient-tundra-25084.herokuapp.com";

    public static BoarApi getInstance() {
        return create();
    }

    private static BoarApi create() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonConfiguration()))
                .build()
                .create(BoarApi.class);
    }

    private static Gson gsonConfiguration() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, dateJsonDeserializer())
                .create();
    }

    private static JsonDeserializer<Date> dateJsonDeserializer() {
        return new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        };
    }

}
