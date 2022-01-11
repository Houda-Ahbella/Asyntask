package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;


import java.io.InputStream;
import java.util.List;

import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView1);


        new Async().execute("lecture.xml");

    }



   class Async extends AsyncTask<String, Integer, String> {
        String TAG = getClass().getSimpleName();
        List<Employee> employees;
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
           Toast.makeText(getApplicationContext(),"First step", Toast.LENGTH_SHORT).show();


        }
        @Override
        protected String doInBackground(String...arg0)
        {
            try {
                XMLPullParserHandler parser = new XMLPullParserHandler();
                InputStream is=getAssets().open(arg0[0]);
                this.employees = parser.parse(is);



            } catch (Exception e) {e.printStackTrace();}


            return null;
        }
        protected void onProgressUpdate(Integer...a) {
            super.onProgressUpdate(a);
           Toast.makeText(getApplicationContext(),"Progressig", Toast.LENGTH_SHORT).show();

        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            for (Employee e: employees){
                System.out.println(e);
            }
            ArrayAdapter<Employee> adapter =new ArrayAdapter<Employee>
                    (getApplicationContext(), R.layout.text_view_layout , this.employees);
            listView.setAdapter(adapter);
        }
    }


}