package sifip;

import sifip.controller.IngresoController;
import sifip.controller.GastoController;
import sifip.controller.ActivoController;
import sifip.dao.IngresoDAO;
import sifip.dao.GastoDAO;
import sifip.service.ReporteService;
import sifip.model.Reporte;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * SIFIP - Sistema de Finanzas Personales
 * 
 * Esta es la clase principal que inicia la aplicación de gestión financiera personal.
 * Implementa un patrón MVC (Model-View-Controller) donde:
 * - Model: Las clases de entidad (Ingreso, Gasto, Activo, etc.)
 * - View: La interfaz de consola con el usuario
 * - Controller: Los controladores que manejan la lógica de negocio
 * 
 * La aplicación permite:
 * 1. Registrar ingresos y gastos
 * 2. Gestionar activos financieros
 * 3. Generar reportes de balance
 * 4. Categorizar gastos para análisis
 */
public class Main {
    
    /**
     * Método principal que inicia la aplicación
     * Configura todas las dependencias y presenta el menú principal al usuario
     */
    public static void main(String[] args) {
        // Inicialización de componentes siguiendo el patrón de inyección de dependencias
        Scanner scanner = new Scanner(System.in);
        
        // Creación de controladores que manejan la lógica de presentación
        IngresoController ingresoController = new IngresoController();
        GastoController gastoController = new GastoController();
        ActivoController activoController = new ActivoController();
        
        // Creación de DAOs para acceso a datos
        IngresoDAO ingresoDAO = new IngresoDAO();
        GastoDAO gastoDAO = new GastoDAO();
        
        // Creación del servicio de reportes que coordina múltiples DAOs
        ReporteService reporteService = new ReporteService(ingresoDAO, gastoDAO);

        // Bucle principal de la aplicación - menú interactivo
        while (true) {
            try {
                // Presentación del menú principal
                System.out.println("\n=== SIFIP - Sistema de Finanzas Personales ===");
                System.out.println("Menú Principal:");
                System.out.println("1. Registrar Ingreso");
                System.out.println("2. Registrar Gasto");
                System.out.println("3. Registrar/Actualizar Activo");
                System.out.println("4. Generar Reporte");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea para evitar problemas de entrada

                // Switch que maneja las diferentes opciones del menú
                switch (opcion) {
                    case 1:
                        // Delegación al controlador de ingresos
                        ingresoController.registrarIngreso();
                        break;
                    case 2:
                        // Delegación al controlador de gastos
                        gastoController.registrarGasto();
                        break;
                    case 3:
                        // Submenú para gestión de activos
                        System.out.println("Gestión de Activos:");
                        System.out.println("1. Registrar Nuevo Activo | 2. Actualizar Precio");
                        System.out.print("Seleccione una opción: ");
                        try {
                            int subOpcion = scanner.nextInt();
                            scanner.nextLine();
                            if (subOpcion == 1) {
                                activoController.registrarActivo();
                            } else if (subOpcion == 2) {
                                activoController.actualizarPrecio();
                            } else {
                                System.out.println("Opción inválida para gestión de activos.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, ingrese un número válido (1 o 2).");
                            scanner.nextLine(); // Limpiar el buffer
                        }
                        break;
                    case 4:
                        // Generación de reportes financieros
                        System.out.println("Ingrese el ID de usuario para generar el reporte:");
                        try {
                            int idUsuario = scanner.nextInt();
                            Reporte reporte = reporteService.generarReporte(idUsuario);
                            System.out.println("Reporte generado: " + reporte);
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, ingrese un ID de usuario válido (número).");
                            scanner.nextLine(); // Limpiar el buffer
                        }
                        break;
                    case 5:
                        // Salida limpia de la aplicación
                        System.out.println("¡Gracias por usar SIFIP!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida (1-5).");
                }
            } catch (InputMismatchException e) {
                // Manejo específico para entradas incorrectas
                System.err.println("Error: Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            } catch (Exception e) {
                // Manejo de errores para una mejor experiencia de usuario
                System.err.println("Error en la aplicación: " + e.getMessage());
                System.err.println("Por favor, intente nuevamente.");
            }
        }
    }
}