package com.example.renovi.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.renovi.model.Renter;

import java.math.BigDecimal;

public class RenterSession {
    final String PREF_RENTER = "renter_prefs";
    final String KEY_ID = "key_id";
    final String KEY_FIRSTNAME = "key_firstname";
    final String KEY_LASTNAME = "key_lastname";
    final String KEY_RENT = "key_rent";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public RenterSession(Context context) {

        sharedPreferences = context.getSharedPreferences(PREF_RENTER, Context.MODE_PRIVATE);
    }

    public void putRenter(Renter renter) {
        editor = sharedPreferences.edit();
        editor.putString(KEY_ID, renter.getId());
        editor.putString(KEY_FIRSTNAME, renter.getFirstName());
        editor.putString(KEY_LASTNAME, renter.getLastName());
        editor.putString(KEY_RENT, renter.getRent().toString());
        editor.commit();
    }

    public void deleteSession() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public Renter getRenter() {
        String id = sharedPreferences.getString(KEY_ID, null);
        String firstName = sharedPreferences.getString(KEY_FIRSTNAME, null);
        String lastName = sharedPreferences.getString(KEY_LASTNAME, null);
        String rentAsString = sharedPreferences.getString(KEY_RENT, null);

        BigDecimal rent = new BigDecimal(rentAsString);

        return new Renter(id, firstName, lastName, rent);
    }
}