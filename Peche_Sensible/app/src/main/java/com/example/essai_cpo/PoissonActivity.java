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

public class PoissonActivity extends AppCompatActivity {

    String names[] = {"anchois","bar","bar moucheté","barbue","cabillaud","chinchard","congre","dorade grise", "dorade rose","dorade royale","flet","germon","hareng","lieu jaune","lieu noir","lingue","lingue bleue","maigre","maquereau","merlan","merlu","mulet","orphie","plie","rouget","sar","sardine","sole","turbo"};
    String scientific_names[] = {"Engraulidae","Dicentrarchus labrax","Dicentrachus punctatus","Scophtalmus rhombus","Gadus morhua","Trachurus trachurus","Conger conger","Spondyliosoma cantharus","Pagellus bogaraveo","Sparus aurata","Platichtys flesus","Thunus alalunga","Clupea harengus","Pollachius pollachius","Pollachius virens","Molva molva","Molva dipterygia","Argyrosomus regius","Scomber scombrus","Merlangius merlangus","Merluccius merluccius","Mugil spp","Belone belone","Pleuronectes platessa","Mullus spp","Diplodus sargus","Sardina pilchardus","Solea solea","Psetta maxima"};
    int images[] = {R.drawable.anchois,R.drawable.bar,R.drawable.bar_mouchete,R.drawable.barbue,R.drawable.cabillaud,R.drawable.chinchard,R.drawable.congre,R.drawable.dorade_grise,R.drawable.dorade_rose,R.drawable.dorade_royale,R.drawable.flet,R.drawable.germon,R.drawable.hareng,R.drawable.lieu_jaune,R.drawable.lieu_noir,R.drawable.lingue,R.drawable.lingue_bleue,R.drawable.maigre,R.drawable.maquereau,R.drawable.merlan,R.drawable.merlu,R.drawable.mulet,R.drawable.orphie,R.drawable.plie,R.drawable.rouget,R.drawable.sar,R.drawable.sardine,R.drawable.sole,R.drawable.turbo};
    String taille_mini[] = {"12cm","42cm","30cm","30cm","42cm","15cm","60cm","23cm","35cm","23cm","20cm","2Kilogrammes","20cm","30cm","35cm","63cm","70cm","45cm","20cm","27cm","27cm","30cm","30cm","27cm","15cm","25cm","11cm","24cm","30cm"};
    String marquage[] = {"non","oui","non","non","oui","non","non","non","non","oui","non","non","non","oui","oui","non","non","oui","oui","non","non","non","non","non","non","oui","non","oui","non"};

    List<PoissonsModel> poissonsModelList = new ArrayList<>();

    ListView listView;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_poisson);
        listView = findViewById(R.id.listview);


        for(int i = 0;i < names.length;i++){

            PoissonsModel poissonsModel = new PoissonsModel(names[i],scientific_names[i],images[i],taille_mini[i],marquage[i]);

            poissonsModelList.add(poissonsModel);

        }

        customAdapter = new CustomAdapter(poissonsModelList,this);

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

        private List<PoissonsModel> poissonsModelsl;
        private List<PoissonsModel> poissonsModelListFiltered;
        private Context context;

        public CustomAdapter(List<PoissonsModel> poissonsModelsl, Context context) {
            this.poissonsModelsl = poissonsModelsl;
            this.poissonsModelListFiltered = poissonsModelsl;
            this.context = context;
        }



        @Override
        public int getCount() {
            return poissonsModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return poissonsModelListFiltered.get(position);
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
            //TextView tailles_minis = view.findViewById(R.id.tailles_minis);

            names.setText(poissonsModelListFiltered.get(position).getName());
            emails.setText(poissonsModelListFiltered.get(position).getScientific_name());
            imageView.setImageResource(poissonsModelListFiltered.get(position).getImages());
            //tailles_minis.setText(poissonsModelListFiltered.get(position).getTaille_mini());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("main activity","item clicked");
                    startActivity(new Intent(PoissonActivity.this,PoissonsPreviewActivity.class).putExtra("items",poissonsModelListFiltered.get(position)));

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
                        filterResults.count = poissonsModelsl.size();
                        filterResults.values = poissonsModelsl;

                    }else{
                        List<PoissonsModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(PoissonsModel poissonsModel:poissonsModelsl){
                            if(poissonsModel.getName().contains(searchStr) || poissonsModel.getScientific_name().contains(searchStr)){
                                resultsModel.add(poissonsModel);

                            }
                            filterResults.count = resultsModel.size();
                            filterResults.values = resultsModel;
                        }


                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    poissonsModelListFiltered = (List<PoissonsModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }



}
