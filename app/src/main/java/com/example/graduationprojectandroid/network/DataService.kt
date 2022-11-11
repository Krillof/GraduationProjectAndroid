package com.example.graduationprojectandroid.network

import android.content.Context
import android.widget.ImageView
import com.example.graduationprojectandroid.GenericsAsTypeParameter
import com.example.graduationprojectandroid.PreferencesService
import com.example.graduationprojectandroid.fragments.for_changing_avatar.AvatarParts
import com.example.graduationprojectandroid.data.Items.*
import com.example.graduationprojectandroid.data.States.HabitDoneStates
import com.example.graduationprojectandroid.network.endpoints.SimpleServerAnswer
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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




    fun checkSimpleAnswer(answer: SimpleServerAnswer) : Boolean {
        if (answer.errors == ""){
            openErrorPage()
        }
        return (answer.errors == "")
    }

    fun standardStringAnswer(awaiter: (String) -> Unit) : DataCallback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer>{
            checkSimpleAnswer(it)
            awaiter(it.data)
        }
    }

    fun standardBooleanAnswer(awaiter: (Boolean) -> Unit) : DataCallback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer>{
            checkSimpleAnswer(it)
            awaiter(it.data == "1")
        }
    }

    fun standardVoidAnswer(awaiter: () -> Unit) : DataCallback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer>{
            checkSimpleAnswer(it)
            awaiter()
        }
    }

    fun standardIntAnswer(awaiter: (Int) -> Unit) : DataCallback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer>{
            checkSimpleAnswer(it)
            awaiter(it.data.toInt())
        }
    }

    fun <E> standardListAnswer(awaiter: (ArrayList<E>) -> Unit) : DataCallback<SimpleServerAnswer> {
        return DataCallback<SimpleServerAnswer> {
            checkSimpleAnswer(it)
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






    // after entering in
    fun saveToken(context: Context, token: String){
        PreferencesService.saveLogin(context, token)
        //TODO: think about it
    }


    fun getUserToken() : String{
        // TODO: храни токен на телефоне
        return "ttt";
    }







    fun getUserData(awaiter: (UserData) -> Unit) {
        networkService.userAPI.getUserData(getUserToken()).enqueue(
            DataCallback<UserData>(awaiter)
        )
    }

    fun registerUser(login: String, password: String, awaiter: (String)->Unit){
        networkService.userAPI.register(login, password).enqueue(standardStringAnswer(awaiter))
    }

    // use in LoginActivity
    fun checkLoginUniquness(login: String, awaiter: (error: String)->Unit){
        networkService.userAPI.isLoginUnique(login).enqueue(standardStringAnswer(awaiter))
    }

    // use in LoginActivity
    fun checkIsPasswordOK(password: String, awaiter: (error: String)->Unit){
        networkService.userAPI.isLoginUnique(password).enqueue(standardStringAnswer(awaiter))
    }

    // use in LoginActivity
    fun checkIsLoginExists(login: String, awaiter: (error: String)->Unit){
        networkService.userAPI.isLoginExists(login).enqueue(standardStringAnswer(awaiter))
    }

    // use in LoginActivity
    fun tryEnter(login: String, password: String, awaiter: (error: String)->Unit){
        networkService.userAPI.enter(login, password).enqueue(standardStringAnswer(awaiter))
    }

    // when enter in app, to not login again
    fun isLogined(awaiter: (Boolean)->Unit) {
        networkService.userAPI.checkToken(getUserToken()).enqueue(standardBooleanAnswer(awaiter))
    }








    fun changedAvatar(chosenParts: Array<Int>, avatarName: String, awaiter: ()->Unit){
        networkService.userAPI.changedAvatar(
            chosenParts.toCollection(ArrayList()),
            avatarName,
            getUserToken()
        ).enqueue(
            standardVoidAnswer(awaiter)
        )
    }










    fun setHabitState(id: Int, state: HabitDoneStates){
        networkService.habitsAPI.setHabitState(id, state.type, getUserToken()).enqueue(
            standardVoidAnswer() {}
        )
    }

    fun createHabit(habit: Habit, awaiter: (error: String) -> Unit){
        networkService.habitsAPI.createHabit(habit, getUserToken())
            .enqueue(standardStringAnswer(awaiter))
    }

    fun editHabit(habit: Habit, awaiter: (error: String) -> Unit){
        networkService.habitsAPI.editHabit(habit, getUserToken())
            .enqueue(standardStringAnswer(awaiter))
    }

    fun getHabits(loginFrom: String, loginTo: String, awaiter: (ArrayList<Habit>)->Unit) {
        networkService.habitsAPI.getHabits(
            loginFrom,
            loginTo,
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }












    fun setTaskState(id: Int, state: Boolean){
        networkService.tasksAPI.setTaskState(id, if (state) 1 else 0, getUserToken())
            .enqueue(standardVoidAnswer() {})
    }

    fun createTask(task: Task, awaiter: (error: String) -> Unit){
        networkService.tasksAPI.createTask(task, getUserToken())
            .enqueue(standardStringAnswer(awaiter))
    }

    fun editTask(task: Task, awaiter: (error: String) -> Unit){
        networkService.tasksAPI.editTask(task, getUserToken())
            .enqueue(standardStringAnswer(awaiter))
    }

    fun getTasks(loginFrom:String, loginTo: String, awaiter: (ArrayList<Task>)->Unit){
        networkService.tasksAPI.getTasks(
            loginFrom,
            loginTo,
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }












    fun getMarketItems(awaiter: (ArrayList<InventoryItem>) -> Unit){
        networkService.itemsAPI.getMarketItems(
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun getInventoryItems(awaiter: (ArrayList<InventoryItem>)->Unit ){
        networkService.itemsAPI.getInventoryItems(
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun getNews(awaiter: (ArrayList<NewsItem>)->Unit){
        networkService.newsAPI.news.enqueue(standardListAnswer(awaiter))
    }

    fun getNewsItemById(id: Int, awaiter: (NewsItem)->Unit){
        networkService.newsAPI.getNewsItem(id).enqueue(
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
        networkService.userAPI.checkAvatarName(avatarName)
            .enqueue(standardStringAnswer(awaiter))
    }

    fun getAmountOfOneAvatarPartType(ap: AvatarParts, awaiter: (Int) -> Unit){
        networkService.userAPI.getAmountOfOneAvatarPartType(ap.number.toString())
            .enqueue(standardIntAnswer(awaiter))
    }






    fun getUserTeachers(awaiter: (ArrayList<TeacherItem>) -> Unit){
        networkService.userTeachersAPI.getUserTeachers(
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun getTeachersToFind(loginStartsWith: String, awaiter: (ArrayList<TeacherItem>) -> Unit){
        networkService.userTeachersAPI.getTeachersToFind(
            loginStartsWith,
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun abandonStudyWithTeacher(login: String, awaiter: () -> Unit){
        networkService.userTeachersAPI.abandonStudyWithTeacher(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }

    fun makeStudyRequest(login: String, awaiter: ()->Unit){
        networkService.userTeachersAPI.makeStudyRequest(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }







    fun getUserStudents(awaiter: (ArrayList<StudentItem>) -> Unit){
        networkService.userStudentsAPI.getUserStudents(
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun getStudyRequests(loginStartsWith: String, awaiter: (ArrayList<StudentItem>) -> Unit){
        networkService.userStudentsAPI.getStudyRequests(
            loginStartsWith,
            getUserToken()
        ).enqueue(standardListAnswer(awaiter))
    }

    fun abandonStudyWithStudent(login: String, awaiter: () -> Unit){
        networkService.userStudentsAPI.abandonStudyWithStudent(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }

    fun abandonStudyRequest(login: String, awaiter: () -> Unit){
        networkService.userStudentsAPI.abandonStudyRequest(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }

    fun acceptStudyRequest(login: String, awaiter: ()->Unit){
        networkService.userStudentsAPI.acceptStudyRequest(
            login,
            getUserToken()
        ).enqueue(standardVoidAnswer(awaiter))
    }
}