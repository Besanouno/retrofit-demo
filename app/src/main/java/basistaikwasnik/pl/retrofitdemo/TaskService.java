package basistaikwasnik.pl.retrofitdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskService {

    private final String APP_ID = "22";

    public void getAction(final Activity activity) {
        Call<List<TaskDto>> call = RetrofitService.getInstance().getTasks(APP_ID);
        call.enqueue(new Callback<List<TaskDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<TaskDto>> call, @NonNull Response<List<TaskDto>> response) {
                List<TaskDto> tasks = response.body();
                TaskAdapter adapter = new TaskAdapter(tasks, activity);
                ((ListView) activity.findViewById(R.id.tasks)).setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<TaskDto>> call, @NonNull Throwable t) {
                errorDialog(activity);
            }
        });
    }

    public void putAction(final Activity activity) {
        Call<Void> call = RetrofitService.getInstance().putTask(APP_ID, prepareTaskFromActivity(activity, null));
        call.enqueue(reload(activity));
    }

    public void deleteAction(final Activity activity, final String uuid) {
        Call<Void> call = RetrofitService.getInstance().deleteTask(APP_ID, uuid);
        call.enqueue(reload(activity));
    }

    public void postAction(final Activity activity, final String uuid) {
        Call<Void> call = RetrofitService.getInstance().postTask(APP_ID, prepareTaskFromActivity(activity, uuid));
        call.enqueue(reload(activity));
    }

    private TaskDto prepareTaskFromActivity(Activity activity, String uuid) {
        EditText title = activity.findViewById(R.id.title);
        EditText description = activity.findViewById(R.id.description);
        return new TaskDto(title.getText().toString(), description.getText().toString(), uuid);
    }

    private Callback<Void> reload(final Activity activity) {
        return new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                getAction(activity);
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                errorDialog(activity);
            }
        };
    }

    private void errorDialog(Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("Niestety wystąpił błąd, spróbuj ponownie")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }


}
