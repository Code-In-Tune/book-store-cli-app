package com.codeintune.bookstore.repository.book;

import com.codeintune.bookstore.model.book.BookRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {

    Optional<BookRecord> findById(Long id);
    List<BookRecord> findAll();
    BookRecord save(BookRecord bookRecord);
    void deleteById(Long id);
}
