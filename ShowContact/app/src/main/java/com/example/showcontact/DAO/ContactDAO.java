package com.example.showcontact.DAO;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.showcontact.bean.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

    public static List<Contact> getContacts(Context context){
        // projection (colonnes utilisées après la requète) :
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        // tri :
        String tri = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC ";
        // requête :
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, // table requétée
                projection, // colonnes à retourner
                null, // colonnes WHERE
                null, // valeurs WHERE
                tri); // ordre de tri

        List<Contact> listeContacts = new ArrayList<>();
        if (cursor != null)
        {
            try
            {
                while (cursor.moveToNext())
                {
// conversion des données remontées en un objet métier :
                    Contact contact = new Contact(
                            cursor.getString(cursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                            cursor.getString(cursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    listeContacts.add(contact);
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            finally
            {
                cursor.close();
            }
        }

        return listeContacts;
    }
}
