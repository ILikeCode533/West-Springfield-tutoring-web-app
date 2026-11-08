

# Tutoring Club Management System


A web-based tutoring management platform designed to streamline how students request academic assistance and how tutoring club officers coordinate tutoring sessions.


The goal is to replace manual request forms with a centralized system for managing tutoring requests, subjects, tutor availability, and assignments.


## Project Status


**Status:** In Development  
**Current Phase:** Backend Development


The initial tutoring request submission workflow is functional and has been verified using PostgreSQL.


The student-facing interface and tutor assignment system are still under development.


## Technology Stack


| Technology | Purpose |
|---|---|
| Java 21 | Backend programming language |
| Spring Boot | Application framework |
| Spring MVC | REST API and HTTP request handling |
| Spring Data JPA / Hibernate | Database persistence and ORM |
| PostgreSQL | Relational database |
| Spring Security | Authentication and authorization |
| Thymeleaf | Planned frontend template engine |
| Maven | Build and dependency management |
| JUnit / Mockito | Automated testing |
| Git / GitHub | Version control |
| Replit | Cloud-based IDE |


## System Architecture


The backend follows a layered architecture that separates HTTP handling, business logic, and database persistence.


```text
Student / Browser
       |
       v
Controller Layer
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
PostgreSQL Database
```


### Controller Layer


Receives HTTP requests, extracts request parameters, and delegates operations to the service layer.


### Service Layer


Implements application logic, including retrieving student and subject records and creating tutoring requests.


### Repository Layer


Uses Spring Data JPA to retrieve and persist database entities.


### Database Layer


PostgreSQL stores users, subjects, and tutoring requests while enforcing relational constraints.


## Implemented Features


### Tutoring Request Submission


The backend supports creating tutoring requests for existing students and subjects.


**Submission workflow:**


1. Receive a student UUID and subject UUID.
2. Retrieve the corresponding student and subject.
3. Construct a new tutoring request.
4. Assign an initial `PENDING` status.
5. Persist the request using Spring Data JPA.
6. Return HTTP `201 Created`.


### REST API


**Endpoint**


```http
POST /api/requests
```


**Required Parameters**


| Parameter | Type | Description |
|---|---|---|
| `studentId` | UUID | Existing student identifier |
| `subjectId` | UUID | Existing subject identifier |


**Successful Response**


```http
HTTP/1.1 201 Created
```


### Database Design


The current application contains three primary entities:


- **User:** Represents a registered application user.
- **Subject:** Represents an academic tutoring subject.
- **TutoringRequest:** Represents a student's request for assistance.


Each tutoring request references one user and one subject through JPA `@ManyToOne` relationships.


## Development Setup


### Prerequisites


- Java 21
- Maven
- PostgreSQL


### Environment Variables


The application uses environment variables for its database connection.


```text
DB_JDBC_URL
DB_USERNAME
DB_PASSWORD
```


Database credentials must not be committed to version control.


### Running the Application


```bash
mvn spring-boot:run
```


The application currently runs on port `8080` in the development environment.


### Testing the API


A tutoring request can be submitted using `curl` with valid student and subject UUIDs.


```bash
curl -i -X POST \
"http://localhost:8080/api/requests?studentId=<STUDENT_UUID>&subjectId=<SUBJECT_UUID>"
```


Successful submissions return HTTP `201 Created`.


Database persistence has been verified directly in PostgreSQL.


## Planned Features


- [x] Database entities and relationships
- [x] Spring Data JPA repositories
- [x] Tutoring request service
- [x] REST API for request submission
- [x] PostgreSQL persistence verification
- [ ] Improved HTTP error handling
- [ ] Automated unit and integration tests
- [ ] Student-facing request form
- [ ] Tutor availability management
- [ ] Officer dashboard
- [ ] Tutor assignment system
- [ ] Request status tracking
- [ ] Production-ready authentication and authorization


## Development Workflow


Development follows a feature-branch workflow using Git and GitHub.


1. Create a feature branch.
2. Implement the functionality.
3. Test the changes.
4. Commit and push the code.
5. Open a pull request.
6. Review and merge completed features into `main`.


### Development Environment


Replit is used as a cloud-based integrated development environment (IDE) for writing, running, and debugging the application.


GitHub is used for source control, branch management, and pull requests.


## Project Goals


The long-term goal is to build a maintainable and accessible tutoring management platform that:


- Simplifies tutoring requests for students.
- Reduces manual coordination for club officers.
- Organizes tutor availability and assignments.
- Improves the efficiency of school tutoring operations.


The project also provides practical experience with Java backend development, REST APIs, relational databases, software architecture, automated testing, and version control.




## License & Copyright


Copyright © 2026 Alex Mitchell. All rights reserved.


This project is publicly available for educational,
portfolio, and code-review purposes.


No permission is granted to reproduce, modify,
redistribute, or commercially use the source code
without prior written authorization from the copyright holder.


Public availability does not constitute an open-source license.


