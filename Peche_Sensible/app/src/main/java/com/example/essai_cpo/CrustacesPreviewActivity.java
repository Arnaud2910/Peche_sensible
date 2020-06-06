package com.example.essai_cpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CrustacesPreviewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    TextView tailles_minis;
    TextView autres_infos;
    CrustacesModel crustacesModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crustaces_preview);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        tailles_minis = findViewById(R.id.tailles_minis);
        autres_infos = findViewById(R.id.autres_infos);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            crustacesModel = (CrustacesModel) intent.getSerializableExtra("items");
            imageView.setImageResource(crustacesModel.getImages());
            textView.setText(crustacesModel.getName());
            tailles_minis.setText(crustacesModel.getNmb_maxi());
            autres_infos.setText(crustacesModel.getAutres_infos());
        }



    }
}