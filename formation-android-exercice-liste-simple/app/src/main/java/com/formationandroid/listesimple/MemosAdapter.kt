package com.formationandroid.listesimple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.formationandroid.listesimple.MemosAdapter.MemoViewHolder
import kotlinx.android.synthetic.main.item_memo.view.*

class MemosAdapter(listeMemos: List<Memo>?) : RecyclerView.Adapter<MemoViewHolder>() {
    // Liste d'objets métier :
    private var listeMemos: MutableList<Memo>? = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val viewMemo = LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        return MemoViewHolder(viewMemo)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(listeMemos!![position])
    }

    override fun getItemCount(): Int {
        return listeMemos!!.size
    }

    /**
     * Ajout d'un mémo à la liste.
     * @param memo Mémo
     */
    fun ajouterMemo(memo: Memo) {
        listeMemos?.add(0, memo)
        notifyItemInserted(0)
    }

    /**
     * ViewHolder.
     */
    inner class MemoViewHolder(itemView: View) : ViewHolder(itemView) {

        /**
         * Constructeur.
         * @param itemView Vue item
         */
        init {
            // listener :
            itemView.setOnClickListener {
                // récupération du context depuis une vue :
                val context = itemView.context
                // affichage du toast :
                Toast.makeText(context, context.getString(R.string.main_message_position, adapterPosition), Toast.LENGTH_LONG).show()
            }
        }

        fun bind(memo:Memo) = with(itemView){
            memo_intitule.text = memo.intitule
        }
    }

    /**
     * Constructeur.
     * @param listeMemos Liste de mémos
     */
    init {
        this.listeMemos = listeMemos as MutableList<Memo>?
    }
}