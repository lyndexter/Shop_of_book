package com.lyndexter.bookshop.controllers.hateoasImpl;

import com.lyndexter.bookshop.controllers.ControllerWithDto;
import com.lyndexter.bookshop.dto.BookDto;
import com.lyndexter.bookshop.models.Book;
import com.lyndexter.bookshop.services.implementations.BookService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("book_shop/book")
public class BookController implements ControllerWithDto<BookDto, Book> {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> getBook(@PathVariable Integer id) {
    Book book = bookService.getEntity(id);
    BookDto bookDto = createDto(book);
    return new ResponseEntity<>(bookDto, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<BookDto>> getBooks() {
    List<BookDto> bookDtoList = createDtos(bookService.getEntities());
    return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BookDto> createBook(@RequestBody Book book) {
    book = bookService.createEntity(book);
    BookDto bookDto = createDto(book);
    return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDto> updateBook(@RequestBody Book book, @PathVariable Integer id) {
    book = bookService.updateEntity(book, id);
    BookDto bookDto = createDto(book);
    return new ResponseEntity<>(bookDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BookDto> deleteBook(@PathVariable Integer id) {
    bookService.deleteEntity(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public BookDto createDto(Book entity) {
    Link selfLink = linkTo(methodOn(BookController.class).getBook(entity.getId())).withSelfRel();
    return new BookDto(selfLink, entity);
  }

  @Override
  public List<BookDto> createDtos(Iterable<Book> entities) {
    Link link = linkTo(methodOn(BookController.class).getBooks()).withRel("list");
    List<BookDto> bookDtoList = new ArrayList<>();
    for (Book entBook : entities) {
      Link selfLink = Link.of(link.getHref() + "/" + entBook.getId());
      BookDto bookDto = new BookDto(selfLink, entBook);
      bookDtoList.add(bookDto);
    }
    return bookDtoList;
  }
}
