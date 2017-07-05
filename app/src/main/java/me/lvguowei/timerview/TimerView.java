package me.lvguowei.timerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerView extends View {
    private Paint backgroundPaint;
    private TextPaint numberPaint;

    private Date time;
    private SimpleDateFormat formatter;

    public TimerView(Context context) {
        super(context);
        init();
    }

    public TimerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        formatter = new SimpleDateFormat("HH:mm:ss");
        time = new Date(System.currentTimeMillis());
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.parseColor("#880000"));

        numberPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        numberPaint.setTextSize(64f * getResources().getDisplayMetrics().scaledDensity);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        float centerX = Math.round(canvasWidth * 0.5f);
        float centerY = Math.round(canvasHeight * 0.5f);

        float radius = (canvasWidth < canvasHeight ? canvasWidth : canvasHeight) * 0.5f;

        String text = formatter.format(time);
        float textOffsetX = numberPaint.measureText(text) * -0.5f;
        // for some reason the ascent is negative
        float textOffsetY = numberPaint.getFontMetrics().ascent * -0.4f;

        canvas.drawCircle(centerX, centerY, radius, backgroundPaint);
        canvas.drawText(text, centerX + textOffsetX, centerY + textOffsetY, numberPaint);
    }

    public void tick() {
        time = new Date(System.currentTimeMillis());
        invalidate();
    }
}
