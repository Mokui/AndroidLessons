package com.formationandroid.firstkotlinapp.operations

import com.formationandroid.firstkotlinapp.middle.Calculable

class CommandeDiv(val mylist: List<Int>): Calculable {
    override fun calculer(): Int {
        var res:Int=mylist.first()
        for(i in 1..mylist.lastIndex){
            res /= mylist[i]
        }
        return res
    }

    override fun show(): String {
        val arrayResp:List<String> = mylist
            .map { it.toString() }// On considere que le reste sont des chiffres/nombres
        var ope:String = "div "
        for (numbers in arrayResp){
            ope = ope.plus("$numbers ")
        }
        return ope
    }
}