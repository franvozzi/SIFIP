package sifip.controller;

import sifip.model.Gasto;
import sifip.dao.GastoDAO;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase GastoController
 * 
 * Controlador que maneja la lógica de presentación para el registro de gastos.
 * Esta clase implementa el patrón MVC (Model-View-Controller) actuando como
 * intermediario entre la interfaz de usuario (consola) y la capa de datos.
 * 
 * Responsabilidades principales:
 * - Capturar entrada del usuario desde la consola
 * - Validar y procesar los datos de entrada
 * - Crear objetos Gasto con los datos proporcionados
 * - Coordinar con el DAO para persistir los datos
 * - Proporcionar retroalimentación al usuario
 * 
 * Esta clase es parte de la capa de presentación y se encarga de
 * la interacción directa con el usuario final para el registro de gastos.
 */
public class GastoController {
    // Dependencias del controlador
    private GastoDAO gastoDAO = new GastoDAO();  // Para persistir datos
    private Scanner scanner = new Scanner(System.in);   // Para capturar entrada del usuario

    /**
     * Registra un nuevo gasto en el sistema
     * 
     * Este método:
     * 1. Solicita al usuario los datos del gasto
     * 2. Valida y procesa la entrada
     * 3. Crea un objeto Gasto
     * 4. Lo guarda en la base de datos
     * 5. Confirma la operación al usuario
     * 
     * @throws Exception Si ocurre un error durante el registro
     */
    public void registrarGasto() throws Exception {
        // Captura de datos del gasto desde la consola
        
        System.out.println("=== Registro de Nuevo Gasto ===");
        
        // Captura del monto
        System.out.println("Monto del gasto:");
        float monto = Float.parseFloat(scanner.nextLine());
        
        // Captura de la descripción
        System.out.println("Descripción del gasto:");
        String descripcion = scanner.nextLine();
        
        // Captura de la categoría
        System.out.println("Categoría del gasto (ej: Alimentación, Transporte, Entretenimiento):");
        String categoria = scanner.nextLine();
        
        // Captura de la fecha
        System.out.println("Fecha del gasto (formato: yyyy-mm-dd):");
        String fechaStr = scanner.nextLine();
        java.util.Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        
        // Captura del ID del usuario
        System.out.println("ID del usuario:");
        int idUsuario = Integer.parseInt(scanner.nextLine());

        // Creación del objeto Gasto con los datos capturados
        Gasto gasto = new Gasto(monto, descripcion, categoria, fecha, idUsuario);
        
        // Persistencia en la base de datos
        gastoDAO.guardar(gasto);
        
        // Confirmación al usuario
        System.out.println("✓ Gasto registrado exitosamente.");
    }
}