package me.lvguowei.timerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TimerView timerView;

    private Handler handler;

    private Runnable updateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerView = (TimerView) findViewById(R.id.timer);

        handler = new Handler(getMainLooper());
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                timerView.tick();
                handler.postDelayed(updateRunnable, 50);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(updateRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateRunnable);
    }
}
