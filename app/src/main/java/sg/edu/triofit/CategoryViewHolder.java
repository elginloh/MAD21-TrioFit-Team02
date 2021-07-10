package sg.edu.triofit;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    ActivityAdapter adapter;

    TextView categoryName;
    RecyclerView rvCategory;
    List<String> categoryList;
    List<Activity> activityList;
    Context context;



    public CategoryViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.Category);
        rvCategory = itemView.findViewById(R.id.recyclerView);
        this.context = context;

        categoryList = new ArrayList<>();
        activityList = new ArrayList<>();
        adapter = new ActivityAdapter(categoryList, activityList, context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        rvCategory.setLayoutManager(layoutManager);
        rvCategory.setAdapter(adapter);
    }

    void setCategoryList(List<String> categoryList){
        this.categoryList.clear();
        this.categoryList.addAll(categoryList);
        adapter.notifyDataSetChanged();
    }

    public void setImageList(List<Activity> activities) {
        this.activityList.clear();
        this.activityList.addAll(activities);
        adapter.notifyDataSetChanged();
    }
}