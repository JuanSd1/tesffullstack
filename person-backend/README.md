# Person Backend

REST API CRUD desarrollada con Java 17 + Spring Boot 3.3.5 + MySQL.

## Requisitos

| Herramienta | Versión mínima |
|-------------|----------------|
| Java        | 17             |
| Maven       | 3.6+           |
| MySQL       | 8.0+           |

## Configuración de base de datos

Ejecutar el script `../bd/script_creacion_bd.sql` en MySQL:

```sql
CREATE DATABASE IF NOT EXISTS bd_quintero CHARACTER SET utf8mb4;
CREATE USER IF NOT EXISTS 'conexion'@'localhost' IDENTIFIED BY 'conexion123';
GRANT ALL PRIVILEGES ON bd_quintero.* TO 'conexion'@'localhost';
FLUSH PRIVILEGES;
```

## Configuración de la aplicación

`src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bd_quintero?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=conexion
spring.datasource.password=conexion123
```

## Ejecución

```bash
./mvnw spring-boot:run
```

La app inicia en `http://localhost:8080`.

> La tabla `person` se crea automáticamente al iniciar (Hibernate `ddl-auto=update`).

## Endpoints

| Método | URL                    | Descripción          |
|--------|------------------------|----------------------|
| GET    | /api/persons           | Listar todas         |
| GET    | /api/persons/{id}      | Obtener por ID       |
| POST   | /api/persons           | Crear persona        |
| PUT    | /api/persons/{id}      | Actualizar persona   |
| DELETE | /api/persons/{id}      | Eliminar persona     |

## Estructura del JSON de respuesta

```json
{
  "status": true,
  "msg": "Mensaje descriptivo",
  "data": [
    {
      "id": 1,
      "nombre": "Juan",
      "apellido": "Pérez",
      "fechaNacimiento": "1990-05-15",
      "puesto": "Desarrollador",
      "sueldo": 25000.00
    }
  ]
}
```

## Body para POST / PUT

```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "fechaNacimiento": "1990-05-15",
  "puesto": "Desarrollador",
  "sueldo": 25000.00
}
```

## Arquitectura

El proyecto aplica **Arquitectura Hexagonal** (Ports & Adapters):

```
domain/
  model/         → Entidad de dominio (Person)
  exception/     → Excepciones de negocio
  port/in/       → Casos de uso (interfaces)
  port/out/      → Puerto de salida (repositorio)

application/
  service/       → Implementación de casos de uso

infrastructure/
  adapter/in/web/          → Controller, DTOs, Mapper, ExceptionHandler
  adapter/out/persistence/ → JPA Entity, Repository, Mapper, Adapter
  config/                  → CORS (WebConfig)
```
