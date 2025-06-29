# SIFIP - Sistema de Planificación Financiera Personal

<div align="center">
  <img src="SIFIP.png" width="30%">
</div>

## 📋 Descripción del Proyecto

**SIFIP** es un prototipo de sistema de gestión de finanzas personales desarrollado en **Java** con persistencia en **PostgreSQL**, diseñado para facilitar la planificación financiera en el contexto económico argentino. 

Este proyecto forma parte del **Seminario de Práctica de Informática** de la **Universidad Empresarial Siglo 21**.

### 🎯 Objetivos del Sistema

El SIFIP permite a los usuarios:
- ✅ Registrar ingresos y gastos con categorización
- 📊 Seguir el rendimiento de inversiones (acciones, criptomonedas, fondos comunes de inversión, plazos fijos)
- 📈 Generar reportes financieros detallados
- 💡 Recibir recomendaciones personalizadas
- 💰 Calcular balances y análisis de flujo de caja

### 🏗️ Arquitectura del Sistema

El proyecto implementa el **patrón MVC (Model-View-Controller)** con las siguientes capas:

#### 📁 Estructura de Capas

```
src/main/java/sifip/
├── 📊 model/          # Capa de Modelo (Entidades de negocio)
│   ├── Transaccion.java    # Clase abstracta base para transacciones
│   ├── Ingreso.java        # Entidad para ingresos
│   ├── Gasto.java          # Entidad para gastos
│   ├── Activo.java         # Entidad para activos financieros
│   ├── Reporte.java        # Entidad para reportes
│   └── Calculable.java     # Interfaz para cálculos financieros
├── 🎮 controller/      # Capa de Controlador (Lógica de presentación)
│   ├── IngresoController.java
│   ├── GastoController.java
│   └── ActivoController.java
├── 🔧 service/         # Capa de Servicio (Lógica de negocio)
│   └── ReporteService.java
├── 💾 dao/            # Capa de Acceso a Datos
│   ├── DAO.java           # Interfaz genérica para DAOs
│   ├── DBConfig.java      # Configuración de base de datos
│   ├── IngresoDAO.java
│   ├── GastoDAO.java
│   └── ActivoDAO.java
└── 🚀 Main.java       # Punto de entrada de la aplicación
```

#### 🔄 Patrones de Diseño Implementados

1. **MVC (Model-View-Controller)**
   - **Model**: Clases de entidad (Ingreso, Gasto, Activo)
   - **View**: Interfaz de consola con Scanner
   - **Controller**: Controladores que manejan la interacción

2. **DAO (Data Access Object)**
   - Abstracción del acceso a datos
   - Separación entre lógica de negocio y persistencia

3. **Template Method**
   - Clase abstracta `Transaccion` define estructura común
   - Subclases implementan comportamientos específicos

4. **Strategy**
   - Interfaz `Calculable` permite diferentes algoritmos de cálculo
   - Cada entidad implementa su propia lógica de cálculo

5. **Service Layer**
   - `ReporteService` coordina múltiples DAOs
   - Encapsula lógica de negocio compleja

## 🚀 Guía Rápida de Ejecución

### 1. Requisitos Previos
- **Java JDK 11** o superior
- **PostgreSQL 14** o superior
- **Git**

### 2. Configuración Inicial

#### 🔧 Paso 1: Clonar el repositorio
```bash
git clone https://github.com/franvozzi/SIFIP.git
cd SIFIP
```

#### 🗄️ Paso 2: Configurar PostgreSQL
```bash
# Crear la base de datos
createdb sifip

# Crear las tablas
psql sifip -f db/create_tables.sql
```

#### ⚙️ Paso 3: Verificar configuración
Asegúrate que el archivo `src/main/resources/config.properties` tenga las credenciales correctas:
```properties
db.url=jdbc:postgresql://localhost:5432/sifip
db.user=tu_usuario
db.password=tu_password
```

### 3. Compilar y Ejecutar

1. **Compilar el proyecto**
   ```bash
   # En macOS/Linux:
   javac -cp "lib/*:src/main/java" src/main/java/sifip/Main.java
   
   # En Windows:
   javac -cp "lib/*;src/main/java" src/main/java/sifip/Main.java
   ```

#### ▶️ Ejecutar la aplicación
```bash
# En macOS/Linux:
java -cp "lib/*:src/main/java" sifip.Main

# En Windows:
java -cp "lib/*;src/main/java" sifip.Main
```

### 4. 🎯 Uso de la Aplicación

Una vez ejecutada, la aplicación mostrará un menú con las siguientes opciones:
1. Registrar ingreso
2. Registrar gasto
3. Registrar activo
0. Salir

Selecciona una opción ingresando el número correspondiente.

### Solución de Problemas Comunes

1. **Error de conexión a la base de datos**
   - Verifica que PostgreSQL esté corriendo
   - Confirma las credenciales en `config.properties`
   - Asegúrate que la base de datos `sifip` existe

2. **Error de compilación**
   - Verifica que el JDK esté instalado correctamente
   - Confirma que el driver PostgreSQL (`lib/postgresql-42.7.5.jar`) está presente

3. **Error de ejecución**
   - Asegúrate de estar en el directorio correcto
   - Verifica que la clase Main.class se haya generado

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

## 📁 Estructura Completa del Repositorio

```
SIFIP/
├── 📊 db/                     # Scripts SQL para la base de datos
│   ├── create_tables.sql      # Esquema completo con comentarios
│   └── ejemplo_inserciones.sql # Datos de ejemplo
├── 📚 docs/                   # Documentación y diagramas UML
│   ├── diagramas/
│   │   ├── SIFIP.png         # Diagrama de casos de uso
│   │   └── dominio.png       # Diagrama de dominio
│   └── Trabajo_Practico_1_SEMINARIO_VOZZI.docx
├── 💻 src/main/
│   ├── java/sifip/           # Código fuente Java
│   │   ├── controller/       # Controladores MVC
│   │   ├── dao/             # Capa de acceso a datos
│   │   ├── model/           # Entidades de negocio
│   │   ├── service/         # Lógica de negocio
│   │   └── Main.java        # Punto de entrada
│   └── resources/
│       └── config.properties # Configuración de BD
├── 🧪 test/                   # Pruebas unitarias (en desarrollo)
├── 📦 lib/                    # Dependencias
│   └── postgresql-42.7.5.jar # Driver JDBC PostgreSQL
├── 📝 .gitignore
└── 📖 README.md              # Este archivo
```

## Diagramas

Ver `docs/diagramas/` para diagramas UML:
- `SIFIP.png`: Diagrama de casos de uso
- `dominio.png`: Diagrama de dominio (entidades y relaciones)


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

## 👨‍💻 Autor y Contacto

**Autor**: Francisco Vozzi  
**Repositorio**: https://github.com/franvozzi/SIFIP  
**Universidad**: Universidad Empresarial Siglo 21  
**Proyecto**: Seminario de Práctica de Informática

---

<div align="center">
  <p><strong>🎓 Proyecto Académico - Universidad Empresarial Siglo 21</strong></p>
  <p><em>Sistema de Finanzas Personales con Arquitectura MVC y Patrones de Diseño</em></p>
</div>
