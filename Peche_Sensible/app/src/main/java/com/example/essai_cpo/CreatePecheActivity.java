package com.example.essai_cpo;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressLint("Registered")
public class CreatePecheActivity extends AppCompatActivity {


    private static final String[] prises = {"", "araignee de mer", "crevette bouquet", "crevettes", "etrille", "homard", "langouste", "tourteau", "pouce-pied", "petoncle", "venus", "vernis", "amande de mer", "bigorneau", "bulot", "clam", "mactre", "moule", "praire", "couteau", "ormeau", "telline", "coquille st jacques", "huitre creuse", "huitre plate", "coque", "palourdre europeenne", "palourdre japonaise", "anchois", "bar", "bar moucheté", "barbue", "cabillaud", "chinchard", "congre", "dorade grise", "dorade rose", "dorade royale", "flet", "germon", "hareng", "lieu jaune", "lieu noir", "lingue", "lingue bleue", "maigre", "maquereau", "merlan", "merlu", "mulet", "orphie", "plie", "rouget", "sar", "sardine", "sole", "turbo"};
    private static final String[] quantité = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private Spinner day;
    private static final String[] pathsDay = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private Spinner month;
    private static final String[] pathsMonth = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
    private Spinner year;
    private static final String[] pathsYear = {"2020", "2021", "2022", "2023", "2024", "2025"};

    private Spinner n_catch;
    private Spinner N_catch;
    private Spinner n_catch1;
    private Spinner N_catch1;
    private Spinner n_catch2;
    private Spinner N_catch2;
    private Spinner n_catch3;
    private Spinner N_catch3;
    private Spinner n_catch4;
    private Spinner N_catch4;

    EditText mInputEt;

    public static String profil_actif = "";
    public static String PROFIL_ACTIF_NAME = "";
    public static String PROFIL_ACTIF_LIEU = "";
    public static final String PROFIL_ACTIF = "profil_actif";
    public static String PROFIL_1_NAME = "";
    public static String PROFIL_2_NAME = "";
    public static String PROFIL_3_NAME = "";
    public static String PROFIL_1_LIEU = "";
    public static String PROFIL_2_LIEU = "";
    public static String PROFIL_3_LIEU = "";

    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_peche);

        n_catch = (Spinner) findViewById(R.id.n_catch);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreatePecheActivity.this,
                android.R.layout.simple_spinner_item, prises);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        n_catch.setAdapter(adapter);

        N_catch = (Spinner) findViewById(R.id.N_catch);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CreatePecheActivity.this,
                android.R.layout.simple_spinner_item, quantité);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        N_catch.setAdapter(adapter2);

        n_catch1 = (Spinner) findViewById(R.id.n_catch1);
        n_catch1.setAdapter(adapter);

        N_catch1 = (Spinner) findViewById(R.id.N_catch1);
        N_catch1.setAdapter(adapter2);

        n_catch2 = (Spinner) findViewById(R.id.n_catch2);
        n_catch2.setAdapter(adapter);

        N_catch2 = (Spinner) findViewById(R.id.N_catch2);
        N_catch2.setAdapter(adapter2);

        n_catch3 = (Spinner) findViewById(R.id.n_catch3);
        n_catch3.setAdapter(adapter);

        N_catch3 = (Spinner) findViewById(R.id.N_catch3);
        N_catch3.setAdapter(adapter2);

        n_catch4 = (Spinner) findViewById(R.id.n_catch4);
        n_catch4.setAdapter(adapter);

        N_catch4 = (Spinner) findViewById(R.id.N_catch4);
        N_catch4.setAdapter(adapter2);

        day = (Spinner) findViewById(R.id.day);
        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(CreatePecheActivity.this,
                android.R.layout.simple_spinner_item, pathsDay);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapterDay);

        month = (Spinner) findViewById(R.id.month);
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(CreatePecheActivity.this,
                android.R.layout.simple_spinner_item, pathsMonth);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adapterMonth);

        year = (Spinner) findViewById(R.id.year);
        ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(CreatePecheActivity.this,
                android.R.layout.simple_spinner_item, pathsYear);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapterYear);

        mInputEt = (EditText) findViewById(R.id.inputEt);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        profil_actif = sharedPreferences.getString(PROFIL_ACTIF, "");
        PROFIL_1_NAME = sharedPreferences.getString("PROFIL_1_NAME", "");
        PROFIL_2_NAME = sharedPreferences.getString("PROFIL_2_NAME", "");
        PROFIL_3_NAME = sharedPreferences.getString("PROFIL_3_NAME", "");
        PROFIL_1_LIEU = sharedPreferences.getString("PROFIL_1_LIEU", "");
        PROFIL_2_LIEU = sharedPreferences.getString("PROFIL_2_LIEU", "");
        PROFIL_3_LIEU = sharedPreferences.getString("PROFIL_3_LIEU", "");


        if (profil_actif.equals("profil 1")) {
            PROFIL_ACTIF_NAME = PROFIL_1_NAME;
            PROFIL_ACTIF_LIEU = PROFIL_1_LIEU;

        }
        if (profil_actif.equals("profil 2")) {
            PROFIL_ACTIF_NAME = PROFIL_2_NAME;
            PROFIL_ACTIF_LIEU = PROFIL_2_LIEU;

        }
        if (profil_actif.equals("profil 3")) {
            PROFIL_ACTIF_NAME = PROFIL_3_NAME;
            PROFIL_ACTIF_LIEU = PROFIL_3_LIEU;

        }


        mInputEt.setText(PROFIL_ACTIF_LIEU);
    }

    public int testI() throws IOException {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fis = openFileInput("i.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            int i = br.read();
            return i;
        } catch (IOException e) {
            int input = 0;
            fos = openFileOutput("i.txt", MODE_PRIVATE);
            fos.write(input);
            int i = 0;
            return i;
        }
    }

    public void save(View v) throws IOException {
        FileOutputStream fos = null;
        String toSave = "";
        if (n_catch.getSelectedItem() != "" && N_catch.getSelectedItem() != "") {
            toSave = toSave + "Prise : " + String.valueOf(n_catch.getSelectedItem()) + " Quantité : " + String.valueOf(N_catch.getSelectedItem()) + "\n";
        }
        if (n_catch1.getSelectedItem() != "" && N_catch1.getSelectedItem() != "") {
            toSave = toSave + "Prise : " + String.valueOf(n_catch1.getSelectedItem()) + " Quantité : " + String.valueOf(N_catch1.getSelectedItem()) + "\n";
        }
        if (n_catch2.getSelectedItem() != "" && N_catch2.getSelectedItem() != "") {
            toSave = toSave + "Prise : " + String.valueOf(n_catch2.getSelectedItem()) + " Quantité : " + String.valueOf(N_catch2.getSelectedItem()) + "\n";
        }
        if (n_catch3.getSelectedItem() != "" && N_catch3.getSelectedItem() != "") {
            toSave = toSave + "Prise : " + String.valueOf(n_catch3.getSelectedItem()) + " Quantité : " + String.valueOf(N_catch3.getSelectedItem()) + "\n";
        }
        if (n_catch4.getSelectedItem() != "" && N_catch4.getSelectedItem() != "") {
            toSave = toSave + "Prise : " + String.valueOf(n_catch4.getSelectedItem()) + " Quantité : " + String.valueOf(N_catch4.getSelectedItem()) + "\n";
        }
        int i = testI();
        if (toSave != "") {
            toSave = toSave +"Lieu : " + mInputEt.getText().toString() + "\n"+ "Profil : " + PROFIL_ACTIF_NAME + "\n" + "Date : " + String.valueOf(day.getSelectedItem()) + " " + String.valueOf(month.getSelectedItem()) + " " + String.valueOf(year.getSelectedItem()) +"\n" +"Pêche" + i + ".txt" + "\n";
        }
        if (toSave == "") {
            Toast.makeText(this, "Veuillez remplir au moins une espèce et une quantité correspondante avant de valider !", Toast.LENGTH_LONG).show();

        } else {
            try {
                fos = openFileOutput("Pêche" + i + ".txt", MODE_PRIVATE);
                fos.write(toSave.getBytes());
                Toast.makeText(this, "Saved to " + getFilesDir() + "/" + "Pêche" + i + ".txt", Toast.LENGTH_LONG).show();
                i = i + 1;
                fos = openFileOutput("i.txt", MODE_PRIVATE);
                fos.write(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
