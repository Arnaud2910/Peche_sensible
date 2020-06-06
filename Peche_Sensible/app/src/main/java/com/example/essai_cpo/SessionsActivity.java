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

public class SessionsActivity extends AppCompatActivity {

    String date[] = {" 12 janvier", "13 fevrier", "31 fevrier"};
    String lieu[] = {"Crozon", "Telgruc", "Argol"};
    String contenu[] = {"12 maquereaux, 3 moules, 4 rouget","",""};

    List<SessionsModel> sessionsModelList = new ArrayList<>();

    ListView listView;

    SessionsActivity.CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_sessions);
        listView = findViewById(R.id.listview2);


        for(int i = 0;i < date.length;i++){

            SessionsModel sessionsModel = new SessionsModel(date[i],lieu[i],contenu[i]);

            sessionsModelList.add(sessionsModel);

        }

        customAdapter = new SessionsActivity.CustomAdapter(sessionsModelList,this);

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

        private List<SessionsModel> sessionsModelssl;
        private List<SessionsModel> sessionsModelsListFiltered;
        private Context context;

        public CustomAdapter(List<SessionsModel> sessionsModelssl, Context context) {
            this.sessionsModelssl = sessionsModelssl;
            this.sessionsModelsListFiltered = sessionsModelssl;
            this.context = context;
        }



        @Override
        public int getCount() {
            return sessionsModelsListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return sessionsModelsListFiltered.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.row_sessions,null);


            TextView dates = view.findViewById(R.id.date);
            TextView lieux = view.findViewById(R.id.lieu);


            //

            dates.setText(sessionsModelsListFiltered.get(position).getdate());
            lieux.setText(sessionsModelsListFiltered.get(position).getlieu());


            //

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("main activity","item clicked");
                    startActivity(new Intent(SessionsActivity.this,SessionsPreviewActivity.class).putExtra("items",sessionsModelsListFiltered.get(position)));

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
                        filterResults.count = sessionsModelssl.size();
                        filterResults.values = sessionsModelssl;

                    }else{
                        List<SessionsModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(SessionsModel sessionsModel:sessionsModelssl){
                            if(sessionsModel.getdate().contains(searchStr) || sessionsModel.getlieu().contains(searchStr)){
                                resultsModel.add(sessionsModel);

                            }
                            filterResults.count = resultsModel.size();
                            filterResults.values = resultsModel;
                        }


                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    sessionsModelsListFiltered = (List<SessionsModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }



}