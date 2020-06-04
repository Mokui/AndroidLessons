package com.formationandroid.listesimple

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    // Adapter :
    private lateinit var memosAdapter: MemosAdapter

    override fun onCreate(savedInstanceState: Bundle?) { // init :
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // à ajouter pour de meilleures performances :
        liste_memos.setHasFixedSize(true)
        // layout manager, décrivant comment les items sont disposés :
        val layoutManager = LinearLayoutManager(this)
        liste_memos.setLayoutManager(layoutManager)
        // contenu d'exemple :
        val listeMemos: MutableList<Memo> = ArrayList()
        for (a in 0..19) {
            listeMemos.add(Memo("Mémo n°" + (a + 1)))
        }
        // adapter :
        memosAdapter = MemosAdapter(listeMemos)
        liste_memos.adapter = memosAdapter
    }

    /**
     * Listener clic bouton valider.
     * @param view Bouton valider
     */
    fun onClickBoutonValider(view: View?) { // ajout du mémo :
        memosAdapter!!.ajouterMemo(Memo(saisie_memo!!.text.toString()))
        // animation de repositionnement de la liste (sinon on ne voit pas l'item ajouté) :
        liste_memos!!.smoothScrollToPosition(0)
        // on efface le contenu de la zone de saisie :
        saisie_memo!!.setText("")
    }
}