package sifip.controller;

import sifip.service.IngresoService;

import java.time.LocalDate;
import java.util.Scanner;

public class IngresoController {

    private final IngresoService ingresoService;
    private final Scanner scanner;

    public IngresoController() {
        this.ingresoService = new IngresoService();
        this.scanner = new Scanner(System.in);
    }

    public void registrarIngreso() {
        System.out.println("\n--- Registrar Ingreso ---");

        try {
            System.out.print("Monto: ");
            double monto = Double.parseDouble(scanner.nextLine());

            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Periodicidad (ej. mensual, ocasional): ");
            String periodicidad = scanner.nextLine();

            System.out.print("Fecha (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            // Por ahora se asume un único usuario con ID = 1
            boolean exito = ingresoService.registrarIngreso(monto, descripcion, periodicidad, fecha, 1);

            if (exito) {
                System.out.println("Ingreso registrado exitosamente.\n");
            } else {
                System.out.println("No se pudo registrar el ingreso.\n");
            }

        } catch (Exception e) {
            System.out.println("Error al ingresar los datos. Verifique e intente nuevamente.\n");
        }
    }
} 
