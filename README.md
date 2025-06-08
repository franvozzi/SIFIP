# SIFIP - Sistema de Planificación Financiera Personal

<div align="center">
  <img src="SIFIP.png" width="30%">
</div>

Prototipo de un sistema de gestión de finanzas personales desarrollado en Java con persistencia en PostgreSQL, diseñado para facilitar la planificación financiera en el contexto económico argentino. 
Este proyecto forma parte del Seminario de Práctica de Informática, Facultad de Informática, Universidad Empresarial Siglo 21.

## Descripción

El SIFIP permite a los usuarios registrar ingresos y gastos, seguir el rendimiento de inversiones (acciones, criptomonedas, fondos comunes de inversión, plazos fijos), generar reportes financieros y recibir recomendaciones personalizadas. El prototipo inicial se implementa con una interfaz en consola y utiliza solo Java estándar (JDK) y PostgreSQL, sin frameworks externos, según las restricciones del proyecto.

## Requisitos

- **JDK**: Versión 11 o superior (verificar con `java -version`)
- **PostgreSQL**: Versión 14 o superior (verificar con `psql --version`)
- **Driver JDBC de PostgreSQL**: Archivo `postgresql-42.7.5.jar` (incluido en el proyecto)
- Sistema operativo con terminal (Linux, macOS, o Windows con WSL)

## Configuración

### Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/franvozzi/SIFIP.git
cd SIFIP
```

### Paso 2: Configurar la Base de Datos

1. Inicia PostgreSQL y crea la base de datos:
   ```bash
   createdb sifip
   ```

2. Ejecuta el script para crear las tablas:
   ```bash
   psql sifip -f db/create_tables.sql
   ```

3. (Opcional) Carga datos de prueba ejecutando:
   ```bash
   psql sifip -f db/ejemplo_inserciones.sql
   ```

### Paso 3: Configurar las Credenciales

Edita el archivo `src/main/resources/config.properties` con tus credenciales de PostgreSQL:

```properties
db.url=jdbc:postgresql://localhost:5432/sifip
db.user=franvozzi
db.password=fran
```

### Paso 4: Compilar y Ejecutar

**Importante:** El archivo `lib/postgresql-42.7.5.jar` (driver JDBC de PostgreSQL) no está incluido en el repositorio por su tamaño. Debes descargarlo manualmente desde https://jdbc.postgresql.org/download.html y colocarlo en la carpeta `lib/` antes de compilar y ejecutar el proyecto.

1. Compila el proyecto:
   ```bash
   javac -cp .:lib/postgresql-42.7.5.jar -d . src/main/java/sifip/*.java src/main/java/sifip/controller/*.java src/main/java/sifip/dao/*.java src/main/java/sifip/model/*.java src/main/java/sifip/service/*.java
   ```
   En Windows, usa `;` en lugar de `:` (ej., `-cp .;lib/postgresql-42.7.5.jar`).

2. Ejecuta la aplicación:
   ```bash
   java -cp .:lib/postgresql-42.7.5.jar sifip.Main
   ```

## Nota Técnica: Elección de PostgreSQL

El proyecto utiliza PostgreSQL en lugar de MySQL por las siguientes razones:

1. **Compatibilidad con Mac M1**: PostgreSQL ofrece mejor estabilidad y rendimiento en arquitecturas ARM (M1/M2) de Apple, evitando los crashes y bugs frecuentes que se presentan con MySQL Workbench en estos sistemas.

2. **Robustez**: PostgreSQL es conocido por su robustez y cumplimiento con los estándares SQL, lo que garantiza mayor confiabilidad en el manejo de datos.

3. **Características Avanzadas**: PostgreSQL ofrece características avanzadas como:
   - Mejor manejo de transacciones
   - Tipos de datos más ricos
   - Mejor soporte para JSON
   - Mejor manejo de concurrencia

4. **Comunidad Activa**: PostgreSQL tiene una comunidad muy activa y un excelente soporte para diferentes plataformas.

## Funcionalidades del Prototipo

El prototipo inicial implementa las siguientes características:

- **Registro de Ingresos (RF1)**: Registrar ingresos con monto, descripción, periodicidad y fecha
- **Registro de Gastos (RF2)**: Registrar gastos con monto, descripción, categoría y fecha
- **Registro y Actualización de Activos (RF3, RF6)**: Registrar activos financieros y actualizar precios para calcular rendimientos
- **Cálculo de Balance (RF4)**: Mostrar el balance financiero (ingresos - gastos)
- **Reporte de Gastos por Categoría (RF5)**: Generar un reporte de gastos agrupados por categoría
- **Persistencia en PostgreSQL**: Almacenar todos los datos en una base de datos local

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
│   │   ├── dao/           # Clases de acceso a datos para PostgreSQL
│   │   ├── model/         # Clases modelo (Ingreso, Gasto, Activo, Reporte)
│   │   ├── service/       # Clases de servicio para la lógica de negocio
│   │   └── Main.java      # Clase principal
│   └── resources/
│       └── config.properties
├── test/                   # Carpeta para pruebas unitarias (en desarrollo)
├── lib/                    # Driver JDBC
│   └── postgresql-42.7.5.jar
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
3. Confirma que los datos persistan en PostgreSQL consultando las tablas directamente

## Notas

- El proyecto usa solo Java estándar y PostgreSQL, sin frameworks externos, como se especifica en las restricciones
- La interfaz es en consola (Java Scanner) y funciona offline
- **El archivo `lib/postgresql-42.7.5.jar` debe ser descargado manualmente y no está en el repositorio.**
- Última actualización: 09 de junio de 2025

---

**Autor**: Francisco Vozzi    
**Repositorio**: https://github.com/franvozzi/SIFIP