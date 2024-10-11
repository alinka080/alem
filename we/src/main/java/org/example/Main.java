package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    private static final String API_KEY = "API_ключ";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) throws ExecutionException,ExecutionException {
        Scanner scanner =new Scanner(System.in);
        System.out.print("Введите город: ");
        final String city = scanner.nextLine();

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                String urlString =BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric&lang=ru";
                URL url = new URL(urlString);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode == 404) {
                    System.out.println("ERROR 404 \nГород не найден. Пожалуйста, попробуйте еще раз.");
                    return;
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }
                in.close();
                conn.disconnect();

                System.out.println(response);
            }
            catch (Exception e){
                System.out.println("Ошибка: " + e.getMessage());
            }
        });
    }
}