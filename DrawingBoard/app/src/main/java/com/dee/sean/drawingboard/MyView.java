package com.dee.sean.drawingboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by dixin on 15/11/26.
 */
public class MyView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private Paint paint = new Paint();
    private Path path = new Path();

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        paint.setColor(Color.RED);
        paint.setTextSize(10);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        setOnTouchListener(this);
    }

    public void draw() {
        Canvas canvas = getHolder().lockCanvas();

        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, paint);

        getHolder().unlockCanvasAndPost(canvas);
    }

    public void clear() {
        path.reset();
        draw();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        draw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                draw();
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                draw();
                break;
        }
        return true;
    }
}
