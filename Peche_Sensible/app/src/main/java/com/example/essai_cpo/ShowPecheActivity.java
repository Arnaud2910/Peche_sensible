package com.example.essai_cpo;

import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShowPecheActivity extends AppCompatActivity {

    List pechesList = new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_peches);
        listView = findViewById(R.id.listview);

        int i = 0;
        try {
            i = testI();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int j = 0;j < i;j++){
            FileInputStream fis = null;
            try {
                fis = openFileInput("Pêche"+j+".txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String text;
                String s = "";
                while ((text = br.readLine()) != null) {
                    s = s + text + "\n";
                }
                pechesList.add(s);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pechesList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, final View v, final int position, final long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(ShowPecheActivity.this);
                adb.setTitle("Supprimer ?");
                adb.setMessage("Êtes vous sûr de vouloir supprimer cette pêche ?");
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = listView.getItemAtPosition(position).toString();
                        pechesList.remove(positionToRemove);
                        String substr = getSubString(text);
                        String dir = getFilesDir().getAbsolutePath();
                        String file = substr +".txt";
                        Log.d("message", "" + file);
                        File f0 = new File(dir, file);
                        f0.delete();
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });
    }

    public int testI() throws IOException {
        FileInputStream  fisTry = openFileInput("i.txt");
        InputStreamReader isr = new InputStreamReader(fisTry);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        int i = br.read();
        return i;
    }

    public static String getSubString(String mainString) {
        String endString = "";
        int endIndex = mainString.indexOf(".");
        int startIndex = mainString.indexOf("Pêche");
        Log.d("message", "" + mainString.substring(startIndex, endIndex));
        endString = mainString.substring(startIndex, endIndex);
        return endString;
    }
}
