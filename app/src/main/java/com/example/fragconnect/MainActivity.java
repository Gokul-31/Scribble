package com.example.fragconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Frag2.F2List {

    Frag1 f1;
    Frag2 f2;
    Button clearBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f1=new Frag1();
        f2=new Frag2();
        clearBt=findViewById(R.id.bt_clear);

        getSupportFragmentManager().beginTransaction().replace(R.id.f1,f1).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.f2,f2).commit();

        clearBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1.clear();
                f2.clear();
            }
        });
    }

    @Override
    public void onInput(float x, float y,boolean line) {
        f1.draw(x,y,line);
    }
}