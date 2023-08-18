package com.qco.octopusapp.ciphers

import android.content.Context
import android.widget.Toast
import kotlin.math.pow

class BinaryToText(private val context: Context,
                   private val text:String) {

    fun toText() : String{
        //--------------------Variables--------------------//
        var result = ""
        var code: Int
        val binaryBase = 2.0
        var newText :String
        var spaces  = 0
        var spaceResult =  0

        //--------------------for detecting spaces on the text--------------------//
        if(isBinary(text = text)){
            text.forEach { char ->
                if(char.isWhitespace()) spaces += 1
            }

            //--------------------equation for getting the number of letters from the inserted binaries--------------------//
            val numberOfLettersInText = ((text.length - spaces) / 8) + spaces
            spaces = 0

            //--------------------Loop--------------------//
            for (i in 0 until numberOfLettersInText){
                code = 0
                val letterIndex = ((i * 8) - (spaceResult * spaces))

                //--------------------because I replaced white space with '~' in this app--------------------//
                if(text[letterIndex].isWhitespace()) {
                    code = '~'.code
                    spaceResult = 7
                    spaces += 1
                }
                //--------------------if it is a binary number we will collect 8 digits--------------------//
                else {
                    newText = getEightDigits(text = text, letterIndex = letterIndex)

                    //--------------------Applying the role for converting binary to decimal on the 8 digits collection--------------------//
                    newText.forEachIndexed { index, char ->
                        if(char == '0'||char == '1'){
                            val number = char.digitToInt()
                            code += number * (binaryBase.pow(index)).toInt()
                        }
                        else return@forEachIndexed
                    }
                }
                //--------------------converting ASCII code to letters and return the result--------------------//
                result += if(code == '~'.code) " " else code.toChar().toString()
            }
            return result
        }
            //--------------------In case if there anything wrong--------------------//
            else {
                Toast.makeText(context, "Enter Only Binary Please", Toast.LENGTH_SHORT).show()
                return ""
            }
        }

    //--------------------Checking the inserted text--------------------//
    private fun isBinary(text: String):Boolean{

        /**
         * to determine if this text is binary or not I made up a simple method
            1\ I created a variable called state with value of 1
            2\ Normal binary text contains zeros, ones and white space
            3\ so I made a loop on the chars of the string and I check if the char is 0, 1 or white space
               state will be multiplied by 1, so it will remain 1, else it will be multiplied by 0
            4\ finally I will return the state
         **/

        var state = 1
        text.forEach { char ->
            state *= if (char == '0' || char == '1' || char.isWhitespace()){
                1
            } else 0
        }
        return state != 0
    }

    //--------------------to separate 8 chosen digits--------------------//
    private fun getEightDigits(text: String,letterIndex:Int):String{
        var eightDigits = ""
        text.forEachIndexed { index, digit ->
            if(index >= letterIndex && index < letterIndex + 8){
                eightDigits += digit
            }
            else return@forEachIndexed
        }
        return eightDigits
    }
}