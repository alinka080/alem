package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String BASE_URL = "https://catfact.ninja/fact";
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();
            JSONObject jsonResponse = new JSONObject(response.toString());
            String fact = jsonResponse.getString("fact");
            String fact1 = translateText(fact);
            System.out.println(fact);

        }
        catch (Exception e){
            System.out.println("Ошибка: "+ e.getMessage());
        }
    }
    private static  String translateText(String text) throws Exception {
        String TRANSLATE_API_URL ="https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ru&dt=t&q";

        String urlString = TRANSLATE_API_URL + URLEncoder.encode(text,"UTF-8");
        URL url = new  URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();
        JSONObject jsonResponse = new JSONObject(response.toString());

        return new JSONArray(response.toString()).getJSONArray(0).getJSONArray(0).getString(0);
    }
}