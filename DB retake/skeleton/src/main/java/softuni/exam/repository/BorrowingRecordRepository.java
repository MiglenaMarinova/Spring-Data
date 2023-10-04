package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportBorrowingRecordsDto;
import softuni.exam.models.entity.BorrowingRecord;

import java.util.List;

// TODO:
@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {


   @Query("SELECT new softuni.exam.models.dto.ExportBorrowingRecordsDto " +
           "(b.title, b.author, r.borrowDate, concat(m.firstName, ' ', m.lastName)) " +
           "FROM BorrowingRecord r " +
           "JOIN Book b ON b.id = r.book.id " +
           "JOIN LibraryMember m ON m.id = r.member.id " +
           "WHERE r.book.genre = 'SCIENCE_FICTION' " +
           "ORDER BY r.borrowDate DESC")
    List<ExportBorrowingRecordsDto> findAllByBookAuthorAndMember();

}
