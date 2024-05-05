package com.example.tictactoeonline

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tictactoeonline.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityGameBinding

    private var gameModel:GameModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)

        binding.startGameBtn.setOnClickListener {
            startGame()
        }

        GameData.gameModel.observe(this){
            gameModel=it
            setUi()
        }
    }

    fun setUi() {
        gameModel?.apply {
            binding.btn0.text = filledPos[0]
            binding.btn1.text = filledPos[1]
            binding.btn2.text = filledPos[2]
            binding.btn3.text = filledPos[3]
            binding.btn4.text = filledPos[4]
            binding.btn5.text = filledPos[5]
            binding.btn6.text = filledPos[6]
            binding.btn7.text = filledPos[7]
            binding.btn8.text = filledPos[8]

            binding.startGameBtn.visibility=View.VISIBLE

            binding.gameStatusText.text=
                when(gameStatus){
                    GameStatus.CREATED->{
                        "Game ID : $gameId"
                    }
                    GameStatus.JOINED->{
                        "Click on start game"
                    }
                    GameStatus.INPROGRESS->{
                        binding.startGameBtn.visibility=View.INVISIBLE
                        "$currentPlayer's turn"
                    }
                    GameStatus.FINISHED->{
                        if(winner.isNotEmpty()) "$winner Won"
                        else "DRAW"
                    }
                }
        }
    }

    override fun onClick(v: View?) {
        gameModel?.apply {
            if(gameStatus!=GameStatus.INPROGRESS){
                Toast.makeText(applicationContext,"Game not started",Toast.LENGTH_SHORT).show()
                return
            }
            //game is in progress
            val clickedPosition=(v?.tag as String).toInt()
            if(filledPos[clickedPosition].isEmpty()){
                filledPos[clickedPosition]=currentPlayer
                currentPlayer=if(currentPlayer=="X") "O" else "X"
                updateGameData(this)
            }

        }
    }

    fun startGame() {
        gameModel?.apply {
            updateGameData(
                GameModel(
                    gameId=gameId,
                    gameStatus = GameStatus.INPROGRESS
                )
            )
        }
    }

    fun updateGameData(model:GameModel) {
        GameData.saveGameModel(model)
    }
    fun checkForWinner(){
        gameModel?.apply {
            if()
        }
    }
}