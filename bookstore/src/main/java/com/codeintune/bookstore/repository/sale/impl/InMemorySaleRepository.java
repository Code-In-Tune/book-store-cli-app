package com.codeintune.bookstore.repository.sale.impl;

import com.codeintune.bookstore.model.sale.BookSale;
import com.codeintune.bookstore.repository.sale.SaleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * In Memory Repository implementation for {@link  BookSale} records
 */

@RequiredArgsConstructor
public class InMemorySaleRepository implements SaleRepository {

    private final Map<Long, BookSale> sales;

    @Override
    public Optional<BookSale> findById(Long id) {
        return Optional.ofNullable(sales.get(id));
    }

    @Override
    public List<BookSale> findAll() {
        return sales.values().stream().toList();
    }

    @Override
    public BookSale save(BookSale bookSale) {
        return sales.put(bookSale.getSaleId(), bookSale);
    }

    @Override
    public void deleteById(Long id) {
        sales.remove(id);
    }
}
