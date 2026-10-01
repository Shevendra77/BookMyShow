# 🎬 BookMyShow – Movie Ticket Booking System

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-success)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-yellow)
![Razorpay](https://img.shields.io/badge/Razorpay-Test%20Mode-blueviolet)
![License](https://img.shields.io/badge/License-MIT-green)

---

🚀 **A full-stack Movie Ticket Booking Application built using Java, Spring Boot, MySQL, Spring Data JPA, Hibernate, HTML, CSS, JavaScript, and Razorpay.**

🎬 Users can browse movies, select theaters and shows, choose seats, create bookings, make online payments through Razorpay, receive ticket confirmation by email, and access a QR-based digital ticket.

🎟️ **Admin users can scan customer QR tickets using a laptop camera and verify the ticket through the Spring Boot backend. Once successfully verified, a confirmed ticket is marked as `USED` to prevent reuse.**

---

## 📋 Table of Contents

* [Features](#features)
* [Tech Stack](#tech-stack)
* [Project Structure](#project-structure)
* [Database Schema](#database-schema)
* [Entity Relationships](#entity-relationships)
* [API Reference](#api-reference)
* [Getting Started](#getting-started)
* [Configuration](#configuration)
* [Running the App](#running-the-app)
* [Sample Data](#sample-data)
* [Frontend](#frontend)
* [Booking Flow](#booking-flow)
* [QR Ticket Verification](#qr-ticket-verification)
* [Email Confirmation](#email-confirmation)
* [Supported Cities & Theaters](#supported-cities--theaters)
* [Movies](#movies)
* [Known Issues & Fixes](#known-issues--fixes)
* [Future Enhancements](#future-enhancements)
* [Author](#author)
* [License](#license)

---

## ✨ Features <a name="features"></a>

### 🎬 Movie & Theater Management

* 🏙️ City management
* 🎬 Movie catalog
* 🔎 Movie search by title
* 🎭 Filter movies by genre
* 🌐 Filter movies by language
* 🎞️ Movie trailer support
* 🏛️ Multiple theaters per city
* 🎥 Multiple screens per theater
* 🎦 Support for screen types such as IMAX, 4DX and Dolby Atmos
* 💺 REGULAR / PREMIUM / VIP seat types
* 🎟️ Multiple shows per screen

### 👤 User Features

* 👤 User registration
* 🔐 User login
* 🎬 Browse movies
* 🏛️ Select theater
* 🎥 Select screen and show
* 💺 Select multiple seats
* 🎟️ Create booking
* 📋 View booking history
* ❌ Cancel booking
* 🎫 View QR ticket

### 💳 Payment Features

* 💳 Razorpay Test Mode integration
* 🔐 Server-side Razorpay payment signature verification
* 💰 Payment amount validation
* 📦 Razorpay order creation
* ✅ Booking confirmation after successful payment

### 📧 Ticket & Notification Features

* 📧 Automatic booking confirmation email
* 🎟️ Digital ticket information
* 🔳 QR code generation using ZXing
* 📱 QR ticket available from My Bookings
* 📧 QR code included in confirmation email

### 🎫 QR Ticket Verification

* 📷 Admin QR scanner using laptop camera
* 🔍 QR data extraction using `html5-qrcode`
* 🔐 Backend ticket verification
* ✅ Valid `CONFIRMED` ticket accepted
* 🔄 `CONFIRMED → USED` after successful verification
* ❌ Cancelled tickets rejected
* ❌ Already used tickets rejected
* ❌ Invalid tickets rejected
* 🔊 Scan confirmation sound
* 🟢 Visual scan detection indicator

### ⚙️ Backend Features

* 🌐 RESTful APIs
* 🗄️ MySQL database
* 🔄 Spring Data JPA
* 🛠️ Hibernate ORM
* ⚠️ Backend validation
* 🚨 Exception handling
* 🌐 CORS configuration
* 📦 DTO-based API communication

---

## 🛠️ Tech Stack <a name="tech-stack"></a>

| 🛠️ Technology  | 📦 Version | 🎯 Purpose                       |
| --------------- | ---------: | -------------------------------- |
| Java            |         21 | Programming Language             |
| Spring Boot     |      4.0.3 | Backend Web Framework            |
| Spring Data JPA |      4.0.3 | Data Access & ORM                |
| Hibernate       |        7.x | JPA Implementation               |
| MySQL           |        8.0 | Relational Database              |
| Lombok          |    1.18.44 | Boilerplate Code Reduction       |
| Maven           |        3.x | Build & Dependency Management    |
| Razorpay        |  Test Mode | Online Payment Integration       |
| Spring Mail     |          — | Email & Ticket Notifications     |
| ZXing           |      3.5.3 | QR Code Generation               |
| html5-qrcode    |          — | QR Code Scanning                 |
| REST API        |          — | Client-Server Communication      |
| Postman         |          — | API Testing                      |
| HTML            |          5 | Frontend Structure               |
| CSS             |          3 | Frontend Styling                 |
| JavaScript      |       ES6+ | Frontend Logic & API Integration |
| Git             |          — | Version Control                  |
| GitHub          |          — | Code Hosting                     |

---

## 📁 Project Structure <a name="project-structure"></a>

```text
BookMyShow/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── cfs/
│       │           └── BMS/
│       │               ├── BmsApplication.java
│       │               │
│       │               ├── config/
│       │               │   └── CorsConfig.java
│       │               │
│       │               ├── controller/
│       │               │   ├── BookingController.java
│       │               │   ├── CityController.java
│       │               │   ├── MovieController.java
│       │               │   ├── PaymentController.java
│       │               │   ├── ScreenController.java
│       │               │   ├── SeatController.java
│       │               │   ├── ShowController.java
│       │               │   ├── TheaterController.java
│       │               │   └── UserController.java
│       │               │
│       │               ├── dto/
│       │               │   ├── BookingDto/
│       │               │   │   ├── BookingRequestDto.java
│       │               │   │   └── BookingResponseDTO.java
│       │               │   ├── LogInDto/
│       │               │   │   └── LoginRequestDto.java
│       │               │   ├── PaymentDto/
│       │               │   │   ├── PaymentOrderRequestDto.java
│       │               │   │   ├── PaymentOrderResponseDto.java
│       │               │   │   └── VerifyPaymentRequestDto.java
│       │               │   ├── ScreenDto/
│       │               │   │   └── ScreenResponseDTO.java
│       │               │   ├── SeatDto/
│       │               │   │   └── SeatResponseDTO.java
│       │               │   ├── ShowDto/
│       │               │   │   └── ShowResponseDTO.java
│       │               │   ├── TheaterDto/
│       │               │   │   └── TheaterResponseDTO.java
│       │               │   └── UserDto/
│       │               │       └── UserRequestDto.java
│       │               │
│       │               ├── entity/
│       │               │   ├── Booking.java
│       │               │   ├── City.java
│       │               │   ├── Movie.java
│       │               │   ├── Payment.java
│       │               │   ├── Screen.java
│       │               │   ├── Seat.java
│       │               │   ├── Show.java
│       │               │   ├── Theater.java
│       │               │   └── User.java
│       │               │
│       │               ├── enums/
│       │               │   ├── BookingStatus.java
│       │               │   └── SeatType.java
│       │               │
│       │               ├── repository/
│       │               │   ├── BookingRepository.java
│       │               │   ├── CityRepository.java
│       │               │   ├── MovieRepository.java
│       │               │   ├── PaymentRepository.java
│       │               │   ├── ScreenRepository.java
│       │               │   ├── SeatRepository.java
│       │               │   ├── ShowRepository.java
│       │               │   ├── TheaterRepository.java
│       │               │   └── UserRepository.java
│       │               │
│       │               └── service/
│       │                   ├── BookingService/
│       │                   ├── CityService/
│       │                   ├── EmailService/
│       │                   ├── MoviesService/
│       │                   ├── PaymentService/
│       │                   ├── ScreenService/
│       │                   ├── SeatService/
│       │                   ├── ShowService/
│       │                   ├── TheaterService/
│       │                   └── UserService/
│       │
│       └── resources/
│           └── application.properties
│
├── frontend/
│   ├── index.html
│   ├── css/
│   ├── js/
│   ├── pages/
│   └── assets/
│
├── pom.xml
└── README.md
```

> Package/folder names should match the actual project structure in your repository. If your physical folder names use lowercase (`controller`, `entity`, etc.), keep them lowercase consistently.

---

## 🗄️ Database Schema <a name="database-schema"></a>

```text
City
 │
 └──< Theater
        │
        └──< Screen
               │
               ├──< Seat
               │
               └──< Show
                      │
                      └──< Booking
                             │
                             ├──< Booking_Seats
                             │
                             └── Payment


Movie
 │
 └──< Show


User
 │
 └──< Booking
```

### Main Entities

```text
City
(id, name, state)

Theater
(id, name, address, city_id)

Screen
(id, name, total_seat, theater_id)

Seat
(id, seat_number, seat_row, seat_col, seat_type, screen_id)

Movie
(id, title, language, genre, duration_in_minutes,
 rating, poster_url, release_date, trailer_url, description)

Show
(id, movie_id, screen_id, show_date,
 start_time, end_time, ticket_price)

User
(id, name, email, password, phone_number, created_at)

Booking
(id, user_id, show_id, total_price,
 booking_status, booked_at)

Booking_Seats
(booking_id, seat_id)

Payment
(id, booking_id, razorpay_order_id,
 razorpay_payment_id, razorpay_signature,
 amount, status)
```

---

## 🔗 Entity Relationships <a name="entity-relationships"></a>

| Relationship      | Type                                                 |
| ----------------- | ---------------------------------------------------- |
| City → Theaters   | One-to-Many                                          |
| Theater → Screens | One-to-Many                                          |
| Screen → Seats    | One-to-Many                                          |
| Screen → Shows    | One-to-Many                                          |
| Movie → Shows     | One-to-Many                                          |
| User → Bookings   | One-to-Many                                          |
| Show → Bookings   | One-to-Many                                          |
| Booking ↔ Seats   | Many-to-Many                                         |
| Booking → Payment | One-to-One / One-to-Many depending on implementation |

---

## 🌐 API Reference <a name="api-reference"></a>

**Base URL**

```text
http://localhost:8080/api
```

---

### 🏙️ City API

| Method | Endpoint       | Description    |
| ------ | -------------- | -------------- |
| GET    | `/cities`      | Get all cities |
| GET    | `/cities/{id}` | Get city by ID |

---

### 🎬 Movie API

| Method | Endpoint                  | Description            |
| ------ | ------------------------- | ---------------------- |
| GET    | `/movies`                 | Get all movies         |
| GET    | `/movies/{id}`            | Get movie by ID        |
| POST   | `/movies`                 | Add movie              |
| PUT    | `/movies/{id}`            | Update movie           |
| DELETE | `/movies/{id}`            | Delete movie           |
| GET    | `/movies/search?title=`   | Search movies by title |
| GET    | `/movies/genre/{genre}`   | Filter by genre        |
| GET    | `/movies/language/{lang}` | Filter by language     |

**Movie Request**

```json
{
  "title": "Pushpa 2: The Rule",
  "description": "Pushpa Raj expands his empire",
  "durationInMinutes": 152,
  "genre": "Action",
  "language": "Telugu",
  "posterUrl": "https://example.com/poster.jpg",
  "trailerUrl": "https://youtu.be/example",
  "rating": 8.2,
  "releaseDate": "2024-12-05"
}
```

---

### 🏛️ Theater API

| Method | Endpoint                  | Description          |
| ------ | ------------------------- | -------------------- |
| GET    | `/theaters/getAllTheater` | Get all theaters     |
| GET    | `/theaters/{id}`          | Get theater by ID    |
| POST   | `/theaters`               | Add theater          |
| GET    | `/theaters/city/{cityId}` | Get theaters by city |

**Request Body**

```json
{
  "name": "PVR ICON",
  "address": "Mumbai",
  "cityId": 1
}
```

---

### 🎥 Screen API

| Method | Endpoint                       | Description            |
| ------ | ------------------------------ | ---------------------- |
| GET    | `/screens`                     | Get all screens        |
| GET    | `/screens/{id}`                | Get screen by ID       |
| POST   | `/screens`                     | Add screen             |
| GET    | `/screens/theater/{theaterId}` | Get screens by theater |

**Request Body**

```json
{
  "name": "Screen 1 - IMAX",
  "totalSeats": 250,
  "theaterId": 1
}
```

---

### 💺 Seat API

| Method | Endpoint                   | Description         |
| ------ | -------------------------- | ------------------- |
| GET    | `/seats/screen/{screenId}` | Get seats by screen |
| GET    | `/seats/{id}`              | Get seat by ID      |
| POST   | `/seats`                   | Add seat            |

**Request Body**

```json
{
  "seatNumber": "A1",
  "row": "A",
  "col": 1,
  "seatType": "REGULAR",
  "screenId": 1
}
```

**Seat Types**

| Type    | Description      |
| ------- | ---------------- |
| REGULAR | Standard seating |
| PREMIUM | Premium seating  |
| VIP     | VIP seating      |

---

### 🎟️ Show API

| Method | Endpoint                            | Description                 |
| ------ | ----------------------------------- | --------------------------- |
| GET    | `/shows`                            | Get all shows               |
| GET    | `/shows/{id}`                       | Get show by ID              |
| POST   | `/shows`                            | Add show                    |
| GET    | `/shows/movie/{movieId}`            | Get shows by movie          |
| GET    | `/shows/movie/{movieId}/date?date=` | Get shows by movie and date |

**Request Body**

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

### 👤 User API

| Method | Endpoint            | Description    |
| ------ | ------------------- | -------------- |
| POST   | `/users/register`   | Register user  |
| POST   | `/users/login`      | Login user     |
| GET    | `/users/{id}`       | Get user by ID |
| GET    | `/users/getalluser` | Get all users  |

**Register Request**

```json
{
  "name": "Rahul Sharma",
  "email": "rahul@gmail.com",
  "password": "pass123",
  "phoneNumber": "9876543210"
}
```

**Login Request**

```json
{
  "email": "rahul@gmail.com",
  "password": "pass123"
}
```

---

### 🎟️ Booking API

| Method | Endpoint                                  | Description          |
| ------ | ----------------------------------------- | -------------------- |
| POST   | `/bookings`                               | Create booking       |
| GET    | `/bookings/{id}`                          | Get booking by ID    |
| GET    | `/bookings/user/{userId}`                 | Get bookings by user |
| PUT    | `/bookings/{id}/cancel`                   | Cancel booking       |
| GET    | `/bookings/show/{showId}/available-seats` | Get available seats  |
| POST   | `/bookings/{id}/verify-ticket`            | Verify QR ticket     |

**Create Booking**

```json
{
  "userId": 1,
  "showId": 5,
  "seatIds": [1, 2, 3]
}
```

**Booking Status**

```text
PENDING
CONFIRMED
CANCELLED
USED
```

**Example Response**

```json
{
  "id": 51,
  "userId": 1,
  "showId": 5,
  "seats": [
    {
      "id": 1,
      "seatNumber": "A1",
      "seatType": "REGULAR"
    }
  ],
  "totalPrice": 450,
  "status": "CONFIRMED"
}
```

---

### 💳 Payment API

| Method | Endpoint                 | Description             |
| ------ | ------------------------ | ----------------------- |
| POST   | `/payments/create-order` | Create Razorpay order   |
| POST   | `/payments/verify`       | Verify Razorpay payment |

**Create Order**

```json
{
  "bookingId": 101,
  "amount": 750.00
}
```

**Payment Verification**

The frontend sends the Razorpay payment information to the backend. The backend verifies the Razorpay signature before confirming the booking.

```text
Razorpay Checkout
       ↓
Payment Completed
       ↓
Frontend receives payment details
       ↓
Spring Boot Backend
       ↓
Verify Razorpay Signature
       ↓
Payment Successful
       ↓
Booking CONFIRMED
```

---

## 🎫 QR Ticket Verification <a name="qr-ticket-verification"></a>

The application provides a complete QR-based ticket verification flow.

### QR Generation

QR codes are generated using **ZXing** after successful booking/payment processing. The QR contains booking-related information including the Booking ID.

### Customer Flow

```text
Successful Payment
        ↓
Booking CONFIRMED
        ↓
QR Ticket Generated
        ↓
QR available in My Bookings
        ↓
QR also included in Email
```

### Admin Flow

```text
Admin Panel
    ↓
Scan QR Ticket
    ↓
Laptop Camera
    ↓
html5-qrcode
    ↓
Decode QR Data
    ↓
Extract Booking ID
    ↓
POST /bookings/{id}/verify-ticket
    ↓
Spring Boot Backend
    ↓
Check Booking Status
```

### Valid Ticket

```text
CONFIRMED
    ↓
Verification Successful
    ↓
Status → USED
    ↓
Entry Allowed
```

### Invalid / Used Ticket

```text
CANCELLED
     ↓
Rejected

USED
     ↓
Rejected

Invalid Booking ID
     ↓
Rejected
```

This prevents the same confirmed ticket from being successfully verified multiple times.

---

## 📧 Email Confirmation <a name="email-confirmation"></a>

After successful payment verification, the system sends a booking confirmation email.

The email contains:

* 🎬 Movie name
* 🏛️ Theater name
* 🎥 Screen name
* 📅 Show date
* 🕐 Show time
* 💺 Selected seats
* 🎟️ Booking ID
* 💰 Amount paid
* ✅ Payment status
* 🔳 QR ticket

The QR image is generated using **ZXing** and embedded directly into the email.

---

## 🚀 Getting Started <a name="getting-started"></a>

### Prerequisites

* Java 21
* Maven 3.x
* MySQL 8.0+
* Git
* IntelliJ IDEA / VS Code
* Modern web browser

### 1. Clone Repository

```bash
git clone https://github.com/Shevendr77/BookMyShow.git
cd BookMyShow
```

### 2. Create MySQL Database

```sql
CREATE DATABASE BMS;
```

### 3. Configure Database

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/BMS?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 4. Configure Environment Variables

> ⚠️ Do not commit real credentials to GitHub.

Set the following environment variables:

```text
MAIL_USERNAME
MAIL_PASSWORD
RAZORPAY_KEY_ID
RAZORPAY_KEY_SECRET
```

Reference them in `application.properties`:

```properties
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}

razorpay.key.id=${RAZORPAY_KEY_ID}
razorpay.key.secret=${RAZORPAY_KEY_SECRET}
```

---

## ⚙️ Configuration <a name="configuration"></a>

### Application

```properties
spring.application.name=BMS
```

### Database

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/BMS?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### JPA / Hibernate

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

### Server

```properties
server.port=8080
server.servlet.context-path=/api
```

---

## ▶️ Running the App <a name="running-the-app"></a>

### Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

### Using JAR

```bash
mvn clean package
java -jar target/BMS-0.0.1-SNAPSHOT.jar
```

### Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Open `BmsApplication.java`.
3. Click **Run ▶️**.
4. Verify that Spring Boot starts successfully.

### Verify Backend

```bash
curl http://localhost:8080/api/movies
```

Backend base URL: `http://localhost:8080/api`

---

## 🌱 Sample Data <a name="sample-data"></a>

If the repository contains the SQL dataset, import it using:

```bash
mysql -u root -p BMS < bms_india_final.sql
```

The sample dataset contains Indian cities, theaters, movies, screens, seats, shows and users.

> Dataset counts may change as the project database is updated, so the SQL file should be treated as the source of truth.

---

## 🖥️ Frontend <a name="frontend"></a>

The frontend is built using:

* HTML5
* CSS3
* JavaScript ES6+
* Fetch API
* Razorpay Checkout
* html5-qrcode
* QRCode.js

The frontend communicates with the Spring Boot backend through REST APIs.

```text
Frontend
   ↓
Fetch API
   ↓
Spring Boot REST API
   ↓
Service Layer
   ↓
Repository Layer
   ↓
MySQL
```

### Run Frontend

The frontend can be opened using a local development server such as **VS Code Live Server**:

```text
http://127.0.0.1:5500/
```

> `3306` is the default MySQL port, not the frontend port.

The backend runs on `http://localhost:8080`, and the API base path is `http://localhost:8080/api`.

### 🔧 CORS Configuration

The backend contains CORS configuration to allow the frontend to communicate with the Spring Boot APIs.

```java
registry.addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        )
        .allowedHeaders("*");
```

> For production deployment, `allowedOrigins("*")` should be replaced with the actual frontend domain.

---

## 📊 Booking Flow <a name="booking-flow"></a>

```text
👤 User
   ↓
🔐 Login / Register
   ↓
🏙️ Select City
   ↓
🎬 Select Movie
   ↓
🏛️ Select Theater
   ↓
🎥 Select Screen
   ↓
🎟️ Select Show
   ↓
💺 Select Seats
   ↓
📝 Create Booking
   ↓
⏳ Booking Status: PENDING
   ↓
💳 Create Razorpay Order
   ↓
🔐 Razorpay Checkout
   ↓
💰 Complete Payment
   ↓
🔍 Verify Razorpay Signature
   ↓
✅ Payment Successful
   ↓
🎟️ Booking Status: CONFIRMED
   ↓
📧 Send Confirmation Email
   ↓
🔳 Generate QR Ticket
   ↓
📱 Customer Views QR
   ↓
📷 Admin Scans QR
   ↓
🔍 Verify Booking
   ↓
✅ CONFIRMED → USED
   ↓
🎫 Entry Allowed
```

### 🔐 Booking Status Lifecycle

```text
PENDING
   │
   │ Payment Successful
   ↓
CONFIRMED
   │
   │ QR Ticket Verified
   ↓
USED
```

Alternative transitions:

```text
PENDING   → CANCELLED
CONFIRMED → CANCELLED
CONFIRMED → USED
```

The `USED` status prevents successful reuse of an already scanned ticket.

---

## 🗺️ Supported Cities & Theaters <a name="supported-cities--theaters"></a>

The sample dataset includes cities and theaters representing multiple locations across India.

| City      | Example Theaters          |
| --------- | ------------------------- |
| Mumbai    | PVR ICON, INOX, Cinepolis |
| Delhi     | PVR Select Citywalk, INOX |
| Bangalore | PVR Orion Mall, INOX      |
| Hyderabad | AMB Cinemas, PVR          |
| Chennai   | SPI Palazzo, PVR          |
| Kolkata   | INOX                      |
| Pune      | PVR, INOX                 |
| Jaipur    | PVR, Cinepolis            |

> Sample data can be modified through the database/admin functionality.

---

## 🎬 Movies <a name="movies"></a>

The application supports movies with information such as:

| Field        | Example            |
| ------------ | ------------------ |
| Title        | Pushpa 2: The Rule |
| Language     | Telugu             |
| Genre        | Action             |
| Rating       | 8.2                |
| Duration     | 152 minutes        |
| Release Date | 2024-12-05         |
| Poster       | Image URL          |
| Trailer      | YouTube URL        |
| Description  | Movie description  |

---

## 🐛 Known Issues & Fixes <a name="known-issues--fixes"></a>

| Issue                             | Solution                                                |
| --------------------------------- | ------------------------------------------------------- |
| Table name mismatch               | Explicitly map entity table names using `@Table`        |
| CORS blocked                      | Configure CORS mapping for API requests                 |
| `id: undefined` in URL            | Validate ID before navigation/API request               |
| `durationInMinutes` mapping issue | Explicit `@Column` mapping                              |
| Seat duplication in UI            | Use show-specific available-seat API                    |
| Camera not opening from `file://` | Run frontend through Live Server/local HTTP server      |
| QR ticket reused                  | Backend changes `CONFIRMED` → `USED` after verification |

### 📝 Entity Column Mapping

Because the application uses:

```properties
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

database column names should be mapped explicitly where required.

**Movie**

```java
@Column(name = "duration_in_minutes")
private Integer durationInMinutes;

@Column(name = "poster_url")
private String posterUrl;

@Column(name = "release_date")
private LocalDate releaseDate;

@Column(columnDefinition = "TEXT")
private String trailerUrl;
```

**Seat**

```java
@Column(name = "seat_row")
private String row;

@Column(name = "seat_col")
private Integer col;
```

**User**

```java
@Column(name = "phone_number")
private String phoneNumber;
```

---

## 📌 Future Enhancements <a name="future-enhancements"></a>

* 🔐 Spring Security with JWT-based authentication
* 👥 Role-based access control for Admin/User
* 🧾 PDF ticket generation
* 📊 Admin dashboard with booking/payment analytics
* 📈 Revenue reports
* 🔔 Notification system
* 🐳 Docker Compose deployment
* ☁️ Cloud deployment
* ⚡ Redis caching
* 🔄 Distributed locking for high-concurrency seat booking
* 🧪 Automated unit and integration testing
* 📱 Responsive mobile-first improvements
* 🔍 Advanced movie filtering and sorting

---

## 👨‍💻 Author <a name="author"></a>

**Shevendra Singh Chandel**

* GitHub: [Shevendr77/BookMyShow](https://github.com/Shevendr77/BookMyShow)
* Email: [shevendrachandel@gmail.com](mailto:shevendrachandel@gmail.com)

---

## 📄 License <a name="license"></a>

This project is licensed under the **MIT License**. See the `LICENSE` file for more information.

---

## 🙏 Acknowledgements

* Inspired by **BookMyShow** for the overall movie-ticket-booking concept.
* Built as a **full-stack learning project** using Spring Boot and Java.
* Razorpay is used in **Test Mode** for payment integration.
* ZXing is used for QR code generation.
* html5-qrcode is used for camera-based QR scanning.

---

⭐ **If you find this project useful, consider giving the repository a star!**

Made with ❤️ in India 🇮🇳
