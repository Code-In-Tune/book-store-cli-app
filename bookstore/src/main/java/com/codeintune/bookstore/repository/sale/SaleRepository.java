package com.codeintune.bookstore.repository.sale;

import com.codeintune.bookstore.model.sale.BookSale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {

    Optional<BookSale> findById(Long id);
    List<BookSale> findAll();
    BookSale save(BookSale bookSale);
    void delete(Long id);
}
