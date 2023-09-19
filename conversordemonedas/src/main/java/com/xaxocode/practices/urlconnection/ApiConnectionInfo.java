package com.xaxocode.practices.urlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiConnectionInfo {

    private ApiConnectionInfo() {
        throw new IllegalStateException("Utility Class");
    }

    public static String getData() {

        String line;
        String responsebody = "";
        String apikey = "";

        try {

            URL urlapi = new URL(
                    "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=" + apikey);

            HttpsURLConnection connection = (HttpsURLConnection) urlapi.openConnection();

            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder bld = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    bld.append(line);
                }
                responsebody = bld.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responsebody;
    }

}