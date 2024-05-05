package com.example.tictactoeonline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//classes are the blueprints from which objects are created
//making object to make it singleton
object GameData {
    //We expose it as LiveData to UI (Activity/Fragment) as it can't modify it directly bcs LiveData has no public method to modify its data.
    // and expose it to Repositories as MutableLiveData where we want to modify the data
    private var _gameModel:MutableLiveData<GameModel> = MutableLiveData()
    var gameModel:LiveData<GameModel> = _gameModel

    fun saveGameModel(model:GameModel){
        _gameModel.postValue(model)
    }
}