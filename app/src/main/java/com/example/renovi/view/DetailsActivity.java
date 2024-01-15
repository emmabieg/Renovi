package com.example.renovi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renovi.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailsActivity extends AppCompatActivity {
    private String id;
    private String kosten;
    private String nachteile;
    private String object;
    private String vorteile;
    private String paragraph;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initializeBackToMainButton();
        initializeReadMoreLink();

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        Intent intent = getIntent();
        String geplanteRenovierung = intent.getStringExtra(MainActivityTest.geplanteRenovierung);
        System.out.println("suchmich" + geplanteRenovierung);

        getRenovierungsDaten(db, geplanteRenovierung);
        TextView kostenTextview = findViewById(R.id.kosten);
        kostenTextview.setText("ksoten");
        System.out.println("das ist ein test:" + kosten);
        //kostenTextview.setText(kosten);
        kostenTextview.setTextColor(ContextCompat.getColor(this, R.color.black));




    }

    //@Todo datenbank muss geklärt werden
    private void getRenovierungsDaten(FirebaseFirestore db,String geplanteRenovierung){
        db.collection("Renovierung").whereEqualTo("id",geplanteRenovierung).get().addOnCompleteListener(
                characteristics ->{
                    if (characteristics.isSuccessful()){
                        System.out.println("ich habs gefunden");
                        for (DocumentSnapshot document : characteristics.getResult()) {
                            //if(document.exists()){
                                System.out.println("wenn du hier ankommst gibts stress"); //er kommt nicht hier an geil. 
                                setKosten(document.getString("kosten"));



                                //String kosten = document.getString("kosten");
                                //String nachteile = document.getString(("nachteile"));
                                //String object = document.getString("object");
                                //String paragraph = document.getString("paragraph");
                                //String vorteile = document.getString("vorteile");

                            //}
                        }

                    }
                    else{
                        System.out.println("error zeile 76");

                    }


                });
    }
    private void initializeBackToMainButton() {
        Button startButton = findViewById(R.id.InboxToMainButton);
        startButton.setOnClickListener(view -> switchToMain());
    }

    private void switchToMain() {
        Intent switchActivityIntent = new Intent(this, MainActivityTest.class);
        startActivity(switchActivityIntent);
    }

    private void initializeReadMoreLink() { //reade more redirects to website
        TextView descriptionString = findViewById(R.id.descriptionString);
        descriptionString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWebPage("https://www.gesetze-im-internet.de/bgb/__555b.html");
            }
        });
    }
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Keine Anwendung gefunden, um diese URL zu öffnen", Toast.LENGTH_SHORT).show();
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setKosten(String kosten) {
        this.kosten = kosten;
    }

    public void setNachteile(String nachteile) {
        this.nachteile = nachteile;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setVorteile(String vorteile) {
        this.vorteile = vorteile;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}