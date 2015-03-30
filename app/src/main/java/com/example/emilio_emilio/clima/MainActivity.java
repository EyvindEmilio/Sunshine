package com.example.emilio_emilio.clima;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {
    public final String LOG_TAG = getClass().getSimpleName();

    @Override
    protected void onStart() {
        Log.w(LOG_TAG,"onStart");
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new forecast_fragment())
                    .commit();
        }
        Log.w(LOG_TAG,"onCreate");
    }

    @Override
    protected void onPause() {
        Log.w(LOG_TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.w(LOG_TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.w(LOG_TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.w(LOG_TAG,"onStop");
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }else if(id == R.id.action_map ){
            openPreferredLocationInMap();
        }


        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap(){
       /* SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String location = pref.getString(getString(R.string.pref_location_key),getString(R.string.pref_location_default));
*/
        String location = Utility.getPreferredLocation(this);
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q",location)
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d(LOG_TAG,"Couldn't call "+location+" no exist");
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */

}
