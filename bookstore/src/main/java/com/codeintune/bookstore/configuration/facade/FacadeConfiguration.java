package com.codeintune.bookstore.configuration.facade;

import com.codeintune.bookstore.facade.CliFacade;
import com.codeintune.bookstore.facade.impl.BookCliFacade;
import com.codeintune.bookstore.utils.constants.view.ViewConfigurationConstants;
import com.codeintune.bookstore.view.BookCliView;
import com.codeintune.bookstore.view.SaleCliView;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class FacadeConfiguration {



    @Bean
    public CliFacade bookCliFacade(
            @Qualifier(ViewConfigurationConstants.BOOK_CLI_VIEW)
            BookCliView bookCliView,
            @Qualifier(ViewConfigurationConstants.SALE_CLI_VIEW)
            SaleCliView saleCliView
    ){
        return new BookCliFacade(new Scanner(System.in), bookCliView, saleCliView);
    }
}
