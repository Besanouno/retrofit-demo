package basistaikwasnik.pl.retrofitdemo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    private List<TaskDto> tasks;
    private LayoutInflater inflater;
    private Activity activity;

    public TaskAdapter(List<TaskDto> tasks, Activity activity) {
        this.tasks = tasks;
        this.inflater = activity.getLayoutInflater();
        this.activity = activity;
    }

    public void addAll(List<TaskDto> c) {
        tasks.addAll(c);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        final TaskDto task = (TaskDto) getItem(position);
        if (itemView == null) {
            itemView = inflater.inflate(R.layout.item_task, null);
        }

        final TextView title = itemView.findViewById(R.id.title);
        title.setText(task.getTitle());
        final TextView description = itemView.findViewById(R.id.description);
        description.setText(task.getDescription());
        final ImageButton remove = itemView.findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO DELETE
            }
        });
        final ImageButton edit = itemView.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = activity.findViewById(R.id.title);
                title.setText(task.getTitle());
                title.setEnabled(false);
                EditText description = activity.findViewById(R.id.description);
                description.setText(task.getDescription());
                TextView uuid = activity.findViewById(R.id.uuid);
                uuid.setText(task.getUuid());
            }
        });

        return itemView;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
