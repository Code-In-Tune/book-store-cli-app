package com.codeintune.bookstore.view.impl;

import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.service.sale.SaleService;
import com.codeintune.bookstore.utils.constants.exception.ValidationExceptionConstants;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.view.SaleCliView;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class SaleCliViewImpl implements SaleCliView {

    private final SaleService saleService;
    private final ExceptionHandler exceptionHandler;
    private final SaleInputService saleInputService;
    private final ResponseFormatter<AddSaleResponseDTO> addSaleResponseDTOResponseFormatter;

    @Override
    public String registerSale() {
        try{
            AddSaleRequestDTO requestDTO = saleInputService.buildAddSaleRequestDTO();

            Optional<AddSaleResponseDTO> responseDTO = saleService.registerSale(requestDTO);

            return addSaleResponseDTOResponseFormatter.format(responseDTO.orElseThrow(() -> {
                ValidationErrorDTO errorDTO = new ValidationErrorDTO();
                errorDTO.setMessage(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(requestDTO.getBookRecordId()));
                return new ValidationException(errorDTO);
            }));
        } catch (Exception exception){
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }
}
