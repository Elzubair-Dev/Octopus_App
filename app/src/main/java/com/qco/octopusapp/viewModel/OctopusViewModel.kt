package com.qco.octopusapp.viewModel


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.qco.octopusapp.ciphers.*

class OctopusViewModel:ViewModel() {
    var state by mutableStateOf(OctopusState())
    private set


    val alphabet  = listOf('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',' ')

    val unSortedAlphabet  = listOf('g','I','J','Y','o','P','u',' ','A','q','d','F','R','j','T','V','Z','N','a','k','c','W','Q','h','C','l',
        'w','L','y','B','G','s','D','t','K','e','f','v','O','r','X','i','M','z','m','H','S','E','b','p','n','x','U')


    fun onAction(action: OctopusAction){
        when(action){
            is OctopusAction.CipherType -> determineCipherType()
            is OctopusAction.Clear -> performClearing()
            is OctopusAction.Swap -> swap(action.context)
            is OctopusAction.TextTyping -> performTyping(action.it)
            is OctopusAction.KeyTyping -> performKeyTyping(action.it)
            is OctopusAction.Cryptography -> performCryptography(action.name, action.context)
            is OctopusAction.ShowProcess -> showTheProcess(action.context)
        }
    }

    /**open and close dialogs**/
    fun onDialogClicked(step:String){
        state = state.copy(openDialog = true, step = step)
    }
    fun onDismiss(){
        state = state.copy(openDialog = false)
    }

    /**to clear result tv and text fields**/
    private fun performClearing() {
        state = if(state.result.isNotBlank()
            || state.text.isNotBlank()
            || state.key.isNotBlank()) {
            state.copy(
                text = "",
                result = "",
                key = "",
                clearName = "Reset",
                cryptographyProcess = "",
                visualizable = false,
                expended = false
            )
        } else OctopusState()
    }

    /**to encrypt, decrypt or convert the text**/
    private fun performCryptography(name: String,context: Context) {
        if(name == "Encryption"){
            when(state.cipher)
            {
                "Caesar" -> {
                    state = state.copy(
                        result = Caesar (context = context,
                        text = state.text,
                        key = state.key, alphabet).encryption())

                    if(state.result.isNotBlank()
                        && state.text.isNotBlank()
                        && state.key.isNotBlank()){

                        state = state.copy(
                            visualizable = true,
                            cryptographyProcess = "Encryption")
                    }

                }
                "Mono" -> {
                    state = state.copy(
                        result = Monoalphabetic(context = context,
                            text = state.text,
                            keyList = unSortedAlphabet,
                            alphabet = alphabet).encryption())

                    if(state.result.isNotBlank()
                        && state.text.isNotBlank()){

                        state = state.copy(
                            visualizable = true,
                            cryptographyProcess = "Encryption")
                    }

                }
                else -> Toast.makeText(context, "Choose Cipher Please", Toast.LENGTH_SHORT).show()
            }
        }
        if(name == "Decryption")
        {
            when(state.cipher)
            {
                "Caesar" -> {
                    state = state.copy(result = Caesar (context = context,
                        text = state.text,
                        key = state.key,
                        alphabet = alphabet).decryption())

                    if(state.result.isNotBlank()
                        && state.text.isNotBlank()
                        && state.key.isNotBlank()){

                        state = state.copy(visualizable = true,
                            cryptographyProcess = "Decryption")

                    }
                }
                "Mono" -> {
                    state = state.copy(result = Monoalphabetic(context = context,
                        text = state.text,
                        keyList = unSortedAlphabet,
                        alphabet = alphabet).decryption())

                    if(state.result.isNotBlank()
                        && state.text.isNotBlank()){

                        state = state.copy(visualizable = true,
                            cryptographyProcess = "Decryption")
                    }
                }
                else -> Toast.makeText(context, "Choose Cipher Please", Toast.LENGTH_SHORT).show()
            }
        }
        if(name == "Convert"){
            when(state.cipher){
                "T2B" -> {
                    state = state.copy(result = TextToBinary(context = context,
                        text = state.text).textConverting())

                    if(state.result.isNotBlank() && state.text.isNotBlank()){
                        state = state.copy(
                            visualizable = true,
                            cryptographyProcess = "T2B"
                        )
                    }
                }
                "B2T" -> {
                    state = state.copy(result = BinaryToText(context = context,
                        text = state.text).toText())

                    if(state.result.isNotBlank() && state.text.isNotBlank()){
                        state = state.copy(
                            visualizable = true,
                            cryptographyProcess = "B2T"
                        )
                    }
                }
                else -> Toast.makeText(context, "Choose Cipher Please", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**to insert key value**/
    private fun performKeyTyping(it: String) {
    state = state.copy(key = it, clearName = "Clear")
    }

    /**to insert texts**/
    private fun performTyping(it:String) {
        state = state.copy(text = it, clearName = "Clear")
    }

    /**this function is part of code to navigate to visualisation screen
     * notice there isn't navigation controller here
     * that because this function is called only if there is something wrong that forbid navigation**/
    private fun showTheProcess(context: Context) {
        Toast.makeText(context, "Complete Any Process First", Toast.LENGTH_LONG).show()
        state = state.copy(visualizable = false)
    }

    /**to change text on the text field by the result text**/
    private fun swap(context: Context) {
        if(state.result.isNotBlank()){
            when (state.cipher) {
                "B2T" -> {
                    state = state.copy(
                        text = state.result,
                        result = "",
                        cipher = "T2B",
                        visualizable = false)
                }
                "T2B" -> {
                    state = state.copy(
                        text = state.result,
                        result = "",
                        cipher = "B2T",
                        visualizable = false)
                }
                else -> {
                    state = state.copy(
                        text = state.result,
                        result = "",
                        visualizable = false)
                }
            }
        }
        else Toast.makeText(context, "Result TV is Empty", Toast.LENGTH_SHORT).show()
    }

    /**part of code that responsible from determining cipher type, so after choosing cipher the menu should automatically close**/
    private fun determineCipherType() {
        state = if(!state.expended){
            state.copy(expended = true)
        } else{
            state.copy(expended = false)
        }
    }
}