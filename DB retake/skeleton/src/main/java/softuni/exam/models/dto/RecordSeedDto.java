package softuni.exam.models.dto;

import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.LibraryMember;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordSeedDto {

    //•	borrow date - a date in the "yyyy-MM-dd" format.
//•	return date - a date in the "yyyy-MM-dd" format.
//•	remarks - can be used to store any relevant information and might be helpful for tracking and managing the borrowing records.
// Accepts char sequence (between 3 to 100 inclusive). Can be nullable.
//•	Constraint: The borrowing_records table has a relation with books table.
//•	Constraint: The borrowing_records table has a relation with library_members table.
    @XmlElement(name = "borrow_date")
    @NotNull
    @NotEmpty
    private String borrowDate;
    @XmlElement(name = "return_date")
    @NotNull
    @NotEmpty
    private String returnDate;
    @XmlElement(name = "book")
    @NotNull
    private BookByTitleDto book;
    @XmlElement(name = "member")
    @NotNull
    private MemberByIdDto member;
    @XmlElement(name = "remarks")
    @Size(min = 3, max = 100)
    private String remarks;

    public RecordSeedDto() {
    }

    public RecordSeedDto(String borrowDate, String returnDate, BookByTitleDto book, MemberByIdDto member, String remarks) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.book = book;
        this.member = member;
        this.remarks = remarks;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BookByTitleDto getBook() {
        return book;
    }

    public void setBook(BookByTitleDto book) {
        this.book = book;
    }

    public MemberByIdDto getMember() {
        return member;
    }

    public void setMember(MemberByIdDto member) {
        this.member = member;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
