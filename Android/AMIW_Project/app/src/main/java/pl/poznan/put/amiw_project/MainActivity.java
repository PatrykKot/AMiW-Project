package pl.poznan.put.amiw_project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class MainActivity extends AppCompatActivity {
    Handler timerHandler = new Handler();
    long startTime = 0;
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            updatedata();
            timerHandler.postDelayed(this, 1000);
        }
    };
    JSONObject jsonObj=new JSONObject();
    private String TAG = MainActivity.class.getSimpleName();


    private static String url = "/measurements/getLast?length=";

    private static String adres = "http://192.168.1.5:8080";
    private static String probki = "100";

    private void updatedata ()
    {
        final EditText adresEdit= (EditText)findViewById(R.id.editText_adres);
        final EditText probkiEdit= (EditText)findViewById(R.id.editText2_probki);

        adres=adresEdit.getText().toString();
        if(Integer.parseInt(probkiEdit.getText().toString()) <100)
        {
            probkiEdit.setText("100");
        }
        probki=probkiEdit.getText().toString();
        new JsnoDownloandTask().execute();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button przycisk = (Button)findViewById(R.id.button_refresh);
        przycisk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("start");
                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("stop");
                }
            }
        });
    }
    private class JsnoDownloandTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "Entering JsnoDownloandTask...");
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(adres+url+probki);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    jsonObj = new JSONObject(jsonStr);
                }
                catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            LineChart chart = (LineChart) findViewById(R.id.chart);
            JSONArray testX=new JSONArray();
            JSONArray testY=new JSONArray();
            try {
                testX = jsonObj.getJSONArray("X");
                testY = jsonObj.getJSONArray("Y");
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
            Log.e(TAG,  "List<Entry> entries = new LinkedList<Entry>()");
            List<Entry> entries = new LinkedList<Entry>();
            try {
                for (int i = 0; i < testY.length() && i < testX.length(); ++i) {
                    entries.add(new Entry(testX.getInt(i), testY.getInt(i)));
                }
            }
            catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
            Log.i(TAG, "Length " + entries.size());
            LineDataSet dataSet = new LineDataSet(entries, "Próbki");
            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate();
            Log.e(TAG, "JsnoDownloandTask ended.");
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        Button b = (Button)findViewById(R.id.button_refresh);
        b.setText("start");
    }
}