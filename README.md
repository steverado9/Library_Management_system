# Library Management System

A **Spring MVC web application** for managing books, users, and borrowing transactions.  
This project demonstrates how to use **Spring Boot**, **Spring Data JPA**, **Thymeleaf**, and **MySQL** to build a functional library system with book availability tracking and transaction management.

---

## Features

**Book Management**  
- Add, update, view, and delete books  
- Display availability status (Available / Borrowed)  

**Borrowing System**  
- Borrow a book if it’s available  
- Return a book and automatically update its availability  
- Track transactions (Borrowed / Returned) with timestamps  

**Search and Filtering**  
- Search books by title or author  

**User Roles (optional)**  
- Librarians can manage books  
- Regular users can only borrow/return  

## Technologies Used

| Technology | Purpose |
|-------------|----------|
| **Spring Boot** | Framework for building and running the app |
| **Spring MVC** | Handles routing and web logic |
| **Spring Data JPA (Hibernate)** |
| **Thymeleaf** | Server-side template engine for UI |
| **MySQL** | Database |
| **Bootstrap 5** | Front-end styling |

---

## Entities Overview

### Book
| Field | Type | Description |
|--------|------|-------------|
| `id` | Long | Primary key |
| `title` | String | Book title |
| `author` | String | Author name |
| `yearPublished` | String | Year of publication |
| `ISBN` | String | Unique identifier |
| `available` | boolean | Availability status |

### Transaction
| Field | Type | Description |
|--------|------|-------------|
| `id` | Long | Primary key |
| `book` | Book | The borrowed/returned book |
| `user` | User | The user who borrowed the book |
| `status` | String | BORROWED / RETURNED |
| `borrowedDate` | LocalDateTime | When the book was borrowed |
| `returnedDate` | LocalDateTime | When the book was returned |

---

