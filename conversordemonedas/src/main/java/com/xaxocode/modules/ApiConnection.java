package com.xaxocode.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ApiConnection {
    /**
     * We specify that the URLConnection class is a Utility class just made to get
     * he currency data and save it to a local json file with Private Constructor.
     */

    private ApiConnection() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * We create a method with which we will make the connection to the
     * CurrencyFreaks API and we will obtain the information from the api using the
     * GET method in a connection Https and we will save it in a json file to use it
     */

    public static String getData() {

        /*
         * We define the line parameter with which we will read what we obtained
         * with GET.
         * 
         * We define the responseBody parameter with which we will store all th
         * information obtained in GET momentarily while the method is executed
         * and then write it in the JSON
         * 
         * We define apikey from CurrencyFreaks to working all.
         */

        String line;
        String responsebody = "";
        String apikey = "";

        /* Resource to working all in case we get Exception IOException */

        try {

            /*
             * Putting in the api URL and telling it to use the apikey variable to complete
             * it (we use typecast to say "java" this is connection HTTPS trust me hehe)
             */

            URL urlapi = new URL(
                    "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=" + apikey);

            /* Say HTTPS start connection with urlapi */

            HttpsURLConnection connection = (HttpsURLConnection) urlapi.openConnection();

            /* Specified method to use in connection is GET */

            connection.setRequestMethod("GET");

            /*
             * InputStream (connection.getInputStream()): Used to get the response from the
             * server in the form of a binary stream (bytes).
             * 
             * InputStreamReader: Converts the binary stream into a character stream to make
             * text easier to read.
             * 
             * BufferedReader: Provides the ability to read characters line by line, making
             * it easier to process long server responses or line-structured text.
             * 
             * Using try-resources to read content we get in connection to apiurl and make a
             * cycle while to say please all line = reader.readLine() , readline() = read
             * LINE PER LINE content we get in connection and after make that we make a
             * compare to say in case CONTENT is diferent NULL make that and in that scope
             * we say responseBody += Line but better use StringBuilder to make better code
             * and append() make that and after make all we say responsecode =
             * bld.toString()
             */

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder bld = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    bld.append(line);
                }
                responsebody = bld.toString();
            }

            /* This is the initial trycatch to avoid any connection errors */

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responsebody;
    }

}