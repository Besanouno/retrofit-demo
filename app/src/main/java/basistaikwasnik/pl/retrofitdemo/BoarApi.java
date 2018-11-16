package basistaikwasnik.pl.retrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BoarApi {

    @PUT("/{id}")
    Call<Void> putTask(@Path("id") final String id, @Body final TaskDto task);

    @POST("/{id}")
    Call<Void> postTask(@Path("id") final String id, @Body final TaskDto task);

    @GET("/{id}")
    Call<List<TaskDto>> getTasks(@Path("id") final String id);

    @DELETE("/{id}/{uuid}")
    Call<Void> deleteTask(@Path("id") final String id, @Path("uuid") final String uuid);

}
