
package com.codigopiojoso.conversordemonedas.conversor;
import com.codigopiojoso.conversordemonedas.api.Api;
import java.util.Scanner;

public class ConversorMonedas {
    private final Scanner consola = new Scanner(System.in);

    public void iniciar() {
        System.out.println("Bienvenido al conversor de monedas");
        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int op = leerOpcion();
            if (op == 7) {
                System.out.println("Saliendo del programa...");
                salir = true;
                continue;
            }
            String[] monedas = obtenerMonedas(op);
            if (monedas == null) {
                System.out.println("Opción no válida, por favor intente de nuevo.");
                continue;
            }
            double monto = leerMonto();
            double tasaCambio = Api.convertirMoneda(monedas[0], monedas[1]);
            double resultado = monto * tasaCambio;
            System.out.printf("""
                ---------------------------------------------
                El resultado de la conversión de %s a %s es: %.2f%n
                ---------------------------------------------
                """, monedas[0], monedas[1], resultado);
        }
    }

    private void mostrarMenu() {
        System.out.println("""
            1. Convertir ARS -> USD
            2. Convertir USD -> ARS
            3. Convertir Real -> USD
            4. Convertir USD -> Real
            5. Convertir EUR -> USD
            6. Convertir USD -> EUR
            7. Salir
            """);
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        while (!consola.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            consola.next();
        }
        return consola.nextInt();
    }

    private double leerMonto() {
        System.out.print("Ingrese el monto a convertir: ");
        while (!consola.hasNextDouble()) {
            System.out.print("Ingrese un monto válido: ");
            consola.next();
        }
        return consola.nextDouble();
    }

    private String[] obtenerMonedas(int op) {
        return switch (op) {
            case 1 -> new String[]{"ARS", "USD"};
            case 2 -> new String[]{"USD", "ARS"};
            case 3 -> new String[]{"BRL", "USD"};
            case 4 -> new String[]{"USD", "BRL"};
            case 5 -> new String[]{"EUR", "USD"};
            case 6 -> new String[]{"USD", "EUR"};
            default -> null;
        };
    }
}