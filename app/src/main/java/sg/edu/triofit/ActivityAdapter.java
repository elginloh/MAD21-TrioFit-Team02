package sg.edu.triofit;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {

    List<Activity> activityImageList;

       Context context;

       List<String> activityList;



    public ActivityAdapter(List<String> activityList , List<Activity> activityImageList, Context context){
        this.context = context;
        this.activityList = activityList;
        this.activityImageList = activityImageList;
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
        String imageName = activityImageList.get(position).getImage();

        actHolder.activity.setText(activity);
        Log.v("ImageName", imageName);
        int imageID = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        actHolder.imageView.setImageDrawable(context.getResources().getDrawable(imageID));

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

}