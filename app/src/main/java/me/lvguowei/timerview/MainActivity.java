package me.lvguowei.timerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TimerView timerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerView = (TimerView) findViewById(R.id.timer);


    }

    @Override
    protected void onResume() {
        super.onResume();
        timerView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timerView.stop();
    }
}
