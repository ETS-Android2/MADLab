package com.example.graphics;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

public class AnimateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        ImageView canvasAnimate = findViewById(R.id.canvasAnimate);

        Paint paint = new Paint();

        Bitmap carBitmap = Bitmap.createBitmap(1920, 1080, Bitmap.Config.ARGB_8888);
        canvasAnimate.setImageBitmap(carBitmap);

        Canvas carCanvas = new Canvas(carBitmap);

        //Car one
        paint.setColor(Color.GREEN);
        carCanvas.drawRect(50, 100, 450, 300, paint);

        paint.setColor(Color.YELLOW);
        RectF roof1 = new RectF(100, 0, 400, 200);
        carCanvas.drawArc(roof1, 180, 180, false, paint);

        paint.setColor(Color.DKGRAY);
        carCanvas.drawCircle(150, 300, 50, paint);
        carCanvas.drawCircle(350, 300, 50, paint);

        //Car two
        paint.setColor(Color.BLUE);
        carCanvas.drawRect(450, 600, 50, 800, paint);

        paint.setColor(Color.RED);
        RectF roof2 = new RectF(100, 500, 400, 700);
        carCanvas.drawArc(roof2, 180, 180, false, paint);

        paint.setColor(Color.BLACK);
        carCanvas.drawCircle(150, 800, 50, paint);
        carCanvas.drawCircle(350, 800, 50, paint);

        ObjectAnimator animateCar = ObjectAnimator.ofFloat(canvasAnimate, "x",  800);
        animateCar.setDuration(2000);
        AnimatorSet animation = new AnimatorSet();

        //Reverse the direction
        animation.play(animateCar).before(ObjectAnimator.ofFloat(canvasAnimate, "x", 0).setDuration(2000));

        //To loop the animation indefinitely
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animation.start();
            }
        });

        //To start the animation
        animation.start();
    }
}