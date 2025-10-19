package ui;

import service.BookService;
import service.MemberService;

import java.util.Scanner;

public class LibraryApp {
    private final Scanner sc = new Scanner(System.in);
    private final BookService bookService = new BookService();
    private final MemberService memberService = new MemberService();


    public static void main(String[] args) {
        new LibraryApp().run();
    }


    public void run() {
        int choice;
        do {
            showMenu();
            choice = readInt("Nhập lựa chọn của bạn: ");
            handleChoice(choice);
        } while (choice != 0);
    }


    private void showMenu() {
        System.out.println("\n========= LIBRARY MANAGEMENT SYSTEM =========");
        System.out.println("1. Thêm sách mới");
        System.out.println("2. Xem danh sách sách");
        System.out.println("3. Tìm sách theo ID");
        System.out.println("4. Cập nhật số lượng sách");
        System.out.println("---------------------------------------------");
        System.out.println("5. Đăng ký độc giả");
        System.out.println("6. Danh sách độc giả");
        System.out.println("7. Xem chi tiết độc giả theo ID");
        System.out.println("---------------------------------------------");
        System.out.println("0. Thoát");
        System.out.println("=============================================");
    }


    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addBookUI();
                break;
            case 2:
                showAllBooksUI();
                break;
            case 3:
                findBookByIdUI();
                break;
            case 4:
                updateBookCopiesUI();
                break;
            case 5:
                registerMemberUI();
                break;
            case 6:
                showAllMembersUI();
                break;
            case 7:
                findMemberByIdUI();
                break;
            case 0:
                System.out.println("Tạm biệt!");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                break;
        }

    }

    //Book Functions

    private void addBookUI() {
        System.out.println("\nThêm sách mới");
        System.out.print("Nhập tên sách: ");
        String title = sc.nextLine();

        System.out.print("Nhập tác giả: ");
        String author = sc.nextLine();

        System.out.print("Nhập ISBN: ");
        String isbn = sc.nextLine();

        int total = readInt("Nhập số lượng: ");
        bookService.addBook(title, author, isbn, total);
    }


    private void showAllBooksUI() {
        System.out.println("\n📚 Danh sách sách hiện có:");
        bookService.showAllBooks();
    }


    private void findBookByIdUI() {
        int id = readInt("\nNhập ID sách cần tìm: ");
        bookService.findBookById(id);
    }


    private void updateBookCopiesUI() {
        int id = readInt("\nNhập ID sách cần cập nhật: ");
        int newCopies = readInt("Nhập tổng số bản mới: ");
        bookService.updateBookCopies(id, newCopies);
    }


    //Member Functions
    private void registerMemberUI() {
        System.out.println("\n🧍‍♂️ Đăng ký độc giả mới");
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Số điện thoại (tùy chọn): ");
        String phone = sc.nextLine();
        memberService.registerMember(name, email, phone);
    }

    private void showAllMembersUI() {
        System.out.println("\n📋 Danh sách độc giả:");
        memberService.showAllMembers();
    }

    private void findMemberByIdUI() {
        int id = readInt("\nNhập ID độc giả: ");
        memberService.findMemberById(id);
    }

    //Validate
    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int number = Integer.parseInt(sc.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ!");
            }
        }
    }
}
