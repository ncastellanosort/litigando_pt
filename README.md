# Litigando prueba tecnica
## Stack
* Spring Boot

## Ejecutar el proyecto
1. Clonar el repositorio
```
git clone https://github.com/ncastellanosort/litigando_pt.git
cd litigando_pt
```
2. Conectarse a la base de datos y ejecutar el script.sql para crear las tablas.
Crear un .env con los valores para la conexion de la base de datos.
```
docker run -d --name oracle23free -p 1521:1521 -p 5500:5500 -e ORACLE_PWD=oracle123 container-registry.oracle.com/database/free:latest
```
3. Construir y ejecutar la aplicacion.
```
mvn clean install
mvn spring-boot:run
```
## Libreria Open CSV
Se uso esta libreria ya que nos ofrece funciones ya establecidas para la lectura del CSV, como lo seria CSVReader, o el manejo de excepciones para el tema de los CSVs.

## Punto C
### Frecuencia de numeros
Complejidad temporal: O(n), ya que recorre el array solo 1 vez para guardar las frecuencias, con el metodo del
getOrDefault, podemos realizar 1 solo recorrido sumando de a 1 si se encuentra el numero.
### Suma objetivo
1. Forma no optimizada:
* Complejidad temporal: O(n^2), ya que estamos realizando 2 bucles anidados, funciona, pero en arrays mas grandes se demorara muchisimo mas.
2. Forma optimizada:
* Complejidad temporal: O(n), ya que recorremos el array solo 1 vez.
### Validacion de parentesis
Estructuras de datos usadas: 
1. Stack (LIFO): para almacenar los parentesis abiertos mientras se recorre el texto, si encontramos por ejemplo un (, lo agregamos al stack,
luego si encontramos un ), sacamos el ( para comprobar si coincide.
2. Map: Es donde guardamos los parentesis, de cierre a apertura, ya que al encontrar un cierre como se comento anteriormente ), podemos saber cual 
es su apertura (.

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


