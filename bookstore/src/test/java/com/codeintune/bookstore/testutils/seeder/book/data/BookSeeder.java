package com.codeintune.bookstore.testutils.seeder.book.data;

import com.codeintune.bookstore.model.book.Book;

public class BookSeeder {


    public static Book generateBook(){
        Book book = new Book();

        book.setBookId(1L);

        book.setTitle("Book Title");
        book.setAuthor("Book Author");
        book.setIsbn("Book ISBN");
        book.setPublisher("Book Publisher");

        return book;
    }
}
