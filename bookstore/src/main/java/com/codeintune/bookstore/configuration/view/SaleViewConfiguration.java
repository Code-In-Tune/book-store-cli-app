package com.codeintune.bookstore.configuration.view;

import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSalesResponseDTO;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import com.codeintune.bookstore.exception.handler.impl.CliExceptionHandler;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.service.sale.SaleService;
import com.codeintune.bookstore.utils.constants.view.ViewConfigurationConstants;
import com.codeintune.bookstore.view.SaleCliView;
import com.codeintune.bookstore.view.impl.SaleCliViewImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CliExceptionHandler.class)
public class SaleViewConfiguration {

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
