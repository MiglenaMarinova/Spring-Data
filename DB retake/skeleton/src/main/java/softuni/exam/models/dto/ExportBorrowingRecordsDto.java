package softuni.exam.models.dto;

import java.time.LocalDate;

public class ExportBorrowingRecordsDto {

    private String bookTitle;
    private String bookAuthor;
    private LocalDate dateBorrowed;
    private String memberFullName;

    public ExportBorrowingRecordsDto() {
    }

    public ExportBorrowingRecordsDto(String bookTitle, String bookAuthor, LocalDate dateBorrowed, String memberFullName) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.dateBorrowed = dateBorrowed;
        this.memberFullName = memberFullName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(LocalDate dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getMemberFullName() {
        return memberFullName;
    }

    public void setMemberFullName(String memberFullName) {
        this.memberFullName = memberFullName;
    }
}
