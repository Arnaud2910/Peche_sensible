package com.example.essai_cpo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PROFIL_ACTIF = "profil_actif";
    public static String profil_actif = "";

    public static  String PROFIL_1_NAME = "";
    public static  String PROFIL_2_NAME = "";
    public static  String PROFIL_3_NAME = "";
    public static  String PROFIL_ACTIF_NAME = "";

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mBDDButton;
    private Button mprofilButton;
    private Button mConseilButton;
    private Button mpecheButton;
    private TextView Nom_profil;

    private MainActivity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBDDButton = (Button) findViewById(R.id.activity_main_BDD_btn);
        mprofilButton = (Button) findViewById(R.id.activity_main_profil_btn);
        mConseilButton = (Button) findViewById(R.id.activity_main_conseil_btn);
        mpecheButton = (Button) findViewById(R.id.activity_main_gerer_peche_btn);
        Nom_profil = (TextView) findViewById(R.id.activity_main_nom_profil);


        this.activity = this;


        mBDDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choixCategorieActivity = new Intent(MainActivity.this, ChoixCategorie.class);
                startActivity(choixCategorieActivity);
            }
        });

        mConseilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent conseilActivity = new Intent(MainActivity.this, Conseil.class);
                startActivity(conseilActivity);
            }
        });

        mprofilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profilActivity = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(profilActivity);
            }
        });

        mpecheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pecheActivity = new Intent(MainActivity.this,PecheActivity.class);
                startActivity(pecheActivity);
            }
        });

        testProfilActif(); //tester si un profil est actif

    }


public void testProfilActif(){
    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
    profil_actif = sharedPreferences.getString(PROFIL_ACTIF,"");
    PROFIL_1_NAME = sharedPreferences.getString("PROFIL_1_NAME","");
    PROFIL_2_NAME = sharedPreferences.getString("PROFIL_2_NAME","");
    PROFIL_3_NAME = sharedPreferences.getString("PROFIL_3_NAME","");

    if (profil_actif.equals("profil 1"))
    {
        PROFIL_ACTIF_NAME=PROFIL_1_NAME;
        Log.d("myTag","                                      PROFIL 1 récupéré                                       ");

    }
    if (profil_actif.equals("profil 2"))
    {
        PROFIL_ACTIF_NAME=PROFIL_2_NAME;
        Log.d("myTag","                                      PROFIL 2 récupéré                                       ");
    }
    if (profil_actif.equals("profil 3"))
    {
        PROFIL_ACTIF_NAME=PROFIL_3_NAME;
    }

    if (PROFIL_ACTIF_NAME.equals("")) //si le profil actif est vide, alors on propose à l'utilisateur d'en choisir un
    {
        Log.d("myTag","                                      wesh                                       ");
        Log.d("myTag",profil_actif);
        Nom_profil.setText("INVITE");
        AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
        myPopup.setTitle("CHOIX PROFIL");
        myPopup.setMessage("Vous n'avez aucun profil actif, voulez vous en choisir un ?");
        myPopup.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent profilActivity = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(profilActivity);
            }
        });

        myPopup.setNegativeButton("NON", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        myPopup.show();
    }
    else //si le profil actif est complété
    {
        Nom_profil.setText(PROFIL_ACTIF_NAME);
    }



}

}
