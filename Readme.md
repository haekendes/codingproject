# Picard Coding Project

A Java-based application developed with **Java 25** and **PostgreSQL**.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 25 | Programming language |
| PostgreSQL 18 | Relational database |
| Maven | Dependency management and build |
| Docker | Database containerization |
| Docker Compose | Development environment |
| IntelliJ IDEA | Development environment |

## Prerequisites

Make sure the following tools are installed:

- JDK 25
- IntelliJ IDEA
- Maven
- Docker Engine
- Docker Compose

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd picardcodingproject
```

### 2. Configure environment variables

The database password is configured through an environment variable (`DB_PASSWORD`) and is not stored directly in `application.yml`.

Before starting the Spring Boot application, set the `DB_PASSWORD` environment variable to your PostgreSQL password.

**Linux / macOS:**

```bash
export DB_PASSWORD=your_database_password

```bash
export DB_PASSWORD="your-secure-password"
```

or set it in your run configuration in IntelliJ. 

### 3. Start PostgreSQL

Start the database using Docker Compose:

```bash
docker compose up -d
```

Check the container status:

```bash
docker compose ps
```

View PostgreSQL logs:

```bash
docker compose logs -f postgres
```

### 4. Database Configuration

| Setting | Value |
|---|---|
| Database | picarddb |
| Username | picard |
| Host | localhost |
| Port | 5432 |
| Database Engine | PostgreSQL 18 |

### 5. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively, build the application:

```bash
mvn clean package
```

Run the generated JAR:

```bash
java -jar target/picardcodingproject-0.0.1-SNAPSHOT.jar
```

## Webhook Configuration for Local Testing

[Webhook.site](https://webhook.site/) is used to test the automatic offer transmission.

Webhook.site provides a unique URL that allows incoming HTTP requests to be inspected. This makes it easy to verify whether the application successfully sends offers to an external system.

### Setup Instructions

1. Visit [https://webhook.site](https://webhook.site/).
2. Use the automatically generated webhook URL.
3. Copy your personal webhook URL.
4. Update the configuration in `application.yml`:

   ```yaml
   app:
     web-frontend:
       offer-url: https://webhook.site/YOUR-WEBHOOK-ID


## Using the cURL Files

The project includes cURL files that can be used to manually test the REST API endpoints.

### Setup Instructions

1. Open the `curl` directory in the project.
2. Select the cURL file for the endpoint you want to test.
3. Before executing an offer-related request, copy the corresponding offer ID from the application response, database or webhook.site.
4. Replace the placeholder `OFFER-ID` in the cURL file with the actual offer UUID:

   ```bash
   curl -X POST http://localhost:8080/offers/OFFER-ID/accept