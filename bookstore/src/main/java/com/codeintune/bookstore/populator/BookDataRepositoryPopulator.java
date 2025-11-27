package com.codeintune.bookstore.populator;

import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookDataRepositoryPopulator implements ApplicationListener<ContextRefreshedEvent> {

    private final BookDataRepository repository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(repository.findAll().isEmpty()){

            Book book1 = new Book();
            book1.setAuthor("Lewis Carroll");
            book1.setIsbn("MOCK_ISBN");
            book1.setTitle("Alice In Wonderland");
            book1.setPublisher("MOCK_PUBLISHER");

            Book book2 = new Book();
            book2.setAuthor("Lewis Carroll");
            book2.setIsbn("MOCK_ISBN");
            book2.setTitle("Alice Through The Looking Glass");
            book2.setPublisher("MOCK_PUBLISHER");



            repository.save(book1);
            repository.save(book2);
        }
    }
}
