package com.example.graduationprojectandroid.network

import android.content.Context
import android.widget.ImageView
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.PreferencesService
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.for_changing_avatar.AvatarParts
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.*
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.TeacherItem

object DataService {

    private val networkService: NetworkService = NetworkService.getInstance()

    fun setPictureById(picture_id: Int, picture_view: ImageView){
        networkService.setPictureById(picture_id, picture_view)
    }

    fun setOtherUserFacePicture(user_login: String, picture_view: ImageView){
        //TODO
    }

    fun getUserData(awaiter: (UserData) -> Unit) {
        networkService.getUserData(awaiter)
    }

    fun changedAvatar(chosenParts: Array<Int>, avatarName: String, awaiter: ()->Unit){
        networkService.changedAvatar(chosenParts.toList(), avatarName, awaiter)
        awaiter()
    }

    fun setPictureOfAvatarPart(type: Int, id: Int, view: ImageView){
        networkService.setPictureOfAvatarPart(type, id, view);
    }

    fun setHabitState(id: Int, state: HabitDoneStates){
        //TODO: Send to server
    }

    fun setTaskState(id: Int, state: Boolean){
        //TODO: Send to server
    }

    fun getNewIdForTask(awaiter: (Int) -> Unit){
        //TODO: DELETE THIS FUNCTION - ID MUST BE GIVEN ON SERVER
        //TODO: Ask from server for new ID
        awaiter(1)
    }

    fun getNewIdForHabit(awaiter: (Int) -> Unit){
        //TODO: DELETE THIS FUNCTION - ID MUST BE GIVEN ON SERVER
        //TODO: Ask from server for new ID
        awaiter(1)
    }

    fun sendHabit(habit: Habit, awaiter: (error: String) -> Unit){
        //TODO: Just send - if id other - new, else - old
        awaiter("Checking error dialogue")
    }

    fun sendTask(task: Task, awaiter: (error: String) -> Unit) {
        //TODO: Just send - if id other - new, else - old
        awaiter("Checking error dialogue")
    }

    fun getTasks(loginFrom:String, loginTo: String, awaiter: (ArrayList<Task>)->Unit){
        var tasksList = ArrayList<Task>()
        //TODO: get tasks from server
        //TODO: use awaiter

        //---------------------------------------
        //TMP
        //val task1 =

        val sbtsks_strs1 = listOf("в тетради", "потом выучи текст", "приготовь картошку")

        var subtasks1: MutableList<Subtask> = MutableList(
            3
        ){ Subtask(false, sbtsks_strs1[it]) }

        var task1 = Task(
            1,
            "", "",
            "Сделать англ",
            "Очень надо, у меня не зачёт",
            subtasks1
        )

        task1.difficulty = Difficulty.hard


        val sbtsks_strs2 = listOf(
            "подумать о картошке",
            "купить картошку",
            "приготовить картошку",
            "сделать из неё торт",
            "подумать о картошке 2",
            "купить картошку 2",
            "приготовить картошку 2",
            "сделать из неё торт 2"
        )

        var subtasks2: MutableList<Subtask> = MutableList(
            sbtsks_strs2.size
        ) { index -> Subtask(false, sbtsks_strs2[index]) }

        var task2 = Task(
            2,
            "", "",
            "Приготовиться к гостям",
            "Гости-то придут",
            subtasks2
        )

        task2.setFullDone(true)
        task2.difficulty = Difficulty.normal

        var subtasks3: MutableList<Subtask> = MutableList(
            0
        ) { index -> Subtask(false, "") }

        var task3 = Task(
            3,
            "", "",
            "Поесть",
            "Очень важно",
            subtasks3
        )

        task3.difficulty = Difficulty.easy



        tasksList.add(task1)
        tasksList.add(task2)
        tasksList.add(task3)

        //TMP
        //-------------------------------------

        awaiter(tasksList)
    }

    fun getHabits(loginFrom: String, loginTo: String, awaiter: (ArrayList<Habit>)->Unit) {
        val habits: ArrayList<Habit> = ArrayList()
        //TODO: Make normal habits load
        //TODO: Use awaiter

        //--------------------------------------------
        //TMP

        val habit1 = Habit(
            1,
            "", "",
            "Бегать по утрам",
            "С 6:30 до 7:00, не забыть \n разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit2 = Habit(
            2,
            "", "",
            "Учить английский",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit3 = Habit(
            3,
            "", "",
            "Бросить пить",
            "Убиваем зеленаго змия весело \n и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )
        val habit4 = Habit(
            4,
            "", "",
            "Точно не йога",
            "С 6:30 до 7:00, не забыть \n разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit5 = Habit(
            5,
            "", "",
            "Учить немецкий",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit6 = Habit(
            6,
            "", "",
            "Бросить курить",
            "Убиваем зеленаго змия весело \n и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )

        val habit7 = Habit(
            7,
            "", "",
            "Спать",
            "С 6:30 до 7:00, не забыть \n разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit8 = Habit(
            8,
            "", "",
            "Учить французский",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit9 = Habit(
            9,
            "", "",
            "Бросить есть сладкое",
            "Убиваем зеленаго змия весело \n и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )



        habits.add(habit1)
        habits.add(habit2)
        habits.add(habit3)
        habits.add(habit4)
        habits.add(habit5)
        habits.add(habit6)
        habits.add(habit7)
        habits.add(habit8)
        habits.add(habit9)


        //TMP
        //----------------------------------------------

        awaiter(habits)
    }

    fun getMarketItems(awaiter: (ArrayList<Item>) -> Unit){
        //TODO: Это точно нормально?
        NetworkService.getInstance().getItemsForMarket {
            val items = ArrayList<Item>()
            items.addAll(it)
            awaiter(items)
        }

    }

    fun getInventoryItems(awaiter: (ArrayList<Item>)->Unit ){
        //TODO: Это точно нормально?
        NetworkService.getInstance().getItemsForInventory{
            val items = ArrayList<Item>()
            items.addAll(it)
            awaiter(items)
        }
    }

    fun getNews(awaiter: (ArrayList<NewsItem>)->Unit){
        //TMP
        val news: ArrayList<NewsItem> = ArrayList()
        val n1: NewsItem = NewsItem(
            1, "20.12.2021", "Новогодний адвент",
            "Хо-хо-хо! В страну Do заглянул Санта и оставил подарки нашим пользовательям! " +
                    "Скорее скачивай последнюю версию!", ""
            )

        val n2: NewsItem = NewsItem(
            2, "28.11.2021", "Версия 3.0.1.2",
            "Почищенны баги, добавлен новый функционал и вот это вот все прочее.", ""
        )

        val n3: NewsItem = NewsItem(
            3, "27.11.2021", "Версия 3.0.1.1",
            "Почищенны баги, добавлен новый функционал и вот это вот все прочее.", ""
        )

        news.add(n1)
        news.add(n2)
        news.add(n3)

        //TMP
        awaiter(news)
    }

    fun getNewsItemById(id: Int, awaiter: (NewsItem)->Unit){
        //TMP
        val news: ArrayList<NewsItem> = ArrayList()
        val n1: NewsItem = NewsItem(
            1, "20.12.2021", "Новогодний адвент",
            "Хо-хо-хо! В страну Do заглянул Санта и оставил подарки нашим пользовательям! " +
                    "Скорее скачивай последнюю версию!",
            "1 " + App.getAppResources().getString(R.string.large_text)
        )

        val n2: NewsItem = NewsItem(
            2, "28.11.2021", "Версия 3.0.1.2",
            "Почищенны баги, добавлен новый функционал и вот это вот все прочее.",
            "2 " + App.getAppResources().getString(R.string.large_text)
        )

        val n3: NewsItem = NewsItem(
            3, "27.11.2021", "Версия 3.0.1.1",
            "Почищенны баги, добавлен новый функционал и вот это вот все прочее.",
            "3 " + App.getAppResources().getString(R.string.large_text)
        )

        news.add(n1)
        news.add(n2)
        news.add(n3)

        //TMP
        awaiter(news[id-1])
    }

    fun registerUser(login: String, password: String, awaiter: (String)->Unit){
        networkService.registerUser(login, password, awaiter)
    }

    // use in LoginActivity
    fun checkNewLogin(login: String, awaiter: (String)->Unit){
        //TODO: check validation and is it new (on server)

        awaiter("")
    }

    // use in LoginActivity
    fun checkPassword(password: String, awaiter: (String)->Unit){
        //TODO: check validation (on server)

        awaiter("")
    }

    // use in LoginActivity
    fun tryLogin(login: String, awaiter: (String)->Unit){
        //TODO: check, is there this login

        awaiter("")
    }

    // use in LoginActivity
    fun tryEnter(login: String, password: String, awaiter: (String)->Unit){
        //TODO: try enter with this password and login

        awaiter("")
    }

    // when enter in app, to not login again
    fun isLogined(context: Context, awaiter: (Boolean)->Unit) {
        //TODO: check on server, if is logined
        //TODO: somewhere must be awaiter(PreferencesService.loadLogin(context) == "")
        awaiter(false)
    }

    // after entering in
    fun saveLogin(context: Context, login: String){
        PreferencesService.saveLogin(context, login)
    }

    fun checkAvatarName(awaiter: (String)->Unit){
        //TODO: send to check to server, from characterData
        awaiter("")
    }

    // get body parts for avatar from server
    fun getAvatarPartPictureForWearing(view: ImageView, ap: AvatarParts, id: Int){
        networkService.setPictureOfAvatarPart(ap.number, id, view)
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