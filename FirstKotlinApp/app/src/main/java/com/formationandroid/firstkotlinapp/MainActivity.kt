package com.formationandroid.firstkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.formationandroid.firstkotlinapp.middle.Calculable
import com.formationandroid.firstkotlinapp.operations.CommandeAdd
import com.formationandroid.firstkotlinapp.operations.CommandeDiff
import com.formationandroid.firstkotlinapp.operations.CommandeDiv
import com.formationandroid.firstkotlinapp.operations.CommandeMult
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    val tag:String = "TAG"
    var operation:String = ""
    val commandes:MutableList<Calculable> = ArrayList()
    var index:Int = 0
    var havePrevious:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.app_bar_next -> getNextCommand()
            R.id.app_bar_previous -> getPreviousCommand()
        }
        return super.onOptionsItemSelected(item)
    }

    fun clic(view: View) {
        val arrayResp = editText.text.split("\\s+".toRegex()).toTypedArray()
        operation = arrayResp.first()
        val arrayRespInt:List<Int> = arrayResp
            .drop(1) //On retire l'opération du calcul maintenant qu'on la connait
            .filter { it.isNotEmpty() }
            .map { it.toInt() }// On considere que le reste sont des chiffres/nombres
        val commande:Calculable? =
        when(operation){
            "add" ->  CommandeAdd(arrayRespInt)
            "diff" -> CommandeDiff(arrayRespInt)
            "mult" -> CommandeMult(arrayRespInt)
            "div" -> CommandeDiv(arrayRespInt)
            else -> null
        }

        if (commande != null) {
            getFinalResultat(commande.calculer())

            //Ajout de la commande à la liste
            commandes.add(commande)
            index++
        }

        editText.text.clear()
    }

    fun getPreviousCommand() {
        if(commandes.isNotEmpty() && commandes.size > 1){
            index--
            havePrevious = true
            editText.setText(commandes[index].show())
        }
    }

    fun getNextCommand() {
        if(havePrevious || commandes.size>=(index+1)){
            index++
            editText.setText(commandes[index].show())
        }
    }

    fun getFinalResultat(resultat: Int) = Toast.makeText(this,"Résultat: $resultat",Toast.LENGTH_LONG).show()
    /*
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
    }*/
}
