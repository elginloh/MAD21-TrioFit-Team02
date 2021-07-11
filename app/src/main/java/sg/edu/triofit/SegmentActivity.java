package sg.edu.triofit;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class SegmentActivity extends AppCompatActivity {

    private static final String video = "sample";
    private VideoView sVideoView;
    private TextView sBufferText;
    private int sCurrentPosition;
    private static final String PLAYBACK_TIME = "play_time";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment);

        sVideoView = findViewById(R.id.videoView);
//        sVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample));
//        sVideoView.start();
        sBufferText = findViewById(R.id.segmentView1);
        if (savedInstanceState != null) {
            sCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(sVideoView);
        sVideoView.setMediaController(controller);

    }
    @Override
    protected void onStart() {
        super.onStart();

        // Load the media each time onStart() is called.
        initializePlayer();
    }
    @Override
    protected void onPause() {
        super.onPause();


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            sVideoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        sVideoView.seekTo(sCurrentPosition);
        sVideoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
//
//        // Media playback takes a lot of resources, so everything should be
//        // stopped and released at this time.
//        releasePlayer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current playback position (in milliseconds) to the
        // instance state bundle.
        outState.putInt(PLAYBACK_TIME, sVideoView.getCurrentPosition());
    }

    private void initializePlayer() {
        // Show the "Buffering..." message while the video loads.
        sBufferText.setVisibility(VideoView.VISIBLE);

        // Buffer and decode the video sample.
        Uri videoUri = getMedia(video);
        sVideoView.setVideoURI(videoUri);

        // Listener for onPrepared() event (runs after the media is prepared).
        sVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                        // Hide buffering message.
                        sBufferText.setVisibility(VideoView.INVISIBLE);

                        // Restore saved position, if available.
                        if (sCurrentPosition > 0) {
                            sVideoView.seekTo(sCurrentPosition);
                        } else {
                            // Skipping to 1 shows the first frame of the video.
                            sVideoView.seekTo(1);
                        }

                        // Start playing!
                        sVideoView.start();
                    }
                });

        // Listener for onCompletion() event (runs after media has finished
        // playing).
        sVideoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Toast.makeText(SegmentActivity.this,
                                "end",
                                Toast.LENGTH_SHORT).show();

                        // Return the video position to the start.
                        sVideoView.seekTo(0);
                    }
                });
    }


    // Release all media-related resources. In a more complicated app this
    // might involve unregistering listeners or releasing audio focus.
    private void releasePlayer() {
        sVideoView.stopPlayback();
    }

    // Get a Uri for the media sample regardless of whether that sample is
    // embedded in the app resources or available on the internet.
    private Uri getMedia(String mediaName) {


            // Media name is a raw resource embedded in the app.
            return Uri.parse("android.resource://" + getPackageName() +
                    "/raw/" + mediaName);

    }
}