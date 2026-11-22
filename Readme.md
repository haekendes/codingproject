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

Set the database password:

```bash
export DB_PASSWORD="your-secure-password"
```

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