package com.example.fragconnect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.view.View.VISIBLE;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class Frag2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag2() {
        // Required empty public constructor
    }

    private F2List listener;

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


    // TODO: Rename and change types and number of parameters
    public static Frag2 newInstance(String param1, String param2) {
        Frag2 fragment = new Frag2();
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
        return inflater.inflate(R.layout.fragment_frag2, container, false);
    }

    public interface F2List{
        void onInput(float x,float y,boolean line);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof F2List){
            listener=(F2List)context;
        }else{
            throw new RuntimeException(context.toString() +
                    "Must implement Frag1 listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        im=getView().findViewById(R.id.f2_im);
        tv=getView().findViewById(R.id.f2_tv);
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.orange));
        paint.setStrokeWidth(10);
        paintFill=new Paint();
        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setColor(getResources().getColor(R.color.orange));

        im.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                width=view.getWidth();
                height=view.getHeight();
                Log.i(TAG, "view width  "+width);
                Log.i(TAG, "view height  "+height);

                bp=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
                can=new Canvas(bp);
                im.setImageBitmap(bp);
            }
        });

        im.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(tv.getVisibility()==VISIBLE){
                    tv.setVisibility(View.INVISIBLE);
                }
                x=motionEvent.getX();
                y=motionEvent.getY();
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        listener.onInput(x,y,false);
                        can.drawCircle(x,y,1,paint);
                        xOld=x;
                        yOld=y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        listener.onInput(x,y,true);
                        can.drawLine(xOld,yOld,x,y,paint);
                        xOld=x;
                        yOld=y;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                im.invalidate();
                return true;
            }
        });

    }
    public void clear() {
        can.drawColor(getResources().getColor(R.color.blackish));
    }
}