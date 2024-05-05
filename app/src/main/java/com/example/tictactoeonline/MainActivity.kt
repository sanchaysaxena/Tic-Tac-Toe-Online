package com.example.tictactoeonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tictactoeonline.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playOfflineBtn.setOnClickListener {
            createOfflineGame()
        }
        binding.createOnlineGameBtn.setOnClickListener {
            createOnlineGame()
        }
        binding.joinOnlineGameBtn.setOnClickListener {
            joinOnlineGame()
        }

    }

    fun createOfflineGame() {
        GameData.saveGameModel(
            GameModel(
                gameStatus = GameStatus.JOINED
            )
        )
        startGame()
    }

    fun createOnlineGame(){
        GameData.myId="X"
        GameData.saveGameModel(
            GameModel(
                gameId= Random.nextInt(1000..9999).toString(),
                gameStatus = GameStatus.CREATED
            )
        )
        startGame()
    }
    fun joinOnlineGame(){
        GameData.myId="O"
        var gameID=binding.gameIdInput.text.toString()
        if(gameID.isEmpty()){
            Toast.makeText(applicationContext,"Please enter an ID",Toast.LENGTH_SHORT).show()
            return
        }
        Firebase.firestore.collection("games")
            .document(gameID)
            .get()
            .addOnSuccessListener {
                val model=it?.toObject(GameModel::class.java)
                if(model==null){
                    Toast.makeText(applicationContext,"Please enter a valid ID",Toast.LENGTH_SHORT).show()
                }
                else{
                    model.gameStatus=GameStatus.JOINED
                    GameData.saveGameModel(model)
                    startGame()
                }
            }
    }

    fun startGame(){
        startActivity(Intent(this,GameActivity::class.java))
    }
}