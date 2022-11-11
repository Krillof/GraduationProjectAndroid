package com.example.graduationprojectandroid.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.graduationprojectandroid.network.endpoints.APIs.HabitsAPI;
import com.example.graduationprojectandroid.network.endpoints.APIs.ItemsAPI;
import com.example.graduationprojectandroid.network.endpoints.APIs.NewsAPI;
import com.example.graduationprojectandroid.network.endpoints.APIs.TasksAPI;
import com.example.graduationprojectandroid.network.endpoints.APIs.UserAPI;
import com.example.graduationprojectandroid.network.endpoints.APIs.UserStudentsAPI;
import com.example.graduationprojectandroid.network.endpoints.APIs.UserTeachersAPI;

import java.io.InputStream;

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
    private final Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //      APIs

    //public JSONPlaceHolderApi getJSONApi() {
    //    return mRetrofit.create(JSONPlaceHolderApi.class);
    //}

    public UserAPI getUserAPI(){
        return mRetrofit.create(UserAPI.class);
    }

    public HabitsAPI getHabitsAPI(){
        return mRetrofit.create(HabitsAPI.class);
    }

    public TasksAPI getTasksAPI(){
        return mRetrofit.create(TasksAPI.class);
    }

    public ItemsAPI getItemsAPI(){
        return mRetrofit.create(ItemsAPI.class);
    }

    public NewsAPI getNewsAPI(){
        return mRetrofit.create(NewsAPI.class);
    }

    public UserTeachersAPI getUserTeachersAPI(){
        return mRetrofit.create(UserTeachersAPI.class);
    }

    public UserStudentsAPI getUserStudentsAPI(){
        return mRetrofit.create(UserStudentsAPI.class);
    }

    //      -------APIs

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
}
