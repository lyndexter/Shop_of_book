package com.lyndexter.bookshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookShopIntegrationTests {

  @Autowired MockMvc mockMvc;

  //  @MockBean private BookService service;

  @Test
  public void getBookTest() throws Exception {
    mockMvc
        .perform(get("/book_shop/book/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Wrapsafe"));
  }

  @Test
  public void postBookTest() throws Exception{
    mockMvc
        .perform(
            post("/book_shop/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    " {\n"
                        + "  \"name\": \"string\",\n"
                        + "  \"numberOfPages\": 233,\n"
                        + "  \"year\": 1965\n"
                        + "}"))
        .andExpect(status().isCreated())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//        .andExpect(jsonPath("$.name").value("Wrapsafe"));
  }

  @Test
  void contextLoads() {}
}
