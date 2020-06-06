package com.example.essai_cpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PoissonsPreviewActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    TextView taille_mini;
    TextView marquage;
    PoissonsModel poissonsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_preview);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        taille_mini = findViewById(R.id.taille_mini);
        marquage = findViewById(R.id.marquage);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            poissonsModel = (PoissonsModel) intent.getSerializableExtra("items");
            imageView.setImageResource(poissonsModel.getImages());
            textView.setText(poissonsModel.getName());
            taille_mini.setText(poissonsModel.getTaille_mini());
            marquage.setText(poissonsModel.getMarquage());
        }



    }
}