package com.example.tictactoeonline

data class GameModel(
    var gameId:String="-1",
    var filledPos:MutableList<String> = mutableListOf("","","","","","","","","",),
    var winner:String="",
    var gameStatus:GameStatus=GameStatus.CREATED,
    var currentPlayer:String=(arrayOf("X","O"))[kotlin.random.Random.nextInt(2)]

)

enum class GameStatus{
    CREATED,
    JOINED,
    INPROGRESS,
    FINISHED
}