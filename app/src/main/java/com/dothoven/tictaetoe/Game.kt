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

    fun findMaxWinCountPos(winCountMatrix: Array<Array<Int>>,duplicatedMatrix:Array<Array<Int>>):Pair<Int,Int>{
        var x = 1
        var y = 1
        var maxWin =-1


        for(i in 0..2){
            for(j in 0..2){
                if(checkElementIsEmpty(duplicatedMatrix,i,j)) {
                    val nowWin = winCountMatrix[i][j]

                    if (nowWin > maxWin) {
                        maxWin = nowWin

                        x = i
                        y = j
                    }
                }
            }
        }

        return Pair(x,y)
    }
    fun computerPut(){

        var duplicatedMatrix:Array<Array<Int>>
        duplicatedMatrix = copyMatrix(matrix)
        var cpuWinCounter = arrayOf(arrayOf(0,0,0), arrayOf(0,0,0), arrayOf(0,0,0))
        for(i in 0..2){
            for(j in 0..2){

                if(checkElementIsEmpty(duplicatedMatrix,i,j)) {
                    duplicatedMatrix[i][j] = 2

                    val tempWinCount = calculateComputerWinCount(duplicatedMatrix, calculateEmptyElementCount(duplicatedMatrix))
                    cpuWinCounter[i][j] = tempWinCount
                    Log.d("myTag",i.toString()+" "+j.toString()+" "+tempWinCount.toString())
                }
            }
        }

        val pos = findMaxWinCountPos(cpuWinCounter,duplicatedMatrix)
        matrix[pos.first][pos.second] =2





    }
    fun calculateComputerWinCount(matrix: Array<Array<Int>>,restPos:Int,):Int{
        var duplicatedMatrix:Array<Array<Int>>
        duplicatedMatrix = copyMatrix(matrix)



        var returnValue =0


        for (i in 0..2) {
            for (j in 0..2) {

                if(checkWhoWin(duplicatedMatrix)==0){
                    if(restPos==0){
                        return 0
                    }
                    else{
                        if(restPos%2==0){
                            duplicatedMatrix[i][j]=1
                            returnValue += calculateComputerWinCount(duplicatedMatrix,restPos-1)
                        }
                        else{
                            duplicatedMatrix[i][j]=2
                            returnValue += calculateComputerWinCount(duplicatedMatrix,restPos-1)
                        }
                    }

                }
                else if(checkWhoWin(duplicatedMatrix)==1){
                    return 0
                }
                else{
                    return factorial(restPos)
                }


            }
        }

        return returnValue

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


    fun checkElementIsEmpty(matrix: Array<Array<Int>>, i: Int, j: Int):Boolean{
        if(matrix[i][j]==0){
            return true
        }
        return false
    }


    fun calculateEmptyElementCount(matrix: Array<Array<Int>>):Int{
        var restPos =0;
        for(i in 0..2){
            for(j in 0..2){
                if(checkElementIsEmpty(matrix,i,j)){
                    restPos++
                }
            }
        }
        return restPos
    }





}