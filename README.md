# 🚀 Employee ETL Spring Boot Project

## 📌 Project Overview

This project is a **Spring Boot-based ETL (Extract, Transform, Load) system** that processes employee data from multiple sources and loads clean, validated data into a target database.

It demonstrates **real-world backend concepts** like:

* Multi-database architecture
* Data pipelines (CSV, JSON, DB → DB)
* Validation & transformation
* Logging & exception handling

---

## 🧠 Core Concept

```text
Raw Data (Source DB / CSV / JSON)
        ↓
   Validation & Cleaning
        ↓
   Duplicate Removal
        ↓
 Clean Data → Target DB
```

---

## ⚙️ Tech Stack

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA (Hibernate)**
* **H2 In-Memory Database**
* **Lombok**
* **Jackson (JSON Processing)**
* **SLF4J Logging**
* **Postman (API Testing)**

---

## 🏗️ Project Architecture

```text
Controller → Service → Repository → Database
```

### Layers:

* **Controller** → Handles API requests
* **Service** → Business logic & ETL processing
* **Repository** → Database operations using JPA
* **Database** → H2 (Source & Target)

---

## 🗄️ Multi Database Setup

| Database   | Purpose                   |
| ---------- | ------------------------- |
| `sourcedb` | Raw data (can be invalid) |
| `targetdb` | Clean, validated data     |

---

## 🔄 ETL Pipelines

### 1. 🔁 Database → Database

* Reads from `sourcedb`
* Cleans & validates data
* Inserts into `targetdb`

---

### 2. 📄 CSV → Database

* Reads `employees.csv`
* Validates & transforms
* Inserts valid records into `targetdb`
* Writes invalid records to file

---

### 3. 📦 JSON → Database

* Reads `employees.json`
* Uses Jackson for parsing
* Same validation + transformation pipeline
* Stores valid data in `targetdb`

---

## ✔️ Features

* ✅ Full CRUD operations
* ✅ Multi-source data ingestion (DB, CSV, JSON)
* ✅ Validation (email, salary, name)
* ✅ Duplicate detection (email-based)
* ✅ Invalid record logging to file
* ✅ SLF4J logging (INFO, DEBUG, ERROR)
* ✅ Global exception handling
* ✅ Clean layered architecture

---

## 📡 API Endpoints

### 🔹 Employee CRUD

| Method | Endpoint          | Description       |
| ------ | ----------------- | ----------------- |
| POST   | `/employees`      | Create employee   |
| GET    | `/employees`      | Get all employees |
| PUT    | `/employees/{id}` | Update employee   |
| DELETE | `/employees/{id}` | Delete employee   |

---

### 🔹 ETL Pipelines

| Method | Endpoint                | Description           |
| ------ | ----------------------- | --------------------- |
| POST   | `/pipeline/db-transfer` | Source DB → Target DB |
| POST   | `/pipeline/csv-import`  | CSV → Target DB       |
| POST   | `/pipeline/json-import` | JSON → Target DB      |

---

## 🧪 How to Run

### 1. Clone Repo

```bash
git clone <your-repo-url>
cd employee-etl-project
```

### 2. Run Application

```bash
mvn spring-boot:run
```

---

## 🌐 H2 Database Console

Open in browser:

```
http://localhost:8080/h2-console
```

### 🔹 Source DB

```
JDBC URL: jdbc:h2:mem:sourcedb
```

### 🔹 Target DB

```
JDBC URL: jdbc:h2:mem:targetdb
```

---

## 🧪 Testing with Postman

### 🔹 Trigger DB Transfer

```
POST /pipeline/db-transfer
```

### 🔹 Import CSV

```
POST /pipeline/csv-import
```

### 🔹 Import JSON

```
POST /pipeline/json-import
```

---

## 📄 Sample JSON Format

```json
[
  {
    "name": "Riya",
    "email": "riya@gmail.com",
    "salary": 40000
  }
]
```

---

## ⚠️ Validation Rules

* Name → Cannot be empty
* Email → Must be valid format
* Salary → Must be > 0
* Email → Must be unique (target DB)

---

## 📝 Logging

Logs include:

* API calls
* Validation failures
* Duplicate detection
* ETL processing status

---

## 📁 Output Files

* `invalid-records.txt` → Stores invalid/duplicate records

---

## 🧠 Key Concepts Used

* JPA & Hibernate (ORM)
* Multi-database configuration
* ETL pipeline design
* Dependency Injection
* Repository pattern
* Exception handling
* Logging (SLF4J)
* Data validation
* File processing (CSV + JSON)

---

## 🚀 Future Improvements

* Add MySQL/PostgreSQL instead of H2
* Add Kafka for real-time ETL
* Add UI dashboard
* Add authentication (JWT)
* Deploy using Docker

---


