package com.formationandroid.starwarsrandomplanet.repository;

import android.util.Log;

import com.formationandroid.starwarsrandomplanet.model.Item;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import androidx.lifecycle.MutableLiveData;
import cz.msebera.android.httpclient.Header;

public class MainRepository {
    private static final String TAGWBS = "-ERROR WEBSERVICE-";

    public void getLiveDataItem(final MutableLiveData<Item> liveDataItem, final MutableLiveData<Boolean> liveDataSpinner) {
        liveDataSpinner.setValue(true);
        //Données
        int random = new Random().nextInt((60-1)+1)+1;
        String url = "https://swapi.dev/api/planets/"+random+"?format=json";
        // client HTTP :
        AsyncHttpClient client = new AsyncHttpClient();
        // paramètres :
        RequestParams requestParams = new RequestParams();

        // appel :
        client.get(url, requestParams, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] response)
            {
                try {
                    JSONObject obj = new JSONObject(new String(response));
                    String name = null; String diameter = null ; String climat = null ; String gravity = null; String sol = null;

                    name = obj.getString("name");
                    diameter = obj.getString("diameter");
                    climat = obj.getString("climate");
                    gravity = obj.getString("gravity");
                    sol = obj.getString("terrain");

                    Item item = new Item(
                            "Nom:\t "+name
                                    +"\nDiamètre(km):\t "+diameter
                                    +"\nClimat:\t "+climat
                                    +"\nGravité:\t "+gravity
                                    +"\nTerrain:\t "+sol);
                    liveDataItem.setValue(item);
                    liveDataSpinner.setValue(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //String retour = new String(response); //Version simple
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] errorResponse, Throwable e)
            {
                Log.e(TAGWBS, e.toString());
            }
        });
    }
}
