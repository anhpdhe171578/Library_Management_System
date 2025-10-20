package model;

import java.time.LocalDate;

public class Loan {
    private int loanId;
    private int memberId;
    private int bookId;
    private LocalDate loanDate;
    private LocalDate returnDate; // null nếu chưa trả

    public Loan(int loanId, int memberId, int bookId) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void displayInfo() {
        System.out.printf("LoanID: %d | MemberID: %d | BookID: %d | LoanDate: %s | ReturnDate: %s%n",
                loanId, memberId, bookId,
                loanDate,
                (returnDate == null ? "Chưa trả" : returnDate.toString()));
    }
}
