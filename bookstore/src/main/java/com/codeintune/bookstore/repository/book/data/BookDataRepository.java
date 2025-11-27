package com.codeintune.bookstore.repository.book.data;

import com.codeintune.bookstore.model.book.Book;

import java.util.List;
import java.util.Optional;

public interface BookDataRepository {

    Optional<Book>  findById(Long id);
    List<Book>   findAll();
    Book  save(Book book);
    void deleteById(Long id);
    List<Book> findAllByTitle(String title);
    List<Book> findAllByAuthor(String author);
}
