package com.formationandroid.firstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    val tag:String = "TAG"
    var resultat:Int = 0
    var operation:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clic(view: View) {
        val arrayResp = editText.text.split("\\s+".toRegex()).toTypedArray()
        operation = arrayResp.first()
        val arrayRespInt:List<Int> = arrayResp
            .drop(1) //On retire l'opération du calcul maintenant qu'on la connait
            .map { it.toInt() }// On considere que le reste sont des chiffres/nombres

        when(operation){
            "add" -> resultat=additionner(arrayRespInt)
            "diff" -> resultat=soustraitre(arrayRespInt)
            "mult" -> resultat=multiplier(arrayRespInt)
            "div" -> resultat=diviser(arrayRespInt)
            else -> Log.e(tag, "ERREUR - Operation non reconnue")
        }

        getFinalResultat()
    }

    fun additionner(mylist: List<Int>): Int {
        var res:Int=0
        for(i in 0..mylist.lastIndex){
            res += mylist[i]
        }
        return res
    }

    fun soustraitre(mylist: List<Int>): Int {
        var res:Int=mylist.first()
        for(i in 1..mylist.lastIndex){
            res -= mylist[i]
        }
        return res
    }

    fun multiplier(mylist: List<Int>): Int {
        var res:Int=1
        for(i in 0..mylist.lastIndex){
            res *= mylist[i]
        }
        return res
    }

    fun diviser(mylist: List<Int>): Int {
        var res:Int=mylist.first()
        for(i in 1..mylist.lastIndex){
            res /= mylist[i]
        }
        return res
    }

    fun getFinalResultat() = Toast.makeText(this,"Résultat: $resultat",Toast.LENGTH_LONG).show()
}
