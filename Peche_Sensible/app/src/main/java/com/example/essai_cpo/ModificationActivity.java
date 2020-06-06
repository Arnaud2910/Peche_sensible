package com.example.essai_cpo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModificationActivity extends AppCompatActivity {


    private TextView Profil_actif_txt;
    private TextView Profil_actif;
    private TextView Nom_profil;
    private  TextView Nom_profil_txt;
    private TextView Lieu_profil;
    private  TextView Lieu_profil_txt;
    private Button saveNameButton;
    private EditText editName;
    private Button saveLieuButton;
    private EditText editLieu;
    private Button Retour_btn;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PROFIL_1 = "profil 1";
    public static final String PROFIL_2 = "profil 2";
    public static final String PROFIL_3 = "profil 3";
    public static final String PROFIL_ACTIF = "profil_actif";
    public static String profil_actif = "";

    public static  String PROFIL_1_NAME = "";
    public static  String PROFIL_2_NAME = "";
    public static  String PROFIL_3_NAME = "";
    public static  String PROFIL_1_LIEU = "";
    public static  String PROFIL_2_LIEU = "";
    public static  String PROFIL_3_LIEU = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);
        Profil_actif = (TextView) findViewById(R.id.profil_actif);
        Profil_actif_txt = (TextView) findViewById(R.id.text_profil_actif);
        Nom_profil = (TextView) findViewById(R.id.nom_profil);
        Nom_profil_txt = (TextView) findViewById(R.id.text_nom_profil);
        Lieu_profil = (TextView) findViewById(R.id.lieu_profil);
        Lieu_profil_txt = (TextView) findViewById(R.id.text_lieu_profil);

        saveNameButton = (Button) findViewById(R.id.save_name);
        editName = (EditText) findViewById(R.id.editname);

        saveLieuButton = (Button) findViewById(R.id.save_lieu);
        editLieu = (EditText) findViewById(R.id.editlieu);

        Retour_btn= (Button) findViewById(R.id.retour_btn);



        saveNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profil_actif== "profil 1")
                {
                    PROFIL_1_NAME=editName.getText().toString();
                    Nom_profil.setText(PROFIL_1_NAME);
                }
                if (profil_actif == "profil 2")
                {
                    PROFIL_2_NAME=editName.getText().toString();
                    Nom_profil.setText(PROFIL_2_NAME);
                }
                if (profil_actif== "profil 3")
                {
                    PROFIL_3_NAME=editName.getText().toString();
                    Nom_profil.setText(PROFIL_3_NAME);
                }

                saveData();
                updateViews();
            }
        });

        saveLieuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (profil_actif== "profil 1")
                {
                    PROFIL_1_LIEU=editLieu.getText().toString();
                    Lieu_profil.setText(PROFIL_1_LIEU);
                }
                if (profil_actif == "profil 2")
                {
                    PROFIL_2_LIEU=editLieu.getText().toString();
                    Lieu_profil.setText(PROFIL_2_LIEU);
                }
                if (profil_actif== "profil 3")
                {
                    PROFIL_3_LIEU=editLieu.getText().toString();
                    Lieu_profil.setText(PROFIL_3_LIEU);
                }
                saveData();
                updateViews();
            }
        });


        Retour_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ProfilActivity = new Intent(ModificationActivity.this, ProfilActivity.class);
                ModificationActivity.this.finish();
                startActivity(ProfilActivity);
            }
        });

        Profil_actif_txt.setText("Le profil actif est : ");
        Nom_profil_txt.setText("Votre nom enregistré : ");
        Lieu_profil_txt.setText("Votre lieu enregistré : ");
        loadData();
        updateViews();
    }


    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROFIL_ACTIF, profil_actif);
        if (profil_actif== "profil 1")
        {
            editor.putString("PROFIL_1_NAME", PROFIL_1_NAME);
            editor.putString("PROFIL_1_LIEU", PROFIL_1_LIEU);
        }
        if (profil_actif == "profil 2")
        {
            editor.putString("PROFIL_2_NAME", PROFIL_2_NAME);
            editor.putString("PROFIL_2_LIEU", PROFIL_2_LIEU);
        }
        if (profil_actif== "profil 3")
        {
            editor.putString("PROFIL_3_NAME", PROFIL_3_NAME);
            editor.putString("PROFIL_3_LIEU", PROFIL_3_LIEU);
        }
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        profil_actif = sharedPreferences.getString(PROFIL_ACTIF,"");
        PROFIL_1_NAME = sharedPreferences.getString("PROFIL_1_NAME","");
        PROFIL_2_NAME = sharedPreferences.getString("PROFIL_2_NAME","");
        PROFIL_3_NAME = sharedPreferences.getString("PROFIL_3_NAME","");
        PROFIL_1_LIEU = sharedPreferences.getString("PROFIL_1_LIEU","");
        PROFIL_2_LIEU = sharedPreferences.getString("PROFIL_2_LIEU","");
        PROFIL_3_LIEU = sharedPreferences.getString("PROFIL_3_LIEU","");
        Log.d("myTag","!"+profil_actif+"!");
        Log.d("myTag","                                   f                                           ");
    }
    public void updateViews() {
        Profil_actif.setText(profil_actif);
        Log.d("myTag","!"+profil_actif+"!");
        if (profil_actif.equals("profil 1"))
        {
            Log.d("myTag","                                  profil 1                                        ");
            Nom_profil.setText(PROFIL_1_NAME);
            Lieu_profil.setText(PROFIL_1_LIEU);
        }
        if (profil_actif.equals("profil 2"))
        {
            Log.d("myTag","                                  profil 2                                         ");
            Nom_profil.setText(PROFIL_2_NAME);
            Lieu_profil.setText(PROFIL_2_LIEU);
        }
        if (profil_actif.equals("profil 3"))
        {
            Log.d("myTag","                                  profil 3                                        ");
            Nom_profil.setText(PROFIL_3_NAME);
            Lieu_profil.setText(PROFIL_3_LIEU);
        }
        else
        {
            Log.d("myTag","                                  Rien                                     ");
        }

    }
}
