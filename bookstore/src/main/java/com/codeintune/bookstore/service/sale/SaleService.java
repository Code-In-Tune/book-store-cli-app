package com.codeintune.bookstore.service.sale;

import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSalesResponseDTO;

import java.util.Optional;
/**
 * Contract defining fundamental CRUD operations on sales
 */
public interface SaleService {

    Optional<AddSaleResponseDTO> registerSale(AddSaleRequestDTO dto);
    GetSalesResponseDTO getSales();

}
