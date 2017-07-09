package me.lvguowei.timerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TimerView timer1;
    private View parent1;

    private TimerView timer2;
    private View parent2;

    private TimerView timer3;
    private View parent3;

    private TimerView timer4;
    private View parent4;

    ImageView icon;
    TextView title;
    TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer1 = (TimerView) findViewById(R.id.timer1);
        parent1 = findViewById(R.id.parent1);

        timer2 = (TimerView) findViewById(R.id.timer2);
        parent2 = findViewById(R.id.parent2);

        timer3 = (TimerView) findViewById(R.id.timer3);
        parent3 = findViewById(R.id.parent3);

        timer4 = (TimerView) findViewById(R.id.timer4);
        parent4 = findViewById(R.id.parent4);

        icon = (ImageView) findViewById(R.id.icon);
        title = (TextView) findViewById(R.id.title);
        subtitle = (TextView) findViewById(R.id.subtitle);

        icon.setImageResource(R.drawable.ic_android_black_24dp);
        title.setText("Hello");
        subtitle.setText("my name is android");
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer1.start();
        timer2.start();
        timer3.start();
        timer4.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer1.stop();
        timer2.stop();
        timer3.stop();
        timer4.stop();
    }
}
