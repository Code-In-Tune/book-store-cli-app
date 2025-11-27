package com.codeintune.bookstore.service.book.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.utils.constants.validator.InputFieldConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class BookInputServiceImpl implements BookInputService {

    private final static Scanner SCANNER = new Scanner(System.in);

    private final Validator<InputField> inputNameValidator;
    private final Validator<InputField> inputIsbnValidator;
    private final Validator<InputField> inputQuantityValidator;
    private final Validator<InputField> inputPriceValidator;

    @Override
    public AddBookRequestDTO buildBookRequestDTO() {
        String title = SCANNER.nextLine();

        InputField titleField = new InputField();
        titleField.setField(InputFieldConstants.TITLE);
        titleField.setValue(title);

        inputNameValidator.validate(titleField);

        String author = SCANNER.nextLine();

        InputField authorField = new InputField();
        authorField.setField(InputFieldConstants.AUTHOR);
        authorField.setValue(author);

       inputNameValidator.validate(authorField);

        String isbn = SCANNER.nextLine();

        InputField isbnField = new InputField();
        isbnField.setField(InputFieldConstants.ISBN);
        isbnField.setValue(isbn);

        inputIsbnValidator.validate(isbnField);

        String quantity = SCANNER.nextLine();

        InputField quantityField = new InputField();
        quantityField.setField(InputFieldConstants.QUANTITY);
        quantityField.setValue(quantity);

        inputQuantityValidator.validate(quantityField);


        String price = SCANNER.nextLine();

        InputField priceField = new InputField();
        priceField.setField(InputFieldConstants.PRICE);
        priceField.setValue(price);

        inputPriceValidator.validate(priceField);

        String publisher = SCANNER.nextLine();

        InputField publisherField = new InputField();
        publisherField.setField(InputFieldConstants.PUBLISHER);
        publisherField.setValue(publisher);

        inputNameValidator.validate(publisherField);

        AddBookRequestDTO bookRequestDTO = new AddBookRequestDTO();
        bookRequestDTO.setTitle(title);
        bookRequestDTO.setAuthor(author);
        bookRequestDTO.setIsbn(isbn);
        bookRequestDTO.setQuantity(quantity);
        bookRequestDTO.setPrice(price);
        bookRequestDTO.setPublisher(publisher);

        return bookRequestDTO;
    }
}
