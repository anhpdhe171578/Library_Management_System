package service;

import model.Book;
import repository.BookRepository;
import java.util.List;

public class BookService {
    private final BookRepository bookRepository = new BookRepository();

    public void addBook(String title, String author, String isbn, int totalCopies) {
        Book book = bookRepository.addBook(title, author, isbn, totalCopies);
        if (book != null) {
            System.out.println("Thêm sách thành công!");
        }
    }

    public void showAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Chưa có sách nào trong thư viện.");
        } else {
            System.out.println("Danh sách sách:");
            for (Book b : books) b.displayInfo();
        }
    }

    public void findBookById(int id) {
        Book b = bookRepository.getBookById(id);
        if (b != null) b.displayInfo();
        else System.out.println("Không tìm thấy sách có ID = " + id);
    }


    public void updateBookCopies(int id, int newTotalCopies) {
        boolean success = bookRepository.updateBookCopies(id, newTotalCopies);
        System.out.println(success ? "Cập nhật thành công!" : "Cập nhật thất bại!");
    }
}
