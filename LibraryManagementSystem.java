import java.util.ArrayList;
import java.util.Scanner;

// One object represents one physical book.
class Book {
    private int id;
    private String title;
    private String author;
    private boolean borrowed;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        borrowed = false;
    }
    int getId() { return id; }
    boolean isBorrowed() { return borrowed; }
    void borrow() { borrowed = true; }
    void giveBack() { borrowed = false; }
    void display() {
        String status = "Available";
        if (borrowed) status = "Borrowed";
        System.out.println(id + " | " + title + " | "
                           + author + " | " + status);
    }
}

public class LibraryManagementSystem {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();

    // Read a nonempty line, including titles with spaces.
    static String readText(String message) {
        System.out.print(message);
        String text = input.nextLine().trim();
        while (text.isEmpty()) {
            System.out.print("Enter a nonempty value: ");
            text = input.nextLine().trim();
        }
        return text;
    }
    // Keep asking when the input is not a positive integer.
    static int readNumber(String message) {
        while (true) {
            try {
                int number = Integer.parseInt(readText(message));
                if (number > 0) return number;
            } catch (NumberFormatException e) {
                // The message below also handles invalid text.
            }
            System.out.println("Enter a positive integer.");
        }
    }
    // A simple loop finds a book by its unique ID.
    static Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }
    static boolean login(String role) {
        String name = readText("Username: ");
        String password = readText("Password: ");
        if (role.equals("Admin")) {
            return name.equals("admin") && password.equals("admin123");
        }
        return name.equals("user") && password.equals("user123");
    }
    static void addBook() {
        int id = readNumber("Book ID: ");
        if (findBook(id) != null) {
            System.out.println("Book ID already exists.");
            return;
        }
        String title = readText("Title: ");
        String author = readText("Author: ");
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }
    static void deleteBook() {
        Book book = findBook(readNumber("Book ID: "));
        if (book == null) System.out.println("Book not found.");
        else if (book.isBorrowed())
            System.out.println("Return the book before deletion.");
        else {
            books.remove(book);
            System.out.println("Book deleted successfully.");
        }
    }
    static void searchBook() {
        Book book = findBook(readNumber("Book ID: "));
        if (book == null) System.out.println("Book not found.");
        else book.display();
    }
    static void displayBooks() {
        if (books.isEmpty()) System.out.println("No books are present.");
        for (Book book : books) book.display();
    }
    static void borrowBook() {
        Book book = findBook(readNumber("Book ID: "));
        if (book == null) System.out.println("Book not found.");
        else if (book.isBorrowed())
            System.out.println("Book is not available.");
        else {
            book.borrow();
            System.out.println("Book borrowed successfully.");
        }
    }
    static void returnBook() {
        Book book = findBook(readNumber("Book ID: "));
        if (book == null) System.out.println("Book not found.");
        else if (!book.isBorrowed())
            System.out.println("Book was not borrowed.");
        else {
            book.giveBack();
            System.out.println("Book returned successfully.");
        }
    }
    static void checkAvailability() {
        Book book = findBook(readNumber("Book ID: "));
        if (book == null) System.out.println("Book not found.");
        else if (book.isBorrowed())
            System.out.println("Book is currently borrowed.");
        else System.out.println("Book is available.");
    }
    static void adminMenu() {
        int choice;
        do {
            System.out.println("\nADMIN MENU\n1. Add book\n2. Delete book"
                + "\n3. Search book\n4. Display books\n5. Logout");
            choice = readNumber("Choice: ");
            switch (choice) {
                case 1: addBook(); break;
                case 2: deleteBook(); break;
                case 3: searchBook(); break;
                case 4: displayBooks(); break;
                case 5: break;
                default: System.out.println("Invalid menu choice.");
            }
        } while (choice != 5);
    }
    static void userMenu() {
        int choice;
        do {
            System.out.println("\nUSER MENU\n1. Search book\n2. Display books"
                + "\n3. Borrow book\n4. Return book"
                + "\n5. Check availability\n6. Logout");
            choice = readNumber("Choice: ");
            switch (choice) {
                case 1: searchBook(); break;
                case 2: displayBooks(); break;
                case 3: borrowBook(); break;
                case 4: returnBook(); break;
                case 5: checkAvailability(); break;
                case 6: break;
                default: System.out.println("Invalid menu choice.");
            }
        } while (choice != 6);
    }
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nLIBRARY MANAGEMENT SYSTEM"
                + "\n1. Admin login\n2. User login\n3. Exit");
            choice = readNumber("Choice: ");
            if (choice == 1) {
                if (login("Admin")) adminMenu();
                else System.out.println("Invalid login.");
            } else if (choice == 2) {
                if (login("User")) userMenu();
                else System.out.println("Invalid login.");
            } else if (choice != 3) {
                System.out.println("Invalid menu choice.");
            }
        } while (choice != 3);
        input.close();
        System.out.println("Goodbye.");
    }
}
