# Library Management System

A simple Java console microproject using two classes, basic OOP, and an ArrayList. No database, JDBC, or multithreading is required.

## Features

- Admin: add, delete, search, and display books
- User: search, display, borrow, return, and check availability
- Unique positive book IDs and basic input validation
- Prevents borrowing unavailable books and deleting borrowed books

## Run

Install JDK 8 or later. Open a terminal in the project folder:

```sh
javac LibraryManagementSystem.java
java LibraryManagementSystem
```

## Demonstration accounts

| Role | Username | Password |
| --- | --- | --- |
| Admin | admin | admin123 |
| User | user | user123 |

These fixed credentials are only for this classroom demonstration.

## Quick demonstration

1. Log in as Admin and add book ID 101, title Java Programming, author Herbert Schildt.
2. Display or search for book 101, then log out.
3. Log in as User, borrow book 101, and check availability.
4. Return book 101 and check availability again.
5. Log in as Admin, delete book 101, then exit.

Records are stored in memory and reset when the program closes. Search is by book ID. There is one fixed User account; individual borrowers are not tracked.

## Verification

The saved GitHub Java source was downloaded, matched against the report code, compiled, and run locally. Checks passed for the main operations, invalid login, duplicate IDs, blank input, invalid numeric input, invalid menu choices, unavailable books, unborrowed returns, borrowed-book deletion, and catalog reset after restart.
