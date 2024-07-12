package com.alura.screenmatch;

import java.util.Scanner;

public class Menu {
    private static final String[] MONEDAS = {"ARS - Peso argentino", "BOB - Boliviano boliviano",
            "BRL - Real brasileño", "CLP - Peso chileno",
            "COP - Peso colombiano", "USD - Dólar estadounidense"};

    public static void mostrarMenuPrincipal() {
        System.out.println("\n*******************************************");
        System.out.println("""
                Seleccione una opción:
                1) Convertir moneda
                2) Salir
                """);
    }

    public static String seleccionarMoneda(String tipo) {
        System.out.println("\nSeleccione la moneda " + tipo + ":");
        for (int i = 0; i < MONEDAS.length; i++) {
            System.out.printf("%d) %s%n", i + 1, MONEDAS[i]);
        }

        Scanner scanner = new Scanner(System.in);

        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            if (opcion >= 1 && opcion <= MONEDAS.length) {
                return MONEDAS[opcion - 1].split(" - ")[0];
            } else {
                System.out.println("¡Opción no válida!");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("¡Opción no válida!");
            return null;
        }
    }
}


