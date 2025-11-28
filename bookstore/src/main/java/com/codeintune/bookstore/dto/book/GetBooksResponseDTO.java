package com.codeintune.bookstore.dto.book;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GetBooksResponseDTO {

    private List<GetBookResponseDTO> books;
}
