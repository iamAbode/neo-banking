# Neo-Banking Microservices

This project is a **Neo-Banking** system built using a microservices architecture. It includes multiple services that interact with each other via REST APIs, using **Spring Boot** and **Docker** for containerization.

## 🛠 Technologies Used

- **Spring Boot** (Microservices framework)
- **Spring Cloud Config** (Centralized configuration management)
- **Spring Cloud Eureka** (Service discovery and registry)
- **Spring Data JPA** (Database access layer)
- **H2 Database** (In-memory database for development and testing)
- **Flyway Migration** (Database versioning and migrations)
- **Apache Kafka** (Event-driven communication between microservices)
- **Resilience4j Circuit Breakers** (Fault tolerance and resilience management)
- **Docker & Docker Compose** (Containerization & orchestration)
- **AWS ECS & ECR** (For cloud deployment)
- **Maven** (Dependency management & build tool)

## 📌 Microservices & Port Numbers

**Note:** The Config Server pulls configurations from the following repository: [Neo Config](https://github.com/iamAbode/neo-config.git)

| Service              | Port |
| -------------------- | ---- |
| Customer Service     | 8081 |
| Account Service      | 8082 |
| Transaction Service  | 8083 |
| Notification Service | 8084 |
| Identity Service     | 8085 |
| API Gateway          | 9090 |
| Config Server        | 8888 |
| Service Registry     | 8761 |
| Neo Frontend         | 3000 |## 🚀 Running the Application

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/iamAbode/neo-banking.git
cd neo-banking
```

### 2️⃣ Build the Application
Ensure you have **Maven** installed, then build the project:
```sh
mvn clean install -DskipTests
```

### 3️⃣ Start the Application with Docker Compose
Run the following command to start all services:
```sh
docker-compose up --build -d
```
This will:
- Build the user interface.
- Start all microservices.
- Set up the necessary configurations.

### 4️⃣ Verify the Services
Check if the containers are running:
```sh
docker ps
```
Access the application through:
- **API Gateway:** [http://localhost:9090](http://localhost:9090)
- **Swagger UI:** [http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)
- **Frontend UI:** [http://localhost:3000](http://localhost:3000)

### 5️⃣ Stop the Services
To stop and remove all running containers:
```sh
docker-compose down
```

## ✅ Testing

You can test the APIs using **Postman** or **cURL**. Ensure all services are running before making requests.

### API Endpoints

1️⃣ **Get Token**
- **URL:** `http://localhost:9090/api/identity/generateToken`
- **Method:** POST
- **Sample Request:**
  ```json
  {
      "username": "blue1",
      "password": "blue123"
  }
  ```
- **Note:** There are three pre-loaded users: `blue1`, `blue2`, and `blue3`, all with the same password.

2️⃣ **Create Customer Account**
- **URL:** `http://localhost:9090/api/account/create`
- **Method:** POST
- **Header:** Authorization Bearer token from Get Token
- **Sample Request:**
  ```json
  {
      "customerID": "CUST002",
      "initialCredit": 20
  }
  ```
- **Note:** There are three pre-loaded customers: `CUST001`, `CUST002`, and `CUST003`.

3️⃣ **Customer Information**
- **URL:** `http://localhost:9090/api/customer/customer-information?customerId=CUST001`
- **Method:** GET
- **Header:** Bearer token from Get Token

You can test the APIs using **Postman** or **cURL**. Ensure all services are running before making requests.

## 📜 License

This project is open-source and available under the [MIT License](LICENSE).

---

For any issues or contributions, feel free to create a pull request or raise an issue. Happy coding! 🚀

