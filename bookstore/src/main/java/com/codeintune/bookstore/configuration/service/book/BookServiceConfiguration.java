package com.codeintune.bookstore.configuration.service.book;

import com.codeintune.bookstore.configuration.repository.RepositoryConfiguration;
import com.codeintune.bookstore.mapper.book.data.BookMapper;
import com.codeintune.bookstore.mapper.book.data.impl.BookMapperImpl;
import com.codeintune.bookstore.mapper.book.record.BookRecordMapper;
import com.codeintune.bookstore.mapper.book.record.impl.BookRecordMapperImpl;
import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.service.book.BookService;
import com.codeintune.bookstore.service.book.impl.BookServiceImpl;
import com.codeintune.bookstore.utils.constants.service.BookServiceConfigurationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        BookRecordMapperImpl.class,
        BookMapperImpl.class,
        RepositoryConfiguration.class
})
public class BookServiceConfiguration {

    @Bean(BookServiceConfigurationConstants.BOOK_SERVICE)
    public BookService bookService(
            BookRecordMapper recordMapper,
            BookMapper bookMapper,
            BookDataRepository bookDataRepository,
            BookRecordRepository bookRecordRepository
            ) {
        return new BookServiceImpl(recordMapper, bookMapper, bookDataRepository, bookRecordRepository);
    }

}
