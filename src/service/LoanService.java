package service;

import model.Book;
import model.Loan;
import model.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

public class LoanService {
    private LoanRepository loanRepo = new LoanRepository();
    private BookRepository bookRepo; private MemberRepository memberRepo;

    public LoanService(BookRepository bookRepo, MemberRepository memberRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
    }

    public String borrow(int memberId, int bookId) {
        Member member = memberRepo.findById(memberId);
        if (member == null) return "Không tìm thấy độc giả có ID = " + memberId;

        Book book = bookRepo.findById(bookId);
        if (book == null) return "Không tìm thấy sách có ID = " + bookId;

        if (book.getAvailableCopies() <= 0) return "Sách đã được mượn hết.";

        List<Loan> memberLoans = loanRepo.findByMember(memberId);
        if (memberLoans.size() >= 3) return "Độc giả đã mượn quá 3 cuốn.";

        Loan loan = loanRepo.add(memberId, bookId);
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        return "✅ Độc giả " + member.getName() + " đã mượn " + book.getTitle() + " thành công (LoanID=" + loan.getLoanId() + ")";
    }

    public String returnBook(int memberId, int bookId) {
        Loan loan = loanRepo.findActive(memberId, bookId);
        if (loan == null) {
            return "Không tìm thấy giao dịch mượn hợp lệ cho MemberID=" + memberId + ", BookID=" + bookId;
        }

        loan.setReturnDate(LocalDate.now());
        Book book = bookRepo.findById(bookId);
        if (book != null) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
        }

        return "Trả sách thành công!";
    }

    public List<Loan> getAll() {
        return loanRepo.getAll();
    }

    public void displayActive() {
        List<Loan> all = loanRepo.getAll();
        boolean has = false;
        for (Loan l : all) {
            if (!l.isReturned()) {
                l.displayInfo();
                has = true;
            }
        }
        if (!has) System.out.println("Không có sách nào đang mượn.");
    }
}
