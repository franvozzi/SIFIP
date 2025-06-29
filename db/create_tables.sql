-- =====================================================
-- SIFIP - Sistema de Finanzas Personales
-- Script de creación de tablas de base de datos
-- =====================================================
-- 
-- Este script crea todas las tablas necesarias para el funcionamiento
-- del sistema de finanzas personales SIFIP.
-- 
-- Estructura de la base de datos:
-- - Usuario: Información básica de los usuarios del sistema
-- - Ingreso: Registro de entradas de dinero (salarios, rentas, etc.)
-- - Gasto: Registro de salidas de dinero (compras, servicios, etc.)
-- - Activo: Registro de activos financieros (acciones, bonos, propiedades)
-- - Configuracion: Configuraciones personalizadas por usuario
-- 
-- Características del esquema:
-- - Uso de claves foráneas para mantener integridad referencial
-- - Tipos de datos apropiados para cada campo
-- - Restricciones para garantizar la calidad de los datos
-- =====================================================

-- Tabla de usuarios del sistema
-- Almacena la información básica de cada usuario registrado
CREATE TABLE Usuario (
  id_usuario SERIAL PRIMARY KEY,  -- Identificador único autoincremental
  nombre VARCHAR(100) NOT NULL    -- Nombre completo del usuario
);

-- Tabla de ingresos
-- Registra todas las entradas de dinero para cada usuario
CREATE TABLE Ingreso (
  id_ingreso SERIAL PRIMARY KEY,           -- Identificador único del ingreso
  monto DECIMAL(12,2) NOT NULL,            -- Cantidad monetaria (máximo 9999999999.99)
  descripcion VARCHAR(255),                -- Descripción del ingreso (ej: "Salario mensual")
  periodicidad VARCHAR(50),                -- Frecuencia del ingreso (ej: "Mensual", "Semanal")
  fecha DATE NOT NULL,                     -- Fecha en que se recibió el ingreso
  id_usuario INT,                          -- Referencia al usuario propietario
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)  -- Restricción de integridad referencial
);

-- Tabla de gastos
-- Registra todas las salidas de dinero para cada usuario
CREATE TABLE Gasto (
  id_gasto SERIAL PRIMARY KEY,             -- Identificador único del gasto
  monto DECIMAL(12,2) NOT NULL,            -- Cantidad monetaria (máximo 9999999999.99)
  descripcion VARCHAR(255),                -- Descripción del gasto (ej: "Compra supermercado")
  categoria VARCHAR(50),                   -- Categoría del gasto (ej: "Alimentación", "Transporte")
  fecha DATE NOT NULL,                     -- Fecha en que se realizó el gasto
  id_usuario INT,                          -- Referencia al usuario propietario
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)  -- Restricción de integridad referencial
);

-- Tabla de activos financieros
-- Registra los activos financieros de cada usuario (acciones, bonos, propiedades, etc.)
CREATE TABLE Activo (
  id_activo SERIAL PRIMARY KEY,            -- Identificador único del activo
  nombre VARCHAR(100) NOT NULL,            -- Nombre del activo (ej: "Apple Inc.", "Tesla")
  tipo VARCHAR(50),                        -- Tipo de activo (ej: "Acciones", "Bonos", "Propiedad")
  cantidad DECIMAL(10,4),                  -- Cantidad de unidades (permite decimales para acciones)
  precio_inicial DECIMAL(12,2),            -- Precio por unidad al momento de la compra
  precio_actual DECIMAL(12,2),             -- Precio por unidad actual (se actualiza periódicamente)
  fecha DATE NOT NULL,                     -- Fecha de adquisición del activo
  id_usuario INT,                          -- Referencia al usuario propietario
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)  -- Restricción de integridad referencial
);

-- Tabla de configuración personalizada
-- Almacena configuraciones específicas para cada usuario
CREATE TABLE Configuracion (
  id_configuracion SERIAL PRIMARY KEY,     -- Identificador único de la configuración
  umbral_ahorro DECIMAL(12,2),             -- Umbral mínimo de ahorro mensual
  recomendaciones_activadas BOOLEAN,       -- Si las recomendaciones están habilitadas
  id_usuario INT UNIQUE,                   -- Referencia única al usuario (un usuario = una configuración)
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)  -- Restricción de integridad referencial
);

-- =====================================================
-- Índices recomendados para mejorar el rendimiento
-- =====================================================

-- Índice en fecha para consultas por período
CREATE INDEX idx_ingreso_fecha ON Ingreso(fecha);
CREATE INDEX idx_gasto_fecha ON Gasto(fecha);
CREATE INDEX idx_activo_fecha ON Activo(fecha);

-- Índice en id_usuario para consultas por usuario
CREATE INDEX idx_ingreso_usuario ON Ingreso(id_usuario);
CREATE INDEX idx_gasto_usuario ON Gasto(id_usuario);
CREATE INDEX idx_activo_usuario ON Activo(id_usuario);

-- Índice en categoría para análisis de gastos
CREATE INDEX idx_gasto_categoria ON Gasto(categoria);

-- =====================================================
-- Comentarios adicionales sobre el diseño:
-- =====================================================
-- 
-- 1. Uso de DECIMAL en lugar de FLOAT para precisión monetaria
-- 2. Claves foráneas para mantener integridad referencial
-- 3. Campos de fecha para análisis temporal
-- 4. Categorización de gastos para análisis detallado
-- 5. Seguimiento de precios de activos para cálculo de rendimientos
-- 6. Configuración personalizada por usuario
-- 
-- Este esquema permite:
-- - Análisis de flujo de caja
-- - Seguimiento de gastos por categoría
-- - Cálculo de rendimientos de activos
-- - Reportes personalizados por usuario
-- - Configuraciones específicas por usuario
