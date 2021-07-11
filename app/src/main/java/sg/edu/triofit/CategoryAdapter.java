package sg.edu.triofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private final List<Category> categoryList;
    private List<Activity> imageList;
    private Context context;

    public CategoryAdapter(List<Category> categoryList, List<Activity> imageList, Context context)
    {
        this.categoryList = categoryList;
        this.imageList = imageList;
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
    }

    @Override
    public int getItemCount()
    {
        return categoryList.size();
    }
}