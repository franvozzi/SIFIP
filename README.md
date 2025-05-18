# SIFIP - Sistema de Planificación Financiera Personal
<div align="center">
  <img src="SIFIP.png" width="30%">
</div>
SIFIP es una aplicación de consola desarrollada en Java que permite a los usuarios gestionar de forma local sus finanzas personales. Está orientado al contexto argentino e incluye funcionalidades para registrar ingresos, gastos, activos financieros y recibir recomendaciones de ahorro.

## 🎯 Objetivo

Ayudar a personas no especializadas en finanzas a planificar sus ingresos, controlar sus gastos, evaluar inversiones y fomentar hábitos de ahorro a través de una herramienta simple, local y funcional.

## 🛠️ Tecnologías utilizadas

- Java 17
- MySQL 8
- JDBC (Java Database Connectivity)
- PlantUML (para diagramas UML)

## 📦 Funcionalidades principales

- Registro de ingresos, gastos y activos
- Cálculo de balance financiero
- Generación de reportes por categoría y período
- Recomendaciones de ahorro e inversión
- Almacenamiento local de los datos (offline)

## 📁 Estructura del proyecto

```
sifip/
├── src/
│   ├── main/java/sifip/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── dao/
│   │   └── model/
│   └── resources/
├── db/
│   ├── create_tables.sql
│   └── ejemplo_inserciones.sql
├── docs/
│   ├── diagramas/
│   └── ejemplos_sql/
├── README.md
├── .gitignore

```

## 🚀 Cómo ejecutar el sistema

1. Clonar el repositorio:
```bash
git clone https://github.com/tuusuario/sifip.git
```

2. Crear la base de datos MySQL y ejecutar `db/create_tables.sql` y `db/ejemplo_inserciones.sql`

3. Compilar el proyecto:
```bash
javac -d bin src/main/java/sifip/**/*.java
```

4. Ejecutar la clase `Main.java`

## ✍️ Autor

Francisco Vozzi
Universidad Empresarial Siglo 21 – Mayo 2025
