package com.enchcrop.hotstarsimilar;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class youplay extends YouTubeBaseActivity {
    YouTubePlayerView youTube;
    DownloadManager downloadManager;
    String S_video;
    ImageView button;
    TextView test,test2;
    GridView gridView;

    YouTubePlayer.OnInitializedListener oninitiallistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youplay);

        youTube=(YouTubePlayerView) findViewById(R.id.youtube);
        button=(ImageView)findViewById(R.id.download);
        test=(TextView)findViewById(R.id.textdesc);
        test2=(TextView)findViewById(R.id.time);
        gridView=(GridView)findViewById(R.id.gridyou);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri=Uri.parse("https://www.youtube.com/watch?v=WM_0NrlF1lg");
                DownloadManager.Request request=new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);
            }
        });


        oninitiallistener =new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                String url1 =getIntent().getStringExtra("idd");
                String url2=getIntent().getStringExtra("descr");
                String url3=getIntent().getStringExtra("time");
                test.setText(url2);
                test2.setText(url3);
  youTubePlayer.loadVideo(url1);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTube.initialize(playconfig.API_KEY,oninitiallistener);


    }



}