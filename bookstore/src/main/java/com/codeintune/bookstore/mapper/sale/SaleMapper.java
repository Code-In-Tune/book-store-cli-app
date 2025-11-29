package com.codeintune.bookstore.mapper.sale;

import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.model.sale.BookSale;

public interface SaleMapper {

    BookSale toEntity(AddSaleResponseDTO dto);
}
