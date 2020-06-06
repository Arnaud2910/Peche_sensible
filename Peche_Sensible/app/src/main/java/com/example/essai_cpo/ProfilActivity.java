package com.example.essai_cpo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ProfilActivity extends AppCompatActivity {
    private Button Profil1_btn;
    private Button Profil2_btn;
    private Button Profil3_btn;
    private TextView Profil_actif_txt;
    private TextView Profil_actif;
    private TextView Nom_profil;
    private  TextView Nom_profil_txt;
    private Button Modification_btn;
    private TextView Lieu_profil_txt;
    private TextView Lieu_profil;
    private Button Supprimer_btn;

    private ProfilActivity activity;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PROFIL_1 = "profil 1";
    public static final String PROFIL_2 = "profil 2";
    public static final String PROFIL_3 = "profil 3";
    public static final String PROFIL_ACTIF = "profil_actif";
    public static String profil_actif = "";
    public static  String PROFIL_ACTIF_NAME = "";

    public static  String PROFIL_1_NAME = "";
    public static  String PROFIL_2_NAME = "";
    public static  String PROFIL_3_NAME = "";

    public static  String PROFIL_1_LIEU = "";
    public static  String PROFIL_2_LIEU = "";
    public static  String PROFIL_3_LIEU = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Profil_actif = (TextView) findViewById(R.id.profil_actif);
        Profil_actif_txt = (TextView) findViewById(R.id.text_profil_actif);
        Profil1_btn = (Button) findViewById(R.id.profil1_btn);
        Profil2_btn = (Button) findViewById(R.id.profil2_btn);
        Profil3_btn = (Button) findViewById(R.id.profil3_btn);
        Nom_profil = (TextView) findViewById(R.id.nom_profil);
        Nom_profil_txt = (TextView) findViewById(R.id.text_nom_profil);
        Lieu_profil = (TextView) findViewById(R.id.lieu_profil);
        Lieu_profil_txt = (TextView) findViewById(R.id.text_lieu_profil);
        Modification_btn= (Button) findViewById(R.id.modification_btn);
        Supprimer_btn = (Button) findViewById(R.id.suppression_btn);
        this.activity = this;

        Profil1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profil_actif="profil 1";
                updateViews2();

            }
        });

        Profil2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profil_actif = "profil 2";
                updateViews2();
            }
        });

        Profil3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profil_actif="profil 3";
                updateViews2();
            }
        });

        Modification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choixModificationActivity = new Intent(ProfilActivity.this, ModificationActivity.class);
                startActivity(choixModificationActivity);
            }
        });



        Supprimer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Suppression");
                myPopup.setMessage("Voulez vous supprimer le profil");
                myPopup.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (profil_actif.equals("profil 1"))
                        {
                            PROFIL_1_LIEU="";
                            PROFIL_1_NAME="";

                        }
                        if (profil_actif.equals("profil 2"))
                        {
                            PROFIL_2_LIEU="";
                            PROFIL_2_NAME="";

                        }
                        if (profil_actif.equals("profil 3"))
                        {
                            PROFIL_3_LIEU="";
                            PROFIL_3_NAME="";

                        }
                        Toast.makeText(getApplicationContext(), "Profil supprimé", Toast.LENGTH_SHORT).show();
                        saveData();
                        updateViews2();
                    }
                });

                myPopup.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                myPopup.show();

            }
        });



        //Profil_actif_txt.setText("Le profil actif est : ");
        //Nom_profil_txt.setText("Votre nom enregistré : ");
        //Lieu_profil_txt.setText("Votre lieu enregistré");

        loadData();
        updateViews2();
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        profil_actif = sharedPreferences.getString(PROFIL_ACTIF,"");
        PROFIL_1_NAME = sharedPreferences.getString("PROFIL_1_NAME","");
        PROFIL_2_NAME = sharedPreferences.getString("PROFIL_2_NAME","");
        PROFIL_3_NAME = sharedPreferences.getString("PROFIL_3_NAME","");

        if (profil_actif.equals("profil 1"))
        {
            PROFIL_ACTIF_NAME=PROFIL_1_NAME;
        }
        if (profil_actif.equals("profil 2"))
        {
            PROFIL_ACTIF_NAME=PROFIL_2_NAME;
        }
        if (profil_actif.equals("profil 3"))
        {
            PROFIL_ACTIF_NAME=PROFIL_3_NAME;
        }
        Log.d("myTag","profil name : ");
        Log.d("myTag",PROFIL_ACTIF_NAME);
        Log.d("myTag","fin profil name : ");
        if (PROFIL_ACTIF_NAME.equals(" ")) //si le profil actif est vide, alors on propose à l'utilisateur d'en choisir un
        {
            Log.d("myTag","                                      wesh                                       ");
            Log.d("myTag",profil_actif);
            AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
            myPopup.setTitle("CHOIX PROFIL");
            myPopup.setMessage("Le profil actif est vide, voulez vous en choisir un autre ?");
            myPopup.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            myPopup.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent mainActivity = new Intent(ProfilActivity.this, MainActivity.class);
                    startActivity(mainActivity);

                }
            });

            myPopup.show();
        }
        else
        {
            Intent mainActivity = new Intent(ProfilActivity.this, MainActivity.class);
            startActivity(mainActivity);
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROFIL_ACTIF, profil_actif);
        editor.putString("PROFIL_1_NAME", PROFIL_1_NAME);
        editor.putString("PROFIL_1_LIEU", PROFIL_1_LIEU);
        editor.putString("PROFIL_2_NAME", PROFIL_2_NAME);
        editor.putString("PROFIL_2_LIEU", PROFIL_2_LIEU);
        editor.putString("PROFIL_3_NAME", PROFIL_3_NAME);
        editor.putString("PROFIL_3_LIEU", PROFIL_3_LIEU);
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




    public void updateViews2() {
        Profil_actif.setText(profil_actif);
        Log.d("myTag","!"+profil_actif+"!");
        if (profil_actif.equals("profil 1"))
        {
            if(PROFIL_1_NAME.equals(""))
            {
                Profil_actif_txt.setText("Le profil est vide");
                Nom_profil_txt.setText(" ");
                Lieu_profil_txt.setText(" ");
                Nom_profil.setText("");
                Lieu_profil.setText("");
                Profil_actif.setText("");
            }
            else
            {
                Profil_actif_txt.setText("Le profil actif est : ");
                Nom_profil_txt.setText("Votre nom enregistré : ");
                Lieu_profil_txt.setText("Votre lieu enregistré");
                Profil_actif.setText("profil 1");
                Nom_profil.setText(PROFIL_1_NAME);
                Lieu_profil.setText(PROFIL_1_LIEU);
                saveData();
            }
        }

        if (profil_actif.equals("profil 2"))
        {

            if(PROFIL_2_NAME.equals(""))
            {
                Profil_actif_txt.setText("Le profil est vide");
                Nom_profil_txt.setText(" ");
                Lieu_profil_txt.setText(" ");
                Nom_profil.setText("");
                Lieu_profil.setText("");
                Profil_actif.setText("");
            }
            else {
                Profil_actif_txt.setText("Le profil actif est : ");
                Nom_profil_txt.setText("Votre nom enregistré : ");
                Lieu_profil_txt.setText("Votre lieu enregistré");
                Profil_actif.setText("profil 2");
                Nom_profil.setText(PROFIL_2_NAME);
                Lieu_profil.setText(PROFIL_2_LIEU);
                saveData();
            }
        }

        if (profil_actif.equals("profil 3"))
        {
            if(PROFIL_3_NAME.equals(""))
            {
                Profil_actif_txt.setText("Le profil est vide");
                Nom_profil_txt.setText(" ");
                Lieu_profil_txt.setText(" ");
                Nom_profil.setText("");
                Lieu_profil.setText("");
                Profil_actif.setText("");
            }
            else {
                Profil_actif_txt.setText("Le profil actif est : ");
                Nom_profil_txt.setText("Votre nom enregistré : ");
                Lieu_profil_txt.setText("Votre lieu enregistré");
                Profil_actif.setText("profil 3");
                Nom_profil.setText(PROFIL_3_NAME);
                Lieu_profil.setText(PROFIL_3_LIEU);
                saveData();
            }
        }
        saveData();

    }


}
