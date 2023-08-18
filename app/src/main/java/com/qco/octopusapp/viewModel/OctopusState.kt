package com.qco.octopusapp.viewModel


data class OctopusState(
    var text:String = "",
    var result:String = "",
    var key:String = "",
    var cipher:String = "Cipher",
    var clearName:String = "Reset",
    var step:String = "",
    var id:String = "",
    var cryptographyProcess:String = "",
    var expended:Boolean = false,
    var visualizable:Boolean = false,
    var openDialog:Boolean = false
)
