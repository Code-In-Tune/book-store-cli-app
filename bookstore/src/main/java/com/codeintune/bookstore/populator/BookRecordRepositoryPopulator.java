package com.codeintune.bookstore.populator;

import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class BookRecordRepositoryPopulator implements ApplicationListener<ContextRefreshedEvent> {

    private final BookRecordRepository repository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(repository.findAll().isEmpty()){
            BookRecord bookRecord1 = new BookRecord();
            bookRecord1.setBookId(1L);
            bookRecord1.setQuantity(1);
            bookRecord1.setPrice(BigDecimal.valueOf(10.50));
            bookRecord1.setAvailability(Availability.IN_STOCK);

            BookRecord bookRecord2 = new BookRecord();
            bookRecord2.setBookId(2L);
            bookRecord2.setQuantity(1);
            bookRecord2.setPrice(BigDecimal.valueOf(10.50));
            bookRecord2.setAvailability(Availability.IN_STOCK);

            repository.save(bookRecord1);
            repository.save(bookRecord2);
        }
    }
}
