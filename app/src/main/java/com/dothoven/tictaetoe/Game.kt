package com.dothoven.tictaetoe

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf

object Game {
    var matrix:Array<Array<Int>> = arrayOf(arrayOf(0,0,0), arrayOf(0,0,0), arrayOf(0,0,0))
    fun printMatrix(inputMatrix:Array<Array<Int>>){
        for(i in 0..2){
            for(j in 0..2){
                Log.d("myTag",inputMatrix[i][j].toString())
            }
        }


    }
    fun playerPut(i:Int,j:Int){
        matrix[i][j] = 1
    }
    fun computerPut(){

        //(이김,짐,비김)
        val winCounterArray:Array<Array<Int>> =  arrayOf(arrayOf(0,0,0), arrayOf(0,0,0), arrayOf(0,0,0))
        var x:Int =1
        var y:Int =1
        var max:Int =0
        for(i in 0..2){
            for(j in 0..2){
                if(matrix[i][j]==0) {
                    winCounterArray[i][j] = computerCalculate(matrix, 0, 8).second
                    Log.d("myTag", "i=${i} j=${j} weight = ${winCounterArray[i][j]}")
                }
            }
        }


        for(i in 0..2){
            for(j in 0..2){
                if(winCounterArray[i][j]>max&&matrix[i][j]==0){
                    max = winCounterArray[i][j]
                    x = i
                    y = j
                }
            }
        }

        matrix[x][y] = 2

    }
    fun computerCalculate(matrix:Array<Array<Int>>,winCounter:Int,restPos:Int):Pair<Array<Array<Int>>,Int>{
        val duplicatedMatrix = copyMatrix(matrix)

        var nowWinCounter = winCounter
        if(checkMatrixFull(duplicatedMatrix)){
            //모든칸이 차있을경우 게임 종료
            if(checkWhoWin(duplicatedMatrix)==1){
                return Pair(duplicatedMatrix,0)
            }
            else if(checkWhoWin(duplicatedMatrix)==2){
                return Pair(duplicatedMatrix,1)
            }



        }
        if(checkWhoWin(duplicatedMatrix)!=0){
            //둘중 하나가 이긴경우
            if(checkWhoWin(duplicatedMatrix)==2){
                //컴퓨터가 이긴경우
                return Pair(duplicatedMatrix, nowWinCounter+factorial(restPos))
                //return computerCalculate(duplicatedMatrix,winCounter+ factorial(restPos),restPos-1)

            }
            else{
                //사람이 이긴경우
                return Pair(duplicatedMatrix,0)
            }
        }




        for(i in 0..2){
            for(j in 0..2){
                if (duplicatedMatrix[i][j]==0){
                    //남은칸들에 대하여 재귀호출
                    if(restPos%2==0){
                        duplicatedMatrix[i][j] = 2
                        nowWinCounter+=computerCalculate(duplicatedMatrix,nowWinCounter,restPos-1).second
                    }
                    else{
                        duplicatedMatrix[i][j] = 1
                        computerCalculate(duplicatedMatrix,nowWinCounter,restPos-1)
                    }
                }
            }
        }

        //칸이 다찬경우

        return Pair(duplicatedMatrix,1)

    }






    fun checkWhoWin(matrix: Array<Array<Int>>):Int{
        //플레이어 윈:return 1,
        //컴퓨터 윈 : return 2,
        //승부나지않음: return 0;

        //가로체크
        if(matrix[0][0]!=0&&(matrix[0][0]==matrix[0][1]&&matrix[0][1]==matrix[0][2])){
            if(matrix[0][0]==1){
                return 1
            }
            else if(matrix[0][0]==2){
                return 2
            }
        }
        if(matrix[1][0]!=0&&(matrix[1][0]==matrix[1][1]&&matrix[1][1]==matrix[1][2])){
            if(matrix[1][0]==1){
                return 1
            }
            else if(matrix[1][0]==2){
                return 2
            }
        }
        if(matrix[2][0]!=0&&(matrix[2][0]==matrix[2][1]&&matrix[2][1]==matrix[2][2])){
            if(matrix[2][0]==1){
                return 1
            }
            else if(matrix[2][0]==2){
                return 2
            }
        }

        //세로 체크
        if(matrix[0][0]!=0&&(matrix[0][0]==matrix[1][0]&&matrix[1][0]==matrix[2][0])){
            if(matrix[0][0]==1){
                return 1
            }
            else if(matrix[0][0]==2){
                return 2
            }
        }
        if(matrix[0][1]!=0&&(matrix[0][1]==matrix[1][1]&&matrix[1][1]==matrix[2][1])){
            if(matrix[0][1]==1){
                return 1
            }
            else if(matrix[0][1]==2){
                return 2
            }
        }
        if(matrix[0][2]!=0&&(matrix[0][2]==matrix[1][2]&&matrix[1][2]==matrix[2][2])){
            if(matrix[0][2]==1){
                return 1
            }
            else if(matrix[0][2]==2){
                return 2
            }
        }

        //대각선 체크
        if(matrix[0][0]!=0&&(matrix[0][0]==matrix[1][1]&&matrix[1][1]==matrix[2][2])){
            if(matrix[0][0]==1){
                return 1
            }
            else if(matrix[0][0]==2){
                return 2
            }
        }
        if(matrix[0][2]!=0&&(matrix[0][2]==matrix[1][1]&&matrix[1][1]==matrix[2][0])){
            if(matrix[0][2]==1){
                return 1
            }
            else if(matrix[0][2]==2){
                return 2
            }
        }


        //그 외
        return 0
    }

    fun factorial(num:Int):Int{
        var result = 1
        for(i in 1..num){
            result = result*i
        }
        return result
    }


    fun copyMatrix(matrix: Array<Array<Int>>):Array<Array<Int>>{
        var duplicatedMatrix:Array<Array<Int>> = arrayOf(arrayOf(0,0,0), arrayOf(0,0,0), arrayOf(0,0,0))
        for(i in 0..2){
            for(j in 0..2){
                duplicatedMatrix[i][j] = matrix[i][j]
            }
        }
        return duplicatedMatrix
    }

    fun checkMatrixFull(matrix: Array<Array<Int>>):Boolean{
        for(i in 0..2){
            for(j in 0..2){
                if(matrix[i][j]==0){
                    return false
                }
            }
        }
        return true
    }







}