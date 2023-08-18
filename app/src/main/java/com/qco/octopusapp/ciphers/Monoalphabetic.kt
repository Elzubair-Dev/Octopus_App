package com.qco.octopusapp.ciphers

import android.content.Context
import android.widget.Toast

class Monoalphabetic(private val context: Context,
                     private val text:String,
                     private val keyList: List<Char>,
                     private val alphabet:List<Char>): ICipher {

    //--------------------overrode from ICipher Interface--------------------//
    override fun encryption(): String {
        var result  = ""

        //--------------------normal checks to avoid crashes--------------------//
        if(text.isNotBlank() && isLetters(text = text))
        {
            for(char in text)
            {
                for(j in alphabet.indices)
                {
                    //--------------------finding target letter index from alphabet list and then saving the letter with same index in the keyList to result--------------------//
                    if(char == alphabet[j])
                    {
                        result  += keyList[j]
                    }
                }
            }

        }
        //--------------------make sure that user doesn't enter anything but letters or white spaces--------------------//
        else Toast.makeText(context, "Enter Only Text please", Toast.LENGTH_LONG).show()
        return result
    }

    override fun decryption(): String {
        var result = ""
        //--------------------normal checks to avoid crashes--------------------//
        if(text.isNotBlank() && isLetters(text = text))
        {
            for(char in text)
            {
                for(j in alphabet.indices)
                {
                    //--------------------finding target letter index from KeyList and then saving the letter with same index in the alphabet List to result--------------------//
                    if(char == keyList[j])
                    {
                        result += alphabet[j]
                    }
                }
            }
        }
        //--------------------make sure that user doesn't enter anything but letters or white spaces--------------------//
        else Toast.makeText(context, "Enter Only Text please", Toast.LENGTH_LONG).show()
        return result
    }
    private fun isLetters(text: String): Boolean {
        var flag = 1
        text.forEach { char->
            flag *= if(char.isLetter() || char.isWhitespace()) 1 else 0
        }
        return flag != 0
    }
}