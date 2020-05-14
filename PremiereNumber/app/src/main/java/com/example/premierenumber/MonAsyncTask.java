package com.example.premierenumber;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class MonAsyncTask extends AsyncTask<Long, Float, Boolean>
{
    private WeakReference<MainActivity> weakActivity;
    private long startTime;

    public MonAsyncTask(MainActivity asyncActivity) {
        this.weakActivity = new WeakReference<>(asyncActivity);
        startTime = System.nanoTime();
    }

    @Override
    protected Boolean doInBackground(Long... valeur)
    {
        publishProgress(0.25F);
        if(valeur.length == 0) {
            publishProgress(1F);
            return null;
        }

        long inputVal = valeur[0];
        double racine = Math.sqrt(valeur[0]);

        if (inputVal % 2 == 0){
            publishProgress(0.5F);
            if (inputVal == 2){
                publishProgress(1F);
                return true;
            } else {
                publishProgress(1F);
                return false;
            }
        }
        for (int i=3; i <= racine; i = i+2)
        {
            if ((i-1) % 10000 == 0) {
                publishProgress((float) (i / racine));
            }
            if(inputVal%i ==0){
                publishProgress(1F);
                return false;
            }
        }
        publishProgress(1F);
        return true;
    }
    @Override
    protected void onProgressUpdate(Float... values)
    {
        this.weakActivity.get().bar.setProgress((int) (values[0]*100));
    }
    @Override
    protected void onPostExecute(Boolean resultat) {
        if(weakActivity.get() != null) {
            weakActivity.get().getRes(resultat);

            weakActivity.get().txt.append("\n "+String.valueOf((System.nanoTime()-startTime)/1000000)+" ms");
            if (this.weakActivity.get().bar.getProgress()>=100){
                try {
                    Thread.sleep(1000);
                    this.weakActivity.get().bar.setVisibility(View.INVISIBLE);
                    this.weakActivity.get().bar.setProgress(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
