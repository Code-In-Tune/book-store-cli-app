package com.codeintune.bookstore.repository.sale.impl;

import com.codeintune.bookstore.model.sale.BookSale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


public class InMemorySaleRepositoryTest {

    private InMemorySaleRepository repository;

    @BeforeEach
    public void setup() {
        BookSale bookSale = new BookSale();
        bookSale.setBookId(1L);
        bookSale.setSaleId(1L);
        bookSale.setAmount(BigDecimal.ZERO);
        bookSale.setQuantity(1);
        bookSale.setDateSold(Instant.now());

        Map<Long, BookSale> sales = new HashMap<>();

        repository = new InMemorySaleRepository(sales);

        repository.save(bookSale);
    }

    @Test
    public void testFindAll(){
        Assertions.assertTrue(
                repository
                        .findAll()
                        .stream()
                        .map(BookSale::getSaleId)
                        .anyMatch(id -> id.equals(1L)
                        ));
    }

    @Test
    public void testFindById(){
        Assertions.assertTrue(repository.findById(1L).isPresent());
    }

    @Test
    public void testSave(){
        BookSale bookSale = new BookSale();
        bookSale.setBookId(1L);
        bookSale.setAmount(BigDecimal.ZERO);
        bookSale.setQuantity(1);
        bookSale.setDateSold(Instant.now());

        repository.save(bookSale);
        Assertions.assertTrue(repository.findById(2L).isPresent());
    }

    @Test
    public void testDelete(){
        BookSale bookSale = new BookSale();
        bookSale.setBookId(1L);
        bookSale.setAmount(BigDecimal.ZERO);
        bookSale.setQuantity(1);
        bookSale.setDateSold(Instant.now());

        repository.save(bookSale);
        Assertions.assertTrue(repository.findById(2L).isPresent());

        repository.deleteById(2L);

        Assertions.assertFalse(repository.findById(2L).isPresent());
    }
}
