package com.ubu.tfg.diagnosticofresadoras;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        /*VideoView videoView = findViewById(R.id.vvVideo);
        Uri path = Uri.parse("android.resource://com.ubu.tfg.diagnosticofresadoras/"
                + R.raw.vid_aceite_desplazandose_por_tubo_engrase_2);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoURI(path);
        videoView.start();*/
    }
}
