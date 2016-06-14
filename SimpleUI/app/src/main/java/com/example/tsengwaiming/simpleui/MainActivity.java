package com.example.tsengwaiming.simpleui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    CheckBox checkBox;
    ListView listview;
    Spinner storeSpinner;

    //ArrayList<String> drinks = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    String name="";
    String sex="";
    String selectedSex = "Male";
    String drinkName = "black tea";

    final int REQUEST_CODE_DRINK_MENU_ACTIVITY = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);
        Log.d("Debug", "Hello World");
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.editText);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        listview = (ListView) findViewById(R.id.listView);
        storeSpinner = (Spinner) findViewById(R.id.spinner);


        setupListView();
        setupSpinner();




        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                changeTextView();
                if (checkBox.isChecked()){
                    radioGroup.setVisibility(View.GONE);
                }else{
                    radioGroup.setVisibility(View.VISIBLE);
                }

            }
        });
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
        Log.d("Debug","Main Activity onCreate");
    }



    @Override
    protected  void onStart(){
        super.onStart();
        Log.d("Debug","Main Activity onStart");
    }

    @Override
    protected  void onStop(){
        super.onStop();
        Log.d("Debug","Main Activity onStop");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.d("Debug","Main Activity onDestroy");
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Log.d("Debug","Main Activity onResume");
    }

    @Override
    protected  void onRestart(){
        super.onRestart();
        Log.d("Debug","Main Activity onRestart");
    }


    @Override
    protected  void onPause(){
        super.onPause();
        Log.d("Debug","Main Activity onPause");
    }

    public void goToMenu(View view)
    {
        Intent intent = new Intent();
        intent.setClass(this,DrinkMenuActivity.class);
        startActivityForResult(intent,REQUEST_CODE_DRINK_MENU_ACTIVITY);

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data ){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_CODE_DRINK_MENU_ACTIVITY){
            if(resultCode==RESULT_OK){
                textView.setText(data.getStringExtra("results"));
                Toast.makeText(this,"完成菜單",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void click(View view)
    {
       // name = editText.getText().toString();
        String note = editText.getText().toString();
        sex=selectedSex;
        //changeTextView();
        //        changeTextView();
        //drinks.add(drinkName);
        Order order = new Order();
        order.storeInfo = (String)storeSpinner.getSelectedItem();
        order.note = note;
        order.drinkName = drinkName;
        orders.add(order);
        textView.setText(drinkName);
        editText.setText("");
        setupListView();
        setupSpinner();


    }

    public void changeTextView(){
        if (name.equals("")){
            return;
        }
        if (checkBox.isChecked()){
            textView.setText(name);
        }else{
            String content = name +"  sex:"+sex;
            textView.setText(content);
        }
    }

    void setupSpinner(){
        String[] data = getResources().getStringArray(R.array.storeInfo);
        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,data);
        storeSpinner.setAdapter(adapter);
    }

    void setupListView(){
        //String[] data =new String[]{"123","456","789","Hello","ListView","Hi"};
        //ArrayAdapter<String>adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drinks);
        /*
        List<Map<String,String>> data= new ArrayList<>();
        for(int i =0;i<orders.size();i++){
            Order order = orders.get(i);
            Map<String,String>item=new HashMap<>();
            item.put("note",order.note);
            item.put("drinkName",order.drinkName);
            data.add(item);

        }
        String[] from ={"note","drinkName"};
        int[] to ={R.id.noteTextView,R.id.drinkNameTextView};
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.listview_order_item, from, to);
        listview.setAdapter(adapter);
        */
        OrderAdapter adapter = new OrderAdapter(this, orders);
        listview.setAdapter(adapter);
    }
}
