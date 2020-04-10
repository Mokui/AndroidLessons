package com.example.reclyclerviewexo.activities;

import android.os.Bundle;

import com.example.reclyclerviewexo.fragments.DetailFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Toast;

import com.example.reclyclerviewexo.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String intituleMemo = getIntent().getStringExtra("detail");

        // fragment :
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DetailFragment.EXTRA_PARAM, intituleMemo);
        fragment.setArguments(bundle);

        // fragment manager :
        FragmentManager fragmentManager = getSupportFragmentManager();
        // transaction :
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_detail, fragment, "exemple2");
        fragmentTransaction.commit();
    }

    public void afficheNote(){
        Toast.makeText(this, "OUI", Toast.LENGTH_SHORT).show();
    }
}
