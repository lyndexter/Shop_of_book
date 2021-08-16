package com.lyndexter.bookshop;

import com.lyndexter.bookshop.controllers.BookController;
import com.lyndexter.bookshop.dto.BookDto;
import com.lyndexter.bookshop.models.Book;
import com.lyndexter.bookshop.services.implementations.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookShopUnitTests {

  @MockBean private BookService service;

  private Book bookSample;

  @Before
  public void setUp() {
    bookSample = new Book();
    bookSample.setName("test");
    bookSample.setYear(1962);
    bookSample.setId(1);
    bookSample.setNumberOfPages(435);

    when(service.getEntity(bookSample.getId())).thenReturn(bookSample);
  }

  @Test
  public void getBookTest() {
    BookController bookController = new BookController(service);

    ResponseEntity<BookDto> found_book = bookController.getBook(bookSample.getId());
    BookDto bookDtoSample = bookController.createDto(bookSample);
    assertThat(found_book.getBody()).isEqualTo(bookDtoSample);
  }
}
