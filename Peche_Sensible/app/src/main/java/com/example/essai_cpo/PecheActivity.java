package com.example.essai_cpo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PecheActivity extends AppCompatActivity{

    private Button mCreatePeche;
    private Button mShowPeche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_peches);
        mCreatePeche = (Button) findViewById(R.id.activity_create_peche_btn);
        mShowPeche = (Button) findViewById(R.id.activity_show_peches_btn);

        mCreatePeche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createPecheActivity = new Intent(PecheActivity.this, CreatePecheActivity.class);
                startActivity(createPecheActivity);
            }
        });


        mShowPeche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showPecheActivity = new Intent(PecheActivity.this, ShowPecheActivity.class);
                startActivity(showPecheActivity);
            }
        });




    }
}
