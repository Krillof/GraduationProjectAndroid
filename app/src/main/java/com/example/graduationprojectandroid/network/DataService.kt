package com.example.graduationprojectandroid.network

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.GenericsAsTypeParameter
import com.example.graduationprojectandroid.PreferencesService
import com.example.graduationprojectandroid.fragments.for_changing_avatar.AvatarParts
import com.example.graduationprojectandroid.data.Items.*
import com.example.graduationprojectandroid.data.States.HabitDoneStates
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer
import com.example.graduationprojectandroid.network.endpoints.UserValidationAnswer
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataService {

    fun openErrorPage(message: String){
        //TODO
        Toast.makeText(App.getAppContext(), "Error on server", Toast.LENGTH_SHORT).show()
        Log.println(Log.ERROR, "Callback error", message)
    }

    class DataCallback<T>(
        val awaiter: (T)->Unit
    ) : Callback<T>{
        override fun onResponse(call: Call<T>,  response: Response<T>){
            if (response.isSuccessful){
                awaiter(response.body()!!)
            } else {
                openErrorPage("Response wasn't succesfull: "
                        + response.message() + " " + response.code())
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            openErrorPage("Response was failed: " + t.message)
        }
    }




    private fun standardStringAnswer(awaiter: (String) -> Unit)
    : DataCallback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer>{
            it.checkErrors()
            awaiter(it.data!!)
        }
    }

    private fun standardUserValidationAnswer(
        awaiter: (UserValidationAnswer.ValidationData)->Unit
    ) : Callback<UserValidationAnswer> {
        return DataCallback<UserValidationAnswer>{
            it.checkErrors()
            awaiter(it.data!!)
        }
    }

    private fun standardBooleanAnswer(awaiter: (Boolean) -> Unit) : Callback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer>{
            it.checkErrors()
            awaiter(it.data == "1")
        }
    }

    private fun standardVoidAnswer(awaiter: () -> Unit) : Callback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer>{
            it.checkErrors()
            awaiter()
        }
    }

    private fun standardIntAnswer(awaiter: (Int) -> Unit) : Callback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer>{
            it.checkErrors()
            awaiter(it.data!!.toInt())
        }
    }

    private fun <E> standardListAnswer(awaiter: (ArrayList<E>) -> Unit) : Callback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer> {
            it.checkErrors()
            awaiter(
                Gson().fromJson(
                    it.data,
                    object : GenericsAsTypeParameter<ArrayList<E>>() {}.type
                )
            )
        }
    }

    /*
        // IF standardListAnswer won't work write:


        data class HabitsArrayJson(
            var data: ArrayList<Habit>
        ) {}

        //..................

        .....enqueue(
            standardStringAnswer(){
                awaiter(
                    Gson().fromJson(
                        it,
                        HabitsArrayJson::class.java
                    ).data
                )
            }
        )
    */








    fun setPictureById(picture_id: Int, picture_view: ImageView){
        RetrofitClient.setPictureById(picture_id, picture_view)
    }

    fun setPictureOfAvatarPart(type: Int, id: Int, view: ImageView){
        RetrofitClient.setPictureOfAvatarPart(type, id, view);
    }

    fun setOtherUserFacePicture(user_login: String, picture_view: ImageView){
        //TODO fun to get picture by login
    }

    // get body parts for avatar from server
    fun getAvatarPartPictureForWearing(view: ImageView, ap: AvatarParts, id: Int){
        RetrofitClient.setPictureOfAvatarPart(ap.number, id, view)
    }






    // after entering in
    fun saveToken(token: String){
        PreferencesService.saveToken(token)
    }

    private var token: String? = null


    private fun getUserToken() : String {
        return token!!
    }

    // when enter in app, to not login again
    fun checkToken(awaiter: (Boolean)->Unit) {
        token = PreferencesService.loadToken()
        RetrofitClient.getUserAPI().checkToken(getUserToken())
            .enqueue(standardBooleanAnswer(awaiter))
    }







    fun getUserData(awaiter: (UserData) -> Unit) {
        RetrofitClient.getUserAPI().getUserData(getUserToken()).enqueue(
            DataCallback<UserData>(awaiter)
        )
    }

    fun tryRegister(
        login: String, password: String,
        awaiter: (UserValidationAnswer.ValidationData)->Unit
    ){
        RetrofitClient.getUserAPI().tryRegister(login, password)
            .enqueue(
                standardUserValidationAnswer(awaiter)
            )
    }

    // use in LoginActivity
    fun tryEnter(
        login: String, password: String,
        awaiter: (UserValidationAnswer.ValidationData)->Unit
    ){
        RetrofitClient.getUserAPI().tryEnter(login, password)
            .enqueue(standardUserValidationAnswer(awaiter))
    }




    fun changedAvatar(chosenParts: Array<Int>, avatarName: String, awaiter: ()->Unit){
        RetrofitClient.getUserAPI().changedAvatar(
            chosenParts.toCollection(ArrayList()),
            avatarName,
            getUserToken()
        ).enqueue(
            standardVoidAnswer(awaiter)
        )
    }










    fun setHabitState(id: Int, state: HabitDoneStates){
        RetrofitClient.getHabitsAPI().setHabitState(id, state.type, getUserToken()).enqueue(
            standardVoidAnswer() {}
        )
    }

    fun createHabit(habit: Habit, awaiter: (error: String) -> Unit){
        RetrofitClient.getHabitsAPI().createHabit(habit, getUserToken())
            .enqueue(standardStringAnswer(awaiter))
    }

    fun editHabit(habit: Habit, awaiter: (error: String) -> Unit){
        RetrofitClient.getHabitsAPI().editHabit(habit, getUserToken())
            .enqueue(standardStringAnswer(awaiter))
    }

    fun getHabits(loginFrom: String, loginTo: String, awaiter: (ArrayList<Habit>)->Unit) {
        RetrofitClient.getHabitsAPI().getHabits(
            loginFrom,
            loginTo,
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }












    fun setTaskState(id: Int, state: Boolean){
        RetrofitClient.getTasksAPI().setTaskState(id, if (state) 1 else 0, getUserToken())
            .enqueue(standardVoidAnswer() {})
    }

    fun createTask(task: Task, awaiter: (error: String) -> Unit){
        RetrofitClient.getTasksAPI().createTask(task, getUserToken())
            .enqueue(standardStringAnswer(awaiter))
    }

    fun editTask(task: Task, awaiter: (error: String) -> Unit){
        RetrofitClient.getTasksAPI().editTask(task, getUserToken())
            .enqueue(standardStringAnswer(awaiter))
    }

    fun getTasks(loginFrom:String, loginTo: String, awaiter: (ArrayList<Task>)->Unit){
        RetrofitClient.getTasksAPI().getTasks(
            loginFrom,
            loginTo,
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }












    fun getMarketItems(awaiter: (ArrayList<InventoryItem>) -> Unit){
        RetrofitClient.getItemsAPI().getMarketItems(
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun getInventoryItems(awaiter: (ArrayList<InventoryItem>)->Unit ){
        RetrofitClient.getItemsAPI().getInventoryItems(
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }






    fun getNews(awaiter: (ArrayList<NewsItem>)->Unit){
        RetrofitClient.getNewsAPI().news.enqueue(standardListAnswer(awaiter))
    }

    fun getNewsItemById(id: Int, awaiter: (NewsItem)->Unit){
        RetrofitClient.getNewsAPI().getNewsItem(id).enqueue(
            standardStringAnswer(){
                awaiter(
                    Gson().fromJson(
                        it,
                        NewsItem::class.java
                    )
                )
            }
        )
    }





    fun checkAvatarName(avatarName: String, awaiter: (String)->Unit){
        RetrofitClient.getUserAPI().checkAvatarName(avatarName)
            .enqueue(standardStringAnswer(awaiter))
    }

    fun getAmountOfOneTypeAvatarParts(ap: AvatarParts, awaiter: (Int) -> Unit){
        RetrofitClient.getUserAPI().getAmountOfOneTypeAvatarParts(ap.number.toString())
            .enqueue(standardIntAnswer(awaiter))
    }






    fun getUserTeachers(awaiter: (ArrayList<TeacherItem>) -> Unit){
        RetrofitClient.getUserTeachersAPI().getUserTeachers(
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun getTeachersToFind(loginStartsWith: String, awaiter: (ArrayList<TeacherItem>) -> Unit){
        RetrofitClient.getUserTeachersAPI().getTeachersToFind(
            loginStartsWith,
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun abandonStudyWithTeacher(login: String, awaiter: () -> Unit){
        RetrofitClient.getUserTeachersAPI().abandonStudyWithTeacher(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }

    fun makeStudyRequest(login: String, awaiter: ()->Unit){
        RetrofitClient.getUserTeachersAPI().makeStudyRequest(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }







    fun getUserStudents(awaiter: (ArrayList<StudentItem>) -> Unit){
        RetrofitClient.getUserStudentsAPI().getUserStudents(
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun getStudyRequests(loginStartsWith: String, awaiter: (ArrayList<StudentItem>) -> Unit){
        RetrofitClient.getUserStudentsAPI().getStudyRequests(
            loginStartsWith,
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun abandonStudyWithStudent(login: String, awaiter: () -> Unit){
        RetrofitClient.getUserStudentsAPI().abandonStudyWithStudent(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }

    fun abandonStudyRequest(login: String, awaiter: () -> Unit){
        RetrofitClient.getUserStudentsAPI().abandonStudyRequest(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }

    fun acceptStudyRequest(login: String, awaiter: ()->Unit){
        RetrofitClient.getUserStudentsAPI().acceptStudyRequest(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }
}
