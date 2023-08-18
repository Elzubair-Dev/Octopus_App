package com.qco.octopusapp.ciphers

import android.content.Context
import android.widget.Toast

class TextToBinary(private val context: Context,
                   private val text:String) {
    fun textConverting() : String{

        //--------------------variables--------------------//
        var result = ""
        val decimalList : MutableList<Int> = mutableListOf()
        var binaryList = ""

        //--------------------checking if it is acceptable text--------------------//
        if(isLetters(text = text)){

            //--------------------iterate on text so we can convert each char alone--------------------//
            text.forEachIndexed { index, char ->
//                if(char.isLetter() || char.isWhitespace())
//                {
                //--------------------if it is char convert it to it's equivalent ASCII code and save it to Decimal List--------------------//
                if(char.isLetter()) {
                    decimalList.add(index = index, element = char.code)
                }
                //--------------------if it is white space convert it to '~' ASCII code and save it to Decimal List--------------------//
                //--------------------'~' is white space place holder--------------------//
                else if(char.isWhitespace()) {
                    decimalList.add(index = index, element = '~'.code)
                }
//                }
                //--------------------make sure that user doesn't enter anything but letters or white spaces--------------------//
                else Toast.makeText(context, "Enter Only Text Please", Toast.LENGTH_SHORT).show()
            }
            //--------------------here we convert each decimal number or ASCII code to binary--------------------//
            decimalList.forEach { num ->
                val modList:MutableList<Int> = mutableListOf()
                //--------------------if it was white space we will save '~' else we covert it to binary using recursion concept--------------------//
                binaryList += if(num == '~'.code){
                    "~"
                } else toBinary(number = num, modList = modList)
            }
            //--------------------it is not for checking it's function is to separate between binaries and white spaces--------------------//
            binaryList.forEach { number ->
                if(isBinary(number = number) || number == '~'){
                    if(number == '~') result += " "
                    else result += number
                }
            }
            return result
        }
        //--------------------if it is not acceptable text--------------------//
        else {
            Toast.makeText(context, "Enter Only Text Please", Toast.LENGTH_SHORT).show()
            return ""
        }
    }

    //--------------------checking chars and white spaces of the text with same way that explained in BinaryToText Class--------------------//
    private fun isLetters(text: String): Boolean {
        var flag = 1
        text.forEach { char->
            flag *= if(char.isLetter() || char.isWhitespace()) 1 else 0
        }
        return flag != 0
    }

    //--------------------converting text to binary using recursion--------------------//
    /**As you know to convert decimal number to binary you need to divide that number by 2
     * you will get division result and remainder, remainder is either 0 or 1
     * save the remainder in modList, and divide division result by 2 again
     * you need to repeat this steps until division result is zero
     * modList elements represents the equivalent binary number to the ASCII code**/
    private fun toBinary(number: Int, modList: MutableList<Int>):String{
        val mod : Int = number % 2
        val newNumber : Int = number / 2
        modList.add(mod)
        return if (newNumber == 0) {
            modList.add(0)
            modList.toString()
        }
        else toBinary(number = newNumber, modList = modList)
    }
    //--------------------Checking if number is correct binary number--------------------//
    private fun isBinary(number: Char):Boolean{
        //--------------------if number is 0 or 1 it will return true else it will return false--------------------//
        return number == '0' || number == '1'
    }
}