package repository;

import model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private final List<Book> books = new ArrayList<>();
    private int nextId = 1;


    public Book addBook(String title, String author, String isbn, int totalCopies) {

        for (Book b : books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                System.out.println("ISBN đã tồn tại! Không thể thêm sách trùng ISBN.");
                return null;
            }
        }

        Book newBook = new Book(nextId++, title, author, isbn, totalCopies);
        books.add(newBook);
        return newBook;
    }


    public List<Book> getAllBooks() {
        return books;
    }


    public Book getBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }





    public boolean updateBookCopies(int id, int newTotalCopies) {
        Book book = getBookById(id);
        if (book != null) {
            if (newTotalCopies < book.getTotalCopies()) {
                System.out.println("Không thể giảm tổng số bản sách nhỏ hơn hiện tại!");
                return false;
            }
            int difference = newTotalCopies - book.getTotalCopies();
            book.setAvailableCopies(book.getAvailableCopies() + difference);
            return true;
        }
        return false;
    }

    // delete
    public boolean removeBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
    }
}