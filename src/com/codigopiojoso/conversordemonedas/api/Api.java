package com.codigopiojoso.conversordemonedas.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    private static final String API_KEY = "3c0094f78e032fc6bf68c87d";

    public static double convertirMoneda(String monedaOrigen, String monedaDestino){
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, monedaOrigen);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject rates = json.getAsJsonObject("conversion_rates");
            return rates.get(monedaDestino).getAsDouble();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la tasa de cambio: " + e);
        }
    }
}
