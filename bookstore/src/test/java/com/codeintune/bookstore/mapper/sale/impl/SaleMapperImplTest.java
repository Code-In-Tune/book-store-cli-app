package com.codeintune.bookstore.mapper.sale.impl;

import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.mapper.sale.SaleMapper;
import com.codeintune.bookstore.model.sale.BookSale;
import com.codeintune.bookstore.testutils.seeder.sale.data.BookSaleSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.AddSaleResponseDTOSeeder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = SaleMapperImpl.class)
@ExtendWith(SpringExtension.class)
class SaleMapperImplTest {

    @Autowired
    private SaleMapper saleMapper;

    @Test
    void toEntityTest() {
        AddSaleResponseDTO response = AddSaleResponseDTOSeeder.generateResponse();
        BookSale entity = BookSaleSeeder.generateEntity();

        BookSale entityResult = saleMapper.toEntity(response);

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals(entity.getQuantity(), entityResult.getQuantity(), "Quantity do no match"),
                () -> Assertions.assertEquals(entity.getAmount(), entityResult.getAmount(), "Amount do not match")
        );
    }
}