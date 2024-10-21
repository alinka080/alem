package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
        System.out.println("Введите какой тип мема вы хотите: ");
        System.out.println("1 - собака");
        System.out.println("2 - факт о котиках");
        System.out.println("3 - анекдоты для стариков");
        System.out.println("0 - выход");
        int command = scanner.nextInt();
        switch (command) {
            case 1:
                String Base_URL = "https://dog.ceo/api/breeds/image/random";
                try {
                    URL url = new URL(Base_URL);
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

                    String message = jsonResponse.getString("message");
                    System.out.println(message);
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
                break;
            case 2:
                String Base_URL1 = "https://catfact.ninja/fact";
                try {
                    URL url = new URL(Base_URL1);
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
                    System.out.println(fact1);

                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
                break;
            case 3:
                String Base_URL2 = "https://official-joke-api.appspot.com/random_joke";
                try {
                    URL url = new URL(Base_URL2);
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

                    String setup = jsonResponse.getString("setup");
                    String punchline = jsonResponse.getString("punchline");
                    String type = jsonResponse.getString("type");

                    String setup1 = translateText(setup);
                    System.out.println(setup1);

                    String punchline1 = translateText(punchline);
                    System.out.println(punchline1);


                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
                break;

            case 0:
                System.out.println("Выход");
                System.exit(0);
            default:
                System.out.println("Извините, такой команды пока нет.");
                break;
                }

        }
    }
    private static String translateText(String text) throws Exception {
        String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ru&dt=t&q=";

        String urlString = TRANSLATE_API_URL + URLEncoder.encode(text, "UTF-8");
        URL url = new URL(urlString);
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
        return new JSONArray(response.toString()).getJSONArray(0).getJSONArray(0).getString(0);
    }
}
