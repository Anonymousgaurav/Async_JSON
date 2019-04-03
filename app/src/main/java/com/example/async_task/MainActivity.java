package com.example.async_task;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<NewsData> newsDatas = new ArrayList<>();
    RecyclerView recyclerView;
    RecycleAdapter recycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.newsrecy);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        new NewsData1().execute();

    }

    public class NewsData1 extends AsyncTask<String, String, String> {
        String Title, Name, Author, UrlImage;

        HttpURLConnection httpURLConnection;
        String json = "";
        JSONObject jsonObject;
        StringBuilder stringBuilder = new StringBuilder();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("onPostExecute: ", s);
            try {
                //all data
                jsonObject = new JSONObject(json);

                //json array with length
                JSONArray jsonArray = jsonObject.getJSONArray("articles");

                for (int i = 0; i < jsonArray.length(); i++) {
                    NewsData newsData = new NewsData();

                    //all data in array and get authors name
                Log.d("in",jsonArray.getJSONObject(i).getString("author"));
                    Author=(jsonArray.getJSONObject(i).getString("author"));
                    newsData.setAuthor(jsonArray.getJSONObject(i).getString("author"));

                    Title=(jsonArray.getJSONObject(i).getString("title"));
                    newsData.setTitle(jsonArray.getJSONObject(i).getString("title"));

                    UrlImage=(jsonArray.getJSONObject(i).getString("urlToImage"));
                    newsData.setUrlToImage(jsonArray.getJSONObject(i).getString("urlToImage"));

                    //source key json obj in json array
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i).getJSONObject("source");
                   //name.add(jsonObject1.getString("name"));

                    //all name from source json obj which is in article json array
                  Log.d("nameData",jsonObject1.getString("name"));

                    Name=(jsonObject1.getString("name"));
                   Log.d("data",jsonObject1.toString());

                    newsData.setName(jsonObject1.getString("name"));
                    newsDatas.add(newsData);

                    Log.d("author", newsData.getAuthor());
                    Log.d("name", newsData.getName());
                    Log.d("title", newsData.getTitle());
                    Log.d("img", newsData.getUrlToImage());

                }
              Log.d("lng", String.valueOf(jsonArray.length()));


                for (int i = 0; i < newsDatas.size(); i++) {
                    Log.d("Values", newsDatas.get(i).getTitle());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recycleAdapter = new RecycleAdapter(newsDatas, getApplicationContext());
            recyclerView.setAdapter(recycleAdapter);
        }


    @Override
        protected String doInBackground(String... strings) {
        try {
            URL url=new URL("https://newsapi.org/v2/top-headlines?country=in&apiKey=b4f0614a42bc491498fa9fc73943a173");
            httpURLConnection=(HttpURLConnection)url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String data;
            while((data = bufferedReader.readLine()) != null) {
                stringBuilder.append(data);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

      Log.d("doInBackground: ",stringBuilder.toString());
        json=stringBuilder.toString();


        Log.d("doInBackground: ",json);

        return json;
    }
    }
}