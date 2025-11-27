package com.codeintune.bookstore.repository.sale.impl;

import com.codeintune.bookstore.model.sale.BookSale;
import com.codeintune.bookstore.repository.sale.SaleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In Memory Repository implementation for {@link  BookSale} records
 */

@RequiredArgsConstructor
public class InMemorySaleRepository implements SaleRepository {

    private final Map<Long, BookSale> sales;
    private final AtomicLong nextId = new AtomicLong(0L);

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
        bookSale.setSaleId(nextId.incrementAndGet());
        return sales.put(bookSale.getSaleId(), bookSale);
    }

    @Override
    public void deleteById(Long id) {
        sales.remove(id);
    }
}
