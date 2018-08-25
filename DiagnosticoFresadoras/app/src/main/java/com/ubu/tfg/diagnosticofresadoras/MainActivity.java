package com.ubu.tfg.diagnosticofresadoras;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Button chooseAlarm = findViewById(R.id.bSingleAlarm);
        Button getAlarms = findViewById(R.id.bManyAlarms);

        chooseAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChooseAlarmActivity.class);
                startActivity(i);
            }
        });

        getAlarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ApiRestActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferencesActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDataApiRest() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("https://milliot.ctme.org/find/app/alarmas/Mill/" +
                            "3effe3e9a8ce5167fb4d6de17c3d1521?_from_date=>1000000");
                    //url
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) url.openConnection();
                    myConnection.setRequestProperty("Header", "token=6NMUHLHR28L0Z79I4SJTKTQZV800YWZ5DZBIT4D7FLJB");
                    if (myConnection.getResponseCode() == 200) {
                        String value = null;
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String key = jsonReader.nextName();
                            Log.i("OK Api Rest", key);
                            if (key.equals("_id")) {
                                value = jsonReader.nextString();
                                break;
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.close();
                        myConnection.disconnect();
                        Log.i("OK Api Rest", value);
                    } else {
                        Log.i("Error Api Rest", "No response");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
