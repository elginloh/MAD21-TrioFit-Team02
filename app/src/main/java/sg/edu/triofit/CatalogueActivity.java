package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class CatalogueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);

        DBHandler dbHandler = new DBHandler(this,null,null,1);
        List<Category> categoryList = dbHandler.getAll();
        List<Activity> imageList = dbHandler.getImages();
        List<Activity> videoList = dbHandler.getVideos();
        CategoryAdapter adapter = new CategoryAdapter(categoryList, imageList, videoList, getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        RecyclerView rvCategory = findViewById(R.id.recyclerView);
        rvCategory.setLayoutManager(layoutManager);
        rvCategory.setAdapter(adapter);
    }
}