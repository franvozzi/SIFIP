package sifip.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Clase DBConfig
 * 
 * Maneja la configuración y conexión a la base de datos PostgreSQL.
 * Esta clase implementa el patrón Singleton para la configuración de base de datos
 * y utiliza un archivo de propiedades para almacenar las credenciales de manera segura.
 * 
 * Características principales:
 * - Carga configuración desde archivo properties
 * - Inicializa el driver de PostgreSQL
 * - Proporciona conexiones a la base de datos
 * - Maneja errores de conexión de manera centralizada
 * 
 * Esta clase es fundamental para toda la capa de acceso a datos del sistema.
 */
public class DBConfig {
    // Constantes para la configuración de la base de datos
    private static final String URL;      // URL de conexión a la base de datos
    private static final String USER;     // Usuario de la base de datos
    private static final String PASSWORD; // Contraseña de la base de datos

    /**
     * Bloque estático de inicialización
     * Se ejecuta una sola vez cuando se carga la clase en memoria.
     * Carga la configuración desde el archivo properties y registra el driver de PostgreSQL.
     */
    static {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            // Carga las propiedades desde el archivo de configuración
            prop.load(fis);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");
            
            // Registra el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            // Si hay un error en la configuración, lanza una excepción que detiene la aplicación
            throw new RuntimeException("Error al cargar config.properties o driver: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene una conexión a la base de datos
     * 
     * @return Connection - Conexión activa a la base de datos PostgreSQL
     * @throws Exception Si no se puede establecer la conexión
     */
    public static Connection getConnection() throws Exception {
        try {
            // Crea y retorna una nueva conexión usando las credenciales cargadas
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            // Manejo de errores de conexión con información detallada
            throw new Exception("Error al conectar a la base de datos: " + e.getMessage(), e);
        }
    }
}