package com.example.showcontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.showcontact.DAO.ContactDAO;
import com.example.showcontact.adapter.ContactAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 69;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.scrollview);

        //PERMISSIONS
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if(permission == PackageManager.PERMISSION_GRANTED) {
            chargerContacts();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               chargerContacts();
            }
            else {
                Toast.makeText(this, R.string.tezrtt, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void chargerContacts() {
        //Scroll
        recyclerView.setHasFixedSize(true);
        ContactAdapter listContactAdapter = new ContactAdapter(ContactDAO.getContacts(this));
        recyclerView.setAdapter(listContactAdapter);

    }
}
