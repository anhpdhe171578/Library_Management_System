package repository;

import model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public boolean addBook(Book book) {
        // clone để gán id tự động
        Book newBook = new Book(nextId++, book.getTitle(), book.getAuthor(), book.getIsbn(), book.getTotalCopies());
        books.add(newBook);
        return true;
    }

    public List<Book> getAll() {
        return books;
    }

    public Book findById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public Book findByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) return b;
        }
        return null;
    }
}
