import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int bookID;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(int bookID, String title, String author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Title: %s, Author: %s, Borrowed: %b",
                bookID, title, author, isBorrowed);
    }
}

public class LibraryManagementSystem {
    private static final ArrayList<Book> books = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int nextBookID = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. View All Books");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> viewAllBooks();
                case 4 -> borrowBook();
                case 5 -> returnBook();
                case 6 -> {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        books.add(new Book(nextBookID++, title, author));
        System.out.println("Book added successfully.");
    }

    private static void removeBook() {
        System.out.print("Enter book ID to remove: ");
        int bookID = scanner.nextInt();

        for (Book book : books) {
            if (book.getBookID() == bookID) {
                books.remove(book);
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\nBooks in Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void borrowBook() {
        System.out.print("Enter book ID to borrow: ");
        int bookID = scanner.nextInt();

        for (Book book : books) {
            if (book.getBookID() == bookID) {
                if (book.isBorrowed()) {
                    System.out.println("Book is already borrowed.");
                } else {
                    book.borrow();
                    System.out.println("Book borrowed successfully.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int bookID = scanner.nextInt();

        for (Book book : books) {
            if (book.getBookID() == bookID) {
                if (book.isBorrowed()) {
                    book.returnBook();
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book is not borrowed.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
