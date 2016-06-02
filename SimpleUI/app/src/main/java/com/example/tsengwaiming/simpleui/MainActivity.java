package com.example.tsengwaiming.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Debug","Hello World");
        textView = (TextView) findViewById(R.id.textview);
        textView.setText("Hello TextView");
    }

    public void click(View view)
    {

        String a = textView.getText().toString();
        if (a== "I love little rain") {
            textView.setText("Hello TextView");
        }else{
            textView.setText("I love little rain");
        }
    }

}
