package main.resources;

import java.io.FileInputStream;
import java.util.Properties;

public class DBConfig {
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(fis);
            URL = prop.getProperty("db.url");
            USER = prop.getProperty("db.user");
            PASSWORD = prop.getProperty("db.password");
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar config.properties", e);
        }
    }

    public static String getUrl() { return URL; }
    public static String getUser() { return USER; }
    public static String getPassword() { return PASSWORD; }
}