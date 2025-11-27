package com.codeintune;

import com.codeintune.bookstore.facade.CliFacade;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        var context = new AnnotationConfigApplicationContext();
        context.scan("com.codeintune");
        context.refresh();
        context.getBean(CliFacade.class).startStore();
    }
}
