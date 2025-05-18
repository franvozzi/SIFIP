package sifip.controller;

import sifip.service.ActivoService;

import java.time.LocalDate;
import java.util.Scanner;

public class ActivoController {

    private final ActivoService activoService;
    private final Scanner scanner;

    public ActivoController() {
        this.activoService = new ActivoService();
        this.scanner = new Scanner(System.in);
    }

    public void registrarActivo() {
        System.out.println("\n--- Registrar Activo ---");

        try {
            System.out.print("Nombre del activo: ");
            String nombre = scanner.nextLine();

            System.out.print("Tipo (acción, cripto, FCI, etc.): ");
            String tipo = scanner.nextLine();

            System.out.print("Cantidad: ");
            double cantidad = Double.parseDouble(scanner.nextLine());

            System.out.print("Precio inicial: ");
            double precioInicial = Double.parseDouble(scanner.nextLine());

            System.out.print("Precio actual: ");
            double precioActual = Double.parseDouble(scanner.nextLine());

            System.out.print("Fecha (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            boolean exito = activoService.registrarActivo(nombre, tipo, cantidad, precioInicial, precioActual, fecha, 1);

            if (exito) {
                System.out.println("Activo registrado exitosamente.\n");
            } else {
                System.out.println("No se pudo registrar el activo.\n");
            }

        } catch (Exception e) {
            System.out.println("Error al ingresar los datos. Verifique e intente nuevamente.\n");
        }
    }
} 