package com.codeintune.bookstore.service.sale;

import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
/**
 * Contract defining how to gather input for sale domain
 */
public interface SaleInputService {

    AddSaleRequestDTO buildAddSaleRequestDTO();
}
