package com.example.essai_cpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MollusquesActivity extends AppCompatActivity {

    String names[] = {"petoncle","venus","vernis","amande de mer","bigorneau","bulot","clam","mactre","moule","praire","couteau","ormeau","telline","coquille st jacques","huitre creuse", "huitre plate","coque","palourdre europeenne","palourdre japonaise"};
    String scientific_names[] = {"chlamys spp","spisula spp","callista chione","glycymeris glycymeris","littorina littorea","(buccinum undatum","mercenaria mercenaria","spisula solida","Mytilus edulis","Venus v.","Solen  spp, Ensis spp, Pharus legumen","Haliotis tuberculata","Donax spp","Pecten maximus","Crassostea gigas","(Ostrea edulis","Cerastoderma edule",". ", " ."};
    int images[] = {R.drawable.petoncle,R.drawable.venus,R.drawable.vernis,R.drawable.amande,R.drawable.bigorneau,R.drawable.bullot,R.drawable.clam,R.drawable.mactre,R.drawable.moule,R.drawable.praire,R.drawable.couteau,R.drawable.ormeau,R.drawable.telline,R.drawable.coquille_st_jacques,R.drawable.huitre_creuse,R.drawable.huitre_plate,R.drawable.coque,R.drawable.paloudre_europeenne,R.drawable.palourde_japonaise};
    String tailles_minis[] ={"4 cm","2.8 cm","6 cm"," non précisée ", "non précisée ","4.5 cm","4.3 cm","2.5 cm","4 cm","4.3 cm","10 cm","9 cm","2.5 cm","11 cm","5 cm","6 cm","2.7 cm","4 cm","3.5 cm"};
    String quantité_maxi[] ={"100/3kg","100/3kg","100/3kg","100/3kg","500/3kg","100/3kg","100/3kg","100/3kg","300/3kg","100/3kg","5dz/3kg","20","500/2kg","30","5dz/5kg","5dz/5kg","5dz/5kg","300/3kg","150/3kg"};

    List<MollusquesModel> mollusquesModelList = new ArrayList<>();

    ListView listView;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_mollusques);
        listView = findViewById(R.id.listview);


        for(int i = 0;i < names.length;i++){

            MollusquesModel mollusquesModel = new MollusquesModel(names[i],scientific_names[i],images[i],tailles_minis[i],quantité_maxi[i]);

            mollusquesModelList.add(mollusquesModel);

        }

        customAdapter = new CustomAdapter(mollusquesModelList,this);

        listView.setAdapter(customAdapter);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("Main"," data search"+newText);

                customAdapter.getFilter().filter(newText);



                return true;
            }
        });


        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.searchView){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<MollusquesModel> mollusquesModelssl;
        private List<MollusquesModel> mollusquesModelsListFiltered;
        private Context context;

        public CustomAdapter(List<MollusquesModel> mollusquesModelssl, Context context) {
            this.mollusquesModelssl = mollusquesModelssl;
            this.mollusquesModelsListFiltered = mollusquesModelssl;
            this.context = context;
        }



        @Override
        public int getCount() {
            return mollusquesModelsListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return mollusquesModelsListFiltered.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row_items,null);


            TextView names = view.findViewById(R.id.name);
            TextView emails = view.findViewById(R.id.email);
            ImageView imageView = view.findViewById(R.id.images);
            //

            names.setText(mollusquesModelsListFiltered.get(position).getName());
            emails.setText(mollusquesModelsListFiltered.get(position).getScientific_name());
            imageView.setImageResource(mollusquesModelsListFiltered.get(position).getImages());
            //

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("main activity","item clicked");
                    startActivity(new Intent(MollusquesActivity.this,MollusquesPreviewActivity.class).putExtra("items",mollusquesModelsListFiltered.get(position)));

                }
            });

            return view;
        }



        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();
                    if(constraint == null || constraint.length() == 0){
                        filterResults.count = mollusquesModelssl.size();
                        filterResults.values = mollusquesModelssl;

                    }else{
                        List<MollusquesModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(MollusquesModel mollusquesModel:mollusquesModelssl){
                            if(mollusquesModel.getName().contains(searchStr) || mollusquesModel.getScientific_name().contains(searchStr)){
                                resultsModel.add(mollusquesModel);

                            }
                            filterResults.count = resultsModel.size();
                            filterResults.values = resultsModel;
                        }


                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    mollusquesModelsListFiltered = (List<MollusquesModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }



}
