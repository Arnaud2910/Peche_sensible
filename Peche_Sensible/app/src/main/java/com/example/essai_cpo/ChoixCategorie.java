package com.example.essai_cpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoixCategorie extends AppCompatActivity {

    private Button mMollusqueButton;
    private Button mCrustaceButton;
    private Button mPoissonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_categorie);
        mMollusqueButton = (Button) findViewById(R.id.activity_category_mollusques_btn);
        mCrustaceButton = (Button) findViewById(R.id.activity_category_crustaces_btn);
        mPoissonButton = (Button) findViewById(R.id.activity_category_poissons_btn);

        mPoissonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choixCategorieActivity = new Intent(ChoixCategorie.this, PoissonActivity.class);
                startActivity(choixCategorieActivity);
            }
        });


        mCrustaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choixCategorieActivity = new Intent(ChoixCategorie.this, CrustacesActivity.class);
                startActivity(choixCategorieActivity);
            }
        });

        mMollusqueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choixCategorieActivity = new Intent(ChoixCategorie.this, MollusquesActivity.class);
                startActivity(choixCategorieActivity);
            }
        });




    }
}
