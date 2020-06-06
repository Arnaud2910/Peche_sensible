package com.example.essai_cpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MollusquesPreviewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    TextView tailles_minis;
    TextView quantité_maxi;
    MollusquesModel mollusquesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mollusques_preview);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        tailles_minis = findViewById(R.id.taille_mini);
        quantité_maxi = findViewById(R.id.quantite_max);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            mollusquesModel = (MollusquesModel) intent.getSerializableExtra("items");
            imageView.setImageResource(mollusquesModel.getImages());
            textView.setText(mollusquesModel.getName());
            tailles_minis.setText(mollusquesModel.getTailles_minis());
            quantité_maxi.setText(mollusquesModel.getQuantité_maxi());
        }



    }
}