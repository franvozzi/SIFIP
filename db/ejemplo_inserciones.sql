INSERT INTO Usuario (nombre) VALUES ('Francisco Vozzi');

INSERT INTO Ingreso (monto, descripcion, periodicidad, fecha, id_usuario)
VALUES (100000.00, 'Sueldo mensual', 'mensual', '2025-05-01', 1);

INSERT INTO Gasto (monto, descripcion, categoria, fecha, id_usuario)
VALUES (3500.00, 'Factura de electricidad', 'Servicios', '2025-05-02', 1);

INSERT INTO Activo (nombre, tipo, cantidad, precio_inicial, precio_actual, fecha, id_usuario)
VALUES ('BTC', 'Criptomoneda', 0.1, 50000.00, 65000.00, '2025-04-30', 1);

INSERT INTO Configuracion (umbral_ahorro, recomendaciones_activadas, id_usuario)
VALUES (10000.00, TRUE, 1);
