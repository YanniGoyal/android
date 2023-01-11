package com.nav.backgroundemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class BG extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            Log.d("YanniBG", "doInBackground: ran");
            String result = "";
            URL url;
            HttpURLConnection conn;
            try {
                url = new URL(urls[0]);
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = 0;
                data = reader.read();
                while(data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("YanniBG", "error");
                return "Something Went Wrong";
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            Log.d("YanniBG", "onPreExecute: ran");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("YanniBG", "onPostExecute: ran");
            Log.d("YanniBG", s);
        }
    }
    Button button;
    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.showInputText);
        editText = findViewById(R.id.inputText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();

                SharedPreferences shrd = getSharedPreferences("demo", MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd.edit();

                editor.putString("str1", msg);
                editor.putString("str2", "This is value 2 added by yanni");
                editor.putString("str3", "This is value 3 added by yanni");
                editor.apply();

                textView.setText(msg);

            }
        });

        // Get the value of shared preference back
        SharedPreferences getShared = getSharedPreferences("demo", MODE_PRIVATE);
        String value1 = getShared.getString("str1","Save a note and it will show up here");
        String value2 = getShared.getString("str2","Save a note and it will show up here");
        String value3 = getShared.getString("str3","Save a note and it will show up here");
        textView.setText(value1 + value2 + value3);


    }
    public void click(View view) {
        EditText editText = findViewById(R.id.inputText);
        TextView textView = findViewById(R.id.showInputText);
        String msg = editText.getText().toString();

        SharedPreferences shrd = getSharedPreferences("demo", MODE_PRIVATE);
        SharedPreferences.Editor editor = shrd.edit();

        editor.putString("str", msg);
        editor.apply();
        SharedPreferences getShared = getSharedPreferences("demo", MODE_PRIVATE);
        String value = getShared.getString("str", "Save a note and it will show up");
        textView.setText(value);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}