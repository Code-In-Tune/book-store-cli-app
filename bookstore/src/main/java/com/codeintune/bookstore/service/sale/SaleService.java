package com.codeintune.bookstore.service.sale;

import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;

import java.util.Optional;

public interface SaleService {

    Optional<AddSaleResponseDTO> registerSale(AddSaleRequestDTO dto);
}
