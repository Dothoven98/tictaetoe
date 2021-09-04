package com.dothoven.tictaetoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.dothoven.tictaetoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    val buttonArray:Array<Array<Button>> by lazy { binding.run { arrayOf(arrayOf(button1,button2,button3),arrayOf(button4,button5,button6), arrayOf(button7,button8,button9))  } }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setMatrixToUI(Game.matrix)
        setButtonBinding()





    }

    fun setMatrixToUI(inputMatrix:Array<Array<Int>>){

        for(i in 0..2){
            for(j in 0..2){
                if(inputMatrix[i][j]!=0){
                    if(inputMatrix[i][j]==1){
                        buttonArray[i][j].setText("O")
                    }
                    else{
                        buttonArray[i][j].setText("X")
                    }
                }
            }
        }
    }

    fun setButtonBinding(){
        for(i in 0..2){
            for(j in 0..2){
                buttonArray[i][j].setOnClickListener {
                    Game.playerPut(i,j)

                    val result = Game.checkWhoWin(Game.matrix)
                    if(result==0){
                        Game.computerPut()
                    }

                    setMatrixToUI(Game.matrix)
                }
            }
        }
    }

}