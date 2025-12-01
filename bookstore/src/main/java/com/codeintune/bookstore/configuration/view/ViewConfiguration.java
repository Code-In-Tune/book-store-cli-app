package com.codeintune.bookstore.configuration.view;

import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBooksResponseDTO;
import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSalesResponseDTO;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import com.codeintune.bookstore.exception.handler.impl.CliExceptionHandler;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.service.book.BookService;
import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.service.sale.SaleService;
import com.codeintune.bookstore.utils.constants.view.ViewConfigurationConstants;
import com.codeintune.bookstore.view.BookCliView;
import com.codeintune.bookstore.view.SaleCliView;
import com.codeintune.bookstore.view.impl.BookCliViewImpl;
import com.codeintune.bookstore.view.impl.SaleCliViewImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CliExceptionHandler.class)
public class ViewConfiguration {

    @Bean(ViewConfigurationConstants.BOOK_CLI_VIEW)
    public BookCliView bookCliView(
            BookService bookService,
            ExceptionHandler handler,
            BookInputService bookInputService,
            ResponseFormatter<AddBookResponseDTO> addBookResponseDTOResponseFormatter,
            ResponseFormatter<GetBookResponseDTO> getBookResponseDTOResponseFormatter,
            ResponseFormatter<GetBooksResponseDTO> getBooksResponseDTOResponseFormatter

    ) {
        return new BookCliViewImpl(bookService,
                handler,
                bookInputService,
                addBookResponseDTOResponseFormatter,
                getBookResponseDTOResponseFormatter,
                getBooksResponseDTOResponseFormatter
        );
    }

    @Bean(ViewConfigurationConstants.SALE_CLI_VIEW)
    public SaleCliView saleCliView(
            SaleService saleService,
            ExceptionHandler handler,
            SaleInputService saleInputService,
            ResponseFormatter<AddSaleResponseDTO> addSaleResponseDTOResponseFormatter,
            ResponseFormatter<GetSalesResponseDTO> getSalesResponseDTOResponseFormatter
    ){
        return new SaleCliViewImpl(saleService, handler, saleInputService, addSaleResponseDTOResponseFormatter, getSalesResponseDTOResponseFormatter);
    }
}
