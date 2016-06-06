package com.example.tsengwaiming.simpleui;

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
