package com.example.user.weather;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherTask extends AsyncTask <String , Void , JSONObject>{

    private String URL="https://api.openweathermap.org/data/2.5/weather?";
    @Override
    protected JSONObject doInBackground(String... strings) {
     Uri uri;
     if(strings.length==1)
     uri=Uri.parse(URL).buildUpon()
             .appendQueryParameter("q" , strings[0])
             .appendQueryParameter("appId" , "956a4d9b7e5dfd8a43dcb1310aaa1c3e")
             .appendQueryParameter("units" , "metric")
             .build();
     else
         uri=Uri.parse(URL).buildUpon()
                 .appendQueryParameter("lat" , strings[0])
                 .appendQueryParameter("lon",strings[1])
                 .appendQueryParameter("appId" , "956a4d9b7e5dfd8a43dcb1310aaa1c3e")
                 .appendQueryParameter("units" , "metric")
                 .build();

        HttpURLConnection connection=null;
        InputStream is=null;
     try{
         java.net.URL url=new URL(uri.toString());
         connection=(HttpURLConnection)url.openConnection();
         is=connection.getInputStream();

         StringBuffer buffer=new StringBuffer();
         BufferedReader reader=new BufferedReader(new InputStreamReader(is));

         String line;
         while((line=reader.readLine())!=null){
             buffer.append(line+"\n");
         }

         JSONObject obj=new JSONObject(buffer.toString());
         return obj;
     }catch (MalformedURLException m){
         m.printStackTrace();
     }catch (IOException io){
         io.printStackTrace();
     }catch (JSONException ex) {
         ex.printStackTrace();
     }
     finally {
         try {
             is.close();
             connection.disconnect();
         }catch (Exception ex){
             ex.printStackTrace();
         }
     }
     return null;
    }
}
