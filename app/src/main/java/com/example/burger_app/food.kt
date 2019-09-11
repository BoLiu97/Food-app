package com.example.burger_app

import android.net.Uri
import java.net.URI
import java.net.URL

class food{
    var name:String? = null
    var des: String = ""
    var image:Int? = null
    var Calo:Int? = null
    var checked:Boolean= false

    constructor(name:String,des:String,image:Int,Calo:Int,checked:Boolean){
        this.name=name
        this.des=des
        this.image=image
        this.Calo = Calo
        this.checked=checked
    }

}