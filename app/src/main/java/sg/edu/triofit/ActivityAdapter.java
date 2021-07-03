package sg.edu.triofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

    private final List<String> activityList;

    public ActivityAdapter(List<String> activityList){
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_holder, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder actHolder, int position){
        String activity = activityList.get(position);
        actHolder.activity.setText(activity);
    }

    @Override
    public int getItemCount(){
        return activityList.size();
    }
}
