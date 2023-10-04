package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportBorrowingRecordsDto;
import softuni.exam.models.dto.RecordsRootSeedDto;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// TODO: Implement all methods
@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    public static final String RECORDS_FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";

    private final BorrowingRecordRepository borrowingRecordRepository;

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository,
                                       XmlParser xmlParser, ValidationUtil validationUtil,
                                       ModelMapper modelMapper, BookRepository bookRepository,
                                       LibraryMemberRepository libraryMemberRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(RECORDS_FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        RecordsRootSeedDto recordsRootSeedDto = xmlParser.fromFile(RECORDS_FILE_PATH, RecordsRootSeedDto.class);

        recordsRootSeedDto.getRecords().stream()
                .filter(recordSeedDto -> {
                    boolean isValid = validationUtil.isValid(recordSeedDto);
                    boolean isValidBook = bookRepository.findAllByTitle(recordSeedDto.getBook().getTitle()).isPresent();
                    boolean isValidMember = libraryMemberRepository.findById(recordSeedDto.getMember().getId()).isPresent();

                    if (isValidBook && isValidMember && isValid){
                        sb.append(String.format("Successfully imported borrowing record %s - %s",
                                recordSeedDto.getBook().getTitle(),
                                recordSeedDto.getBorrowDate())).append(System.lineSeparator());
                    }else{
                        sb.append("Invalid borrowing record").append(System.lineSeparator());
                    }
                    return (isValidBook && isValidMember && isValid);
                }).map(recordSeedDto -> {
                    BorrowingRecord borrowingRecord = modelMapper.map(recordSeedDto, BorrowingRecord.class);
                    borrowingRecord.setBook(bookRepository.findAllByTitle(recordSeedDto.getBook().getTitle()).get());
                    borrowingRecord.setLibraryMember(libraryMemberRepository.findById(recordSeedDto.getMember().getId()).get());

                    return borrowingRecord;
                }).forEach(borrowingRecordRepository::save);




        return sb.toString().trim();
    }

    @Override
    public String exportBorrowingRecords() {

        StringBuilder sb = new StringBuilder();

         borrowingRecordRepository.findAllByBookAuthorAndMember().stream()
                .forEach(r -> sb.append(String.format("Book title: %s%n" +
                        "*Book author: %s%n" +
                        "**Date borrowed: %s%n" +
                        "***Borrowed by: %s",
                        r.getBookTitle(),
                        r.getBookAuthor(),
                        r.getDateBorrowed(),
                        r.getMemberFullName())).append(System.lineSeparator()));


        return sb.toString().trim();
    }
}
