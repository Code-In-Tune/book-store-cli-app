package com.codeintune.bookstore.repository.sale.impl;

import com.codeintune.bookstore.model.sale.BookSale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InMemorySaleRepositoryTest {

    private Map<Long, BookSale> sales;
    private InMemorySaleRepository repository;

    @BeforeAll
    public void setup() {
        BookSale bookSale = new BookSale();
        bookSale.setBookId(1L);
        bookSale.setSaleId(1L);
        bookSale.setAmount(BigDecimal.ZERO);
        bookSale.setQuantity(1);
        bookSale.setDateSold(Instant.now());

        sales = new HashMap<>();
        sales.put(bookSale.getSaleId(),bookSale);

        repository = new InMemorySaleRepository(sales);
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
        bookSale.setSaleId(2L);
        bookSale.setAmount(BigDecimal.ZERO);
        bookSale.setQuantity(1);
        bookSale.setDateSold(Instant.now());

        repository.save(bookSale);
        Assertions.assertTrue(repository.findById(2L).isPresent());

        // cleanup

        sales.remove(bookSale.getSaleId());
    }

    @Test
    public void testDelete(){
        BookSale bookSale = new BookSale();
        bookSale.setBookId(1L);
        bookSale.setSaleId(2L);
        bookSale.setAmount(BigDecimal.ZERO);
        bookSale.setQuantity(1);
        bookSale.setDateSold(Instant.now());

        repository.save(bookSale);
        Assertions.assertTrue(repository.findById(2L).isPresent());

        repository.deleteById(2L);

        Assertions.assertFalse(repository.findById(2L).isPresent());
    }
}
