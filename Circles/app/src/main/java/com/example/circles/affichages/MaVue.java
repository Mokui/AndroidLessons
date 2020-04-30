package com.example.circles.affichages;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.circles.R;

import java.util.Random;

import androidx.annotation.Nullable;

public class MaVue extends View {
    private Paint paint;
    private Random rnd = new Random();
    private int x,y = 0;
    private int color = -1;
    private boolean argColor = false;

    public MaVue(Context context) {
        super(context);
        init(context, null);
    }

    public MaVue(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaVue(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if(attrs != null){
            // récupération d'un tableau d'arguments :
            TypedArray arguments = context.obtainStyledAttributes(attrs, R.styleable.MaVue);
            color = arguments.getColor(R.styleable.MaVue_couleur, -1);
            argColor = (color != -1);
            setRandColor();

            paint.setColor(color);

            // IMPORTANT !! ne pas oublier :
            arguments.recycle();
        }

        //Rand size
        int size = rnd.nextInt(50 - 20) + 20;

        //paint.setColor(color);
        paint.setTextSize(size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Rand size
        int rayon = rnd.nextInt((getHeight()/3) - (getWidth()/3)) + (getWidth()/3);
        paint.setColor(color);
        canvas.drawCircle(x,y,rayon,paint);
    }

    public void setValX(int x) {
        this.x = x;
    }

    public void setValY(int y) {
        this.y = y;
    }

    public void setRandColor(){
        if(!argColor){
            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        }
    }
}
