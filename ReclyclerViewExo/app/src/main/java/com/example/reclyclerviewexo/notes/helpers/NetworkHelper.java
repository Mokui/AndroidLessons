package com.example.reclyclerviewexo.notes.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkHelper {
    public static boolean estConnecte(Context context)
    {
        // récupération du manager :
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // récupération de l'état de la connexion :
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            return networkInfo.isConnected();
        }
        return false;
    }
}
