package com.example.tsengwaiming.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    String selectedSex = "Male";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Debug", "Hello World");
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.editText);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.MaleRadioButton){
                    selectedSex="Male";
                }else if(i==R.id.FemaleRadioButton){
                    selectedSex="Female";
                }

            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int KeyCode, KeyEvent event) {
                if (KeyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    click(v);
                    return true;
                }
                return false;
            }
        });
    }

    public void click(View view)
    {
        String content = editText.getText().toString();
        content = content +"  sex:"+selectedSex;
        textView.setText(content);
        editText.setText("");

    }

}
