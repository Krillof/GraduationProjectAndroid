package com.example.graduationprojectandroid.network

import com.example.graduationprojectandroid.fragments.for_main_page.adapters.HabitDoneStates

class DataService() {

    private var characterData: CharacterData
    private val networkService: NetworkService

    init {
        characterData = getCharacterData()
        networkService = NetworkService.getInstance()
    }

    fun tryUpdateData(listener: ()->Unit){
        //TODO: Check, if data is updated
        // if not - update
        listener()
    }

    fun getCharacterData(): CharacterData{
        //TODO: if character data updated - ask server
        // else just return data from files
        if (true){
            characterData = CharacterData(
                "abc", 129, 65, 100, 220, 378, 13,
                1, 1, 1, 1
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