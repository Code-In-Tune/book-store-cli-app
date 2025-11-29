package com.codeintune.bookstore.configuration.service.book;

import com.codeintune.bookstore.mapper.sale.SaleMapper;
import com.codeintune.bookstore.mapper.sale.impl.SaleMapperImpl;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.repository.sale.SaleRepository;
import com.codeintune.bookstore.service.sale.SaleService;
import com.codeintune.bookstore.service.sale.impl.SaleServiceImpl;
import com.codeintune.bookstore.utils.constants.service.SaleServiceConfigurationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SaleMapperImpl.class)
public class SaleServiceConfiguration {

    @Bean(SaleServiceConfigurationConstants.SALE_SERVICE)
    public SaleService saleService(
            BookRecordRepository bookRecordRepository,
            SaleRepository saleRepository,
            SaleMapper saleMapper) {
        return new SaleServiceImpl(saleRepository, saleMapper, bookRecordRepository);
    }
}
