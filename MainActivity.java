package com.example.gvg.myweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by GVG on 29.10.2017.
 */

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView text;
    private static final String MY_PREFERENCES = "MyWeatherPreferences";
    private static final String MY_START_CITY = "City";
    private SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.myweather_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putInt(MY_START_CITY, spinner.getSelectedItemPosition());
        editor.apply();
    }

    protected void init() {

        spinner = (Spinner) findViewById(R.id.select_list);
        myPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        if (myPreferences.contains(MY_START_CITY)) {
            spinner.setSelection(myPreferences.getInt(MY_START_CITY, 0));
        }

        text = (TextView) findViewById(R.id.weather_text);
        text.setText("");
        Button button = (Button) findViewById(R.id.select_button);
        button.setOnClickListener(new MyOnClickListener());

    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.select_button) {
                int idSelect = spinner.getSelectedItemPosition();
                String[] cityArray = getResources().getStringArray(R.array.city_list);
                text.setText(cityArray[idSelect]);
            }
        }
    }

}


