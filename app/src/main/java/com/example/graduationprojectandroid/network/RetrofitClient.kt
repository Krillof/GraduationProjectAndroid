package com.example.graduationprojectandroid.network

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import com.example.graduationprojectandroid.network.endpoints.APIs.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

object RetrofitClient {
    //Retrofit
    private const val BASE_URL = "https://graduationprojectdo.azurewebsites.net/api"
    private var mRetrofit: Retrofit
        = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
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
                val `in` = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Ошибка передачи изображения", e.message!!)
                e.printStackTrace()
            }
            return mIcon11
        }
    }

    fun setPictureByURL(URL: String?, view: ImageView) {
        DownloadImageTask(view)
            .execute("https://awontis.com/wp-content/uploads/revslider/home-business-slide3/icon-slide4.png")
    }

    fun setPictureById(id: Int, view: ImageView) {
        setPictureByURL(BASE_URL + "", view)
    }

    fun setPictureOfAvatarPart(type: Int, id: Int, view: ImageView) {
        //TODO: get from SERVER choosing_pictures for avatar parts
        setPictureByURL(BASE_URL + "", view)
    }

    //---------getting picture
}