package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityViewHolder extends RecyclerView.ViewHolder {

    final TextView activity;
    ImageView imageView;

    public ActivityViewHolder(@NonNull View itemView){
        super(itemView);

        activity = itemView.findViewById(R.id.Activity);
        imageView = itemView.findViewById(R.id.ActivityImage);
    }
}