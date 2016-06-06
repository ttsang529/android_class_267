package com.example.tsengwaiming.simpleui;

import android.content.Context;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Text;

/**
 * Created by tsengwaiming on 2016/6/6.
 */
public class OrderAdapter extends BaseAdapter {

    ArrayList<Order> orders;
    LayoutInflater inflater;


    public OrderAdapter(Context context, ArrayList<Order> orders){
        this.orders   = orders;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return orders.size();
    }



    @Override
    public Object getItem(int position){
        return orders.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position,View convertView ,ViewGroup parent){
        Holder holder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.listview_order_item,null);
            TextView drinkNameTextView = (TextView)convertView.findViewById(R.id.drinkNameTextView);
            TextView noteTextView = (TextView)convertView.findViewById(R.id.noteTextView);
            TextView storeTextView = (TextView)convertView.findViewById(R.id.storetextView);
            holder =  new Holder();
            holder.drinkName = drinkNameTextView;
            holder.note = noteTextView;
            holder.store = storeTextView;
            convertView.setTag(holder);

        }else{
            holder = (Holder)convertView.getTag();
        }


        Order order = orders.get(position);

        holder.drinkName.setText(order.drinkName);
        holder.note.setText(order.note);
        holder.store.setText(order.storeInfo);
        return convertView;
    }

    class Holder{
        TextView drinkName;
        TextView note;
        TextView store;

    }

}
