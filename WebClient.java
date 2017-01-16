package br.com.andrey.devmakertest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by andrey on 28/12/2016.
 */

public class WebClient{

  public String get(){
            try {
                StringBuilder result = new StringBuilder();
                URL url = new URL("http://andreyviktor.esy.es/DevMakerTest/list-contacts.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept","application/json");

                connection.connect();

               // Scanner scanner = new Scanner(connection.getInputStream());


                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    InputStream in = new BufferedInputStream(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                }


                return String.valueOf(result);      //scanner.next();
            } catch (IOException e) {
                e.printStackTrace();
            }
      return "error";

    }
}

