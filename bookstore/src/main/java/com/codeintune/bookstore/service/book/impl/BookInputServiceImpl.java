package com.codeintune.bookstore.service.book.impl;

import com.codeintune.bookstore.dto.book.*;
import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.utils.constants.service.BookInputServiceConstants;
import com.codeintune.bookstore.utils.constants.validator.InputFieldConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class BookInputServiceImpl implements BookInputService {

    private final BookStoreInputReader bookStoreInputReader;
    private final BookStorePrinter bookStorePrinter;

    private final Validator<InputField> inputNameValidator;
    private final Validator<InputField> inputIsbnValidator;
    private final Validator<InputField> inputQuantityValidator;
    private final Validator<InputField> inputPriceValidator;
    private final Validator<InputField> inputIdValidator;

    @Override
    public AddBookRequestDTO buildBookRequestDTO() {
        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_TITLE_MESSAGE);
        bookStorePrinter.printQuitOption();

        String title = bookStoreInputReader.readNextLineWithQuitOption();

        InputField titleField = new InputField();
        titleField.setField(InputFieldConstants.TITLE);
        titleField.setValue(title);

        inputNameValidator.validate(titleField);

        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_AUTHOR_MESSAGE);
        bookStorePrinter.printQuitOption();

        String author = bookStoreInputReader.readNextLineWithQuitOption();

        InputField authorField = new InputField();
        authorField.setField(InputFieldConstants.AUTHOR);
        authorField.setValue(author);

       inputNameValidator.validate(authorField);

       bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_ISBN_MESSAGE);
       bookStorePrinter.printQuitOption();

        String isbn = bookStoreInputReader.readNextLineWithQuitOption();

        InputField isbnField = new InputField();
        isbnField.setField(InputFieldConstants.ISBN);
        isbnField.setValue(isbn);

        inputIsbnValidator.validate(isbnField);

        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_QUANTITY);
        bookStorePrinter.printQuitOption();

        String quantity = bookStoreInputReader.readNextLineWithQuitOption();

        InputField quantityField = new InputField();
        quantityField.setField(InputFieldConstants.QUANTITY);
        quantityField.setValue(quantity);

        inputQuantityValidator.validate(quantityField);


        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_PRICE_MESSAGE);
        bookStorePrinter.printQuitOption();

        String price = bookStoreInputReader.readNextLineWithQuitOption();

        InputField priceField = new InputField();
        priceField.setField(InputFieldConstants.PRICE);
        priceField.setValue(price);

        inputPriceValidator.validate(priceField);


        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_PUBLISHER_MESSAGE);
        bookStorePrinter.printQuitOption();

        String publisher = bookStoreInputReader.readNextLineWithQuitOption();

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

    @Override
    public GetBookByIdRequestDTO buildGetBookByIdRequestDTO() {
        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_RECORD_ID_MESSAGE);
        bookStorePrinter.printQuitOption();

        String id = bookStoreInputReader.readNextLineWithQuitOption();

        InputField recordIdField = new InputField();
        recordIdField.setField(InputFieldConstants.BOOK_RECORD_ID);
        recordIdField.setValue(id);

        inputIdValidator.validate(recordIdField);

        GetBookByIdRequestDTO requestDTO = new GetBookByIdRequestDTO();
        requestDTO.setBookRecordId(Long.parseLong(id));
        return requestDTO;
    }

    @Override
    public GetBooksByAuthorRequestDTO buildGetBooksByAuthorRequestDTO() {
        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_AUTHOR_MESSAGE);
        bookStorePrinter.printQuitOption();

        String author = bookStoreInputReader.readNextLineWithQuitOption();

        InputField authorField = new InputField();
        authorField.setField(InputFieldConstants.AUTHOR);
        authorField.setValue(author);

        inputNameValidator.validate(authorField);

        GetBooksByAuthorRequestDTO requestDTO = new GetBooksByAuthorRequestDTO();
        requestDTO.setAuthor(author);
        return requestDTO;
    }

    @Override
    public GetBooksByTitleRequestDTO buildGetBooksByTitleRequestDTO() {
        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_TITLE_MESSAGE);
        bookStorePrinter.printQuitOption();

        String title = bookStoreInputReader.readNextLineWithQuitOption();

        InputField titleField = new InputField();
        titleField.setField(InputFieldConstants.TITLE);
        titleField.setValue(title);

        inputNameValidator.validate(titleField);

        GetBooksByTitleRequestDTO requestDTO = new GetBooksByTitleRequestDTO();
        requestDTO.setTitle(title);
        return requestDTO;
    }

    @Override
    public UpdateBookByIdRequestDTO buildUpdateBookByIdRequestDTO() {



        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_RECORD_ID_MESSAGE);
        bookStorePrinter.printQuitOption();



        String id = bookStoreInputReader.readNextLineWithQuitOption();


        InputField recordIdField = new InputField();
        recordIdField.setField(InputFieldConstants.BOOK_RECORD_ID);
        recordIdField.setValue(id);
        inputIdValidator.validate(recordIdField);


        bookStorePrinter.print(BookInputServiceConstants.LEAVE_BLANK_MESSAGE);
        bookStorePrinter.printQuitOption();

        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_TITLE_MESSAGE);
        BookInputServiceImpl.this.bookStorePrinter.printQuitOption();

        String title = bookStoreInputReader.readNextLineWithQuitOption();

        InputField titleField = new InputField();
        titleField.setField(InputFieldConstants.TITLE);
        titleField.setValue(title);

        if(!title.isBlank()){
            inputNameValidator.validate(titleField);
        }

        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_AUTHOR_MESSAGE);
        BookInputServiceImpl.this.bookStorePrinter.printQuitOption();

        String author = bookStoreInputReader.readNextLineWithQuitOption();

        InputField authorField = new InputField();
        authorField.setField(InputFieldConstants.AUTHOR);
        authorField.setValue(author);

        if(!author.isBlank()){
            inputNameValidator.validate(authorField);
        }

        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_ISBN_MESSAGE);
        BookInputServiceImpl.this.bookStorePrinter.printQuitOption();

        String isbn = bookStoreInputReader.readNextLineWithQuitOption();

        InputField isbnField = new InputField();
        isbnField.setField(InputFieldConstants.ISBN);
        isbnField.setValue(isbn);

        if(!isbn.isBlank()){
            inputIsbnValidator.validate(isbnField);
        }



        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_PRICE_MESSAGE);
        BookInputServiceImpl.this.bookStorePrinter.printQuitOption();

        String price = bookStoreInputReader.readNextLineWithQuitOption();

        InputField priceField = new InputField();
        priceField.setField(InputFieldConstants.PRICE);
        priceField.setValue(price);

        if(!price.isBlank()){
            inputPriceValidator.validate(priceField);
        }


        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_PUBLISHER_MESSAGE);
        BookInputServiceImpl.this.bookStorePrinter.printQuitOption();

        String publisher = bookStoreInputReader.readNextLineWithQuitOption();

        InputField publisherField = new InputField();
        publisherField.setField(InputFieldConstants.PUBLISHER);
        publisherField.setValue(publisher);

        if (!publisher.isBlank()){
            inputNameValidator.validate(publisherField);
        }


        UpdateBookByIdRequestDTO requestDTO = new UpdateBookByIdRequestDTO();
        requestDTO.setBookRecordId(id);
        requestDTO.setTitle(title);
        requestDTO.setAuthor(author);
        requestDTO.setIsbn(isbn);
        requestDTO.setPrice(price);
        requestDTO.setPublisher(publisher);
        return requestDTO;
    }

    @Override
    public UpdateBookQuantityByIdRequestDTO buildUpdateBookQuantityByIdRequestDTO() {
        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_RECORD_ID_MESSAGE);
        bookStorePrinter.printQuitOption();



        String id = bookStoreInputReader.readNextLineWithQuitOption();


        InputField recordIdField = new InputField();
        recordIdField.setField(InputFieldConstants.BOOK_RECORD_ID);
        recordIdField.setValue(id);
        inputIdValidator.validate(recordIdField);



        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_QUANTITY);
        bookStorePrinter.printQuitOption();

        String quantity = bookStoreInputReader.readNextLineWithQuitOption();

        InputField quantityField = new InputField();
        quantityField.setField(InputFieldConstants.TITLE);
        quantityField.setValue(quantity);

        inputQuantityValidator.validate(quantityField);

        UpdateBookQuantityByIdRequestDTO requestDTO = new UpdateBookQuantityByIdRequestDTO();
        requestDTO.setBookRecordId(id);
        requestDTO.setQuantity(quantity);
        return requestDTO;
    }
}
