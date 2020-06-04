package com.formationandroid.listesimple

class Memo(intitule: String?) {
    // Attributs :
    @JvmField
    var intitule: String? = null

    /**
     * Constructeur.
     * @param intitule Intitulé du mémo
     */
    init {
        this.intitule = intitule
    }
}