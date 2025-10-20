package ui;

import model.Book;
import model.Member;
import service.BookService;
import service.MemberService;
import service.LoanService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryApp {
    private Scanner sc = new Scanner(System.in);
    private BookService bookService = new BookService();
    private MemberService memberService = new MemberService();
    private LoanService loanService = new LoanService(bookService.getRepo(), memberService.getRepo());

    public void start() {
        int choice = -1;

        while (choice != 0) {
            showMenu();
            choice = inputInt("Nhập lựa chọn của bạn: ", 0, 9);

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    findBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    addMember();
                    break;
                case 6:
                    listMembers();
                    break;
                case 7:
                    borrowBook();
                    break;
                case 8:
                    returnBook();
                    break;
                case 9:
                    loanService.displayActive();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n========= LIBRARY MANAGEMENT SYSTEM =========");
        System.out.println("1. Thêm sách mới");
        System.out.println("2. Xem danh sách sách");
        System.out.println("3. Tìm sách theo ID");
        System.out.println("4. Cập nhật số lượng sách");
        System.out.println("5. Đăng ký độc giả");
        System.out.println("6. Danh sách độc giả");
        System.out.println("7. Mượn sách");
        System.out.println("8. Trả sách");
        System.out.println("9. Danh sách sách đang mượn");
        System.out.println("0. Thoát");
        System.out.println("============================================");
    }

    // ================== VALIDATED INPUT ==================
    private int inputInt(String msg, int min, int max) {
        int num;
        while (true) {
            System.out.print(msg);
            try {
                num = sc.nextInt();
                sc.nextLine();
                if (num < min || num > max) {
                    System.out.println("Nhập trong khoảng " + min + " - " + max + "!");
                } else return num;
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                sc.nextLine();
            }
        }
    }

    private int inputPositiveInt(String msg) {
        int num;
        while (true) {
            System.out.print(msg);
            try {
                num = sc.nextInt();
                sc.nextLine();
                if (num <= 0) System.out.println("Số phải lớn hơn 0!");
                else return num;
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
                sc.nextLine();
            }
        }
    }

    private String inputLine(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    // ================== CHỨC NĂNG ==================

    private void addBook() {
        String title = inputLine("Tên sách: ");
        String author = inputLine("Tác giả: ");
        String isbn = inputLine("ISBN: ");
        String totalStr = inputLine("Tổng số bản: ");

        StringBuilder errors = new StringBuilder();

        if (title.isEmpty()) errors.append("- Tên sách không được để trống\n");
        if (author.isEmpty()) errors.append("- Tác giả không được để trống\n");
        if (isbn.isEmpty()) errors.append("- ISBN không được để trống\n");
        else if (bookService.findByIsbn(isbn) != null)
            errors.append("- ISBN đã tồn tại\n");

        int total = 0;
        try {
            total = Integer.parseInt(totalStr);
            if (total <= 0) errors.append("- Tổng số bản phải > 0\n");
        } catch (NumberFormatException e) {
            errors.append("- Tổng số bản phải là số hợp lệ\n");
        }

        if (errors.length() > 0) {
            System.out.println("Lỗi nhập dữ liệu:");
            System.out.println(errors);
            return;
        }

        Book book = new Book(0, title, author, isbn, total);
        if (bookService.add(book))
            System.out.println("Thêm sách thành công!");
        else
            System.out.println("Lỗi thêm sách!");
    }

    private void addMember() {
        String name = inputLine("Tên độc giả: ");
        String email = inputLine("Email: ");
        String phone = inputLine("Số điện thoại: ");

        StringBuilder errors = new StringBuilder();

        if (name.isEmpty()) errors.append("- Tên độc giả không được để trống\n");

        if (email.isEmpty()) {
            errors.append("- Email không được để trống\n");
        } else if (!email.matches("^[a-z0-9]+@[a-z]+\\.[a-z]+$")) {
            errors.append("- Email không hợp lệ\n");
        } else if (memberService.findByEmail(email) != null) {
            errors.append("- Email đã tồn tại\n");
        }

        if (errors.length() > 0) {
            System.out.println("Lỗi nhập dữ liệu:");
            System.out.println(errors);
            return;
        }

        Member member = new Member(0, name, email, phone);
        if (memberService.add(member))
            System.out.println("Đăng ký độc giả thành công!");
        else
            System.out.println("Lỗi đăng ký độc giả!");
    }

    private void listBooks() {
        if (bookService.getAll().isEmpty()) {
            System.out.println("Chưa có sách nào trong hệ thống.");
            return;
        }
        for (Book b : bookService.getAll()) b.displayInfo();
    }

    private void findBook() {
        int id = inputPositiveInt("Nhập ID sách: ");
        Book book = bookService.findById(id);
        if (book != null) book.displayInfo();
        else System.out.println("Không tìm thấy sách!");
    }

    private void updateBook() {
        int id = inputPositiveInt("Nhập ID sách: ");
        int total = inputPositiveInt("Tổng số bản mới: ");

        if (bookService.updateQuantity(id, total))
            System.out.println("Cập nhật thành công!");
        else
            System.out.println("Không tìm thấy sách!");
    }

    private void listMembers() {
        if (memberService.getAll().isEmpty()) {
            System.out.println("Chưa có độc giả nào.");
            return;
        }
        for (Member m : memberService.getAll()) m.displayInfo();
    }

    private void borrowBook() {
        int memberId = inputPositiveInt("Nhập ID độc giả: ");
        int bookId = inputPositiveInt("Nhập ID sách: ");
        System.out.println(loanService.borrow(memberId, bookId));
    }

    private void returnBook() {
        int memberId = inputPositiveInt("Nhập ID độc giả: ");
        int bookId = inputPositiveInt("Nhập ID sách: ");
        System.out.println(loanService.returnBook(memberId, bookId));
    }

    public static void main(String[] args) {
        new LibraryApp().start();
    }
}
