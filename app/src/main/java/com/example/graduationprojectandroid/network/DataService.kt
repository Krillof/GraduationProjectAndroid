package com.example.graduationprojectandroid.network

import android.content.Context
import android.widget.ImageView
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.PreferencesService
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.for_changing_avatar.AvatarParts
import com.example.graduationprojectandroid.data.Items.*
import com.example.graduationprojectandroid.data.States.Difficulty
import com.example.graduationprojectandroid.data.States.HabitDoneStates
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import kotlin.reflect.typeOf

object DataService {

    private val networkService: NetworkService = NetworkService.getInstance()

    fun openErrorPage(){
        //TODO
    }

    class DataCallback<T>(
        val awaiter: (T)->Unit
    ) : Callback<T>{
        override fun onResponse(call: Call<T>,  response: Response<T>){
            if (response.isSuccessful){
                awaiter(response.body()!!)
            } else {
                openErrorPage()
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            openErrorPage()
        }
    }

    data class HabitsArrayJson (
        var data: ArrayList<Habit>
    ) {}

    data class TasksArrayJson (
        var data: ArrayList<Task>
    ) {}

    data class ItemsArrayJson (
        var data: ArrayList<InventoryItem>
    ) {}

    data class NewsArrayJson (
        var data: ArrayList<NewsItem>
    ) {}





    fun setPictureById(picture_id: Int, picture_view: ImageView){
        networkService.setPictureById(picture_id, picture_view)
    }

    fun setPictureOfAvatarPart(type: Int, id: Int, view: ImageView){
        networkService.setPictureOfAvatarPart(type, id, view);
    }

    fun setOtherUserFacePicture(user_login: String, picture_view: ImageView){
        //TODO fun to get picture by login
    }

    // get body parts for avatar from server
    fun getAvatarPartPictureForWearing(view: ImageView, ap: AvatarParts, id: Int){
        networkService.setPictureOfAvatarPart(ap.number, id, view)
    }











    fun getUserLogin() : String{
        // TODO: храни логин на телефоне
        return "ttt";
    }

    fun getUserToken() : String{
        // TODO: храни токен на телефоне
        return "ttt";
    }







    fun getUserData(awaiter: (UserData) -> Unit) {
        networkService.userAPI.getUserData(getUserLogin(), getUserToken()).enqueue(
            DataCallback<UserData>(awaiter)
        )
    }

    fun registerUser(login: String, password: String, awaiter: (String)->Unit){
        networkService.userAPI.register(login, password).enqueue(
            // It was before a:
            // DataCallback<SimpleServerAnswer> { awaiter(it.answer) }
            DataCallback<String>(awaiter)
        )
    }

    // use in LoginActivity
    fun checkLoginUniquness(login: String, awaiter: (String)->Unit){
        networkService.userAPI.isLoginUnique(login).enqueue(
            DataCallback<String>(awaiter)
        )
    }

    // use in LoginActivity
    fun checkIsPasswordOK(password: String, awaiter: (String)->Unit){
        networkService.userAPI.isLoginUnique(password).enqueue(
            DataCallback<String>(awaiter)
        )
    }

    // use in LoginActivity
    fun checkIsLoginExists(login: String, awaiter: (String)->Unit){
        networkService.userAPI.isLoginExists(login).enqueue(
            DataCallback<String>(awaiter)
        )
    }

    // use in LoginActivity
    fun tryEnter(login: String, password: String, awaiter: (String)->Unit){
        networkService.userAPI.enter(login, password).enqueue(
            DataCallback<String>(awaiter)
        )
    }

    // when enter in app, to not login again
    fun isLogined(awaiter: (Boolean)->Unit) {
        networkService.userAPI.checkToken(getUserToken()).enqueue(
            DataCallback<Boolean>(awaiter)
        )
    }








    fun changedAvatar(chosenParts: Array<Int>, avatarName: String, awaiter: ()->Unit){
        val jsonObject = JsonObject()
        val chosenPartsToJson = Gson().toJsonTree(chosenParts)
        jsonObject.add("chosenParts", chosenPartsToJson) //Add Json Element in JsonObject
        jsonObject.addProperty("avatarName", avatarName)

        networkService.userAPI.changedAvatar(jsonObject, getUserToken()).enqueue(
            DataCallback<String>{ awaiter() }
        )
    }










    fun setHabitState(id: Int, state: HabitDoneStates){
        networkService.habitsAPI.setHabitState(id, state.type, getUserToken()).enqueue(
            DataCallback<String>{}
        )
    }

    fun createHabit(habit: Habit, awaiter: (error: String) -> Unit){
        val jsonObject = JsonObject()
        val habitToJson = Gson().toJsonTree(habit)
        jsonObject.add("habit", habitToJson)

        networkService.habitsAPI.createHabit(jsonObject, getUserToken()).enqueue(
            DataCallback<String>(awaiter)
        )
    }

    fun editHabit(habit: Habit, awaiter: (error: String) -> Unit){
        val jsonObject = JsonObject()
        val habitToJson = Gson().toJsonTree(habit)
        jsonObject.add("habit", habitToJson)

        networkService.habitsAPI.editHabit(jsonObject, getUserToken()).enqueue(
            DataCallback<String>(awaiter)
        )
    }

    fun getHabits(loginFrom: String, loginTo: String, awaiter: (ArrayList<Habit>)->Unit) {
        networkService.habitsAPI.getHabits(
            loginFrom,
            loginTo,
            getUserToken()
        ).enqueue(
            DataCallback<String>{
                awaiter(
                    Gson().fromJson(
                        it,
                        HabitsArrayJson::class.java
                    ).data
                )
            }
        )
    }










    fun setTaskState(id: Int, state: Boolean){
        networkService.tasksAPI.setTaskState(id, if (state) 1 else 0, getUserToken()).enqueue(
            DataCallback<String>{}
        )
    }

    fun createTask(task: Task, awaiter: (error: String) -> Unit){
        val jsonObject = JsonObject()
        val habitToJson = Gson().toJsonTree(task)
        jsonObject.add("task", habitToJson)

        networkService.tasksAPI.createTask(jsonObject, getUserToken()).enqueue(
            DataCallback<String>(awaiter)
        )
    }

    fun editTask(task: Task, awaiter: (error: String) -> Unit){
        val jsonObject = JsonObject()
        val habitToJson = Gson().toJsonTree(task)
        jsonObject.add("task", habitToJson)

        networkService.tasksAPI.editTask(jsonObject, getUserToken()).enqueue(
            DataCallback<String>(awaiter)
        )
    }

    fun getTasks(loginFrom:String, loginTo: String, awaiter: (ArrayList<Task>)->Unit){
        networkService.tasksAPI.getTasks(
            loginFrom,
            loginTo,
            getUserToken()
        ).enqueue(
            DataCallback<String>{
                awaiter(
                    Gson().fromJson(
                        it,
                        TasksArrayJson::class.java
                    ).data
                )
            }
        )
    }












    fun getMarketItems(awaiter: (ArrayList<InventoryItem>) -> Unit){
        networkService.itemsAPI.getMarketItems(
            getUserToken()
        ).enqueue(
            DataCallback<String>{
                awaiter(
                    Gson().fromJson(
                        it,
                        ItemsArrayJson::class.java
                    ).data
                )
            }
        )
    }

    fun getInventoryItems(awaiter: (ArrayList<InventoryItem>)->Unit ){
        networkService.itemsAPI.getInventoryItems(
            getUserToken()
        ).enqueue(
            DataCallback<String>{
                awaiter(
                    Gson().fromJson(
                        it,
                        ItemsArrayJson::class.java
                    ).data
                )
            }
        )
    }

    fun getNews(awaiter: (ArrayList<NewsItem>)->Unit){
        networkService.newsAPI.news.enqueue(
            DataCallback<String>{
                awaiter(
                    Gson().fromJson(
                        it,
                        NewsArrayJson::class.java
                    ).data
                )
            }
        )
    }

    fun getNewsItemById(id: Int, awaiter: (NewsItem)->Unit){
        networkService.newsAPI.getNewsItem(id).enqueue(
            DataCallback<String>{
                awaiter(
                    Gson().fromJson(
                        it,
                        NewsItem::class.java
                    )
                )
            }
        )
    }



    // after entering in
    fun saveLogin(context: Context, login: String){
        PreferencesService.saveLogin(context, login)
        //TODO: think about it
    }

    fun checkAvatarName(awaiter: (String)->Unit){
        networkService.userAPI.checkAvatarName() //TODO: Доделай
    }







    fun getAmountOfOneAvatarPartType(ap: AvatarParts, awaiter: (Int) -> Unit){
        networkService.getAmountOfOneAvatarPartType(ap, awaiter)
    }

    fun getUserTeachers(awaiter: (ArrayList<TeacherItem>) -> Unit){
        var list: ArrayList<TeacherItem> = ArrayList()
        //TODO: Это временное заполнение
        list.add(TeacherItem("Анна Павловна"))
        list.add(TeacherItem("Сергей Николаевич"))
        list.add(TeacherItem("1111111111111111111111111"))
        list.add(TeacherItem("2222222222222222222222222"))
        list.add(TeacherItem("3333333333333333333333"))
        list.add(TeacherItem("sdfhgdf"))
        awaiter(list)
    }

    fun getTeachersToFind(loginStartsWith: String, awaiter: (ArrayList<TeacherItem>) -> Unit){
        var list: ArrayList<TeacherItem> = ArrayList()
        //TODO: Это временное заполнение
        list.add(TeacherItem("Анна Павловна"))
        list.add(TeacherItem("Сергей Николаевич"))
        list.add(TeacherItem("1111111111111111111111111"))
        list.add(TeacherItem("2222222222222222222222222"))
        list.add(TeacherItem("3333333333333333333333"))
        list.add(TeacherItem("sdfhgdf"))
        awaiter(list)
    }

    fun getUserStudents(awaiter: (ArrayList<StudentItem>) -> Unit){
        var list: ArrayList<StudentItem> = ArrayList()
        //TODO: Это временное заполнение
        list.add(StudentItem("Анна Павловна"))
        list.add(StudentItem("Сергей Николаевич"))
        list.add(StudentItem("1111111111111111111111111"))
        list.add(StudentItem("2222222222222222222222222"))
        list.add(StudentItem("3333333333333333333333"))
        list.add(StudentItem("sdfhgdf"))
        awaiter(list)
    }

    fun getStudyRequests(loginStartsWith: String, awaiter: (ArrayList<StudentItem>) -> Unit){
        var list: ArrayList<StudentItem> = ArrayList()
        //TODO: Это временное заполнение
        list.add(StudentItem("Анна Павловна"))
        list.add(StudentItem("Сергей Николаевич"))
        list.add(StudentItem("1111111111111111111111111"))
        list.add(StudentItem("2222222222222222222222222"))
        list.add(StudentItem("3333333333333333333333"))
        list.add(StudentItem("sdfhgdf"))
        awaiter(list)
    }

    fun abandonStudyWithTeacher(login: String, awaiter: () -> Unit){
        //TODO: доделать
        awaiter()
    }

    fun abandonStudyWithStudent(login: String, awaiter: () -> Unit){
        //TODO: доделать
        awaiter()
    }

    fun abandonStudyRequest(login: String, awaiter: () -> Unit){
        //TODO: доделать
        awaiter()
    }

    fun makeStudyRequest(login: String, awaiter: ()->Unit){
        //TODO: доделать
        awaiter()
    }

    fun acceptStudyRequest(login: String, awaiter: ()->Unit){
        //TODO: доделать
        awaiter()
    }
}