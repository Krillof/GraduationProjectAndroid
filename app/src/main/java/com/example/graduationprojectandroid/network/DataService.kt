package com.example.graduationprojectandroid.network

import com.example.graduationprojectandroid.fragments.for_main_page.adapters.*

class DataService() {

    private var characterData: CharacterData
    private val networkService: NetworkService

    init {
        characterData = getCharacterData()
        networkService = NetworkService.getInstance()
    }

    fun getCharacterData(): CharacterData{
        //TODO: if character data updated - ask server
        // else just return data from files
        if (true){
            characterData = CharacterData(
                "abc", "Avocado",
                129, 65, 100, 220, 378,
                13, 1, 1, 1, 1,
                0, 0, 0, 0
            )
        }
        return characterData
    }

    fun setHabitDoneState(id: Int, state: HabitDoneStates){
        //TODO: Send to server
    }

    fun setTaskState(id: Int, state: Boolean){
        //TODO: Send to server
    }

    fun getNewIdForTask(): Int{
        return 1
    }

    fun getNewIdForHabit(): Int{
        return 1
    }

    fun sendHabit(habit: Habit, listener: (error: String) -> Unit){
        //Just send - if id other - new, else - old
        listener("Checking error dialogue")
    }

    fun sendTask(task: Task, listener: (error: String) -> Unit) {
        //Just send - if id other - new, else - old
        listener("Checking error dialogue")
    }

    fun tryToUpdateCharacterData(){
        characterData = CharacterData(
            "abc", "Aristotle",
            19, 90, 100, 220, 378,
            5, 1, 1, 1, 1,
            0, 0, 0, 0
        )
    }


    fun getTasks(): ArrayList<Task>{
        var tasksList = ArrayList<Task>()

        //---------------------------------------
        //TMP
        //val task1 =

        val sbtsks_strs1 = listOf("в тетради", "потом выучи текст", "приготовь картошку")

        var subtasks1: MutableList<Subtask> = MutableList(
            3,
            {index -> Subtask(false, sbtsks_strs1[index]) }
        )

        var task1 = Task(
            1,
            "Сделать англ",
            "Очень надо, у меня не зачёт",
            subtasks1
        )

        task1.isEveryday = true
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
            sbtsks_strs2.size,
            {index -> Subtask(false, sbtsks_strs2[index]) }
        )

        var task2 = Task(
            2,
            "Приготовиться к гостям",
            "Гости-то придут",
            subtasks2
        )

        task2.setFullDone(true)
        task2.difficulty = Difficulty.normal

        var subtasks3: MutableList<Subtask> = MutableList(
            0,
            {index -> Subtask(false, "") }
        )

        var task3 = Task(
            3,
            "Поесть",
            "Очень важно",
            subtasks3
        )

        task3.isEveryweek = true
        task3.difficulty = Difficulty.easy



        tasksList.add(task1)
        tasksList.add(task2)
        tasksList.add(task3)

        //TMP
        //-------------------------------------

        return tasksList
    }

    fun getHabits() : ArrayList<Habit>{
        val habits: ArrayList<Habit> = ArrayList()
        //TODO: Make normal habits load
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

        return habits
    }


    companion object{
        private var dataService: DataService? = null

        fun getDataService(): DataService{
            if (dataService == null){
                dataService = DataService()
            }

            return dataService!!
        }
    }
}