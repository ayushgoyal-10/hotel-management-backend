# Hotel Management System API

A robust RESTful API built with **Spring Boot 3** and **Java 21** to streamline hotel operations. This system handles the entire lifecycle of a reservation, from guest registration and room availability checks to check-in/check-out workflows, secured with Spring Security.

## Key Features

* **Room Management**: Manage room inventory, pricing, and types. Real-time status updates (Available, Occupied, Dirty).
* **Smart Booking Engine**: Prevents double-booking by automatically checking for date overlaps before confirming a reservation.
* **Guest Management**: Securely store guest details with input validation (Email, Phone Number regex).
* **Check-in / Check-out Workflow**: specific endpoints to transition bookings through their lifecycle, automatically updating room statuses.
* **Security**: Secured endpoints using **Spring Security** (Basic Auth).
* **Robust Error Handling**: Global Exception Handling returning clean, standardized JSON error responses.
* **Data Validation**: Strict DTO validation using Jakarta Validation (`@Future` dates, `@NotNull` fields, etc.).

## Tech Stack

* **Language**: Java 21
* **Framework**: Spring Boot
* **Database**: MySQL
* **ORM**: Spring Data JPA (Hibernate)
* **Security**: Spring Security
* **Build Tool**: Gradle
* **Utilities**: Lombok, ModelMapper

## Setup & Installation

### Prerequisites
* Java 21 SDK installed.
* MySQL installed and running.

### Steps
1.  **Clone the repository**
    ```bash
    git clone [https://github.com/ayushgoyal-10/hotel-management-backend.git]
    ```

2.  **Configure Database**
    Open `src/main/resources/application.properties` and update your MySQL credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Run the Application**
    ```bash
    ./gradlew bootRun
    ```

## API Endpoints

### Authentication
**Default Credentials:**
* **Username:** `admin`
* **Password:** `admin@10`

### Rooms
* `GET /api/rooms` - List all rooms
* `POST /api/rooms` - Add a new room
* `GET /api/rooms/available` - Get rooms available for booking

### Guests
* `GET /api/guests` - List all guests
* `POST /api/guests` - Register a new guest
* `GET /api/guests/{id}` - Get guest details

### Bookings
* `POST /api/bookings` - Create a reservation (Includes conflict checks)
* `PUT /api/bookings/{id}/checkin` - Check a guest in (Updates room to OCCUPIED)
* `PUT /api/bookings/{id}/checkout` - Check a guest out (Updates room to DIRTY)
* `DELETE /api/bookings/{id}` - Cancel a booking (Only if not checked in)

## Validation Rules
The API strictly validates input to ensure data integrity:
* **Dates:** Check-out must be after Check-in; dates cannot be in the past.
* **Phone:** Must be exactly 10 digits.
* **Email:** Must be a valid email format.

## Future Improvements
* [ ] Implementation of JWT (JSON Web Token) Authentication.
* [ ] Unit and Integration Testing with JUnit 5 & Mockito.
* [ ] Docker support for easy deployment.