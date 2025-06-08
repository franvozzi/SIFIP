# SIFIP - Sistema de Planificación Financiera Personal

<div align="center">
  <img src="SIFIP.png" width="30%">
</div>

Prototipo de un sistema de gestión de finanzas personales desarrollado en Java con persistencia en MySQL, diseñado para facilitar la planificación financiera en el contexto económico argentino. 
Este proyecto forma parte del Seminario de Práctica de Informática, Facultad de Informática, Universidad Empresarial Siglo 21.

## Descripción

El SIFIP permite a los usuarios registrar ingresos y gastos, seguir el rendimiento de inversiones (acciones, criptomonedas, fondos comunes de inversión, plazos fijos), generar reportes financieros y recibir recomendaciones personalizadas. El prototipo inicial se implementa con una interfaz en consola y utiliza solo Java estándar (JDK) y MySQL, sin frameworks externos, según las restricciones del proyecto.

## Requisitos

- **JDK**: Versión 11 o superior (verificar con `java -version`)
- **MySQL**: Versión 8.0 o superior (verificar con `mysql --version`)
- **Driver JDBC de MySQL**: Archivo `mysql-connector-java-8.0.33.jar` (descargable desde https://dev.mysql.com/downloads/connector/j/) o configurado vía Maven
- Sistema operativo con terminal (Linux, macOS, o Windows con WSL)

## Configuración

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/franvozzi/SIFIP.git
cd SIFIP
```

### Paso 2: Configurar la Base de Datos

1. Inicia MySQL y crea la base de datos:
   ```sql
   CREATE DATABASE sifip_db;
   ```

2. Ejecuta el script para crear las tablas:
   ```bash
   mysql -u root -p sifip_db < db/create_tables.sql
   ```
   Ingresa tu contraseña de MySQL cuando se solicite.

3. (Opcional) Carga datos de prueba ejecutando:
   ```bash
   mysql -u root -p sifip_db < db/ejemplo_inserciones.sql
   ```

### Paso 3: Configurar las Credenciales

Edita el archivo `src/main/resources/config.properties` con tus credenciales de MySQL:

```properties
db.url=jdbc:mysql://localhost:3306/sifip_db
db.user=root
db.password=tu_contraseña
```

Reemplaza `tu_contraseña` con la contraseña de tu usuario MySQL.

### Paso 4: Configurar el Driver JDBC

Descarga `mysql-connector-java-8.0.33.jar` desde https://dev.mysql.com/downloads/connector/j/ y colócalo en una carpeta `lib/` en el directorio raíz del proyecto.

Alternativamente, si usas Maven, agrega la dependencia en un `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Paso 5: Compilar y Ejecutar

1. Compila el proyecto:
   ```bash
   javac -cp .:lib/mysql-connector-java-8.0.33.jar src/main/java/sifip/*.java src/main/java/sifip/controller/*.java src/main/java/sifip/dao/*.java src/main/java/sifip/model/*.java src/main/java/sifip/service/*.java
   ```
   En Windows, usa `;` en lugar de `:` (ej., `-cp .;lib/mysql-connector-java-8.0.33.jar`).

2. Ejecuta la aplicación:
   ```bash
   java -cp .:lib/mysql-connector-java-8.0.33.jar sifip.Main
   ```

## Funcionalidades del Prototipo

El prototipo inicial implementa las siguientes características:

- **Registro de Ingresos (RF1)**: Registrar ingresos con monto, descripción, periodicidad y fecha
- **Registro de Gastos (RF2)**: Registrar gastos con monto, descripción, categoría y fecha
- **Registro y Actualización de Activos (RF3, RF6)**: Registrar activos financieros y actualizar precios para calcular rendimientos
- **Cálculo de Balance (RF4)**: Mostrar el balance financiero (ingresos - gastos)
- **Reporte de Gastos por Categoría (RF5)**: Generar un reporte de gastos agrupados por categoría
- **Persistencia en MySQL**: Almacenar todos los datos en una base de datos local

## Estructura del Repositorio

```
SIFIP/
├── db/                     # Scripts SQL para la base de datos
│   ├── create_tables.sql
│   └── ejemplo_inserciones.sql
├── docs/                   # Documentación y diagramas UML
│   ├── diagramas/
│   │   ├── SIFIP.png
│   │   └── dominio.png
│   └── Trabajo_Practico_1_SEMINARIO_VOZZI.docx
├── src/main/
│   ├── java/sifip/
│   │   ├── controller/     # Controladores para la interfaz
│   │   ├── dao/           # Clases de acceso a datos para MySQL
│   │   ├── model/         # Clases modelo (Ingreso, Gasto, Activo, Reporte)
│   │   ├── service/       # Clases de servicio para la lógica de negocio
│   │   └── Main.java      # Clase principal
│   └── resources/
│       └── config.properties
├── test/                   # Carpeta para pruebas unitarias (en desarrollo)
├── lib/                    # Driver JDBC
│   └── mysql-connector-java-8.0.33.jar
├── .gitignore
└── README.md
```

## Diagramas

Ver `docs/diagramas/` para diagramas UML:
- `SIFIP.png`: Diagrama de casos de uso
- `dominio.png`: Diagrama de dominio (entidades y relaciones)

## Documentación

Consulta el archivo `docs/Trabajo_Practico_1_SEMINARIO_VOZZI.docx` o `.pdf` para detalles completos del proyecto, incluyendo la propuesta de solución, requerimientos y justificaciones.

## Pruebas

Para verificar que el sistema funciona correctamente:

1. Ejecuta la aplicación siguiendo las instrucciones de configuración
2. Prueba cada funcionalidad desde la interfaz en consola:
   - Registra ingresos y gastos
   - Agrega activos y actualiza precios
   - Genera reportes de gastos por categoría
   - Verifica el cálculo de balance
3. Confirma que los datos persistan en MySQL consultando las tablas directamente

## Notas

- El proyecto usa solo Java estándar y MySQL, sin frameworks externos, como se especifica en las restricciones
- La interfaz es en consola (Java Scanner) y funciona offline
- Última actualización: 08 de junio de 2025

---

**Autor**: Francisco Vozzi    
**Repositorio**: https://github.com/franvozzi/SIFIP