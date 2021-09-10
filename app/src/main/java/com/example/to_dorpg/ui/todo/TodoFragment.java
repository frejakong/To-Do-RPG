package com.example.to_dorpg.ui.todo;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.to_dorpg.DetailActivity;
import com.example.to_dorpg.database.TodoTaskManager;

import androidx.fragment.app.Fragment;

import com.example.to_dorpg.R;
import com.example.to_dorpg.model.TodoTask;

public class TodoFragment extends Fragment {
    private TodoTaskManager todoTaskManager;

    private ListView lvTasks;

    private List<TodoTask> tasks = new ArrayList<TodoTask>();

    private TaskAdapter taskAdapter;

    ImageButton btn_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        todoTaskManager = new TodoTaskManager(getActivity());
        tasks = todoTaskManager.queryAll();
        btn_add = (ImageButton) getActivity().findViewById(R.id.newItemButton);
        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });
        initComponents();
    }

    public void onResume() {
        super.onResume();
        tasks = todoTaskManager.queryAll();
        taskAdapter.notifyDataSetChanged();
    }



    private void initComponents() {

        lvTasks = (ListView) getActivity().findViewById(R.id.lvTasks);
        taskAdapter = new TaskAdapter();

        lvTasks.setAdapter(taskAdapter);
        lvTasks.setOnItemLongClickListener(onItemLongClickListener);
        lvTasks.setOnItemClickListener(onItemClickListener);
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            String taskId = String.valueOf(id);
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.TASK_ID, taskId);
            startActivity(intent);
        }
    };

    OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                       int position, long id) {
            delete(id);
            return false;
        }
    };

    public void delete(final long id) {
        new AlertDialog.Builder(getActivity()).setTitle("Delete Items")
                .setMessage("Delete ? ")
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (todoTaskManager.delete("_id = ? ", new String[]{String.valueOf(id)})) {
                            tasks = todoTaskManager.queryAll();
                            taskAdapter.notifyDataSetChanged();
                        }
                    }
                }).setNegativeButton("Nope", null).show();
    }


    public class TaskAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public TaskAdapter() {
            layoutInflater = LayoutInflater.from(getActivity());
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
            return tasks.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.task_item, null);
                viewHolder.ivComplete = (ImageView) convertView.findViewById(R.id.ivComplete);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if ("Y".equals(tasks.get(position).getFlagCompleted())) {
                viewHolder.ivComplete.setVisibility(View.VISIBLE);
            } else {
                viewHolder.ivComplete.setVisibility(View.GONE);
            }
            viewHolder.tvTitle.setText(tasks.get(position).getTitle());
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView ivComplete;
        TextView tvTitle;
    }
}
