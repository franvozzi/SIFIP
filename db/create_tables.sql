CREATE TABLE Usuario (
  id_usuario SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Ingreso (
  id_ingreso SERIAL PRIMARY KEY,
  monto DECIMAL(12,2) NOT NULL,
  descripcion VARCHAR(255),
  periodicidad VARCHAR(50),
  fecha DATE NOT NULL,
  id_usuario INT,
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Gasto (
  id_gasto SERIAL PRIMARY KEY,
  monto DECIMAL(12,2) NOT NULL,
  descripcion VARCHAR(255),
  categoria VARCHAR(50),
  fecha DATE NOT NULL,
  id_usuario INT,
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Activo (
  id_activo SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  tipo VARCHAR(50),
  cantidad DECIMAL(10,4),
  precio_inicial DECIMAL(12,2),
  precio_actual DECIMAL(12,2),
  fecha DATE NOT NULL,
  id_usuario INT,
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Configuracion (
  id_configuracion SERIAL PRIMARY KEY,
  umbral_ahorro DECIMAL(12,2),
  recomendaciones_activadas BOOLEAN,
  id_usuario INT UNIQUE,
  FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);
