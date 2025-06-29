package sifip.controller;

import sifip.model.Activo;
import sifip.dao.ActivoDAO;
import java.util.Scanner;

/**
 * Clase ActivoController
 * 
 * Controlador que maneja la lógica de presentación para la gestión de activos financieros.
 * Esta clase implementa el patrón MVC (Model-View-Controller) actuando como
 * intermediario entre la interfaz de usuario (consola) y la capa de datos.
 * 
 * Responsabilidades principales:
 * - Capturar entrada del usuario desde la consola
 * - Validar y procesar los datos de entrada
 * - Crear y actualizar objetos Activo
 * - Coordinar con el DAO para persistir los datos
 * - Proporcionar retroalimentación al usuario
 * 
 * Esta clase es parte de la capa de presentación y se encarga de
 * la interacción directa con el usuario final para la gestión de activos.
 */
public class ActivoController {
    // Dependencias del controlador
    private ActivoDAO activoDAO = new ActivoDAO();  // Para persistir datos
    private Scanner scanner = new Scanner(System.in);   // Para capturar entrada del usuario

    /**
     * Registra un nuevo activo financiero en el sistema
     * 
     * Este método:
     * 1. Solicita al usuario los datos del activo
     * 2. Valida y procesa la entrada
     * 3. Crea un objeto Activo
     * 4. Lo guarda en la base de datos
     * 5. Confirma la operación al usuario
     * 
     * @throws Exception Si ocurre un error durante el registro
     */
    public void registrarActivo() throws Exception {
        // Captura de datos del activo desde la consola
        
        System.out.println("=== Registro de Nuevo Activo ===");
        
        // Captura del nombre del activo
        System.out.println("Nombre del activo (ej: Apple Inc., Tesla, Casa):");
        String nombre = scanner.nextLine();
        
        // Captura del tipo de activo
        System.out.println("Tipo de activo (ej: Acciones, Bonos, Propiedad):");
        String tipo = scanner.nextLine();
        
        // Captura de la cantidad
        System.out.println("Cantidad de unidades:");
        float cantidad = Float.parseFloat(scanner.nextLine());
        
        // Captura del precio inicial
        System.out.println("Precio inicial por unidad:");
        float precioInicial = Float.parseFloat(scanner.nextLine());
        
        // Captura de la fecha
        System.out.println("Fecha de adquisición (formato: yyyy-mm-dd):");
        String fechaStr = scanner.nextLine();
        java.sql.Date fecha = java.sql.Date.valueOf(fechaStr);
        
        // Captura del ID del usuario
        System.out.println("ID del usuario:");
        int idUsuario = Integer.parseInt(scanner.nextLine());

        // Creación del objeto Activo con los datos capturados
        Activo activo = new Activo(nombre, tipo, cantidad, precioInicial, fecha, idUsuario);
        
        // Persistencia en la base de datos
        activoDAO.guardar(activo);
        
        // Confirmación al usuario
        System.out.println("✓ Activo registrado exitosamente.");
    }

    /**
     * Actualiza el precio actual de un activo existente
     * 
     * Este método:
     * 1. Solicita al usuario el ID del activo a actualizar
     * 2. Solicita el nuevo precio
     * 3. Actualiza el precio en la base de datos
     * 4. Confirma la operación al usuario
     * 
     * @throws Exception Si ocurre un error durante la actualización
     */
    public void actualizarPrecio() throws Exception {
        // Captura de datos para la actualización
        
        System.out.println("=== Actualización de Precio de Activo ===");
        
        // Captura del ID del activo
        System.out.println("ID del activo a actualizar:");
        int id = Integer.parseInt(scanner.nextLine());
        
        // Captura del nuevo precio
        System.out.println("Nuevo precio por unidad:");
        float nuevoPrecio = Float.parseFloat(scanner.nextLine());
        
        // Actualización en la base de datos
        activoDAO.actualizarPrecio(id, nuevoPrecio);
        
        // Confirmación al usuario
        System.out.println("✓ Precio actualizado exitosamente.");
    }
}