package com.dino.downloadprogress;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.dino.progress.Constants;
import com.dino.progress.DownloadProgress;

public class MainActivity extends AppCompatActivity {
    private DownloadProgress downProgress;
    private int progress = 0;
    private long time = 200;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progress = progress+5;
            if(progress ==100){
                downProgress.setStatue(Constants.FINISHSTATUE);
            }else{
                downProgress.setProgress(progress);
            }
            handler.postDelayed(runnable, time);
        }
    };

    private void assignViews() {
        downProgress = (DownloadProgress) findViewById(R.id.down_progress);
        downProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(downProgress.getStatue() == Constants.FINISHSTATUE){
                    return;
                }
                downProgress.setStatue(Constants.PROGRESSSTATUE);
                downProgress.setProgress(progress);
                handler.postDelayed(runnable,time);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }
}
