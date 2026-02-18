# Recruitment System – Spring Boot REST Backend

## Objective

The **Recruitment Management System** is a RESTful backend application built using **Spring Boot**. 

It provides a complete recruitment workflow where **Applicants** can manage profiles, upload resumes, and apply for jobs, while **Admins** can create job postings and manage applicants.

This project follows **clean architecture principles** using DTOs, mappers, layered services, and role-based security.


---

## Technologies Used

- **Language:** Java
- **Framework:** Spring Boot
- **Security:** Spring Security + JWT Authentication 
- **Database:** MySQL
- **ORM:** JPA / Hibernate
- **Build Tool:** Maven
- **API Client:** Postman
- **Mapping:** DTO ↔ Entity Mapper (ModelMapper / Manual Mapper)
- **Validation:** Jakarta Bean Validation 
- **Architecture:** Controller -> Service -> Repository
- **DTO Mapping:** Manual DTO <-> Entity Mappers

---

## User Roles

### 1. Admin

* Create job openings
* View all applicants
* View applicant resume & extracted data
* View job-wise applications

### 2. Applicant

* Create profile
* Upload resume (PDF / DOCX)
* View available jobs
* Apply to jobs

---

## Features

### Authentication & Authorization

* User signup with role (**ADMIN / APPLICANT**)
* Login using email & password
* JWT-based authentication
* Role-based API access using Spring Security

### Resume Management

* Resume upload (PDF / DOCX only)
* Resume stored on server (file system)
* Resume parsed using **third-party Resume Parser API**
* Extracted data stored in the `Profile` table

### Job Management

* Admin can create job postings
* Applicants can view all jobs
* Applicants can apply to jobs
* Track total applications per job

---

## API Endpoints

### Authentication APIs

* **POST** `/signup` – Create user profile
* **POST** `/login` – Authenticate user & return JWT

### Applicant APIs

* **GET** `/jobs` – View all job openings
* **GET** `/jobs/apply?jobId={id}` – Apply to a job
* **POST** `/uploadResume` – Upload resume (PDF / DOCX)

> **Note:** Only users with role **APPLICANT** can upload resumes or apply for jobs.

### Admin APIs

* **POST** `/admin/job` – Create a job opening
* **GET** `/admin/job/{jobId}` – View job details & applicants
* **GET** `/admin/applicants` – View all applicants
* **GET** `/admin/applicant/{id}` – View applicant profile & resume data

> **Note:** Only users with role **ADMIN** can access these APIs.

---

## Data Models

### User

* id
* name
* email
* password (encrypted)
* address
* profileHeadline
* userType (ADMIN / APPLICANT)
* profile

### Profile

* resumeFilePath
* skills (String)
* education (String)
* experience (String)
* name
* email
* phone
* applicant (User)

### Job

* title
* description
* companyName
* postedOn
* totalApplications
* postedBy (Admin User)

### JobApplication

* applicant
* job
* appliedOn

---

## Resume Parser Integration

**Third-party API Used:** APILayer Resume Parser

* **Endpoint:** `https://api.apilayer.com/resume_parser/upload`
* **Method:** POST
* **Headers:**

  * `Content-Type: application/octet-stream`
  * `apikey: <API_KEY>`

### Workflow

1. Applicant uploads resume (PDF/DOCX)
2. File is stored on the server
3. Resume file is sent to the third-party API
4. JSON response is received
5. Relevant fields are extracted
6. Data is mapped and saved in the `Profile` table

### Stored Fields

* Name
* Email
* Phone
* Skills
* Education
* Experience

> Any missing field from the API response is stored as **null** or an empty string.

---

## DTO & Mapper Usage

* Request and response handling is done using **DTOs**
* Entity ↔ DTO conversion is handled via a dedicated **Mapper layer**
* Ensures clean separation between API, business, and persistence layers

---

## Security

* Passwords stored using encryption
* JWT token validation for each secured API
* Role-based authorization using Spring Security

