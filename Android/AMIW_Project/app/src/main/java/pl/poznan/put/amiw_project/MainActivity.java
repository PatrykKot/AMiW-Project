package pl.poznan.put.amiw_project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class MainActivity extends AppCompatActivity {

    JSONObject jsonObj;
    private String TAG = MainActivity.class.getSimpleName();

    // URL to get contacts JSON
    private static String url = "http://192.168.0.102:8080/measurements/getLastTest?length=100"; //tu będziemy kombinować ze zmianą ilości pobieranych rekordów


    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsnoDownloandTask().execute();

    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class JsnoDownloandTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "Entering JsnoDownloandTask...");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    jsonObj = new JSONObject(jsonStr);


                } catch (final JSONException e) {
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
                e.printStackTrace();
            }


            List<Entry> entries = new LinkedList<Entry>();
            try {
                for (int i = 0; i < testY.length() && i < testX.length(); ++i) {
                    entries.add(new Entry(testX.getInt(i), testY.getInt(i)));
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i(TAG, "Length " + entries.size());
            LineDataSet dataSet = new LineDataSet(entries, "Próbki");
            LineData lineData = new LineData(dataSet);
            chart.setData(lineData);
            chart.invalidate(); // refresh

            Log.e(TAG, "JsnoDownloandTask ended.");
        }

    }
}