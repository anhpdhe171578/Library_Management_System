package repository;

import model.Loan;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {
    private List<Loan> loans = new ArrayList<>();
    private int nextId = 1;

    public Loan add(int memberId, int bookId) {
        Loan loan = new Loan(nextId++, memberId, bookId);
        loans.add(loan);
        return loan;
    }

    public List<Loan> getAll() {
        return loans;
    }

    public List<Loan> findByMember(int memberId) {
        List<Loan> result = new ArrayList<>();
        for (Loan l : loans) {
            if (l.getMemberId() == memberId) result.add(l);
        }
        return result;
    }

    public Loan findActive(int memberId, int bookId) {
        for (Loan l : loans) {
            if (l.getMemberId() == memberId && l.getBookId() == bookId && !l.isReturned()) {
                return l;
            }
        }
        return null;
    }
}
