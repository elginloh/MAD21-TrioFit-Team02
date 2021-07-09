package sg.edu.triofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

    private final List<Activity> activityList;

//    List<String> activityList2;
//
//    public ActivityAdapter(List<String> activityList2) {
//        this.activityList2 = activityList2;
//    }

    private Context context;


    public ActivityAdapter(List<Activity> activityList, Context context){
        this.context = context;
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
        Activity activity = activityList.get(position);
//        actHolder.activity.setText(activity);
        actHolder.activity.setText(activity.getName());

        Glide.with(context).load(activityList.get(position).getImageURL().into(actHolder.imageView);
//        DBHandler dbHandler = new DBHandler(this.context,null,null,1);
//        activityList = dbHandler.ge
    }



    @Override
    public int getItemCount(){
        return activityList.size();
    }
}
