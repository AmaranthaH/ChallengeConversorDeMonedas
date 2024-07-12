package com.alura.screenmatch;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenidos al conversor de divisas");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Menu.mostrarMenuPrincipal();

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                if (opcion == 1) {
                    String monedaBase = Menu.seleccionarMoneda("base");
                    String monedaObjetivo = Menu.seleccionarMoneda("objetivo");

                    if (monedaBase != null && monedaObjetivo != null) {
                        System.out.print("¿Cuánto " + monedaBase + " quiere convertir? ");
                        double cantidad = Double.parseDouble(scanner.nextLine());

                        ApiResponse apiResponse = ApiClient.obtenerTasasDeCambio(monedaBase);

                        if (apiResponse != null) {
                            double tasaDeCambio = apiResponse.getTasaDeCambio(monedaObjetivo);
                            String horaActualizacion = apiResponse.getHoraActualizacion();

                            if (tasaDeCambio != -1) {
                                double resultado = cantidad * tasaDeCambio;
                                System.out.printf("\n El cambio de %.2f %s es %.2f %s%n", cantidad, monedaBase, resultado, monedaObjetivo);
                                System.out.println("Última actualización de la tasa de cambio: " + horaActualizacion+"\n");
                            } else {
                                System.out.println("Error al obtener la tasa de cambio para " + monedaObjetivo);
                            }
                        } else {
                            System.out.println("Error al obtener las tasas de cambio.");
                        }
                    }
                } else if (opcion == 2) {
                    System.out.println("Gracias por utilizar el conversor de divisas.");
                    break;
                } else {
                    System.out.println("¡La opción seleccionada (" + opcion + ") no es válida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("¡La opción seleccionada no es válida!");
            }
        }
    }
}
