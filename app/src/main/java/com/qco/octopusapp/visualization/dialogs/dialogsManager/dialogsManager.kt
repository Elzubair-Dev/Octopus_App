package com.qco.octopusapp.visualization.dialogs.dialogsManager

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.qco.octopusapp.R
import com.qco.octopusapp.visualization.dialogs.dialogsComposables.BasicDialog
import com.qco.octopusapp.visualization.dialogs.dialogsComposables.LazyRowDialog
import com.qco.octopusapp.visualization.dialogs.dialogsComposables.pagerDialog.data.BPagerList
import com.qco.octopusapp.visualization.dialogs.dialogsComposables.pagerDialog.data.TPagerList
import com.qco.octopusapp.visualization.dialogs.dialogsComposables.pagerDialog.dialog.PagerDialog

@Composable
fun DialogsManager(cipher: String,
                   cryptography: String,
                   step: String,
                   language: String,
                   alphabet : List<Char>,
                   unSortedAlphabet : List<Char>,
                   onDismiss:()->Unit) {

    val rounds = stringResource(id = R.string.rounds)

    val text = stringResource(id = R.string.text)

    val findingIndex = if(cryptography == "Encryption") stringResource(id = R.string.findingIndex)
    else stringResource(id = R.string.mono_decryption_finding_index)

    val replacing = if(cryptography == "Encryption") stringResource(id = R.string.replacing)
    else stringResource(id = R.string.mono_decryption_replacing)

    val result = stringResource(id = R.string.result)

    /**all what you need to know is cipher and step to get the suitable dialog**/
    when(cipher){
        "Mono" -> {
                when(step){
                    "Round" -> {
                        BasicDialog(title = step,
                            explanation = rounds,
                            image = painterResource(id = R.drawable.octopus),
                            onDismiss = {onDismiss()},
                        language = language)
                    }
                    "Text" -> {
                        BasicDialog(title = step,
                            explanation = text,
                            image = painterResource(id = R.drawable.octopus),
                            onDismiss = {onDismiss()},
                            language = language)
                    }
                    "Finding Index" -> {
                        LazyRowDialog(title = step,
                            explanation = findingIndex,
                            image = painterResource(id = R.drawable.octopus),
                            listTitle = if(cryptography == "Encryption") stringResource(id = R.string.alphabet) else stringResource(id = R.string.unsorted_alphabet),
                            list = if(cryptography == "Encryption") alphabet else unSortedAlphabet,
                            onDismiss = {onDismiss()},
                            withIndex = true,
                            language = language)
                    }
                    "Replacing" -> {
                        LazyRowDialog(title = step,
                            explanation = replacing,
                            image = painterResource(id = R.drawable.octopus),
                            listTitle = if(cryptography == "Encryption") stringResource(id = R.string.unsorted_alphabet) else stringResource(id = R.string.alphabet),
                            list = if(cryptography == "Encryption") unSortedAlphabet else alphabet,
                            onDismiss = {onDismiss()},
                            withIndex = true,
                            language = language)
                    }
                    "Result" -> {
                        BasicDialog(title = step,
                            explanation = result,
                            image = painterResource(id = R.drawable.octopus),
                            onDismiss = {onDismiss()},
                            language = language)
                    }
                }
        }
        "Caesar" -> {
            when(step){
                "Round" -> {
                    BasicDialog(title = step,
                        explanation = rounds,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
                "Text" -> {
                    BasicDialog(title = step,
                        explanation = text,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
                "Finding Index" -> {
                    LazyRowDialog(title = step,
                        explanation = findingIndex,
                        image = painterResource(id = R.drawable.octopus),
                        listTitle = stringResource(id = R.string.alphabet),
                        list = alphabet ,
                        onDismiss = {onDismiss()},
                        withIndex = true,
                        language = language)
                }
                "n Equation" -> {
                    //val sign = if(cryptography == "Encryption") "+" else "-"
                    BasicDialog(title = step,
                        explanation = stringResource(id = R.string.n_equation),
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
                "Replacing" -> {
                    LazyRowDialog(title = step,
                        explanation = stringResource(id = R.string.caesarReplacing),
                        image = painterResource(id = R.drawable.octopus),
                        listTitle = stringResource(id = R.string.alphabet),
                        list = alphabet,
                        onDismiss = {onDismiss()},
                        withIndex = true,
                        language = language)
                }
                "Result" -> {
                    BasicDialog(title = step,
                        explanation = result,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
            }
        }
        "B2T" -> {
            when(step){
                "Round" -> {
                    BasicDialog(title = step,
                        explanation = rounds,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
                "Text" -> {
                    BasicDialog(title = step,
                        explanation = text,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
                "Summation" -> {
                    BasicDialog(title = step,
                        explanation = stringResource(id = R.string.b2t_summation),
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
                "To Decimal" ->{
                    PagerDialog(
                        image = painterResource(id = R.drawable.octopus),
                        title = step,
                        onDismiss = {onDismiss()},
                        pager = BPagerList,
                        language = language)
                }
                "To Char" -> {
                    LazyRowDialog(title = step,
                        explanation = stringResource(id = R.string.b2t_toChar),
                        image = painterResource(id = R.drawable.octopus),
                        listTitle = stringResource(id = R.string.alphabet),
                        list = alphabet,
                        onDismiss = {onDismiss()},
                        withIndex = false,
                        language = language)
                }
                "Result" -> {
                    BasicDialog(title = step,
                        explanation = result,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
            }
        }
        "T2B" -> {
            when(step){
                "Round" -> {
                    BasicDialog(title = step,
                        explanation = rounds,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
                "Text" -> {
                    BasicDialog(title = step,
                        explanation = text,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
                "To Ascii" -> {
                    LazyRowDialog(title = step,
                        explanation = stringResource(id = R.string.t2b_toAscii),
                        image = painterResource(id = R.drawable.octopus),
                        listTitle = stringResource(id = R.string.alphabet),
                        list = alphabet,
                        onDismiss = {onDismiss()},
                        withIndex = false,
                        language = language)
                }
                "To Binary" -> {
                    PagerDialog(image = painterResource(id = R.drawable.octopus),
                        title = step,
                        onDismiss = {onDismiss()},
                        pager = TPagerList,
                        language = language)
                }
                "Result" -> {
                    BasicDialog(title = step,
                        explanation = result,
                        image = painterResource(id = R.drawable.octopus),
                        onDismiss = {onDismiss()},
                        language = language)
                }
            }
        }
    }
}