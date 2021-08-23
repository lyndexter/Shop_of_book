package com.lyndexter.bookshop;

import com.lyndexter.bookshop.models.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookShopRestTemplateIntegrationTest {
  @Autowired private TestRestTemplate restTemplate;



  @Test
  public void getBookTest() throws Exception {
    // only with basic auth
    ResponseEntity<Book> bookResponseEntity =
        restTemplate.withBasicAuth("user1", "1111").getForEntity("/book_shop/book/1", Book.class);
    assertEquals(HttpStatus.OK, bookResponseEntity.getStatusCode());
  }
}
