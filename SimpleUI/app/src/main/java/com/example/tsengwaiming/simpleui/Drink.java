package com.example.tsengwaiming.simpleui;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tsengwaiming on 2016/6/13.
 */
public class Drink {
    String name;
    int mPrice;
    int lPrice;
    int imageId;

    public JSONObject getData()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("price",mPrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
