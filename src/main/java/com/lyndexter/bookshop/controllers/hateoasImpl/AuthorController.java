package com.lyndexter.bookshop.controllers.hateoasImpl;

import com.lyndexter.bookshop.controllers.ControllerWithDto;
import com.lyndexter.bookshop.dto.AuthorDto;
import com.lyndexter.bookshop.models.Author;
import com.lyndexter.bookshop.services.implementations.AuthorService;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("book_shop/author")
public class AuthorController implements ControllerWithDto<AuthorDto, Author> {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDto> getAuthor(@PathVariable Integer id) {
    AuthorDto authorDto = createDto(authorService.getEntity(id));
    return new ResponseEntity<>(authorDto, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<AuthorDto>> getAuthors() {
    List<AuthorDto> authorDtoList = createDtos(authorService.getEntities());
    return new ResponseEntity<>(authorDtoList, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<AuthorDto> createAuthor(@RequestBody Author author) {
    author = authorService.createEntity(author);
    AuthorDto authorDto = createDto(author);
    return new ResponseEntity<>(authorDto, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorDto> updateAuthor(
      @RequestBody Author author, @PathVariable Integer id) {
    author = authorService.updateEntity(author, id);
    AuthorDto authorDto = createDto(author);
    return new ResponseEntity<>(authorDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AuthorDto> deleteAuthor(@PathVariable Integer id) {
    authorService.deleteEntity(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public AuthorDto createDto(Author entity) {
    Link selfLink =
        linkTo(methodOn(AuthorController.class).getAuthor(entity.getId())).withSelfRel();
    return new AuthorDto(selfLink, entity);
  }

  @Override
  public List<AuthorDto> createDtos(Iterable<Author> entities) {
    Link link = linkTo(methodOn(AuthorController.class).getAuthors()).withRel("list");
    List<AuthorDto> authorDtoList = new ArrayList<>();
    for (Author entity : entities) {
      Link selfLink = Link.of(link.getHref() + "/" + entity.getId());
      AuthorDto authorDto = new AuthorDto(selfLink, entity);
      authorDtoList.add(authorDto);
    }
    return authorDtoList;
  }
}
