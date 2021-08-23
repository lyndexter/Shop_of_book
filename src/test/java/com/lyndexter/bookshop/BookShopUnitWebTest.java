package com.lyndexter.bookshop;

import com.lyndexter.bookshop.configurations.security.JwtTokenUtil;
import com.lyndexter.bookshop.configurations.security.filters.JwtTokenFilter;
import com.lyndexter.bookshop.controllers.hateoasImpl.BookController;
import com.lyndexter.bookshop.models.Book;
import com.lyndexter.bookshop.models.User;
import com.lyndexter.bookshop.repositories.UserRepository;
import com.lyndexter.bookshop.services.implementations.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@Import({UserRepository.class, JwtTokenUtil.class, JwtTokenFilter.class})
public class BookShopUnitWebTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private BookService service;
  @MockBean private UserRepository userRepository;

  @WithMockUser(value = "user1", password = "1111")
  @Test
  public void getBookTest() throws Exception {

    Book bookSample = new Book();
    bookSample.setName("test");
    bookSample.setYear(1962);
    bookSample.setId(1);
    bookSample.setNumberOfPages(435);

    given(service.getEntity(bookSample.getId())).willReturn(bookSample);
    given(userRepository.findByUsername("user1"))
        .willReturn(java.util.Optional.of(new User("user1", "1111")));

    mockMvc
        .perform(get("/book_shop/book/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
        .andExpect(jsonPath("$.name").value("test"))
        .andExpect(jsonPath("$.year").value(1962));
  }
}
