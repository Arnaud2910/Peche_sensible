package com.example.essai_cpo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SessionsPreviewActivity extends AppCompatActivity {


    TextView date;
    TextView lieu;
    TextView contenu;
    SessionsModel sessionsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions_preview);


        date = findViewById(R.id.date);
        lieu = findViewById(R.id.lieu);
        contenu = findViewById(R.id.contenu);


        Intent intent = getIntent();
        if(intent.getExtras() != null){
            sessionsModel = (SessionsModel) intent.getSerializableExtra("items");
            date.setText(sessionsModel.getdate());
            lieu.setText(sessionsModel.getlieu());
            contenu.setText(sessionsModel.getContenu());
        }



    }
}
