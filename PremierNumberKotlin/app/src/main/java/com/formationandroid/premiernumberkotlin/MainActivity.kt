package com.formationandroid.premiernumberkotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.ceil
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("SetTextI18n")
    fun clickIsPremier(view: View){
        CoroutineScope(Default).launch {
            if (editText.text.toString().isNotEmpty()){
                val nbToDeterminate: Long = editText.text!!.toString().toLong()
                val retour = getIfPremier(nbToDeterminate)
                withContext(Main) {
                    resultat.visibility = View.VISIBLE
                    if (retour) {
                        resultat.setTextColor(Color.RED)
                        resultat.text = "$nbToDeterminate n'est pas premier!"
                    } else {
                        resultat.setTextColor(Color.GREEN)
                        resultat.text = "$nbToDeterminate est un nombre premier!"
                    }
                    editText.text.clear()
                    progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private suspend fun getIfPremier(nbToDeterminate: Long): Boolean {
        var flag = false
        withContext(Main) {
            progressBar.visibility = View.VISIBLE
        }
        // condition for nonprime number
        for (i: Long in 2L..ceil(sqrt(nbToDeterminate.toDouble())).toLong()) {
            if (i%10000L == 0L) {
                withContext(Main) {
                    progressBar.progress = ((i * 100) / nbToDeterminate).toInt()
                }
            }
            if (nbToDeterminate % i == 0L) {
                flag = true
                break
            }
        }

        return flag
    }
}
