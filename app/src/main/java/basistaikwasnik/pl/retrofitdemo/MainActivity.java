package basistaikwasnik.pl.retrofitdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TaskService taskService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.taskService = new TaskService();

        initializeFields(this);
        taskService.getAction(this);
    }

    private void initializeFields(final Activity activity) {
        final ListView tasksView = activity.findViewById(R.id.tasks);
        final TaskAdapter taskAdapter = new TaskAdapter(new ArrayList<TaskDto>(), activity);
        tasksView.setAdapter(taskAdapter);

        final Button send = this.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView uuid = activity.findViewById(R.id.uuid);
                if (!uuid.getText().toString().isEmpty()) {
                    taskService.postAction(activity, uuid.getText().toString());
                } else {
                    taskService.putAction(activity);
                }
                clearFields(activity);
            }
        });


        final Button clear = this.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields(activity);
            }
        });
    }

    private void clearFields(Activity activity) {
        EditText title = activity.findViewById(R.id.title);
        title.setText("");
        title.setEnabled(true);
        EditText description = activity.findViewById(R.id.description);
        description.setText("");
        TextView uuid = activity.findViewById(R.id.uuid);
        uuid.setText("");
    }


}
