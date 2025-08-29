# Banking System (_Actualmente en desarollo / Work in progress_) — README

👇🏻 _Click para expandir / Click to expand_ 👇🏻
<details>
  <summary><strong>Español</strong></summary>

1. [Resumen del proyecto](#1-resumen-del-proyecto)  
2. [Objetivo](#2-objetivo)  
3. [Tecnologías y herramientas](#3-Tecnologías-y-herramientas-explicación-breve)  
4. [Estructura](#4-Estructura-qué-hay-en-el-repo)  
5. [Cómo arrancarlo](#5-Cómo-arrancarlo-estilo-quick-start)  
6. [Endpoints](#6-Endpoints-Desarrollados-hasta-ahora)  
7. [Sugerencias para local](#7-Sugerencias-para-local)  

  
  ## 1. Resumen del proyecto
  Proyecto backend organizado como **microservicios** orientado al sector financiero (API REST). La raíz del repositorio contiene un `pom.xml` y subproyectos dedicados a responsabilidades separadas: clientes, cuentas, transacciones y notificaciones —cada uno como servicio independiente—. Esto facilita despliegue, escalado y mantenimiento por dominios.
  
  ---
  
  ## 2. Objetivo
  Proveer APIs REST independientes para operaciones bancarias (gestión de clientes, cuentas, movimientos/transferencias y notificaciones), siguiendo el enfoque de microservicios: servicios pequeños, autónomos y comunicantes por HTTP/REST
  
  ---
  
  ## 3. Tecnologías y herramientas (explicación breve)
  - **Java + Spring Boot**: framework para crear servicios REST, con convenciones que aceleran el desarrollo de microservicios.  
  - **Maven**: gestión de dependencias y build; existe un `pom.xml` en la raíz que sugiere organización multi-módulo o control centralizado de dependencias.  
  - **Arquitectura de microservicios**: cada carpeta `*-service` representa un servicio con su propio ciclo de vida y despliegue. Beneficios: escalabilidad horizontal, despliegues independientes y separación de responsabilidades.
  
  > **Nota:** El README mezcla lo visible en la estructura del repo con prácticas estándar para proyectos Spring Boot + microservicios. Si quieres un listado exacto de dependencias o rutas, puedo extraerlos desde los ficheros `pom.xml` y controladores de cada servicio.
  
  ---
  
  ## 4. Estructura (qué hay en el repo)
  
  - `clients-service` — servicio para clientes.  
  - `accounts-service` — servicio para cuentas.  *Por desarrollar*
  - `transactions-service` — servicio para movimientos/transacciones. *Por desarrollar*  
  - `notifications-service` — servicio para enviar notificaciones (email, SMS, push).  *Por desarrollar*
  - `pom.xml` en la raíz (control de build/dependencias).
  
  Cada `*-service` contiene:
  - `src/main/java` (controladores, servicios, repositorios, entidades).  
  - `src/main/resources/application.yml` con configuración por perfil.  
  - `pom.xml` propio con herencias del `pom` raíz.
  
  ---
  
  ## 5. Cómo arrancarlo (estilo “quick start”)
  
  > Supuesto: cada servicio es una aplicación Spring Boot estándar.
  
  5.1 Desde la raíz (si es multi-módulo):
  ```bash
  # construir todo
  mvn clean install
  
  # ejecutar un servicio (ejemplo: clients-service)
  cd clients-service
  mvn spring-boot:run
  ```
  
  5.2 Ejecutar cada servicio en su propio terminal:
  ```bash
  cd clients-service && mvn spring-boot:run
  cd ../accounts-service && mvn spring-boot:run
  # repetir para los demás servicios
  ```
  
  5.3 Variables de entorno típicas (ejemplos):
  - `SPRING_PROFILES_ACTIVE=dev`  
  - `SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/<db>`  
  - `SERVER_PORT=8081` (cada servicio en un puerto distinto)
  
  5.4 Recomendación rápida: usar **Docker Compose** para orquestar servicios + DB en local (ver sección “Sugerencias” abajo).
  
  ---
  
  ## 6. Endpoints (Desarrollados hasta ahora)
  
  ### ClientsServer👇🏻
  - `GET /api/clients` — listar clientes  
  - `GET /api/clients/{id}` — obtener cliente por id
  - `POST /api/clients` — crear cliente  
  - `DELETE /api/clients/{id}` - Eliminar cliente por id
  ---
  
  ## 7. Sugerencias para local
  - Crear un `docker-compose.yml` que levante:
    - una base de datos (Postgres),  
    - cada servicio (build via Dockerfile o desde el jar),  
    - (opcional) broker (Kafka / RabbitMQ).  
  - Pipelines CI: build multi-módulo, ejecutar tests, empaquetar imágenes y push a un registry.  
  - Para producción: usar orquestadores (Kubernetes) y un Ingress / API Gateway.
</details>
<details>
  <summary><strong>English</strong></summary>

1. [Project summary](#1-project-summary)  
2. [Objective](#2-objective)  
3. [Technologies and tools](#3-technologies-and-tools-brief-explanation)  
4. [Structure](#4-Structure-repository-contents)  
5. [Quick start](#5-how-to-run-quick-start)  
6. [Example endpoints](#6-endpoints-developed-so-far)
7. [Suggestions for Local / CI / Deployment](#7-Suggestions-for-local-deployment)  


  
## 1. Project Summary
Backend project organized as **microservices** targeted at the financial sector (REST API). The repository root contains a `pom.xml` and subprojects dedicated to separate responsibilities: clients, accounts, transactions, and notifications —each as an independent service. This design enables domain-based deployment, scaling, and maintenance.

---

## 2. Objective
To provide independent REST APIs for banking operations (customer management, accounts, transactions/transfers, and notifications), following the microservices approach: small, autonomous services communicating via HTTP/REST.

---

## 3. Technologies and Tools (brief explanation)
- **Java + Spring Boot**: framework for building REST services, offering conventions that accelerate microservices development.  
- **Maven**: dependency and build management; a root-level `pom.xml` suggests either a multi-module setup or centralized dependency control.  
- **Microservices architecture**: each `*-service` directory represents a service with its own lifecycle and deployment. Benefits: horizontal scalability, independent deployments, and clear separation of concerns.

> **Note:** This README combines information visible in the repository structure with standard practices for Spring Boot + microservices projects. For an exact list of dependencies or routes, these can be extracted from each service’s `pom.xml` and controller files.

---

## 4. Structure (repository contents)

- `clients-service` — clients service.  
- `accounts-service` — accounts service. *To be developed*  
- `transactions-service` — transactions service. *To be developed*  
- `notifications-service` — notifications service (email, SMS, push). *To be developed*  
- Root `pom.xml` (build/dependency management).

Each `*-service` contains:  
- `src/main/java` (controllers, services, repositories, entities).  
- `src/main/resources/application.yml` with profile-specific configuration.  
- Own `pom.xml`, inheriting from the root `pom`.

---

## 5. How to Run (Quick Start)

> Assumption: each service is a standard Spring Boot application.

**5.1 From the root (multi-module case):**
```bash
# build everything
mvn clean install

# run a service (example: clients-service)
cd clients-service
mvn spring-boot:run
```

**5.2 Run each service in its own terminal:**
```bash
cd clients-service && mvn spring-boot:run
cd ../accounts-service && mvn spring-boot:run
# repeat for the remaining services
```

**5.3 Typical environment variables (examples):**
- `SPRING_PROFILES_ACTIVE=dev`  
- `SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/<db>`  
- `SERVER_PORT=8081` (each service runs on a different port)

**5.4 Quick recommendation:** use **Docker Compose** to orchestrate services + database locally (see “Suggestions” section below).

---

## 6. Endpoints (developed so far)

### ClientsService 👇🏻
- `GET /api/clients` — list clients  
- `GET /api/clients/{id}` — get client by id  
- `POST /api/clients` — create client  
- `DELETE /api/clients/{id}` — delete client by id  

---

## 7. Suggestions for local deployment
- Create a `docker-compose.yml` that includes:
  - a database (Postgres),  
  - each service (built via Dockerfile or from the JAR),  
  - (optional) message broker (Kafka / RabbitMQ).  
- CI pipelines: multi-module build, run tests, package images, and push to a registry.  
- For production: use orchestrators (Kubernetes) and an Ingress / API Gateway.  
