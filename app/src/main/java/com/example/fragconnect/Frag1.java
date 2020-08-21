package com.example.fragconnect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.VISIBLE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class Frag1 extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag1() {
    }

    private ImageView im;
    private Bitmap bp;
    private Canvas can;
    private Paint paint;
    private Paint paintFill;
    private int width;
    private int height;
    private float x;
    private float y;
    private float xOld;
    private float yOld;
    private TextView tv;
    private boolean first=true;


    // TODO: Rename and change types and number of parameters
    public static Frag1 newInstance(String param1, String param2) {
        Frag1 fragment = new Frag1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        im=getView().findViewById(R.id.f1_im);
        tv=getView().findViewById(R.id.f1_tv);
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.blue));
        paint.setStrokeWidth(10);
        paintFill=new Paint();
        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setColor(getResources().getColor(R.color.blue));

    }

    public void draw(float x,float y,boolean line){
        if(first){
            tv.setVisibility(View.INVISIBLE);
            width=im.getWidth();
            height=im.getHeight();
            bp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
            can=new Canvas(bp);
            im.setImageBitmap(bp);

            can.drawCircle(x,y,1,paintFill);
            first=false;
        }
        else {
            if(!line){
                can.drawCircle(x,y,1,paintFill);
            }else {
                can.drawLine(xOld, yOld, x, y, paint);
            }
        }
        xOld=x;
        yOld=y;
        im.invalidate();
    }

    public void clear() {
        can.drawColor(getResources().getColor(R.color.blackish));
    }
}