# RESTful API for Logistics Network Optimization with Graphs

This project is a backend microservice developed with Spring Boot that implements graph theory algorithms for logistics network optimization. Its primary goal is to demonstrate the practical application of complex data structures and algorithms in a modern backend development environment, using a RESTful API and PostgreSQL persistence.

## 🚀 Technologies Used

  * **Programming Language:** Java 17+
  * **Framework:** Spring Boot 3.5.3
  * **Database:** PostgreSQL (Neon recommended for cloud setup)
  * **ORM:** Spring Data JPA / Hibernate
  * **DB Migration Management:** Flyway
  * **Validation:** Spring Boot Starter Validation (Hibernate Validator)
  * **API Documentation:** Springdoc OpenAPI (Swagger UI)
  * **Build Tool:** Apache Maven
  * **Task Automation:** Makefile
  * **Testing:** JUnit 5, Mockito

## ✨ Key Features

  * **Node and Edge Management:** Full CRUD operations to model a logistics network.
  * **Optimal Path Calculation:** Implementation of shortest path algorithms (Dijkstra and Bellman-Ford) to find the most efficient routes based on distance, time, or cost.
  * **Connectivity Analysis:** Use of BFS and DFS to explore the network and verify accessibility between points.
  * **Task Sequencing:** Application of Topological Sort to plan processes with dependencies.
  * **Network Resilience Analysis:** Identification of Articulation Points and Strongly Connected Components (SCCs) to detect bottlenecks and single points of failure.
  * **Cost-Efficient Network Design:** Calculation of Minimum Spanning Trees (MST) to optimize infrastructure.
  * **Data Validation:** Implementation of robust validations for API inputs.
  * **Database Version Control:** Automatic database schema management with Flyway.
  * **Interactive Documentation:** API documentation accessible via Swagger UI.

## ⚙️ Prerequisites

Before you begin, ensure you have the following installed:

  * **Java Development Kit (JDK) 17 or higher**
  * **Apache Maven 3.x or higher**
  * **A PostgreSQL instance**.
  * **`make`** (usually pre-installed on Unix/Linux/macOS systems; for Windows).

---

## 🛠️ Project Setup

### 1. Clone the Repositorybash

``` bash
git clone [https://github.com/ValentinoCarmonaS/Logistic_Network.git](https://github.com/ValentinoCarmonaS/Logistic_Network.git)
cd Logistic_Network
```

---

### 2. Configuration in `application.properties`

The project uses `src/main/resources/application.properties` for configuration, including database connection details and other constants. Update this file with your specific settings:

```properties
# PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://<your_db_host>:<your_db_port>/<your_db_name>
spring.datasource.username=<your_db_username>
spring.datasource.password=<your_db_password>

# JPA/Hibernate Properties
spring.jpa.hibernate.ddl-auto=validate # Use 'validate' with Flyway for schema management
spring.jpa.show-sql=true # Set to false for production

# Flyway Configuration (optional, Flyway is enabled by default if flyway-core is on classpath)
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true # Useful for existing databases

# Other constants (example, you can add more as needed)
server.servlet.context-path=/api
```
---

### 3\. Database Migrations with Flyway

Flyway will automatically manage the database schema upon application startup. SQL migration scripts are located in `src/main/resources/db/migration/`. Ensure your database is accessible with the credentials provided in the environment variables.

## 🚀 Running the API

You can build and run the application using Maven directly or via the `Makefile` for convenience.

### Using Maven

1.  **Compile the Project:**
    ```bash
    mvn clean install
    ```
2.  **Run the Application:**
    ```bash
    mvn spring-boot:run
    ```

### Using Makefile

The `Makefile` provides quick commands for common operations:

  * **`make build`**: Cleans and compiles the project, generating the JAR file.
    ```bash
    make build
    ```
  * **`make run`**: Executes the Spring Boot application.
    ```bash
    make run
    ```
  * **`make test`**: Runs all unit and integration tests.
    ```bash
    make test
    ```
  * **`make clean`**: Cleans up build artifacts.
    ```bash
    make clean
    ```

The API will start by default on `http://localhost:8080`.

## 📝 API Endpoints

The API exposes the following endpoints to interact with the logistics network.

| URL | HTTP Method | Description |
| :--- | :--- | :--- |
| `/api/nodos` | `POST` | Creates a new node in the network. |
| `/api/nodos/{id}` | `GET` | Retrieves details of a specific node. |
| `/api/nodos/{id}` | `PUT` | Updates details of an existing node. |
| `/api/nodos/{id}` | `DELETE` | Deletes a node and all associated edges. |
| `/api/aristas` | `POST` | Creates a new edge between two nodes. |
| `/api/aristas/{id}` | `GET` | Retrieves details of a specific edge. |
| `/api/aristas/{id}` | `PUT` | Updates details of an existing edge. |
| `/api/aristas/{id}` | `DELETE` | Deletes an edge. |
| `/api/rutas/optima` | `GET` | Calculates the most optimal route between an origin and a destination, based on a criterion (distance, time, cost). Parameters: `origen`, `destino`, `criterio`. |
| `/api/grafos/alcanzables/bfs` | `GET` | Performs a BFS traversal from an origin node and returns reachable nodes. Parameter: `origen`. |
| `/api/grafos/alcanzables/dfs` | `GET` | Performs a DFS traversal from an origin node and returns reachable nodes. Parameter: `origen`. |
| `/api/grafos/orden_topologico` | `GET` | Calculates a topological sort of the graph (if it's a DAG). |
| `/api/grafos/puntos_articulacion` | `GET` | Identifies articulation points in the graph. |
| `/api/grafos/sccs` | `GET` | Identifies strongly connected components in the directed graph. |
| `/api/grafos/mst` | `GET` | Calculates the Minimum Spanning Tree of the graph. |

## 💡 Usage Examples

Below are examples of how to interact with the API using `curl`. Ensure the API is running.

### 1\. Create a Node

```bash
curl -X POST "http://localhost:8080/api/nodos" \
-H "Content-Type: application/json" \
-d '{
  "nombre": "Central Warehouse",
  "latitud": 40.7128,
  "longitud": -74.0060,
  "tipo": "Warehouse"
}'
```

### 2\. Get a Node by ID

Assuming the ID of the created node is `a1b2c3d4-e5f6-7890-1234-567890abcdef`:

```bash
curl -X GET "http://localhost:8080/api/nodos/a1b2c3d4-e5f6-7890-1234-567890abcdef"
```

### 3\. Create an Edge

Assuming you have two nodes with IDs `origin_id` and `destination_id`:

```bash
curl -X POST "http://localhost:8080/api/aristas" \
-H "Content-Type: application/json" \
-d '{
  "origenId": "origin_id",
  "destinoId": "destination_id",
  "distanciaKm": 15.5,
  "tiempoMin": 20.0,
  "costoEur": 5.25,
  "dirigida": true,
  "descripcion": "Main Route"
}'
```

### 4\. Calculate Optimal Route (Dijkstra/Bellman-Ford)

Assuming you have node IDs `origin_id` and `destination_id`:

```bash
curl -X GET "http://localhost:8080/api/rutas/optima?origen=origin_id&destino=destination_id&criterio=tiempoMin"
```

Valid criteria are: `distanciaKm`, `tiempoMin`, `costoEur`.

### 5\. Connectivity Exploration (BFS)

```bash
curl -X GET "http://localhost:8080/api/grafos/alcanzables/bfs?origen=origin_id"
```

### 6\. Get Articulation Points

```bash
curl -X GET "http://localhost:8080/api/grafos/puntos_articulacion"
```

## 🧪 Testing

To run the project's unit and integration tests, use the following command:

```bash
make test
```

Or directly with Maven:

```bash
mvn test
```

## 📄 API Documentation (Swagger UI)

Once the application is running, you can access the interactive API documentation via Swagger UI in your browser:

[http://localhost:8080/swagger-ui.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui.html)

Here you can see all available endpoints, their parameters, data models, and test requests directly from the interface.
