package sifip.controller;

import sifip.service.GastoService;

import java.time.LocalDate;
import java.util.Scanner;

public class GastoController {

    private final GastoService gastoService;
    private final Scanner scanner;

    public GastoController() {
        this.gastoService = new GastoService();
        this.scanner = new Scanner(System.in);
    }

    public void registrarGasto() {
        System.out.println("\n--- Registrar Gasto ---");

        try {
            System.out.print("Monto: ");
            double monto = Double.parseDouble(scanner.nextLine());

            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine();

            System.out.print("Fecha (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            boolean exito = gastoService.registrarGasto(monto, descripcion, categoria, fecha, 1);

            if (exito) {
                System.out.println("Gasto registrado exitosamente.\n");
            } else {
                System.out.println("No se pudo registrar el gasto.\n");
            }

        } catch (Exception e) {
            System.out.println("Error al ingresar los datos. Verifique e intente nuevamente.\n");
        }
    }
} 
