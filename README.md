# Litigando prueba tecnica
## Stack:
* Spring Boot

## Database:
```
docker run -d --name oracle23free -p 1521:1521 -p 5500:5500 -e ORACLE_PWD=oracle123 container-registry.oracle.com/database/free:latest
```

## Punto D
### 1. ¿Qué cambios harías en tu solución si el archivo CSV pasara de 1.000 a 10 millones de registros?
R/: Primero mantendria el batch size, para realizar la insercion por grupos sin que la base de datos tenga tantos llamados. Luego haria el uso de colas de mensajes, para enviarle a cada worker lo que seria un batch size y que el lo guarde asincronamente, desacoplando la lectura del CSV y el guardado en la base de datos.
Tambien se podria leer solo linea por linea sin guardar en memoria, hay un problema y es que lo que es el allData que guarda el reader esta en memoria, llegara un punto en el que el CSV sea tan grande que la memoria se agote, por lo que en vez de guardarlos, se podria solo leerlas para realizar las operaciones.

### 2. ¿Cómo manejarías los registros que fallan al insertarse en la base de datos?
R/: Principalmente en el codigo podemos visualizar que gracias al logger podemos guardar el log del error cuando se guarda en la base de datos, a futuro se podria implementar una tabla de errores dentro de la base de datos, la cual pueda guardar cada fila que incumple las validaciones o tambien el motivo del fallo. 
Tambien implementaria un sistema de reintentos controlados, controlando la cantidad de veces que se realizaria de nuevo la operacion, y esperando en cada intento cierto tiempo para no saturar al a base de datos.

### 3. ¿Qué tipo de pruebas implementarías para este proyecto y qué buscarías validar con cada una?
R/: Primero pruebas unitarias, ellas para la validar las funciones del parsing del CSV, los checks del email, y lo que seria la asignacion de roles.
Luego tendriamos pruebas de integracion, donde comprobariamos que un batch completo se guarde correctamente en la base de datos, como tambien se podria revisar que los registros invalidos se registren en los logs.
Tendriamos pruebas de estres o de rendimiento, donde se carguen CSVs con millones de registros, para poder medir tiempos, memoria y tambien revisar diferentes tamanos del batch size.


