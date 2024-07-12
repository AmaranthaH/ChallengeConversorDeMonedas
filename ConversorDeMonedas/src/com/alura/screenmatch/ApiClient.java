package com.alura.screenmatch;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ApiClient {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/4a6cf505d44961a375826823/latest/";

    public static ApiResponse obtenerTasasDeCambio(String monedaBase) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + monedaBase))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            Map<String, Double> conversionRates = gson.fromJson(jsonObject.getAsJsonObject("conversion_rates"), Map.class);
            String timeLastUpdateUtc = jsonObject.get("time_last_update_utc").getAsString();
            return new ApiResponse(conversionRates, timeLastUpdateUtc);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al realizar la solicitud a la API: " + e.getMessage());
            return null;
        }
    }
}
