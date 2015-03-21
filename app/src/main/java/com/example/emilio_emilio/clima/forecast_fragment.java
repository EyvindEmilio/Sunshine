package com.example.emilio_emilio.clima;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
/**
 * Created by Emilio-Emilio on 2/21/2015.
 */
public  class forecast_fragment extends Fragment {
    ArrayAdapter<String> mForecastAdapter;
    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment,menu);
        //super.onCreateOptionsMenu(menu, inflater);
    }

    public void updateWeather(){
        //FetchWeatherTask myAsynk =  new FetchWeatherTask(getActivity(),mForecastAdapter);
        FetchWeatherTask myAsynk =  new FetchWeatherTask(getActivity(),mForecastAdapter);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location = pref.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        myAsynk.execute(location);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_refresh:
               updateWeather();
              return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public forecast_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //String [] lista = {"Eyvind","Judy","Adriana","Emilio"};

       // List<String> clima = new ArrayList<String>(Arrays.asList(lista));

        mForecastAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_forecast,R.id.listview_forecast_teviewview,new ArrayList<String>());

        ListView listView = (ListView)rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String forecastStr = mForecastAdapter.getItem(position);
                //Toast.makeText(getActivity(),forecastStr,Toast.LENGTH_LONG ).show();
                Intent detailActivity = new Intent(getActivity(),DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT,forecastStr);
                startActivity(detailActivity);
            }
        });
        return rootView;
    }
}