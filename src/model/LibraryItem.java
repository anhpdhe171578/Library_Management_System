package model;

public abstract class LibraryItem {
    protected int id;
    protected String title;
    protected String status;

    public LibraryItem(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void displayInfo();
}
