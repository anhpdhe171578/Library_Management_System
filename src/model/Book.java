package model;

public class Book extends LibraryItem {
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;

    public Book(int id, String title, String author, String isbn, int totalCopies) {
        super(id, title, "AVAILABLE");
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    @Override
    public void displayInfo() {
        System.out.printf("ID: %d | Title: %s | Author: %s | ISBN: %s | Available: %d/%d%n",
                id, title, author, isbn, availableCopies, totalCopies);
    }
}
