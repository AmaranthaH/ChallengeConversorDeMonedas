package com.alura.screenmatch;

import java.util.Map;

public class ApiResponse {
    private final Map<String, Double> conversionRates;
    private final String timeLastUpdateUtc;

    public ApiResponse(Map<String, Double> conversionRates, String timeLastUpdateUtc) {
        this.conversionRates = conversionRates;
        this.timeLastUpdateUtc = timeLastUpdateUtc;
    }

    public double getTasaDeCambio(String monedaObjetivo) {
        return conversionRates.getOrDefault(monedaObjetivo, -1.0);
    }

    public String getHoraActualizacion() {
        return timeLastUpdateUtc;
    }
}


