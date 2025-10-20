package service;

import model.Book;
import repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepo = new BookRepository();
    public BookRepository getRepo() {
        return bookRepo;
    }
    public boolean add(Book book) {
        if (bookRepo.findByIsbn(book.getIsbn()) != null) {
            return false;
        }
        return bookRepo.addBook(book);
    }

    public List<Book> getAll() {
        return bookRepo.getAll();
    }

    public Book findById(int id) {
        return bookRepo.findById(id);
    }

    public boolean updateQuantity(int id, int totalCopies) {
        Book book = bookRepo.findById(id);
        if (book == null) return false;
        book.setTotalCopies(totalCopies);
        return true;
    }

    public Book findByIsbn(String isbn) {
        for (Book b : bookRepo.getAll()) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                return b;
            }
        }
        return null;
    }

}
