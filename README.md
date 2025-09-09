# Test Ética - API de Gestión de Empleados

API REST desarrollada con Spring Boot para la gestión de empleados de una empresa.

## Descripción del Proyecto

Este proyecto implementa un microservicio REST que permite gestionar información básica de empleados, incluyendo operaciones CRUD y búsquedas específicas. Los datos se almacenan en una base de datos MySQL y se exponen a través de endpoints REST documentados con Swagger/OpenAPI.

### Funcionalidades Principales

- ✅ Listar todos los empleados
- ✅ Obtener empleado por ID
- ✅ Buscar empleados por puesto de trabajo
- ✅ Crear nuevos empleados
- ✅ Validación de datos de entrada
- ✅ Manejo centralizado de excepciones
- ✅ Documentación automática con Swagger

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.0.5**
- **Spring Data JPA**
- **Spring Boot Validation**
- **MySQL 8.0**
- **Lombok**
- **Swagger/OpenAPI 3**
- **Docker & Docker Compose**
- **Maven**

## Prerequisitos

- Java 17 o superior
- Maven 3.6+
- Docker y Docker Compose

## Instalación y Ejecución

### 1. Clonar el repositorio
```bash
git clone <url-del-repositorio>
cd test-etica
```

### 2. Levantar la base de datos
```bash
docker-compose up -d
```

### 3. Compilar y ejecutar la aplicación
```bash
# Compilar el proyecto
./mvnw clean compile

# Ejecutar la aplicación
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

### 4. Acceder a la documentación
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## Endpoints Disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/api/employees` | Obtiene la lista completa de empleados |
| GET    | `/api/employees/{id}` | Obtiene un empleado específico por ID |
| GET    | `/api/employees/search?puesto={puesto}` | Busca empleados por puesto de trabajo |
| POST   | `/api/employees` | Crea un nuevo empleado |

## Modelo de Datos

### Employee
```json
{
  "id": 1,
  "nombre": "Juan Arrua",
  "puesto": "Desarrollador", 
  "salario": 75000
}
```

### Validaciones
- **nombre**: Obligatorio, máximo 100 caracteres
- **puesto**: Obligatorio, máximo 50 caracteres  
- **salario**: Obligatorio, debe ser mayor a 0

## Configuración de Base de Datos

La aplicación utiliza MySQL con la siguiente configuración por defecto:

```properties
# Configuración incluida en docker-compose.yml
Host: localhost
Port: 3308
Database: employeedb
Username: admin
Password: password
```

## Arquitectura del Proyecto

```
src/main/java/com/example/etica/
├── config/           # Configuración (OpenAPI)
├── controller/       # Controladores REST
├── dto/             # Data Transfer Objects
├── exception/       # Manejo de excepciones
├── mapper/          # Mappers DTO ↔ Entity
├── model/           # Entidades JPA
├── repository/      # Repositorios JPA
└── service/         # Lógica de negocio
```
