package com.example.eventsterapp.Network;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class NetworkController {

    public void getSomething(){

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://vefforritun2-2019-h1-synilausn.herokuapp.com")
                        .build();
                Response response = null;

                try{
                    response = client.newCall(request).execute();
                    return response.body().string();
                }
                catch (IOException e){
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                System.out.println("postExecution======");
                System.out.println(o.toString());
            }
        }.execute();

        }


}
