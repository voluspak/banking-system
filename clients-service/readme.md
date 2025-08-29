# clients-service — README

**Módulo:** `clients-service`
**Proyecto padre:** Banking System (microservicios)

> *README preparado para mostrar de forma concisa y técnica lo que hace este microservicio.*

---

## Descripción breve

`clients-service` es el microservicio encargado de la **gestión de clientes** dentro del proyecto Banking System. Implementa una API REST mínima para crear, leer y borrar clientes, persistiendo los datos en PostgreSQL. Está diseñado para ser fácil de arrancar localmente y listo para integrarse con el resto de microservicios del sistema.

---

## Tech stack

Java • Spring Boot (Web, Data JPA, Validation, Actuator) • Maven • PostgreSQL • Docker (opcional)

---

## Qué hace (resumen funcional)

Este servicio expone endpoints REST bajo el prefijo `/api/clients` para:

* Listar clientes
* Obtener un cliente por id
* Crear un cliente
* Eliminar un cliente por id

Los datos básicos manejados por el servicio (modelo simplificado):

```json
{
  "id": "Long",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "createdAt": "LocalTime"
}
```

---

## Endpoints implementados

Base path: `/api/clients` (ajustable según `application.yml`)

* `GET /api/clients` — devolver lista de clientes
* `GET /api/clients/{id}` — obtener cliente por id
* `POST /api/clients` — crear cliente (JSON)
* `DELETE /api/clients/{id}` — eliminar cliente por id

**Ejemplo: crear cliente**

```bash
curl -X POST http://localhost:8081/api/clients \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan.perez@example.com",
    "documentNumber": "12345678A"
  }'
```

**Ejemplo: listar clientes**

```bash
curl http://localhost:8081/api/clients
```

---

## Configuración y ejecución (local)

**Requisitos:** JDK 17+, Maven, PostgreSQL (o usar Docker)

**Construir y ejecutar**

```bash
# desde la raíz del proyecto (multi-módulo) o dentro de clients-service
mvn clean install
cd clients-service
mvn spring-boot:run
```

**Variables de entorno / properties principales**

* `SPRING_DATASOURCE_URL` (ej. `jdbc:postgresql://localhost:5432/banking_dev`)
* `SPRING_DATASOURCE_USERNAME` (ej. `postgres`)
* `SPRING_DATASOURCE_PASSWORD` (ej. `postgres`)
* `SERVER_PORT` (por defecto 8081 en este módulo)
* `SPRING_PROFILES_ACTIVE` (ej. `dev`)

---

## Docker / Docker-Compose (desarrollo rápido)

**Dockerfile (sugerido)**

```dockerfile
FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

**docker-compose (clients-service + Postgres)**

```yaml
version: '3.8'
services:
  clients-service:
    build: ./clients-service
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/banking_dev
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=postgres
      - SERVER_PORT=8081
    ports:
      - 8081:8081
    depends_on:
      - postgres

  postgres:
    image: postgres:15
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: banking_dev
    volumes:
      - pgdata:/var/lib/postgresql/data

volumes:
  pgdata:
```

---

## Tests

Ejecutar pruebas unitarias/integración integradas al módulo con:

```bash
cd clients-service
mvn test
```

Si se desea, el proyecto admite integrar Testcontainers para pruebas de repositorio con Postgres.

---

## Qué destacar

* Diseño e implementación de APIs REST con Spring Boot
* Persistencia con JPA y PostgreSQL
* Configuración por perfiles y uso de variables de entorno
* Empaquetado y ejecución con Maven y Docker
* Pruebas automatizadas a nivel de módulo
