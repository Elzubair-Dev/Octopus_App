package com.qco.octopusapp.viewModel

import android.content.Context

sealed class OctopusAction{
    object Clear: OctopusAction()
    object CipherType: OctopusAction()
    data class Swap(var context: Context): OctopusAction()
    data class ShowProcess(var context: Context): OctopusAction()
    data class Cryptography(var name:String,var context: Context): OctopusAction()
    data class TextTyping(var it:String): OctopusAction()
    data class KeyTyping(var it:String): OctopusAction()
}
