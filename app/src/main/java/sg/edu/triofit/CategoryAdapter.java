package sg.edu.triofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private final List<Category> categoryList;
    private List<Activity> imageList;
    private List<Activity> videoList;
//    private List<Activity> descList;
    private Context context;

    public CategoryAdapter(List<Category> categoryList, List<Activity> imageList, List<Activity> videoList, Context context)
    {
        this.categoryList = categoryList;
        this.imageList = imageList;
        this.videoList = videoList;
//        this.descList = descList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view_holder, parent, false);
        return new CategoryViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder catHolder, int position){
        Category category = categoryList.get(position);
        catHolder.categoryName.setText(category.getCategoryName());
        catHolder.setCategoryList(category.getCategory());
        Activity activity = imageList.get(position);
        catHolder.setImageList(activity.getActivities());
        Activity activity1 = videoList.get(position);
        catHolder.setVideoList(activity1.getActivities());
//        Activity activity2 = descList.get(position);
//        catHolder.setDescList(activity2.getActivities());
    }

    @Override
    public int getItemCount()
    {
        return categoryList.size();
    }
}