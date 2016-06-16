package com.example.tsengwaiming.simpleui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DrinkMenuActivity extends AppCompatActivity {

    ListView drinkListView;
    TextView priceTextView;

    ArrayList<Drink> drinks= new ArrayList();

    ArrayList<Drink> drinkOrders= new ArrayList();

    //SET DATA
    String[] names = {"東瓜紅荼","玫瑰鹽奶蓋紅荼","珍珠紅荼拿鐡","紅荼拿鐡"};
    int[] mPrices = {25,35,45,35};
    int[] lPrice ={35,45,55,45};
    int[] imageId= {R.drawable.drink1,R.drawable.drink2,R.drawable.drink3,R.drawable.drink4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        setData();
        drinkListView = (ListView)findViewById(R.id.drinkListView);
        priceTextView = (TextView)findViewById(R.id.priceTextview);
        setupListView();
        drinkListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrinkAdapter drinkAdapter = (DrinkAdapter)adapterView.getAdapter();
                Drink drink = (Drink)drinkAdapter.getItem(i);
                drinkOrders.add(drink);
                updateTotalPrice();


            }

        });

        Log.d("Debug","activity_drink_menu Activity onCreate");
    }

    private void updateTotalPrice(){
        int total = 0;
        for (Drink drink : drinkOrders){
            total +=drink.mPrice;

        }
        priceTextView.setText((String.valueOf(total)));
    }


    private void setupListView(){
            DrinkAdapter adapter = new DrinkAdapter(this, drinks);
            drinkListView.setAdapter(adapter);
    }

    public void done(View view){
        Intent intent = new Intent();
        JSONArray array = new JSONArray();
        for (Drink drink :drinkOrders){
            JSONObject object = drink.getData();
            array.put(object);
        }
        intent.putExtra("results",array.toString());
        setResult(RESULT_OK,intent);
        finish();

    }

    public void cancel(View view){
        Intent intent = new Intent();
        JSONArray array = new JSONArray();
        intent.putExtra("results",array.toString());
        setResult(1,intent);
        finish();

    }
    private void setData()
    {
        for(int i = 0 ; i<4 ;i++)
        {
            Drink drink = new Drink();
            drink.name = names[i];
            drink.mPrice = mPrices[i];
            drink.lPrice = lPrice[i];
            drink.imageId =  imageId[i];
            drinks.add(drink);


        }
    }

    @Override
    protected  void onStart(){
        super.onStart();
        Log.d("Debug","activity_drink_menu Activity onStart");
    }

    @Override
    protected  void onStop(){
        super.onStop();
        Log.d("Debug","activity_drink_menu Activity onStop");
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        Log.d("Debug","activity_drink_menu Activity onDestroy");
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Log.d("Debug","activity_drink_menu Activity onResume");
    }

    @Override
    protected  void onRestart(){
        super.onRestart();
        Log.d("Debug","activity_drink_menu Activity onRestart");
    }


    @Override
    protected  void onPause(){
        super.onPause();
        Log.d("Debug","activity_drink_menu Activity onPause");
    }


}
