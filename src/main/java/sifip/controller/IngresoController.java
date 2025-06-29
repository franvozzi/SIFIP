package sifip.controller;

import sifip.model.Ingreso;
import sifip.dao.IngresoDAO;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase IngresoController
 * 
 * Controlador que maneja la lógica de presentación para el registro de ingresos.
 * Esta clase implementa el patrón MVC (Model-View-Controller) actuando como
 * intermediario entre la interfaz de usuario (consola) y la capa de datos.
 * 
 * Responsabilidades principales:
 * - Capturar entrada del usuario desde la consola
 * - Validar y procesar los datos de entrada
 * - Crear objetos Ingreso con los datos proporcionados
 * - Coordinar con el DAO para persistir los datos
 * - Proporcionar retroalimentación al usuario
 * 
 * Esta clase es parte de la capa de presentación y se encarga de
 * la interacción directa con el usuario final.
 */
public class IngresoController {
    // Dependencias del controlador
    private IngresoDAO ingresoDAO = new IngresoDAO();  // Para persistir datos
    private Scanner scanner = new Scanner(System.in);   // Para capturar entrada del usuario

    /**
     * Registra un nuevo ingreso en el sistema
     * 
     * Este método:
     * 1. Solicita al usuario los datos del ingreso
     * 2. Valida y procesa la entrada
     * 3. Crea un objeto Ingreso
     * 4. Lo guarda en la base de datos
     * 5. Confirma la operación al usuario
     * 
     * @throws Exception Si ocurre un error durante el registro
     */
    public void registrarIngreso() throws Exception {
        // Captura de datos del ingreso desde la consola
        
        System.out.println("=== Registro de Nuevo Ingreso ===");
        
        // Captura del monto
        System.out.println("Monto del ingreso:");
        float monto = Float.parseFloat(scanner.nextLine());
        
        // Captura de la descripción
        System.out.println("Descripción del ingreso:");
        String descripcion = scanner.nextLine();
        
        // Captura de la periodicidad
        System.out.println("Periodicidad del ingreso (ej: Mensual, Semanal, Único):");
        String periodicidad = scanner.nextLine();
        
        // Captura de la fecha
        System.out.println("Fecha del ingreso (formato: yyyy-mm-dd):");
        String fechaStr = scanner.nextLine();
        java.util.Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        
        // Captura del ID del usuario
        System.out.println("ID del usuario:");
        int idUsuario = Integer.parseInt(scanner.nextLine());

        // Creación del objeto Ingreso con los datos capturados
        Ingreso ingreso = new Ingreso(monto, descripcion, periodicidad, fecha, idUsuario);
        
        // Persistencia en la base de datos
        ingresoDAO.guardar(ingreso);
        
        // Confirmación al usuario
        System.out.println("✓ Ingreso registrado exitosamente.");
    }
}