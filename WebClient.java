package br.com.andrey.devmakertest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by andrey on 28/12/2016.
 */

public class WebClient implements Runnable{


    @Override
    public void run() {
        public String GET(){
            try {
                URL url = new URL("");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept","application/json");
                InputStream in = new BufferedInputStream(connection.getInputStream());
                readStream(in);
                return ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

