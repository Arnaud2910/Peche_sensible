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

public class CrustacesActivity extends AppCompatActivity {

    String names[] = {"araignee de mer","crevette bouquet","crevettes","etrille","homard","langouste","tourteau","pouce-pied"};
    String scientific_names[] = {"maja brachydactyla","palaemon serratus","autres que bouquet","(necora puber","homarus gammarus", "palinurus spp","cancer pagurus","pollicipes pollicipes"  };
    int images[] = {R.drawable.araignee,R.drawable.crevette_bouquet,R.drawable.crevette,R.drawable.etrille,R.drawable.homard,R.drawable.langouste,R.drawable.tourteau,R.drawable.pouce_pied};
    String tailles_minis[] = {"12 cm","5 cm","3 cm","6.5 cm","8.7 cm","11 cm", "13/14 cm","/"};
    String autres_infos[] = {"Prélèvement limité à 6 araignées par personnes et par jour en peche sous-marine"," / "," / "," / "," / ","","14cm au NORD du 48è parallèle, 13cm au SUD. Le 48è parallèle correspond à la latitude d'Audierne","Peche autorisée du 18/01 au 14/03 et du 16/09 au 14/11. 3 kilos maximum par personnes et par jour"};

    List<CrustacesModel> crustacesModelList = new ArrayList<>();

    ListView listView;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_crustaces);
        listView = findViewById(R.id.listview);


        for(int i = 0;i < names.length;i++){

            CrustacesModel crustacesModel = new CrustacesModel(names[i],scientific_names[i],images[i],tailles_minis[i],autres_infos[i]);

            crustacesModelList.add(crustacesModel);

        }

        customAdapter = new CustomAdapter(crustacesModelList,this);

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

        private List<CrustacesModel> crustacesModelssl;
        private List<CrustacesModel> crustacesModelsListFiltered;
        private Context context;

        public CustomAdapter(List<CrustacesModel> crustacesModelssl, Context context) {
            this.crustacesModelssl = crustacesModelssl;
            this.crustacesModelsListFiltered = crustacesModelssl;
            this.context = context;
        }



        @Override
        public int getCount() {
            return crustacesModelsListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return crustacesModelsListFiltered.get(position);
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

            names.setText(crustacesModelsListFiltered.get(position).getName());
            emails.setText(crustacesModelsListFiltered.get(position).getScientific_name());
            imageView.setImageResource(crustacesModelsListFiltered.get(position).getImages());
            //

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("main activity","item clicked");
                    startActivity(new Intent(CrustacesActivity.this,CrustacesPreviewActivity.class).putExtra("items",crustacesModelsListFiltered.get(position)));

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
                        filterResults.count = crustacesModelssl.size();
                        filterResults.values = crustacesModelssl;

                    }else{
                        List<CrustacesModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(CrustacesModel crustacesModel:crustacesModelssl){
                            if(crustacesModel.getName().contains(searchStr) || crustacesModel.getScientific_name().contains(searchStr)){
                                resultsModel.add(crustacesModel);

                            }
                            filterResults.count = resultsModel.size();
                            filterResults.values = resultsModel;
                        }


                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    crustacesModelsListFiltered = (List<CrustacesModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }



}
