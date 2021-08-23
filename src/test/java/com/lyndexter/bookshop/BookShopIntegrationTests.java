package com.lyndexter.bookshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookShopIntegrationTests {

  @Autowired MockMvc mockMvc;

  //  @MockBean private BookService service;

  @WithMockUser(value = "user1", password = "1111")
  @Test
  public void getBookTest() throws Exception {
    mockMvc
        .perform(get("/book_shop/book/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
        .andExpect(jsonPath("$.name").value("Wrapsafe"));
  }

  @WithMockUser(value = "user1", password = "1111")
  @Test
  public void postBookTest() throws Exception {
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
        .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON))
        .andExpect(jsonPath("$.name").value("string"));
  }
}
