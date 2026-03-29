# 🎬 BookMyShow — Spring Boot Backend

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Backend-success)
![MySQL](https://img.shields.io/badge/MySQL-Database-orange)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-yellow)
![License](https://img.shields.io/badge/License-MIT-green)

---

🚀 **A full-featured Movie Ticket Booking System REST API built using Spring Boot, JPA/Hibernate, and MySQL — inspired by BookMyShow India.**

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Database Schema](#database-schema)
- [API Reference](#api-reference)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Running the App](#running-the-app)
- [Sample Data](#sample-data)
- [Frontend](#frontend)
- [Screenshots](#screenshots)

---

## ✨ Features <a name="features"></a>

🏙️ City Management — Manage cities across India  

🎬 Movie Catalog — Full movie listing with genre, language, rating, poster  

🏛️ Theater Management — Multiple theaters per city  

🎥 Screen Management — Multiple screens per theater (4DX, IMAX, Dolby Atmos) 

💺 Seat Management — REGULAR / PREMIUM / VIP seat types  

🎟️ Show Scheduling — Multiple shows per day per screen  

👤 User Registration & Login

📱 Booking System — Book multiple seats in one booking  

❌ Booking Cancellation  

✅ Available Seats API — Real-time seat availability per show  

---



## 🛠️ Tech Stack <a name="tech-stack"></a>

| 🛠️ Technology       | 📦 Version | 🎯 Purpose                  |
|--------------------|-----------|----------------------------|
| Java               | 24        | Programming Language       |
| Spring Boot        | 4.0.3     | Web Framework              |
| Spring Data JPA    | 4.0.3     | ORM / Database Layer       |
| Hibernate          | 7.2.4     | JPA Implementation         |
| MySQL              | 8.0       | Database                   |
| Lombok             | 1.18.42   | Boilerplate Reduction      |
| Maven              | 3.x       | Build Tool                 |

---



##  📁 Project Structure  <a name="project-structure"></a>


```text
BMS/
├── src/
│   └── main/
│       ├── java/com/jaivy/BMS/
│       │   ├── BmsApplication.java          # Main entry point
│       │
│       │   ├── Config/
│       │   │   └── CorsConfig.java          # CORS configuration
│       │
│       │   ├── Controller/
│       │   │   ├── BookingController.java
│       │   │   ├── CityController.java
│       │   │   ├── MovieController.java
│       │   │   ├── ScreenController.java
│       │   │   ├── SeatController.java
│       │   │   ├── ShowController.java
│       │   │   ├── TheaterController.java
│       │   │   └── UserController.java
│       │
│       │   ├── Dto/
│       │   │   ├── BookingDto/
│       │   │   │   ├── BookingRequestDto.java
│       │   │   │   └── BookingResponseDTO.java
│       │   │   ├── LogInDto/
│       │   │   │   └── LoginRequestDto.java
│       │   │   ├── ScreenDto/
│       │   │   │   └── ScreenResponseDTO.java
│       │   │   ├── SeatDto/
│       │   │   │   └── SeatResponseDTO.java
│       │   │   ├── ShowDto/
│       │   │   │   └── ShowResponseDTO.java
│       │   │   ├── TheaterDto/
│       │   │   │   └── TheaterResponseDTO.java
│       │   │   └── UserDto/
│       │   │       └── UserRequestDto.java
│       │
│       │   ├── Entity/
│       │   │   ├── Booking.java
│       │   │   ├── City.java
│       │   │   ├── Movie.java
│       │   │   ├── Screen.java
│       │   │   ├── Seat.java
│       │   │   ├── Show.java
│       │   │   ├── Theater.java
│       │   │   └── User.java
│       │
│       │   ├── Enum/
│       │   │   └── SeatType.java            # REGULAR, PREMIUM, VIP
│       │
│       │   ├── Repository/
│       │   │   ├── BookingRepository.java
│       │   │   ├── CityRepository.java
│       │   │   ├── MovieRepository.java
│       │   │   ├── ScreenRepository.java
│       │   │   ├── SeatRepository.java
│       │   │   ├── ShowRepository.java
│       │   │   ├── TheaterRepository.java
│       │   │   └── UserRepository.java
│       │
│       │   └── Service/
│       │       ├── BookingService/
│       │       ├── CityService/
│       │       ├── MoviesService/
│       │       ├── ScreenService/
│       │       ├── SeatService/
│       │       ├── ShowService/
│       │       ├── TheatorService/
│       │       └── UserService/
│       │
│       └── resources/
│           └── application.properties
│
└── pom.xml

```
---

## 🗄️  Database Schema <a name="database-schema"></a>


```text
City (id, name, state)
  └──< Theater (id, name, address, city_id)
        └──< Screen (id, name, total_seat, theater_id)
              ├──< Seat (id, seat_number, seat_row, seat_col, seat_type, screen_id)
              └──< Show (id, movie_id, screen_id, show_date, start_time, end_time, ticket_price)
                    └──< Booking (id, user_id, show_id, total_price, booking_status, booked_at)
                          └──< Booking_Seats (booking_id, seat_id)

Movie (id, title, language, genre, duration_in_minutes, rating, poster_url, release_date, description)

User (id, name, email, password, phone_number, created_at)
```

---

## Entity Relationships

| Relationship        | Type         |
|---------------------|-------------|
| City → Theaters     | One-to-Many |
| Theater → Screens   | One-to-Many |
| Screen → Seats      | One-to-Many |
| Screen → Shows      | One-to-Many |
| Movie → Shows       | One-to-Many |
| User → Bookings     | One-to-Many |
| Show → Bookings     | One-to-Many |
| Booking ↔ Seats     | Many-to-Many |

---


## API Reference

**Base URL:** `http://localhost:8080/api`

---

## 🏙️ City API

| Method | Endpoint        | Description        |
|--------|----------------|--------------------|
| GET    | /cities        | Get all cities     |
| GET    | /cities/{id}   | Get city by ID     |

---

## 🎬 Movie API

| Method | Endpoint                      | Description           |
|--------|------------------------------|-----------------------|
| GET    | /movies                      | Get all movies        |
| GET    | /movies/{id}                 | Get movie by ID       |
| POST   | /movies                      | Add new movie         |
| PUT    | /movies/{id}                 | Update movie          |
| DELETE | /movies/{id}                 | Delete movie          |
| GET    | /movies/search?title=        | Search by title       |
| GET    | /movies/genre/{genre}        | Filter by genre       |

### Request Body (POST /movies)

```json
{
  "title": "Pushpa 2: The Rule",
  "description": "Pushpa Raj expands his empire",
  "durationInMinutes": 152,
  "genre": "Action",
  "language": "Telugu",
  "posterUrl": "https://example.com/poster.jpg",
  "rating": 8.2,
  "releaseDate": "2024-12-05"
}
```

---

## 🏛️ Theater API

| Method | Endpoint                    | Description               |
|--------|-----------------------------|---------------------------|
| GET    | /theaters/getAllTheater     | Get all theaters          |
| GET    | /theaters/{id}              | Get theater by ID         |
| POST   | /theaters                   | Add new theater           |
| GET    | /theaters/city/{cityId}     | Get theaters by city      |

### Request Body (POST /theaters)

```json
{
  "name": "PVR ICON, Versova",
  "address": "Versova, Andheri West, Mumbai 400061",
  "cityId": 1
}
```

---

## 🎥 Screen API

| Method | Endpoint                         | Description                 |
|--------|----------------------------------|-----------------------------|
| GET    | /screens                         | Get all screens             |
| GET    | /screens/{id}                    | Get screen by ID            |
| POST   | /screens                         | Add new screen              |
| GET    | /screens/theater/{theaterId}     | Get screens by theater      |

### Request Body (POST /screens)

```json
{
  "name": "Screen 1 - IMAX",
  "totalSeats": 250,
  "theaterId": 1
}
```

---

## 💺 Seat API

| Method | Endpoint                  | Description                |
|--------|---------------------------|----------------------------|
| GET    | /seats/screen/{screenId}  | Get seats by screen        |
| GET    | /seats/{id}               | Get seat by ID             |
| POST   | /seats                    | Add new seat               |

### Request Body (POST /seats)

```json
{
  "seatNumber": "A1",
  "row": "A",
  "col": 1,
  "seatType": "REGULAR",
  "screenId": 1
}
```

### Seat Types

| Type    | Description                         |
|---------|-------------------------------------|
| REGULAR | Standard seats (Rows A–C)           |
| PREMIUM | Premium seats (Rows D–E)            |
| VIP     | VIP seats (Row F)                   |

---

## 🎟️ Show API

| Method | Endpoint                                      | Description                    |
|--------|-----------------------------------------------|--------------------------------|
| GET    | /shows                                        | Get all shows                  |
| GET    | /shows/{id}                                   | Get show by ID                 |
| POST   | /shows                                        | Add new show                   |
| GET    | /shows/movie/{movieId}                        | Get shows by movie             |
| GET    | /shows/movie/{movieId}/date?date=             | Get shows by movie and date    |

### Request Body (POST /shows)

```json
{
  "movieId": 5,
  "screenId": 1,
  "showDate": "2026-03-20",
  "startTime": "18:00",
  "endTime": "20:32",
  "ticketPrice": 550
}
```

---

## 👤 User API

| Method | Endpoint             | Description          |
|--------|----------------------|----------------------|
| POST   | /users/register      | Register new user    |
| POST   | /users/login         | Login user           |
| GET    | /users/{id}          | Get user by ID       |
| GET    | /users/getalluser    | Get all users        |

### Request Body (Register)

```json
{
  "name": "Rahul Sharma",
  "email": "rahul@gmail.com",
  "password": "pass123",
  "phoneNumber": "9876543210"
}
```

### Request Body (Login)

```json
{
  "email": "rahul@gmail.com",
  "password": "pass123"
}
```

---

## 🎟️ Booking API

| Method | Endpoint                                      | Description                          |
|--------|-----------------------------------------------|--------------------------------------|
| POST   | /bookings                                     | Create new booking                   |
| GET    | /bookings/{id}                                | Get booking by ID                    |
| GET    | /bookings/user/{userId}                       | Get bookings by user                 |
| PUT    | /bookings/{id}/cancel                         | Cancel booking                       |
| GET    | /bookings/show/{showId}/available-seats       | Get available seats for show         |

### Request Body (POST /bookings)

```json
{
  "userId": 1,
  "showId": 5,
  "seatIds": [1, 2, 3]
}
```

### Response

```json
{
  "id": 1,
  "userId": 1,
  "showId": 5,
  "seats": [
    { "id": 1, "seatNumber": "A1", "seatType": "REGULAR" }
  ],
  "totalPrice": 1650,
  "bookingStatus": "CONFIRMED",
  "bookedAt": "2026-03-18T10:30:00"
}
```

---


## 🚀 Getting Started <a name="getting-started"></a>

### Prerequisites

Make sure you have the following installed:

- Java 17+ (Project uses Java 24)
- Maven 3.x
- MySQL 8.0+
- Git



### Installation

#### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/BookMyShow-backend.git
cd bookmyshow-backend
```



#### 2. Create MySQL Database

```sql
CREATE DATABASE BMS;
```



#### 3. Configure `application.properties`

```properties
# src/main/resources/application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/BMS?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```


#### 4. Build the Project

```bash
mvn clean install
```

#### 5. Run the Application

```bash
mvn spring-boot:run
```


### Server

✅ Application will start at:

```
http://localhost:8080/api
```

---

## ⚙️ Configuration <a name="configuration"></a>


Path: `src/main/resources/application.properties`



### Application Settings

```properties
# Application Name
spring.application.name=BMS
```


### Database Configuration

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/BMS?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```


### JPA / Hibernate

```properties
# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

### Server Configuration

```properties
# Server Settings
server.port=8080
server.servlet.context-path=/api
```

---


## ▶️ Running the App <a name="running-the-app"></a>


---

###  Using Maven

```bash
mvn spring-boot:run
```


## Using JAR File

#### Build the JAR

```bash
mvn clean package
```

#### Run the JAR

```bash
java -jar target/BMS-0.0.1-SNAPSHOT.jar
```


## Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA  
2. Navigate to `BmsApplication.java`  
3. Click **Run ▶️**


## Verify Application is Running

```bash
curl http://localhost:8080/api/movies
```
---

## 🌱 Sample Data <a name="sample-data"></a>


Import the SQL file to load real Indian cinema data:

```bash
mysql -u root -p BMS < bms_india_final.sql
```



### 📊 Dataset Includes

- 8 Cities — Mumbai, Delhi, Bangalore, Hyderabad, Chennai, Kolkata, Pune, Jaipur  
- 14 Movies — Pathaan, RRR, Pushpa 2, Jawan, Animal, Leo, and more  
- 17 Theaters — PVR, INOX, Cinepolis, AMB, SPI across India  
- 30 Screens — 4DX, IMAX, Dolby Atmos, MX4D, Gold Class  
- 138 Seats — REGULAR, PREMIUM, VIP across screens  
- 48 Shows — Today's shows with real timings  
- 10 Users — Sample user accounts  

---


## 🖥️ Frontend <a name="frontend"></a>


This backend is connected to a **plain HTML, CSS, and JavaScript frontend**.

---

### 📁 Project Structure

```text
bookmyshow-ui/
├── index.html                  # Main HTML page
├── css/
│   └── style.css               # Stylesheet
├── js/
│   └── script.js               # Frontend logic
├── assets/                     # Images, posters, icons
└── api/
    └── api.js                  # AJAX / fetch calls to backend APIs
```

---

### ▶️ Run Frontend

 Open `index.html` in your browser  

---

### 🌐 Access Application

```
http://localhost:3306
```

---

## 🔧 CORS Configuration

CORS is configured in `CorsConfig.java` to allow frontend access:

<pre>
registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*");
</pre>

---


## 📊 Booking Flow

The following shows the step-by-step flow for booking tickets in the BookMyShow system:



1. **User selects a Movie**  
      ↓
2. **User selects Show** (date/time/theater)  
      ↓
3. **Check available seats**  

```http
GET /bookings/show/{showId}/available-seats
```
      ↓
4. **User selects seats** from the SeatMap  
      ↓
5. **Create booking**  

```http
POST /bookings
Body:
{
  "userId": 1,
  "showId": 5,
  "seatIds": [1, 2, 3]
}
```
      ↓
6. **Booking CONFIRMED ✅**  
      ↓
7. **View booking history**  

```http
GET /bookings/user/{userId}
```
      ↓
8. **Cancel booking if needed**  

```http
PUT /bookings/{id}/cancel
```


---

## 🗺️ Supported Cities & Theaters

| City         | Theaters                                                                 |
|--------------|--------------------------------------------------------------------------|
| 🏙️ Mumbai    | PVR ICON Versova, INOX R-City Mall, Cinepolis Viviana                     |
| 🏙️ Delhi     | PVR Select Citywalk, INOX Nehru Place, Cinepolis DLF                      |
| 🏙️ Bangalore | PVR Orion Mall, INOX Garuda Mall                                         |
| 🏙️ Hyderabad | AMB Cinemas Gachibowli, PVR Inorbit Mall                                  |
| 🏙️ Chennai   | SPI Palazzo Vadapalani, PVR VR Chennai                                     |
| 🏙️ Kolkata   | INOX South City Mall                                                      |
| 🏙️ Pune      | PVR Pavilion Mall, INOX Westin                                           |
| 🏙️ Jaipur    | PVR Crystal Palm, Cinepolis GT Central                                    |

---

## 🎬 Movies Available

| Movie                   | Language | Genre   | Rating |
|-------------------------|---------|--------|--------|
| Pathaan                 | Hindi   | Action | ⭐ 8.0 |
| RRR                     | Telugu  | Action | ⭐ 7.8 |
| Article 370             | Hindi   | Thriller | ⭐ 8.3 |
| Jawan                   | Hindi   | Action | ⭐ 7.9 |
| Pushpa 2: The Rule      | Telugu  | Action | ⭐ 8.2 |
| Animal                  | Hindi   | Drama  | ⭐ 6.9 |
| Leo                     | Tamil   | Thriller | ⭐ 7.0 |
| Dunki                   | Hindi   | Comedy | ⭐ 6.7 |
| Kalki 2898 AD           | Hindi   | Drama  | ⭐ 7.5 |
| Jailer                  | Tamil   | Action | ⭐ 7.2 |
| Crew                    | Hindi   | Comedy | ⭐ 7.1 |
| Tiger 3                 | Hindi   | Action | ⭐ 5.4 |
| Sam Bahadur             | Hindi   | Drama  | ⭐ 7.8 |
---

## 🐛 Known Issues & Fixes

| Issue                         | Fix                                                                 |
|-------------------------------|---------------------------------------------------------------------|
| Table 'bms.cities' doesn't exist | Add `@Table(name="City")` to City entity                             |
| CORS blocked                  | Change `addMapping("/api/**")` to `addMapping("/**")`               |
| id: undefined in URL          | Add guard: `if (!id || id === 'undefined') navigate('/movies')`     |
| `durationInMinutes` null      | Add `@Column(name="duration_in_minutes")` to Movie entity            |
| Double seats in modal         | Use `available-seats` API for `screenId`, not `seatService`         |
---

## 📝 Entity Column Mapping

> Important: Add `PhysicalNamingStrategyStandardImpl` in `application.properties`, then map columns explicitly.

### 🎬 Movie.java
```java
@Column(name = "duration_in_minutes")
private Integer durationInMinutes;

@Column(name = "poster_url")
private String posterUrl;

@Column(name = "realese_date")
private LocalDate realeseDate;
```

### 💺 Seat.java
```java
@Column(name = "seat_rol")
private String row;

@Column(name = "seat_col")
private Integer col;
```

### 👤 User.java
```java
@Column(name = "phone_number")
private String phoneNumber;
```
---

## 👨‍💻 Author

**Shevendra77 / Shevendra Singh**

- 🌐 GitHub: [https://github.com/Shevendra77/BookMyShow](https://github.com/Shevendra77/BookMyShow.git)
- 📧 Email: shevendrachandel@gmail.com
---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](./LICENSE) file for details.
--
## 🙏 Acknowledgements

- Inspired by [**BookMyShow**](https://in.bookmyshow.com) — India's largest entertainment ticketing platform  
- Built as a **full-stack learning project** with Spring Boot  
- Special thanks to [**Code for Success platform**] - for guidance and tutorials

- --


                                                   ⭐ **Star this repo** if you found it helpful!  

                                                              Made with ❤️ **in India** 🇮🇳



