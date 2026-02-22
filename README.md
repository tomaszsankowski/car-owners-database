# 🚗 Car Owners Database

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-19-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

A comprehensive application based on a microservices architecture, used to manage a database of cars and their owners. The project combines a modern Java backend (Spring Boot) with a responsive Angular frontend, all containerized using Docker.

## 🏗️ System Architecture

The project consists of several independent modules (microservices) that communicate with each other:

- **`angular-front`** 🌐 - Client application (SPA) written in Angular 19, served by Nginx.
- **`gateway`** 🚪 - API Gateway (Spring Cloud Gateway) that routes traffic from the frontend to the appropriate backend microservices.
- **`car-management`** 🚙 - Microservice responsible for managing car data. It has its own MySQL database.
- **`person-management`** 👤 - Microservice responsible for managing owner (person) data. It has its own MySQL database.

Each backend microservice has its own isolated database, which follows the best practices of microservice architecture (Database-per-service).

## 🚀 How to run the project?

### Prerequisites
- [Docker](https://www.docker.com/) and Docker Compose
- [Java 17](https://adoptium.net/) (for local backend building)
- [Node.js](https://nodejs.org/) (for local frontend building)

### Running with Docker (Recommended)

1. **Build all projects**
   In the main project directory, run the build script which will compile the Angular and Spring Boot applications:
   ```bash
   ./build.sh
   ```
   *(The script will automatically go through all directories and run their local `build.sh` scripts)*

2. **Run the containers**
   Once the build is complete, bring up the entire infrastructure using Docker Compose:
   ```bash
   docker-compose up -d
   ```

3. **Enjoy the application!** 🎉
   - Frontend (Web Application): [http://localhost:4200](http://localhost:4200)
   - API Gateway: [http://localhost:8080](http://localhost:8080)

### Stopping the application
To stop the running containers, type:
```bash
docker-compose down
```

## 🛠️ Technologies and Tools

- **Backend:** Java 17, Spring Boot 3, Spring Cloud Gateway, Spring Data JPA
- **Frontend:** Angular 19, TypeScript, HTML/CSS
- **Database:** MySQL 8.0
- **DevOps:** Docker, Docker Compose, Nginx, Maven

## 📂 Project Structure

```text
car-owners-database/
├── angular-front/       # Frontend application source code (Angular)
├── car-management/      # Microservice managing cars (Spring Boot)
├── gateway/             # API Gateway (Spring Boot)
├── person-management/   # Microservice managing owners (Spring Boot)
├── docker-compose.yml   # Docker containers configuration
└── build.sh             # Main script building the entire project
```

## 📝 API Testing
In the microservices directories (`car-management`, `person-management`, `gateway`) you will find `requests.http` files. You can use them in your IDE (e.g., IntelliJ IDEA or VS Code with the REST Client extension) to quickly test the available REST API endpoints.
