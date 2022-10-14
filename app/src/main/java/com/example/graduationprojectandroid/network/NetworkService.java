package com.example.graduationprojectandroid.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.graduationprojectandroid.fragments.for_changing_avatar.AvatarParts;
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Item;
import com.example.graduationprojectandroid.network.endpoints.JSONPlaceHolderApi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    //Classes for lambda functions

    public interface AwaiterForRegisterUser{
        void start(String answer);
    }

    public interface AwaiterForMarketItems{
        void start(ArrayList<Item> items);
    }

    public interface AwaiterForInventoryItems{
        void start(ArrayList<Item> items);
    }

    public interface AwaiterForAmountOfOneAvatarPartType{
        void start(int amount);
    }

    public interface AwaiterForGetUserData{
        void start(UserData userData);
    }

    public interface AwaiterForChangedAvatar{
        void start();
    }



    //--------Classes for lambda functions

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

    public void setPictureByURL(String URL, ImageView view){
        new DownloadImageTask(view)
                .execute("https://awontis.com/wp-content/uploads/revslider/home-business-slide3/icon-slide4.png");
    }

    public void setPictureById(int id, ImageView view){
        setPictureByURL(BASE_URL + "", view);
    }

    public void setPictureOfAvatarPart(int type, int id, ImageView view){
        //TODO: get from SERVER choosing_pictures for avatar parts
        setPictureByURL(BASE_URL + "", view);
    }

    //---------getting picture

    //@GET(BASE_URL + )
    //private Call<List<MarketItem>> itemsForMarket();

    public void registerUser(String login, String password, AwaiterForRegisterUser awaiter){
        //TODO: make it
        awaiter.start("");
    }


    public void getItemsForMarket(AwaiterForMarketItems awaiter){
        ArrayList<Item> items = new ArrayList<>();

        //TODO: Get from server

        items.add(new Item(1,1, 19, 10, 15, View.VISIBLE));
        items.add(new Item(2,1, 21, 3, 5, View.VISIBLE));
        items.add(new Item(3,1, 128));
        items.add(new Item(1,1, 215));
        items.add(new Item(2,1,15));
        items.add(new Item(3,1,125));
        items.add(new Item(1,1,145));
        items.add(new Item(2,1,155));
        items.add(new Item(3,1,165));
        items.add(new Item(1,1,175));
        items.add(new Item(2,1,185));
        items.add(new Item(3,1,195));
        items.add(new Item(1,1,11655));
        items.add(new Item(2,1,1345));
        items.add(new Item(3,1,135));
        items.add(new Item(1,1,165));
        items.add(new Item(1,1, 19, 10, 15, View.VISIBLE));
        items.add(new Item(2,1, 21, 3, 5, View.VISIBLE));
        items.add(new Item(3,1, 128));
        items.add(new Item(1,1, 215));
        items.add(new Item(2,1,15));
        items.add(new Item(3,1,125));
        items.add(new Item(1,1,145));
        items.add(new Item(2,1,155));
        items.add(new Item(3,1,165));
        items.add(new Item(1,1,175));
        items.add(new Item(2,1,185));
        items.add(new Item(3,1,195));
        items.add(new Item(1,1,11655));
        items.add(new Item(2,1,1345));
        items.add(new Item(3,1,135));
        items.add(new Item(1,1,165));

        awaiter.start(items);
    }

    public void getItemsForInventory(AwaiterForInventoryItems awaiter){
        ArrayList<Item> items = new ArrayList<>();

        //TODO: Get from server

        items.add(new Item(1, 1, 19, 10, 15, View.VISIBLE));
        items.add(new Item(2, 1, 21, 3, 5, View.VISIBLE));
        items.add(new Item(3,1,  128));
        items.add(new Item(1,1,  215));
        items.add(new Item(2,1, 15));
        items.add(new Item(3,1, 125));
        items.add(new Item(1,1, 145));
        items.add(new Item(2,1, 155));
        items.add(new Item(3,1, 165));
        items.add(new Item(1,1, 175));
        items.add(new Item(2,1, 185));
        items.add(new Item(3,1, 195));
        items.add(new Item(1,1, 11655));
        items.add(new Item(2,1, 1345));
        items.add(new Item(3,1, 135));
        items.add(new Item(1,1, 165));
        items.add(new Item(1,1,  19, 10, 15, View.VISIBLE));
        items.add(new Item(2,1,  21, 3, 5, View.VISIBLE));
        items.add(new Item(3,1,  128));
        items.add(new Item(1,1,  215));
        items.add(new Item(2,1, 15));
        items.add(new Item(3,1, 125));
        items.add(new Item(1,1, 145));
        items.add(new Item(2,1, 155));
        items.add(new Item(3,1, 165));
        items.add(new Item(1,1, 175));
        items.add(new Item(2,1, 185));
        items.add(new Item(3,1, 195));
        items.add(new Item(1,1, 11655));
        items.add(new Item(2,1, 1345));
        items.add(new Item(3,1, 135));
        items.add(new Item(1,1, 165));

        awaiter.start(items);
    }

    public void getAmountOfOneAvatarPartType(AvatarParts ap,
                                             AwaiterForAmountOfOneAvatarPartType awaiter)
            throws Exception
    {

        switch (ap){ //TODO: Get from SERVER
            case BODY:
                awaiter.start(15);
                break;
            case HAIR:
                awaiter.start(2);
                break;
            case EYES:
                awaiter.start(5);
                break;
            default:
                throw new Exception("NetworkService: getAmountOfOneAvatarPartType: Unknown AvatarPart");
        }
    }

    public void getUserData(AwaiterForGetUserData awaiter){
        //TODO: get userData from data server
        awaiter.start(new UserData(
                "abc", "Avocado",
                129, 65, 100, 220, 378,
                13, 1, 1, 1, 1,
                1, 1, 1
        ));
    }

    public void changedAvatar(List<Integer> chosenParts,
                              String avatarName,
                              AwaiterForChangedAvatar awaiter){
        //TODO: not sure in list
        //TODO: send choosen parts (see table with numbers)
        awaiter.start();
    }
}
