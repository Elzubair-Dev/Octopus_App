package com.qco.octopusapp.ciphers

import android.content.Context
import android.widget.Toast

class Caesar(private val context: Context,
             private val text: String,
             private val key:String,
             private val alphabet: List<Char>): ICipher {

    //--------------------overrode from ICipher Interface--------------------//
    override fun encryption(): String {
        var result  = ""

        //--------------------normal checks to avoid crashes--------------------//
        if(text.isNotBlank() && isLetters(text = text))
        {
            if (key.isNotBlank()
                && isNumber(key = key)
                && key.toInt() > 0)
            {
                if((key.toInt()%alphabet.size)!= 0)
                {
                    for(char in text)
                    {
                        for(j in alphabet.indices)
                        {
                            if(char == alphabet[j])
                            {
                                //--------------------in encryption : getting n is by the rule (Letter's index + key) % alphabet size--------------------//
                                val n:Int = (j + key.toInt()) % alphabet.size
                                result  += alphabet[n].toString()
                            }
                        }
                    }
                }
                //--------------------you must know that our alphabet size is 53 so entering this number or it's Multiplexing is like entering zero which is unacceptable--------------------//
                else Toast.makeText(context, "avoid 53 & it's Multiplexing Please", Toast.LENGTH_LONG).show()
            }
            //--------------------make sure that user doesn't enter zero or negative number--------------------//
            else Toast.makeText(context, "Enter Positive Numeric key Please", Toast.LENGTH_LONG).show()
        }
        //--------------------make sure that user doesn't enter anything but letters or white spaces--------------------//
        else Toast.makeText(context, "Enter Only Text Please", Toast.LENGTH_LONG).show()

        return result
    }

    //--------------------overrode from ICipher Interface--------------------//
    override fun decryption(): String {
        var result = ""
        //--------------------normal checks to avoid crashes--------------------//
        if(text.isNotBlank() && isLetters(text = text))
        {
            if (key.isNotBlank() && isNumber(key = key) && key.toInt() > 0)
            {
                if((key.toInt()%alphabet.size)!= 0)
                {
                    for(char in text)
                    {
                        for(j in alphabet.indices)
                        {
                            if(char == alphabet[j])
                            {
                                //--------------------in decryption : getting n is by the rule (Letter's index - key) % alphabet size--------------------//
                                var n = (j - key.toInt()) % alphabet.size
                                if(n < 0)
                                {
                                    n += alphabet.size
                                }
                                result += alphabet[n].toString()
                            }
                        }
                    }
                }
                //--------------------you must know that our alphabet size is 53 so entering this number or it's Multiplexing is like entering zero which is unacceptable--------------------//
                else Toast.makeText(context, "avoid 53 & it's Multiplexing Please", Toast.LENGTH_LONG).show()
            }
            //--------------------make sure that user doesn't enter zero or negative number--------------------//
            else Toast.makeText(context, "Enter Positive Numeric Key Please", Toast.LENGTH_LONG).show()
        }
        //--------------------make sure that user doesn't enter anything but letters or white spaces--------------------//
        else Toast.makeText(context, "Enter Only Text Please", Toast.LENGTH_LONG).show()
        return result
    }
    //--------------------checking key value with same way that explained in BinaryToText Class--------------------//
    private fun isNumber(key: String): Boolean {
        var flag = 1
        key.forEach { i->
            flag *= if(i.isDigit()) 1 else 0
        }
        return flag != 0
    }

    //--------------------checking chars and white spaces of the text with same way that explained in BinaryToText Class--------------------//
    private fun isLetters(text: String): Boolean {
        var flag = 1
        text.forEach { char->
            flag *= if(char.isLetter() || char.isWhitespace()) 1 else 0
        }
        return flag != 0
    }
}