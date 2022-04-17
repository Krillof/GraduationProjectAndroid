package com.example.graduationprojectandroid.network

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.example.graduationprojectandroid.PreferencesService
import com.example.graduationprojectandroid.fragments.for_creating_avatar.AvatarParts
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.*

object DataService {

    private var characterData: CharacterData
    private val networkService: NetworkService

    init {
        networkService = NetworkService.getInstance()
        characterData = getCharacterData()
    }

    fun getCharacterData(): CharacterData{
        //TODO: if character data updated - ask server
        // else just return data from files
        if (true){
            characterData = CharacterData(
                "abc", "Avocado",
                129, 65, 100, 220, 378,
                13, 1, 1, 1, 1,
                0, 0, 0
            )
        }
        return characterData
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

    fun getTasks(awaiter: (ArrayList<Task>)->Unit){
        var tasksList = ArrayList<Task>()
        //TODO: get tasks from server by user's login
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

    fun getHabits(awaiter: (ArrayList<Habit>)->Unit) {
        val habits: ArrayList<Habit> = ArrayList()
        //TODO: Make normal habits load
        //TODO: Use awaiter

        //--------------------------------------------
        //TMP

        val habit1 = Habit(
            1,
            "Бегать по утрам",
            "С 6:30 до 7:00, не забыть \n разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit2 = Habit(
            2,
            "Учить английский",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit3 = Habit(
            3,
            "Бросить пить",
            "Убиваем зеленаго змия весело \n и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )
        val habit4 = Habit(
            4,
            "Точно не йога",
            "С 6:30 до 7:00, не забыть \n разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit5 = Habit(
            5,
            "Учить немецкий",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit6 = Habit(
            6,
            "Бросить курить",
            "Убиваем зеленаго змия весело \n и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )

        val habit7 = Habit(
            7,
            "Спать",
            "С 6:30 до 7:00, не забыть \n разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit8 = Habit(
            8,
            "Учить французский",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit9 = Habit(
            9,
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
        NetworkService.getInstance().getItemsForMarket {
            val items = ArrayList<Item>()
            items.addAll(it)

            for (i in 0..15){
                items.add(Item(0, i, 0, 0,  View.INVISIBLE))
            }

            awaiter(items)
        }

    }

    fun getInventoryItems(awaiter: (ArrayList<Item>)->Unit ){
        NetworkService.getInstance().getItemsForInventory{
            val items = ArrayList<Item>()
            items.addAll(it)

            for (i in 0..15){
                items.add(Item(0, i, 0, 0,  View.INVISIBLE))
            }

            awaiter(items)
        }
    }

    fun registerUser(password: String, awaiter: ()->Unit){
        // Login and avatar parts are in character data - get them here
        //TODO: Send to server
        //TMP___________________
        characterData = CharacterData(
            "abc", "Aristotle",
            19, 90, 100, 220, 378,
            5, 1, 1, 1, 1,
            0, 0, 0
        )
        //TMP___________________

        awaiter()
    }

    fun updateCharacterData(awaiter: ()->Unit){
        //TODO: Send to server (In another method! Not from registerUser!)
        //TMP___________________
        characterData = CharacterData(
            "abc", "Aristotle",
            19, 90, 100, 220, 378,
            5, 1, 1, 1, 1,
            0, 0, 0
        )
        //TMP___________________

        awaiter()
    }

    // when registering, in CreatingAvatar
    fun getNewCharacterData(login: String) : CharacterData{
        characterData = CharacterData(
            login, "",
            0, 100, 100, 0, 100,
            1, 1, 1,1,1,
            0,0,0
        )
        return characterData
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
}