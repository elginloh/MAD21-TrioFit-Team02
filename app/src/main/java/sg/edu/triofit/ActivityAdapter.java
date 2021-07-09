package sg.edu.triofit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

//    List<Activity> activityList;
//
       Context context;

       List<String> activityList;

    public ActivityAdapter(List<String> activityList) {
        this.activityList = activityList;
    }
    //    public ActivityAdapter(List<Activity> activityList, Context context){
//        this.context = context;
//        this.activityList = activityList;
//    }


    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_holder, parent, false);
        return new ActivityViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder actHolder, int position){


        String activity = activityList.get(position);
//        String urlList = activityList.indexOf(2).get(position);
        actHolder.activity.setText(activity);
//        Drawable drawable = loadImageFromWeb("https://www.ihrsa.org/uploads/Articles/Column-Width/equipment_triggerpoint-roller_column.jpg");
//        actHolder.imageView.setImageDrawable(drawable);

//        actHolder.imageView.setImageURI(Uri.parse("https://www.active.com/Assets/Running/460/Hill-Runner-Silhouette.jpg"));
//        Uri.parse(activity)

//        DBHandler dbHandler = new DBHandler(this.context,null,null,1);
//        activityList = dbHandler.ge
//
//        Glide.with(context).load(activityList.get(position).getImage()).into(actHolder.imageView);

        actHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(actHolder.imageView.getContext(),SegmentActivity.class);
                actHolder.imageView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){

        return activityList.size();
    }

//    private Drawable loadImageFromWeb(String url) {
//
//        try
//        {
//            InputStream is = (InputStream) new URL(url).getContent();
//            Drawable d = Drawable.createFromStream(is, "src name");
//            return d;
//        }catch (Exception e) {
//            System.out.println("Exc="+e);
//            return null;
//        }
//    }
}