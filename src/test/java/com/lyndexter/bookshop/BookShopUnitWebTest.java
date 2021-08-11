package com.lyndexter.bookshop;

import com.lyndexter.bookshop.controllers.BookController;
import com.lyndexter.bookshop.models.Book;
import com.lyndexter.bookshop.services.implementations.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookShopUnitWebTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService service;


  @Test
  public void getBookTest() throws Exception {

    Book book_sample = new Book();
    book_sample.setName("test");
    book_sample.setYear(1962);
    book_sample.setId(1);
    book_sample.setNumberOfPages(435);

    given(service.getEntity(book_sample.getId())).willReturn(book_sample);

    mockMvc
            .perform(get("/book_shop/book/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(jsonPath("$.year").value(1962));
  }
}
