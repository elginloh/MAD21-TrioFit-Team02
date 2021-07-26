package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.services.youtube.model.VideoPlayer;

public class SegmentActivity extends YouTubeBaseActivity {


    YouTubePlayerView youtubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    TextView desc;

    private String video = "w9xfXsqIGKk";
    private String description = "description of video.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment);
        //retrieve data
        Intent intent = getIntent();
        video = intent.getStringExtra("videoCode");
        description = intent.getStringExtra("description");
        desc = findViewById(R.id.videoDesc);
        desc.setText(description);
        youtubePlayerView = findViewById(R.id.YoutubePlayerView);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //load video if video code is found through youtube API
                youTubePlayer.loadVideo(video);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        //with the correct key, the video will play
        youtubePlayerView.initialize("AIzaSyArsX82MHSKkv3iGbZ9Ywu8KCOIfmvI-7w", onInitializedListener);
        Button btn =  findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SegmentActivity.this, CatalogueActivity.class);
                startActivity(intent);
            }
        });
    }
}