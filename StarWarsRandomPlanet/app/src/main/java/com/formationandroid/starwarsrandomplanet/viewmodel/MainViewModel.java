package com.formationandroid.starwarsrandomplanet.viewmodel;

import android.view.View;

import com.formationandroid.starwarsrandomplanet.model.Item;
import com.formationandroid.starwarsrandomplanet.repository.MainRepository;

import java.util.Random;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    // Repository :
    private MainRepository mainRepository;
    // LiveData item :
    private MutableLiveData<Item> liveDataItem;

    private MutableLiveData<Boolean> liveDataSpinner;

    // Initialisation :
    public void init(MainRepository mainRepository)
    {
        // vérification que l'initialisation n'a pas déjà été faite :
        if (liveDataItem != null)
        {
            return;
        }
        // initialisation et premier chargement :
        this.mainRepository = mainRepository;
        liveDataItem = new MutableLiveData<>();
        liveDataSpinner = new MutableLiveData<>();
        mainRepository.getLiveDataItem(liveDataItem, liveDataSpinner);
    }

    public LiveData<Item> getLiveDataItem() {
        return liveDataItem;
    }

    public LiveData<Boolean> getLiveDataSpinner() {
        return liveDataSpinner;
    }

    public void clicRandomViewModel(View view){
        mainRepository.getLiveDataItem(liveDataItem, liveDataSpinner);
    }
}
