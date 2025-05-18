package sifip;

import sifip.controller.IngresoController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IngresoController ingresoController = new IngresoController();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Sistema de Planificación Financiera Personal (SIFIP) ===");
                System.out.println("1. Registrar ingreso");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1" -> ingresoController.registrarIngreso();
                    case "0" -> {
                        System.out.println("Saliendo del sistema. ¡Hasta luego!");
                        return;
                    }
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            }
        }
    }
}
