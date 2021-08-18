package com.example.graphics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

public class ShapesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);

        final ImageView canvasImage = findViewById(R.id.canvasImage);

        Bitmap bitmap = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888);
        canvasImage.setImageBitmap(bitmap);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        paint.setTextSize(50);

        paint.setColor(Color.MAGENTA);
        canvas.drawText("Rectangle",720,350, paint);
        canvas.drawRect(700,400,950,900, paint);

        paint.setColor(Color.BLUE);
        canvas.drawText("Circle",220,350, paint);
        canvas.drawCircle(300,550,150, paint);

        paint.setColor(Color.RED);
        canvas.drawText("Square",220,1100, paint);
        canvas.drawRect(150,1150,450,1450, paint);

        paint.setColor(Color.BLACK);
        canvas.drawText("Line",780,1100,paint);
        canvas.drawLine(820,1150,820,1450, paint);

        paint.setColor(Color.parseColor("#FFA500"));    //orange
        RectF oval = new RectF(200, 100, 800, 200);
        canvas.drawText("Arc", 475, 100, paint);
        canvas.drawArc(oval, 0, 180, false, paint);
    }
}