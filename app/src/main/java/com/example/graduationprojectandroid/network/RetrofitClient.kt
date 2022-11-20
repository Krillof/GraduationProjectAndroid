package com.example.graduationprojectandroid.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import com.example.graduationprojectandroid.network.endpoints.APIs.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.URL
import java.util.concurrent.TimeUnit


object RetrofitClient {
    //Retrofit
    private const val BASE_URL = "https://graduationprojectwebapi.azurewebsites.net/"
    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
    private var mRetrofit: Retrofit
        = Retrofit.Builder()
        .baseUrl(BASE_URL + "api/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()



    //      APIs
    fun getUserAPI(): UserAPI {
        return mRetrofit.create(UserAPI::class.java)
    }

    fun getHabitsAPI(): HabitsAPI {
        return mRetrofit.create(HabitsAPI::class.java)
    }

    fun getTasksAPI(): TasksAPI {
        return mRetrofit.create(TasksAPI::class.java)
    }

    fun getItemsAPI(): ItemsAPI {
        return mRetrofit.create(ItemsAPI::class.java)
    }

    fun getNewsAPI(): NewsAPI {
        return mRetrofit.create(NewsAPI::class.java)
    }

    fun getUserTeachersAPI(): UserTeachersAPI {
        return mRetrofit.create(UserTeachersAPI::class.java)
    }

    fun getUserStudentsAPI(): UserStudentsAPI {
        return mRetrofit.create(UserStudentsAPI::class.java)
    }

    //      -------APIs

    //--------Retrofit
    //getting picture
    private class DownloadImageTask(var bmImage: ImageView) :
        AsyncTask<String?, Void?, Bitmap?>() {

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }

        override fun doInBackground(vararg urls: String?): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val inputStream = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.e("Ошибка передачи изображения", e.message!!)
                e.printStackTrace()
            }
            return mIcon11
        }
    }



    private fun setPictureByURL(URL: String?, view: ImageView) {
        DownloadImageTask(view).execute(URL)
    }

    fun getAvatarPartPicture(id: Int, view: ImageView){
        setPictureByURL(BASE_URL + "files/getavatarpartpicture?id="+id.toString(), view)
    }

    fun getAvatarPartPictureByTypeAndNumber(type: Int, number: Int, view: ImageView){
        setPictureByURL(BASE_URL
                + "files/getavatarpartpicturebytypeandnumber?type=" + type.toString()
                + "&number=" + number.toString(),
            view)
    }

    fun getMarketItemPicture(id: Int, view: ImageView){
        setPictureByURL(BASE_URL + "files/getmarketitempicture?id="+id.toString(), view)
    }

    fun getFacePicture(login: String, view:ImageView){
        setPictureByURL(BASE_URL + "files/getfacepicture?login="+login, view)
    }

    //---------getting picture
}