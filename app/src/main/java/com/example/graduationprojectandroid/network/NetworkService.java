package com.example.graduationprojectandroid.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.graduationprojectandroid.fragments.for_main_page.adapters.MarketItem;
import com.example.graduationprojectandroid.network.endpoints.JSONPlaceHolderApi;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    //singleton
    private static NetworkService mInstance;

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }
    //---------singleton

    //Retrofit
    private static final String BASE_URL = "https://graduationprojectdo.azurewebsites.net/";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //      endpoints

    public JSONPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JSONPlaceHolderApi.class);
    }

    //      -------endpoints

    //--------Retrofit

    //getting picture

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Ошибка передачи изображения", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public void setPictureById(int id, ImageView view){
        new DownloadImageTask(view)
                .execute("https://awontis.com/wp-content/uploads/revslider/home-business-slide3/icon-slide4.png");
    }

    //---------getting picture

    public ArrayList<MarketItem> getItemsForMarket(){
        ArrayList<MarketItem> items = new ArrayList<>();

        items.add(new MarketItem(1, 19, 10, 15, View.VISIBLE));
        items.add(new MarketItem(2, 21, 3, 5, View.VISIBLE));
        items.add(new MarketItem(3, 128));
        items.add(new MarketItem(1, 215));
        items.add(new MarketItem(2,15));
        items.add(new MarketItem(3,125));
        items.add(new MarketItem(1,145));
        items.add(new MarketItem(2,155));
        items.add(new MarketItem(3,165));
        items.add(new MarketItem(1,175));
        items.add(new MarketItem(2,185));
        items.add(new MarketItem(3,195));
        items.add(new MarketItem(1,11655));
        items.add(new MarketItem(2,1345));
        items.add(new MarketItem(3,135));
        items.add(new MarketItem(1,165));
        items.add(new MarketItem(1, 19, 10, 15, View.VISIBLE));
        items.add(new MarketItem(2, 21, 3, 5, View.VISIBLE));
        items.add(new MarketItem(3, 128));
        items.add(new MarketItem(1, 215));
        items.add(new MarketItem(2,15));
        items.add(new MarketItem(3,125));
        items.add(new MarketItem(1,145));
        items.add(new MarketItem(2,155));
        items.add(new MarketItem(3,165));
        items.add(new MarketItem(1,175));
        items.add(new MarketItem(2,185));
        items.add(new MarketItem(3,195));
        items.add(new MarketItem(1,11655));
        items.add(new MarketItem(2,1345));
        items.add(new MarketItem(3,135));
        items.add(new MarketItem(1,165));

        return items;
    }

    public ArrayList<MarketItem> getItemsForInventory(){

        return getItemsForMarket();
    }
}
