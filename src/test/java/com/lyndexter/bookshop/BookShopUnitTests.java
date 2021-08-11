package com.lyndexter.bookshop;

import com.lyndexter.bookshop.controllers.BookController;
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

  private Book book_sample;


  @Before
  public void setUp() {
    book_sample = new Book();
    book_sample.setName("test");
    book_sample.setYear(1962);
    book_sample.setId(1);
    book_sample.setNumberOfPages(435);

    when(service.getEntity(book_sample.getId())).thenReturn(book_sample);
  }

  @Test
  public void getBookTest() {
    BookController bookController = new BookController(service);

    ResponseEntity<Book> found_book = bookController.getBook(book_sample.getId());
    assertThat(found_book.getBody()).isEqualTo(book_sample);
  }
}
